package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.Random;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class BaseUtils {

	public static WebDriverWait wait;
	public static JSONObject jobj;
	public static JSONObject jobjPageName;
	public static JSONObject jobjTestSet;
	public static XSSFWorkbook workbook;
	private static Logger log = LogManager.getLogger(BaseUtils.class.getName());
	private static Map<String, String> DataTable = new HashMap<String, String>();
	public static WebDriver driver;
	
// read data from properties files ****************************************************
	public static String readProperty (String fileName,String key){
		String fPath = System.getProperty("user.dir") + "/src/main/resources/"+fileName+".properties";
		File file = new File (fPath);
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Properties prop = new Properties ();
		try {
			prop.load(fis);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String value = prop.getProperty(key);
		return value;	
	}
// add data to properties files *******************************************************
	public static void addPropData(String fileName, String keyName, String keyValue ) throws IOException {
		String fPath = System.getProperty("user.dir") + "/src/main/java/resources/"+fileName+".properties";
	    
		//1. Convert properties data to HashMap ***********************
		Map mapProp = new HashMap();
		// create file input stream object, to make file as readable by machine
		FileInputStream fis = new FileInputStream(fPath);
		// create properties class object to access all non-static methods
		Properties prop = new Properties();
		// load the required .properties file
		prop.load(fis);
		// close the file as Properties class object have all the details
		fis.close();
		// Properties work like HashTable, so we have to handle like hash table only
		Enumeration enumKeys = prop.keys();
		// iterate till the enumKeys has keys
		while (enumKeys.hasMoreElements()) {
			// move from null to first element(key), by default it will not point to first element
			String key = (String) enumKeys.nextElement();
			// fetch the property for the key
			String value = prop.getProperty(key);
			// store the key and value in map
			mapProp.put(key, value);
		}		
		mapProp.put(keyName, keyValue);
		System.out.println("Key: " + keyName + " and Value: " + keyValue + " are added to "+fileName+".properties file");		
		
		//2. Write Hashmap to property file *************************
		
		File file = new File(fPath);
		FileOutputStream outStream = new FileOutputStream (file);
		Properties prop1 = new Properties();
		
		Iterator keys = (Iterator) mapProp.keySet().iterator();
		while (keys.hasNext()) {
			String key = (String) keys.next();
			String value = (String) mapProp.get(key);
			prop1.setProperty(key, value);
		}
		prop1.store(outStream, null);
	}
// ***********************************************************************************			
// read data from Json ********************************************************************
	public static void initilizeJSON (String fileName,String pageName,String TestSet) {	
		String filePath = System.getProperty("user.dir") + "/src/test/resources/"+fileName+".json";
		JSONParser  jparser = new JSONParser();
		Object obj = null;
		try {
			obj = jparser.parse(new FileReader(filePath));
		} catch (IOException | org.json.simple.parser.ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		jobj = (JSONObject) obj;	
		jobjPageName = (JSONObject) jobj.get(pageName);
		jobjTestSet = (JSONObject) jobjPageName.get(TestSet);
	}
	
	public static String getJSONData (String keyName) {		
		String keyValue = (String) jobjTestSet.get(keyName);
		return keyValue;
	}
// ******************************************************************************************	
// 1st method to read data from Excel *******************************************************
	public static void connectToExcel (String filePath) throws IOException {
		FileInputStream file = new FileInputStream(filePath);
		workbook = new XSSFWorkbook(file);
	}
	
	public static ArrayList <String> getExcelData (String sheetName, String columnName,String rowName ) {
		// TODO Auto-generated method stub
	ArrayList <String>	excelData = new ArrayList <String>();	
	boolean sheetFound = false;
	int sheetsCount = workbook.getNumberOfSheets();
	for(int i=0; i<sheetsCount; i++){
		if(workbook.getSheetName(i).equalsIgnoreCase(sheetName)) {// get sheet
			System.out.println("sheet "+sheetName+" is found");
			sheetFound = true;
			XSSFSheet sheet = workbook.getSheetAt(i);
			Iterator <Row> rows = sheet.rowIterator();
			Row rowIndex = rows.next();// get next row
			Iterator <Cell> cells = rowIndex.cellIterator();
			
			boolean columnFound = false;
			int columnIndex = 0;		
			while(cells.hasNext()) {
				Cell cellIndex = cells.next();
				if (cellIndex.getStringCellValue().equalsIgnoreCase(columnName)) {// get column 
					columnFound = true;
					break;
				}
				columnIndex++;
			}
			if (columnFound==true) {
				System.out.println("column "+columnName+" is found");
				System.out.println("column: "+ columnName+ " has index "+columnIndex);
				boolean rowFound = false;
				while(rows.hasNext()) {
					rowIndex =rows.next();//move to the next row
					
					if(rowIndex.getCell(columnIndex).getStringCellValue().equalsIgnoreCase(rowName)) {
					rowFound = true;
					System.out.println("row "+rowName+" is found");
					cells = rowIndex.cellIterator();
					while(cells.hasNext()) {
						Cell cellIndex = cells.next();
						if (cellIndex.getCellType()==CellType.STRING) {
							excelData.add(cellIndex.getStringCellValue());
						}
						else {
							excelData.add(NumberToTextConverter.toText(cellIndex.getNumericCellValue()));
						}
					}
					}
				}
				if(rowFound == false) {
					System.out.println("row "+rowName+" not found");
					excelData=null;	
				}
			}
			else {
				System.out.println("column "+columnName+" not found");
				excelData=null;
			}
		}
	if(sheetFound==true) {
		break;
				}
	}
	if(sheetFound ==false) {
		System.out.println("sheet "+sheetName+" not found");
		excelData = null;
	}
	return excelData;			
	}	

	public static Integer getMonthNumber (String monthName) throws ParseException {
	Date date = new SimpleDateFormat("MMMM").parse(monthName);
	Calendar cal = Calendar.getInstance();
	cal.setTime(date);
	return cal.get(Calendar.MONTH)+1;
	}
// *******************************************************************************************	
// 2nd method to read data from Excel*********************************************************
	public static void Import(String FileName, String SheetName, int RowNumber) {

		// Create an object of File class to open xlsx file
		File file = new File(FileName);
		// Create an object of FileInputStream class to read excel file
		FileInputStream inputStream = null;
		try {
			inputStream = new FileInputStream(file);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		Workbook excelWorkbook = null;
		// Find the file extension by splitting file name in substring and getting only
		// extension name
		String fileExtensionName = FileName.substring(FileName.indexOf("."));

		// Check condition if the file is xlsx file
		if (fileExtensionName.equals(".xlsx")) {
			// If it is xlsx file then create object of XSSFWorkbook class
			try {
				excelWorkbook = new XSSFWorkbook(inputStream);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		// Check condition if the file is xls file
		else if (fileExtensionName.equals(".xls")) {
			// If it is xls file then create object of XSSFWorkbook class
			try {
				excelWorkbook = new HSSFWorkbook(inputStream);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		// search for a sheet by SheetName
		boolean sheetFound = false;
		int sheetsCount = excelWorkbook.getNumberOfSheets();
		for (int i = 0; i < sheetsCount; i++) {
			if (excelWorkbook.getSheetName(i).equalsIgnoreCase(SheetName)) {// get sheet
				sheetFound = true;
				Sheet excelSheet = excelWorkbook.getSheetAt(i);
				// Read row0
				Row row0 = excelSheet.getRow(0);
				// Read rowNumber and store in map as a key (column name) and a value (cell
				// string value)
				if (RowNumber <= excelSheet.getLastRowNum()) {
					Row row1 = excelSheet.getRow(RowNumber);
					for (int j = 0; j < row0.getLastCellNum(); j++) {
						Cell cell1 = row1.getCell(j);
						DataTable.put(row0.getCell(j).getStringCellValue(), getCellValueAsString(cell1));
						// Print Excel data in console
						// System.out.println(row0.getCell(j).getStringCellValue() + " = " +
						// getCellValueAsString(cell1));
					}
				} else {
					log.error("Total number of rows is " + excelSheet.getLastRowNum() + ". There is no row with number "
							+ RowNumber);
					System.exit(0);
				}
			}
			if (!sheetFound) {
				log.error("There is no sheet with name " + SheetName);
				System.exit(0);
			}
		}
	}

	public static String Value(String KeyName) {
		String strValue = "";
		strValue = DataTable.get(KeyName);
		if (strValue == null) {
			log.error("There is no column with name " + KeyName);
			System.exit(0);
		}
		return strValue;
	}

	//convert different type of data to string
	public static String getCellValueAsString(Cell cell) {
		String strCellValue = null;

		if (cell != null) {
			switch (cell.getCellType()) {
			case STRING:
				strCellValue = cell.toString();
				break;
			case NUMERIC:
				if (DateUtil.isCellDateFormatted(cell)) {
					SimpleDateFormat dateFormat = new SimpleDateFormat("dd/mm/yyyy");
					strCellValue = dateFormat.format(cell.getDateCellValue());
				} else {
					Double value = cell.getNumericCellValue();
					Long longValue = value.longValue();
					strCellValue = new String(longValue.toString());
				}
				break;
			case BOOLEAN:
				strCellValue = new String(new Boolean(cell.getBooleanCellValue()).toString());
				break;
			case BLANK:
				strCellValue = "";
				break;
			default:
				break;
			}
		} else
			strCellValue = "";
		return strCellValue;
	}	
// *******************************************************************************************	
	public static String GetRandomNum (int countDigit) {
		Random objGenerator = new Random();
	    String generatedText = "";
	  	for (int iCount = 0; iCount< countDigit; iCount++){
	        int randomNumber = objGenerator.nextInt(10);
	        generatedText = generatedText + randomNumber;
	       }	
	  	return generatedText;
	  	
	}
// get screenshot method ********************************************************************	
	public static void getScreenShot(String fileName) {
		WebDriver driver = MyDriverClass.getDriver();
		File src = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		String timeStamp = new SimpleDateFormat("MM_dd-HH_mm_ss").format(Calendar.getInstance().getTime());
		try {
			FileUtils.copyFile(src, new File(System.getProperty("user.dir") + "/FailedTestsScreenshoots/"+timeStamp+"_"+fileName+".png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}  
// explicit wait for an element to be visible *************************************************	
	public static WebElement getWhenVisible(By locator, int timeout) {
		driver = MyDriverClass.getDriver();
		WebElement element = null;
		wait = new WebDriverWait(driver, timeout);
		element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		return element;	
	}
	// explicit wait for an element to be clickable *************************************************		
	public static void clickWhenReady(By locator, int timeout) {
		driver = MyDriverClass.getDriver();
		WebElement element = null;
		wait = new WebDriverWait(driver, timeout);
		element = wait.until(ExpectedConditions.elementToBeClickable(locator));
		element.click();
	}
}
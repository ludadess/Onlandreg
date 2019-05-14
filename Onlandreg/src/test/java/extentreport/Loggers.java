package extentreport;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.aventstack.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import stepsdefinition.SDCommon;

public class Loggers extends ExtentReportUtils {
    private static Logger log = LogManager.getLogger(Loggers.class.getName());
    public static ExtentTest logInfo;
    
    public static void info(String message) {
        log.info(message);
        logInfo.info(message);
        //ExtentTestManager.getTest().log(LogStatus.INFO, "", message);
    }
    public static void error(String message) {
        log.error(message);
        logInfo.error(message);
        //ExtentTestManager.getTest().log(LogStatus.FAIL, message, "error");
    }
    public static void warn(String message) {
        log.warn(message);
        logInfo.warning(message);
        //ExtentTestManager.getTest().log(LogStatus.WARNING,message, "warning");
    }
    public static void fatal(String message) {
        log.fatal(message);
        logInfo.fatal(message);
        //ExtentTestManager.getTest().log(LogStatus.FATAL,message, "error");
    }

}
Feature: Search Property
As a guest user

Scenario: One
Given The user is on the Home page
When The user selects LRO "80"
When The user clicks on the Property Search button
Then "Search Property By PIN - Ontario Land Registry Access" page is dispalyed

Scenario: Search Property by PIN
Given The user is on the "Search Property By PIN - Ontario Land Registry Access" page
When The user enters "PIN" to search property by PIN
Then Results for search property by "PIN" are shown

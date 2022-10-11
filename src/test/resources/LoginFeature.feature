Feature: Login to salesforce
  Checking the login feature of salesforce application

  Scenario: Login failure with no password
  	Given when i open URL "https://login.salesforce.com"
  	When i enter value for username as "correctemail@gmail.com" and pasword as ""
  	And click on the login button
  	Then i see login failure as "Please enter your password."

	Scenario: Login success
  	Given when i open URL "https://login.salesforce.com"
  	When i enter value for username as "correctemail@gmail.com" and pasword as "<<correctpwd>>"
  	And click on the login button
  	Then i should get page with title as "Home Page"
	
	Scenario: Login with rememberme
  	Given when i open URL "https://login.salesforce.com"
  	When i enter value for username as "correctemail@gmail.com" and pasword as "<<correctpwd>>"
  	And i check rememberMe checkbox
  	And click on the login button
  	And click on the logout button
  	Then i should get login page with username as "correctemail@gmail.com"
		
	Scenario: Forgot password
  	Given when i open URL "https://login.salesforce.com"
  	When i click on forgot password link
  	And i enter value for username as "correctemail@gmail.com"
  	And click on the Continue button
  	Then i should get success page for password reset

  Scenario: Login failure with password
  	Given when i open URL "https://login.salesforce.com"
  	When i enter value for username as "correctemail@gmail.com" and pasword as "dummypwd"
  	And click on the login button
  	Then i see login failure as "Please check your username and password. If you still can't log in, contact your Salesforce administrator."
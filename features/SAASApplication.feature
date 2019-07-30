Feature: Test SAAS smoke scenario
Scenario: Test login with valid credentials
Given Open chrome and start application
When User enter admin username and password
And User able to create new organization
Then user should able to see the created organization

@PMS
Scenario: Test confirmation workflow
Given Open chrome and start PMS application
When Login as HR on PMS application
Then HR should able to see the PMS home page
When HR initiate an employee on confirmation review page
Then HR should able to initiate a employee successfully
When Reviewer login into pms application
Then Reviewer should able to see the initiated employee details on confirmation review initiation page
And Reviewe should able to save the template
Feature: HRMS New User Validation

Scenario: Create new user with all details
Given Open chrome and start HRMS application
When Login as HR on HRMS application
Then HR should able to see the HRMS home page
And  create a new user and verify
Then HR should able to see created user
And Login as created user and update the details
Then User should able to see the updated details 
And Logout the application and login as HR
Then Verify the created employee details

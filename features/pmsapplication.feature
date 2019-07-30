Feature: Test PMS smoke scenarios

Scenario: Test confirmation workflow with level 1
Given Open chrome and start PMS application
When Login as HR on PMS application
Then HR should able to see the PMS home page
And Initiate an employee on confirmation review page
Then HR should able to initiate a employee successfully
When Reviewer login into pms application and initiate reviewee to level 1
Then level 1 should able to rating for reviewee and submit
And Reviewer save the reviewees template
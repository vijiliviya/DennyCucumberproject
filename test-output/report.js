$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("features/hrmsapplication.feature");
formatter.feature({
  "line": 1,
  "name": "HRMS New User Validation",
  "description": "",
  "id": "hrms-new-user-validation",
  "keyword": "Feature"
});
formatter.before({
  "duration": 4085829380,
  "status": "passed"
});
formatter.scenario({
  "line": 3,
  "name": "Create new user with all details",
  "description": "",
  "id": "hrms-new-user-validation;create-new-user-with-all-details",
  "type": "scenario",
  "keyword": "Scenario"
});
formatter.step({
  "line": 4,
  "name": "Open chrome and start HRMS application",
  "keyword": "Given "
});
formatter.step({
  "line": 5,
  "name": "Login as HR on HRMS application",
  "keyword": "When "
});
formatter.step({
  "line": 6,
  "name": "HR should able to see the HRMS home page",
  "keyword": "Then "
});
formatter.step({
  "line": 7,
  "name": "create a new user and verify",
  "keyword": "And "
});
formatter.step({
  "line": 8,
  "name": "HR should able to see created user",
  "keyword": "Then "
});
formatter.step({
  "line": 9,
  "name": "Login as created user and update the details",
  "keyword": "And "
});
formatter.step({
  "line": 10,
  "name": "User should able to see the updated details",
  "keyword": "Then "
});
formatter.step({
  "line": 11,
  "name": "Logout the application and login as HR",
  "keyword": "And "
});
formatter.step({
  "line": 12,
  "name": "Verify the created employee details",
  "keyword": "Then "
});
formatter.match({
  "location": "hrmsapplication.open_chrome_and_start_HRMS_application()"
});
formatter.result({
  "duration": 101361464,
  "status": "passed"
});
formatter.match({
  "location": "hrmsapplication.login_as_HR_on_HRMS_application()"
});
formatter.result({
  "duration": 20481,
  "status": "passed"
});
formatter.match({
  "location": "hrmsapplication.hr_should_able_to_see_the_HRMS_home_page()"
});
formatter.result({
  "duration": 16213,
  "status": "passed"
});
formatter.match({
  "location": "hrmsapplication.create_a_new_user_and_verify()"
});
formatter.result({
  "duration": 18204,
  "status": "passed"
});
formatter.match({
  "location": "hrmsapplication.hr_should_able_to_see_created_user()"
});
formatter.result({
  "duration": 13938,
  "status": "passed"
});
formatter.match({
  "location": "hrmsapplication.Login_as_created_user_and_update_the_details()"
});
formatter.result({
  "duration": 15360,
  "status": "passed"
});
formatter.match({
  "location": "hrmsapplication.User_should_able_to_see_the_updated_details()"
});
formatter.result({
  "duration": 21903,
  "status": "passed"
});
formatter.match({
  "location": "hrmsapplication.Logout_the_application_and_login_as_HR()"
});
formatter.result({
  "duration": 33279,
  "status": "passed"
});
formatter.match({
  "location": "hrmsapplication.Verify_the_created_employee_details()"
});
formatter.result({
  "duration": 91549404288,
  "error_message": "java.lang.NullPointerException\r\n\tat org.openqa.selenium.support.ui.Select.\u003cinit\u003e(Select.java:45)\r\n\tat com.app.commonFunctionsLibrary.CommonFunctions.selectDropdown(CommonFunctions.java:627)\r\n\tat pages.HRMS_Page.clickOnEmployeesSideMenuAndChooseActiveEmployee(HRMS_Page.java:454)\r\n\tat stepDefination.hrmsapplication.Verify_the_created_employee_details(hrmsapplication.java:127)\r\n\tat ✽.Then Verify the created employee details(features/hrmsapplication.feature:12)\r\n",
  "status": "failed"
});
formatter.after({
  "duration": 540058015,
  "status": "passed"
});
});
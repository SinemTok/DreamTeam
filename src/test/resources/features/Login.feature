
@login
Feature: login to HRM system

Scenario: login with valid credentials
Given I put valid username and password click login button

Then I should be able to see dashboard page


Scenario: login with invalid credentials 
Given I put invalid username and password
And click login button
Then I should navigate to RETRY LOGIN page and see the error message


Scenario: login with empty username and valid password
Given I leave the username box empty and send valid password
And click login button
Then I should be able to see USERNAME CANNOT BE EMPTY message


Scenario: login with empty password and valid username
Given I leave the password box empty and send valid username
And click login button
Then I should be able to see PASSWORD CANNOT BE EMPTY message

Scenario: login with empty password and username
Given I leave the password and username empty
And click login button
Then I should be able to see CANNOT BE EMPTY messages


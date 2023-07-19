Feature: Login feature
    As a user, I should be able to login.


  Scenario: Login with valid credentials
    Given I am on the homepage
    When I enter the valid credentials
    Then I should be able to login

  Scenario: Login with invalid credentials
    Given I am on the homepage
    When I enter the invalid credentials
    Then I should not be able to login
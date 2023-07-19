#tags can be added on top of a feature file too to include all scenarios
#@login
Feature: Login feature
    As a user, I should be able to login.

  @login @signup
  Scenario: Login with valid credentials
    Given I am on the homepage
    When I enter the valid credentials
    Then I should be able to login

  @login @invalid
  Scenario: Login with invalid credentials
    Given I am on the homepage
    When I enter the invalid credentials
    Then I should not be able to login
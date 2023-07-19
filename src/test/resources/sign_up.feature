Feature: Sign up feature

  @signup @login
  Scenario: Sign up with valid info
    Given I am on the homepage
    And I click on the sign up link
    When I fill up the fields with valid info and click sign up
    Then I should be able to sign up

  @signup
  Scenario: Sign up with valid info 2
    Given I am on the homepage
    And I click on the sign up link
    When I fill up the fields with valid info and click sign up
    Then I should be able to sign up

Feature: Demo

  @demo
  Scenario: Demo
    Given I have 12 "cakes"
    When I take 6.5 away
    Then I should have 5.5 left
    And I still feel happy


  @demo
  Scenario: Demo
    Given I have 34 "cukes"
    When I take 34 away
    Then I should have 0 left
    And He yet feels hungry
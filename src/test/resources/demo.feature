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

  @docString
  Scenario: Demo of string
    Given I have the the query
         """
          UPDATE User
          SET UserId = 12345
          , Name = 'J Doe'
          , Location = 'USA'
          , Bio='my bio
          spans
          multiple
          lines!'
          WHERE UserId = 12345
       """
    When send the query
    Then should have the result

  @duplicate
  Scenario: Demo of duplicate step defintion
    Given I navigate to the preapproval page
    When I click on "Browse" link
    Then I should see a price 23.55

@DB
Feature: Verify basic CRUD operations UI to DB flow

   @create
  Scenario: Sign up with valid user details and verify user creation  - CREATE
    Given I click on the sign up link
    When I fill up the fields with valid info and click sign up
    Then I should be able to sign up
    Then the system should create a new user in the "users" table of the database


   @read
  Scenario: Sign up with valid user details and verify user info - READ
    Given I click on the sign up link
    When I fill up the fields with the following info and click sign up
      | username    | first | last   | email                 | password |
      | troy.darwin | Troy  | Darwin | troydarwing@gmail.com | troyd123 |
    Then I should be able to sign up
    Then the created user info in the database should be the following
      | username    | first | last   | email                 | password |
      | troy.darwin | Troy  | Darwin | troydarwing@gmail.com | troyd123 |




  Scenario: Update user email and verify the update - UPDATE
    Given the user is on the login page
    When the user enters valid username as "duotech2023" and password as "duotech"
    Then the user is redirected to the home page
    And the user updates the email field to a new value "helloworld2050@gmail.com"
    Then the the success message should be displayed on the UI
    And the user email with username "duotech2023" is also updated in the database

  @DB_test
  Scenario: Create and Delete Playlist - DELETE
    Given the user is on the login page
    When the user enters valid username as "duotech2023" and password as "duotech"
    Then the user is redirected to the home page
    And the user creates a new playlist with the following details
      |playlist_name|
      |Study|
    Then the playlist should be created on the UI
    And the playlist should be created in the database and should belong to the user "duotech2023"
    When the user deletes the playlist with the following details
      |playlist_name|
      |Study|
    Then the playlist should be deleted on the UI
    And the playlist should be deleted in the database that belongs to the user "duotech2023"
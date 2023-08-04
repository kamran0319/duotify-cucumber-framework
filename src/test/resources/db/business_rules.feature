@DB
Feature: Verify business rules

    @db_only
  Scenario: verify user creation in db and validate on the ui  - CREATE
     When I retrieve the column names from the "songs" table
     Then it should have the following
       | id         |
       | title      |
       | artist     |
       | album      |
       | genre      |
       | duration   |
       | path       |
       | albumOrder |
       | plays      |



  @business_rule @db_only
  Scenario: verify email column for duplicates
    When I retrieve the emails from "users" table
    Then it should not contain duplicates
    Then it should not contain duplicates using sql query





  @delete_db
  Scenario: Create and Delete Playlist - DELETE
#    When I enter username as "duotech2023" and password as "duotech"
#    Then I should be able to login
#    And I create a new playlist with random name
#    Then the playlist should be created on the UI
#    When the user deletes the same playlist
#    Then the playlist should be deleted on the UI
#    And the playlist that belongs to the user "duotech2023" should be deleted in the database too
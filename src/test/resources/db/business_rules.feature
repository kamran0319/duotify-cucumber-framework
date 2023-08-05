@DB
Feature: Verify business rules

    @db_only
  Scenario: verify column names for songs table
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

  @genres @db_only
  Scenario: Verify genres
    When I send a query to retrieve genres from the db
    Then The result should contain the following genres
      | rap       |
      | pop       |
      | techno    |
      | rnb       |
      | house     |
      | classical |
      | jazz      |
      | electronic |
      | dance     |
      | reggae    |
      | reggaeton |


  @business_rule @db_only
  Scenario: verify email column for duplicates
    When I retrieve the emails from "users" table
    Then it should not contain duplicates
    Then it should not contain duplicates using sql query


   @playCount
  Scenario: Successful Song Playback
    Given I enter the valid credentials
    Then I should be able to login
    And I play a song "Whirls" from the album "Werk"
    Then the play count of the song in the database should be incremented by one
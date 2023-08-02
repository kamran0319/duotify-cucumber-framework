package stepDefinitions;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Assert;
import pages.*;
import utils.DBUtils;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class CrudOpsStepDefs {


    @When("I fill up the fields with the following info and click sign up")
    public void i_fill_up_the_fields_with_the_following_info_and_click_sign_up(List<Map<String, String>> dataTable) {

        SignUpPage signUpPage = new SignUpPage();

        System.out.println(dataTable);
        Map<String, String> row = dataTable.get(0);
        System.out.println(row);

        signUpPage.getUsername().sendKeys(row.get("username"));
        signUpPage.getFirstName().sendKeys(row.get("first"));
        signUpPage.getLastName().sendKeys(row.get("last"));
//
        signUpPage.getEmail().sendKeys(row.get("email"));
        signUpPage.getEmail2().sendKeys(row.get("email"));

        signUpPage.getPassword().sendKeys(row.get("password"));
        signUpPage.getPassword2().sendKeys(row.get("password"));
        signUpPage.getRegisterButton().click();


    }



    String expectedUsername;
    @Then("the created user info in the database should be the following")
    public void the_created_user_info_in_the_database_should_be_the_following(List<Map<String, String>> expected) throws SQLException {

        Map<String, String> expectedData = expected.get(0);
        expectedUsername = expectedData.get("username");
        String expectedFirst = expectedData.get("first");
        String expectedLast = expectedData.get("last");
        String expectedEmail = expectedData.get("email");
        String expectedPass = expectedData.get("password");  // troy123

        String expectedPassMd5 = DigestUtils.md5Hex(expectedPass);




//        System.out.println("Expected: " + expectedData);

        List<Map<String, Object>> listOfMaps = DBUtils.getQueryResultListOfMaps("select username, firstName, lastName, email, password  from users where username='" + expectedUsername + "'");

        Map<String, Object> actualDBData = listOfMaps.get(0);

//        System.out.println("Actual: " + actualDBData);

        try {
            Assert.assertEquals(expectedUsername, actualDBData.get("username"));
            Assert.assertEquals(expectedFirst, actualDBData.get("firstName"));
            Assert.assertEquals(expectedLast, actualDBData.get("lastName"));
//            Assert.assertEquals(expectedEmail, actualDBData.get("email"));
            Assert.assertEquals(expectedPassMd5, actualDBData.get("password"));
        }finally {
            DBUtils.executeUpdate("DELETE FROM users WHERE username='"+expectedUsername+"'");
        }

    }



    String expectedEmail;
    @When("I update the email field to a new value {string}")
    public void i_update_the_email_field_to_a_new_value(String newValue) throws InterruptedException {
        expectedEmail = newValue;
        new Homepage().clickOnUsernameLink();
        new SettingsPage().getUserDetailsButton().click();
        new UpdateDetailsPage().getEmailField().clear();
        new UpdateDetailsPage().getEmailField().sendKeys(newValue);

        new UpdateDetailsPage().getSaveButton().click();
        Thread.sleep(1000);

    }
    @Then("the the success message should be displayed on the UI")
    public void the_the_success_message_should_be_displayed_on_the_ui() throws InterruptedException {
          Assert.assertEquals("Update successful", new UpdateDetailsPage().getSuccessMessage().getText() );

    }
    @Then("the user email with username {string} is also updated in the database")
    public void the_user_email_with_username_is_also_updated_in_the_database(String username) {

        List<List<Object>> listOfList = DBUtils.getQueryResultAsListOfLists("SELECT email from users where username='" + username + "'");
        String actualEmail = (String)listOfList.get(0).get(0);

        System.out.println(expectedEmail);
        System.out.println(actualEmail);

        Assert.assertEquals(expectedEmail,actualEmail);



    }

    String namePlaylist;
    @Then("I create a new playlist with the following details")
    public void i_create_a_new_playlist_with_the_following_details(Map<String, String> dataTable) throws InterruptedException {
         new Homepage().clickOnLink("Your Music");
         new AllPlaylistsPage().getNewPlaylistButton().click();
         namePlaylist = dataTable.get("playlistName");
         new AllPlaylistsPage().enterPlaylistName(namePlaylist);
        Thread.sleep(2000);


    }
    @Then("the playlist should be created on the UI")
    public void the_playlist_should_be_created_on_the_ui() {
           Assert.assertTrue(new AllPlaylistsPage().getPlaylistByName(namePlaylist).isDisplayed());
    }
    @When("the user deletes the playlist with the following details")
    public void the_user_deletes_the_playlist_with_the_following_details(io.cucumber.datatable.DataTable dataTable) throws InterruptedException {
           new AllPlaylistsPage().clickPlaylistByName(namePlaylist);
           new PlaylistPage().getDeleteButton().click();
           new PlaylistPage().acceptDeleteAlert();
           Thread.sleep(2000);

    }
    @Then("the playlist should be deleted on the UI")
    public void the_playlist_should_be_deleted_on_the_ui() {



    }
    @Then("the playlist that belongs to the user {string} should be deleted in the database too")
    public void the_playlist_that_belongs_to_the_user_should_be_deleted_in_the_database_too(String string) {

    }






}

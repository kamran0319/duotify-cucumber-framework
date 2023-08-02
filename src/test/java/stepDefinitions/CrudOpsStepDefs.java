package stepDefinitions;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Assert;
import pages.SignUpPage;
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






}

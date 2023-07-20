package stepDefinitions;

import com.github.javafaker.Faker;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import pages.SignInPage;
import pages.SignUpPage;
import utils.Driver;

public class SignUpStepDefs {

    @Given("I click on the sign up link")
    public void i_click_on_the_sign_up_link() {
        SignInPage signInPage = new SignInPage();
        signInPage.getSignUpLink().click();
    }
    @When("I fill up the fields with valid info and click sign up")
    public void i_fill_up_the_fields_with_valid_info_and_click_sign_up() {
        Faker faker = new Faker();
        SignUpPage signUpPage = new SignUpPage();
        signUpPage.getUsername().sendKeys(faker.name().username());
        signUpPage.getFirstName().sendKeys(faker.name().firstName());
        signUpPage.getLastName().sendKeys(faker.name().lastName());
        String email = faker.internet().emailAddress();
        signUpPage.getEmail().sendKeys(email);
        signUpPage.getEmail2().sendKeys(email);
        String pass = faker.internet().password();
        signUpPage.getPassword().sendKeys(pass);
        signUpPage.getPassword2().sendKeys(pass);
        signUpPage.getRegisterButton().click();
    }
    @Then("I should be able to sign up")
    public void i_should_be_able_to_sign_up() {
        Assert.assertEquals("http://duotify.us-east-2.elasticbeanstalk.com/browse.php?", Driver.getDriver().getCurrentUrl());

    }
}

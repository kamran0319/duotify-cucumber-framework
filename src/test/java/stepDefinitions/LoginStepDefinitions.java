package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import pages.SignInPage;
import utils.ConfigReader;
import utils.Driver;

public class LoginStepDefinitions {


    @Given("I am on the homepage")
    public void i_am_on_the_homepage() {
        Driver.getDriver().get(ConfigReader.getProperty("url"));

    }
    @When("I enter the valid credentials")
    public void i_enter_the_valid_credentials() {
        SignInPage signInPage = new SignInPage();
        signInPage.getUsername().sendKeys(ConfigReader.getProperty("username"));
        signInPage.getPassword().sendKeys(ConfigReader.getProperty("password"));
        signInPage.getLoginButton().click();
    }
    @Then("I should be able to login")
    public void i_should_be_able_to_login() {
        Assert.assertEquals("http://duotify.us-east-2.elasticbeanstalk.com/browse.php?", Driver.getDriver().getCurrentUrl());
        Driver.quitDriver();
    }

    @When("I enter the invalid credentials")
    public void i_enter_the_invalid_credentials() {
        SignInPage signInPage = new SignInPage();
        signInPage.getUsername().sendKeys("username");
        signInPage.getPassword().sendKeys("password");
        signInPage.getLoginButton().click();
    }
    @Then("I should not be able to login")
    public void i_should_not_be_able_to_login() {
        Assert.assertNotEquals("http://duotify.us-east-2.elasticbeanstalk.com/browse.php?", Driver.getDriver().getCurrentUrl());
        Driver.quitDriver();
    }
}

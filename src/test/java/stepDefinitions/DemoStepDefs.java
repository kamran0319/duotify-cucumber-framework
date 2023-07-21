package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class DemoStepDefs {

    @Given("I have {int} {string}")
    public void i_have(Integer count, String veggie) {
        System.out.println("I have " + count + " " + veggie);
    }
    @When("I take {double} away")
    public void i_take_away(Double double1) {
        System.out.println("I took away " + double1);
    }
    @Then("I should have {double} left")
    public void i_should_have_left(Double double1) {
        System.out.println("I have left " + double1);
    }

    @Then("I/He still/yet feel(s) {word}")
    public void i_still_feel_hungry(String word) {
        System.out.println("I feel " + word);
    }
}

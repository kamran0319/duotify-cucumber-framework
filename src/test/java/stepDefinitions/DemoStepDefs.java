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


    @Given("I have the the query")
    public void i_have_the_the_query(String docString) {
        System.out.println(docString);
    }
    @When("send the query")
    public void send_the_query() {

    }
    @Then("should have the result")
    public void should_have_the_result() {

    }


    @When("I click on {string} link")
    public void iClickOnLink(String args) {
    }

//    @When("I click on {word} link")
//    public void iClickOnUserInfoLink() {
//
//    }

    @Then("I should see a price {double}")
    public void i_should_see_a_price(Double price) {

    }

//    @Then("I should see a price {float}")
//    public void i_should_see_a_pricedcs(Float price) {
//
//    }



}

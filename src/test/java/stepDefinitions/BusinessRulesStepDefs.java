package stepDefinitions;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import utils.DBUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BusinessRulesStepDefs {



    SharedData sharedData;

    public BusinessRulesStepDefs(SharedData sharedData) {
        this.sharedData = sharedData;
    }

    @When("I retrieve the column names from the {string} table")
    public void i_retrieve_the_column_names_from_the_table(String tableName) {

        List<List<Object>> list = DBUtils.getQueryResultAsListOfLists("describe " + tableName);
        System.out.println(list);

        sharedData.setDbColumnNames(new ArrayList<>());


        for (List<Object> each : list) {

            sharedData.getDbColumnNames().add((String)each.get(0));

        }



    }
    @Then("it should have the following")
    public void it_should_have_the_following(List<String> expected) {
        Assert.assertEquals(expected, sharedData.getDbColumnNames());
    }


    @When("I retrieve the emails from {string} table")
    public void i_retrieve_the_emails_from_table(String table) {
        List<List<Object>> result = DBUtils.getQueryResultAsListOfLists("select email FROM " + table);

         sharedData.setEmailsColumn(DBUtils.getColumnData(result, 0));



    }
    @Then("it should not contain duplicates")
    public void it_should_not_contain_duplicates() {
//          Assert.assertTrue("The list has duplicates: "  + sharedData.getEmailsColumn(), !hasDuplicate(sharedData.getEmailsColumn()));




    }

    public static boolean hasDuplicate(List<String> list) {

        Collections.sort(list);

        for (int i = 0; i < list.size()-1; i++) {
            if(list.get(i).equals(list.get(i+1))){
                return true;
            }
        }

        return false;
    }

    @Then("it should not contain duplicates using sql query")
    public void itShouldNotContainDuplicatesUsingSqlQuery() {

        List<List<Object>> list = DBUtils.getQueryResultAsListOfLists("select email, count(*) from users group by email having count(*)>1");
        Assert.assertTrue(list.isEmpty());
    }
}

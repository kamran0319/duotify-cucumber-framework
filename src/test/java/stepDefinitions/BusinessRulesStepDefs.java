package stepDefinitions;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import pages.AlbumPage;
import pages.Homepage;
import utils.DBUtils;
import utils.SeleniumUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

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


    @When("I send a query to retrieve genres from the db")
    public void i_send_a_query_to_retrieve_genres_from_the_db() {
        List<List<Object>> list = DBUtils.getQueryResultAsListOfLists("select name from genres");
        sharedData.setGenres(DBUtils.getColumnData(list, 0));
    }
    @Then("The result should contain the following genres")
    public void the_result_should_contain_the_following_genres(List<String > expected) {
           Assert.assertEquals(expected, sharedData.getGenres());
    }

    @Then("I play a song {string} from the album {string}")
    public void i_play_a_song_from_the_playlist(String song, String album) {
        
         String query = "select \n" +
                 "songs.title as songTitle,\n" +
                 "albums.title as albumTitle,\n" +
                 "songs.plays\n" +
                 "from songs\n" +
                 "join\n" +
                 "albums on songs.album=albums.id\n" +
                 "where songs.title='"+song+"' and albums.title='"+album+"'";
         sharedData.setSong(song);
         sharedData.setAlbum(album);

        List<Map<String, Object>> listOfMaps = DBUtils.getQueryResultListOfMaps(query);
         sharedData.setNoOfplays((Integer)listOfMaps.get(0).get("plays"));


        new Homepage().clickOnAlbum(album);
            new AlbumPage().clickOnPlayButton(song);
            SeleniumUtils.waitFor(1);


    }
    @Then("the play count of the song in the database should be incremented by one")
    public void the_play_count_of_the_song_in_the_database_should_be_incremented_by_one() {

        String query = "select \n" +
                "songs.title as songTitle,\n" +
                "albums.title as albumTitle,\n" +
                "songs.plays\n" +
                "from songs\n" +
                "join\n" +
                "albums on songs.album=albums.id\n" +
                "where songs.title='"+sharedData.getSong()+"' and albums.title='"+sharedData.getAlbum()+"'";

        List<Map<String, Object>> listOfMaps = DBUtils.getQueryResultListOfMaps(query);
        int actual = (Integer)listOfMaps.get(0).get("plays");

         Assert.assertEquals(sharedData.getNoOfplays()+1, actual);
    }
}

package stepDefinitions;

import io.cucumber.java.en.Then;
import io.cucumber.java.it.Ma;
import org.junit.Assert;
import pages.Homepage;
import pages.PlaylistPage;
import utils.SeleniumUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class AlbumStepDefs {


    @Then("the  following albums and their details should be displayed")
    public void the_following_albums_and_their_details_should_be_displayed(List<Map<String, String>> expected) throws InterruptedException {

        PlaylistPage playlistPage = new PlaylistPage();
        for (Map<String, String> playlist : expected) {

            playlistPage.clickOnPlaylist(playlist.get("Name"));
            Assert.assertEquals(playlist.get("Author"), playlistPage.getUsername().getText().split(" ")[1]);
            Assert.assertEquals(playlist.get("Count"), playlistPage.getSongCount().getText().split(" ")[0]);
            Thread.sleep(500);
            new Homepage().clickOnLink("Your Music");

        }

    }


    @Then("the following albums should be displayed")
    public void the_following_albums_should_be_displayed(List<String> expected) {

        List<String> actual = SeleniumUtils.getElementsText(new Homepage().getAlbumsNames());

        List<String> expectedMod =  new ArrayList<>(expected);
        Collections.sort(expectedMod);
        Collections.sort(actual);

        Assert.assertEquals(expectedMod,actual);
    }
}

package pages;

import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.Driver;
import utils.SeleniumUtils;

import java.util.List;
@Getter
public class AlbumPage {

    public AlbumPage(){
        PageFactory.initElements(Driver.getDriver(),this);
    }

    @FindBy(xpath = "//span[@class='trackName']//span[@role='link']")
    private WebElement currentSongName;

    @FindBy(tagName = "h2")
    private WebElement albumName;

    @FindBy(xpath = "//p[@role='link']")
    private WebElement artistName;

    public String getArtistName() {
        return artistName.getText().substring(3);
    }

    public String getAlbumName() {
        return albumName.getText();
    }

    public String getSongCount() {

        return songCount.getText().split(" ")[0];
    }

    @FindBy(xpath = "//p[@role='link']//following-sibling::p")
    private WebElement songCount;


    public void clickOnPlayButton(String nameOfSong){
        String xpath = "//span[@class='trackName'][.='"+nameOfSong+"']//parent::div//preceding-sibling::div//img";
        SeleniumUtils.jsClick(Driver.getDriver().findElement(By.xpath(xpath)));
    }
}

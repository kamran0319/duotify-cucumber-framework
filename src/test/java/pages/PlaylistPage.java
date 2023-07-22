package pages;

import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.Driver;

import java.util.List;
@Getter
public class PlaylistPage {


    public PlaylistPage(){
        PageFactory.initElements(Driver.getDriver(),this);
    }

    @FindBy(xpath = "//div[@class='rightSection']/p[1]")
    private WebElement username;

    @FindBy(xpath = "//div[@class='rightSection']/p[2]")
    private WebElement songCount;


    public void clickOnPlaylist(String name){
        Driver.getDriver().findElement(By.xpath("//div[@class='gridViewInfo'][.='"+name+"']")).click();
    }

}

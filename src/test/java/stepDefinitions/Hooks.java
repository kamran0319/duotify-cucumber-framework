package stepDefinitions;

import io.cucumber.java.*;
import org.junit.BeforeClass;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import utils.ConfigReader;
import utils.Driver;

import java.time.Duration;

public class Hooks {



    @Before(order = 1)  // before each scenario
    public void setupScenario(){
        Driver.getDriver().manage().window().maximize();
        Driver.getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        Driver.getDriver().get(ConfigReader.getProperty("url"));

    }

//    @Before("@db or @DATABASE")
//    public void setupScenarioForDB(){
//        System.out.println("Establish db connection");
//    }

//    @Before(order = 2)  // before each scenario
//    public void setupScenario2(){
//        System.out.println("Second Before Hook");
//    }

    @After () // after each scenario
    public void tearDownScenario(Scenario scenario){
        if(scenario.isFailed()){
            byte[] screenshotFile = ((TakesScreenshot) Driver.getDriver()).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshotFile, "image/png", "screenshot");
        }

        Driver.quitDriver();
    }

//    @After (order = 2) // after each scenario
//    public void tearDownScenario2(){
//        System.out.println("Second after ");
//    }


//    @BeforeAll
//    public static void beforeAllScenarios(){
//        System.out.println("Before all sceanrios");
//        // establish db connection
//    }
//
//    @AfterAll
//    public static void afterAllScenarios(){
//        System.out.println("After all sceanrios");
//        // close db connection
//    }

//    @BeforeStep
//    public void beforeEachStep(){
//        System.out.println("Before step");
//    }
//
//    @AfterStep
//    public void afterEachStep(){
//        System.out.println("After step");
//    }


}

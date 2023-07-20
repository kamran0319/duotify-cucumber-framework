package runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@CucumberOptions (
          tags = "@SMOKE",
          features = "src/test/resources", // the path where feature files are located
          glue = "stepDefinitions" // the path where step definitions are located
//          ,dryRun = true // to generate step definitions quickly without running the code

)

@RunWith(Cucumber.class)
public class CucumberRunnerSmoke {
}

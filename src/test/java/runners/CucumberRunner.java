package runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@CucumberOptions (
          tags = "@signup",
          features = "src/test/resources",
          glue = "stepDefinitions"


)

@RunWith(Cucumber.class)
public class CucumberRunner {
}

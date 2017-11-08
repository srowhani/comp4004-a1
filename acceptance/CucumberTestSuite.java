import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "acceptance/features",
        plugin = {"pretty", "html:output/cucumber.html"}
)
public class CucumberTestSuite {}

package Runner;


import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
		plugin = {"pretty", "html:target/cucumber-html-report.html"},
        features = "src/test/resources/features",
        glue = "stepdefs",
        monochrome = true      
)

public class TestRunner {
}

package Framework;
//import io.cucumber.testng.CucumberOptions;
//import org.testng.annotations.Test;
//
//
//@CucumberOptions(plugin = "json:target/cucumber-report/cucumber-report-feature-composite.json", features = {
//        "/FeatureFile/."}, glue = {
//        "StepDefinition/."}, tags = {"@testTitle"},
//dryRun = true)
//
//@Test
//public class DryRunner {
//}

import io.cucumber.junit.Cucumber;
//import io.cucumber.junit.CucumberOptions;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;
import org.testng.annotations.Test;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"src/test/java/FeatureFile/."},
        glue = {"StepDefinition"},
        plugin = {"pretty", "html:target/cucumber-reports",
        },
        tags = "@addObject"
)
// @testSearchKeyword
//@testTitle
public class DryRunner {
}

//import io.cucumber.junit.Cucumber;
//import org.junit.runner.RunWith;
//import cucumber.junit.Cucumber;
//
//@RunWith(Cucumber.class)
//@Cucumber.Options(format = {"pretty", "html:target/cucumber"})
//
//public class DryRunner { }

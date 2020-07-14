package FeatureTests;

import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;


@RunWith(Cucumber.class)
@Cucumber.Options(format = {"pretty", "html:target/cucumber"},
        features = "src/test/java/FeatureTests/")

public class runTest { }


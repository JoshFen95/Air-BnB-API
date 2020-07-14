package FeatureTests;

import com.example.airbnbapi.service.MediaService;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.http.HttpResponse;

public class deleteItemSteps {


        WebDriver driver = null;

//        private MediaService mediaService;




    @Given("^I am on the delete \"(.*)\" page$")
    public void goToAddDelete(String arg1) {
        driver = new ChromeDriver();
        driver.navigate().to("http://localhost:8000/" + arg1 + "/delete");
    }

        @When("^I enter id as \"(.*)\"$")
        public void enterId(String arg1) {
            driver.findElement(By.id("id")).sendKeys(arg1);
            driver.findElement(By.id("delete")).click();

        }


    @Then("^Item should be deleted with title of \"(.*)\"$")
    public void checkItemAdded(String arg1) {
        if(driver.findElement(By.id("h2")).equals(arg1)) {

            System.out.println("Item was not deleted");
        } else {
            System.out.println("Item was not delete successfully.");
        }


        driver.close();
    }
}



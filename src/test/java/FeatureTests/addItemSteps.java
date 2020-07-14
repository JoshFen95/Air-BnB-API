package FeatureTests;



import com.example.airbnbapi.model.MediaType;
import com.example.airbnbapi.service.MediaService;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.http.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;


public class addItemSteps {

    WebDriver driver = null;

//    private MediaService mediaService = new MediaService();


    @Given("^I am on the add \"(.*)\" page$")
    public void goToAdd(String arg1) {
        driver = new ChromeDriver();
        driver.navigate().to("http://localhost:8000/" + arg1 + "/add");
    }

    @When("^I enter title as \"(.*)\"$")
    public void enterTitle(String arg1) {
        driver.findElement(By.id("title")).sendKeys(arg1);
    }

    @When ("^I enter release year as \"(.*)\"$")
    public void enterReleaseYear(String arg1) {
        driver.findElement(By.id("year")).sendKeys(arg1);
    }
    @When ("^I enter creators as \"(.*)\"$")
    public void enterCreators(String arg1) {
        driver.findElement(By.id("creator")).sendKeys(arg1);
    }
    @When ("^I enter image url as \"(.*)\"$")
    public void enterImageUrl(String arg1) {
        driver.findElement(By.id("imgurl")).sendKeys(arg1);
    }

    @When ("^I enter video url as \"(.*)\"$")
    public void enterVideoUrl(String arg1) {
        driver.findElement(By.id("vidurl")).sendKeys(arg1);
    }
    @When("^I click add$")
    public void clickAdd() {
        driver.findElement(By.id("add")).click();
    }


    @Then("^Item should be added with title of \"(.*)\"$")
    public void checkItemAdded(String arg1) {
       if(driver.findElement(By.id("h2")).equals(arg1)) {

           System.out.println("Item was added successfully");
       } else {
           System.out.println("Item was not added.");
       }


        driver.close();
    }
}

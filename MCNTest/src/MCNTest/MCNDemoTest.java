package MCNTest;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.annotations.Test;

public class MCNDemoTest {
    public WebDriver driver = new FirefoxDriver();
    String appUrl = "https://www.healthcare.gov/";
	
	@Test
  public void HealthCareLogin() {
		//step 1 of exercise - Launch FireFox browser and launch Healthcare site 
		driver.get("https://www.healthcare.gov/");
		
		//maximize the browser window
		driver.manage().window().maximize();
		
		//Click on "See if I can Enroll" Button to dismiss the 'Start Here' dialog
        WebElement submitButton = driver.findElement(By.id("prefix-overlay-header"));
        submitButton.click();
			
		//step 2 of exercise - Move to 'Screener' page
        driver.get("https://www.healthcare.gov/screener/");

		//Click on "Continue" Button to invoke error messages
        WebElement submitButton1 = driver.findElement(By.id("submit"));
        submitButton1.click();
        
        //Verify if ZipCode field exists
        boolean zipCodePresent;        
        try {
           driver.findElement(By.id("errormessage_zipcode"));
           zipCodePresent = true;
        } catch (NoSuchElementException e) {
           zipCodePresent = false;
        }
        
      //Verify if Select Below field exists
        boolean belowPresent;
        try {
           driver.findElement(By.id("errormessage_path-0"));
           belowPresent = true;
        } catch (NoSuchElementException e) {
           belowPresent = false;
        }

        //Add 99999 ZIP code and select 'If you qualify...'
        WebElement zipCode = driver.findElement(By.id("zipcode"));
        zipCode.clear();
        zipCode.sendKeys("99999");
        WebElement spEnrollmentRadio = driver.findElement(By.id("path-0"));
        spEnrollmentRadio.click();
        
      //Verify if ZipCode field exists
        try {
           driver.findElement(By.id("errormessage_zipcode"));
           zipCodePresent = true;
        } catch (NoSuchElementException e) {
           zipCodePresent = false;
        }
        
      //Add 80210 ZIP code and select 'If you qualify...'
        zipCode.clear();
        zipCode.sendKeys("80210");
        driver.findElement(By.id("zipcode")).sendKeys(Keys.ENTER);
        driver.findElement(By.id("zipcode")).sendKeys(Keys.ENTER);
        //driver.findElement(By.id("zipcode")).sendKeys(Keys.TAB);
        //WebElement spEnrollmentClick = driver.findElement(By.id("path-0"));
        //spEnrollmentRadio.click();
        submitButton1.click();
      
      //Go to 'Visit Colorado...'
        WebElement connectButton = driver.findElement(By.id("statesite"));
        connectButton.click();      
        
      //Confirm we are in the 'Visit Colorado...' page
        String expectedURL = "http://connectforhealthco.com/";

//fetch the title of the web page and save it into a string variable
        //driver.manage().timeouts().implicitlyWait(200, TimeUnit.MILLISECONDS);
        String actualURL = driver.getCurrentUrl();
        Assert.assertEquals(expectedURL,actualURL);
	}	
}


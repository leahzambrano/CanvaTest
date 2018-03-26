package myTest;

import static org.junit.Assert.*;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.internal.TextListener;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import pageObjects.HomePage;
import pageObjects.TemplateBookCovers;

public class CanvaTest {

private static WebDriver driver = null;

	
	public static void main(String args[]) {
		  JUnitCore junit = new JUnitCore();
		  junit.addListener(new TextListener(System.out));
		  Result result = junit.run(myTest.CanvaTest.class);
		  if (result.getFailureCount() > 0) {
			    System.out.println("Test failed.");
			    System.exit(1);
			  } else {
			    System.out.println("Test finished successfully.");
			    System.exit(0);
			  }
			}	  
  
	
	@BeforeClass
	public static void openBrowser() {
		setupChromeDriver();
	}
	
	@Test
	public void testCanva() throws InterruptedException {
	
		System.out.println("Starting test... " + "\n");
		
		String baseURL = "https://www.canva.com/templates/book-covers/";
		String actualURL = "";
		String banner = "";
		 
	
		
		//STEP 1. GO TO URL
		System.out.println("1. Go to URL: " +baseURL);
		driver.get(baseURL);
		
		//validate URL
		System.out.println("   ...validating");
		actualURL = driver.getCurrentUrl();
		assertEquals (baseURL, actualURL);
		
		
		//validate other elements in page
		banner = TemplateBookCovers.bannerHeading(driver).getText();
		assertEquals ("Book Cover Templates", banner);
		
		//STEP 2. RANDOMLY SELECT BOOK COVER > CLICK
		System.out.println("2. Select random cover");
	    randomSelect("marketplaceCard__thumbnailLink");
		
	    //STEP 3. CLICK ON COVER
	    System.out.println("3. Click on random cover");
	    TemplateBookCovers.editCover(driver).click();
	    Thread.sleep(500);
	    
	    System.out.println("   ...validating");
	  	String homepage = "https://www.canva.com/signup?signupRedirect";
        String myURL = driver.getCurrentUrl();
        assertTrue(myURL.contains(homepage));
        System.out.println("   Redirected to: " +myURL);
        
        //validate other elements in page
        HomePage.signupFormContainer(driver).isDisplayed();
        HomePage.emailTextbox(driver).isDisplayed();
        HomePage.pwTextbox(driver).isDisplayed();
        
        
        System.out.println("\n" + "End of test");
		
	}
	
	
	
	
	public void randomSelect(String classname) throws InterruptedException {

		List<WebElement> links = driver.findElements(By.className(classname));
		//System.out.println("Number of links: "+links.size());	    

		Random r = new Random();
		WebElement random = links.get(r.nextInt(links.size()));
		String random1 = random.getAttribute("href");
		System.out.println("   Random cover selected:" +random1);	
		

		String s1 = random1.substring(21);
				
		WebElement selected = driver.findElement(By.xpath("//a[@href= '" + s1 + "' ]"));
		
		((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", selected);
		
		Thread.sleep(500);
        selected.click();
        Thread.sleep(500);
        
        //validate URL
        System.out.println("   ...validating");
        String myURL = driver.getCurrentUrl();
        assertTrue(myURL.contains(s1));
        
}
	
	@AfterClass
	public static void closeBrowser() {
		driver.quit();
	}
	
	public static void setupChromeDriver() {
		
		//System.setProperty("webdriver.chrome.driver",CanvaTest.class.getResource("chromedriver.exe").getFile());
		System.setProperty("webdriver.chrome.driver", "D://bin//chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--start-maximized");
		options.addArguments("--ignore-certificate-errors");
		driver = new ChromeDriver( options );
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		   }
		

}

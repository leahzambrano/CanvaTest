package pageObjects;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage {

private static WebElement element = null;
	
	public static WebElement signupFormContainer(WebDriver driver) {
	element = driver.findElement(By.className("signupFormContainer__inner"));
	return element;
	}
	
	public static WebElement emailTextbox(WebDriver driver) {
	element = driver.findElement(By.name("email"));
	return element;
	}
	
	public static WebElement pwTextbox(WebDriver driver) {
	element = driver.findElement(By.name("password"));
	return element;
	}
} 



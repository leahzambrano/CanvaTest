package pageObjects;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class TemplateBookCovers {

private static WebElement element = null;
	
	public static WebElement editCover(WebDriver driver) {
		element = driver.findElement(By.className("marketplaceSingle__imageContainer"));
		return element;
	}
	
	public static WebElement bannerHeading(WebDriver driver) {
		element = driver.findElement(By.className("marketplaceHeader__bannerHeading"));
		return element;
	}
	
	public static WebElement useThisTemplate_btn(WebDriver driver) {
		element = driver.findElement(By.className("marketplaceSingle__sidebarBlock marketplaceSingle__sidebarBlock--noShadow"));
		return element;
	}
	
	
}



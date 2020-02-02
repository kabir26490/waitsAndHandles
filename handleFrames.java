package Day12;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import Day11.OrangeCRM;

public class handleFrames {
	WebDriver driver;
	@Test
	public void handleFramesExample() throws InterruptedException {
		driver = new ChromeDriver(OrangeCRM.LoadChromeoptions());
		driver.get("https://www.toolsqa.com/iframe-practice-page/");
		
		
		//By finding all the web elements using iframe tag
		List<WebElement> iframeElements = driver.findElements(By.tagName("iframe"));
		System.out.println("Total number of iframes are " + iframeElements.size());
		
		
		//By executing a java script
		JavascriptExecutor exe = (JavascriptExecutor) driver;
		Integer noOfFrames = Integer.parseInt(exe.executeScript("return window.length").toString());
		System.out.println("No. of iframes on the page are " + noOfFrames);
		
		
		driver.switchTo().frame(0); 
	//	Thread.sleep(15000);
	//	WebElement temp = driver.findElement(By.xpath("(//*[text()='Advertising With Us'])"));
		WebDriverWait wait = new WebDriverWait(driver, 15);
		//*[@href='https://www.toolsqa.com/advertising-with-us/']
		
		//*[text()='Advertising With Us']
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@href='https://www.toolsqa.com/advertising-with-us/']")));
		
		WebElement temp = driver.findElement(By.xpath("//*[@href='https://www.toolsqa.com/advertising-with-us/']"));
		System.out.println(temp.getText());
		
		//driver.close();
		//System.out.println(driver.findElement(By.xpath("(//*[text()='Advertising With Us'])")).getText());
	}

}

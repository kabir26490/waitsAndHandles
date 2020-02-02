package Day12;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AjaxHandle {
	static WebDriver driver;
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.goibibo.com/");
		driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
		
		driver.findElement(By.xpath("//*[@id='gosuggest_inputSrc']")).sendKeys("P");
		WebDriverWait wait=new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//ul[@role='listbox']//li")));

		List<WebElement> autoSuggestLists = driver.findElements(By.xpath("//ul[@role='listbox']//li"));
		
		System.out.println(autoSuggestLists.size());
		
		for(int i = 0 ;i< autoSuggestLists.size();i++)
		{
			System.out.println(autoSuggestLists.get(i).getText());
			
			if(autoSuggestLists.get(i).getText().contains("Pune, India(PNQ)"))  //
			{
				autoSuggestLists.get(i).click();
				break;
			} else {
				throw new Exception("Place does not exists");
			}
		}
	}
	
	
	
	

}

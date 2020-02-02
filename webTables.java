package Day12;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

//table[@class='dataTable'] complete table 
//table[@class='dataTable']/tbody/tr[3]/td[4]  3rd row and 4th cell
//table[@class='dataTable']/tbody/tr[3]/td  count of td cell- find elements 
//table[@class='dataTable']/tbody/tr all rows of the table
//table[@class='dataTable']/tbody/tr/td[5]  each cell in 5th column , track values by the columns
//CSS
//.dataTable>tbody>tr:nth-child(5)>td 5th row all cells

public class webTables {
	static String  companyName="ITI";
	static WebDriver driver;
	public static void main(String[] args) {
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://money.rediff.com/gainers/bse/daily/groupa?src=gain_lose");
		driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
		
		
		List<WebElement> names = driver.findElements(By.xpath("//table[@class='dataTable']/tbody/tr/td[1]"));
		List<WebElement> price = driver.findElements(By.xpath("//table[@class='dataTable']/tbody/tr/td[4]"));
		System.out.println("Total names"+":"+names.size());
		System.out.println("Total Price"+":"+price.size());
	
		int columns =driver.findElements(By.xpath("//table[@class='dataTable']/tbody/tr/td")).size();  //when the column count is not known or it is dynamic
		int rows = driver.findElements(By.xpath("//table[@class='dataTable']/tbody/tr")).size(); // size of rows
		
		System.out.println("Rows are"+":"+rows);
		System.out.println("Columns are"+":"+columns);
		
		/*for(int i=0;i<names.size();i++) {
			System.out.println(names.get(i).getText()+":"+price.get(i).getText());
		}*/
		
		
		/**
		 * get the specific company name and its price
		 */
		for(int i=0;i<names.size();i++) {
			if(companyName.equals(names.get(i).getText())) {
			System.out.println(names.get(i).getText()+":"+price.get(i).getText());
			break;
			}
		}
		
		
		
		
	}

}

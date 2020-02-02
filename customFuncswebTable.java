package Day12;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class customFuncswebTable {
	static WebDriver driver;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://money.rediff.com/gainers/bse/daily/groupa?src=gain_lose");
		driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
	//Let us get first the company name of the price
		int rNum = getRowNumWithCellData("3.21");
	System.out.println("rNum"+":"+rNum);
	
	// if you want to get the price with the company name
//	int rNum = getRowNumWithCellData("Avanti Feeds Lim");
	System.out.println("rNum"+":"+rNum);
	String price = driver.findElement(By.xpath("//table[@class='dataTable']/tbody/tr["+rNum+"]/td[4]")).getText();
	System.out.println(price);

	}
	
	
	public static int getRowNumWithCellData(String data) {
		// we would take the rows in a list
		List<WebElement> rows = driver.findElements(By.xpath("//table[@class='dataTable']/tbody/tr"));
		
		//iterate through the rows
		for(int rNum=0;rNum<rows.size();rNum++) {
			//each row would be traversed
			//inside row find the elements
			WebElement row = rows.get(rNum);  // for 1 st row then 2nd row 
			List<WebElement> cells = row.findElements(By.tagName("td"));
			for(int cNum=0;cNum<cells.size();cNum++) {
				String cellData = cells.get(cNum).getText();
				if(cellData.equalsIgnoreCase(data)) {
					return ++rNum;  // as we start from 0 
				}
			}
		}
		return -1;
	}

}

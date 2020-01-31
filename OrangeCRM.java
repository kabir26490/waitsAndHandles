package Day11;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import com.google.common.base.Function;

import Day10.CommonFunctions;

public class OrangeCRM {
	
	/**
	 * Explicit Wait
	 * Fluent Wait
	 * handles
	 * Alert
	 */
	
	static WebDriver driver;

	@Test(priority =1,enabled=false)
	public void DoLogin() throws InterruptedException, ParseException {
		
		
		driver = new ChromeDriver(LoadChromeoptions());
		
		//driver = new FirefoxDriver(loadFireFox());
		driver.get("https://opensource-demo.orangehrmlive.com/");
		driver.findElement(By.xpath("//*[@id='txtUsername']")).sendKeys("Admin");
		driver.findElement(By.xpath("//*[@id='txtPassword']")).sendKeys("admin123");
		//Login Button Element
		WebElement btnLogin = driver.findElement(By.xpath("//*[@id='btnLogin']"));
		
		CommonFunctions.click(btnLogin);
		
		driver.close();
	}
	
	
	@Test(priority=2,enabled=false)
	public void checkRedif() throws InterruptedException {
		
		driver = new ChromeDriver(LoadChromeoptions());
		driver.get("https://portfolio.rediff.com/portfolio-login");
		driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS); // To load all the components of the page
		driver.manage().timeouts().setScriptTimeout(10, TimeUnit.SECONDS);  // For Ajax class
		driver.findElement(By.xpath("//*[@id='useremail']")).sendKeys("romen.xylon@iiron.us");
		driver.findElement(By.xpath("//*[@id='emailsubmit']")).click();
		
		
		/**
		 * Element not interactable exception as the password element does not show
		 */
//		driver.findElement(By.xpath("//*[@id='userpass']")).sendKeys("abc@123.com");
		
		/**
		 * Explicit wait
		 * it will check within 15 seconds if element is found it would work ahead
		 */
		/*WebDriverWait wait = new WebDriverWait(driver, 15);
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//*[@id='userpass']"))));
		driver.findElement(By.xpath("//*[@id='userpass']")).sendKeys("abc@123.com");
		*/
		// try for clear trip.com for searching flights
		
		/**
		 * Find with fluent wait
		 */
		String path ="//*[@id='userpass']";
		String keys="abc@123.com";
		
		fluentwait(path,keys);
		
		
				 
		
		driver.close();
	}
	
	
	@Test(priority=3,enabled = false)
	public void handleWindows() {
		
		driver = new ChromeDriver(LoadChromeoptions());
		driver.get("https://www.naukri.com/");
		/**
		 * Check size if you don't know whether pop up is present or not
		 * if(size >1) for window handles then go inside
		 */
		Set<String> windowHandels = driver.getWindowHandles();
		System.out.println("Window Handels size is "+" : "+windowHandels.size());
		Iterator<String> windowIds = windowHandels.iterator();
		
			
		String mainWindow = windowIds.next();
		String firstPopup = windowIds.next();
		String secondpop = windowIds.next();
		System.out.println(mainWindow+":"+"mainWindow");
		System.out.println(firstPopup+":"+"firstPopup");
		System.out.println(secondpop+":"+"secondpop");
		
		//Now switch to Window
		driver.switchTo().window(firstPopup);
		//first pop up
		driver.close();
		driver.switchTo().window(secondpop);
		driver.close();
		driver.switchTo().window(mainWindow);
		
		
		//if does not work by above code then use default
		//driver.switchTo().defaultContent();
	}
	
	public String[] setDate() throws ParseException {
		
		String date ="26/04/1990";  //DD/MM/YYYY;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date dt =sdf.parse(date);
		String month =  new SimpleDateFormat("MMM").format(dt);
		int year = Integer.parseInt(new SimpleDateFormat("yyyy").format(dt));
		String day = new SimpleDateFormat("d").format(dt);
	//	System.out.println(day+" - "+ month+" - "+year );
		String str[] = new String[3];
		str[0]= day;
		str[1] = month;
		str[2] = String.valueOf(year);
		
		return str;
	}
	
	public static void selectDate() {
		
		
	}
	
	@Test(priority=3,enabled = false)
	public void checkDateGoIbibo(){
		driver = new ChromeDriver(LoadChromeoptions());
		driver.get("https://www.goibibo.com/");
		
		WebDriverWait wait = new WebDriverWait(driver,10);
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//*[@id='departureCalendar']"))));
		
		driver.findElement(By.xpath("//*[@id='departureCalendar']")).click();
		
		
		String month = "March";
		
		WebElement MonthPicker = driver.findElement(By.xpath("//*[@class='DayPicker-Caption']"));
		String str[] = MonthPicker.getText().split(" ");
		String displayMonth = str[0];
		
		System.out.println(displayMonth+":"+"Display Month"+"  "+month+":"+"Month");
		
		while(!displayMonth.equalsIgnoreCase(month)) {
			WebElement nextBtn =driver.findElement(By.xpath("//*[@role='button']"));
			nextBtn.click();
		}
	/*	WebElement MonthPicker_ = driver.findElement(By.xpath("//*[@class='DayPicker-Caption']"));
		String str_[] = MonthPicker_.getText().split(" ");
		String displayMonth_ = str_[0];
		
		System.out.println(displayMonth_);
*/	}
	
	@Test(priority =2, enabled = true)
	public void RedifAlert() {
		// http://register.rediff.com/commonreg/index.php?redr=//portfolio.rediff.com/portfolio
		// click on submit button w/o entering details
		// it takes time to come for the alert and at times the alert is not shown, need to use explicit wait
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.alertIsPresent()); 
		
		Alert al = driver.switchTo().alert();
		System.out.println(al.getText());
		al.accept();
		al.dismiss();
		
		// you have switched the contorl 
		
		driver.switchTo().defaultContent(); // back to main page.
		}
	
	
	
	
	
	/*getLinksOfAllBox_.get(i).click();
	System.out.println("Title of the page"+"====="+driver.getTitle());
	driver.get("https://cnn.com");
	WebDriverWait wait = new WebDriverWait(driver, 10);
	wait.until(ExpectedConditions.stalenessOf(getLinksOfAllBox_.get(i)));
	getLinksOfAllBox = driver.findElement(By.xpath("//section[@id='intl_homepage1-zone-1']//div[@class='column zn__column--idx-2']"));
	getLinksOfAllBox_ = getLinksOfAllBox.findElements(By.tagName("a"));
*/
	
	public ChromeOptions LoadChromeoptions() {
		ChromeOptions options = new ChromeOptions();
		System.setProperty(ChromeDriverService.CHROME_DRIVER_SILENT_OUTPUT_PROPERTY, "false");
		System.setProperty(ChromeDriverService.CHROME_DRIVER_LOG_PROPERTY, "H:\\Selenium Web Driver\\Eclipse Workspace\\WebDriverDemo\\Chrome.log");  //null
		System.setProperty("webdriver.chrome.verboseLogging" ,"true");
		options.setPageLoadStrategy(PageLoadStrategy.EAGER);
		//options.addArguments("--incognito");
		options.addArguments("start-maximized");
		options.addArguments("disable-infobars");
		options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
		
		Map<String, Boolean> prefs = new Hashtable<String, Boolean>();
		prefs.put("download.prompt_for_download", true);
		prefs.put("profile.password_manager_enabled", false);
		prefs.put("credentials_enable_service", false);
		options.setExperimentalOption("prefs", prefs);
		
		
		options.setExperimentalOption("useAutomationExtension", false);
		//profile
		//options.addArguments("user-data-dir=C:\\Users\\aakar\\AppData\\Local\\Google\\Chrome\\User Data\\Profile 1");   //no profile name given then default profile
	
		
		
		DesiredCapabilities caps = new DesiredCapabilities();
		caps.setCapability(CapabilityType.BROWSER_NAME,"Chrome");
		System.out.println(caps.getCapability(CapabilityType.BROWSER_NAME));
		caps.setCapability(CapabilityType.APPLICATION_NAME, "Flipkart");
		caps.setCapability(CapabilityType.BROWSER_VERSION, 78);
		System.out.println(caps.getCapability(CapabilityType.BROWSER_VERSION));
		caps.merge(options);
		
		
		
		return options;
	}
	
	
	public void fluentwait(String xpath,String keys) {
		 FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver);
		 wait.pollingEvery(25,  TimeUnit.MILLISECONDS);
		 wait.withTimeout(2, TimeUnit.SECONDS);
		 
		 Function<WebDriver, Boolean> function = new Function<WebDriver, Boolean>()
		 {
		 public Boolean apply(WebDriver arg0) {
		 WebElement element = driver.findElement(By.xpath(xpath));
		 if(element.isDisplayed())
		 {
			 driver.findElement(By.xpath(xpath)).sendKeys(keys);
		 return true;
		 }
		 return false;
		 }
		 };
		 
		 wait.until(function);
		 }
	
	}
	
	



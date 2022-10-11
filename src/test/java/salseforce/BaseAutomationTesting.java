package salseforce;


import java.time.Duration;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

public class BaseAutomationTesting {  
	protected static WebDriver driver;
	public static WebDriverWait wait = null;
	public static Logger logger =LogManager.getLogger(BaseAutomationTesting.class);

	public static void setText(WebElement element, String text) {
		if (element.isDisplayed()) {
			element.clear();
			element.sendKeys(text);
		} else
			System.out.println("element not found");
	}

	public static void setTextById(String id, String text) {
		WebElement element = driver.findElement(By.id(id));
		
		if (element.isDisplayed()) {
			element.clear();
			element.sendKeys(text);
		} else
			System.out.println("element not found");
	}
	
	public static String getValue(WebElement element) {
		if (element.isDisplayed()) {
			return element.getAttribute("value");
		} else
			System.out.println("element not found");
		return "";
	}

	public static String getText(WebElement element) {
		if (element.isDisplayed()) {
			return element.getText();
		} else
			System.out.println("element not found");
		return "";
	}

	public static void click(WebElement element) {
		if (element.isDisplayed()) {
			element.click();
		} else
			System.out.println("element not found");
	}
	
	public static WebElement clickByXPath(String xpath) {
		WebElement element = driver.findElement(By.xpath(xpath));
		
		if (element.isDisplayed()) {
			element.click();
		} else
			System.out.println("element not found");
		return element;
	}
	
	public static WebElement clickByClass(String cls) {
		WebElement element = driver.findElement(By.className(cls));
		
		if (element.isDisplayed()) {
			element.click();
		} else
			System.out.println("element not found");
		return element;
	}
	
	public static WebElement clickById(String id) {
		WebElement element = driver.findElement(By.id(id));
		if (element.isDisplayed()) {
			element.click();
		} else
			System.out.println("element not found");
		return element;
	}

	
	public static WebElement clickByName(String name) {
		WebElement element = driver.findElement(By.name(name));
		if (element.isDisplayed()) {
			element.click();
		} else
			System.out.println("element not found");
		return element;
	}
	
	@BeforeMethod
	public static void setUp() {
		System.out.println("beofre method execution started");
		setDriver("chrome");
		wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		waitUntilPageLoads();
	}
	@AfterMethod
	public static void tearDown() {
		logger.info("after method execution is started");
		closeBrowser();
	}
	


	public static void setDriver(String browser) {

		switch (browser) {
		case "chrome":
			System.setProperty("webdriver.chrome.driver", "C:\\Development\\chromedriver_win32\\chromedriver.exe");
			driver = new ChromeDriver();
			break;
		default:
			break;

		}

	}
	
	public static String getPageTitle() {
		return driver.getTitle();
	}

	public static void refreshPage() {
		driver.navigate().refresh();
		System.out.println("current page is refreshed");
	}
	public static void waitUntilPageLoads() {
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
	}

	public static void enterText(WebElement element, String text, String objName) {
		if (element.isDisplayed()) {
			clearElement(element, objName);
			element.sendKeys(text);
			System.out.println("text entered in " + objName + "field");
		} else {
			System.out.println("fail: " + objName + " element not displayed");
		}
	}

	public static void clickElement(WebElement element, String objName) {
		if (element.isDisplayed()) {
			element.click();
			System.out.println("pass:" + objName + " element clicked");
		} else {
			logger.error("fail:" + objName + "  element not displayed");

		}
	}

	public static void clearElement(WebElement element, String objName) {
		if (element.isDisplayed()) {
			element.clear();
			System.out.println("pass:" + objName + "  element cleared");

		} else {
			System.out.println("fail:" + objName + " element not displayed");
		}
	}

	public static WebDriver getDriverInstance() {
		return driver;
	}

	public static void gotoUrl(String url) {
		driver.get(url);
	}

	public static void moveToElement(WebElement element, String objectName) {
		waitUntilVisible(element, objectName);
		Actions action = new Actions(driver);
		action.moveToElement(element).build().perform();
		System.out.println("moved to " + objectName);

	}

	public static void closeBrowser() {
		//driver.close();
	}

	public static void closeAllbrowser() {
		driver.quit();
	}

	public static void waitUntilVisibilityOf(By locator, String objName) {
		wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}

	public static String readText(WebElement element, String objectName) {
		waitUntilVisible(element, objectName);
		String text = element.getText();
		return text;
	}

	public static void waitUntilVisible(WebElement element, String objName) {
		wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.visibilityOf(element));
	}

	public static void waitUntilAlertIsPresent() {
		wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.alertIsPresent());
	}

	public static void waitUntilElementToBeClickable(By locator, String objName) {
		wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.elementToBeClickable(locator));
	}

	public static Alert switchToAlert() {
		waitUntilAlertIsPresent();
		return driver.switchTo().alert();
	}

	public static void AcceptAlert(Alert alert) {

		System.out.println("Alert accepted");
		alert.accept();

	}

	public static String getAlertText(Alert alert) {

		return alert.getText();

	}

	public static void dismisAlert() {
		waitUntilAlertIsPresent();
		Alert alert = switchToAlert();
		alert.dismiss();
		System.out.println("Alert dismissed");

	}

	public static boolean selectByTextData(WebElement element, String text) {
		boolean flag = true;
		Select selectCity = new Select(element);
		try {
		selectCity.selectByVisibleText(text);
		}
		catch(Exception e)
		{
			flag =false;
		}
		System.out.println(" seelcted " + text);
		return flag;
	}

	public static void selectByIndexData(WebElement element, int index) {
		Select selectCity = new Select(element);
		selectCity.selectByIndex(index);
	}

	public static void selectByValueData(WebElement element, String text) {
		Select selectCity = new Select(element);
		selectCity.selectByValue(text);
	}

	public static void switchToWindowOpned(String mainWindowHandle) {
		Set<String> allWindowHandles = driver.getWindowHandles();
		for (String handle : allWindowHandles) {
			if (!mainWindowHandle.equalsIgnoreCase(handle))
				driver.switchTo().window(handle);
		}
		System.out.println("switched to new window");
	}
	public static WebElement selectFromList(List<WebElement> list,String text) {
		WebElement country=null;
		for (WebElement i : list) {
			if (i.getText().equalsIgnoreCase(text)) {
				System.out.println("selected=" +i.getText());
				country=i;
				break;
			}
			
		}
		return country;
		
	}
	
}

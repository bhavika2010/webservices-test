package salseforce;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SalesforceUserMenuTesting extends BaseAutomationTesting {
	public static final String user_name = "bhavikaforbill@gmail.com";
	public static final String pwd = "bhavika2010";

	@Test
	public static void testVerify_Create_Account() throws InterruptedException {
		String menuList[] = {"Logout","My Settings" , "My Profile" , "Developer Console", "Switch to Lightning Experience" };
		List<String> menuLst =  Arrays.asList(menuList);

		String expectedTile = "Selenium";

		System.out.println("Browser opened");

		driver.get("https://login.salesforce.com");
		driver.manage().window().maximize();

		Thread.sleep(1000);
		System.out.println("URL opened");

		WebElement username = driver.findElement(By.id("username"));
		setText(username, user_name);

		WebElement password = driver.findElement(By.id("password"));
		setText(password, pwd);


		WebElement button = driver.findElement(By.id("Login"));
		button.click();
		Thread.sleep(1000);

		String title = driver.getTitle();

		if (title.contains("Home Page")) {
			clickById("Account_Tab");

			clickByXPath("//input[@title='New']");
			
			
			setTextById("acc2", "freelance" + System.currentTimeMillis());
			
			WebElement type=  driver.findElement(By.id("acc6"));
			selectByTextData(type, "Technology Partner");
			
			
			
		}
	}
	
	
    private static String getFileFromResource(String fileName) throws URISyntaxException, ClassNotFoundException{
        ClassLoader classLoader = Class.forName("salseforce.SalesforceUserMenuTesting").getClassLoader();
        URL resource = classLoader.getResource(fileName);
        if (resource == null) {
            throw new IllegalArgumentException("file not found! " + fileName);
        } else {
            return new File(resource.toURI()).getAbsolutePath();
        }

    }

}

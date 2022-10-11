package salseforce.steps;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import salseforce.BaseAutomationTesting;

public class LoginModule extends BaseAutomationTesting {

	WebDriver driver = null;
	@Given("when i open URL {string}")
	public void when_i_open_url(String url) throws InterruptedException {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.get(url);
		driver.manage().window().maximize();
		Thread.sleep(1000);
	}

	@When("i enter value for username as {string} and pasword as {string}")
	public void i_enter_value_for_username_as_and_pasword_as(String string, String string2) {
		WebElement username = driver.findElement(By.id("username"));
		setText(username, string);

		WebElement password = driver.findElement(By.id("password"));
		setText(password, string2);
	}

	@When("click on the login button")
	public void click_on_the_login_button() throws InterruptedException {
		WebElement button = driver.findElement(By.id("Login"));
		button.click();
		Thread.sleep(1000);
	}

	@Then("i should get page with title as {string}")
	public void i_should_get_page_with_title_as(String string) {
		String title = driver.getTitle();
		Assert.assertTrue(title.contains("Home Page"));
	}
	
	@Then("i see login failure as {string}")
	public void i_see_login_failure_as(String string) {
		WebElement error = driver.findElement(By.id("error"));
		Assert.assertTrue(error.isDisplayed() && string.equals(error.getText()));
	}
	
	@Then("i see login failure")
	public void i_see_login_failure() {
		WebElement error = driver.findElement(By.id("error"));
		Assert.assertTrue(error.isDisplayed() && "Please enter your password.".equals(error.getText()));
	}
	
	@When("i check rememberMe checkbox")
	public void i_check_remember_me_checkbox() {
		WebElement rememberMe = driver.findElement(By.id("rememberUn"));
		if (rememberMe.isDisplayed()) {
			rememberMe.click();
			System.out.println("rememberMe found");
		} else
			System.out.println("rememberMe not found");
	}
	@When("click on the logout button")
	public void click_on_the_logout_button() throws InterruptedException {
		WebElement userNavLabel = driver.findElement(By.id("userNavLabel"));
		userNavLabel.click();

		WebElement menuButtonMenuLink = driver.findElement(By.xpath("//a[contains(text(),'Logout')]"));
		menuButtonMenuLink.click();
		Thread.sleep(1000);
		
	}
	@Then("i should get login page with username as {string}")
	public void i_should_get_login_page_with_username_as(String string) {
		
		WebElement username = driver.findElement(By.id("idcard-identity"));
		String value = getText(username);
		WebElement rememberMe = driver.findElement(By.id("rememberUn"));

		Assert.assertTrue(value.equals(string) && rememberMe.isDisplayed() && rememberMe.isSelected());

	}
	
	@When("i click on forgot password link")
	public void i_click_on_forgot_password_link() throws InterruptedException {
		WebElement forgot_password_link = driver.findElement(By.id("forgot_password_link"));
		forgot_password_link.click();
		Thread.sleep(1000);
	}
	@When("i enter value for username as {string}")
	public void i_enter_value_for_username_as(String string) {
		WebElement un = driver.findElement(By.id("un"));
		setText(un, string);
	}
	@When("click on the Continue button")
	public void click_on_the_continue_button() throws InterruptedException {
		WebElement con = driver.findElement(By.id("continue"));
		con.click();
		Thread.sleep(1000);
	}
	
	@Then("i should get success page for password reset")
	public void i_should_get_success_page_for_password_reset() {
		WebElement text = driver
				.findElement(By.xpath("//p[contains(text(),'We’ve sent you an email with a link to finish rese')]"));
		Assert.assertTrue(
				text.getText().contains("We’ve sent you an email with a link to finish resetting your password."));
	}
	
	
}

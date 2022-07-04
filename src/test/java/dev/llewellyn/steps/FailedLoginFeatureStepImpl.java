package dev.llewellyn.steps;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import dev.llewellyn.pages.LoginPage;
import dev.llewellyn.runners.FailedLoginRunner;
import io.cucumber.java.AfterAll;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class FailedLoginFeatureStepImpl {
	
	private static WebDriver driver = FailedLoginRunner.driver;
	private static LoginPage loginPage = FailedLoginRunner.loginPage;
	
	@BeforeAll
	public static void setup() {
		File chrome = new File("src/test/resources/chromedriver.exe");
		System.setProperty("webdriver.chrome.driver", chrome.getAbsolutePath());
		driver = new ChromeDriver();

		loginPage = new LoginPage(driver);
	}

	@Given("a user is on the home page")
	public void a_user_is_on_the_home_page() {
		driver.get("http://localhost:8080/login.html");
	}

	@When("a user types in a invalid {string} and\\/or {string} and clicks the login button")
	public void a_user_types_in_a_invalid_and_or_and_clicks_the_login_button(String email, String password) {
		loginPage.emailInput.sendKeys(email);
		loginPage.passwordInput.sendKeys(password);
		loginPage.loginButton.click();
	}

	@Then("the user should get an alert")
	public void the_user_should_get_an_alert() {
		new WebDriverWait(driver, Duration.ofSeconds(10))
			.until(ExpectedConditions.alertIsPresent());
		
		assertEquals("Invalid credentials", driver.switchTo().alert().getText());
	}

	@Then("after clicking ok")
	public void after_clicking_ok() {
	    driver.switchTo().alert().accept();
	}

	@Then("still be on the login page")
	public void still_be_on_the_login_page() {
	    assertEquals("ERS Login", driver.getTitle());
	}
	
	@AfterAll
	public static void teardown() {
		driver.quit();
	}
}

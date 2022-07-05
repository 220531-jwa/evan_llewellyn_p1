package dev.llewellyn.steps;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import dev.llewellyn.pages.HomePage;
import dev.llewellyn.pages.LoginPage;
import dev.llewellyn.pages.UpdateReimbursementPage;
import io.cucumber.java.AfterAll;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class FMUpdatesReimbursementStepImpl {

	public static WebDriver driver;
	public static LoginPage loginPage;
	public static HomePage homePage;
	public static UpdateReimbursementPage rUpdatePage;

	@BeforeAll
	public static void setup() {
		File chrome = new File("src/test/resources/chromedriver.exe");
		System.setProperty("webdriver.chrome.driver", chrome.getAbsolutePath());
		driver = new ChromeDriver();

		loginPage = new LoginPage(driver);
		homePage = new HomePage(driver);
		rUpdatePage = new UpdateReimbursementPage(driver);
	}
	
	@Given("a finance manager is on the login page and has a reimbursement requests to update")
	public void a_finance_manager_is_on_the_login_page_and_has_a_reimbursement_requests_to_update() {
		driver.get("http://localhost:8080/login.html");
	}

	@When("a finance manager types in their {string} and {string} and then clicks the login button")
	public void a_finance_manager_types_in_their_and_email_and_password_then_clicks_the_login_button(String email, String pass) {
		loginPage.emailInput.sendKeys(email);
		loginPage.passwordInput.sendKeys(pass);
		loginPage.loginButton.click();
	}

	@When("they click on the edit button for one of the reimbursement requests")
	public void they_click_on_the_edit_button_for_one_of_the_reimbursement_requests() {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		WebElement editButton = driver.findElement(By.xpath("//*[@id=\"rTable\"]/tbody/tr[3]/td[15]/button"));
		editButton.click();
	}

	@When("they update the request to {string}")
	public void they_update_the_request_to_status(String status) {
		new WebDriverWait(driver, Duration.ofSeconds(10))
			.until(ExpectedConditions.titleContains("Update Reimbursement"));
		
		rUpdatePage.statusInput = new Select(driver.findElement(By.id("updateStatus")));
		
		rUpdatePage.statusInput.selectByValue(status);
	}

	@When("they click on the update button to finish editing")
	public void they_click_on_the_update_button_to_finish_editing() {
		rUpdatePage.updateReimbursementButton.click();
	}

	@Then("the finance manager should be on the home page with their edits made to {string}")
	public void the_finance_manager_should_be_on_the_home_page_with_their_edits_made_to_status(String status) {
		new WebDriverWait(driver, Duration.ofSeconds(10))
			.until(ExpectedConditions.titleContains("Home Page"));
	
		assertEquals("Home Page", driver.getTitle());
		
		WebElement rStatus = driver.findElement(By.xpath("//*[@id=\"rTable\"]/tbody/tr[9]/td[3]"));
	    assertEquals(status, rStatus.getText());
	}
	
	@AfterAll
	public static void teardown() {
		driver.quit();
	}
}

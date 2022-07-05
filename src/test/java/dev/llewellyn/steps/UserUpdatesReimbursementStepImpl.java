package dev.llewellyn.steps;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import dev.llewellyn.pages.HomePage;
import dev.llewellyn.pages.LoginPage;
import dev.llewellyn.pages.UpdateReimbursementPage;
import io.cucumber.java.AfterAll;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class UserUpdatesReimbursementStepImpl {

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
	
	@Given("a user is on the login page and already has a reimbursement request submitted")
	public void a_user_is_on_the_login_page_and_already_has_a_reimbursement_request_submitted() {
	    driver.get("http://localhost:8080/login.html");
	}

	@When("a user types in their {string} and {string} and then clicks the login button")
	public void a_user_types_in_their_and_email_and_password_then_clicks_the_login_button(String email, String pass) {
		loginPage.emailInput.sendKeys(email);
		loginPage.passwordInput.sendKeys(pass);
		loginPage.loginButton.click();
	}

	@When("they click on the edit button for one of their reimbursement requests")
	public void they_click_on_the_edit_button_for_one_of_their_reimbursement_requests() {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		WebElement editButton = driver.findElement(By.xpath("//*[@id=\"rTable\"]/tbody/tr[3]/td[15]/button"));
		editButton.click();
	}

	@When("they fill out the {string} or {string} field")
	public void they_fill_out_the_or_field(String grade, String presentation) {
		new WebDriverWait(driver, Duration.ofSeconds(10))
			.until(ExpectedConditions.titleContains("Update Reimbursement"));
		
		boolean pSubmitted = Boolean.parseBoolean(presentation);
		System.out.println(presentation);
		System.out.println(pSubmitted);
	    if (pSubmitted) {
	    	rUpdatePage.pSubmittedInput.click();
	    } else {
	    	rUpdatePage.gReceivedInput.sendKeys(grade);
	    }
	}

	@When("they click on the update button")
	public void they_click_on_the_update_button() {
	    rUpdatePage.updateReimbursementButton.click();
	}

	@Then("the user should be on the home page with their edits made to either {string} or {string}")
	public void the_user_should_be_on_the_home_page_with_their_edits_made_to_either_grade_or_presentation(String grade, String presentation) {
		new WebDriverWait(driver, Duration.ofSeconds(10))
			.until(ExpectedConditions.titleContains("Home Page"));
		
		assertEquals("Home Page", driver.getTitle());
		
		boolean pSubmitted = Boolean.parseBoolean(presentation);
		if (pSubmitted) {
			WebElement rSPresentationSubmitted = driver.findElement(By.xpath("//*[@id=\"rTable\"]/tbody/tr[3]/td[11]"));
		    assertEquals(presentation, rSPresentationSubmitted.getText());
	    } else {
	    	WebElement rGradeReceived = driver.findElement(By.xpath("//*[@id=\"rTable\"]/tbody/tr[3]/td[10]"));
		    assertEquals(grade, rGradeReceived.getText());
	    }
	}
	
	@AfterAll
	public static void teardown() {
		driver.quit();
	}
}

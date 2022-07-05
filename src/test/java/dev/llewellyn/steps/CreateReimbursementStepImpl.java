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
import dev.llewellyn.pages.ReimbursementFormPage;
import dev.llewellyn.runners.CreateReimbursementRunner;
import io.cucumber.java.AfterAll;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class CreateReimbursementStepImpl {
	
	private static WebDriver driver = CreateReimbursementRunner.driver;
	private static LoginPage loginPage = CreateReimbursementRunner.loginPage;
	private static HomePage homePage = CreateReimbursementRunner.homePage;
	private static ReimbursementFormPage rFormPage = CreateReimbursementRunner.rFormPage;
	
	@BeforeAll
	public static void setup() {
		File chrome = new File("src/test/resources/chromedriver.exe");
		System.setProperty("webdriver.chrome.driver", chrome.getAbsolutePath());
		driver = new ChromeDriver();

		loginPage = new LoginPage(driver);
		homePage = new HomePage(driver);
		rFormPage = new ReimbursementFormPage(driver);
	}

	@Given("a employee is on the login page")
	public void a_employee_is_on_the_login_page() {
		driver.get("http://localhost:8080/login.html");
	}

	@When("a employee logs in successfully")
	public void a_employee_logs_in_successfully() {
		loginPage.emailInput.sendKeys("tu@gmail.com");
		loginPage.passwordInput.sendKeys("passy");
		loginPage.loginButton.click();
	}

	@When("they click on the create reimbursement request button on the home page")
	public void they_click_on_the_create_reimbursement_request_button_on_the_home_page() {
		new WebDriverWait(driver, Duration.ofSeconds(10))
			.until(ExpectedConditions.titleContains("Home Page"));
		
	    homePage.createReimbursementButton.click();
	}

	@When("they fill out the reimbursement request form")
	public void they_fill_out_the_reimbursement_request_form() {
		new WebDriverWait(driver, Duration.ofSeconds(10))
			.until(ExpectedConditions.titleContains("Reimbursement Form"));
	    
		rFormPage.rTypeInput = new Select(driver.findElement(By.id("rType")));
		rFormPage.gFormatInput = new Select(driver.findElement(By.id("gFormat")));
		
		rFormPage.rTypeInput.selectByValue("Technical Training");
		rFormPage.costInput.sendKeys("123.45");
		rFormPage.locationInput.sendKeys("UVM");
		rFormPage.gFormatInput.selectByValue("Letter");
		rFormPage.gPassInput.sendKeys("A");
		rFormPage.startDateInput.sendKeys("08012022");
		rFormPage.endDateInput.sendKeys("08312022");
		rFormPage.startTimeInput.sendKeys("830a");
		rFormPage.endTimeInput.sendKeys("330p");
		rFormPage.descriptionInput.sendKeys("This is a test");
		
		rFormPage.submitReimbursementButton.click();
	}

	@Then("employee should be on the home page")
	public void employee_should_be_on_the_home_page() {
		new WebDriverWait(driver, Duration.ofSeconds(10))
			.until(ExpectedConditions.titleContains("Home Page"));
		
	    assertEquals("Home Page", driver.getTitle());
	}
	
	@Then("be able to see the new reimbursement request")
	public void be_able_to_see_the_new_reimbursement_request() {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
	    WebElement rAmount = driver.findElement(By.xpath("//*[@id=\"rTable\"]/tbody/tr[2]/td[7]"));
	    assertEquals("$111.11", rAmount.getText());
	}
	
	@AfterAll
	public static void teardown() {
		driver.quit();
	}
}

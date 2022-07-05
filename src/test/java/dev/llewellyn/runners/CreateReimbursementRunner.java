package dev.llewellyn.runners;

import java.io.File;

import org.junit.platform.suite.api.Suite;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import dev.llewellyn.pages.HomePage;
import dev.llewellyn.pages.LoginPage;
import dev.llewellyn.pages.ReimbursementFormPage;
import io.cucumber.java.AfterAll;
import io.cucumber.java.BeforeAll;

@Suite
public class CreateReimbursementRunner {
	
	public static WebDriver driver;
	public static LoginPage loginPage;
	public static HomePage homePage;
	public static ReimbursementFormPage rFormPage;

	@BeforeAll
	public static void setup() {
		File chrome = new File("src/test/resources/chromedriver.exe");
		System.setProperty("webdriver.chrome.driver", chrome.getAbsolutePath());
		driver = new ChromeDriver();

		loginPage = new LoginPage(driver);
		homePage = new HomePage(driver);
		rFormPage = new ReimbursementFormPage(driver);
	}

	@AfterAll
	public static void teardown() {
		driver.quit();
	}
}

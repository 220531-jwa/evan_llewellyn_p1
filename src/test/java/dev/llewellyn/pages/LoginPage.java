package dev.llewellyn.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

	private WebDriver driver;
	
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id = "email")
	public WebElement emailInput;
	
	@FindBy(id = "pass")
	public WebElement passwordInput;
	
	@FindBy(xpath = "/html/body/div/div[4]/button")
	public WebElement loginButton;
}

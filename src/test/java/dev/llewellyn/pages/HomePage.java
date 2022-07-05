package dev.llewellyn.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

	private WebDriver driver;
	
	public HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id = "formButton")
	public WebElement createReimbursementButton;
	
	@FindBy(xpath = "/html/body/div/button[1]")
	public WebElement logoutButton;
}

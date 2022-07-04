package dev.llewellyn.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class UpdateReimbursementPage {

	private WebDriver driver;
	
	public UpdateReimbursementPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id = "updateGReceived")
	public WebElement gReceivedInput;
	
	@FindBy(id = "updatePresentation")
	public WebElement pSubmittedInput;
	
	public Select statusInput;
	
	@FindBy(id = "updateRAmount")
	public WebElement rAmountInput;
	
	@FindBy(xpath = "/html/body/div/button")
	public WebElement submitReimbursementButton;
	
	@FindBy(xpath = "/html/body/button")
	public WebElement cancelButton;
}

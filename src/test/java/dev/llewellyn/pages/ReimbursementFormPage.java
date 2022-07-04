package dev.llewellyn.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class ReimbursementFormPage {

	private WebDriver driver;
	
	public ReimbursementFormPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public Select rTypeInput;
	
	@FindBy(id = "cost")
	public WebElement costInput;
	
	@FindBy(id = "location")
	public WebElement locationInput;
	
	public Select gFormatInput;
	
	@FindBy(id = "gPass")
	public WebElement gPassInput;
	
	@FindBy(id = "startDate")
	public WebElement startDateInput;
	
	@FindBy(id = "endDate")
	public WebElement endDateInput;
	
	@FindBy(id = "startTime")
	public WebElement startTimeInput;
	
	@FindBy(id = "endTime")
	public WebElement endTimeInput;
	
	@FindBy(id = "description")
	public WebElement descriptionInput;
	
	@FindBy(xpath = "/html/body/div/button")
	public WebElement submitReimbursementButton;
	
	@FindBy(xpath = "/html/body/button")
	public WebElement cancelButton;
}

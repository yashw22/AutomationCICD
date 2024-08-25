package yashwardhan.SFD.PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import yashwardhan.SFD.AbstractComponents.AbstractComponent;

public class LandingPage extends AbstractComponent {
	WebDriver driver;

	@FindBy(id = "userEmail")
	WebElement userEmail;

	@FindBy(id = "userPassword")
	WebElement userPassword;

	@FindBy(id = "login")
	WebElement submitBtn;

	@FindBy(css = "[class*='flyInOut']")
	WebElement errMsg;

	public LandingPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public String getErrMsg() {
		waitForElementToAppear(errMsg);
		return errMsg.getText();
	}

	public ProductCataloguePage loginToApplication(String email, String password) {
		userEmail.sendKeys(email);
		userPassword.sendKeys(password);
		submitBtn.click();
		
		return new ProductCataloguePage(driver);
	}

	public void gotoPage() {
		driver.get("https://rahulshettyacademy.com/client");
	}
}

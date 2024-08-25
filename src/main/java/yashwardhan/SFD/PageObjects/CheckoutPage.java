package yashwardhan.SFD.PageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import yashwardhan.SFD.AbstractComponents.AbstractComponent;

public class CheckoutPage extends AbstractComponent {
	WebDriver driver;

	@FindBy(css = "input[placeholder='Select Country']")
	WebElement countryInput;

	@FindBy(css = ".ta-results")
	WebElement countryBox;

	@FindBy(css = ".ta-item")
	List<WebElement> countryItems;

	@FindBy(css = ".action__submit")
	WebElement placeOrderBtn;

	public CheckoutPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void chooseCountry(String country) {
		countryInput.sendKeys(country);
		waitForElementToAppear(countryBox);
		countryItems.stream().filter(item -> item.getText().equalsIgnoreCase(country)).findFirst().orElse(null).click();
	}

	public ConfirmationPage submitOrder() {
		placeOrderBtn.click();
		return new ConfirmationPage(driver);
	}

}

package yashwardhan.SFD.PageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import yashwardhan.SFD.AbstractComponents.AbstractComponent;

public class CartPage extends AbstractComponent {
	WebDriver driver;

	@FindBy(css = ".cartSection h3")
	List<WebElement> productTitles;

	@FindBy(css = ".totalRow button")
	WebElement checkoutBtn;

	public CartPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public Boolean verifyProductDisplay(String productName) {
		return productTitles.stream().anyMatch(cartProduct -> cartProduct.getText().equalsIgnoreCase(productName));
	}

	public CheckoutPage gotoCheckout() {
		checkoutBtn.click();
		return new CheckoutPage(driver);
	}
}

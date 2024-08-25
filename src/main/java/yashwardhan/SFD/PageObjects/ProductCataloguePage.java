package yashwardhan.SFD.PageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import yashwardhan.SFD.AbstractComponents.AbstractComponent;

public class ProductCataloguePage extends AbstractComponent {
	WebDriver driver;

	@FindBy(css = ".mb-3")
	List<WebElement> products;

	@FindBy(css = "#toast-container")
	WebElement toastMsg;

	@FindBy(css = ".ng-animating")
	WebElement spinner;

	By productsBy = By.cssSelector(".mb-3");
	By addToCartBy = By.cssSelector(".card-body button:last-of-type");

	public ProductCataloguePage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public List<WebElement> getProductList() {
		waitForElementToAppear(productsBy);
		return products;
	}

	public WebElement getProductByName(String productName) {
		return getProductList().stream()
				.filter(product -> product.findElement(By.cssSelector("b")).getText().equalsIgnoreCase(productName))
				.findFirst().orElse(null);
	}

	public void addProductToCart(String productName) {
		getProductByName(productName).findElement(addToCartBy).click();
		waitForElementToAppear(toastMsg);
//		waitForElementToDisappear(spinner);
//		waitForElementToDisappear(spinner);

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}

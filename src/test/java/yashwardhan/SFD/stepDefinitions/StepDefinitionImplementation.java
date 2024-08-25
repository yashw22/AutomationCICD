package yashwardhan.SFD.stepDefinitions;

import java.io.IOException;

import org.testng.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import yashwardhan.SFD.PageObjects.CartPage;
import yashwardhan.SFD.PageObjects.CheckoutPage;
import yashwardhan.SFD.PageObjects.ConfirmationPage;
import yashwardhan.SFD.PageObjects.LandingPage;
import yashwardhan.SFD.PageObjects.ProductCataloguePage;
import yashwardhan.SFD.TestComponents.BaseTest;

public class StepDefinitionImplementation extends BaseTest {
	String country = "united states";

	public LandingPage landingPage;
	public ProductCataloguePage productCatalogue;
	public ConfirmationPage confirmationPage;

	@Given("I landed on Ecommerce Page")
	public void I_landed_on_Ecommerce_Page() throws IOException {
		landingPage = launchApp();
	}

	@Given("^Logged in with email (.+) and password (.+)$")
	public void Logged_in_with_email_and_passowrd(String email, String password) {
		productCatalogue = landingPage.loginToApplication(email, password);
	}

	@When("^I add product (.+) to cart$")
	public void I_add_product_to_cart(String prodName) {
		productCatalogue.addProductToCart(prodName);
	}

	@When("^Checkout (.+) and submit the order$")
	public void Checkout_and_submit_order(String prodName) {
		CartPage cartPage = productCatalogue.gotoCartPage();
		Assert.assertTrue(cartPage.verifyProductDisplay(prodName));

		CheckoutPage checkoutPage = cartPage.gotoCheckout();
		checkoutPage.chooseCountry(country);

		confirmationPage = checkoutPage.submitOrder();
	}

	@Then("{string} message is displayed on ConfirmationPage")
	public void message_displayed_ConfirmationPage(String confirmationMag) {
		Assert.assertTrue(confirmationPage.getMsg().equalsIgnoreCase(confirmationMag));
		driver.close();
	}
	
	@Then("{string} message is displayed")
	public void message_displayed(String expectedErrMsg) {
		Assert.assertEquals(landingPage.getErrMsg(), expectedErrMsg);
		driver.close();
	}

}

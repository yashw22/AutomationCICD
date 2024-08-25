package yashwardhan.SFD.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import yashwardhan.SFD.PageObjects.CartPage;
import yashwardhan.SFD.PageObjects.ProductCataloguePage;
import yashwardhan.SFD.TestComponents.BaseTest;
import yashwardhan.SFD.TestComponents.Retry;

public class ErrorValidationTest extends BaseTest {

	@Test(groups = { "ErrorHandling" }, retryAnalyzer = Retry.class)
	public void LoginErrorValidation() {
		String email = "surajsharma@gmail.com", password = "SurajSharma";
		String expectedErrMsg = "Incorrect email or password.";
//		String expectedErrMsg = "Incorrect email, password.";

//		ProductCataloguePage productCatalogue = landingPage.loginToApplication(email, password);
		landingPage.loginToApplication(email, password);
		Assert.assertEquals(landingPage.getErrMsg(), expectedErrMsg);
	}

	@Test
	public void ProductErrorValidation() {
		String email = "surajsharma67@gmail.com", password = "SurajSharma67";
		String prodName = "ZARA COAT 3";

		ProductCataloguePage productCatalogue = landingPage.loginToApplication(email, password);
		productCatalogue.addProductToCart(prodName);

		CartPage cartPage = productCatalogue.gotoCartPage();
		Assert.assertFalse(cartPage.verifyProductDisplay(prodName + "33"));

	}

}

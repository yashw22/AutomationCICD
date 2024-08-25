package yashwardhan.SFD.tests;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import yashwardhan.SFD.PageObjects.CartPage;
import yashwardhan.SFD.PageObjects.CheckoutPage;
import yashwardhan.SFD.PageObjects.ConfirmationPage;
import yashwardhan.SFD.PageObjects.OrderPage;
import yashwardhan.SFD.PageObjects.ProductCataloguePage;
import yashwardhan.SFD.TestComponents.BaseTest;

public class SubmitOrderTest extends BaseTest {
	String country = "united states";
	String confirmationMag = "Thankyou for the order.";

	@Test(dataProvider = "getData", groups = { "Purchase" })
	public void SumbitAnOrder(HashMap<String, String> inp) throws IOException {
		ProductCataloguePage productCatalogue = landingPage.loginToApplication(inp.get("email"), inp.get("password"));
		productCatalogue.addProductToCart(inp.get("prodName"));

		CartPage cartPage = productCatalogue.gotoCartPage();
		Assert.assertTrue(cartPage.verifyProductDisplay(inp.get("prodName")));

		CheckoutPage checkoutPage = cartPage.gotoCheckout();
		checkoutPage.chooseCountry(country);

		ConfirmationPage confirmationPage = checkoutPage.submitOrder();
		Assert.assertTrue(confirmationPage.getMsg().equalsIgnoreCase(confirmationMag));
	}

	@Test(dependsOnMethods = { "SumbitAnOrder" }, dataProvider = "getData", groups = { "Purchase" })
	public void OrderHistoryCheck(HashMap<String, String> inp) throws IOException {
		ProductCataloguePage productCatalogue = landingPage.loginToApplication(inp.get("email"), inp.get("password"));
		productCatalogue.addProductToCart(inp.get("prodName"));

		OrderPage orderPage = productCatalogue.gotoOrdersPage();
		Assert.assertTrue(orderPage.verifyProductDisplay(inp.get("prodName")));
	}

	@DataProvider
	public Object[][] getData() throws IOException {
//		return new Object[][] { { "surajsharma67@gmail.com", "SurajSharma67", "ZARA COAT 3" },
//				{ "surajsharma68@gmail.com", "SurajSharma68", "ADIDAS ORIGINAL" } };

//		HashMap<String, String> map1 = new HashMap<String, String>();
//		map1.put("email", "surajsharma67@gmail.com");
//		map1.put("password", "SurajSharma67");
//		map1.put("prodName", "ZARA COAT 3");
//
//		HashMap<String, String> map2 = new HashMap<String, String>();
//		map2.put("email", "surajsharma68@gmail.com");
//		map2.put("password", "SurajSharma68");
//		map2.put("prodName", "ADIDAS ORIGINAL");

//		return new Object[][] { { map1 }, { map2 } };

		List<HashMap<String, String>> data = getJsonDataToMap(
				System.getProperty("user.dir") + "\\src\\test\\java\\yashwardhan\\SFD\\data\\PurchaseOrder.json");
		return new Object[][] { { data.get(0) }, { data.get(1) } };
	}
}

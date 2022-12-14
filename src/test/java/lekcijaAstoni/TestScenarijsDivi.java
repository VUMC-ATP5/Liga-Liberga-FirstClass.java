package lekcijaAstoni;

import lekcijaAstoni.pageObjects.CartPage;
import lekcijaAstoni.pageObjects.CheckoutPage;
import lekcijaAstoni.pageObjects.LoginPage;
import lekcijaAstoni.pageObjects.ProductsPage;
import lekcijaSeptini.labDarbs.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestScenarijsDivi extends BaseTest {
    @Test
    public void testCheckoutPageMandatoryFields() throws InterruptedException {
        System.out.println("1. Navigēt uz saiti https://www.saucedemo.com/");
        System.out.println("2. Ielogoties ar pareizu lietotāja vārdu/paroli");
        LoginPage loginPage = new LoginPage(driver);
        ProductsPage produktuLapa = new ProductsPage(driver);
        loginPage.login("standard_user","secret_sauce");
        Assert.assertEquals(produktuLapa.getPageTitle().getText(), "PRODUCTS");


        // System.out.println("Prece ir grozā");
        // produktuLapa.getProductItem().click();
        // Assert.assertEquals(produktuLapa.getProductItem(),"add-to-cart-sauce-labs-bike-light");

        //produktuLapa.getProductCartButton().click();
       // Assert.assertEquals(produktuLapa.getProductCartButton(),"shopping_cart_container");




        System.out.println("3. Doties uz grozu");
        CartPage grozaLapa = new CartPage(driver);
        produktuLapa.getCartButton().click();
        Assert.assertEquals(grozaLapa.getPageTitle().getText(),"YOUR CART");

        System.out.println("4. Doties uz Checkout");
        grozaLapa.getCheckoutButton().click();

        System.out.println("5. Pārbaudīt, ka FirstName/LastName/Zip code ir obligāts");
        CheckoutPage checkoutLapa = new CheckoutPage(driver);
        Assert.assertEquals(checkoutLapa.getPageTitle().getText(),"CHECKOUT: YOUR INFORMATION");
        checkoutLapa.clickContinueButton();

        Assert.assertEquals(checkoutLapa.getErrorText(),"Error: First Name is required");

        checkoutLapa.inputFirstName("Liga");
        checkoutLapa.clickContinueButton();
        Assert.assertEquals(checkoutLapa.getErrorText(),"Error: Last Name is required");

        checkoutLapa.inputLastName("Liberga");
        checkoutLapa.clickContinueButton();

        Assert.assertEquals(checkoutLapa.getErrorText(),"Error: Postal Code is required");
        checkoutLapa.inputPostalCode("LV3139");
        checkoutLapa.clickContinueButton();



    }




}

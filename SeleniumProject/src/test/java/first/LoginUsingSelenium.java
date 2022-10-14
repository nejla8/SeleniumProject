package first;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import java.util.concurrent.TimeUnit;

public class LoginUsingSelenium {

    protected WebDriver Driver;
    public WebElement BurgerMenu(){return Driver.findElement(By.id("react-burger-menu-btn"));}
    public WebElement ShoppingCartLink(){return Driver.findElement(By.cssSelector(".shopping_cart_link"));
    }

    @BeforeClass
    public void TestInitialize(){
        InitializeDriver();
        Login("standard_user", "secret_sauce");
    }

    public void InitializeDriver(){
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\snejla\\Downloads\\chromedriver_win32 (1)\\chromedriver.exe");
        Driver = new ChromeDriver();
        Driver.manage().window().maximize();
        Driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        Driver.get("https://www.saucedemo.com/");
    }
    public void Login(String username, String password){
        WebElement usernameInput = Driver.findElement(By.id("user-name"));
        WebElement passwordInput = Driver.findElement(By.id("password"));
        WebElement loginButton = Driver.findElement(By.name("login-button"));
        usernameInput.sendKeys(username);
        passwordInput.sendKeys(password);
        loginButton.click();
    }

    public void Logout(){
        BurgerMenu().click();
        Driver.findElement(By.id("logout_sidebar_link"));
    }

    @Test
    public void TC_Login() {
        InitializeDriver();
        Login("standard_user", "secret_sauce");
        Driver.findElement(By.cssSelector("span.title"));
        Assert.assertTrue(BurgerMenu().isDisplayed());
        Driver.findElement(By.cssSelector(".shopping_cart_link"));
        Driver.findElement(By.cssSelector("a[href*='facebook']"));
        Driver.findElement(By.cssSelector("a[href*='linkedin']"));
        Driver.findElement(By.cssSelector("a[href*='twitter']"));
        Logout();
        Driver.quit();
    }

    @Test
    public void TC_SendOrder(){
        InitializeDriver();
        Login("standard_user", "secret_sauce");
        Driver.findElement(By.linkText("Sauce Labs Backpack")).click();

        var title=Driver.findElement(By.cssSelector(".inventory_details_name.large_size")).getText();
        Assert.assertEquals(title, "Sauce Labs Backpack");

        var description= Driver.findElement(By.cssSelector(".inventory_details_desc.large_size")).getText();
        Assert.assertEquals(description, "carry.allTheThings() with the sleek, streamlined Sly Pack that melds uncompromising style with unequaled laptop and tablet protection.");

        var price=Driver.findElement(By.cssSelector(".inventory_details_price")).getText();
        Assert.assertEquals(price, "$29.99");

        var addToCartButton = Driver.findElement(By.cssSelector("#add-to-cart-sauce-labs-backpack"));
        addToCartButton.click();

        var backToProduct = Driver.findElement(By.cssSelector("#back-to-products"));
        backToProduct.click();

        var addToCartButton2=Driver.findElement(By.id("add-to-cart-sauce-labs-fleece-jacket"));
        addToCartButton2.click();

        ShoppingCartLink().click();

        var checkout=Driver.findElement(By.id("checkout"));
        checkout.click();

        var nameInput= Driver.findElement(By.id("first-name"));
        nameInput.sendKeys("Nejla");

        var surnameInput= Driver.findElement(By.id("last-name"));
        surnameInput.sendKeys("Sehagic");

        var zipcode= Driver.findElement(By.id("postal-code"));
        zipcode.sendKeys("72240");

        var Continue=Driver.findElement(By.id("continue"));
        Continue.click();

        var finish=Driver.findElement(By.id("finish"));
        finish.click();

        var finalMessage=Driver.findElement(By.cssSelector(".complete-header")).getText();
        Assert.assertEquals(finalMessage, "THANK YOU FOR YOUR ORDER");
        Logout();
        Driver.quit();
    }
    @Test
    public void TC_NegativeTestPassword(){
        InitializeDriver();
        Login("standard_user", "nejla");
        var finalMess=Driver.findElement(By.cssSelector("[data-test='error']")).getText();
        Assert.assertEquals(finalMess, "Epic sadface: Username and password do not match any user in this service");

        Driver.quit();
    }

    @Test
    public void TC_NegativeTestUserName(){
        InitializeDriver();
        Login("StandardUser", "secret_sauce");
        var finalMess=Driver.findElement(By.cssSelector("[data-test='error']")).getText();
        Assert.assertEquals(finalMess, "Epic sadface: Username and password do not match any user in this service");

        Driver.quit();
    }

    @Test
    public void TC_NegativeTest2() {
        InitializeDriver();
        Login("standard_user", "secret_sauce");
        Driver.findElement(By.linkText("Sauce Labs Backpack")).click();

        var title = Driver.findElement(By.cssSelector(".inventory_details_name.large_size")).getText();
        Assert.assertEquals(title, "Sauce Labs Backpack");

        var addToCartButton = Driver.findElement(By.cssSelector("#add-to-cart-sauce-labs-backpack"));
        addToCartButton.click();

        ShoppingCartLink().click();

        var checkout = Driver.findElement(By.id("checkout"));
        checkout.click();

        var nameInput = Driver.findElement(By.id("first-name"));
        nameInput.sendKeys("Nejla");

        var surnameInput = Driver.findElement(By.id("last-name"));
        surnameInput.sendKeys("");

        var zipcode = Driver.findElement(By.id("postal-code"));
        zipcode.sendKeys("72240");

        var Continue = Driver.findElement(By.id("continue"));
        Continue.click();

        var finMessage = Driver.findElement(By.cssSelector("[data-test='error']")).getText();
        Assert.assertEquals(finMessage, "Error: Last Name is required");
        Logout();
        Driver.quit();
    }
    @Test
    public void TC_NegativeTest3() {
        InitializeDriver();
        Login("standard_user", "secret_sauce");
        Driver.findElement(By.linkText("Sauce Labs Backpack")).click();

        var title = Driver.findElement(By.cssSelector(".inventory_details_name.large_size")).getText();
        Assert.assertEquals(title, "Sauce Labs Backpack");

        var addToCartButton = Driver.findElement(By.cssSelector("#add-to-cart-sauce-labs-backpack"));
        addToCartButton.click();

        ShoppingCartLink().click();

        var checkout = Driver.findElement(By.id("checkout"));
        checkout.click();

        var nameInput = Driver.findElement(By.id("first-name"));
        nameInput.sendKeys("Nejla");

        var surnameInput = Driver.findElement(By.id("last-name"));
        surnameInput.sendKeys("Sehagic");

        var zipcode = Driver.findElement(By.id("postal-code"));
        zipcode.sendKeys("");

        var Continue = Driver.findElement(By.id("continue"));
        Continue.click();

        var finMessage = Driver.findElement(By.cssSelector("[data-test='error']")).getText();
        Assert.assertEquals(finMessage, "Error: Postal Code is required");
        Logout();
        Driver.quit();
    }
    @AfterClass
    public void CleanUp(){
        Logout();
        Driver.quit();
    }


}
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.concurrent.TimeUnit;


public class amazonCheckout {

    String siteUrl = "https://www.amazon.com";
    String email= "dakshdubey97@gmail.com";     //type your EMAIL ID
    String pass="";                             //type your PASSWORD
    static int count1 =0;

    public static void performLogin(WebDriver driver, String email, String pass){
                //Method for login step, PLEASE ENTER THE PASSWORD & OTP within 1min while running
        driver.findElement(By.xpath("//input[@type='email']")).sendKeys(email,Keys.ENTER);
        driver.findElement(By.xpath("//input[@type='password']")).sendKeys(pass,Keys.ENTER);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='Order Summary']")));
        System.out.println("Successfully reached the checkout screen");
        count1++;

    }

    public static void searchItem_andSelect(WebDriver driver, String query){            //method to search for item on the amazon website
        driver.findElement(By.xpath("//*[@class='nav-search-field ']//input")).sendKeys(query, Keys.ENTER);
        goToSleep(2000);
        driver.findElement(By.xpath("(//*[contains(@class,'s-product-image-container')])[1]")).click();
        goToSleep(1000);
        driver.findElement(By.id("add-to-cart-button")).click();
        count1++;
    }

    @Test
    public void TC_01_VerifyAddingItemsToCart(){

        ChromeOptions options = new ChromeOptions();
        options.setBrowserVersion("123");                    //starting the chrome browser for testing on version Version 123.0.6312.86
        WebDriver driver = new ChromeDriver(options);       //creating the driver
        driver.get(siteUrl);                            //opening the amazon website

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        By addPopup = By.xpath("//*[@id='nav-main']//input[@data-action-type='DISMISS']");
        if (driver.findElement(addPopup).isDisplayed())         //dismissing the change address/pin code popup shown
            driver.findElement(addPopup).click();
        String count = driver.findElement(By.xpath("//span[@id='nav-cart-count']")).getText();
        System.out.println("Cart item: "+ count);
        searchItem_andSelect(driver,"Backlit keyboard");
        driver.findElement(By.xpath("//span[@id='nav-cart-count']")).click();       //clicking the cart icon on top right
        count = driver.findElement(By.xpath("//span[@id='nav-cart-count']")).getText();

        Assert.assertEquals(Integer.parseInt(count),1,"The item is added to card and cart count is incremented");
        System.out.println("Cart item: "+ count);
        //actions.perform();
    }

    // ----------- PLEASE ADD THE LOGIN CREDENTIALS MANDATORY BEFORE RUNNING BELOW TC_02  ----------------//
    @Test
    public void TC_02_VerifyChangingShippingAddress() {

        ChromeOptions options = new ChromeOptions();
        options.setBrowserVersion("123");                //starting the chrome browser for testing on version Version 123.0.6312.86
        WebDriver driver = new ChromeDriver(options);
        driver.get(siteUrl);                           //opening the amazon website

        driver.manage().window().maximize();
        goToSleep(2000);
        By addPopup = By.xpath("//*[@id='nav-main']//input[@data-action-type='DISMISS']");
        if (driver.findElement(addPopup).isDisplayed())         //dismissing the change address/pin code popup shown
            driver.findElement(addPopup).click();
        else driver.navigate().refresh();
        String count = driver.findElement(By.xpath("//span[@id='nav-cart-count']")).getText();
        System.out.println(count + " items in cart");
        searchItem_andSelect(driver,"Mobile");
        System.out.println("Item added to cart");
        driver.findElement(By.name("proceedToRetailCheckout")).click();
        performLogin(driver,email,pass);                //calling the login function

        String name = driver.findElement(By.id("address-ui-widgets-FullName")).getText();
        String address_line1 = driver.findElement(By.id("address-ui-widgets-AddressLineOne")).getText();
        String address_line2 = driver.findElement(By.id("address-ui-widgets-AddressLineTwo")).getText();

                                                        //fetching the current address & validating the address shown on diff. sections on checkout page
        Assert.assertEquals(driver.findElement(By.xpath("//li[@class='displayAddressLI displayAddressFullName']")).getText(), name, "Verify checking the address name Before.. ");
        Assert.assertEquals(driver.findElement(By.xpath("//li[@class='displayAddressLI displayAddressAddressLine1']")).getText(), address_line1, "Verify checking the addressline 1 Before.. ");
        Assert.assertEquals(driver.findElement(By.xpath("//li[@class='displayAddressLI displayAddressAddressLine2']")).getText(), address_line2, "Verify checking the addressline 1 Before.. ");
        System.out.println("The address is read & validated before changing address");


        driver.findElement(By.id("addressChangeLinkId")).click();
        driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
        String xpath_txt = "//*[@data-action='select-address-in-list']//*[@class='break-word']";
        int i = 4;
        System.out.println(i);
        driver.findElements(By.xpath(xpath_txt)).get(i-1).click();
        String radioOption_class = driver.findElements(By.xpath("//*[contains(@class,'a-row address-row')]")).get(1).getAttribute("class");  //fetching the webelement class value
        System.out.println("Radio option clicked");
        System.out.println(radioOption_class);
        Assert.assertTrue(radioOption_class.contains("list-address-selected"),"Verifying the address radio option clicked is selected");

        driver.findElement(By.xpath("//*[@id='shipToThisAddressButton']/span")).click();
        goToSleep(3000);
        Assert.assertNotEquals(driver.findElement(By.xpath("//li[@class='displayAddressLI displayAddressFullName']")).getText(),name,"Verify checking the address name After.. ");
        Assert.assertNotEquals(driver.findElement(By.xpath("//li[@class='displayAddressLI displayAddressAddressLine1']")).getText(),address_line1,"Verify checking the addressline 1 After.. ");
        Assert.assertNotEquals(driver.findElement(By.xpath("//li[@class='displayAddressLI displayAddressAddressLine2']")).getText(),address_line2,"Verify checking the addressline 1 After.. ");
        System.out.println("The address is read & validated after changing address on checkout");

    }

    @Test
    public void TC_03_VerifyRemovingItemsFromCart(){

        ChromeOptions options = new ChromeOptions();
        options.setBrowserVersion("123");               //starting the chrome browser for testing on version Version 123.0.6312.86
        WebDriver driver = new ChromeDriver(options);
        driver.get(siteUrl);                           //opening the amazon website

        driver.manage().window().maximize();
        goToSleep(1000);
        By addPopup = By.xpath("//*[@id='nav-main']//input[@data-action-type='DISMISS']");
        if (driver.findElement(addPopup).isDisplayed())         //dismissing the change address/pin code popup shown
            driver.findElement(addPopup).click();
        String cartcount = driver.findElement(By.xpath("//span[@id='nav-cart-count']")).getText();
        Assert.assertEquals(Integer.parseInt(cartcount),count1,"verifying the intial cart items count shown");
        System.out.println("Cart item: "+ count1);
        searchItem_andSelect(driver,"Backlit keyboard");
        searchItem_andSelect(driver,"Table");
        searchItem_andSelect(driver,"Pillow");
        driver.navigate().back();
        cartcount = driver.findElement(By.xpath("//span[@id='nav-cart-count']")).getText();
        Assert.assertEquals(Integer.parseInt(cartcount),count1,"verifying the intial cart items count shown");
        driver.findElement(By.xpath("//span[@id='nav-cart-count']")).click();       //clicking the cart icon on top right
        deleteItemFromCart(driver);

        cartcount = driver.findElement(By.xpath("//span[@id='nav-cart-count']")).getText();
        Assert.assertEquals(Integer.parseInt(cartcount),count1,"The item removed and final cart count matches");
        System.out.println("Cart item at end: "+ count1);
    }

    public static void deleteItemFromCart(WebDriver driver){
        Assert.assertTrue(driver.findElement(By.xpath("//h1[contains(text(),'Shopping Cart')]")).isDisplayed(),"Verify landed on the shopping cart screen");
        driver.findElements(By.xpath("//input[@value='Delete']")).get(1).click();
        count1--;
        goToSleep(2000);
    }

    static void goToSleep(int ms){          //method for waiting for provided milli seconds before next step
        try {
            Thread.sleep(ms);         //always halt for milli-sec
        }
        catch(InterruptedException e) {
            e.printStackTrace();
        }
    }
}


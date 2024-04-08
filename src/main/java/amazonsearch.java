import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Test;

import java.sql.SQLOutput;

public class amazonsearch {

    @Test
    public void TC_searchItem(){

        ChromeOptions option = new ChromeOptions();
        option.setBrowserVersion("123");
        WebDriver driver = new ChromeDriver(option);

        driver.get("https://www.amazon.in/");
        driver.manage().window().maximize();
        driver.findElement(By.xpath("//input[@id='twotabsearchtextbox']")).sendKeys("iPHone 15", Keys.ENTER);

        String title = "//span[@class='a-size-medium a-color-base a-text-normal']";
        long n = driver.findElements(By.xpath(title)).stream().count();
        String titleNames;

        System.out.println("Titles of the items listed for search term:-");
        for(int i= 0; i<n; i++){
            titleNames = driver.findElements(By.xpath(title)).get(i).getText();
            System.out.println(titleNames);
        }

    }

}

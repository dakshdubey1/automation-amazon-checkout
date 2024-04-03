
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;


public class automationTestcase {
    @Test
    public void TC1_Validate_Movies() {

        //System.setProperty("webdriver.chrome.driver","/home/dakshdubey/Desktop/chromedriver_linux64/chromedriver");
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.google.com");

        driver.findElement(By.xpath("//*[@name='q']")).sendKeys("Movies", Keys.ENTER);

        String text = driver.findElement(By.xpath("(//h3[contains(text(),'Movies')])[2]")).getText();
        System.out.println(text);
        Assert.assertTrue(text.length()> "Movies".length());
    }

}

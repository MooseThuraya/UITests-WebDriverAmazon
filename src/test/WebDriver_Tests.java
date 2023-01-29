

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.jupiter.api.Assertions.*;

public class WebDriver_Tests {

    @Test
    public void testAmazonSearch_addToCart() throws InterruptedException, IOException {
        // Optional. If not specified, WebDriver searches the PATH for chromedriver.
        System.setProperty("webdriver.chrome.driver", "c:\\users\\mosta\\chromedriver\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("http://www.amazon.com/");

        //screenShot 1
        File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile, new File("c:\\tmp\\addToCart1.png"));

        //Check cart count
        WebElement searchBox = driver.findElement(By.id("nav-cart-count"));

        int startingCartCount = Integer.parseInt(searchBox.getText());

        searchBox = driver.findElement(By.id("twotabsearchtextbox"));
        searchBox.sendKeys("Dewalt oscillating 20V Max");
        searchBox.submit();
        Thread.sleep(5000); // wait 5 seconds, Let the user actually see something!
        //screenShot 2
        scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile, new File("c:\\tmp\\addToCart2.png"));

        searchBox = driver.findElement(By.linkText("DEWALT 20V Max XR Oscillating Multi-Tool, Variable Speed, Tool Only (DCS356B)"));
        searchBox.click();

        Thread.sleep(5000);

        //screenShot 3
        scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile, new File("c:\\tmp\\addToCart3.png"));

        searchBox = driver.findElement(By.xpath("//*[@id=\"add-to-cart-button\"]"));
        searchBox.click();

        Thread.sleep(5000);

        //screenShot 4
        scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile, new File("c:\\tmp\\addToCart4.png"));

        searchBox = driver.findElement(By.xpath("//*[@id=\"attachSiNoCoverage\"]/span/input"));
        searchBox.click();

        Thread.sleep(5000);

        //screenShot 5
        scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile, new File("c:\\tmp\\addToCart5.png"));
        driver.get("http://www.amazon.com/");

        //screenShot 6
        scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile, new File("c:\\tmp\\addToCart6.png"));
        //Check current cart count
        searchBox = driver.findElement(By.id("nav-cart-count"));
        int currentCartCount = Integer.parseInt(searchBox.getText());

        //check cartCount went up by one
        assertEquals(++startingCartCount, currentCartCount);

        Thread.sleep(1000); // wait 5 seconds, Let the user actually see something!
        driver.quit();
    }

    @Test
    public void testAmazonSearch_checkSignInStatus() throws InterruptedException, IOException {
        // Optional. If not specified, WebDriver searches the PATH for chromedriver.
        System.setProperty("webdriver.chrome.driver", "c:\\users\\mosta\\chromedriver\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        WebElement searchBox;

        driver.get("http://smile.amazon.com/");
        Thread.sleep(5000);

        //screenShot 1
        File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile, new File("c:\\tmp\\checkSignInStatus1.png"));

        try{
            searchBox = driver.findElement(By.xpath("//*[@id=\"ge-hello\"]/div[2]/span"));
        }catch (Exception e){
            throw new IllegalArgumentException("The user is signed in");
        }

        searchBox = driver.findElement(By.xpath("//*[@id=\"ge-footer\"]/div[2]/div[1]/a"));
        searchBox.click();
        Thread.sleep(5000); // wait 5 seconds, Let the user actually see something!

        //screenShot 2
        scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile, new File("c:\\tmp\\checkSignInStatus2.png"));

        searchBox = driver.findElement(By.xpath("//*[@id=\"a-autoid-0-announce\"]"));

        String targetButtonTxt = searchBox.getText();

        Thread.sleep(1000); // wait 5 seconds, Let the user actually see something!
        assertEquals("Get Started", targetButtonTxt);
        driver.quit();
    }

}

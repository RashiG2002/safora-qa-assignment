import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;

public class ContactFormTest {

    public static void main(String[] args) {

        WebDriverManager.chromedriver().setup();

        WebDriver driver = new ChromeDriver();

        try {

            driver.manage().window().maximize();

            driver.get("https://safora.se/en/");

            WebDriverWait wait =
                    new WebDriverWait(driver, Duration.ofSeconds(10));

            wait.until(ExpectedConditions.elementToBeClickable(
                    By.partialLinkText("Contact"))).click();

            wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.name("name"))).sendKeys("Ashi");

            driver.findElement(By.name("email"))
                    .sendKeys("ashi@gmail.com");

            driver.findElement(By.name("message"))
                    .sendKeys("Testing QA Assignment");

            WebElement submitButton =
                    driver.findElement(By.xpath("//button[@type='submit']"));

            ((JavascriptExecutor) driver)
                    .executeScript("arguments[0].scrollIntoView(true);", submitButton);

            Thread.sleep(1000);

            ((JavascriptExecutor) driver)
                    .executeScript("arguments[0].click();", submitButton);

            System.out.println("Form submitted successfully");

            Thread.sleep(3000);

        } catch (Exception e) {

            e.printStackTrace();

        } finally {

            driver.quit();
        }
    }
}
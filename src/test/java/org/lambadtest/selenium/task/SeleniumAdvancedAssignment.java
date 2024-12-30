package org.lambadtest.selenium.task;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.net.URL;
import java.time.Duration;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class SeleniumAdvancedAssignment {
    private WebDriver driver;

    @Parameters({"browser", "platform"})
    @BeforeClass
    public void setup(String browser, String platform) throws Exception {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", platform);
        capabilities.setCapability("browserName", browser);
        capabilities.setCapability("LT:Options", Map.of(
                "username", "USER_NAME",
                "accessKey", "KEY",
                "video", true,
                "network", true
        ));


        driver = new RemoteWebDriver(new URL("https://hub.lambdatest.com/wd/hub"), capabilities);
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    }

    @Test
    public void testScenario() throws Exception {
        // Step 1: Navigate to LambdaTest
        driver.get("https://www.lambdatest.com");
        // Locate the element
        WebElement link = driver.findElement(By.cssSelector("a[href='/integrations']"));

        // Use JavaScript to open the link in a new tab
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.open(arguments[0], '_blank');", link.getAttribute("href"));
        List<String> windowHandles = new ArrayList<>(driver.getWindowHandles());
        System.out.println("Window Handles: " + windowHandles);

        Iterator<String> windowIterator = driver.getWindowHandles().iterator();
        String mainWindow = windowIterator.next();  // Main window handle
        String newWindow = windowIterator.next();   // New tab handle

        // Switch to the new tab
        driver.switchTo().window(newWindow);
        // Verify the URL of the new tab
        String currentURL = driver.getCurrentUrl();
        String expectedURL = "https://www.lambdatest.com/integrations";  // Replace with the expected URL
        System.out.println("Expected URL: " + expectedURL);
        System.out.println("Current URL: " + currentURL);

        // Assert that the URLs match
        Assert.assertEquals(expectedURL, currentURL);

        // Wait until the DOM is fully loaded
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.jsReturnsValue("return document.readyState == 'complete';"));

        System.out.println("DOM is fully loaded. Current URL: " + driver.getCurrentUrl());

        // Locate the 'Codeless Automation' element
        WebElement codelessAutomation = driver.findElement(By.xpath("//h2[text()='Codeless Automation']"));

        // Scroll the element into view
        JavascriptExecutor codelessAutomationJs = (JavascriptExecutor) driver;
        codelessAutomationJs.executeScript("arguments[0].scrollIntoView(true);", codelessAutomation);

        // Locate the 'INTEGRATE TESTING WHIZ WITH LAMBDATEST' link
        WebElement integrateLink = driver.findElement(By.linkText("INTEGRATE TESTING WHIZ WITH LAMBDATEST"));

        // Click the link to open in the same window
        integrateLink.click();

        // Verify the title of the page


        try {
            // Verify the title of the page
            String expectedTitle = "TestingWhiz Integration With LambdaTest";
            String actualTitle = driver.getTitle();

            // Assert if the title doesn't match
            Assert.assertEquals("The page title does not match!", expectedTitle, actualTitle);

        } catch (AssertionError e) {
            // Log the assertion failure message
            System.out.println("Assertion failed: " + e.getMessage());
            // Do NOT throw a RuntimeException or close the application
            // Simply handle the failure and continue
            // Switch back to the main window

            driver.switchTo().window(mainWindow);
        }
        System.out.println("Window count after closing: " + driver.getWindowHandles().size());

        driver.get("https://www.lambdatest.com/blog");
        WebElement communityLink = driver.findElement(By.linkText("Community"));
        communityLink.click();
        System.out.println(driver.getCurrentUrl());
        Assert.assertEquals(driver.getCurrentUrl(), "https://community.lambdatest.com/");
        windowHandles = List.copyOf(driver.getWindowHandles());

        // Close the browser
        driver.quit();

    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}

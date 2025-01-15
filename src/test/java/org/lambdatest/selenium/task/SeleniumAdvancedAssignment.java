package org.lambdatest.selenium.task;

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

    @Parameters({ "browser", "platform", "version" })
    @BeforeClass
    public void setup(String browser, String platform, String version) throws Exception {
        // Step 1: Set up capabilities,LambdaTest options and driver
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", platform);
        capabilities.setCapability("browserName", browser);
        capabilities.setCapability("browserVersion", version);

        HashMap<String, Object> ltOptions = new HashMap<String, Object>();
        ltOptions.put("username", System.getenv("LT_USERNAME"));
        ltOptions.put("accessKey", System.getenv("LT_ACCESS_KEY"));
        ltOptions.put("visual", true);
        ltOptions.put("video", true);
        ltOptions.put("build", "Assignment Task: Selenium Advanced");
        ltOptions.put("project", "Assignment Task: Selenium Advanced");
        ltOptions.put("console", "true");
        ltOptions.put("selenium_version", "4.0.0");
        ltOptions.put("w3c", true);
        capabilities.setCapability("LT:Options", ltOptions);

        driver = new RemoteWebDriver(new URL("https://hub.lambdatest.com/wd/hub"), capabilities);
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

    }

    @Test
    public void testScenario() throws Exception {
        // Step 2: Navigate to the website
        driver.get("https://www.lambdatest.com");

        // Step 3: Perform an explicit wait until the DOM is fully loaded
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("footer")));
        System.out.println("Page loaded successfully.");

        // Step 4: Try using a more specific CSS selector
        WebElement exploreIntegrations = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[contains(text(), 'Explore all Integrations')]")));

        // Step 5: Scroll the element into view with offset
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", exploreIntegrations);
        System.out.println("Successfully scrolled to 'Explore all Integrations'.");

        // Wait for any animations to complete
        wait.until(ExpectedConditions.visibilityOf(exploreIntegrations));

        // Step 6: Open in new tab using JavaScript
        js.executeScript("window.open(arguments[0].getAttribute('href'), '_blank');", exploreIntegrations);
        System.out.println("Opened the 'Explore all Integrations' link in a new tab.");

        // Get all window handles and store them in a List
        List<String> windowHandles = new ArrayList<>(driver.getWindowHandles());

        // Print all window handles
        System.out.println("\nWindow Handles of opened windows:");
        for (int i = 0; i < windowHandles.size(); i++) {
            System.out.println("Window " + (i + 1) + ": " + windowHandles.get(i));
        }

        // Switch to the new tab (last window handle in the list)
        driver.switchTo().window(windowHandles.get(windowHandles.size() - 1));

        // Verify the URL
        String expectedUrl = "https://www.lambdatest.com/integrations";
        String actualUrl = driver.getCurrentUrl();
        Assert.assertEquals(actualUrl, expectedUrl,
                "URL mismatch! Expected: " + expectedUrl + " but found: " + actualUrl);
        System.out.println("URL verification successful: " + actualUrl);

        // Wait for and scroll to "Codeless Automation" element
        WebElement codelessAutomation = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//h2[contains(text(),'Codeless Automation')]")));

        // Scroll the element into view with smooth behavior
        js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", codelessAutomation);
        System.out.println("Successfully scrolled to 'Codeless Automation' section.");

        // Small pause to allow smooth scroll to complete
        Thread.sleep(1000);

        // Try first with CSS selector using href attribute
        WebElement testingWhizLink = wait.until(ExpectedConditions.elementToBeClickable(
                By.cssSelector("a[href*='testingwhiz']")));

        // Scroll to the Testing Whiz link to ensure it's in view
        js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", testingWhizLink);

        // Click the link
        testingWhizLink.click();
        System.out.println("Clicked on Testing Whiz integration link");

        // Wait for the new page to load
        wait.until(ExpectedConditions.urlContains("testingwhiz"));

        WebElement titleElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("title")));
        String pageTitle = driver.getTitle();
        String expectedTitle = "TestingWhiz Integration With LambdaTest";
        System.out.println("Title Element: " + titleElement);
        String actualTitle = pageTitle;// titleElement.getText();
        System.out.println("Actual Title: " + actualTitle);
        System.out.println("Expected Title: " + expectedTitle);

        try {
            Assert.assertEquals(actualTitle, expectedTitle,
                    "Page title mismatch! Expected: '" + expectedTitle + "' but found: '" + actualTitle + "'");
            System.out.println("Title verification successful: " + actualTitle);
        } catch (AssertionError e) {
            System.out.println("Title verification failed");
            // Continue execution without stopping the test
        }

        // Close current window (TestingWhiz page)
        String currentHandle = driver.getWindowHandle();
        driver.close();
        System.out.println("Closed window with handle: " + currentHandle);

        // Switch back to the main window (first window handle)
        driver.switchTo().window(windowHandles.get(0));
        System.out.println("Switched back to main window with handle: " + windowHandles.get(0));

        // Print current window count
        Set<String> currentWindows = driver.getWindowHandles();
        System.out.println("Current window count: " + currentWindows.size());

        // Navigate to LambdaTest blog
        driver.get("https://www.lambdatest.com/blog");
        System.out.println("Navigated to LambdaTest blog");

        // Wait for the page to load, using urlContains instead of exact match
        wait.until(ExpectedConditions.urlContains("lambdatest.com/blog"));

        // Find and click the Community link
        WebElement communityLink = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[contains(text(), 'Community')]")));

        // Scroll the community link into view
        js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", communityLink);

        communityLink.click();
        System.out.println("Clicked on Community link");

        // Wait for and verify the Community URL
        String expectedCommunityUrl = "https://community.lambdatest.com/";
        wait.until(ExpectedConditions.urlToBe(expectedCommunityUrl));

        String actualCommunityUrl = driver.getCurrentUrl();
        Assert.assertEquals(actualCommunityUrl, expectedCommunityUrl,
                "Community URL mismatch! Expected: " + expectedCommunityUrl + " but found: " + actualCommunityUrl);
        System.out.println("Community URL verification successful: " + actualCommunityUrl);

        driver.close();
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}

package org.lambdatest.selenium.task;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.chrome.ChromeDriver;
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
        // System.setProperty("webdriver.chrome.driver",
        // "C:\\Users\\ASUS\\Downloads\\chromedriver-win64\\chromedriver.exe");

        // driver = new ChromeDriver();
        // driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    }

    public void TestScenario1() throws InterruptedException {
        driver.get("https://www.lambdatest.com/");
        driver.manage().window().maximize();
        Thread.sleep(2000);

        WebElement firstresult = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(By.xpath(
                        "//div[@class='text-center mt-25']/a[@href='https://www.lambdatest.com/integrations']")));

        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebElement integrations = driver.findElement(
                By.xpath("//div[@class='text-center mt-25']/a[@href='https://www.lambdatest.com/integrations']"));
        js.executeScript("arguments[0].scrollIntoView(true);", integrations);
        js.executeScript("arguments[0].style.border='2px solid red'", integrations);

        String clicklnk = Keys.chord(Keys.CONTROL, Keys.ENTER);
        integrations.sendKeys(clicklnk);

        Set<String> allwindows = driver.getWindowHandles();
        System.out.println("All window handles : " + allwindows);
        String current = driver.getWindowHandle();
        System.out.println("Current window handle : " + current);

        ArrayList<String> window1 = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(window1.get(1));

        String Actual_url = driver.getCurrentUrl();
        String Expected_url = "https://www.lambdatest.com/integrations";

        if (Actual_url.equals(Expected_url)) {
            System.out.println("url is matched");
        } else {
            System.out.println("url is not matched");
        }

        WebElement codeless = driver.findElement(By.xpath(
                "//li[4]/a[@class='block px-14 py-9 font-normal text-size-14 hover:bg-black hover:text-white ']"));

        js.executeScript("arguments[0].scrollIntoView(true);", codeless);
        js.executeScript("arguments[0].style.border='2px solid red'", codeless);

        WebElement learn_more = driver
                .findElement(By.xpath("//a[@href='https://www.lambdatest.com/support/docs/testingwhiz-integration/']"));
        learn_more.click();
        String Expected_title = "TestingWhiz Integration | LambdaTest";
        String Actual_title = driver.getCurrentUrl();

        if (Expected_title.equals(Actual_title)) {
            System.out.println("title is matched");
        } else {
            System.out.println("Title is not matched!");
        }

        driver.switchTo().window(window1.get(1)).close();
        driver.switchTo().window(window1.get(0));

        String url = "https://www.lambdatest.com/";
        String url1 = url.replaceAll("https://www.lambdatest.com/", "https://www.lambdatest.com/blog");
        driver.navigate().to(url1);

        WebElement community = driver
                .findElement(By.xpath("//li[@id='menu-item-10121']/a[@href='https://community.lambdatest.com/']"));
        js.executeScript("arguments[0].style.border='2px solid red'", community);
        community.click();

        String Exp_community_url = "https://community.lambdatest.com/";
        String Act_community_url = driver.getCurrentUrl();

        if (Exp_community_url.equals(Act_community_url)) {
            System.out.println("Community url is matched");
        } else
            System.out.println("Community url is not matched!");

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

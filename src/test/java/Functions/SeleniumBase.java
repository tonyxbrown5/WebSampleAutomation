package Functions;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;


public class SeleniumBase{
	
	public static WebDriver driver;
	public static ExtentReports extent = new ExtentReports();
	public static ExtentSparkReporter spark = new ExtentSparkReporter("target/Spark.html");
	public static ExtentTest test;
	public static Exception e;
	
	// Selenium function for fluent wait
	public FluentWait<WebDriver> fWait() {
		FluentWait<WebDriver> fluentWait = new FluentWait<>(driver)
				.withTimeout(Duration.ofSeconds(10))
				.pollingEvery(Duration.ofSeconds(1))
				.ignoring(NoSuchElementException.class)
				.ignoring(StaleElementReferenceException.class, ElementNotInteractableException.class);
		        
        return fluentWait;
	}
	
	public void waitInSeconds(int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
	
	public void click(By elm) {
		fWait().until(ExpectedConditions.elementToBeClickable(driver.findElement(elm))).click();
	}
	
	public void textInput(By elm, String txt) {
		fWait().until(ExpectedConditions.visibilityOfElementLocated(elm)).sendKeys(txt);
	}
	
	public void notDisplayed(By elm) {
		fWait().until(ExpectedConditions.invisibilityOfElementLocated(elm));
	}
	
	public boolean isDisplayed(By elm) {
		return fWait().until(ExpectedConditions.visibilityOfElementLocated(elm)).isDisplayed();
	}
	
	public boolean isEqual(String txt, By elm) {
		return txt.equals(getText(elm));
	}
	
	public boolean doesContain(String txt, By elm) {
	    return getText(elm).contains(txt);
	}
	
	public String getText(By elm) {
		return fWait().until(ExpectedConditions.visibilityOfElementLocated(elm)).getText();
	}
	
}


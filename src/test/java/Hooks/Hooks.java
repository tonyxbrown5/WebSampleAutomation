package Hooks;


import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import com.aventstack.extentreports.Status;
import Functions.SeleniumBase;
import Util.StepDetails;
import io.cucumber.java.After;
import io.cucumber.java.AfterAll;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.BeforeStep;
import io.cucumber.java.Scenario;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Hooks extends SeleniumBase {
	
	private String browser = System.getProperty("browser", "chrome");
	
	@BeforeAll
	public static void setup() {
		System.setProperty("cucumber.options", "--plugin html:target/webstaurant_report.html");
		extent.attachReporter(spark);
	}

	@Before
    public void beforeScenario(Scenario scenario){
		test = extent.createTest(scenario.getName());
		driver = getDriver(browser);
    }	
	
	@BeforeStep
	public void beforeStep(Scenario scenario) {
	   
	}
	
	
	@AfterStep
	public void afterStep(Scenario scenario) {
		if(scenario.isFailed()==false) {
			test.log(Status.PASS, StepDetails.stepName);
		}
		else if(scenario.isFailed()) {
			test.log(Status.FAIL, StepDetails.stepName);
		}
		else
			test.log(Status.SKIP, StepDetails.stepName);
	}
	
	@After
    public void afterScenario(Scenario scenario){
		if (scenario.isFailed()) {
			TakesScreenshot ts = (TakesScreenshot) driver;
			
			//Attach screenshot to Extent Spark report
			String screenshot = ts.getScreenshotAs(OutputType.BASE64);
			test.addScreenCaptureFromBase64String(screenshot, "Failed ScreenShot");
			
			//Attach screenshot to cucumber HTML report
			byte[] src = ts.getScreenshotAs(OutputType.BYTES);
			scenario.attach(src, "image/png", "failed screenshot");
		}
		
		driver.quit();
    }
	
	@AfterAll
	public static void teardown() {
		extent.flush();
	}
	
	public WebDriver getDriver(String browser) {
		WebDriver webdriver;
		
		if(browser.equalsIgnoreCase("Chrome")) {
			WebDriverManager.chromedriver().setup();
			webdriver = new ChromeDriver();
		}
		else {
			WebDriverManager.firefoxdriver().setup();
			webdriver = new FirefoxDriver();
		}
		
	    return webdriver;
	}
}

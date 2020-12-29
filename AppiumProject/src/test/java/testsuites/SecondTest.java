package testsuites;

import java.util.concurrent.TimeUnit;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import lib.CapabilityBase;

public class SecondTest extends CapabilityBase {
	AndroidDriver<AndroidElement> driver;
	
	
	@BeforeTest
	public void setUp() throws Exception {
		
		
		Runtime.getRuntime().exec("taskkill /F /IM node.exe");
	
		service = startserver();
		Thread.sleep(9000);	
		driver = driverSetUp(appPackage, appActivity, deviceName, ChromeDriverPath, platformName);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
	}
	
	@Test
	public  void TestOne() {
		
	}
	
	
	@AfterTest
	public void tearDown() {
		service.stop();
		
	}

}

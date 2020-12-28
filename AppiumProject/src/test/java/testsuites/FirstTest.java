package testsuites;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import lib.CapabilityBase;

public class FirstTest extends CapabilityBase {
	
	AndroidDriver<AndroidElement> driver;
	
	
	@BeforeTest
	public void setUp() throws Exception {
		
		
		Runtime.getRuntime().exec("taskkill /F /IM node.exe");
	
		service = startserver();
		Thread.sleep(5000);	
		driver = driverSetUp(appPackage, appActivity, deviceName, ChromeDriver, platformName);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
	}
	
	
	@Test
	public void oneTest() throws Exception {
		
		driver.findElement(By.xpath("//*[@text=\"Female\"]")).click();
		driver.findElement(By.id("com.androidsample.generalstore:id/nameField")).sendKeys("Hello");
		driver.findElement(By.id("android:id/text1")).click();		
		driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Afghanistan\"))").click();
		String actualValue = driver.findElement(By.xpath("//*[@text=\"Afghanistan\"]")).getText();
		String expectedValue = "Afghanistan";
		Assert.assertEquals(actualValue, expectedValue);
		
		driver.findElement(By.id("com.androidsample.generalstore:id/btnLetsShop")).click();	
		
		driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().resourceId(\"com.androidsample.generalstore:id/rvProductList\")).getChildByText(new UiSelector().className(\"android.widget.RelativeLayout\"), \"Jordan Lift Off\").getChildByText(new UiSelector().className(\"android.widget.TextView\"), \"ADD TO CART\")").click();;
		
		
	}
	
	@AfterTest
	public void tearDown() {
		service.stop();
		
	}
	
	
	

}

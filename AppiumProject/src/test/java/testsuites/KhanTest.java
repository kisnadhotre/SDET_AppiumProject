package testsuites;

import java.util.concurrent.TimeUnit;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import lib.CapabilityBase;

public class KhanTest extends CapabilityBase {

	AndroidDriver<AndroidElement> driver;
	
	
	@BeforeTest
	public void setUp() throws Exception {
		
		
		Runtime.getRuntime().exec("taskkill /F /IM node.exe");
	
		service = startserver();
		Thread.sleep(9000);	
		driver = driverSetUp(appPackage, appActivity, deviceName, ChromeDriver, platformName);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
	}
	
	
	@Test(enabled = false)
	public  void signInTest() {
		
		driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().resourceId(\"org.khanacademy.android:id/content_root\")).getChildByText(new UiSelector().className(\"android.widget.TextView\"),\"Sign in\")").click();
		
	}


	@Test(enabled = true)
	public  void selectLanguageTest() {
		driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().resourceId(\"org.khanacademy.android:id/content_root\")).getChildByText(new UiSelector().className(\"android.widget.TextView\"),\"Select language\")").click();
		driver.findElementByAndroidUIAutomator("new UiSelector().className(\"android.widget.TextView\").text(\"English\")").click();
		
		driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().resourceId(\"org.khanacademy.android:id/language_options\")).getChildByText(new UiSelector().className(\"android.widget.TextView\"),\"English\")").click();
		
	AndroidElement radioEng = driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().resourceId(\"org.khanacademy.android:id/language_options\")).getChildByText(new UiSelector().className(\"android.widget.TextView\"),\"English\").instance(1)");
		System.out.println(radioEng);
		
	}
	
	@Test(enabled = false)
	public  void selectSoundEffectTest() {
		driver.findElementByXPath("//android.widget.ImageView[@content-desc=\"Settings\"]").click();
	}
	
	
	
	
	
	
	
	@AfterTest
	public void tearDown() {
		service.stop();
		
	}
	
	
}

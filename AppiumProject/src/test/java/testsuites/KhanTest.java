package testsuites;

import static org.testng.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import lib.CapabilityBase;

public class KhanTest extends CapabilityBase {

	AndroidDriver<AndroidElement> driver;
	String signupTitle;
	
	@BeforeTest
	public void setUp() throws Exception {
		
		Runtime.getRuntime().exec("taskkill /F /IM node.exe");
	
		service = startserver();
		Thread.sleep(9000);	
		driver = driverSetUp(appPackage, appActivity, deviceName, ChromeDriverPath, platformName);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
	}
	
	@Test(alwaysRun = true, priority = 1)
	public void dismissAlert() throws Exception {

		Thread.sleep(9000);
		try {
			 driver.findElementByAndroidUIAutomator("new UiSelector().className(\"android.widget.TextView\").text(\"Dismiss\")").click();
						
		} catch(Exception e) {
			System.out.println("Dismiss Alert : " + e);
		}
		   
		
	}
	
	
	@Test(enabled = true, priority = 3)
	public  void signInTest() {
		
		driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().resourceId(\"org.khanacademy.android:id/content_root\")).getChildByText(new UiSelector().className(\"android.widget.TextView\"),\"Sign in\")").click();
		
		driver.findElement(By.xpath("//android.widget.Button[@content-desc=\"Sign up with email\"]")).click();
				
		String actualSignupTitle = driver.findElementByAndroidUIAutomator("new UiSelector().className(\"android.widget.TextView\").text(\"Sign up\")").getText().toString();
		String expectedSignupTitle = "Sign up";
		assertEquals(actualSignupTitle, expectedSignupTitle);
		
		driver.findElement(By.xpath("//android.widget.EditText[@content-desc=\"First name\"]")).sendKeys("Test");
		driver.findElement(By.xpath("//android.widget.EditText[@content-desc=\"Last name\"]")).sendKeys("Test");
		driver.findElement(By.xpath("//android.view.ViewGroup[@content-desc=\"Birthday\"]")).click();
		
		driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().className(\"android.widget.NumberPicker\").index(0)).scrollIntoView(new UiSelector().className(\"android.widget.EditText\").textContains(\"Feb\"))");
		driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().className(\"android.widget.NumberPicker\").index(1)).scrollIntoView(new UiSelector().className(\"android.widget.EditText\").textContains(\"03\"))");
		driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().className(\"android.widget.NumberPicker\").index(2)).scrollIntoView(new UiSelector().className(\"android.widget.EditText\").textContains(\"1992\"))");
		
//		driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().resourceId(\"android:id/datePicker\")).className(\"android.widget.NumberPicker\").className("").instance(0).scrollIntoView(text(\"Jan\")");
//		driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().resourceId(\"android:id/datePicker\")).className(\"android.widget.NumberPicker\").instance(1).scrollIntoView(text(\"21\")");
//		driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().resourceId(\"android:id/datePicker\")).className(\"android.widget.NumberPicker\").instance(2).scrollIntoView(text(\"1990\")");

		driver.findElementByAccessibilityId("android:id/button1").click();
		
		driver.findElement(By.xpath("//android.widget.EditText[@content-desc=\"Email address\"]")).sendKeys("abc@test.com");;
		driver.findElement(By.xpath("//android.widget.EditText[@content-desc=\"Password\"]")).sendKeys("HelloPassword");
		
		driver.findElementByAndroidUIAutomator("new UiSelector().resourceId(\"org.khanacademy.android:id/action_bar_root\").getChildByText(new UiSelector().className(\"android.widget.TextView\"),\"CREATE\")").click();
		
		
		
		
		
	}


	@Test(enabled = true, priority = 2)
	public  void selectLanguageTest() {
		driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().resourceId(\"org.khanacademy.android:id/content_root\")).getChildByText(new UiSelector().className(\"android.widget.TextView\"),\"Select language\")").click();
		driver.findElementByAndroidUIAutomator("new UiSelector().className(\"android.widget.TextView\").text(\"English\")").click();
		
		driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().resourceId(\"org.khanacademy.android:id/language_options\")).getChildByText(new UiSelector().className(\"android.widget.TextView\"),\"English\")").click();
		
		//	AndroidElement radioEng = driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().resourceId(\"org.khanacademy.android:id/language_options\")).getChildByText(new UiSelector().className(\"android.widget.TextView\"),\"English\").instance(1)");
		//	System.out.println(radioEng);	
		try {
			Thread.sleep(4000);
		} catch(Exception e) {
			System.out.println(" : " + e);
		}
		
		driver.findElement(By.xpath("//android.widget.ImageButton[@content-desc=\"Navigate up\"]")).click();

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

package lib;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.URL;
import java.util.Properties;

import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.AutomationName;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public class CapabilityBase {
	
	protected String appPackage;
	protected String appActivity;
	protected String deviceName;
	protected String ChromeDriverPath;
	protected String platformName;
	
	public AppiumDriverLocalService service;
	
    public AppiumDriverLocalService startserver()
    {
        boolean flag = checkifserverisRunning(4723);
        if(!flag)
        {
        
            service = AppiumDriverLocalService.buildService(new AppiumServiceBuilder()
                    .usingDriverExecutable(new File("C:\\Program Files\\nodejs\\node.exe"))
                    .withAppiumJS(new File("C:\\Users\\KrishnaDhotre\\AppData\\Roaming\\npm\\node_modules\\appium\\build\\lib\\main.js"))
                    .withIPAddress("0.0.0.0").usingPort(4723));
            
            service.start();
        }
        return service;
    }
	
	public static boolean checkifserverisRunning(int port)
    {
        boolean isserverRunning = false;
        ServerSocket serversocket;
        try
        {
            serversocket = new ServerSocket(port);
            serversocket.close();
        }
        catch (Exception e) {
            isserverRunning= true;
        }
        finally {
            serversocket=null;
        }
        return isserverRunning;
    }
	
	
	public static void startEmulator() throws InterruptedException, IOException {
		Runtime.getRuntime().exec("C:\\Users\\KrishnaDhotre\\Documents\\appium_workspace\\emulator.bat");
		Thread.sleep(9000);
	}
	
	
	public static AndroidDriver<AndroidElement> driverSetUp(String appPackage, String appActivity, String deviceName, String ChromeDriver, String platformName) throws Exception {
		
		//String propPath = System.getProperty("user.dir") + "\\src\\main\\resources\\global.properties";
		//FileReader inStream = new FileReader(propPath);
		//Properties prop = new Properties();
		//prop.load(inStream);
			
		
		//appPackage = com.androidsample.generalstore
		//appActivity = com.androidsample.generalstore.SplashActivity
		//appPackage = "org.khanacademy.android";
		//appActivity = "org.khanacademy.android.ui.library.MainActivity";
		
		//appPackage = prop.getProperty(appPackage);
		//appActivity = prop.getProperty(appActivity);
		deviceName = "sdk_gphone_x86";
		//ChromeDriverPath = System.getProperty("user.dir") + "\\src\\main\\resources\\chromedriver.exe";		
		//platformName = prop.getProperty(platformName);
				
		if (deviceName.contains("sdk_gphone_x86")) {
			startEmulator();
		}
		
		Thread.sleep(10000);
		
		DesiredCapabilities capability = new DesiredCapabilities();
		capability.setCapability(MobileCapabilityType.DEVICE_NAME, "sdk_gphone_x86");
		capability.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
		capability.setCapability(MobileCapabilityType.AUTOMATION_NAME, AutomationName.ANDROID_UIAUTOMATOR2);

		capability.setCapability(AndroidMobileCapabilityType.CHROMEDRIVER_EXECUTABLE, System.getProperty("user.dir") + "\\src\\main\\resources\\chromedriver.exe");

		capability.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, "org.khanacademy.android");        
		capability.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, "org.khanacademy.android.ui.library.MainActivity");

		
		AndroidDriver<AndroidElement> driver = new AndroidDriver<AndroidElement>(new URL("http://0.0.0.0:4723/wd/hub"),capability);
		return driver;
		
	}
}

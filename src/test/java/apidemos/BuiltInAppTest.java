package apidemos;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class BuiltInAppTest {

    AppiumDriver driver;

    @BeforeTest
    public void setup() throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("automationName", "UiAutomator2");
        capabilities.setCapability("platformVersion", "10.0");
        capabilities.setCapability("deviceName", "Android Emulator");
        capabilities.setCapability("appPackage", "com.android.dialer");
        capabilities.setCapability("appActivity", ".app.DialtactsActivity");
        driver = new AndroidDriver(new URL("http://0.0.0.0:4723/wd/hub"), capabilities);
    }

    @Test
    public void dialANumberAndMoveOntoCreateNewContact() throws InterruptedException {
        //Tap on dialIcon
        WebElement dialIcon = driver.findElement(By.id("com.android.dialer:id/fab"));
        dialIcon.click();

        //Dial a Number
        WebElement dialpad = driver.findElement(By.id("com.android.dialer:id/dialpad"));

        for (int i=0; i<=5;i++){
            dialpad.click();
        }

        //click on add new contact
        List<WebElement> options = driver.findElements(By.xpath("//android.widget.FrameLayout/android.support.v7.widget.RecyclerView/android.widget.FrameLayout[1]"));
        options.get(0).click();

        //fill in form
        List<WebElement> formOptions = driver.findElements(By.className("android.widget.EditText"));
        formOptions.get(0).sendKeys("Diego");
        formOptions.get(1).sendKeys("Castano");

        driver.findElement(By.id("com.android.contacts:id/editor_menu_save_button")).click();

    }

    @AfterTest
    public void teardown(){
        if(null != driver){
            driver.quit();
        }
    }

}

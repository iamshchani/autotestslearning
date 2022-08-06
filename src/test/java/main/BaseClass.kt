package main

import io.appium.java_client.AppiumDriver
import io.appium.java_client.MobileBy
import io.appium.java_client.MobileElement
import io.appium.java_client.android.AndroidDriver
import io.appium.java_client.remote.AndroidMobileCapabilityType
import io.appium.java_client.remote.MobileCapabilityType
import org.openqa.selenium.remote.DesiredCapabilities
import org.testng.annotations.AfterSuite
import org.testng.annotations.BeforeSuite
import org.testng.annotations.Test
import java.net.URL
import java.util.concurrent.TimeUnit

class BaseClass {
    lateinit var driver: AppiumDriver<MobileElement>

    @BeforeSuite
    fun setupDriver() {
        val url = URL("http://127.0.0.1:4723/wd/hub")
        val capabilities = DesiredCapabilities() // Инициализируем желаемые настройки. Делаем инициализацию один раз, поэтому используем val
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android") //
        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "11.0")
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Galaxy Nexus API 30")
        capabilities.setCapability(MobileCapabilityType.NO_RESET, false) // false - МП будет каждый раз переустанавливаться, true - при каждом последующем запуске тестом МП не будет переустанавливаться
        capabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, "7200")
        capabilities.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, "ru.sportmaster.app.handh.dev")
        capabilities.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, "ru.sportmaster.app.presentation.start.StartActivity")
        capabilities.setCapability(MobileCapabilityType.APP, "/Users/ivanshchankin/Desktop/sportmaster-4.10.0.31177_dev_beta.apk")
        //capabilities.setCapability(MobileCapabilityType.UDID, "paramUDID")
        driver = AndroidDriver(url, capabilities)
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS) // Время, в течение которого будет осуществляться поиск элементов на экране
    }

    @AfterSuite
    fun end() {
        driver.quit()
    }

    @Test
    fun testOne() {
        TimeUnit.SECONDS.sleep(1)

        try {
            lateinit var crossButton: MobileElement
            crossButton = driver.findElement(MobileBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.widget.ImageButton"))
            crossButton.click()
            TimeUnit.SECONDS.sleep(5)
        } catch (e: org.openqa.selenium.NoSuchElementException) {
            println("Cross Button не найден")
        }

        lateinit var phoneNumberField: MobileElement
        phoneNumberField = driver.findElement(MobileBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup[2]/android.widget.LinearLayout[2]/android.widget.FrameLayout/android.widget.EditText"))
        phoneNumberField.click()
        phoneNumberField.sendKeys("9999999900")
        TimeUnit.SECONDS.sleep(5)
        lateinit var getSMSButton: MobileElement
        getSMSButton = driver.findElement(MobileBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.Button"))
        getSMSButton.click()
        TimeUnit.SECONDS.sleep(5)
        lateinit var smsCodeField: MobileElement
        smsCodeField = driver.findElement(MobileBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.ViewFlipper/android.widget.LinearLayout/android.widget.ViewFlipper/android.widget.LinearLayout/android.widget.EditText"))
        smsCodeField.click()
        smsCodeField.sendKeys("1111")
        TimeUnit.SECONDS.sleep(5)
        TimeUnit.SECONDS.sleep(5)
    }

}
/*
	This is the Geb configuration file.
	
	See: http://www.gebish.org/manual/current/#configuration
*/


import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.firefox.MarionetteDriver
import org.openqa.selenium.phantomjs.PhantomJSDriver

waiting {
    timeout = 10
    retryInterval = 0.2
}

environments {

    // run via “./gradlew chromeTest”
    // See: http://code.google.com/p/selenium/wiki/ChromeDriver
    chrome {
        driver = {
            def driver = new ChromeDriver()
            driver.manage().window().maximize()
            return driver
        }
    }

    // run via “./gradlew firefoxTest”
    // See: http://code.google.com/p/selenium/wiki/FirefoxDriver
    firefox {
        System.setProperty("webdriver.gecko.driver", "src/test/resources/geckodriver.exe")
        driver = {
            def driver = new MarionetteDriver()
            driver.manage().window().maximize()
            return driver
        }
    }

    phantomJs {
        driver = {
            def driver = new PhantomJSDriver()
            driver.manage().window().maximize()
            return driver
        }
    }

}

// To run the tests with all browsers just run “./gradlew test”

baseUrl = "https://examplanner.pgs-soft.com/gftpl/"
autoClearCookies = false

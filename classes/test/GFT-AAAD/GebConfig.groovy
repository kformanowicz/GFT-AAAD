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
		driver = { new ChromeDriver() }
	}
	
	// run via “./gradlew firefoxTest”
	// See: http://code.google.com/p/selenium/wiki/FirefoxDriver
	firefox {
		System.setProperty("webdriver.gecko.driver", "src/test/resources/geckodriver.exe")
		driver = { new MarionetteDriver() }
	}

    phantomJs {
        driver = { new PhantomJSDriver() }
    }

}

// To run the tests with all browsers just run “./gradlew test”

baseUrl = "https://examplanner.pgs-soft.com/gftpl/"
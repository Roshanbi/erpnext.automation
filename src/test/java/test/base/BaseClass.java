package test.base;

import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.time.Duration;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.Platform;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BaseClass {

	protected Properties properties;
	protected Logger logger;

	@BeforeClass
	@Parameters({ "os", "browser" })
	public void setUp(String os, String br) throws IOException {

		// logging
		logger = LogManager.getLogger(this.getClass());
		logger.info("===== Test Setup Started =====");
		logger.info("Operating System: " + os);
		logger.info("Browser: " + br);

		// Loading properties file
		FileReader file = new FileReader("./src/test/resources/config.properties");
		properties = new Properties();
		properties.load(file);

		logger.info("Execution environment: " + properties.getProperty("execution_env"));

		// local execution setup
		if (properties.getProperty("execution_env").equalsIgnoreCase("remote")) {

			MutableCapabilities options = null;// can hold any browser's options

			// set br
			switch (br.toLowerCase()) {
			case "chrome":
				logger.info("Launching chrome browser remotely");
				ChromeOptions chromeOptions = new ChromeOptions();
				chromeOptions.setAcceptInsecureCerts(true);
				options = chromeOptions;
				break;

			case "edge":
				logger.info("Launching edge browser remotely");
				EdgeOptions edgeOptions = new EdgeOptions();
				edgeOptions.setAcceptInsecureCerts(true);
				options = edgeOptions;
				break;

			case "firefox":
				logger.info("Launching firefox browser");
				FirefoxOptions firefoxOptions = new FirefoxOptions();
				firefoxOptions.setAcceptInsecureCerts(true);
				options = firefoxOptions;
				break;
			default:
				throw new IllegalArgumentException("Unsupported browser:" + br);
			}

			// set platformName (W3C standard)
			switch (os.toLowerCase()) {
			case "windows":
				logger.info("Launching windows os");

				options.setCapability("platformName", "Windows");
				break;
			case "linux":
				logger.info("Launching linux os");
				options.setCapability("platformName", "Linux");
				break;
			case "mac":
				logger.info("Launching mac os");
				options.setCapability("platformName", "macOS");// macOS w3c capability name
				break;
			default:
				logger.error("Unsupported OS:" + os);
				throw new IllegalArgumentException("Unsupported os:" + os);

			}

			// start remote browser
			String gridurl = properties.getProperty("gridURL");
			DriverFactory.setDriver(new RemoteWebDriver(new URL(gridurl), options));

		} else {
			switch (br.toLowerCase()) {
			case "chrome":
				logger.info("Launching Chrome browser");

				DriverFactory.setDriver(new ChromeDriver());
				break;
			case "edge":
				logger.info("Launching Edge browser");
				DriverFactory.setDriver(new EdgeDriver());
				break;
			case "firefox":
				logger.info("Launching Firefox browser");
				DriverFactory.setDriver(new FirefoxDriver());
				break;
			default:
				logger.error("Unsupported browser: " + br);
				throw new IllegalArgumentException("unsupportedbrowser" + br);
			}
		}

		DriverFactory.getDriver().manage().deleteAllCookies();
		DriverFactory.getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		logger.info("Navigating to: " + properties.getProperty("baseURL"));
		DriverFactory.getDriver().get(properties.getProperty("baseURL"));
		DriverFactory.getDriver().manage().window().maximize();
		logger.info("===== Test Setup Completed =====");
	}

	@AfterClass
	public void tearDown() {
		logger.info("===== Test Teardown Started =====");
		DriverFactory.getDriver().quit();
		DriverFactory.removeDriver();// if you don't remove driver, stack will be full
		logger.info("Driver quit and resources cleaned up");
		logger.info("===== Test Teardown Completed =====");
	}

}

package test.base;

import org.openqa.selenium.WebDriver;

public class DriverFactory {
	private static ThreadLocal<WebDriver> threadLocal = new ThreadLocal<>();
	
	public static void setDriver(WebDriver driver) {
		threadLocal.set(driver);
	}
	
	public static WebDriver getDriver() {
		return threadLocal.get();
	}
	
	public static void removeDriver() {
		threadLocal.remove();
	}

}

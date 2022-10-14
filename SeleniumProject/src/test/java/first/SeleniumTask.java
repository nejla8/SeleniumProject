package first;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class SeleniumTask {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\snejla\\Downloads\\chromedriver_win32 (1)\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        driver.get("https://www.saucedemo.com/");

        driver.quit();
    }
}

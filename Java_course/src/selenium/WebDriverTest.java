package selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

/**
 *
 * @author coming  2016-11-13
 */
public class WebDriverTest {

	/**
	 * @param args
	 * @return void
	 */
	public static void main(String[] args) {
		//�����������û��Ĭ�ϰ�װ��C�̣���Ҫ�ƶ���·��         
		System.setProperty("webdriver.ie.driver", "C:/Program Files/Internet Explorer/iexplore.exe");          
		WebDriver driver = new InternetExplorerDriver();
		driver.get("https://www.baidu.com/");
		driver.manage().window().maximize();
		WebElement txtbox = driver.findElement(By.name("wd"));
		txtbox.sendKeys("Glen");
		WebElement btn = driver.findElement(By.id("su"));
		btn.click();
		driver.close();

	}

}

package pre_wiz;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.DeathByCaptcha.Captcha;
import com.DeathByCaptcha.SocketClient;
public class Death_Captcha {
	
	int Exported_file_name;
	WebElement capthca_Img;
	WebElement captcha_field;
	public String death_Captcha(WebDriver driver, WebDriverWait wait)
	{
		try
		{
	
			capthca_Img=wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//img[@alt='Captcha']")));
			//captcha_field=wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@id='Captcha']")));
			
			/*
			Actions action=new Actions(driver);
			
			action.contextClick(capthca_Img).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ARROW_DOWN);
			
			Robot robot = new Robot();
			robot.keyPress(KeyEvent.VK_ENTER);
			 
			*/
		// Get entire page screenshot
			File screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			BufferedImage  fullImg = ImageIO.read(screenshot);

		// Get the location of element on the page
			
			JavascriptExecutor jso = (JavascriptExecutor)driver;
			jso.executeScript("scroll(700, 0)");
			
			
			Point point = capthca_Img.getLocation();

		// Get width and height of the element
			int eleWidth = capthca_Img.getSize().getWidth();
			int eleHeight = capthca_Img.getSize().getHeight();

		// Crop the entire page screenshot to get only element screenshot
			BufferedImage eleScreenshot= fullImg.getSubimage(point.getX(), point.getY(),
			 eleWidth,eleHeight);
			ImageIO.write(eleScreenshot, "png", screenshot);

		// Copy the element screenshot to disk
			File screenshotLocation = new File("Captcha_Img.png");
			FileUtils.copyFile(screenshot, screenshotLocation);
			System.out.println("Image Saved Successfully....");
			Thread.sleep(800);
		
		
			SocketClient client = new SocketClient("msedkyTh", "Testing_Thiqah@897");
			Captcha res = client.decode("Captcha_Img.png",10);
			
			System.out.println("Captcha Code is : "+res.text);
			//captcha_field.sendKeys(res.text);
			return res.text;
	
			
	     
	     } catch (Exception e) {
	        e.printStackTrace();
	        return null;
	     } 
	}

}

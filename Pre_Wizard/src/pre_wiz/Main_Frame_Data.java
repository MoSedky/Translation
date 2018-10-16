package pre_wiz;

import java.awt.AWTException;
import java.net.MalformedURLException;
import java.sql.SQLException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Main_Frame_Data {
	
	WebElement username;
	WebElement passwrd;
	WebElement login_btn;
	WebElement Inquiry_srvs_section;
	WebElement Inquiry_by_CR_section;
	WebElement Inquiry_by_Name_section;
	WebElement Est_Name_srch;
	WebElement CR_Number_srch;
	WebElement srch_btn;
	Select results_number;
	List<WebElement> CR_num_found;
	List<WebElement> CR_status_found;
	List<WebElement> CR_type_found;
	String [] selected_CRNum;
	String [] type_CRs;
	
	
	public GET_ECR_Data getECRData(WebDriver driver, WebDriverWait wait, String Cr_Status) throws InterruptedException
	{
		
		driver.navigate().to("http://10.10.52.106/MCIBackend/Home/Index");
		username=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='UserName']")));
		passwrd=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='Password']")));
		login_btn=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='الدخول']")));
		
		username.sendKeys("MoSedky");
		passwrd.sendKeys("Sedky24303252");
		
		Thread.sleep(1500);
		login_btn.click();
		
		Inquiry_srvs_section=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'خدمات الإستعلام')]")));
		Inquiry_srvs_section.click();
		Thread.sleep(2000);
		
		Inquiry_by_CR_section=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'الإستعلام ببيانات السجل')]")));
		Inquiry_by_CR_section.click();
		Thread.sleep(1000);
		
		Inquiry_by_Name_section=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'الإستعلام ببيانات الشخص')]")));//not now...
		
		Est_Name_srch=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='CrName']")));
		
		CR_Number_srch=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='CrNumber']")));//not now...
		
		srch_btn=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@class='fa fa-search']")));
		
		results_number=new Select(wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[text()='عدد النتائج']/../select"))));
		
		Est_Name_srch.sendKeys("مؤسسة");
		
		results_number.selectByIndex(4);
		
		Thread.sleep(1000);
		
		srch_btn.click();
		
		CR_num_found=wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//tbody//a")));
		
		CR_status_found=wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//tbody//a/../../td[6]")));
		
		CR_type_found=wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//tbody//a/../../td[5]")));
		
		
		for(int iterator=0;iterator<CR_num_found.size();iterator++)
		{
			if(CR_status_found.get(iterator).getText()=="نشط") //Cr_Status
			{
				selected_CRNum[iterator]=CR_num_found.get(iterator).getText();
				type_CRs[iterator]=CR_type_found.get(iterator).getText();
			}
		}
		
		
		return new GET_ECR_Data(selected_CRNum,type_CRs);
	}

}

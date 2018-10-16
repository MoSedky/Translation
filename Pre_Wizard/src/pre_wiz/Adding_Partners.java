package pre_wiz;

import java.sql.SQLException;
import java.util.Arrays;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Adding_Partners {
	
	GET_ECR_Data multi_arrs;
	Data_Layer_PreWiz data_db=new Data_Layer_PreWiz();
	WebElement add_partnr;
	Death_Captcha death=new Death_Captcha();
	
	public void Add_Partners(int cmp_type_tawsyia, WebDriver driver,WebDriverWait wait) throws InterruptedException, ClassNotFoundException, SQLException
	{
		multi_arrs=data_db.Get_Saudi_Data();
		
		String[] retrived_IDs=Arrays.copyOf(multi_arrs.a,multi_arrs.a.length);
		String[] retrived_BDatess=Arrays.copyOf(multi_arrs.b,multi_arrs.b.length);
		
		System.out.println("length="+multi_arrs.a.length);
		
		for(int i=0;i<retrived_IDs.length;i++)
		{
			System.out.println("ID of :"+retrived_IDs[i]+"Birthday is:"+retrived_BDatess[i]);
		}
		
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("scroll(0, 7500)");
		
		WebElement continue_contract=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='إستكمال تأسيس العقد ']")));
		continue_contract.click();
		
		
		
		
	//	Thread.sleep(1000);
		
		for(int partners_iterator=0;partners_iterator<retrived_IDs.length;partners_iterator++)
		{
			
			Thread.sleep(2500);
			JavascriptExecutor jso = (JavascriptExecutor)driver;
			jso.executeScript("scroll(0, 700)");
			
			Thread.sleep(3000);
			add_partnr=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='أضف شريك جديد']")));
			
			add_partnr.click();
		
		Select partnr_type=new Select (wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'نوع الشريك')]/../select"))));
		partnr_type.selectByIndex(1); // Saudi
		
		WebElement ID_field=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'رقم الهوية')]/../input")));
		ID_field.sendKeys(retrived_IDs[partners_iterator]);
		
		WebElement DB_field=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'تاريخ الميلاد')]/../input")));
		DB_field.sendKeys(retrived_BDatess[partners_iterator].substring(8, 10)+"/"+retrived_BDatess[partners_iterator].substring(5, 7)+"/"+retrived_BDatess[partners_iterator].substring(0, 4));
		
		Thread.sleep(1500);
		
		DB_field.sendKeys(Keys.ESCAPE);
		
		
		//Captcha Part
		if(partners_iterator>=3)
		{
			String cap_text=death.death_Captcha(driver, wait);
			WebElement Capt_field=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='Captch']")));
			Capt_field.sendKeys(cap_text);
		}
		Thread.sleep(1500);
		WebElement verify_partnr_data=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(text(),'تحقق')]")));
		verify_partnr_data.click();
		
		if(cmp_type_tawsyia==2)
		{
			WebElement type_mwasy=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='LPCPersonType_0']")));
			type_mwasy.click();
			
			if(partners_iterator==5)
			{
				WebElement type_motadmen=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='LPCPersonType_1']")));
				type_motadmen.click();
			}
		}
		
		Select city_residence_Saudi=new Select (wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'مدينة الإقامة')]/../select"))));
		city_residence_Saudi.selectByIndex(3);
		
		WebElement Mobile_Saudi=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'الجوال')]/../input")));
		Thread.sleep(500);
		Mobile_Saudi.clear();
		Thread.sleep(500);
		Mobile_Saudi.sendKeys("00966512345678");
		
		WebElement Email_Saudi=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'البريد')]/../input")));
		Thread.sleep(500);
		Email_Saudi.clear();
		Thread.sleep(500);
		Email_Saudi.sendKeys("automationtest@thiqah.sa");
		
		WebElement profession_Saudi=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'المهنة')]/../input")));
		Thread.sleep(500);
		profession_Saudi.clear();
		Thread.sleep(500);
		profession_Saudi.sendKeys("محلل نظم");
		
		
		Thread.sleep(1000);
		WebElement add_partnr_btn=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'إغلاق')]/../*[contains(text(),'إضافة')]")));
		add_partnr_btn.click();
		
		}
	}
	

}

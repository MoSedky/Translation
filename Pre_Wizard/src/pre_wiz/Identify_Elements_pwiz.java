package pre_wiz;
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.net.MalformedURLException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.OffsetDateTime;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import javax.swing.Icon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.UIManager;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class Identify_Elements_pwiz {
	
	static WebDriver driver;
	WebDriverWait wait;
	String[] activities_to_print=new String[100];
	
	String[] displayed_main_activities;
	String[] displayed_third_activities;
	
	String password;
	Integer selected_Login_Way;
	Integer selected_Sheet_Template;
	Random rand = new Random();
	int Capital_amount=0;
	String City;
	int Company_selected_type;
	int selected_years;
	Fees_Calculations fc=new Fees_Calculations();
	
	List<WebElement> third_LV_Activities;
	List<WebElement> Input_Checkboxes;
	WebElement select_Company_button;
	
	WebElement main_CR_Fees_Element;
	WebElement coc_Fees_Element;
	WebElement publish_Fees_Element;
	WebElement total_Fees_Element;
	WebElement vat_Fees_Element;
	WebElement total_plus_vat_Element;
	WebElement add_partnr;
	String returned_username_index;
	
	double main_CR_Fees;
	double coc_Fees;
	double publish_Fees;
	double total_Fees;
	double vat_Fees;
	double total_plus_vat;
	WebElement Company_Type;
	int  random_index_radio;
	int  random_username_index;
	int  auto_picked_city;
	int  auto_picked_company;
	int  auto_picked_activity;
	int  auto_picked_year;
	int  auto_select_category;
	Actions actions;
	Robot robot;
	Export_Activities export=new Export_Activities();
	String Cmp_Type;
	String cpt_amount;
	Boolean Acts_needed;
	Screenshot_of_results shot=new Screenshot_of_results();
	WebElement Add_ECR;
	WebElement ECR_num_Field;
	WebElement Verify_ECR;
	WebElement Esta_Name;
	WebElement Esta_Expirydate;
	WebElement Esta_Capital;
	WebElement Esta_successor_chkbx;
	WebElement Esta_successor_txt;
	WebElement Add_Esta;
	String []Establishment_data=new String[8];
	String []get_Establishment_data=new String[8];
	List<WebElement> KeepCR;
	List<WebElement> KeepCrName;
	List<WebElement> MarkCrMain;
	int ECR_critieria_KeepCR;
	int ECR_critieria_IsMain;
	int ECR_critieria_KeepName;
	Data_Layer_PreWiz data_db=new Data_Layer_PreWiz();
	GET_ECR_Data multi_arrs;
	
	int[] KeepCRs_chked;
	int[] IsMain_chked;
	int[] KeepName_chked;
	Death_Captcha death=new Death_Captcha();
	HelpMe help=new HelpMe();
	String[] help_me_values=new String[100];
	Adding_Partners add_p=new Adding_Partners();
	
	
	
	
	double[]results=new double[6];
	String[]radio_buttons= {"premium2211","premium211"};
	
	String[] usernames= {"1104784556"};//,"2285625097","Sagiauser123","khaliji_01"};
	String[] passwords= {"123456"};//,"123456","123456","Asd123456"};
	
	@BeforeClass
	public void setUp() throws MalformedURLException, AWTException, ClassNotFoundException, SQLException{
		//String webdriver_url=Reader.extract("chromedriver_Latest.exe"); //Extract ChromeDriver in User Temp folder
		
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\mmostafa\\eclipse-workspace\\Selenium\\src\\test\\chromedriver_Latest.exe");
        driver = new ChromeDriver();              						//Initiate instance of create ChromeDriver
        driver.get("http://10.10.52.92:4545/Unified/Welcome");			//Open TimeSheet URL
		
        wait=new WebDriverWait(driver, 30);
         
        actions = new Actions(driver); 
		
	}
	
	
	
	
	
	@Test
	public void testCal() throws Exception {
		
	
		
	for(int repeat_time=0;repeat_time<usernames.length;repeat_time++)
	{
		
		returned_username_index=login_temp(usernames[repeat_time],passwords[repeat_time]);
		
	//	help_me_values=help.helpme(driver,  wait);
		
	//	export.print_results_HelpMe(help_me_values,0);
		
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("scroll(0, 500)");
		
		random_index_radio = rand.nextInt(radio_buttons.length);
		
		Capital_amount=selectCapital(radio_buttons[random_index_radio]);//premium211
		
		City=selectCity();
		
		
		jse.executeScript("scroll(0, 500)");
	
		if(repeat_time==2)
		{
			Company_selected_type=selectCompany(true);
			selectActivities(); //no Activities Need 
			
			//select_Company_button.click();
		}
		else
		{
			Company_selected_type=selectCompany(false);
			activities_to_print=selectActivities();
			//select_Company_button.click();
		}
		
		/*
		if(Company_selected_type==4)
		{
			
		}
			driver.close();
		*/
		//selectCmp_Category();
		//activities_to_print=selectActivities();
		jse.executeScript("scroll(0, 500)");
		selected_years=selectYears();
		
		
		String []result_verified=verify_results();
		int mfees=fc.calculate_main_fees(Company_selected_type, selected_years);
		int pfees=fc.calculate_publish_fees(Company_selected_type);
		//int ofees=fc.calculate_operation_fees(Company_selected_type);
		double vfees=fc.calculate_VAT(Double.parseDouble(result_verified[1]), Company_selected_type);
		double total_wout_fees=mfees+pfees+(Double.parseDouble(result_verified[1]));
		double total_w_fees=mfees+pfees+(Double.parseDouble(result_verified[1]))+vfees;
		
		System.out.println("The Main Fees is: "+mfees+'\n');
		System.out.println("The COC Fess is: "+result_verified[1]+'\n');
		System.out.println("The Publish Fees is: "+pfees+'\n');
		//System.out.println("The Operation Fees is: "+ofees+'\n');
		System.out.println("The Total value without Fees is :"+total_wout_fees);
		System.out.println("The VAT Fees is :"+vfees);
		System.out.println("The Total value plus Fees is :"+(total_wout_fees+vfees));
		
		/*
		 * 	results[0]=main_CR_Fees;
		results[1]=coc_Fees;
		results[2]=publish_Fees;
		results[3]=total_Fees;
		results[4]=vat_Fees;
		results[5]=total_plus_vat;
		 * 
		 */
		
		String[] results_Str=new String[result_verified.length];//Actual Results
		
		String[] results_ex_Str=new String[6];//Expected Results
		
		results_ex_Str[0]=("Main CR Fees is: "+String.valueOf(mfees));
		results_ex_Str[1]=("COC Fees is: "+String.valueOf(result_verified[1]));
		results_ex_Str[2]=("Publish Fees is: "+String.valueOf(pfees));
		results_ex_Str[3]=("Total without Fees is: "+String.valueOf(total_wout_fees));
		results_ex_Str[4]=("VAT Fees is: "+String.valueOf(vfees));
		results_ex_Str[5]=("Total Fees including VAT is: "+String.valueOf(total_w_fees));
		
		for (int i=0;i<result_verified.length;i++)
		{
			results_Str[i] = String.valueOf(result_verified[i]);
			System.out.println("Values displayed of "+i +'\t'+"is"+result_verified[i]);
		}
		
		if(repeat_time!=2||activities_to_print.length!=0||!activities_to_print.equals(null))
		{
			for(int it=0;it<activities_to_print.length;it++)
			{
				System.out.println("Activity number "+it+"value is "+activities_to_print[it]+'\n');
				
			
			}
		}
		
		
		
		if(Company_selected_type==1)
		{
			Cmp_Type="شركة ذات مسؤولية محدودة";
		}
		else if(Company_selected_type==2)
		{
			Cmp_Type="شركة توصية بسيطة";
		}
		else if(Company_selected_type==3)
		{
			Cmp_Type="شركة تضامنية";
		}
		else if(Company_selected_type==2)
		{
			Cmp_Type="شركة مهنية";
		}
		
		if(Capital_amount==1)
		{
			cpt_amount="Less Than 5 M";
		}
		
		else if (Capital_amount==6)
		{
			cpt_amount="More Than 5 M";
		}
		
		shot.takeSnapShot(driver, "C:\\Users\\mmostafa\\eclipse-workspace\\Pre_Wizard\\Actual_results");
		
		export.print_results_PreWizard(activities_to_print, results_Str,results_ex_Str,repeat_time, Cmp_Type,returned_username_index, cpt_amount);
		
		Arrays.fill(activities_to_print,null);
		
		Thread.sleep(5000);
		
	//	WebElement loggedin=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@aria-haspopup='true']/span")));
	//	loggedin.click();
		
	//	WebElement logout=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li/a[@href='/Account/LogOut']")));
//		if(repeat_time<=2)
//		{
//			logout.click();
//		}
//		else
//		{
			//Code for Add Partners
		add_p.Add_Partners(Company_selected_type, driver, wait);
//		}
		
		System.out.println("---------End of a scenario --------------");
		
	}
		
	}
	
	public String login_temp(String uname,String pass) throws InterruptedException
	{
		driver.manage().window().maximize();
		System.out.println("following is URL:"+driver.getCurrentUrl());
		
		driver.navigate().to("http://10.10.52.92:4545/Unified/Welcome");
		
		if(driver.getCurrentUrl().contains("client_id"))
		{
			driver.navigate().to("http://10.10.52.92:4545/Unified/Welcome");
		}
		
		Thread.sleep(500);
		
		WebElement username=wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@id='IdentityNumber']")));
		WebElement password=wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@id='Password']")));
		
		//random_username_index = rand.nextInt(usernames.length-1);
		
		System.out.println("selected credentials id is "+random_username_index);
		
		username.sendKeys(uname);
		
		password.sendKeys(pass);
		
		Thread.sleep(5000);
		WebElement login_button=wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[text()='دخـول']")));
		login_button.click();
		
		WebElement create_CC=wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[@href='/PreWizard']")));
		create_CC.click();
		
		System.out.println("username index is :"+random_username_index+"And "+usernames[random_username_index]);
		
		return uname;
		
	}
	
	public int selectCapital(String Radio_b_ID)
	{
		WebElement capital=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='"+Radio_b_ID+"']/..")));
		capital.click();
		if(Radio_b_ID.equals("premium2211"))
		{
		
			return 6;
		}
		else
			return 1;
	}
	
	
	
	public String selectCity()
	{
		
		Select City_DDL=new Select(wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//select[preceding::*[text()='أين يقع المقر الرئيسي للشركة']]"))));
		int number_of_cities=City_DDL.getOptions().size();
		
		

		auto_picked_city = rand.nextInt(number_of_cities-1);
		City_DDL.selectByIndex(auto_picked_city);
		
		return City_DDL.getFirstSelectedOption().getText();
	}
	//*[contains(@id,'City')]/../
	public int selectCompany(Boolean sagia_selected) throws Exception
	{
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		auto_picked_company = rand.nextInt(3) + 1;
		System.out.println(auto_picked_company);
		
		
		if(sagia_selected)
		{
			
			jse.executeScript("scroll(250, 0)");
			Thread.sleep(1500);
			Company_Type=wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='card card1 wow flipInY']/div/span")));
			Company_Type.click();
			
			Acts_needed=selectCmp_Category(auto_picked_company,sagia_selected);
			
			Thread.sleep(2000);
			select_Company_button=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@id='crad1-choose']")));
			select_Company_button.click();
			
			return 1;
			
		}
		else if(!sagia_selected)
		{
			
			jse.executeScript("scroll(250, 0)");
			Thread.sleep(1500);
			Company_Type=wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='card card"+auto_picked_company+" wow flipInY']/div/span")));
			Company_Type.click();
			
			if(auto_picked_company==4)
			{
				List<WebElement> no_results=wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//ul[@class='select2-results__options']/*")));
				//export.print_Acts(no_results);
				System.out.println("No Results ...");
			//	driver.close();
			}
			
			Thread.sleep(500);
			
			//select_Company_button.click();
		}
		
		Acts_needed=selectCmp_Category(auto_picked_company,sagia_selected);
		select_Company_button=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='crad"+auto_picked_company+"-choose']")));
		Thread.sleep(4000);
		select_Company_button.click();
		Thread.sleep(3000);
		
		/*
		try
		{
			actions.moveToElement(select_Company_button);
			actions.perform();
			
			select_Company_button.click();
		}catch (Exception c)
		{
			actions.moveToElement(select_Company_button);
			actions.perform();
			
			
		}
		*/
		shot.takeSnapShot(driver, "C:\\Users\\mmostafa\\eclipse-workspace\\Pre_Wizard\\Selected_Company");
		

		
		
		return auto_picked_company;// 1 if Ma7doda, 2 if Tawsyia Baseeta, 3 if Tadmonyia 
	}
	
	public Boolean selectCmp_Category(int selectedcmptype,Boolean user_sagia) throws InterruptedException
	{
		System.out.println("Selected Company type is :"+selectedcmptype);
		
		
		if(user_sagia)
		{
			Thread.sleep(3000);
			Input_Checkboxes=wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//ul[preceding::*]//input[@type='checkbox']/..//label")));
			
			 
			 Thread.sleep(3000);
			 
			 actions.moveToElement(Input_Checkboxes.get(1)).click().build().perform();
			 
			 add_Foreign_CR(true);
			 
			 return false; // No Activity Needed
			
		}
		
		if(selectedcmptype==1)
		{
			
			
			Thread.sleep(3000);
			Input_Checkboxes=wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//ul[preceding::*]//input[@type='checkbox']/..//label")));
			
			 auto_select_category=rand.nextInt((Input_Checkboxes.size())-1) + 1;
			 Thread.sleep(3000);
			 
			 actions.moveToElement(Input_Checkboxes.get(auto_select_category)).click().build().perform();
			 
			
			 
			 if(!user_sagia&&auto_select_category==2)
			 {
				 add_Foreign_CR(false);
				 return false;
			 }
			 
		}
		
		else if(!user_sagia&&(selectedcmptype==2||selectedcmptype==3))
		{
			 Thread.sleep(3000);//ul[preceding::*]//input[@type='checkbox']
			 Input_Checkboxes=wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//div[@class='back back"+selectedcmptype+"']/div/form/div[@class='col-xs-12']//label")));
			 
			 auto_select_category= 0;
			 
			 actions.moveToElement(Input_Checkboxes.get(auto_select_category)).click().build().perform();
			 return true;
		}
		
		 
		 return true;
		
	}
	
	public void add_Foreign_CR(Boolean Sagia_cr) throws InterruptedException
	{
		
		WebElement Add_CR=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='أضف ترخيص']")));//label[contains(text(),'وجود ورثة')]
		Add_CR.click();
		
		if(Sagia_cr)
		{
			Select unified_License_select=new Select(wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//select[@id='investNumber']"))));
			unified_License_select.selectByIndex(1);
		}
		else if(!Sagia_cr)
		{
			WebElement Frng_LIC=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='licenseNumber']")));
			Frng_LIC.sendKeys("10213390279224");
			Thread.sleep(2000);
		}
		
		
		
		WebElement verify_UL_button=wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[@id='investNumber_btn']")));
		verify_UL_button.click();
		
		Thread.sleep(5000);
		
		WebElement Add_verified_UL=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='إضافة ']")));
		Add_verified_UL.click();
	}
	
	public String[] selectActivities() throws Exception
	{
		if(!Acts_needed)
		{
			return null;
		}
		
		
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("scroll(0, 250)");
		String[] added_activities;
		String[] add_main_activities;
		
		
		WebElement activities=wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='select2-ActivitiesLevelThree-container']")));
		
		actions.moveToElement(activities);
		actions.perform();
		activities.click();
		
		Select activities_DDL=new Select(wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='ActivitiesLevelThree']"))));
		int number_of_activities=activities_DDL.getOptions().size();
		
	
		 Robot robot = new Robot();
		
		
		try
		{
			auto_picked_activity = rand.nextInt((number_of_activities/70)) + 1;
		}catch(Exception c)
		{
			System.out.println("No Activities exists for Selected Company");
		//	driver.close();
		
		}
		
		 
		
		
		
		for (int i=0;i<1;i++)
		{
		
			
			for(int j=0;j<auto_picked_activity;j++)
			{
				
				Thread.sleep(2000);
				robot.keyPress(KeyEvent.VK_DOWN);
				
			}
			robot.keyPress(KeyEvent.VK_DOWN);
			Thread.sleep(2000);
			
			robot.keyPress(KeyEvent.VK_ENTER);
			Thread.sleep(2000);
			
			activities.click();
		}
		
		//div[@id='gridCRActivity']//div/span[1]
		Thread.sleep(3000);
		
		List<WebElement> main_CR_activity=wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//div[@id='gridCRActivity']//div/span[1]")));
		
		third_LV_Activities=wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//div[@id='gridCRActivity']//strong")));
		
		added_activities=new String[third_LV_Activities.size()];
		add_main_activities=new String[main_CR_activity.size()];
		
		//added_activities[0]=main_CR_activity.getText();
		
		for (int j=1;j<third_LV_Activities.size();j++)
		{
			added_activities[j]=third_LV_Activities.get(j).getText();
		}
		
		
		for (int ac=1;ac<main_CR_activity.size();ac++)
		{
			add_main_activities[ac]=main_CR_activity.get(ac).getText();
		}
		
		String[] combined_acts=Arrays.copyOf(added_activities, added_activities.length + add_main_activities.length);
		System.arraycopy(add_main_activities, 0, combined_acts, added_activities.length, add_main_activities.length);
		
		shot.takeSnapShot(driver, "C:\\Users\\mmostafa\\eclipse-workspace\\Pre_Wizard\\Activities_screenshot");
	
		
		return combined_acts;
		
		
		
	}
	
	public int selectYears() throws Exception
	{
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		auto_picked_year = rand.nextInt(5) + 1;
		
		WebElement Years=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[@data='"+auto_picked_year+"']")));
		
		Thread.sleep(2000);
		Years.click();
		
		jse.executeScript("scroll(0, 2000)");
		
		Thread.sleep(5000);
		
		shot.takeSnapShot(driver, "C:\\Users\\mmostafa\\eclipse-workspace\\Pre_Wizard\\inputs_screenshot");
		
		WebElement Summary_button=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='عرض تفاصيل العقد المبدئية']")));
		Summary_button.click();
		
		System.out.println("Total Number of Years="+auto_picked_year);
		
		
		
		return auto_picked_year;
	}
	
	
	public String[] verify_results()
	{
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("scroll(0, 1500)");
		
		
		main_CR_Fees_Element=     wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='رسوم السجل التجاري']/../../div/span[text()='ريال']/../strong")));
		
		try
		{
			coc_Fees_Element=         wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='رسوم إشتراك الغرفة التجارية  ']/../../div/span[text()='ريال']/../strong")));
		}
		catch(Exception c)
		{
			
			if(c.getMessage().contains("//*[text()='رسوم إشتراك الغرفة التجارية  ']/"))
			{
				System.out.println("There's no Subscription for COC for selected City...");
			}
		}
		
		publish_Fees_Element=     wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='رسوم النشر الإلكتروني  ']/../../div/span[text()='ريال']/../strong")));
		
		total_Fees_Element=       wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='اجمالي الرسوم']/../../div/span[text()='ريال']/../strong")));
		
		vat_Fees_Element=         wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='اجمالي القيمة المضافة']/../../div/span[text()='ريال']/../strong")));
		
		total_plus_vat_Element=   wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='اجمالي الرسوم + القيمة المضافة']/../../div/span[text()='ريال']/../strong")));
		
		List<WebElement> displayed_main_Acts_elems=wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[@id='ActivityViewOnly']//div/span[1]")));
		
		List<WebElement> displayed_third_LV_Acts_elems=wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[@id='ActivityViewOnly']//strong")));
		
		displayed_main_activities=new String[displayed_main_Acts_elems.size()];
		displayed_third_activities=new String[displayed_third_LV_Acts_elems.size()];
		
		//added_activities[0]=main_CR_activity.getText();
		
		for (int ac=1;ac<displayed_main_Acts_elems.size();ac++)
		{
			displayed_main_activities[ac]=displayed_main_Acts_elems.get(ac).getText();
		}
		
		
		
		for (int j=1;j<displayed_third_LV_Acts_elems.size();j++)
		{
			displayed_third_activities[j]=displayed_third_LV_Acts_elems.get(j).getText();
		}
		
		
		
		
		String[] combined_acts=Arrays.copyOf(displayed_third_activities, displayed_third_activities.length + displayed_main_activities.length);
		System.arraycopy(displayed_main_activities, 0, combined_acts, displayed_third_activities.length, displayed_main_activities.length);
		
		
		
		
		main_CR_Fees=Double.valueOf(main_CR_Fees_Element.getText());
		//coc_Fees=Double.valueOf(coc_Fees_Element.getText());
		publish_Fees=Double.valueOf(publish_Fees_Element.getText());
		total_Fees=Double.valueOf(total_Fees_Element.getText());
		vat_Fees=Double.valueOf(vat_Fees_Element.getText());
		total_plus_vat=Double.valueOf(total_plus_vat_Element.getText());
		
		results[0]=main_CR_Fees;
		results[1]=coc_Fees;
		results[2]=publish_Fees;
		results[3]=total_Fees;
		results[4]=vat_Fees;
		results[5]=total_plus_vat;
		
		String[]results_str= new String[results.length];
		
		for (int val=0;val<results.length;val++)
		{
			results_str[val]=String.valueOf(results[val]);
		}
		
		String[] combined_results_acts=Arrays.copyOf(results_str, results_str.length + combined_acts.length);
		System.arraycopy(combined_acts, 0, combined_results_acts, results_str.length, combined_acts.length);
		
		return combined_results_acts;
	}
	
	public String[] ECR_Converted_Firms(String ECRNum) throws InterruptedException
	{
		 Add_ECR=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(),'أضف منشأة')]")));
		 Add_ECR.click();
		 
		 
		 ECR_num_Field=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='CrNumber']")));
		 Verify_ECR=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(text(),'تحقق')]")));
		 
		 ECR_num_Field.sendKeys(ECRNum);
		 Verify_ECR.click();
		 
		 Thread.sleep(1500);
		 
		 
		 Esta_Name=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[contains(text(),'اسم المنشأة')]/../span")));
		 Esta_Expirydate=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[contains(text(),'تاريخ انتهاء السجل')]/../span")));
		 
		 Esta_Capital=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[contains(text(),'تقييم اصولها')]/../input")));
		 Esta_Capital.sendKeys("8000");
		 
		 Esta_successor_chkbx=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[contains(text(),'وجود ورثة')]")));
		 actions.moveToElement(Esta_successor_chkbx).click().build().perform();
		 
		 
		 Esta_successor_txt=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[contains(text(),'وجود ورثة')]/../../textarea")));
		 Esta_successor_txt.sendKeys("Automation script by Sedky");
		 
		 get_Establishment_data[0]="Establishment Name is:"+Esta_Name;
		 get_Establishment_data[1]="Establishment Expirydate is:"+Esta_Expirydate;
		 
		 Add_Esta=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(text(),'إضافة')]")));
		 
		 return get_Establishment_data;
	}
	
	public Checked_ECR_Criteria ECR_marked(int number_of_added_CRs)
	{
		KeepCR=wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//label/input[@id='KeepCRChk']/..")));
		KeepCrName=wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//label/input[@id='KeepNameChk']/..")));
		MarkCrMain=wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//label/input[@id='IsMainChk']/..")));
		
		
		for (int numchecked_keepCR=0;numchecked_keepCR<=number_of_added_CRs;numchecked_keepCR++)
		{
			ECR_critieria_KeepCR = rand.nextInt(KeepCR.size()-1)+1;
			
			actions.moveToElement(KeepCR.get(ECR_critieria_KeepCR)).click().build().perform();
			
			KeepCRs_chked[numchecked_keepCR]=ECR_critieria_KeepCR;
		}//Randomly Checked for KeepCR Check-boxes
		
		
		
		for (int numchecked_IsMain=0;numchecked_IsMain<=number_of_added_CRs;numchecked_IsMain++)
		{
			ECR_critieria_IsMain = rand.nextInt(KeepCR.size()-1)+1;
			
		
			actions.moveToElement(MarkCrMain.get(ECR_critieria_IsMain)).click().build().perform();
			
			IsMain_chked[numchecked_IsMain]=ECR_critieria_IsMain;
		}//Randomly Checked for IsMain Check-boxes
		
		
		
		for (int numchecked_keepName=0;numchecked_keepName<=number_of_added_CRs;numchecked_keepName++)
		{
			ECR_critieria_KeepName = rand.nextInt(KeepCR.size()-1)+1;
			
			actions.moveToElement(KeepCrName.get(ECR_critieria_KeepName)).click().build().perform();
			
			KeepName_chked[numchecked_keepName]=ECR_critieria_KeepName;
		}//Randomly Checked for KeepName Check-boxes
		
		return new Checked_ECR_Criteria(KeepCRs_chked, IsMain_chked, KeepName_chked);
		
		
	}
	
/*
	public void getSaudi_DB() throws ClassNotFoundException, SQLException
	{
		multi_arrs=data_db.Get_Saudi_Data();
		
		String[] retrived_IDs=multi_arrs.a;
		String[] retrived_BDatess=multi_arrs.b;
	}
*/	
	/*
	public void Add_Partners(int cmp_type_tawsyia) throws InterruptedException, ClassNotFoundException, SQLException
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
		
		if(partners_iterator>=3)
		{
			String cap_text=death.death_Captcha(driver, wait);
			WebElement Capt_field=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='Captch']")));
			Capt_field.sendKeys(cap_text);
		}
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
	
	
	*/
	@AfterClass
	public void teardown(){
		//close the app
		//driver.quit();
	}
	

}

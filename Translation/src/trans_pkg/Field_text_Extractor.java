package trans_pkg;

import java.awt.AWTException;
import java.net.MalformedURLException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Field_text_Extractor {
	
	//static WebDriver driver;
	Create_translate_sampler trans=new Create_translate_sampler();
	List<String> wanna_be_translated = Arrays.asList("المنشأت المحولة","شركاتي","طلباتي");
	String[] translated_result_text=new String[wanna_be_translated.size()];
	Export_translated export=new Export_translated();
	
	
	@BeforeClass
	public void setUp() throws MalformedURLException, AWTException, ClassNotFoundException, SQLException{
		//String webdriver_url=Reader.extract("chromedriver_Latest.exe"); //Extract ChromeDriver in User Temp folder
		
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\mmostafa\\eclipse-workspace\\Selenium\\src\\test\\chromedriver_Latest.exe");
        WebDriver driver = new ChromeDriver();              						//Initiate instance of create ChromeDriver
        driver.get("http://10.10.52.92:4545/Unified/Welcome");			//Open TimeSheet URL
		
	}
	
	
	
	@Test
	public void testCal() throws Exception {
		
		translated_result_text=trans.translate(wanna_be_translated);
		
		export.print_results_translated(translated_result_text, 0);
		
		for(int result_iterator=0;result_iterator<translated_result_text.length;result_iterator++)
	 	{
	 		System.out.println(translated_result_text[result_iterator]);
	 	}
		
		 
	}
	
	@AfterClass
	public void teardown(){
		//close the app
		//driver.quit();
	}

}

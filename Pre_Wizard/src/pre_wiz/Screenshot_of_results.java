package pre_wiz;

import java.io.File;

import org.apache.commons.io.FileUtils;

import org.openqa.selenium.OutputType;

import org.openqa.selenium.TakesScreenshot;

import org.openqa.selenium.WebDriver;

public class Screenshot_of_results {
	
	 public void takeSnapShot(WebDriver webdriver,String fileWithPath) throws Exception{
		 int res=1;

	        //Convert web driver object to TakeScreenshot

	        TakesScreenshot scrShot =((TakesScreenshot)webdriver);

	        //Call getScreenshotAs method to create image file

	        File SrcFile=scrShot.getScreenshotAs(OutputType.FILE);

	        //Move image file to new destination

	        File DestFile=new File(fileWithPath.concat(String.valueOf(res++)).concat(".png"));

	        //Copy file at destination

	        FileUtils.copyFile(SrcFile, DestFile);

	    }


}

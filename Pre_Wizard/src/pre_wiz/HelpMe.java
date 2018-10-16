package pre_wiz;

import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HelpMe {
	
	WebElement HelpMe_btn;
	WebElement Next_btn;
	WebElement back_btn;
	List<WebElement> Trad_Voc_Activities;
	List<WebElement> No_of_pratnrs;
	List<WebElement> partnrs_Individuals;
	List<WebElement> partnrs_Cmps;
	List<WebElement> partnrs_Ecrs;
	List<WebElement> Trade_Acts;
	List<WebElement> debts_ways;
	List<WebElement> Is_Family;
	List<WebElement> Is_Hold;
	WebElement acts_text_saudi_gulf;
	WebElement debts_way_text;
	WebElement Is_family_text;
	WebElement Is_Hold_text;
	WebElement Is_Foreign_text;
	WebElement Foreign_Lic_No;
	WebElement Foreign_Lic_No_verify;
	WebElement Foreign_LIC_Result;
	WebElement result_cmp;
	Random rand = new Random();
	int selected_value;
	String selected_ind_parts;
	String selected_cmp_parts;
	String selected_ecr_parts;
	String selected_Trade_Acts_val;
	String selected_debts_way_val;
	String selected_Is_family_val;
	String selected_Is_Hold_val;
	int selected_Trade_Acts;
	int selected_debts_ways;
	int selected_indiv_partnrs;
	int selected_ecr_partnrs;
	int selected_acts;
	int selected_pratnrs;
	int selected_cmps_partnrs;
	int selected_Is_Family;
	int selected_Is_Hold;
	String []final_results=new String[100];
	
	
	public String[] helpme(WebDriver driver, WebDriverWait wait) throws InterruptedException
	{
		HelpMe_btn=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'ساعدني')]")));
		HelpMe_btn.click();
		
		Trad_Voc_Activities=wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//label[contains(@for,'10')]")));
		
		for(int iterator_acts=0;iterator_acts<Trad_Voc_Activities.size();iterator_acts++)
		{
			System.out.println("Acts are: "+Trad_Voc_Activities.get(iterator_acts).getText());
			final_results[iterator_acts]="Disbaled Activity types are is :"+Trad_Voc_Activities.get(iterator_acts).getText(); //0,1 index contains Activities
			
		}
		
		selected_acts=select_random_value(Trad_Voc_Activities,false);
		Trad_Voc_Activities.get(0).click();
		
		final_results[2]="Selected Activity Type is: "+Trad_Voc_Activities.get(0).getText(); //2 index for selected Activity type
		
		Next_btn=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(text(),'التالي')]")));
		Next_btn.click();
		
		if(Trad_Voc_Activities.get(0).getAttribute("for").contains("100")) 
		{//Trade Activity start Condition
			No_of_pratnrs=wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//label[contains(@for,'12') and @class='premium-label four col white-box height-auto']")));
			
			for(int iterator_pratnrs=0;iterator_pratnrs<No_of_pratnrs.size();iterator_pratnrs++)
			{
				System.out.println("pratnrs amounts are: "+No_of_pratnrs.get(iterator_pratnrs).getText());
				final_results[iterator_pratnrs+3]="Partners displayed Amounts are "+No_of_pratnrs.get(iterator_pratnrs).getText(); //3,4 for Partners Amount
				
			}
			
			selected_pratnrs=select_random_value(No_of_pratnrs,false);
			No_of_pratnrs.get(1).click();
			
			final_results[5]="Selected Amount of Partners is : "+No_of_pratnrs.get(1).getText(); //5 for select amount of Partners
			
			Next_btn=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(text(),'التالي')]")));
			Next_btn.click();
			
			if(No_of_pratnrs.get(1).getAttribute("for").contains("121"))
			{//More Than One Partner Condition
				partnrs_Individuals=wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//*[text()='أفراد']/..//label")));
				partnrs_Cmps=wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//*[text()='شركات ']/..//label")));
				partnrs_Ecrs=wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//*[text()='مؤسسات']/..//label")));
				
				for(int iterator_Indiv_partners=0;iterator_Indiv_partners<partnrs_Individuals.size();iterator_Indiv_partners++)
				{
					System.out.println("Individual Partners are: "+partnrs_Individuals.get(iterator_Indiv_partners).getText());
					final_results[iterator_Indiv_partners+6]="Individual Partners are: "+partnrs_Individuals.get(iterator_Indiv_partners).getText(); //6,7,8 for Individual Partner types
				}
				
				selected_indiv_partnrs=select_random_value(partnrs_Individuals,true);
				
				if(selected_indiv_partnrs<partnrs_Individuals.size())
				{
					selected_ind_parts=partnrs_Individuals.get(selected_indiv_partnrs).getText(); //get selected individual partns
					partnrs_Individuals.get(selected_indiv_partnrs).click();
					
					final_results[9]="Selected Individual Partner is: "+partnrs_Individuals.get(selected_indiv_partnrs).getText(); //9 for selected Individual Partner
				}
				
				
				
				
				for(int iterator_cmps_partners=0;iterator_cmps_partners<partnrs_Cmps.size();iterator_cmps_partners++)
				{
					System.out.println("Company Partners are: "+partnrs_Cmps.get(iterator_cmps_partners).getText());
					
					final_results[iterator_cmps_partners+10]="Company Partners are: "+partnrs_Cmps.get(iterator_cmps_partners).getText(); //10,11,12 for Company Partner Types
				}
				
				selected_cmps_partnrs=select_random_value(partnrs_Cmps,true);
				
				if(selected_cmps_partnrs<partnrs_Cmps.size())
				{
					selected_cmp_parts=partnrs_Cmps.get(selected_cmps_partnrs).getText(); // get selected company partns
					partnrs_Cmps.get(selected_cmps_partnrs).click();
					
					final_results[13]="Selected Company Partner is: "+partnrs_Cmps.get(selected_cmps_partnrs).getText(); //13 for selected Company Partner
				}
				
				
				
				for(int iterator_ecr_partners=0;iterator_ecr_partners<partnrs_Ecrs.size();iterator_ecr_partners++)
				{
					System.out.println("Company Partners are: "+partnrs_Ecrs.get(iterator_ecr_partners).getText());
					
					final_results[iterator_ecr_partners+14]="ECR Partners are: "+ partnrs_Ecrs.get(iterator_ecr_partners).getText(); //14,15 for ECR Partner types
					
				}
				
				selected_ecr_partnrs=select_random_value(partnrs_Ecrs,true);
				
				if(selected_ecr_partnrs<partnrs_Ecrs.size())
				{
					selected_ecr_parts=partnrs_Ecrs.get(selected_ecr_partnrs).getText(); // get selected ecr partns
					partnrs_Ecrs.get(selected_ecr_partnrs).click();
					
					final_results[16]="Selected ECR Partner is:"+partnrs_Ecrs.get(selected_ecr_partnrs).getText(); //16 for Selected ECR Partner
				}
				
				Thread.sleep(1000);
				
				//end of selections of more partners
				Next_btn=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(text(),'التالي')]")));
				Next_btn.click();
				
				if((selected_cmp_parts.contains("شركة")||selected_ecr_parts.contains("مؤسسة"))&&!selected_cmp_parts.contains("شركة أجنبية")&&!selected_ind_parts.contains("أجنبي"))
				{
					Is_Hold_cmp(driver, wait);
					
					Next_btn=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(text(),'التالي')]")));
					Next_btn.click();
					
					Is_Family_cmp(driver, wait);
					
					Next_btn=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(text(),'التالي')]")));
					Next_btn.click();
					
					result_cmp=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='stepLast']//h2")));
					
					String Selected_Company=result_cmp.getAttribute("innerText");
					
					System.out.println("Selected Company is: "+Selected_Company);
					
					final_results[17]="Selected Company will be :"+Selected_Company;
					
					
				}
				
				if(selected_ind_parts.contains("سعودي")||selected_ind_parts.contains("خليجي"))
				{
					Acts_Trade_types(driver,wait);
					
					
					//end of selection Trade Activities for selected *Gulf and Saudi*
					Next_btn=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(text(),'التالي')]")));
					Next_btn.click();
					
					if(selected_Trade_Acts_val.contains("البنوك"))
					{
						debts_ways_types( driver, wait);
						
						// end of selection of debts ways of *Bank* Acts of Selected partnrs *Gulf and Saudi* 
						Next_btn=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(text(),'التالي')]")));
						Next_btn.click();
						
						if(selected_debts_way_val.contains("جميع")||selected_debts_way_val.contains("بعض"))
						{
							Is_Family_cmp(driver,wait);
							
							// end of selection of debts ways *All/some of Partners* of *Bank* Acts of Selected partnrs *Gulf and Saudi* 
							
							Next_btn=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(text(),'التالي')]")));
							Next_btn.click();
							
							if(selected_Is_family_val.contains("نعم")||selected_Is_family_val.contains("لا"))
							{
								result_cmp=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='stepLast']//h2")));
								
								String Selected_Company=result_cmp.getText();
								
								System.out.println("Selected Company is: "+Selected_Company);
								
								final_results[17]="Selected Company will be :"+Selected_Company;
							}
							else
							{
								result_cmp=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='stepLast']//h2")));
								
								String Selected_Company=result_cmp.getText();
								
								System.out.println("Selected Company is: "+Selected_Company); 
								
								final_results[17]="Selected Company will be :"+Selected_Company;
							}
						} // end of selection of Is Family of debts ways *All/some of Partners* of *Bank* Acts of Selected partnrs *Gulf and Saudi*  -- Tawsyia baseta - Tadmonyia 
						
						
						
						
						
					}//end of selection of *Banks* Trade Acts
					
					
					
					else //start of غير ذلك of Trade Acts ...
					{
						debts_ways_types(driver, wait);
						
						
						
						
						// end of selection of debts ways of *Bank* Acts of Selected partnrs *Gulf and Saudi* 
						Next_btn=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(text(),'التالي')]")));
						Next_btn.click();
						
						if(selected_debts_way_val.contains("جميع")||selected_debts_way_val.contains("بعض"))
						{
							Is_Family_cmp(driver, wait);
							
							// end of selection of debts ways *All/some of Partners* of *Bank* Acts of Selected partnrs *Gulf and Saudi* 
							
							Next_btn=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(text(),'التالي')]")));
							Next_btn.click();
							
							if(selected_Is_family_val.contains("نعم")||selected_Is_family_val.contains("لا"))
							{
								result_cmp=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='stepLast']//h2")));
								
								String Selected_Company=result_cmp.getText();
								
								System.out.println("Selected Company is: "+Selected_Company);
								
								final_results[17]="Selected Company will be :"+Selected_Company;
							}
							else
							{
								result_cmp=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='stepLast']//h2")));
								
								String Selected_Company=result_cmp.getText();
								
								System.out.println("Selected Company is: "+Selected_Company); 
								
								final_results[17]="Selected Company will be :"+Selected_Company;
							}
						}//end of scenarios to go for Is Family Company
						
						else //start of Is Hold company option
						{
							Is_Hold_cmp(driver,wait);
														
							Next_btn=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(text(),'التالي')]")));
							Next_btn.click();
							
							//Is Hold Option selected or not 
							
							Is_Family_cmp(driver, wait);
							
							// end of selection of debts ways *All/some of Partners* of *Bank* Acts of Selected partnrs *Gulf and Saudi* 
							
							Next_btn=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(text(),'التالي')]")));
							Next_btn.click();
							
							if(selected_Is_family_val.contains("نعم")||selected_Is_family_val.contains("لا"))
							{
								result_cmp=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='stepLast']//h2")));
								
								String Selected_Company=result_cmp.getText();
								
								System.out.println("Selected Company is: "+Selected_Company);
								
								final_results[17]="Selected Company will be :"+Selected_Company;
							}
							else
							{
								result_cmp=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='stepLast']//h2")));
								
								String Selected_Company=result_cmp.getText();
								
								System.out.println("Selected Company is: "+Selected_Company); 
								
								final_results[17]="Selected Company will be :"+Selected_Company;
							}
					
							
						} //end of Is Hold company option
					}// end of Other Acts ... 
					
					
				}// end of Saudi or Gulf option
				
				else if(selected_ind_parts.contains("أجنبي")|| selected_cmp_parts.contains("أجنبية"))// start of Foreign user
				{
					Is_Foreign_cmp(driver, wait, "10213390279224");
					
					Next_btn=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(text(),'التالي')]")));
					Next_btn.click();
					
					Is_Hold_cmp(driver, wait);
					
					Next_btn=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(text(),'التالي')]")));
					Next_btn.click();
					
					result_cmp=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='stepLast']//h2")));
					
					String Selected_Company=result_cmp.getText();
					
					System.out.println("Selected Company is: "+Selected_Company);
					
					final_results[17]="Selected Company will be :"+Selected_Company;
					
				}
				
				
			}//end of More Than one Partner Condition
			else
			{
				Is_Hold_cmp(driver, wait);
				
				Next_btn=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(text(),'التالي')]")));
				Next_btn.click();
				
				result_cmp=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='stepLast']//h2")));
				
				String Selected_Company=result_cmp.getText();
				
				System.out.println("Selected Company is: "+Selected_Company);
				
				final_results[17]="Selected Company will be :"+Selected_Company;
				
				
			}
			
			
		}//end of Trade Activity Condition
		else
		{
			result_cmp=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='stepLast']//h2")));
			
			String Selected_Company=result_cmp.getAttribute("innerText");
			
			System.out.println("Selected Company is: "+Selected_Company);
			
			final_results[17]="Selected Company will be :"+Selected_Company;
		}
		return final_results;
		
		
	}
	
	
	
	
	
	
	
	

	public void Acts_Trade_types(WebDriver driver,WebDriverWait wait)
	{
		acts_text_saudi_gulf=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//strong[contains(text(),'التجارية')]")));
		System.out.println("Next step is: "+acts_text_saudi_gulf.getText());
		
		Trade_Acts=wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//strong[contains(text(),'التجارية')]/../..//label")));
		
		for(int iterator_Trade_Acts=0;iterator_Trade_Acts<Trade_Acts.size();iterator_Trade_Acts++)
		{
			System.out.println("Trade Activity options are: "+Trade_Acts.get(iterator_Trade_Acts).getText());
		}
		
		selected_Trade_Acts=select_random_value(Trade_Acts,false);
		
		if(selected_Trade_Acts<partnrs_Individuals.size())
		{
			selected_Trade_Acts_val=Trade_Acts.get(selected_Trade_Acts).getText(); //get selected Trade Acts
			Trade_Acts.get(selected_Trade_Acts).click();
		}
		
	}
	
	public void debts_ways_types(WebDriver driver,WebDriverWait wait)
	{
		debts_way_text=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//strong[contains(text(),'ديون')]")));
		System.out.println("Next step is: "+debts_way_text.getText());
		
		debts_ways=wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//strong[contains(text(),'ديون')]/../..//label")));
		
		for(int iterator_debts_ways=0;iterator_debts_ways<debts_ways.size();iterator_debts_ways++)
		{
			System.out.println("debts ways options are: "+Trade_Acts.get(iterator_debts_ways).getText());
		}
		
		selected_debts_ways=select_random_value(debts_ways,false);
		
		if(selected_Trade_Acts<debts_ways.size())
		{
			selected_debts_way_val=debts_ways.get(selected_debts_ways).getText(); //get selected Trade Acts
			debts_ways.get(selected_debts_ways).click();
		}
	}
	
	public void Is_Family_cmp(WebDriver driver,WebDriverWait wait)
	{
		try
		{
			Is_family_text=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//strong[contains(text(),'عائلية')]")));
			System.out.println("Next step is: "+Is_family_text.getText());
			
			Is_Family=wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//strong[contains(text(),'عائلية')]/../..//label")));
			
			for(int iterator_Is_Family=0;iterator_Is_Family<Is_Family.size();iterator_Is_Family++)
			{
				System.out.println("Is Family options are: "+Is_Family.get(iterator_Is_Family).getText());
			}
			
			selected_Is_Family=select_random_value(Is_Family,false);
			
			if(selected_Is_Family<Is_Family.size())
			{
				selected_Is_family_val=Is_Family.get(selected_Is_Family).getText(); //get selected Trade Acts
				Is_Family.get(selected_Is_Family).click();
			}
		}catch(Exception e)
		{
			System.out.println("There is an Issue in Is Family Step ");
		}
		
		
	}
	
	public void Is_Hold_cmp(WebDriver driver,WebDriverWait wait)
	{
		Is_Hold_text=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//strong[contains(text(),'قابضة')]")));
		System.out.println("Next Step is :"+Is_Hold_text);
		
		Is_Hold=wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//strong[contains(text(),'قابضة')]/../..//label")));
		
		for(int iterator_Is_Hold=0;iterator_Is_Hold<Is_Hold.size();iterator_Is_Hold++)
		{
			System.out.println("Is Hold options are: "+Is_Hold.get(iterator_Is_Hold).getText());
		}
		
		selected_Is_Hold=select_random_value(Is_Hold,false);
		
		if(selected_Is_Hold<Is_Hold.size())
		{
			selected_Is_Hold_val=Is_Hold.get(selected_Is_Hold).getText(); //get selected Trade Acts
			Is_Hold.get(selected_Is_Hold).click();
		}
	}
	
	
	public void Is_Foreign_cmp(WebDriver driver,WebDriverWait wait,String LIC_No)
	{
		
		Is_Foreign_text=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//strong[contains(text(),'رقم الترخيص')]")));
		System.out.println("Next step is: "+Is_Foreign_text.getText());
		
		Foreign_Lic_No=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//strong[contains(text(),'رقم الترخيص')]/../..//input")));
		Foreign_Lic_No.sendKeys(LIC_No);
		
		Foreign_Lic_No_verify=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//strong[contains(text(),'رقم الترخيص')]/../..//*[contains(text(),'تحقق')]")));
		Foreign_Lic_No_verify.click();
		
		Foreign_LIC_Result=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='_forigen_help']//h2")));
		System.out.println("Foreign Company selected is :"+Foreign_LIC_Result.getText());
	}
	
	
	public int select_random_value(List<WebElement> list,Boolean Is_for_partners)
	{
		if(Is_for_partners=false)
		{
			selected_value=rand.nextInt(list.size()-1);
		}
		else
		{
			selected_value=rand.nextInt(list.size());
		}
		
		return selected_value;
	}
	
	
	
}

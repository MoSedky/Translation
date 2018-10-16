package trans_pkg;


import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import java.util.List;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.translate.Translate;
import com.google.api.services.translate.model.TranslationsListResponse;
import com.google.api.services.translate.model.TranslationsResource;
import com.google.common.collect.Lists;


public class Create_translate_sampler {
	
	  
	  
	 
	 
	public String[] translate(List<String> input_text)throws IOException, GeneralSecurityException
	{
		 String[] translated_txt=new String[input_text.size()];
		System.out.println(input_text.size());
		int translated_iterator=0;
        Translate t = new Translate.Builder(
                GoogleNetHttpTransport.newTrustedTransport()
                , GsonFactory.getDefaultInstance(), null)
                // Set your application name
                .setApplicationName("CCUX").build();
               // .build();
        Translate.Translations.List list = t.new Translations().list(
               
                        // Pass in list of strings to be translated
                		input_text,
                // Target language
                "EN");

        // TODO: Set your API-Key from https://console.developers.google.com/
        list.setKey("AIzaSyAUll9gWSjQOScd96sDZFYoiaHb_R3AMQE");
        TranslationsListResponse response = list.execute();
        for (TranslationsResource translationsResource : response.getTranslations())
        {
        	
        		//System.out.println(translationsResource.getTranslatedText());
                translated_txt[translated_iterator++]=translationsResource.getTranslatedText();
        
            
        }
        
		return translated_txt;
    }
	
	/*
	public static void main(String[] arguments) throws IOException, GeneralSecurityException
	 {
		 
		 	result=translate(wanna_be_translated);
		 
		 	for(int i=0;i<result.length;i++)
		 	{
		 		System.out.println(result[i]);
		 	}
	 }
	 */
	
	

}

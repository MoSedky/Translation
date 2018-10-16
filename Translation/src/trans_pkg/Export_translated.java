package trans_pkg;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Export_translated {

	
	private static final String FILE_NAME_TRANSLATE = "C:\\Users\\mmostafa\\eclipse-workspace\\Pre_Wizard\\Translated_Text.xlsx";
    
    XSSFWorkbook workbook = new XSSFWorkbook();
   
    
	

    
    public void  print_results_translated(String[] Help_Me_Res,int rowNum) throws Exception
    {
    	
    	 XSSFSheet sheet=workbook.createSheet("Help Me Results"+rowNum);
    	 
    	 XSSFCellStyle style = workbook.createCellStyle();
    	
    	 XSSFFont font = workbook.createFont();
    	 font.setFontHeightInPoints((short) 15);
    	 font.setBold(true);
    	 style.setFont(font);                 
       //  sheet = workbook.createSheet("PreWizard Results"+rowNum);
        if(sheet.getLastRowNum()==0)
        {
        	rowNum=0;
        }
        else
        {
        	rowNum=sheet.getLastRowNum();
        }
       

        
        System.out.println("Creating excel");

        for (int it_Acts=0;it_Acts<Help_Me_Res.length;it_Acts++) 
        {
            Row row = sheet.createRow(rowNum++);
            int colNum = 0;
           
            Cell cell = row.createCell(colNum++);
                
            cell.setCellValue(Help_Me_Res[it_Acts]);   
        }
        
        
        
        try {
            FileOutputStream outputStream = new FileOutputStream(FILE_NAME_TRANSLATE);
            workbook.write(outputStream);
           // workbook.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Done");
    }
}

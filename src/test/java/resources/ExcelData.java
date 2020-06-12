package resources;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelData {
	
	static Workbook book;
	 static Sheet sheet;
	 
	public static Object[][] getTestData(String sheetName) throws InterruptedException {
		FileInputStream file = null;
		try {
			file = new FileInputStream("C:\\Users\\Pal\\workspace1\\RestAssured\\TestData\\TestDataMS.xlsx");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			book = WorkbookFactory.create(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
		sheet = book.getSheet(sheetName);
	
		
Object[][] data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
		
		for (int i = 0; i < sheet.getLastRowNum(); i++) {
			Thread.sleep(4000);
			try{
			for (int k = 0; k < sheet.getRow(0).getLastCellNum(); k++) {
				
				Cell c=sheet.getRow(i + 1).getCell(k);
				if(c!=null){
				
				data[i][k] = c.toString();
				}
			}
			}catch(NullPointerException ec){
				
			ec.getMessage();
				
				
			}
		}
		return data;
	}
		
}

package Practice;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.formula.functions.Rows;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.ss.util.NumberToTextConverter;

public 

class dataDriven {
public ArrayList<String> getData(String TestCaseName) throws EncryptedDocumentException, IOException{
	ArrayList<String> al=new ArrayList();
	
	FileInputStream	file = new FileInputStream("C:\\Users\\Pal\\workspace1\\RestAssured\\TestData\\Book125.xlsx");	
	Workbook book = WorkbookFactory.create(file);
	int sheets=book.getNumberOfSheets();
	for(int i=0;i<sheets;i++){
		if(book.getSheetName(i).equalsIgnoreCase("Sheet1"))
		{
			
		Sheet sheet=book.getSheetAt(i);
		Iterator<Row> rows=sheet.iterator();
		Row row1=rows.next();
		Iterator<Cell>cells=row1.cellIterator();
		int k=0;
		int column = 0;
		while(cells.hasNext()){
			Cell c=cells.next();
			if(c.getStringCellValue().equalsIgnoreCase("State"))
			{
				column=k;
			}
		
		k++;
		}
		System.out.println(column);
		while(rows.hasNext()){
		 Row r=rows.next();
		if(r.getCell(column).getStringCellValue().equalsIgnoreCase(TestCaseName)){
			
			////after you grab purchase testcase row = pull all the data of that row and feed into test
			
			Iterator<Cell>  cv=r.cellIterator();
			while(cv.hasNext())
			{
			Cell c=	cv.next();
			if(c.getCellTypeEnum()==CellType.STRING)
			{
				
			al.add(c.getStringCellValue());
			}
			else{
				
				al.add(NumberToTextConverter.toText(c.getNumericCellValue()));
			
			}
			}
		}
		
			}
		
		}
		
	}
	return al;
	}


	public static void main(String[] args) throws EncryptedDocumentException, IOException {
		
		
}
}
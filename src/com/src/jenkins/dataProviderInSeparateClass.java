package com.src.jenkins;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;

public class dataProviderInSeparateClass {
	
	//Taking values from excel
	@DataProvider(name="anotherClass")
	public Object[][] dataProviderAnotherClass() throws IOException{
		String file = "D:\\ExcelRead.xlsx";
		FileInputStream fileStream = new FileInputStream(file);
		XSSFWorkbook workBook = new XSSFWorkbook(fileStream);
		XSSFSheet sheet = workBook.getSheetAt(0);
		
		Object [][] data = new Object [sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
		
		for(int i=0;i<sheet.getLastRowNum();i++){
			for(int j=0;j<sheet.getRow(0).getLastCellNum();j++){
				data[i][j] = sheet.getRow(i+1).getCell(j).toString();
			}
		}
		return data;
	}
	
	
}

package com.vtiger.comcast.genericutility;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

/**
 * This class is developed using Apache POI libraries which is used to handle microsoft excel workbook.
 * @author Swathi
 *
 */
public class ExcelUtility {

	
	/**
	 * This method is used to get the data from the cell in string form.
	 * @param sheetName
	 * @param rowNo
	 * @param cellNo
	 * @return
	 * @throws Exception 
	 * @throws EncryptedDocumentException 
	 */
	public String getDataFromExcel(String sheetName, int rowNo, int cellNo) throws Throwable {
		FileInputStream fis = new FileInputStream("./testdata/TestScriptData.xlsx");
		
		//Read the data from the excel workbook
		Workbook workbook = WorkbookFactory.create(fis);
		Sheet sheet = workbook.getSheet(sheetName);
		Row row = sheet.getRow(rowNo);
		String cellValue = row.getCell(cellNo).getStringCellValue();	
		workbook.close();
		return cellValue;
	}
	
	/**
	 * This method is used to get the row count. 
	 * @param sheetName
	 * @return
	 * @throws Throwable
	 */
	public int getRowCount(String sheetName) throws Throwable {
		FileInputStream fis = new FileInputStream("./testdata/TestScriptData.xlsx");
		
		//Read the data from the excel workbook
		Workbook workbook = WorkbookFactory.create(fis);
		Sheet sheet = workbook.getSheet(sheetName);
		workbook.close();
		return sheet.getLastRowNum();
		
	}
	
	/**
	 * This method is used to write the data into the specified cell.
	 * @param sheetName
	 * @param rowNo
	 * @param cellNo
	 * @param data
	 * @throws Throwable
	 */
	public void setDataToExcel(String sheetName, int rowNo, int cellNo, String data) throws Throwable {
		
		//Create a java object of the physical file for writing into the workbook.
		FileInputStream fis = new FileInputStream("./testdata/TestScriptData.xlsx");
		Workbook workbook = WorkbookFactory.create(fis);
		Sheet sheet = workbook.getSheet(sheetName);
		Row row = sheet.getRow(rowNo);
		Cell createCell = row.createCell(cellNo);
		
		//Write status to the physical file.
		createCell.setCellValue(data);
		
		//Create a java object of the physical file for writing into the workbook.
		FileOutputStream fos = new FileOutputStream("./testdata/TestScriptData.xlsx");
		
		//Write the data into the excel workbook
		workbook.write(fos);
		workbook.close();
	}
}

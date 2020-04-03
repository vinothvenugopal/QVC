package utils;

import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadExcel {

	public static String[][] readData(String wBook, String wSheet) throws IOException
	{
		XSSFWorkbook wbook = new XSSFWorkbook("./data/"+wBook);
		XSSFSheet sheet = wbook.getSheet(wSheet);
		int lastRowNum = sheet.getLastRowNum();
		short lastColNum = sheet.getRow(0).getLastCellNum();
		String[][] value = new String[lastRowNum][lastColNum];
		for (int i = 1; i <= lastRowNum; i++) 
		{
			XSSFRow row = sheet.getRow(i);
			for (int j = 0; j < lastColNum; j++) 
			{
				String cellValue = row.getCell(j).getStringCellValue();
				value[i-1][j]=cellValue;
			}
		}
		return value;
	}
}

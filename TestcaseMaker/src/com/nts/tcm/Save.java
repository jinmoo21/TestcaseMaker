package com.nts.tcm;

import java.io.FileOutputStream;
import java.io.IOException;

import javax.swing.JTable;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Save {
	private static final String excelFormat = ".xlsx";
	private static final String sheetName = "SheetName";
	
	public static void toXLSXFile(JTable table, String path) {
		XSSFWorkbook wb = new XSSFWorkbook();
		XSSFSheet sheet = wb.createSheet(sheetName);
		for (int i = 0; i < 5; ++i) {
			XSSFRow row = sheet.createRow(i);
			for (int j = 0; j < 5; ++j) {
				XSSFCell cell = row.createCell(j);
				cell.setCellValue(table.getValueAt(i, j).toString());
			}
		}
		try {
			FileOutputStream fileOut = new FileOutputStream(path + excelFormat);
			wb.write(fileOut);
			fileOut.flush();
			fileOut.close();
			wb.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

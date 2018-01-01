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
		if (path.endsWith(excelFormat)) {
			path = path.substring(0, (path.length() - excelFormat.length()));
		}
		XSSFWorkbook wb = new XSSFWorkbook();
		XSSFSheet sheet = wb.createSheet(sheetName);
		XSSFRow row = sheet.createRow(0);
		for (int i = 0; i < table.getColumnCount(); ++i) {
			XSSFCell cell = row.createCell(i);
			cell.setCellValue(Controller.columnNames[i]);
		}
		for (int i = 0; i < table.getRowCount(); ++i) {
			row = sheet.createRow(i + 1);
			for (int j = 0; j < table.getColumnCount(); ++j) {
				XSSFCell cell = row.createCell(j);
				if (table.getValueAt(i, j) != null) {
					cell.setCellValue(table.getValueAt(i, j).toString());
				}
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

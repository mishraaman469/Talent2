
package com.mindtree.user.controller;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserControllerTest {

	@InjectMocks
	UserController userController;
	static String fileName="/mydata/test.xls";
	static int result=0;
	static HSSFWorkbook workbook=new HSSFWorkbook();
	static HSSFSheet sheet=workbook.createSheet();
	
	
	@BeforeAll
	public static void writeExcel() {
		
		 HSSFRow row=sheet.createRow(0);
		row.createCell(0).setCellValue("TestCaseName");
		row.createCell(1).setCellValue("Result");
	}
	
	@Test
	void test() {
		 HSSFRow row=sheet.createRow(1);
			row.createCell(0).setCellValue("Test1");
			row.createCell(1).setCellValue("Fail");
		assertEquals("working", userController.getIndex());
		result+=5;
		row.createCell(0).setCellValue("Test1");
		row.createCell(1).setCellValue("Pass");
	}
	
	@Test
	void testGetIndex() {
		 HSSFRow row=sheet.createRow(2);
			row.createCell(0).setCellValue("Test2");
			row.createCell(1).setCellValue("Fail");
		assertEquals("index", userController.getIndex());
		result+=5;
		System.out.println(result);
		row.createCell(0).setCellValue("Test2");
		row.createCell(1).setCellValue("Pass");
	}
	
	@Test
	void testetIndex() {
		 HSSFRow row=sheet.createRow(3);
			row.createCell(0).setCellValue("Test3");
			row.createCell(1).setCellValue("Fail");
		assertEquals("index", userController.getIndex());
		result+=5;
		System.out.println(result);
		row.createCell(0).setCellValue("Test3");
		row.createCell(1).setCellValue("Pass");
	}
	
	
	@AfterAll
	public static void saveExcel() throws IOException {
		FileOutputStream out=new FileOutputStream(fileName);
		workbook.write(out);
		out.close();
		workbook.close();
	}
	
	
	
}

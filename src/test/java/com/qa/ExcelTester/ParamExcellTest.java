package com.qa.ExcelTester;

import static org.junit.Assert.*;

import java.io.*;
import java.util.*;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.*;
import org.junit.runners.Parameterized.Parameters;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.support.PageFactory;

@RunWith(Parameterized.class)
public class ParamExcellTest {

	public WebDriver driver;
	
	@Before
	public void setup() {
		System.setProperty("phantomjs.binary.path", Constants.PHANTDRIVER);
		driver = new PhantomJSDriver();
	}
	
	@After
	public void tearDown() {
		driver.quit();
	}
	
	@Parameters
	public static Collection<Object[]> data() throws IOException {
		FileInputStream file = new FileInputStream(Constants.FILELOCATION);
		XSSFWorkbook workbook = new XSSFWorkbook(file);
		XSSFSheet sheet = workbook.getSheetAt(0);
		
		Object[][] ob = new Object[sheet.getPhysicalNumberOfRows()-1][4];
		
		for (int rowNum = 1; rowNum < sheet.getPhysicalNumberOfRows(); rowNum++) {
			ob[rowNum-1][0] = sheet.getRow(rowNum).getCell(0).getStringCellValue();
			ob[rowNum-1][1] = sheet.getRow(rowNum).getCell(1).getStringCellValue();
			ob[rowNum-1][2] = sheet.getRow(rowNum).getCell(2).getStringCellValue();
			ob[rowNum-1][3] = rowNum;
		}
		file.close();
		workbook.close();
	return Arrays.asList(ob);
	}
	
	private String username;
	private String password;
	private String expected;
	private int row;
	
	public ParamExcellTest(String username, String password, String expected, int row) {
		this.username = username;
		this.password = password;
		this.expected = expected;
		this.row = row;
	}
	
	@Test
	public void testy() throws Exception {
		
		FileInputStream file = new FileInputStream(Constants.FILELOCATION);
		XSSFWorkbook workbook = new XSSFWorkbook(file);
		XSSFSheet sheet = workbook.getSheetAt(0);
		
		DemoLandingPage dlp = PageFactory.initElements(driver,DemoLandingPage.class);
		DemoLoginPage loginPage = PageFactory.initElements(driver, DemoLoginPage.class);
		
		driver.get(Constants.DEMOURL);
		dlp.inputUsername(username); dlp.inputPassword(password); dlp.submit();
		dlp.goToLoginPage();
		
		loginPage.inputUsername(username); loginPage.inputPassword(password); loginPage.submit();
		String status = loginPage.getResult();
		
		XSSFRow rowNum = sheet.getRow(row);
		XSSFCell cellActual = rowNum.getCell(3);
		XSSFCell cellResult = rowNum.getCell(4);
		
		if(cellActual == null) {
			cellActual = rowNum.createCell(3);
		}
		
		if(cellResult == null) {
			cellResult = rowNum.createCell(4);
		}
		
		cellActual.setCellValue(status);
		
		try {
			assertEquals("Login not successful", expected, status);
			cellResult.setCellValue("Pass");
		}
		catch (AssertionError e) {
			cellResult.setCellValue("Fail");
			System.out.println("Error: " + e);
		}
		finally {
		FileOutputStream fileout = new FileOutputStream(Constants.FILELOCATION);
		workbook.write(fileout);
		fileout.flush();
		fileout.close();
		file.close();
		workbook.close();
		}
	}
}

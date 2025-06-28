package com.swaglabs.utils;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;



public class ReadData {

	private Sheet sheet;
	
	public ReadData(String filename)
	{
		String projectpath=System.getProperty("user.dir");
		String filepath=projectpath+"/src/main/resources/testdata/"+filename+".xlsx";
		File f = new File(filepath);
		if(!f.exists())
		{
			System.out.println("File not found : "+filepath);
			return;
		}
		try {
		FileInputStream instream = new FileInputStream(f);
		Workbook wb = new XSSFWorkbook(instream);
		 sheet =wb.getSheet("Sheet1");
		
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	
	public String getData(int rowno,int cellno)
	{
		return sheet.getRow(rowno).getCell(cellno).getStringCellValue();
	}
	
	public List<String> getData(int rowno)
	{
		List<String> rowdata = new ArrayList<String>();
		int cellscount = sheet.getRow(rowno).getLastCellNum(); 
		for(int i=0;i<cellscount;i++)
		{
			rowdata.add(sheet.getRow(rowno).getCell(i).getStringCellValue());
		}
		return  rowdata;
	}
	
	public String[][] getData()
	{
		int lastrowno=sheet.getLastRowNum(); // 9
		int cellscount=sheet.getRow(0).getLastCellNum();  //2
		String testdata[][] = new String[lastrowno][cellscount];
		int k=0,l;
		
		for(int i=1;i<=lastrowno;i++)
		{
			l=0;
			for(int j=0;j<cellscount;j++)
			{
				String data=sheet.getRow(i).getCell(j).getStringCellValue();
				testdata[k][l]=data;
				l++;
			}
			k++;
		}
		return testdata;
	}
	
	public static void main(String[] args) {
		ReadData read = new ReadData("swaglabslogindata");
		String testdata[][]=read.getData();
		for(int i=0;i<testdata.length;i++)
		{
			
			for(int j=0;j<testdata[i].length;j++)
			{
				System.out.print(testdata[i][j]+" ");
			}
			System.out.println();
			
		}
	}
	
}
// workbook --> sheet --> rows --> cells --> data
// get data from specific cell 












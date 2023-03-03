package dataProvider;

/* Logic here is to have an object 2D array that picks all the values from an Excel and stores into an array accordingly
So, it picks row 0, column 0 and stores into [0] and then row 0. column 1 into [1]

We will build a library that will ask only for the sheet name, load the complete excel sheet or workbook or data
 & it will give us the data in object 2D array using a "for loop" and no need to write the code
 */

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class ExcelReaderToGetDataFromSheetRowAndColumn {
    static XSSFWorkbook wb;

    //Writing a method that will pick values from the excel and return Object 2D Array

    //Creating method for Getting String Data from Sheet starting with Row 0

    public static Object[][] getOnlyStringDataFromSheetAndRow0(String sheetName) throws IOException {
        System.out.println("********Loading Data From Excel*************");

        Object[][] arr = null; //making the value of Object 2D null as it's a local variable

        //Then will create my workbook object which will accept my file to load the excel workbook
        wb = new XSSFWorkbook(new FileInputStream(new File("./testData/AutomationTestData2.xlsx")));

        //Then we load the sheet
        XSSFSheet sh = wb.getSheet(sheetName);

        //Followed by loading the row to get rows
        int row = sh.getPhysicalNumberOfRows();

        //Get columns: Then we load the column: And to do this, we first use getRow then getPhysicalNumberOfCells which will load all cells in that row
        int column = sh.getRow(0).getPhysicalNumberOfCells();

        //Create array based on rows and columns from Excel
        arr = new Object[row][column]; // this will automatically get the details and store in array & change based on sheetName

        //To transport the data from Excel to 2D Object Array, we use a "for loop"
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                arr[i][j] = wb.getSheet(sheetName).getRow(i).getCell(j).getStringCellValue();
            }
        }
        return arr;
    }


    //Creating method for Getting String Data from Sheet starting with Row 1
    public static Object[][] getOnlyStringDataFromSheetAndRow1(String sheetName) throws IOException {
        System.out.println("********Loading Data From Excel*************");

        Object[][] arr = null; //making the value of Object 2D null as it's a local variable

        //Then will create my workbook object which will accept my file to load the excel workbook
        wb = new XSSFWorkbook(new FileInputStream(new File("./testData/AutomationTestData2.xlsx")));

        //System.getProperty("user.dir")+"/testDataForFramework/AutomationTestData2.xlsx"

        //Then we load the sheet
        XSSFSheet sh = wb.getSheet(sheetName);

        //Followed by loading the row to get rows
        int row = sh.getPhysicalNumberOfRows();

        //Get columns: Then we load the column: And to do this, we first use getRow then getPhysicalNumberOfCells which will load all cells in that row
        int column = sh.getRow(0).getPhysicalNumberOfCells();

        //Create array based on rows and columns from Excel
        arr = new Object[row-1][column]; // this will automatically get the details and store in array & change based on sheetName

        //To transport the data from Excel to 2D Object Array, we use a "for loop"
        for (int i = 1; i < row; i++) {
            for (int j = 0; j < column; j++) {
                //arr[i-1][j] = wb.getSheet(sheetName).getRow(i).getCell(j).getStringCellValue();
                //Or i could write above as:
                arr[i-1][j] = getStringAndNumericDataFromSheet(sheetName,i,j);
            }
        }
        return arr;
    }


    //Creating method for Getting Numbers & String Data from Sheet starting with Row 1 (And if we are picking from
    // row 0, we just have to change the loop to start int i from 0: int i=0)
    public static String getStringAndNumericDataFromSheet(String sheetName, int row, int column)  {

        //I can use wb to call getSheet because wb is now a global variable in the class above and is also static
        //To get the cell, i have to use XSSFCell
        XSSFCell cell = wb.getSheet(sheetName).getRow(row).getCell(column);

        String data = "";

        //Then i need to capture the cell Type using Get Cell type
        if (cell.getCellType() == CellType.STRING) {
            data = cell.getStringCellValue();
        } else if (cell.getCellType() == CellType.NUMERIC) {
            data = String.valueOf(cell.getNumericCellValue());
        } else if (cell.getCellType() == CellType.BLANK) {
            data = "";
        }
        return data;

    }

}

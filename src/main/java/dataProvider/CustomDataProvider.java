package dataProvider;

import org.testng.annotations.DataProvider;

import java.io.IOException;

public class CustomDataProvider {

    @DataProvider(name = "LoginData")
    public Object[][] getData1() throws IOException {

        Object[][] arr = ExcelReaderToGetDataFromSheetRowAndColumn.getOnlyStringDataFromSheetAndRow1("login");
        return arr;
    }

    //Add Logout data
    //Add Home Page data
    //Add create course data
}

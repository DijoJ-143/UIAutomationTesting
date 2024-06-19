package com.ust.AssesmentSelenium.dataProvider;

import org.testng.annotations.DataProvider;

import com.ust.AssesmentSelenium.base.ReusableFunction;

public class DataProviding {


	@DataProvider(name = "Sizedata")
	public String[][] getData() {
		return ReusableFunction.readExcelFile("PATH_1", "Sheet1");
	}
}

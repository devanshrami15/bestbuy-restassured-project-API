package com.bestbuy.testbase;

import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;

public class TestBase {

    @BeforeClass
    public void inIt() {
        RestAssured.baseURI = "http://localhost";
        // RestAssured.baseURI = PropertyReader.getInstance().getProperty("baseURI");
        RestAssured.port = 3030;
        //RestAssured.basePath = "/stores";
    }
}

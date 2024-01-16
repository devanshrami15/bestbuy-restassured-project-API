package com.bestbuy.crudtest;

import com.bestbuy.model.ProductPojo;
import com.bestbuy.model.StorePojo;
import com.bestbuy.testbase.TestBase;
import com.bestbuy.utils.TestUtils;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class StoresCRUDTest extends TestBase {

    static String name = "Prime"+ TestUtils.getRandomValue()+"123";
    static String type = "Testing";
    static String address = "Offshore";
    static String address2 = "Zoom";
    static String city = "London";
    static String state = "UK";
    static String zip = "HA00AA";
    static int lat = 123456;
    static int lng = 456789;
    static String hours = "2";

    static int storeID;

    @Test
    public void test001() {

        HashMap<String, Object> services = new HashMap<>();
        services.put("JAVA", "3 Weeks");
        services.put("API", "2 Weeks");

        StorePojo storePojo = new StorePojo();
        storePojo.setName(name);
        storePojo.setType(type);
        storePojo.setAddress(address);
        storePojo.setAddress2(address2);
        storePojo.setCity(city);
        storePojo.setState(state);
        storePojo.setZip(zip);
        storePojo.setLat(lat);
        storePojo.setLng(lng);
        storePojo.setHours(hours);
        storePojo.setServices(services);

        Response response = given()
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .when()
                .body(storePojo)
                .post();
        response.prettyPrint();
        response.then().statusCode(201);

        storeID = response.then().extract().path("id");
        System.out.println("ID = " + storeID);

    }

    @Test
    public void test002() {

        Response response = given()
                .header("Accept", "application/json")
                .when()
                .get("/" + storeID);
        response.then().statusCode(200);

        response.prettyPrint();
    }

    @Test
    public void test003() {

        ProductPojo productPojo = new ProductPojo();
        productPojo.setName(name + "_123");
        productPojo.setType(type + "_good");

        Response response = given()
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .body(productPojo)
                .when()
                .patch("/" + storeID);
        response.then().statusCode(200);

        response.prettyPrint();
    }

    @Test
    public void test004() {

        given()
                .when()
                .delete("/" + storeID)
                .then()
                .statusCode(200);

        Response response = given()
                .header("Accept", "application/json")
                .when()
                .get("/" + storeID);
        response.then().statusCode(404);
    }
}

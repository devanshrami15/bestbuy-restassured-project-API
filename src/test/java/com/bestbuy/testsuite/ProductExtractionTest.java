package com.bestbuy.testsuite;

import com.bestbuy.testbase.TestBase;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;

import static io.restassured.RestAssured.given;

public class ProductExtractionTest extends TestBase {

    static ValidatableResponse response;

    @Test
    public static void productTest() {

        response = given()
                .when()
                .get("products")
                .then().statusCode(200);

        int limit = response.extract().path("limit");
        System.out.println("21. Extract the limit = " + limit);

        int total = response.extract().path("total");
        System.out.println("22. Extract the total = " + total);

        String name = response.extract().path("data[4].name");
        System.out.println("23. Extract the name of 5th product = " + name);

        List<String> productName = response.extract().path("data.name");
        System.out.println("24. Extract the names of all the products = " + productName);

        List<Integer> productId = response.extract().path("data.id");
        System.out.println("25. Extract the productId of all the products = " + productId);

        List<HashMap<String, Object>> data = response.extract().path("data");
        System.out.println("26. Print the size of the data list = " + data.size());

        List<Double> values = response.extract().path("data.findAll{it.name == 'Energizer - MAX Batteries AA (4-Pack)'}.id");
        System.out.println("27. Get all the value of the product where product name (Energizer - MAX Batteries AA (4-Pack)) = " + values);

        List<String> productModel = response.extract().path("data.findAll{it.name=='Energizer - N Cell E90 Batteries (2-Pack)'}.model");
        System.out.println("28. Get the model of the product where product name (Energizer - N Cell E90 Batteries (2-Pack)) = " + productModel);

        List<List<HashMap<String, Object>>> categories = response.extract().path("data[7].categories");
        System.out.println("29. Get all the categories of 8th products = " + categories);

        List<List<HashMap<String, Object>>> categories1 = response.extract().path("data.findAll{it.id == 150115}.categories");
        System.out.println("30. Get categories of the store where product id (150115) = " + categories1);

        List<String> desc = response.extract().path("data.description");
        System.out.println("31. Get all the descriptions of all the products = " + desc);

        List<String> categoriesId = response.extract().path("data.categories.id");
        System.out.println("32. Get id of all the all categories of all the products = " + categoriesId);

        List<String> pName = response.extract().path("data.findAll{it.type == 'HardGood'}.name");
        System.out.println("33. Find the product names Where type (HardGood) = " + pName);

        List<List<HashMap<String, Object>>> cate = response.extract().path("data.findAll{it.name == 'Duracell - AA 1.5V CopperTop Batteries (4-Pack)'}.categories");
        System.out.println("34. Find the Total number of categories for the product where product name (Duracell - AA 1.5V CopperTop Batteries (4-Pack)) = " + cate.size());

        List<String> createdAt = response.extract().path("data.findAll{it.price < 5.49}.createdAt");
        System.out.println("35. Find the createdAt for all products whose price < 5.49 = " + createdAt);

        List<String> cateNames = response.extract().path("data.findAll{it.name == 'Energizer - MAX Batteries AA (4-Pack)'}.categories.name");
        System.out.println("36. Find the name of all categories Where product name (Energizer - MAX Batteries AA (4-Pack)) = " +cateNames);

        List<String> manufacturers = response.extract().path("data.manufacturer");
        System.out.println("37. Find the manufacturer of all the products = " + manufacturers);

        List<String > images = response.extract().path("data.findAll{it.manufacturer == 'Energizer'}.image");
        System.out.println("38. Find the image of products whose manufacturer is (Energizer) = " + images);

        List<String> createdAt1 = response.extract().path("data.findAll{it.price > 5.99}.createdAt");
        System.out.println("39. Find the createdAt for all categories products whose price > 5.99 = " + createdAt1);

        List<String> urls = response.extract().path("data.url");
        System.out.println("40. Find the url of all the products = " + urls);
    }
}

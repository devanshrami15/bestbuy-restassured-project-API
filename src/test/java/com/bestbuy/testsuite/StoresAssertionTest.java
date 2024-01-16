package com.bestbuy.testsuite;

import com.bestbuy.testbase.TestBase;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class StoresAssertionTest extends TestBase {

   @Test
   public static void storeTest(){
      given()
              .when()
              .get("stores")
              .then().statusCode(200)
              // Verify the if the total is equal to 1561
              .body("total", equalTo(1561))
              // Verify the if the stores of limit is equal to 10
              .body("limit", equalTo(10))
              // Check the single ‘Name’ in the Array list (Inver Grove Heights)
              .body("data.name", hasItem("Inver Grove Heights"))
              // Check the multiple ‘Names’ in the ArrayList (Roseville, Burnsville, Maplewood)
              .body("data.name", hasItems("Roseville", "Burnsville", "Maplewood"))
              // Verify the storied=7 inside storeservices of the third store of second services
              .body("data[2].services[3].storeservices.storeId", equalTo(7))
              // Check hash map values ‘createdAt’ inside storeservices map where store name = Roseville
              .body("data[2].services.storeservices", everyItem(hasKey("createdAt")))
              // Verify the state = MN of forth store
              .body("data[3].state", equalTo("MN"))
              // Verify the store name = Rochester of 9th store
              .body("data[8].name", equalTo("Rochester"))
              // Verify the storeId = 11 for the 6th store
              .body("data[5].id", equalTo(11))
              // Verify the serviceId = 4 for the 7th store of forth service
              .body("data[6].services[3].storeservices.serviceId", equalTo(4));
   }
}

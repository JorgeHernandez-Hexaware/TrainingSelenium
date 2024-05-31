import static org.junit.Assert.assertEquals;

import java.util.List;

import org.json.JSONObject;
import org.junit.Test;
import static io.restassured.RestAssured.*;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class FakeStoreAPI {
	
	@Test
	public void validateProductListSize() {
		Response productsResponse = 
			given()
				.contentType(ContentType.JSON)
				.when()
					.get("https://fakestoreapi.com/products");
		
		assertEquals(200, productsResponse.getStatusCode());

		System.out.println(productsResponse.body().asPrettyString());

		List<Object> products = productsResponse.jsonPath().getList("$");

		assertEquals(20, products.size());
	}

	@Test
	public void validateFourRatingProdcts(){
		Response productsResponse = 
			given()
			.contentType(ContentType.JSON)
			.when()
				.get("https://fakestoreapi.com/products");
		
		assertEquals(200, productsResponse.getStatusCode());

		List<Object> products = productsResponse.jsonPath().getList("findAll {it.rating.rate >= 4}");
		
		assertEquals(7, products.size());
	}

	@Test
	public void validatePriceById(){
		Response productsResponse = 
			given()
			.contentType(ContentType.JSON)
			.when()
			.get("https://fakestoreapi.com/products/10");
		
		assertEquals(200, productsResponse.getStatusCode());

		JsonPath result =  productsResponse.getBody().jsonPath();
		
		Object obj = result.getJsonObject("price");

		assertEquals(109, obj);
	}

	@Test
	public void validateRatingCountById(){
		Response productsResponse = 
			given()
			.contentType(ContentType.JSON)
			.when()
			.get("https://fakestoreapi.com/products/10");
		
		assertEquals(200, productsResponse.getStatusCode());

		JsonPath result =  productsResponse.getBody().jsonPath();
		
		Object obj = result.getJsonObject("rating.count");

		assertEquals(470, obj);
	}

	@Test
	public void validateCategoryById(){
		Response productsResponse = 
			given()
			.contentType(ContentType.JSON)
			.when()
			.get("https://fakestoreapi.com/products/10");
		
		assertEquals(200, productsResponse.getStatusCode());

		JsonPath result =  productsResponse.getBody().jsonPath();
		
		Object obj = result.getJsonObject("category");

		assertEquals("electronics", obj);
	}
}
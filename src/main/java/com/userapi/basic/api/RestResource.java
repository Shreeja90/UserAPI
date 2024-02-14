package com.userapi.basic.api;

import static com.userapi.basic.api.Route.BASE_PATH;
import static io.restassured.RestAssured.given;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class RestResource {
	
    public static Response post(String path, Object requestUserApi){
        return given(getRequestSpec()).
                body(requestUserApi).
                auth().basic("Numpy@gmail.com","tim123").
                when().post(path).
                then().spec(getResponseSpec()).
                statusCode(201).
                extract().response();
    }
    
    public static Response get(String path){
        return given(getRequestSpec()).
        		auth().basic("Numpy@gmail.com","tim123").
        		when().get(path).
        		then().spec(getResponseSpec()).
                extract().
                response();
    }

    public static RequestSpecification getRequestSpec(){
        return new RequestSpecBuilder().
                //setBaseUri(System.getProperty("BASE_URI")).
        		setBaseUri("https://userapi-8877aadaae71.herokuapp.com").
                setBasePath(BASE_PATH).
                setContentType(ContentType.JSON).
                addFilter(new AllureRestAssured()).
                log(LogDetail.ALL).
                build();
    }
    
    public static ResponseSpecification getResponseSpec(){
        return new ResponseSpecBuilder().
                log(LogDetail.ALL).
                build();
    }
}

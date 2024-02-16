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
	
    public static Response post(String path, Object requestUserApi, int sCode){
        return given(getRequestSpec()).
                body(requestUserApi).
                auth().basic("Numpy@gmail.com","tim123").
                when().post(path).
                then().spec(getResponseSpec()).
                statusCode(sCode).
                extract().response();
    }
    
    public static Response get(String path, int sCode){
        return given(getRequestSpec()).
        		auth().basic("Numpy@gmail.com","tim123").
        		when().get(path).
        		then().spec(getResponseSpec()).
        		statusCode(sCode).
                extract().
                response();
    }
    
    public static Response update(String path, Object requestUserApi,int sCode ){
        return given(getRequestSpec()).
        		auth().basic("Numpy@gmail.com","tim123").
                body(requestUserApi).
                when().put(path).
                then().spec(getResponseSpec()).
                statusCode(sCode).
                extract().
                response();
    }
    
    public static Response delete(String path, int sCode){
        return given(getRequestSpec()).
        		auth().basic("Numpy@gmail.com","tim123").
        		when().delete(path).
        		then().spec(getResponseSpec()).
        		statusCode(sCode).
                extract().
                response();
    }
    

    public static RequestSpecification getRequestSpec(){
        return new RequestSpecBuilder().
                //setBaseUri(System.getProperty("BASE_URI")).
        		setBaseUri("https://userapi-8877aadaae71.herokuapp.com").
                setBasePath(BASE_PATH).
                setContentType(ContentType.JSON). //this line is used to set the content type to json
                addFilter(new AllureRestAssured()).//this line is to generate allure results
                log(LogDetail.ALL). //logger
                build(); //this method is from rest assured used to build the header for the request
    }
    //this method is written to log and build the response
    public static ResponseSpecification getResponseSpec(){
        return new ResponseSpecBuilder().
                log(LogDetail.ALL).
                build();
        
    }
    

}

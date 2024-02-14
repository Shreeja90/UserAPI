package com.userapi.tests;

import static com.userapi.basic.api.Route.USERS;
import static com.userapi.basic.api.Route.NEWUSERS;

import java.lang.reflect.Method;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.io.File;

import com.userapi.basic.api.RestResource;

public class Test01 {
	
	
    @BeforeMethod
    public void beforeMethod(Method m){
        System.out.println("STARTING TEST: " + m.getName());
    }
    
    @Test
    public void getRequest() {
    	RestResource.get(USERS);
    	File jsonData = new File("/Users/shreeeja/eclipse-workspace/RestAssured_Testng/src/main/java/com/userapi/tests/user.json");
    	RestResource.post(NEWUSERS,jsonData);
    }

}

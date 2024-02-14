package com.userapi.tests;

import static com.userapi.basic.api.Route.USERS;
import static com.userapi.basic.api.Route.NEWUSERS;
import static com.userapi.basic.api.Route.DELETEUSER;

import java.io.IOException;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.json.JSONObject;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.userapi.basic.api.RestResource;

public class Test01 {
	
	
    @BeforeMethod
    public void beforeMethod(Method m){
        System.out.println("STARTING TEST: " + m.getName());
    }
    
    @Test(groups = {"users", "smoke"})	
    public void getRequest() throws IOException {
    	RestResource.get(USERS, 200);
    }
    
    @Test(groups = {"create", "smoke"})	
    public void postCreateUser() throws IOException
    {
    	String content = new String(Files.readAllBytes(Paths.get("/Users/shreeeja/eclipse-workspace/UserAPI/src/main/java/com/userapi/tests/user.json")));
        // Parse JSON string into JSONObject
        JSONObject jsonObject = new JSONObject(content);
    	RestResource.post(NEWUSERS,jsonObject.toString(), 201);
    }
    
    @Test(groups = {"delete"})	
    public void deleteuser() {
    	RestResource.delete(DELETEUSER +"/7612", 200);
    	
    }

}

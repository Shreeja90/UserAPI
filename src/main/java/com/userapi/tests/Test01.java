package com.userapi.tests;

import static com.userapi.basic.api.Route.DELETEUSER;
import static com.userapi.basic.api.Route.NEWUSERS;
import static com.userapi.basic.api.Route.USERS;
import static com.userapi.basic.api.Route.PUTUSER;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.userapi.basic.api.RestResource;

public class Test01 {
	
	
    @BeforeMethod
    public void beforeMethod(Method m){
        System.out.println("STARTING TEST: " + m.getName()); //is used to define the test name before every test 
    }
    
    @Test(groups = {"users", "smoke"}, priority=1)	
    public void getRequest() throws IOException {
    	RestResource.get(USERS, 200);
    }
    
    @Test(groups = {"create", "smoke"}, priority=2)	
    public void postCreateUser() throws IOException
    {
    	String content = new String(Files.readAllBytes(Paths.get("/Users/shreeeja/eclipse-workspace/UserAPI/src/main/java/com/userapi/tests/user.json")));
        // Parse JSON string into JSONObject
        JSONObject jsonObject = new JSONObject(content);
    	RestResource.post(NEWUSERS,jsonObject.toString(), 201);
    }
    
    @DataProvider(name = "jsonDataProvider")
    public Object[][] jsonDataProvider() throws IOException {
        String jsonContent = new String(Files.readAllBytes(new File("/Users/shreeeja/eclipse-workspace/UserAPI/src/main/java/com/userapi/tests/negativePostRequest.json").toPath()));
        JSONArray jsonArray = new JSONArray(jsonContent);
        Object[][] data = new Object[jsonArray.length()][1];
        for (int i = 0; i < jsonArray.length(); i++) {
            data[i][0] = jsonArray.get(i);
        }
        return data;
    }
    @Test(dataProvider = "jsonDataProvider", groups= "smoke", priority=3)
    public void testWithJsonData(Object jsonData) {
    	RestResource.post(NEWUSERS,jsonData.toString(), 400);;
    }
    
    
    @DataProvider(name = "jsonDataProvider1")
    public Object[][] jsonDataProvider1() throws IOException {
        String jsonContent = new String(Files.readAllBytes(new File("/Users/shreeeja/eclipse-workspace/UserAPI/src/main/java/com/userapi/tests/positivePostRequest.json").toPath()));
        //String jsonContent = new String(Files.readAllBytes(new File("/Users/shreeeja/eclipse-workspace/UserAPI/src/main/java/com/userapi/tests/negativePostRequest.json").toPath()));
        JSONArray jsonArray = new JSONArray(jsonContent);
        Object[][] data = new Object[jsonArray.length()][1];
        for (int i = 0; i < jsonArray.length(); i++) {
            data[i][0] = jsonArray.get(i);
        }
        return data;
    }
        
     @Test(dataProvider = "jsonDataProvider1", groups= "smoke", priority=4)
      public void positivetestWithJsonData(Object jsonPositiveData) {
        	RestResource.post(NEWUSERS,jsonPositiveData.toString(), 201);;
     }
     
     @DataProvider(name = "jsonDataProvider2")
     public Object[][] jsonDataProvider2() throws IOException {
         String jsonContent = new String(Files.readAllBytes(new File("/Users/shreeeja/eclipse-workspace/UserAPI/src/main/java/com/userapi/tests/putRequest.json").toPath()));
         JSONArray jsonArray = new JSONArray(jsonContent);
         Object[][] data = new Object[jsonArray.length()][1];
         for (int i = 0; i < jsonArray.length(); i++) {
             data[i][0] = jsonArray.get(i);
         }
         return data;
     }
    
     @Test(dataProvider = "jsonDataProvider2", groups= "smoke", priority=5)
     public void putTestWithJsonData(Object jsonPutData) {
     	RestResource.update(PUTUSER +"Shreeja",jsonPutData.toString(), 400);;
     }
     
     
    @Test	(groups= "smoke", priority=6)
    public void deleteuser() {
    	RestResource.delete(DELETEUSER +"Shreeja", 200);
    	RestResource.delete(DELETEUSER +"Shrija", 200);
    	RestResource.delete(DELETEUSER +"Abhi", 200);
    	RestResource.delete(DELETEUSER +"Anaishu", 200);
    	
    }

}

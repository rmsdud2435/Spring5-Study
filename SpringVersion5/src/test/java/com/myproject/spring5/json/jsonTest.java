package com.myproject.spring5.json;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class jsonTest {

	public static void main(String[] args) throws JsonParseException, JsonMappingException, IOException {
		// TODO Auto-generated method stub
		HashMap paramMap = new HashMap();
		paramMap.put("firstName", "Geun-Young");
		paramMap.put("lastName", "Kim");
		paramMap.put("age", 21);
		System.out.println("paramMap looks like " + paramMap.toString());
		
		ObjectMapper mapper = new ObjectMapper();
		mapper.writeValue(new File("C:/Users/gykim/Desktop/data2.json"),paramMap); //첫 인자에 File, URL, InputStream이 들어갈 수 있다.
		
		JsonNode jsonNode = mapper.readValue(new File("C:/Users/gykim/Desktop/data.json"), JsonNode.class); //첫 인자에 File, Writer, OutputStream이 들어갈 수 있다. 
		
		System.out.println("jsonNode from File looks like " + jsonNode.toString());
		System.out.println("String values is " + jsonNode.get("firstName") + ", String asText Value is " + jsonNode.get("firstName").asText());
		System.out.println("Int values is " + jsonNode.get("age") + ", Int asText Value is " + jsonNode.get("age").asText());
		
		JsonNode jsonNode2 = mapper.convertValue(paramMap, JsonNode.class);
		System.out.println("jsonNode from HashMap looks like " + jsonNode2.toString());
		
		HashMap paramMap2 =  mapper.convertValue(jsonNode2, HashMap.class);
		System.out.println("HashMap from jsonNode looks like " + paramMap2.toString());
		
		String firstName = (String) paramMap2.get("firstName");
		System.out.println("firstName from HashMap looks like " + firstName);
		
		JsonNode jsonNode3 = mapper.convertValue(paramMap2, JsonNode.class);
		System.out.println("jsonNode from HashMap looks like " + jsonNode3.toString());
		
		ObjectNode objectNode = mapper.convertValue(jsonNode3, ObjectNode.class);
		System.out.println("objectNode from jsonNode looks like " + objectNode.toString());
		
		JsonNode jsonNode4 = mapper.convertValue(objectNode, JsonNode.class);
		System.out.println("jsonNode from objectNode looks like " + jsonNode4.toString());
		
		ObjectNode objectNode2 = mapper.createObjectNode();
		objectNode2.put("firstName", "Gil-dong");
		objectNode2.put("LastName", "Hong");
		objectNode2.put("age", 243);	
		System.out.println("objectNode looks like " + objectNode2.toString());
		
	}

}

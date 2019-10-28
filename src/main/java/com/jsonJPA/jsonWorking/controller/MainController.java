package com.jsonJPA.jsonWorking.controller;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import org.apache.tomcat.util.json.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jsonJPA.jsonWorking.Entity.Auto;
import com.jsonJPA.jsonWorking.handler.PojoMergeHandler;
import com.jsonJPA.jsonWorking.repository.AutoRepository;
import com.jsonJPA.jsonWorking.valueObj.PayLoad;

@RestController
@RequestMapping("/v1")
public class MainController {

	@Autowired
	private AutoRepository autoRepository;
	
	@PostMapping("/aggregate")
	public List<Auto> updateTable(@RequestBody Auto autoTBL) throws JsonParseException, JsonMappingException, IOException {
		List<Auto> autoDetails = autoRepository.getJsonFromAutoTBL(autoTBL.getC_Id(), autoTBL.getSme_Name());
		System.out.println("autoDetails::::"+autoDetails);
		Map<String, Object> FinalMap = new HashMap<String, Object>();
		List<PayLoad> myList = new ArrayList<PayLoad>();
		ObjectMapper objectMapper = new ObjectMapper();
		PayLoad finalPayLoad = new PayLoad();
		
		for(int i=0;i<autoDetails.size();i++) {
			ObjectMapper mapper = new ObjectMapper();
			Map<String, Object> map = new HashMap<String, Object>();
			Auto auto = autoDetails.get(i);
			System.out.println("Hi:::"+auto.getJsonAuto());
			
			
			mapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
			myList.add(mapper.readValue(auto.getJsonAuto(), PayLoad.class));
			
			System.out.println("jsonId::"+myList);
			
		}
		
		PojoMergeHandler pojoMergeHandler = new PojoMergeHandler();
		
		for(int i=0;i<myList.size();i++) {
			finalPayLoad = pojoMergeHandler.compareAndMergePojo(finalPayLoad, myList.get(i));
		}
		
		
		String jsonStr = objectMapper.writeValueAsString(myList.get(0)); 
		System.out.println("jsonStr::"+jsonStr);
 		
		return autoDetails;
	}
}

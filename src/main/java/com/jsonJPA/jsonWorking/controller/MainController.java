package com.jsonJPA.jsonWorking.controller;


import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jsonJPA.jsonWorking.Entity.Auto;
import com.jsonJPA.jsonWorking.constants.RestConstants;
import com.jsonJPA.jsonWorking.handler.PojoMergeHandler;
import com.jsonJPA.jsonWorking.repository.AutoRepository;
import com.jsonJPA.jsonWorking.valueObj.IntermediateJson;
import com.jsonJPA.jsonWorking.valueObj.PayLoad;
import com.jsonJPA.jsonWorking.valueObj.ResponceInnerStr;
import com.jsonJPA.jsonWorking.valueObj.aggregated.InputFromIntermediate;

@RestController
@RequestMapping("/v1")
public class MainController {

	@Autowired
	private AutoRepository autoRepository;
	
	@PostMapping("/aggregate")
	public List<Auto> updateTable(@RequestBody Auto autoTBL) throws JsonParseException, JsonMappingException, IOException {
		
		String rule1 = RestConstants.RULE1;
		String rule2 = RestConstants.RULE2;
		
		List<Auto> autoDetails = autoRepository.getJsonFromAutoTBL(autoTBL.getC_Id(), autoTBL.getSme_Name());
		System.out.println("autoDetails::::"+autoDetails);
		ObjectMapper objectMapper = new ObjectMapper();
		Map<Integer, PayLoad> map1 = new HashMap<Integer, PayLoad>();
		
		
		for(int i=0;i<autoDetails.size();i++) {
			ObjectMapper mapper = new ObjectMapper();
			Auto auto = autoDetails.get(i);
			System.out.println("Hi:::"+auto.getJsonAuto());
			
			
			mapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
			
			map1.put(auto.getPk_Id(), mapper.readValue(auto.getJsonAuto(), PayLoad.class));
			
			System.out.println("jsonId::"+map1);
			
		}
		
		IntermediateJson[] intermediateJsons = new IntermediateJson[map1.size()];
		int j=0;
		for (Entry<Integer, PayLoad> entry : map1.entrySet()) {
			IntermediateJson intermediateJson = new IntermediateJson();
			intermediateJson.setAorCode(entry.getKey());
			intermediateJson.setPidate(entry.getValue());
			intermediateJsons[j] = intermediateJson;
			j++;
		}
		
		
		PojoMergeHandler pojoMergeHandler = new PojoMergeHandler();
		List<InputFromIntermediate> aggList = new ArrayList<InputFromIntermediate>();
		for(int i=0;i<intermediateJsons.length;i++) {
			aggList.add(pojoMergeHandler.minimizePojo(intermediateJsons[i]));
		}
		
		String jsonStr = objectMapper.writeValueAsString(aggList); 
		System.out.println("jsonStr::"+jsonStr);
		
		List<ResponceInnerStr> resposeInside = new ArrayList<ResponceInnerStr>();
		Map<String, Object> respose = new HashMap<String, Object>();
		
		resposeInside.add(new ResponceInnerStr("54654"));
		
		respose.put("responceDetails", resposeInside);
		String responseStr= objectMapper.writeValueAsString(respose); 
		System.out.println("responseStr::::::"+responseStr);
 		
		return autoDetails;
	}
	
	
	
}

package com.jsonJPA.jsonWorking.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.BooleanNode;
import com.fasterxml.jackson.databind.node.IntNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.JsonNodeType;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.TextNode;
import com.fasterxml.jackson.databind.node.ValueNode;
import com.jsonJPA.jsonWorking.Entity.Auto;
import com.jsonJPA.jsonWorking.constants.RestConstants;
import com.jsonJPA.jsonWorking.handler.PojoMergeHandler;
import com.jsonJPA.jsonWorking.repository.AutoRepository;
import com.jsonJPA.jsonWorking.valueObj.IntermediateJson;
import com.jsonJPA.jsonWorking.valueObj.PayLoad;
import com.jsonJPA.jsonWorking.valueObj.aggregated.InputFromIntermediate;
import com.jsonJPA.jsonWorking.valueObj.response.AddPhone;
import com.jsonJPA.jsonWorking.valueObj.response.FirstName;
import com.jsonJPA.jsonWorking.valueObj.response.Responce;

@RestController
@RequestMapping("/v1")
public class MainController {

	@Autowired
	private AutoRepository autoRepository;

	@PostMapping("/aggregate")
	public List<Auto> updateTable(@RequestBody Auto autoTBL)
			throws JsonParseException, JsonMappingException, IOException {

		String rule1 = RestConstants.RULE1;
		String rule2 = RestConstants.RULE2;

		List<Auto> autoDetails = autoRepository.getJsonFromAutoTBL(autoTBL.getC_Id(), autoTBL.getSme_Name());
		System.out.println("autoDetails::::" + autoDetails);
		ObjectMapper objectMapper = new ObjectMapper();
		Map<Integer, PayLoad> map1 = new HashMap<Integer, PayLoad>();

		ArrayNode arrayNode = objectMapper.createArrayNode();
		// arrayNode.add(firstJsonNode);
		// arrayNode.add(secondJsonNode);

		for (int i = 0; i < autoDetails.size(); i++) {
			ObjectMapper mapper = new ObjectMapper();
			Auto auto = autoDetails.get(i);
			System.out.println("Hi:::" + auto.getJsonAuto());

			mapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);

			arrayNode.add(auto.getJsonAuto());

			map1.put(auto.getPk_Id(), mapper.readValue(auto.getJsonAuto(), PayLoad.class));

			System.out.println("jsonId::" + map1);

		}

		IntermediateJson[] intermediateJsons = new IntermediateJson[map1.size()];
		int j = 0;
		for (Entry<Integer, PayLoad> entry : map1.entrySet()) {
			IntermediateJson intermediateJson = new IntermediateJson();
			intermediateJson.setAorCode(entry.getKey());
			intermediateJson.setPidate(entry.getValue());
			intermediateJsons[j] = intermediateJson;
			j++;
		}

		PojoMergeHandler pojoMergeHandler = new PojoMergeHandler();
		List<InputFromIntermediate> aggList = new ArrayList<InputFromIntermediate>();
		for (int i = 0; i < intermediateJsons.length; i++) {
			aggList.add(pojoMergeHandler.minimizePojo(intermediateJsons[i]));
		}

		objectMapper.setSerializationInclusion(Include.NON_NULL);
		objectMapper.setSerializationInclusion(Include.NON_EMPTY);
			
		String jsonStr = objectMapper.writeValueAsString(aggList);
		System.out.println("jsonStr::" + jsonStr);

		
		//System.out.println("responseStr::::::" + responseStr);
		
		Responce responce = new Responce();
		List<InputFromIntermediate> inputFromIntermediate = new ArrayList<InputFromIntermediate>();
		
		/*
		 * String addDetails = getStringDetails(aggList);
		 * responce.setResponceDetails(addDetails.replace("\"", ""));
		 */
		
		Map<String, Object> respose = createResponse(aggList);
		objectMapper.setVisibility(PropertyAccessor.FIELD, Visibility.ANY);
		String responseStr = objectMapper.writeValueAsString(respose);
		System.out.println("JSONArray :: "+responseStr);

		return autoDetails;
	}

	public Map<String, Object> createResponse(List<InputFromIntermediate> aggList) {
		List resposeInside = new ArrayList();
		
		resposeInside.add(new AddPhone("54654"));
		resposeInside.add(new FirstName(aggList.get(0).getAggregatedPayload().getFirstName()));
		
		Map map = new HashMap<>();
		List policy = new ArrayList();
		
		Map map1 = new HashMap<>();
		map1.put("Yappale", "yemmale");
		policy.add(map1);
		map.put("policy", policy);
		resposeInside.add(map);
		Map<String, Object> respose = new HashMap<String, Object>();
		respose.put("pkId", String.valueOf(aggList.get(0).getPkId()));
		respose.put("responceDetails", resposeInside);
		return respose;
	}

	

}

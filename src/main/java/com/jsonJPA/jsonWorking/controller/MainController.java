package com.jsonJPA.jsonWorking.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
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
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.jsonJPA.jsonWorking.Entity.Auto;
import com.jsonJPA.jsonWorking.constants.RestConstants;
import com.jsonJPA.jsonWorking.handler.PojoMergeHandler;
import com.jsonJPA.jsonWorking.repository.AutoRepository;
import com.jsonJPA.jsonWorking.valueObj.IntermediateJson;
import com.jsonJPA.jsonWorking.valueObj.PayLoad;
import com.jsonJPA.jsonWorking.valueObj.aggregated.AggregatedPayload;
import com.jsonJPA.jsonWorking.valueObj.aggregated.Clm_fldsAgg;
import com.jsonJPA.jsonWorking.valueObj.aggregated.ImagingAgg;
import com.jsonJPA.jsonWorking.valueObj.aggregated.InputFromIntermediate;
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

		// System.out.println("responseStr::::::" + responseStr);

		Responce responce = new Responce();
		List<InputFromIntermediate> inputFromIntermediate = new ArrayList<InputFromIntermediate>();

		/*
		 * String addDetails = getStringDetails(aggList);
		 * responce.setResponceDetails(addDetails.replace("\"", ""));
		 */

		List respose = createResponse(aggList);
		objectMapper.setVisibility(PropertyAccessor.FIELD, Visibility.ANY);
		String responseStr = objectMapper.writeValueAsString(respose);
		System.out.println("JSONArray :: " + responseStr);

		return autoDetails;
	}

	List createResponse(List<InputFromIntermediate> aggList) {

		List outerloop = new ArrayList<>();

		for (int i = 0; i < aggList.size(); i++) {
			List piiData = new ArrayList();

			AggregatedPayload aggregatedPayload = aggList.get(i).getAggregatedPayload();
			Set<ImagingAgg> imaging = aggregatedPayload.getImaging();
			List<ImagingAgg> imagingAggList = imaging.stream().collect(Collectors.toList());

			piiDataMethods(piiData, aggregatedPayload);

			List imgAggOuter = new ArrayList<>();
			for (int j = 0; j < imagingAggList.size(); j++) {
				ImagingAgg imagingAggOrg = imagingAggList.get(j);
				Set<Clm_fldsAgg> clm_fldsAggSET = imagingAggOrg.getClm_flds();
				List<Clm_fldsAgg> clm_fldsAggOrg = clm_fldsAggSET.stream().collect(Collectors.toList());
				List imgAddAllValues = new ArrayList<>();
				List imgAggInner = new ArrayList<>();

				setImgValues(imagingAggList, j, imgAddAllValues);

				List clmfieldListORG = claimFieldExtractor(clm_fldsAggOrg);
				Map<String, Object> clmFieldMap = new HashMap<>();
				clmFieldMap.put("claimFields", clmfieldListORG);
				imgAddAllValues.add(clmFieldMap);
				imgAggInner.add(imgAddAllValues);

				Map<String, Object> clmFieldDataMap = new HashMap<>();
				clmFieldDataMap.put("polclmdta", imgAggInner);
				imgAggOuter.add(clmFieldDataMap);
			}

			piiData.add(imgAggOuter);

			Map<String, Object> respose = new HashMap<String, Object>();
			respose.put("pkId", String.valueOf(aggList.get(i).getPkId()));
			respose.put("piiData", piiData);

			outerloop.add(respose);
		}

		return outerloop;
	}

	public void piiDataMethods(List piiData, AggregatedPayload aggregatedPayload) {
		Set<String> companies = aggregatedPayload.getCompany();
		if (companies != null && !companies.isEmpty()) {
			for (String str : companies) {
				piiData.add("{\"name\":\"" + str + "\",\"ctg\":\"" + "R" + "\"}");
			}
		}

		Set<String> address = aggregatedPayload.getAddress();
		if (address != null && !address.isEmpty()) {
			for (String str : address) {
				piiData.add("{\"name\":\"" + address + "\",\"ctg\":\"" + "R" + "\"}");
			}
		}

		Set<String> location = aggregatedPayload.getCompany();
		if (location != null && !location.isEmpty()) {
			for (String str : location) {
				piiData.add("{\"location\":\"" + str + "\",\"ctg\":\"" + "R" + "\"}");
			}
		}
	}

	public void setImgValues(List<ImagingAgg> imagingAggList, int j, List imgAddAllValues) {
		String policyNumber = imagingAggList.get(j).getPolicyNumber();
		if (policyNumber != null) {
			imgAddAllValues.add("{\"claim_nbr\":\"" + policyNumber + "\",\"ctg\":\"" + "R" + "\"}");
		}

		Set<String> names = imagingAggList.get(j).getName();
		if (names != null && !names.isEmpty()) {
			for (String str : names) {
				imgAddAllValues.add("{\"name\":\"" + str + "\",\"ctg\":\"" + "R" + "\"}");
			}
		}
	}

	public List claimFieldExtractor(List<Clm_fldsAgg> clm_fldsAggOrg) {
		List clmfieldListORG = new ArrayList<>();
		for (int k = 0; k < clm_fldsAggOrg.size(); k++) {
			List<String> clmfieldList = new ArrayList<>();

			String clmNumber = clm_fldsAggOrg.get(k).getClaim_nbr();
			clmfieldList.add("{\"claim_nbr\":\"" + clmNumber + "\",\"ctg\":\"" + "R" + "\"}");

			Set<String> setclmPeril = clm_fldsAggOrg.get(k).getClaim_peril();
			if (setclmPeril != null && !setclmPeril.isEmpty()) {
				for (String str : setclmPeril) {
					clmfieldList.add("{\"claim_peril\":\"" + str + "\",\"ctg\":\"" + "R" + "\"}");
				}
			}

			Set<String> setclmLossdate = clm_fldsAggOrg.get(k).getClaim_loss_dt();
			if (setclmLossdate != null && !setclmLossdate.isEmpty()) {
				for (String str : setclmLossdate) {
					clmfieldList.add("{\"claim_loss_dt\":\"" + str + "\",\"ctg\":\"" + "R" + "\"}");
				}
			}

			Set<String> setclmStatus = clm_fldsAggOrg.get(k).getClaim_status();
			if (setclmStatus != null && !setclmStatus.isEmpty()) {
				for (String str : setclmStatus) {
					clmfieldList.add("{\"claim_status\":\"" + str + "\",\"ctg\":\"" + "R" + "\"}");
				}
			}
			clmfieldListORG.add(clmfieldList);
		}
		return clmfieldListORG;
	}

}

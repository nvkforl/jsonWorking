package com.jsonJPA.jsonWorking.constants;

import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jsonJPA.jsonWorking.Entity.Rules;
import com.jsonJPA.jsonWorking.repository.RuleRepository;

@Component
public class RestConstants {

	public static String RULE1;
	public static String RULE2;
	public static String RULE3;
	public static String RULE4;
	
	
	@Autowired
	private RuleRepository ruleRepository;
	
	@PostConstruct
	public void getRuleValues() {
		List<Rules> list = ruleRepository.findAll();
		Map<String, String> map = new HashMap<String,String>();
		
		for(int i=0; i < list.size() ; i++) { 
             map.put(list.get(i).getRuleBox(), list.get(i).getValue());
         } 
		
		System.out.println("Maps:::"+map);
		
		RULE1 = map.get("rule1");
		RULE2 = map.get("rule2");
		RULE3 = map.get("rule3");
		RULE4 = map.get("rule4");
	}
	
}

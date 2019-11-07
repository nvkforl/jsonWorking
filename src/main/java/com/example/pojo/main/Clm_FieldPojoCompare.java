package com.example.pojo.main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import com.example.pojo.Clm_flds;
import com.example.pojo.Clm_fldsAgg;

public class Clm_FieldPojoCompare {

	public static void main(String[] args) throws Exception {

		Clm_flds policyDetails1 = new Clm_flds("1", "date1", "type1", "Due");

		Clm_flds policyDetails2 = new Clm_flds("1", "date1", "type1", "Pending");

		Clm_flds policyDetails3 = new Clm_flds("2", "date1", "type1", "Pending3");

		Clm_flds policyDetails4 = new Clm_flds("2", "date2", "type1", "Pending3");

		Clm_flds policyDetails5 = new Clm_flds("2", "date3", "type1", "Pending3");

		Clm_flds policyDetails6 = new Clm_flds("3", "date2", "type1", "Pending3");

	

		Set<Clm_flds> policyDetailsSET = new HashSet<Clm_flds>();

		policyDetailsSET.add(policyDetails1);
		policyDetailsSET.add(policyDetails5);
		policyDetailsSET.add(policyDetails3);
		policyDetailsSET.add(policyDetails4);
		policyDetailsSET.add(policyDetails2);
		policyDetailsSET.add(policyDetails6);

		//Set<Clm_fldsAgg> set = clm_fieldMethod(policyDetailsSET);
		Set<Clm_fldsAgg> set = clm_fieldMethodUsingMap(policyDetailsSET);

		System.out.println(set);
		System.out.println("size:" + set.size());

		Object[] array = set.toArray();
		System.out.println(Arrays.toString(array));

	}

	public static Set<Clm_fldsAgg> clm_fieldMethodUsingMap(Set<Clm_flds> policyDetailsSET) {
		
		Set<Clm_fldsAgg> set = new HashSet<Clm_fldsAgg>();

		List<Clm_flds> aList = policyDetailsSET.stream().collect(Collectors.toList());
		
		Map<String,List<Clm_flds>> claimFieldMap = new HashMap<String,List<Clm_flds>>();
		
		for(int i=0; i< aList.size();i++) {
			if(!claimFieldMap.containsKey(aList.get(i).getClaim_nbr())) {
				claimFieldMap.put(aList.get(i).getClaim_nbr(), new ArrayList<Clm_flds>());
			}
		}
		
		for(int i=0; i< aList.size();i++) {
			claimFieldMap.get(aList.get(i).getClaim_nbr()).add(aList.get(i));
		}
		
		for (Map.Entry<String,List<Clm_flds>> entry : claimFieldMap.entrySet()) {
			Clm_fldsAgg setAgg =mergeclaimFields(entry.getValue());
			set.add(setAgg);
		}
			
		
		return set;
	}

	public static Clm_fldsAgg mergeclaimFields(List<Clm_flds> listofValues) {
		Clm_fldsAgg clm_fldsAgg = new Clm_fldsAgg();
		
		Set<Clm_fldsAgg> clm_fldsAggSET = new HashSet<Clm_fldsAgg>();

		Set<String> claimLossDate = new HashSet<String>();
		Set<String> clamLossType = new HashSet<String>();
		Set<String> claimStatus = new HashSet<String>();
		
		clm_fldsAgg.setClaim_nbr((String)listofValues.get(0).getClaim_nbr());
		for(int i=0;i< listofValues.size();i++) {
			
			
			
			claimLossDate.add(listofValues.get(i).getClaim_loss_dt());
			clm_fldsAgg.setClaim_loss_dt(claimLossDate);
			claimLossDate.remove(null);
			
			clamLossType.add(listofValues.get(i).getClaim_peril());
			clm_fldsAgg.setClaim_peril(clamLossType);
			clamLossType.remove(null);
			
			claimStatus.add(listofValues.get(i).getClaim_status());
			clm_fldsAgg.setClaim_status(claimStatus);
			claimStatus.remove(null);
			
		}
		
		return clm_fldsAgg;
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/*
	 * private static Clm_fldsAgg mergeclaimFields(Set<Clm_flds> policyDetailsSET) {
	 * Clm_fldsAgg clm_fldsAgg = new Clm_fldsAgg(); List<Clm_flds> listofValues =
	 * policyDetailsSET.stream().collect(Collectors.toList());
	 * 
	 * Set<Clm_fldsAgg> clm_fldsAggSET = new HashSet<Clm_fldsAgg>();
	 * 
	 * Set<String> claimLossDate = new HashSet<String>(); Set<String> clamLossType =
	 * new HashSet<String>(); Set<String> claimStatus = new HashSet<String>();
	 * for(int i=0;i< listofValues.size();i++) {
	 * 
	 * clm_fldsAgg.setClaim_nbr((String)listofValues.get(i).getClaim_nbr());
	 * 
	 * claimLossDate.add(listofValues.get(i).getClaim_loss_dt());
	 * clm_fldsAgg.setClaim_loss_dt(claimLossDate); claimLossDate.remove(null);
	 * 
	 * clamLossType.add(listofValues.get(i).getClaim_peril());
	 * clm_fldsAgg.setClaim_peril(clamLossType); clamLossType.remove(null);
	 * 
	 * claimStatus.add(listofValues.get(i).getClaim_status());
	 * clm_fldsAgg.setClaim_status(claimStatus); claimStatus.remove(null);
	 * 
	 * }
	 * 
	 * return clm_fldsAgg; }
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * public static Set<Clm_fldsAgg> clm_fieldMethod(Set<Clm_flds>
	 * policyDetailsSET) { Set<Clm_fldsAgg> set = new HashSet<Clm_fldsAgg>();
	 * 
	 * List<Clm_flds> aList =
	 * policyDetailsSET.stream().collect(Collectors.toList());
	 * 
	 * for (int i = 0; i < aList.size(); i++) { boolean matchFound = false;
	 * Clm_fldsAgg policyDetailAgg = new Clm_fldsAgg();
	 * 
	 * for (int l = 1; l < aList.size(); l++) { if (i != l && aList.get(i) != null
	 * && aList.get(l) != null &&
	 * aList.get(i).getClaim_nbr().equalsIgnoreCase(aList.get(l).getClaim_nbr())) {
	 * 
	 * policyDetailAgg.setClaim_nbr(aList.get(i).getClaim_nbr());
	 * 
	 * Set<String> claimLossDate = new HashSet<String>();
	 * claimLossDate.add(aList.get(i).getClaim_loss_dt());
	 * claimLossDate.add(aList.get(l).getClaim_loss_dt());
	 * claimLossDate.remove(null); if (claimLossDate.isEmpty()) {
	 * claimLossDate.add(null); } policyDetailAgg.setClaim_loss_dt(claimLossDate);
	 * 
	 * Set<String> clamLossType = new HashSet<String>();
	 * clamLossType.add(aList.get(i).getClaim_peril());
	 * clamLossType.add(aList.get(l).getClaim_peril()); clamLossType.remove(null);
	 * if (clamLossType.isEmpty()) { clamLossType.add(null); }
	 * policyDetailAgg.setClaim_peril(clamLossType);
	 * 
	 * Set<String> claimStatus = new HashSet<String>();
	 * claimStatus.add(aList.get(i).getClaim_status());
	 * claimStatus.add(aList.get(l).getClaim_status()); claimStatus.remove(null); if
	 * (claimStatus.isEmpty()) { claimStatus.add(null); }
	 * policyDetailAgg.setClaim_status(claimStatus);
	 * 
	 * set.add(policyDetailAgg); matchFound = true; aList.set(l,null); } } if
	 * (matchFound) { aList.set(i,null); } matchFound = false;
	 * 
	 * }
	 * 
	 * for (int i = 0; i < aList.size(); i++) { if (aList.get(i) != null) {
	 * 
	 * Clm_fldsAgg policyDetailAgg = new Clm_fldsAgg();
	 * 
	 * policyDetailAgg.setClaim_nbr(aList.get(i).getClaim_nbr());
	 * 
	 * Set<String> claimLossDate = new HashSet<String>();
	 * claimLossDate.add(aList.get(i).getClaim_loss_dt());
	 * claimLossDate.remove(null); if (claimLossDate.isEmpty()) {
	 * claimLossDate.add(null); } policyDetailAgg.setClaim_loss_dt(claimLossDate);
	 * 
	 * Set<String> clamLossType = new HashSet<String>();
	 * clamLossType.add(aList.get(i).getClaim_peril()); clamLossType.remove(null);
	 * if (clamLossType.isEmpty()) { clamLossType.add(null); }
	 * policyDetailAgg.setClaim_peril(clamLossType);
	 * 
	 * Set<String> claimStatus = new HashSet<String>();
	 * claimStatus.add(aList.get(i).getClaim_status()); claimStatus.remove(null); if
	 * (claimStatus.isEmpty()) { claimStatus.add(null); }
	 * policyDetailAgg.setClaim_status(claimStatus);
	 * 
	 * set.add(policyDetailAgg); aList.set(i,null); } }
	 * 
	 * return set; }
	 */
	
	/*
	 * public static Set<Clm_fldsAgg> clm_fieldMethodStoreReturn(Set<Clm_flds>
	 * policyDetailsSET) {
	 * 
	 * 
	 * List<Clm_flds> aList =
	 * policyDetailsSET.stream().collect(Collectors.toList());
	 * 
	 * for (int i = 0; i < aList.size(); i++) { boolean matchFound = false;
	 * Clm_fldsAgg policyDetailAgg = new Clm_fldsAgg();
	 * 
	 * for (int l = 1; l < aList.size(); l++) { if (i != l && aList.get(i) != null
	 * && aList.get(l) != null &&
	 * aList.get(i).getClaim_nbr().equalsIgnoreCase(aList.get(l).getClaim_nbr())) {
	 * 
	 * policyDetailAgg.setClaim_nbr(aList.get(i).getClaim_nbr());
	 * 
	 * Set<String> claimLossDate = new HashSet<String>();
	 * claimLossDate.add(aList.get(i).getClaim_loss_dt());
	 * claimLossDate.add(aList.get(i).getClaim_loss_dt());
	 * claimLossDate.remove(null); if (claimLossDate.isEmpty()) {
	 * claimLossDate.add(null); } policyDetailAgg.setClaim_loss_dt(claimLossDate);
	 * 
	 * Set<String> clamLossType = new HashSet<String>();
	 * clamLossType.add(aList.get(i).getClaim_peril());
	 * clamLossType.add(aList.get(i).getClaim_peril()); clamLossType.remove(null);
	 * if (clamLossType.isEmpty()) { clamLossType.add(null); }
	 * policyDetailAgg.setClaim_peril(clamLossType);
	 * 
	 * Set<String> claimStatus = new HashSet<String>();
	 * claimStatus.add(aList.get(i).getClaim_status());
	 * claimStatus.add(aList.get(i).getClaim_status()); claimStatus.remove(null); if
	 * (claimStatus.isEmpty()) { claimStatus.add(null); }
	 * policyDetailAgg.setClaim_status(claimStatus);
	 * 
	 * set.add(policyDetailAgg); matchFound = true; aList.set(l,null); } } if
	 * (matchFound) { aList.set(i,null); }
	 * 
	 * }
	 * 
	 * for (int i = 0; i < aList.size(); i++) { if (aList.get(i) != null) {
	 * 
	 * Clm_fldsAgg policyDetailAgg = new Clm_fldsAgg();
	 * 
	 * policyDetailAgg.setClaim_nbr(aList.get(i).getClaim_nbr());
	 * 
	 * Set<String> claimLossDate = new HashSet<String>();
	 * claimLossDate.add(aList.get(i).getClaim_loss_dt());
	 * claimLossDate.remove(null); if (claimLossDate.isEmpty()) {
	 * claimLossDate.add(null); } policyDetailAgg.setClaim_loss_dt(claimLossDate);
	 * 
	 * Set<String> clamLossType = new HashSet<String>();
	 * clamLossType.add(aList.get(i).getClaim_peril()); clamLossType.remove(null);
	 * if (clamLossType.isEmpty()) { clamLossType.add(null); }
	 * policyDetailAgg.setClaim_peril(clamLossType);
	 * 
	 * Set<String> claimStatus = new HashSet<String>();
	 * claimStatus.add(aList.get(i).getClaim_status()); claimStatus.remove(null); if
	 * (claimStatus.isEmpty()) { claimStatus.add(null); }
	 * policyDetailAgg.setClaim_status(claimStatus);
	 * 
	 * set.add(policyDetailAgg); aList.set(i,null); } }
	 * 
	 * return set; }
	 */

	public static Set<Clm_fldsAgg> clm_fieldMethod(Set<Clm_fldsAgg> storing, Set<Clm_flds> policyDetails) {
		// TODO Auto-generated method stub
		return null;
	}

}

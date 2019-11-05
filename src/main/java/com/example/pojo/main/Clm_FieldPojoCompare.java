package com.example.pojo.main;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
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

		Set<Clm_fldsAgg> set = clm_fieldMethod(policyDetailsSET);

		System.out.println(set);
		System.out.println("size:" + set.size());

		Object[] array = set.toArray();
		System.out.println(Arrays.toString(array));

	}

	public static Set<Clm_fldsAgg> clm_fieldMethod(Set<Clm_flds> policyDetailsSET) {
		Set<Clm_fldsAgg> set = new HashSet<Clm_fldsAgg>();

		List<Clm_flds> aList = policyDetailsSET.stream().collect(Collectors.toList());

		for (int i = 0; i < aList.size(); i++) {
			boolean matchFound = false;
			Clm_fldsAgg policyDetailAgg = new Clm_fldsAgg();

			for (int l = 1; l < aList.size(); l++) {
				if (i != l && aList.get(i) != null && aList.get(l) != null
						&& aList.get(i).getClaim_nbr().equalsIgnoreCase(aList.get(l).getClaim_nbr())) {

					policyDetailAgg.setClaim_nbr(aList.get(i).getClaim_nbr());

					Set<String> claimLossDate = new HashSet<String>();
					claimLossDate.add(aList.get(i).getClaim_loss_dt());
					claimLossDate.add(aList.get(l).getClaim_loss_dt());
					claimLossDate.remove(null);
					if (claimLossDate.isEmpty()) {
						claimLossDate.add(null);
					}
					policyDetailAgg.setClaim_loss_dt(claimLossDate);

					Set<String> clamLossType = new HashSet<String>();
					clamLossType.add(aList.get(i).getClaim_peril());
					clamLossType.add(aList.get(l).getClaim_peril());
					clamLossType.remove(null);
					if (clamLossType.isEmpty()) {
						clamLossType.add(null);
					}
					policyDetailAgg.setClaim_peril(clamLossType);

					Set<String> claimStatus = new HashSet<String>();
					claimStatus.add(aList.get(i).getClaim_status());
					claimStatus.add(aList.get(l).getClaim_status());
					claimStatus.remove(null);
					if (claimStatus.isEmpty()) {
						claimStatus.add(null);
					}
					policyDetailAgg.setClaim_status(claimStatus);

					set.add(policyDetailAgg);
					matchFound = true;
					aList.set(l,null);
				}
			}
			if (matchFound) {
				aList.set(i,null);
			}
			matchFound = false;

		}

		for (int i = 0; i < aList.size(); i++) {
			if (aList.get(i) != null) {

				Clm_fldsAgg policyDetailAgg = new Clm_fldsAgg();

				policyDetailAgg.setClaim_nbr(aList.get(i).getClaim_nbr());

				Set<String> claimLossDate = new HashSet<String>();
				claimLossDate.add(aList.get(i).getClaim_loss_dt());
				claimLossDate.remove(null);
				if (claimLossDate.isEmpty()) {
					claimLossDate.add(null);
				}
				policyDetailAgg.setClaim_loss_dt(claimLossDate);

				Set<String> clamLossType = new HashSet<String>();
				clamLossType.add(aList.get(i).getClaim_peril());
				clamLossType.remove(null);
				if (clamLossType.isEmpty()) {
					clamLossType.add(null);
				}
				policyDetailAgg.setClaim_peril(clamLossType);

				Set<String> claimStatus = new HashSet<String>();
				claimStatus.add(aList.get(i).getClaim_status());
				claimStatus.remove(null);
				if (claimStatus.isEmpty()) {
					claimStatus.add(null);
				}
				policyDetailAgg.setClaim_status(claimStatus);

				set.add(policyDetailAgg);
				aList.set(i,null);
			}
		}

		return set;
	}
	
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

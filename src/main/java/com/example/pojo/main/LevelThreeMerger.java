package com.example.pojo.main;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.example.pojo.Clm_flds;
import com.example.pojo.Imaging;
import com.example.pojo.ImagingAgg;
import com.example.pojo.Payload;
import com.example.pojo.PayloadAgg;

public class LevelThreeMerger {

	public static void main(String[] args) {
		Clm_flds policyDetails1 = new Clm_flds("1", "date1", "type1", "Due");
		Clm_flds policyDetails2 = new Clm_flds("1", "date1", "type1", "Pending");
		Clm_flds policyDetails3 = new Clm_flds("2", "date1", "type1", "Pending3");
		Clm_flds policyDetails4 = new Clm_flds("2", "date2", "type1", "Pending3");
		Clm_flds policyDetails5 = new Clm_flds("3", "date2", "type1", "Pending3");

		Set<Clm_flds> clm_fldSet1 = new HashSet<Clm_flds>();
		clm_fldSet1.add(policyDetails1);
		clm_fldSet1.add(policyDetails2);
		clm_fldSet1.add(policyDetails3);
		clm_fldSet1.add(policyDetails4);
		clm_fldSet1.add(policyDetails5);

		Clm_flds policyDetails6 = new Clm_flds("11", "date1", "type1", "Due");
		Clm_flds policyDetails7 = new Clm_flds("11", "date1", "type1", "Pending");
		Clm_flds policyDetails8 = new Clm_flds("21", "date1", "type1", "Pending3");
		Clm_flds policyDetails9 = new Clm_flds("21", "date2", "type1", "Pending3");
		Clm_flds policyDetails10 = new Clm_flds("31", "date2", "type1", "Pending3");

		Set<Clm_flds> clm_fldSet2 = new HashSet<Clm_flds>();
		clm_fldSet2.add(policyDetails6);
		clm_fldSet2.add(policyDetails10);
		clm_fldSet2.add(policyDetails8);
		clm_fldSet2.add(policyDetails9);
		clm_fldSet2.add(policyDetails7);

		Imaging img1 = new Imaging("5555", "harish", "time1", "mysore", clm_fldSet1);
		Imaging img2 = new Imaging("5555", "Ramesh", "time2", null, clm_fldSet2);
		Imaging img3 = new Imaging("7777", "Ramesh", "time2", null, clm_fldSet2);

		Set<Imaging> imgArr = new HashSet<Imaging>();
		imgArr.add(img1);
		imgArr.add(img2);
		imgArr.add(img3);

		Set<String> relatives = new HashSet<String>();
		relatives.add("relatives1");
		relatives.add("relatives2");

		Set<String> namesOfRelatives = new HashSet<String>();
		namesOfRelatives.add("namesOfRelatives1");
		namesOfRelatives.add("namesOfRelatives2");
		Payload payload = new Payload("nitish", "krishna", "ssn1", relatives, namesOfRelatives, imgArr);
		Payload payload1 = new Payload("nitish2", "krishna2", "ssn2", relatives, namesOfRelatives, imgArr);

		Set<Payload> setThree = new HashSet<Payload>();
		setThree.add(payload);
		setThree.add(payload1);

		PayloadAgg set = threeLevelMerger(setThree);

		System.out.println("set:::" + set);
		//System.out.println("Size::" + set.size());
	}

	public static PayloadAgg threeLevelMerger(Set<Payload> setThreeSET) {
		Set<PayloadAgg> set = new HashSet<PayloadAgg>();
		List<Payload> aList = setThreeSET.stream().collect(Collectors.toList());

		PayloadAgg payloadAgg = new PayloadAgg();
		Set<String> firstName = new HashSet<String>();
		Set<String> lastName = new HashSet<String>();
		Set<String> ssn = new HashSet<String>();

		for (int a = 0; a < aList.size(); a++) {

			
			firstName.add(aList.get(a).getFirstname());
			firstName.remove(null);
			if (firstName.isEmpty()) {
				firstName.add(null);
			}
			payloadAgg.setFirstname(firstName);

			
			lastName.add(aList.get(a).getLastname());
			lastName.remove(null);
			if (lastName.isEmpty()) {
				lastName.add(null);
			}
			payloadAgg.setLastname(lastName);
			
			
			ssn.add(aList.get(a).getSsn());
			ssn.remove(null);
			if (ssn.isEmpty()) {
				ssn.add(null);
			}
			payloadAgg.setSsn(ssn);

			try {
				Set<String> relatives = aList.get(a).getRelatives();
				payloadAgg.setRelatives(relatives);
			}catch (Exception e) {
				payloadAgg.setRelatives(null);		
			}
			
			try {
				Set<String> namesRelatives = aList.get(a).getNamesOfRelatives();
				payloadAgg.setNamesOfRelatives(namesRelatives);
			}catch (Exception e) {
				payloadAgg.setNamesOfRelatives(null);		
			}
		}
		
		Set<Imaging> imgSet = new HashSet<Imaging>();
		for (int a = 0; a < aList.size(); a++) {
			imgSet.addAll(aList.get(a).getImaging());
		}

		Set<ImagingAgg> set1 = ImagingFieldPojoCompare.imagingAgg(imgSet);
		
		payloadAgg.setImaging(set1);
		
		return payloadAgg;
	}

}

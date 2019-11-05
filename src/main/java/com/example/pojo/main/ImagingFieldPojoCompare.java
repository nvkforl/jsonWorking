package com.example.pojo.main;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.example.pojo.Clm_flds;
import com.example.pojo.Clm_fldsAgg;
import com.example.pojo.Imaging;
import com.example.pojo.ImagingAgg;

public class ImagingFieldPojoCompare {

	public static void main(String[] args) {

		Clm_flds policyDetails1 = new Clm_flds("1", "date1", "type1", "Due");
		Clm_flds policyDetails2 = new Clm_flds("1", "date1", "type1", "Pending");
		Clm_flds policyDetails3 = new Clm_flds("2", "date1", "type1", "Pending3");
		Clm_flds policyDetails4 = new Clm_flds("2", "date2", "type1", "Pending3");
		Clm_flds policyDetails5 = new Clm_flds("3", "date2", "type1", "Pending3");

		Clm_flds[] clm_fld = new Clm_flds[5];
		clm_fld[0] = policyDetails1;
		clm_fld[1] = policyDetails5;
		clm_fld[2] = policyDetails3;
		clm_fld[3] = policyDetails4;
		clm_fld[4] = policyDetails2;

		Set<Clm_flds> clm_fldSet1 = Set.of(clm_fld);

		Clm_flds policyDetails6 = new Clm_flds("11", "date1", "type1", "Due");
		Clm_flds policyDetails7 = new Clm_flds("11", "date1", "type1", "Pending");
		Clm_flds policyDetails8 = new Clm_flds("21", "date1", "type1", "Pending3");
		Clm_flds policyDetails9 = new Clm_flds("21", "date2", "type1", "Pending3");
		Clm_flds policyDetails10 = new Clm_flds("31", "date2", "type1", "Pending3");

		Clm_flds[] clm_fld1 = new Clm_flds[5];
		clm_fld1[0] = policyDetails6;
		clm_fld1[1] = policyDetails10;
		clm_fld1[2] = policyDetails8;
		clm_fld1[3] = policyDetails9;
		clm_fld1[4] = policyDetails7;

		Set<Clm_flds> clm_fldSet2 = Set.of(clm_fld1);

		Imaging img1 = new Imaging("5555", "harish", "time1", "mysore", clm_fldSet1);
		Imaging img2 = new Imaging("5555", "Ramesh", "time2", null, null);
		Imaging img3 = new Imaging("7777", "Ramesh", "time2", null, clm_fldSet2);

		Imaging[] imgArr = new Imaging[3];
		imgArr[0] = img1;
		imgArr[1] = img2;
		imgArr[2] = img3;

		Set<Imaging> imgSet = new HashSet<Imaging>();
		imgSet.add(img1);
		imgSet.add(img2);
		imgSet.add(img3);

		// Set<ImagingAgg> set = imagingAgg(imgArr);
		Set<ImagingAgg> set = imagingAgg(imgSet);

		System.out.println("set:::" + set);
		System.out.println("Size::" + set.size());

	}

	public static Set<ImagingAgg> imagingAgg(Set<Imaging> imgSET) {
		Set<ImagingAgg> set = new HashSet<ImagingAgg>();
		List<Imaging> aList = imgSET.stream().collect(Collectors.toList());

		for (int a = 0; a < aList.size(); a++) {
			boolean imgFound = false;
			Set<String> name = new HashSet<String>();
			Set<String> time = new HashSet<String>();
			Set<String> location = new HashSet<String>();
			
			ImagingAgg imagingAgg = new ImagingAgg();
			for (int b = 0; b < aList.size(); b++) {
				if (a != b && aList.get(a) != null && aList.get(b) != null
						&& aList.get(a).getPolicyNumber().equalsIgnoreCase(aList.get(b).getPolicyNumber())) {
					
					imagingAgg.setPolicyNumber(aList.get(a).getPolicyNumber());
					
					
					name.add(aList.get(a).getName());
					name.add(aList.get(b).getName());
					name.remove(null);
					if (name.isEmpty()) {
						name.add(null);
					}
					imagingAgg.setName(name);

					
					time.add(aList.get(a).getTime());
					time.add(aList.get(b).getTime());
					time.remove(null);
					if (time.isEmpty()) {
						time.add(null);
					}
					imagingAgg.setTime(time);
					
					
					location.add(aList.get(a).getLocation());
					location.add(aList.get(b).getLocation());
					location.remove(null);
					if (location.isEmpty()) {
						location.add(null);
					}
					imagingAgg.setLocation(location);
					
					Set<Clm_flds> policyDetails = new HashSet<Clm_flds>();
					if(null != aList.get(a).getClm_flds()) {
						policyDetails.addAll(aList.get(a).getClm_flds());
					}
					if(null != aList.get(b).getClm_flds()) {
						policyDetails.addAll(aList.get(b).getClm_flds());
					}
					
					if (policyDetails != null && policyDetails.size() != 0) {
						Set<Clm_fldsAgg> clm_fldsAgg = Clm_FieldPojoCompare.clm_fieldMethod(policyDetails);
						imagingAgg.setClm_flds(clm_fldsAgg);
					}
					
					set.add(imagingAgg);
					imgFound = true;
					aList.set(b,null);
				}
			}

			if (imgFound) {
				aList.set(a,null);
			}
			imgFound = false;
		}

		for (int c = 0; c < aList.size(); c++) {
			if (aList.get(c) != null) {
				ImagingAgg imagingAgg = new ImagingAgg();

				imagingAgg.setPolicyNumber(aList.get(c).getPolicyNumber());

				Set<String> name = new HashSet<String>();
				name.add(aList.get(c).getName());
				if (name.isEmpty()) {
					name.add(null);
				}
				imagingAgg.setName(name);

				Set<String> time = new HashSet<String>();
				time.add(aList.get(c).getTime());
				if (time.isEmpty()) {
					time.add(null);
				}
				imagingAgg.setTime(time);

				Set<String> location = new HashSet<String>();
				location.add(aList.get(c).getLocation());
				if (location.isEmpty()) {
					location.add(null);
				}
				imagingAgg.setLocation(location);

				Set<Clm_flds> clm_flds = aList.get(c).getClm_flds();

				if (clm_flds != null) {

					Set<Clm_flds> policyDetails = new HashSet<Clm_flds>();
					policyDetails.addAll(aList.get(c).getClm_flds());

					if (policyDetails != null && policyDetails.size() != 0) {
						Set<Clm_fldsAgg> clm_fldsAgg = Clm_FieldPojoCompare.clm_fieldMethod(policyDetails);
						imagingAgg.setClm_flds(clm_fldsAgg);
					}
				}

				set.add(imagingAgg);
				//aList.remove(c);
				aList.set(c,null);
			}
		}
		
		return set;
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/*
	 * public static Set<ImagingAgg> imagingAgg(Imaging[] imgArr) { Set<ImagingAgg>
	 * set = new HashSet<ImagingAgg>();
	 * 
	 * for (int a = 0; a < imgArr.length; a++) { boolean imgFound = false;
	 * ImagingAgg imagingAgg = new ImagingAgg();
	 * 
	 * for (int b = 0; b < imgArr.length; b++) { if (a != b && imgArr[a] != null &&
	 * imgArr[b] != null && imgArr[a].getPolicyNumber() ==
	 * imgArr[b].getPolicyNumber()) {
	 * 
	 * imagingAgg.setPolicyNumber(imgArr[a].getPolicyNumber());
	 * 
	 * Set<String> name = new HashSet<String>(); name.add(imgArr[a].getName());
	 * name.add(imgArr[b].getName()); name.remove(null); if (name.isEmpty()) {
	 * name.add(null); } imagingAgg.setName(name);
	 * 
	 * Set<String> time = new HashSet<String>(); time.add(imgArr[a].getTime());
	 * time.add(imgArr[b].getTime()); time.remove(null); if (time.isEmpty()) {
	 * time.add(null); } imagingAgg.setTime(time);
	 * 
	 * Set<String> location = new HashSet<String>();
	 * location.add(imgArr[a].getLocation()); location.add(imgArr[b].getLocation());
	 * location.remove(null); if (location.isEmpty()) { location.add(null); }
	 * imagingAgg.setLocation(location);
	 * 
	 * Set<Clm_flds> clm_flds = imgArr[a].getClm_flds(); Set<Clm_flds> clm_flds1 =
	 * imgArr[b].getClm_flds();
	 * 
	 * int clm_fldsSize = 0; Iterator<Clm_flds> itr = null; if (clm_flds != null &&
	 * !clm_flds.isEmpty()) { clm_fldsSize = clm_flds.size(); itr =
	 * clm_flds.iterator(); }
	 * 
	 * int clm_fldsSize2 = 0; Iterator<Clm_flds> itr1 = null; if (clm_flds != null
	 * && !clm_flds.isEmpty()) { clm_fldsSize2 = clm_flds.size(); itr1 =
	 * clm_flds1.iterator(); }
	 * 
	 * Clm_flds[] policyDetails = new Clm_flds[clm_fldsSize + clm_fldsSize2];
	 * 
	 * int i = 0; while (itr.hasNext() && itr != null) { policyDetails[i] =
	 * itr.next(); i++; }
	 * 
	 * while (itr1.hasNext() && itr1 != null) { policyDetails[i] = itr1.next(); i++;
	 * }
	 * 
	 * if (policyDetails != null && policyDetails.length != 0) { Set<Clm_fldsAgg>
	 * clm_fldsAgg = Clm_FieldPojoCompare.clm_fieldMethod(policyDetails);
	 * imagingAgg.setClm_flds(clm_fldsAgg); }
	 * 
	 * set.add(imagingAgg); imgFound = true; imgArr[b] = null; } }
	 * 
	 * if (imgFound) { imgArr[a] = null; } }
	 * 
	 * for (int c = 0; c < imgArr.length; c++) { if (imgArr[c] != null) { ImagingAgg
	 * imagingAgg = new ImagingAgg();
	 * 
	 * imagingAgg.setPolicyNumber(imgArr[c].getPolicyNumber());
	 * 
	 * Set<String> name = new HashSet<String>(); name.add(imgArr[c].getName()); if
	 * (name.isEmpty()) { name.add(null); } imagingAgg.setName(name);
	 * 
	 * Set<String> time = new HashSet<String>(); time.add(imgArr[c].getTime()); if
	 * (time.isEmpty()) { time.add(null); } imagingAgg.setTime(time);
	 * 
	 * Set<String> location = new HashSet<String>();
	 * location.add(imgArr[c].getLocation()); if (location.isEmpty()) {
	 * location.add(null); } imagingAgg.setLocation(location);
	 * 
	 * Set<Clm_flds> clm_flds = imgArr[c].getClm_flds();
	 * 
	 * if (clm_flds != null) {
	 * 
	 * Clm_flds[] policyDetails = new Clm_flds[clm_flds.size()]; Iterator<Clm_flds>
	 * itr = clm_flds.iterator(); int i = 0; while (itr.hasNext()) {
	 * policyDetails[i] = itr.next(); i++; }
	 * 
	 * if (policyDetails != null && policyDetails.length != 0) { Set<Clm_fldsAgg>
	 * clm_fldsAgg = Clm_FieldPojoCompare.clm_fieldMethod(policyDetails);
	 * imagingAgg.setClm_flds(clm_fldsAgg); } }
	 * 
	 * set.add(imagingAgg); imgArr[c] = null; } }
	 * 
	 * for (int c = 0; c < imgArr.length; c++) { if (imgArr[c] != null) { ImagingAgg
	 * imagingAgg = new ImagingAgg();
	 * 
	 * imagingAgg.setPolicyNumber(imgArr[c].getPolicyNumber());
	 * 
	 * Set<String> name = new HashSet<String>(); name.add(imgArr[c].getName()); if
	 * (name.isEmpty()) { name.add(null); } imagingAgg.setName(name);
	 * 
	 * Set<String> time = new HashSet<String>(); time.add(imgArr[c].getTime()); if
	 * (time.isEmpty()) { time.add(null); } imagingAgg.setTime(time);
	 * 
	 * Set<String> location = new HashSet<String>();
	 * location.add(imgArr[c].getLocation()); if (location.isEmpty()) {
	 * location.add(null); } imagingAgg.setLocation(location);
	 * 
	 * Set<Clm_flds> clm_flds = imgArr[c].getClm_flds();
	 * 
	 * if (clm_flds != null) {
	 * 
	 * Clm_flds[] policyDetails = new Clm_flds[clm_flds.size()]; Iterator<Clm_flds>
	 * itr = clm_flds.iterator(); int i = 0; while (itr.hasNext()) {
	 * policyDetails[i] = itr.next(); i++; }
	 * 
	 * if (policyDetails != null && policyDetails.length != 0) { Set<Clm_fldsAgg>
	 * clm_fldsAgg = Clm_FieldPojoCompare.clm_fieldMethod(policyDetails);
	 * imagingAgg.setClm_flds(clm_fldsAgg); } }
	 * 
	 * set.add(imagingAgg); imgArr[c] = null; } } return set; }
	 */
}

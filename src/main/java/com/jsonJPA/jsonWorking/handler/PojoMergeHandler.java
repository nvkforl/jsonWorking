package com.jsonJPA.jsonWorking.handler;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

import com.jsonJPA.jsonWorking.valueObj.Clm_flds;
import com.jsonJPA.jsonWorking.valueObj.Imaging;
import com.jsonJPA.jsonWorking.valueObj.IntermediateJson;
import com.jsonJPA.jsonWorking.valueObj.aggregated.AggregatedPayload;
import com.jsonJPA.jsonWorking.valueObj.aggregated.Clm_fldsAgg;
import com.jsonJPA.jsonWorking.valueObj.aggregated.ImagingAgg;
import com.jsonJPA.jsonWorking.valueObj.aggregated.InputFromIntermediate;

public class PojoMergeHandler {

	/*
	 * public PayLoad compareAndMergePojo(PayLoad finalPayLoad, PayLoad
	 * initialPayLoad) {
	 * 
	 * String[] intialLamp = initialPayLoad.getLamp(); String[] finalLamp =
	 * finalPayLoad.getLamp(); if (intialLamp != null) { if (finalLamp == null) {
	 * finalLamp = intialLamp; } else { finalLamp =
	 * mergeAndRemoveDuplicatesFromStringArray(finalLamp, intialLamp); }
	 * finalPayLoad.setLamp(finalLamp); }
	 * 
	 * Imaging[] initialImaging = initialPayLoad.getImaging(); Imaging[]
	 * finalImaging = finalPayLoad.getImaging();
	 * 
	 * Imaging initialImg = null; Imaging finalImg = null;
	 * 
	 * if (finalImaging == null) { finalImaging = initialImaging; } else if
	 * (initialImaging != null) { for (int i = 0; i < initialImaging.length; i++) {
	 * initialImg = initialImaging[i]; for (int j = 0; j < finalImaging.length; j++)
	 * { finalImg = finalImaging[j];
	 * 
	 * if (initialImg.getName() != null) { if (finalImg.getName() == null) {
	 * finalImg.setName(initialImg.getName()); } else if
	 * (!containsIgnoreCase(finalImg.getName(), initialImg.getName())) {
	 * finalImg.setName(finalImg.getName() + "," + initialImg.getName()); } }
	 * 
	 * Shipping_address[] initialShippingAddress = initialImg.getShipping_address();
	 * Shipping_address[] finalShippingAddress = finalImg.getShipping_address();
	 * 
	 * for (int k = 0; k < initialShippingAddress.length; k++) { Shipping_address
	 * shipping = initialShippingAddress[k];
	 * 
	 * if (finalImg.getShipping_address() == null &&
	 * initialImg.getShipping_address() != null) { finalShippingAddress =
	 * initialShippingAddress; } else if (initialImg.getShipping_address() != null)
	 * { // finalShippingAddress[++index] = // // // initialShippingAddress[k];
	 * finalShippingAddress = add(finalShippingAddress, initialShippingAddress); } }
	 * finalImg.setShipping_address(finalShippingAddress); } finalImaging[i] =
	 * finalImg; } } finalPayLoad.setImaging(finalImaging);
	 * 
	 * return finalPayLoad; }
	 */

	/*
	 * public static Shipping_address[] add(Shipping_address[] arr,
	 * Shipping_address... elements) { Shipping_address[] tempArr = new
	 * Shipping_address[arr.length + elements.length]; System.arraycopy(arr, 0,
	 * tempArr, 0, arr.length);
	 * 
	 * for (int i = 0; i < elements.length; i++) tempArr[arr.length + i] =
	 * elements[i]; return tempArr; }
	 */

	public static boolean containsIgnoreCase(String str, String subString) {
		return str.toLowerCase().contains(subString.toLowerCase());
	}

	public String[] mergeAndRemoveDuplicatesFromStringArray(String[] a, String[] b) {
		String[] mergedArray = new String[a.length + b.length];
		int k = 0;
		for (String n : a) {
			mergedArray[k++] = n;
		}
		for (String n : b) {
			mergedArray[k++] = n;
		}

		LinkedHashSet<String> mergedSet = new LinkedHashSet<String>(Arrays.asList(mergedArray));

		String[] newArray = mergedSet.toArray(new String[mergedSet.size()]);

		return newArray;

	}

	public InputFromIntermediate minimizePojo(IntermediateJson intermediateJson) {

		InputFromIntermediate inputInter = new InputFromIntermediate();

		inputInter.setPkId(intermediateJson.getAorCode());

		AggregatedPayload aggPayload = new AggregatedPayload();

		aggPayload.setFirstName(intermediateJson.getPidate().getFirstName());
		aggPayload.setLastName(intermediateJson.getPidate().getLastName());

		Set<String> fulfillmentStr = new HashSet<String>(
				Arrays.asList(intermediateJson.getPidate().getFulfillmentText()));
		aggPayload.setFulfillmentText(fulfillmentStr);

		String lineAddress11 = intermediateJson.getPidate().getAddress_line1_1();
		String lineAddress21 = intermediateJson.getPidate().getAddress_line2_1();
		String state1 = intermediateJson.getPidate().getState_1();
		String zipCode1 = intermediateJson.getPidate().getZipcode_1();
		String country1 = intermediateJson.getPidate().getCountry_1();

		String address1 = (lineAddress11 == null ? "" : lineAddress11) + " "
				+ (lineAddress21 == null ? "" : lineAddress21) + " " + (state1 == null ? "" : state1) + " "
				+ (zipCode1 == null ? "" : zipCode1) + " " + (country1 == null ? "" : country1);

		String lineAddress12 = intermediateJson.getPidate().getAddress_line1_2();
		String lineAddress22 = intermediateJson.getPidate().getAddress_line2_2();
		String state2 = intermediateJson.getPidate().getState_2();
		String zipCode2 = intermediateJson.getPidate().getZipcode_2();
		String country2 = intermediateJson.getPidate().getCountry_2();

		String address2 = (lineAddress12 == null ? "" : lineAddress12) + " "
				+ (lineAddress22 == null ? "" : lineAddress22) + " " + (state2 == null ? "" : state2) + " "
				+ (zipCode2 == null ? "" : zipCode2) + " " + (country2 == null ? "" : country2);

		Set<String> address = new HashSet<String>();
		address.add(address1.trim());
		address.add(address2.trim());
		aggPayload.setAddress(address);

		Imaging[] imaging = intermediateJson.getPidate().getImaging();
		//ImagingAgg[] aggImaging = new ImagingAgg[intermediateJson.getPidate().getImaging().length];

		/*
		 * for (int i = 0; i < imaging.length; i++) {
		 * com.jsonJPA.jsonWorking.valueObj.aggregated.Imaging aggImg = new
		 * com.jsonJPA.jsonWorking.valueObj.aggregated.Imaging();
		 * 
		 * aggImg.setPolicyNumber(imaging[i].getPolicyNumber());
		 * aggImg.setName(imaging[i].getName());
		 * 
		 * com.jsonJPA.jsonWorking.valueObj.Clm_flds[] ClmFldsOrg =
		 * imaging[i].getClm_flds();
		 * com.jsonJPA.jsonWorking.valueObj.aggregated.Clm_flds[] aggClmFlds = new
		 * com.jsonJPA.jsonWorking.valueObj.aggregated.Clm_flds[ClmFldsOrg.length];
		 * 
		 * Set<com.jsonJPA.jsonWorking.valueObj.aggregated.Clm_flds> set =
		 * clm_fieldMethod(ClmFldsOrg);
		 * 
		 * aggImg.setClm_flds(set); aggImaging[i] = aggImg; }
		 */

		Set<ImagingAgg> set1 = imagingAgg(imaging);

		aggPayload.setImaging(set1);

		inputInter.setAggregatedPayload(aggPayload);

		return inputInter;
	}

	public static Set<Clm_fldsAgg> clm_fieldMethod(Clm_flds[] ClmFldsOrg) {
		Set<Clm_fldsAgg> set = new HashSet<Clm_fldsAgg>();

		for (int k = 0; k < ClmFldsOrg.length; k++) {

			boolean matchFound = false;
			Clm_fldsAgg policyDetailAgg = new Clm_fldsAgg();

			for (int l = 1; l < ClmFldsOrg.length; l++) {
				if (k != l && ClmFldsOrg[k] != null && ClmFldsOrg[l] != null
						&& ClmFldsOrg[k].getClaim_nbr().equalsIgnoreCase(ClmFldsOrg[l].getClaim_nbr())) {

					policyDetailAgg.setClaim_nbr(ClmFldsOrg[k].getClaim_nbr());

					Set<String> claimLossDate = new HashSet<String>();
					claimLossDate.add(ClmFldsOrg[k].getClaim_loss_dt());
					claimLossDate.add(ClmFldsOrg[l].getClaim_loss_dt());
					claimLossDate.remove(null);
					if (claimLossDate.isEmpty()) {
						claimLossDate.add(null);
					}
					policyDetailAgg.setClaim_loss_dt(claimLossDate);

					Set<String> clamLossType = new HashSet<String>();
					clamLossType.add(ClmFldsOrg[k].getClaim_peril());
					clamLossType.add(ClmFldsOrg[l].getClaim_peril());
					clamLossType.remove(null);
					if (clamLossType.isEmpty()) {
						clamLossType.add(null);
					}
					policyDetailAgg.setClaim_peril(clamLossType);

					Set<String> claimStatus = new HashSet<String>();
					claimStatus.add(ClmFldsOrg[k].getClaim_status());
					claimStatus.add(ClmFldsOrg[l].getClaim_status());
					claimStatus.remove(null);
					if (claimStatus.isEmpty()) {
						claimStatus.add(null);
					}
					policyDetailAgg.setClaim_status(claimStatus);

					set.add(policyDetailAgg);
					matchFound = true;
					ClmFldsOrg[l] = null;
				}
			}
			if (matchFound) {
				ClmFldsOrg[k] = null;
			}
		}

		for (int m = 0; m < ClmFldsOrg.length; m++) {
			if (ClmFldsOrg[m] != null) {

				Clm_fldsAgg policyDetailAgg = new Clm_fldsAgg();

				policyDetailAgg.setClaim_nbr(ClmFldsOrg[m].getClaim_nbr());

				Set<String> claimLossDate = new HashSet<String>();
				claimLossDate.add(ClmFldsOrg[m].getClaim_loss_dt());
				if (claimLossDate.isEmpty()) {
					claimLossDate.add(null);
				}
				policyDetailAgg.setClaim_loss_dt(claimLossDate);

				Set<String> clamLossType = new HashSet<String>();
				clamLossType.add(ClmFldsOrg[m].getClaim_peril());
				if (clamLossType.isEmpty()) {
					clamLossType.add(null);
				}
				policyDetailAgg.setClaim_peril(clamLossType);

				Set<String> claimStatus = new HashSet<String>();
				claimStatus.add(ClmFldsOrg[m].getClaim_status());
				if (claimStatus.isEmpty()) {
					claimStatus.add(null);
				}
				policyDetailAgg.setClaim_status(claimStatus);

				set.add(policyDetailAgg);
				ClmFldsOrg[m] = null;
			}
		}
		return set;
	}

	public static Set<ImagingAgg> imagingAgg(Imaging[] imgArr) {
		Set<ImagingAgg> set = new HashSet<ImagingAgg>();

		for (int a = 0; a < imgArr.length; a++) {
			boolean imgFound = false;
			ImagingAgg imagingAgg = new ImagingAgg();

			for (int b = 0; b < imgArr.length; b++) {
				if (a != b && imgArr[a] != null && imgArr[b] != null
						&& imgArr[a].getPolicyNumber() == imgArr[b].getPolicyNumber()) {

					imagingAgg.setPolicyNumber(imgArr[a].getPolicyNumber());

					Set<String> name = new HashSet<String>();
					name.add(imgArr[a].getName());
					name.add(imgArr[b].getName());
					name.remove(null);
					if (name.isEmpty()) {
						name.add(null);
					}
					imagingAgg.setName(name);

					Set<String> time = new HashSet<String>();
					time.add(imgArr[a].getTime());
					time.add(imgArr[b].getTime());
					time.remove(null);
					if (time.isEmpty()) {
						time.add(null);
					}
					imagingAgg.setTime(time);

					Set<String> location = new HashSet<String>();
					location.add(imgArr[a].getLocation());
					location.add(imgArr[b].getLocation());
					location.remove(null);
					if (location.isEmpty()) {
						location.add(null);
					}
					imagingAgg.setLocation(location);

					Clm_flds[] clm_flds = imgArr[a].getClm_flds();
					Clm_flds[] clm_flds1 = imgArr[b].getClm_flds();

					int clm_fldsSize = 0;
					if (clm_flds != null && clm_flds.length != 0) {
						clm_fldsSize = clm_flds.length;
					}

					int clm_fldsSize2 = 0;
					if (clm_flds != null && clm_flds1.length != 0) {
						clm_fldsSize2 = clm_flds1.length;
					}

					Clm_flds[] cmlField = new Clm_flds[clm_fldsSize + clm_fldsSize2];

					int i = 0;
					if (clm_flds != null && clm_flds.length != 0) {
						for (Clm_flds clmFlds : clm_flds) {
							cmlField[i] = clm_flds[i];
							i++;
						}
					}

					if (cmlField != null && cmlField.length != 0) {
						Set<Clm_fldsAgg> clm_fldsAgg = clm_fieldMethod(cmlField);
						imagingAgg.setClm_flds(clm_fldsAgg);
					}

					set.add(imagingAgg);
					imgFound = true;
					imgArr[b] = null;
				}
			}

			if (imgFound) {
				imgArr[a] = null;
			}
		}

		for (int c = 0; c < imgArr.length; c++) {
			if (imgArr[c] != null) {
				ImagingAgg imagingAgg = new ImagingAgg();

				imagingAgg.setPolicyNumber(imgArr[c].getPolicyNumber());

				Set<String> name = new HashSet<String>();
				name.add(imgArr[c].getName());
				if (name.isEmpty()) {
					name.add(null);
				}
				imagingAgg.setName(name);

				Set<String> time = new HashSet<String>();
				time.add(imgArr[c].getTime());
				if (time.isEmpty()) {
					time.add(null);
				}
				imagingAgg.setTime(time);

				Set<String> location = new HashSet<String>();
				location.add(imgArr[c].getLocation());
				if (location.isEmpty()) {
					location.add(null);
				}
				imagingAgg.setLocation(location);

				Clm_flds[] clm_flds = imgArr[c].getClm_flds();

				if (clm_flds != null) {
					Set<Clm_fldsAgg> clm_fldsAgg = clm_fieldMethod(clm_flds);
					imagingAgg.setClm_flds(clm_fldsAgg);
				}

				set.add(imagingAgg);
				imgArr[c] = null;
			}
		}
		return set;
	}

}

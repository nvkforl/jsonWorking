package com.jsonJPA.jsonWorking.handler;

import java.util.Arrays;
import java.util.LinkedHashSet;

import com.jsonJPA.jsonWorking.valueObj.Imaging;
import com.jsonJPA.jsonWorking.valueObj.PayLoad;
import com.jsonJPA.jsonWorking.valueObj.Shipping_address;

public class PojoMergeHandler {

	public PayLoad compareAndMergePojo(PayLoad finalPayLoad, PayLoad initialPayLoad) {

		String[] intialLamp = initialPayLoad.getLamp();
		String[] finalLamp = finalPayLoad.getLamp();
		if (intialLamp != null) {
			if (finalLamp == null) {
				finalLamp = intialLamp;
			} else {
				finalLamp = mergeAndRemoveDuplicatesFromStringArray(finalLamp, intialLamp);
			}
			finalPayLoad.setLamp(finalLamp);
		}

		Imaging[] initialImaging = initialPayLoad.getImaging();
		Imaging[] finalImaging = finalPayLoad.getImaging();

		Imaging initialImg = null;
		Imaging finalImg = null;

		if (finalImaging == null) {
			finalImaging = initialImaging;
		} else if(initialImaging !=null){
			for (int i = 0; i < initialImaging.length; i++) {
				initialImg = initialImaging[i];
				for (int j = 0; j < finalImaging.length; j++) {
					finalImg = finalImaging[j];

					if (initialImg.getName() != null) {
						if (finalImg.getName() == null) {
							finalImg.setName(initialImg.getName());
						} else if (!containsIgnoreCase(finalImg.getName(), initialImg.getName())) {
							finalImg.setName(finalImg.getName() + "," + initialImg.getName());
						}
					}

					Shipping_address[] initialShippingAddress = initialImg.getShipping_address();
					Shipping_address[] finalShippingAddress = finalImg.getShipping_address();

					for (int k = 0; k < initialShippingAddress.length; k++) {
						Shipping_address shipping = initialShippingAddress[k];

						if (finalImg.getShipping_address() == null && initialImg.getShipping_address() != null) {
							finalShippingAddress = initialShippingAddress;
						} else if (initialImg.getShipping_address() != null) {
							//finalShippingAddress[++index] = initialShippingAddress[k];
							finalShippingAddress = add(finalShippingAddress,initialShippingAddress);
						}
					}
					finalImg.setShipping_address(finalShippingAddress);
				}
				finalImaging[i] = finalImg;
			}
		}
		finalPayLoad.setImaging(finalImaging);

		return finalPayLoad;
	}

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
	
	public static Shipping_address[] add(Shipping_address[] arr, Shipping_address... elements){
		Shipping_address[] tempArr = new Shipping_address[arr.length+elements.length];
        System.arraycopy(arr, 0, tempArr, 0, arr.length);
        
        for(int i=0; i < elements.length; i++)
            tempArr[arr.length+i] = elements[i];
        return tempArr;
    }
}

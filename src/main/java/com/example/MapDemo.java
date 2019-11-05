package com.example;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class MapDemo {

	public static void main(String[] args) {
		Obj object1 = new Obj("aorcode1", "payload1");
		Obj object2 = new Obj("aorcode2", "payload2");
		Obj object3 = new Obj("aorcode1", "payload3");
		Obj object4 = new Obj("aorcode2", "payload4");
		Obj object5 = new Obj("aorcode1", "payload5");

		List<Obj> list = new ArrayList<Obj>();
		list.add(object1);
		list.add(object2);
		list.add(object3);
		list.add(object4);
		list.add(object5);

		HashMap<String, List<String>> map = new HashMap<String, List<String>>();
		String[] strList = new String[list.size()];

		for (int i = 0; i < list.size(); i++) {
			map.put(list.get(i).getAorcode(), new ArrayList<String>());
		}
		for (int i = 0; i < list.size(); i++) {
			strList[i]  =list.get(i).getPayload();
			map.get(list.get(i).getAorcode()).add(strList[i]);
		}

		System.out.println(map);
	}

}

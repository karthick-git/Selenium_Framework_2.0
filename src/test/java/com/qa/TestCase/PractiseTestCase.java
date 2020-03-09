package com.qa.TestCase;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PractiseTestCase 
{
	public static void main(String[] args) {
		//setClear();
		returnList();

	}
	public static void setClear() {
		Set<String>newSet= new HashSet<String>();
		newSet.add("Test");
		newSet.add("Google");
		newSet.add("Hide");
		for(String value:newSet) {
			System.out.println("String value "+value);
			newSet.clear();
		}

	}
	public static void returnList() {
		List<String>alList= new ArrayList<String>();
		alList.add("Check");
		alList.add("Evaluate");
		alList.add("Test");
		alList.add(2, "Vin");
		for(int i=0;i<alList.size();i++) {
			alList.remove(i);
			if(alList.get(i++).equals("Evaluate")) {
				alList.add("Hands");
				
			}
		}
		System.out.println(alList);
	}
}

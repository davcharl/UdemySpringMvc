package com.luv2code.jackson.json;

import java.io.File;
import java.util.Arrays;

import com.fasterxml.jackson.databind.ObjectMapper;

public class Driver {

	public static void main(String[] args) {
		
		try {
			
			// create object mapper
			ObjectMapper mapper = new ObjectMapper();
			
			// read json file and map/convert to pojo
			// data/sample-lite.json
			Student theStudent = mapper.readValue(new File("data/sample-full.json"), Student.class);
			
			// print first and last names
			System.out.println("Student firstname: " + theStudent.getFirstName() );
			System.out.println("Student lastname: " + theStudent.getLastName() );
			System.out.println("Student address: " + theStudent.getAddress());
			System.out.println("Student Country from address: " + theStudent.getAddress().getCountry());
			
			for (String language : theStudent.getLanguages() ) {
				System.out.println(language);
			}
			
			String tempArray[] = {"dasdf","fghfgh","ghjghjgh"};
			Arrays.stream(tempArray).forEach( e -> System.out.println(" " + e) );
			
		} catch (Exception exp) {
			exp.printStackTrace();
		}

	}

}

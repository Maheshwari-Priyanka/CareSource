package edu.neu.csye6200;

/**
 * @author Priyanka Maheshwari
 *
 */
public class PatientFileDatabase {
	String[] array = new String[8];
	String temp = "";
	public String patient(String tname,String tmno, String patientWard, int n) {
		String bedNumber = Integer.toString(n);
		array[0] = tname;
		array[1] = tmno;
		array[6] = patientWard;
		array[7] = bedNumber;
		temp += array[0]+","+array[1]+","+array[6]+","+array[7]+",";
		System.out.println(temp);
		return temp;
	}
	
	public String doctor(String dId, String dFName, String dLName, String spec) {
		array[2] = dId;
		array[3] = dFName;
		array[4] = dLName;
		array[5] = spec;
		temp += array[2]+","+array[3]+","+array[4]+","+array[5];
		System.out.println(temp);
		return temp;
	}
	
	public String patientString(String csvS) {
		String[] details = csvS.split(",");
		System.out.println(csvS);
		System.out.println(details.length);
		String final_new = details[0]+","+details[1]+","+details[4]+","+details[5]+","+details[6]+","+details[7]+","+details[2]+","+details[3];
		temp = "";
		return final_new;
	}
}

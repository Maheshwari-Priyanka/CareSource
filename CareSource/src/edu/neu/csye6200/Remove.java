package edu.neu.csye6200;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

public class Remove {
	String file = ".\\main.csv";
	String org;	
	static List<String> pharma = new ArrayList<>();
	static List<String> pharmak = new ArrayList<>();
	void read() {
		try { 
			
			FileReader filereader = new FileReader(file); 
			CSVReader csvReader = new CSVReader(filereader);
			String[] nextRecord; 
		        
		        while ((nextRecord = csvReader.readNext()) != null) 
		        { 
		        	String data = String.join(",", nextRecord);
		        	pharma.add(data);
		        	
		        } 
		        csvReader.close();
	    } 
	    catch (Exception e) { e.printStackTrace(); } 
		}
	static void readk(String name) {
		try { 
			String filename_new = name.split(",")[0].replace('"', ' ').trim()+".csv";
			FileReader filereader = new FileReader(filename_new); 
			CSVReader csvReader = new CSVReader(filereader);
			FileWriter outputfile = new FileWriter(filename_new); 
			CSVWriter writer = new CSVWriter(outputfile); 
			String[] nextRecord; 
		        
		        while ((nextRecord = csvReader.readNext()) != null) 
		        { 
		        	String data = String.join(",", nextRecord);
		        	pharmak.add(data);
		        	
		        } 
		        pharmak.add(name);
		        
		        csvReader.close();
		        for(String x: pharmak)
		        {	
		        	String[] data = x.split(",");
		        	
		        	writer.writeNext(data); 
		        	System.out.println("list updated");
		        }
		        writer.close(); 
		        pharmak.clear();
	    } 
	    catch (Exception e) { e.printStackTrace(); } 
		}
	void write() {
		try { 
		    FileWriter outputfile = new FileWriter(file); 
		    CSVWriter writer = new CSVWriter(outputfile); 
		        
		        for(String x: pharma)
		        {	
		        	String[] data = x.split(",");
		        	
		        	writer.writeNext(data); 
		        	System.out.println("list updated");
		        }
		        writer.close(); 
		        pharma.clear();
		    } 
		    catch (IOException e) { e.printStackTrace();} 
		    
	}
	
	String search(String name) {
		try {
			FileReader filereader = new FileReader(file); 
			BufferedReader csvReader = new BufferedReader(filereader);
			String nextRecord; 
		         int k = 0;
		        while ((nextRecord = csvReader.readLine()) != null ) 
		        { 
		        	System.out.println("Inside while.....");
		        	System.out.println(nextRecord);
		        	System.out.println();
		        	String[] next_rec = nextRecord.split(",");
		        	String pname = next_rec[0].replace('"', ' ').trim();
		        	String ino = next_rec[1].replace('"', ' ').trim();
		        	String did = next_rec[2].replace('"', ' ').trim();
		        	String dfname = next_rec[3].replace('"', ' ').trim();
		        	String dlname = next_rec[4].replace('"', ' ').trim();
		        	String sp = next_rec[5].replace('"', ' ').trim();
		        	String wardname = next_rec[6]+"Ward".replace('"', ' ').trim();
		        	String bedno = next_rec[7].replace('"', ' ').trim();

		        	System.out.println("txtFE Text is:"+name+" "+pname);
		        	String qname = '"'+name+'"';
		        	if(name.equals(pname) || qname.equals(pname)) {
		        		System.out.println("Testing inside: "+name+" "+pname);
		        		System.out.println("Index to remove:"+k);
		        		pharma.remove(k);
		        		System.out.println(pharma);
		        		write();
		        		return nextRecord;
		        	}
		        	k++;
		        }
		}
		catch (Exception e){ 
			e.printStackTrace(); 
		}
		return "Patient not found";
	}
	
	public static String demo(String name) {
		System.out.println("Name is: "+name);
		Remove r = new Remove();
		r.read();
		String s = r.search(name);
		System.out.println(s);
		return s;
	}
}
	
	
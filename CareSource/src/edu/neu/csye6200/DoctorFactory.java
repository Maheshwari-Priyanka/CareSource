package edu.neu.csye6200;


import java.util.Arrays;
import java.util.List;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.nio.file.Files;
import java.util.Scanner;

import org.apache.commons.lang3.reflect.FieldUtils;

import com.opencsv.CSVIterator;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

public class DoctorFactory implements AbstractDoctorFactory {
	private static int cr;
	private static int nr;
	private static int oc;
	private static int gc;
	private static int cs;
	private static int sr;
	enum Doctorenum{
		CARDIOLOGIST,NEUROLOGIST,ONCOLOGIST,GYNOCOLOGIST,CHILDSPECIALIST,SURGEON;
	}
	
	public DoctorFactory() {
		cr=readcount("cr");
		nr=readcount("nr");
		oc=readcount("oc");
		gc=readcount("gc");
		cs=readcount("cs");
		sr=readcount("sr");
	}
	
	
	@Override
	public AbstractDoctor getObject(Doctorenum d1) {
		// TODO Auto-generated method stub
		switch(d1) {
		case CARDIOLOGIST:{ cr++;
		if(cr>12) {
			String val= onExit("Cardiologist");
			if(val==null)
			return null;
			return new Cardiologist(val);
			}
			writecount("cr",cr);
			//System.out.println(cr/4);
			return new Cardiologist(readData((cr-1)/4+1));}
		case NEUROLOGIST: { nr++;
		if(nr>24) {
			String val= onExit("Neurologist");
			if(val==null)
			return null;
			return new Neurologist(val);}
			writecount("nr",nr);
			return new Neurologist(readData((nr-1)/4+1));
		}
		case ONCOLOGIST: { oc++;
		if(oc>36) {
			String val= onExit("Oncologist");
			if(val==null)
			return null;
			return new Oncologist(val);}
			writecount("oc",oc);
			return new Oncologist(readData((oc-1)/4+1));
		}
		case GYNOCOLOGIST: { gc++;
		if(gc>48) {
			String val= onExit("Gynocologist");
			if(val==null)
			return null;
			return new Gynocologist(val);
			}
			writecount("gc",gc);
			return new Gynocologist(readData((gc-1)/4+1));
		}
		case CHILDSPECIALIST: { cs++;
		if(cs>60) {
			String val= onExit("Childspecialist");
			if(val==null)
			return null;
			return new ChildSpecialist(val);}
			writecount("cs",cs);
			return new ChildSpecialist(readData((cs-1)/4+1));
		}
		case SURGEON: { sr++;
		if(sr>72) {
			String val= onExit("Surgeon");
			if(val==null)
			return null;
			return new Surgeon(val);}
			writecount("sr",sr);
			return new Surgeon(readData((sr-1)/4+1));
		}
		
		}
		return null;
	}
	public String readData(int id) {
		try(FileReader fr= new FileReader("Doctors.txt");
				BufferedReader br= new BufferedReader(fr)){
			int c=1;
			String res="";
			Scanner s= new Scanner(br);
			while(c<=id&&s.hasNext()) {
				c++;
				res=s.next();
			}
			s.close();
			return res;
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
		
	}
	public int readcount(String name) {
		try(FileReader fr= new FileReader("Doctorcount.txt");
			BufferedReader br= new BufferedReader(fr);){
			name="\""+name+"\"";
			//System.out.println(name);
			Scanner sc= new Scanner(br);
			while(sc.hasNext()) {
				String s=sc.next();
				String[] s1=s.split(",");
			//	System.out.println(s1[0]);
				if(name.equals(s1[0])) {
			//		System.out.println("true");
					String[] s2 =s1[1].split("\"");
					return Integer.parseInt(s2[1]);
				}
			}
			sc.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	public String onExit(String dept){
			try {
				File inputFile= new File("ReleasedDocs.txt");
				CSVReader reader= new CSVReader(new FileReader(inputFile));
				List<String[]> csvBody = reader.readAll();
				String res=null;
				for(int i=0;i<csvBody.size();i++ ) {
					String[] s=csvBody.get(i);
					//System.out.println(s[1]);
					if(s[1].equals(dept)){
					//	System.out.println("Hey");
						res= readData(Integer.parseInt(s[0]));
						csvBody.remove(i);
						break;
					}	
				}
				reader.close();
				CSVWriter writer = new CSVWriter(new FileWriter(inputFile));
				writer.writeAll(csvBody);
				writer.flush();
				writer.close();
				return res;
			}
			catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();;
			}
			return null;
	}
	public void upDateReleasedDocs(String id,String dept,String fn,String ln) {
		try {
			File inputFile=new File("ReleasedDocs.txt");
			CSVWriter writer = new CSVWriter(new FileWriter(inputFile,true));
			String[] s= {id,dept,fn,ln};
			writer.writeNext(s);
			writer.flush();
			writer.close();
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	public void writecount(String name,int count) {

	try {
		File inputFile = new File("Doctorcount.txt");
		CSVReader reader = new CSVReader(new FileReader(inputFile));
		List<String[]> csvBody = reader.readAll();
		csvBody.get((count-1)/(3*4))[1] = String.valueOf(count);
		reader.close();
		CSVWriter writer = new CSVWriter(new FileWriter(inputFile));
		writer.writeAll(csvBody);
		writer.flush();
		writer.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	

}

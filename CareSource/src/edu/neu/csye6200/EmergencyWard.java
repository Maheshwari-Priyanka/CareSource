package edu.neu.csye6200;


public class EmergencyWard {
	private static int count=1;
	protected static String ew="EmergencyWard";
	private static EmergencyWard instance;
	
	private EmergencyWard()
	{
		if(instance!=null)
			throw new RuntimeException("Use getInstance() method to get the single instance");
	}
	
	public static int getCount() {
		return count;
	}

	public static void setCount(int count) {
		EmergencyWard.count = count;
	}

	public static synchronized  EmergencyWard getInstance()
	{  
		if(instance==null) 
			instance = new EmergencyWard();
		 count =Counter.demo(ew);
		
			return instance;
	}
	}
	



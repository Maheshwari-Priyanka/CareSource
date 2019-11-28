package edu.neu.csye6200;


public class NormalWard {
private static NormalWard instance;
private static int count=-1;	
protected static String nw= "NormalWard";
	private NormalWard()
	{
		if(instance!=null)
			throw new RuntimeException("Use getInstance() method to get the single instance");
	}
	
	public static synchronized NormalWard getInstance()
	{
		if(instance==null) 
			instance = new NormalWard();
			
			 count =Counter.demo(nw);
			 
			return instance;
	}

	public static int getCount() {
		return count;
	}

	public static void setCount(int count) {
		NormalWard.count = count;
	}			
}

package edu.neu.csye6200;


public class WardConnect {

//	public static void main(String[] args) {
//		WardConnect w = new WardConnect();
//		w.ConnectwithUI("NormalWard");
//	}
	
     public int ConnectwithUI(String Wardname)
    {
    	 switch(Wardname)
    	 {
    	 case "ChildrenWard":
		
    		ChildrenWard.getInstance();
//    		System.out.println(ChildrenWard.getCount());
//    		ChildrenWard.getInstance();
    		return ChildrenWard.getCount();
//     		ChildrenWard.getInstance();
//    		System.out.println(ChildrenWard.getCount());
//		    
//	}
    	 case "NormalWard":
    		 NormalWard.getInstance();
    		 return NormalWard.getCount();
    	
		
//		NormalWard.getInstance();
//				int n =NormalWard.getCount();
//				if(n==-1)
//					System.out.println("No beds available");
//				else
//					System.out.println(n);
//        
     	 case "EmergencyWard":
  		 EmergencyWard.getInstance();
    	 return EmergencyWard.getCount();
   		    	 
//    	 }
    }
    	 return -1;

	}
}	
	
      	


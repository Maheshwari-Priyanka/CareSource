package edu.neu.csye6200;

public class ChildSpecialist extends Person implements AbstractDoctor {
	public ChildSpecialist(String csv) {
		// TODO Auto-generated constructor stub
		String[] s= csv.split(",");
		setId(Integer.parseInt(s[0]));
		setFirstname(s[1]);
		setLastname(s[2]);
		
	}
	@Override
	public String assignDoctor() {
		// TODO Auto-generated method stub
		return "Child Specialist, "+"with first name, "+ getFirstname()+", last name,"+ getLastname()+",and,"+getId()+ ", assigned";
		//return s;
	}
	

}

package edu.neu.csye6200;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.opencsv.CSVWriter;

/**
 * UI layout
 * @author Pritesh Chauhan
 * 
 * field validation and code documentation
 * @author Priyanka Maheshwari
 */
public class RevisitPatientFormView extends JFrame implements ActionListener {
	private Container c; 
    private JLabel title; 
    private JLabel name; 
    private JTextField tname; 
    private JLabel ino; 
    private JTextField tmno; 
    private JButton sub; 
    private JButton reset;
    private JButton back;
    private JButton check; 
    private JTextArea tout; 
    private JLabel res; 
    private JTextArea resadd; 
    private JLabel ward; 
    private JRadioButton normal; 
    private JRadioButton emergency; 
    private JRadioButton child; 
    private ButtonGroup gengp;
    
    String COMMA_DELIMITER = ",";
    String NEW_LINE_SEPARATOR = "\n";
	String csvFile = ".\\developer.csv";
	FileWriter fileWriter;
	private boolean statusName = true;
	private boolean statusInsID = true;
	private String patientName;
	private String patientInsId;
	private String patientInsTermDate;
	private String patientDob;
	private int patientAge;
	private String patientGender;
	private String patientWard;
    
    List<String> x = new ArrayList<String>();
    String org;
    String dest;
	public RevisitPatientFormView() {
		setTitle("Patient ReVisiting Form"); 
        setBounds(500, 150, 900, 600); 
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        setResizable(false); 
  
        c = getContentPane(); 
        c.setLayout(null); 
  
        title = new JLabel("Revisit Patient Form"); 
        title.setFont(new Font("Arial", Font.PLAIN, 30)); 
        title.setSize(300, 30); 
        title.setLocation(300, 30); 
        c.add(title); 
  
        name = new JLabel("Name"); 
        name.setFont(new Font("Arial", Font.PLAIN, 20)); 
        name.setSize(100, 20); 
        name.setLocation(120, 100); 
        c.add(name); 
  
        tname = new JTextField(); 
        tname.setFont(new Font("Arial", Font.PLAIN, 15)); 
        tname.setSize(190, 20); 
        tname.setLocation(200, 100); 
        c.add(tname); 
  
        ino = new JLabel("Insurance Number"); 
        ino.setFont(new Font("Arial", Font.PLAIN, 20)); 
        ino.setSize(200, 20); 
        ino.setLocation(120, 150); 
        c.add(ino); 
  
        tmno = new JTextField(); 
        tmno.setFont(new Font("Arial", Font.PLAIN, 15)); 
        tmno.setSize(190, 20); 
        tmno.setLocation(200, 175); 
        c.add(tmno); 
        
        ward = new JLabel("Ward"); 
        ward.setFont(new Font("Arial", Font.PLAIN, 20)); 
        ward.setSize(100, 40); 
        ward.setLocation(120, 200); 
        c.add(ward); 
  
        normal = new JRadioButton("Normal"); 
        normal.setFont(new Font("Arial", Font.PLAIN, 15)); 
        normal.setSelected(true); 
        normal.setSize(75, 40); 
        normal.setLocation(200, 200); 
        c.add(normal); 
  
        emergency = new JRadioButton("Emergency"); 
        emergency.setFont(new Font("Arial", Font.PLAIN, 15)); 
        emergency.setSelected(false); 
        emergency.setSize(100, 40); 
        emergency.setLocation(280, 200); 
        c.add(emergency); 
        
        child = new JRadioButton("Child"); 
        child.setFont(new Font("Arial", Font.PLAIN, 15)); 
        child.setSelected(false); 
        child.setSize(100, 40); 
        child.setLocation(380, 200); 
        c.add(child); 
        
        gengp = new ButtonGroup(); 
        gengp.add(normal); 
        gengp.add(emergency);
        gengp.add(child);
        
        check = new JButton("Check"); 
        check.setFont(new Font("Arial", Font.PLAIN, 15)); 
        check.setSize(100, 20); 
        check.setLocation(220, 250); 
        check.addActionListener(this); 
        c.add(check); 
        
        sub = new JButton("Submit"); 
        sub.setFont(new Font("Arial", Font.PLAIN, 15)); 
        sub.setSize(100, 20); 
        sub.setLocation(120, 300); 
        sub.addActionListener(this); 
        sub.setEnabled(false);
        c.add(sub); 
  
        reset = new JButton("Reset"); 
        reset.setFont(new Font("Arial", Font.PLAIN, 15)); 
        reset.setSize(100, 20); 
        reset.setLocation(220, 300); 
        reset.addActionListener(this); 
        c.add(reset); 
        
        back = new JButton("Go Back"); 
        back.setFont(new Font("Arial", Font.PLAIN, 15)); 
        back.setSize(100, 20); 
        back.setLocation(320, 300); 
        back.addActionListener(this); 
        c.add(back);
        
        tout = new JTextArea(); 
        tout.setFont(new Font("Arial", Font.PLAIN, 15)); 
        tout.setSize(300, 400); 
        tout.setLocation(500, 100); 
        tout.setLineWrap(true); 
        tout.setEditable(false); 
        c.add(tout); 
  
        res = new JLabel(""); 
        res.setFont(new Font("Arial", Font.PLAIN, 20)); 
        res.setSize(500, 25); 
        res.setLocation(100, 500); 
        c.add(res); 
  
        resadd = new JTextArea(); 
        resadd.setFont(new Font("Arial", Font.PLAIN, 15)); 
        resadd.setSize(200, 75); 
        resadd.setLocation(580, 175); 
        resadd.setLineWrap(true); 
        c.add(resadd); 
  
        setVisible(true); 
	}
	public void actionPerformed(ActionEvent e) {
//		sub.setEnabled(false);
		if (e.getSource() == check) {
			System.out.println("Inside Check");
			String ward1 = "";
			try (BufferedReader inLine = new BufferedReader(new FileReader(csvFile));) {
				String inputLine = null;
				
				while ((inputLine = inLine.readLine()) != null) {
					
					System.out.println(inputLine);
					String input = inputLine.replace("\"",	"");
					x.add(input);
					String[] field = input.split(",");
					patientName = field[0];
					patientInsId = field[1];
					patientInsTermDate = field[2];
					patientDob = field[3];
					patientAge = Integer.parseInt(field[4]);
					patientGender = field[5];
					patientWard = field[6];
					
					System.out.println(x);
					
					if (tname.getText().equals(patientName)) {
						org = inputLine;
						System.out.println("Org:"+org);
						statusName = true;
						if (tmno.getText().equals(patientInsId)) {
							statusInsID = true;
							
//							JOptionPane.showMessageDialog(null,  "Patient Already registered!");
							
							/**
							 * Calculation of Age
							 */
							System.out.println(patientDob);
							String[] i1 = Instant.now().toString().split("T")[0].split("-");
							String[] i2_Dob = patientDob.split("/");
							LocalDate end = LocalDate.of(Integer.parseInt(i1[0]), Integer.parseInt(i1[1]), Integer.parseInt(i1[2]));
			        		LocalDate start = LocalDate.of(Integer.parseInt(i2_Dob[2]), Integer.parseInt(i2_Dob[1]), Integer.parseInt(i2_Dob[0]));
			        		Period diff1 = Period.between(start, end);
			        		patientAge = diff1.getYears();
			        		System.out.println(patientAge);
			        		
			        		/**
			        		 * Insurance Termination Date Check
			        		 */
			        		System.out.println("Insurance Termination Date:"+patientInsTermDate);
			        		String[] i2_insTerm = patientInsTermDate.split("/");
			        		LocalDate curr = LocalDate.of(Integer.parseInt(i1[0]), Integer.parseInt(i1[1]), Integer.parseInt(i1[2]));
			        		LocalDate term_date = LocalDate.of(Integer.parseInt(i2_insTerm[2]), Integer.parseInt(i2_insTerm[1]), Integer.parseInt(i2_insTerm[0]));
			        		System.out.println(term_date+" "+curr);
			        		Period diff_term = Period.between(curr, term_date);
			        		int checkYear = diff_term.getYears();
			        		checkYear = 12*checkYear;
			        		int check = diff_term.getMonths();
			        		check = Math.abs(check);
			        		check = check+checkYear;
			        		
			        		if (check <= 6) {
			        			JOptionPane.showMessageDialog(null,  "Insurance is Expiring Soon or has been Expired! Bill to be paid in full by the patient.");
			        		}
			        		
			        		if (normal.isSelected() && patientAge >= 18) {
			        			patientWard = "Normal"; 
			        			ward1 = "NormalWard";
			        		}
			                else  if (normal.isSelected() && patientAge < 18) {
			                	patientWard = "Child";
			                	ward1 = "ChildrenWard";
			                }
			                else if (child.isSelected() && patientAge < 18) {
			                	patientWard = "Child";
			                	ward1 = "ChildrenWard";
			                }
			                else if (child.isSelected() && patientAge >= 18) {
			                	patientWard = "Normal";
			                	ward1 = "NormalWard";
			                }
			                else {
			                	patientWard = "Emergency";
			                	ward1 = "EmergencyWard";
			                }
			        		break;
						}
						else {
//							System.out.println("Insurance ID did not match or is incorrectly entered");
//							JOptionPane.showMessageDialog(null,  "Insurance ID did not match or is incorrectly entered!");
//							patientInsId=tmno.getText();
							statusInsID = false;
							break;
						}
					}
					else {
//						System.out.println("Patient Name did not match!");
//						JOptionPane.showMessageDialog(null,  "Patient Name did not match!");
//						tname.setText("");
						statusName = false;
						break;
					}					
				} // end of while
				
        		
			}
			catch (Exception ex) {
					ex.printStackTrace();
			}
			
			if (statusName == false && statusInsID == false) {
				JOptionPane.showMessageDialog(null,  "Patient Details did not match. Please check or register as a new patient!");
				String toutCSV = "Name: "+tname.getText()+"\n"+"Insurance Number: "+tmno.getText()+"\n"+"Patient Name and Insurance number is Incorrect!";
				tout.setText("Incorrect Name");
				tout.setText(toutCSV);				
				res.setText("Registration Unsuccessfull..");
				tmno.setText("");
				tname.setText("");
			}
			else if (statusName == false) {
				JOptionPane.showMessageDialog(null,  "Patient Name did not match. Please check or register as a new patient!");
				String toutCSV = "Name: "+tname.getText()+"\n"+"Insurance Number: "+tmno.getText()+"\n"+"Patient Name is Incorrect!";
				tout.setText(toutCSV);
				res.setText("Registration Unsuccessfull..");
				tname.setText("");
			}
			else if (statusInsID == false) {
				JOptionPane.showMessageDialog(null,  "Patient Insurance Number did not match. Please check or register as a new patient!");
				String toutCSV = "Name: "+tname.getText()+"\n"+"Insurance Number: "+tmno.getText()+"\n"+"Insurance Number is Incorrect!";
				tout.setText(toutCSV);
				res.setText("Registration Unsuccessfull..");
				tmno.setText("");
			}
			
			else {
				try {
	    			String dest = patientName+","+patientInsId+","+patientInsTermDate+","+patientDob+","+patientAge+","+patientGender+","+patientWard;
	    			FileWriter file = new FileWriter(csvFile);
	    			System.out.println("Dest:"+dest);
	    			Collections.replaceAll(x, org, dest);
					CSVWriter bw = new CSVWriter(file);
	    			for(String a: x) {
	    				System.out.println("A:"+a);
	    				String[] newdata = a.split(",");
	    				System.out.println("String:"+newdata[0]);
	    				bw.writeNext(newdata);
	    			}
	    			bw.close();
	    			
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				
				sub.setEnabled(true);
	                String data 
	                    = "Name : "
	                      + tname.getText() + "\n"
	                      + "Insurance Number : "
	                      + ino.getText() + "\n"; 
	                String data2="";
	            	if (normal.isSelected()&&patientWard.equals("Child")) 
	                    data2 = "Ward : Updated to Normal"
	                            + "\n"; 
	            	else if (normal.isSelected()&&patientWard.equals("Normal")) 
	                    data2 = "Ward : Normal"
	                            + "\n"; 
	                else if (child.isSelected() && patientWard.equals("Normal")) 
	                    data2 = "Ward : Updated to Normal"
	                            + "\n";
	                else if (child.isSelected() && patientWard.equals("Child")) 
	                    data2 = "Ward : Child"
	                            + "\n";
	                else if (emergency.isSelected()) 
	                    data2 = "Ward : Emergency"
	                            + "\n";
	                System.out.println(tname.getText().trim().equals("")+"  "+ino.getText().trim().equals(""));
	                if(tname.getText().trim().equals("") || ino.getText().trim().equals("")) {
//	            		tout.setText("Test"); 
	                    tout.setEditable(false);
		                res.setText("Please input all the fields..");
	            	}
	                else {
	                	System.out.println("Came here");
	                	WardConnect w = new WardConnect();
		                int n = w.ConnectwithUI(ward1);
		                if(n > -1) {
		                	check.setEnabled(false);
		                	System.out.println("Bed Number: "+n);
		                	String data5 = "Bed Number: "+n;
		                	tout.setText(data + data2 +data5); 
		                	sub.setEnabled(true);
		                	ActionListener a = new ActionListener() {
								
								@Override
								public void actionPerformed(ActionEvent f) {
									if(f.getSource() == sub) {
										setVisible(false);
//						                new DoctorAssignment();
									}
									
								}
							};
							sub.addActionListener(a);
		                }
		                else {
		                	sub.setEnabled(false);
		                	System.out.println(data2+"Bed Not Available...");
		                	tout.setText(data+data2+"Bed Not Available...");
		                } 
		                tout.setEditable(false); 
		                res.setText("Data Fetched Successfully..");
		               
//		                setVisible(false); 
//		                new DoctorAssignment();
	                }
			}
			
               
		}
		else if(e.getSource() == back) {
			setVisible(false);
			new PatientCheckView();
		}
		else { 
			tname.setText("");
			tmno.setText("");
            tout.setText(""); 
            resadd.setText(""); 
        }
	}
	
}

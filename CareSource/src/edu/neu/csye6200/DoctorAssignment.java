package edu.neu.csye6200;
import javax.swing.*;

import edu.neu.csye6200.DoctorFactory.Doctorenum;

import java.awt.*; 
import java.awt.event.*;
import java.io.FileWriter;

  
class DoctorAssignment extends JFrame implements ActionListener { 
    private Container c; 
    private JLabel title; 
    private JLabel name; 
    private JTextField tname; 
    private JTextField tmno; 
 

    private JComboBox tdisease; 

    private JCheckBox term; 
    private JButton sub; 
    private JButton assign; 
    private JButton back;
    private JTextArea tout; 
    private JLabel res; 
    private JTextArea resadd; 
    private String FILE_HEADER = "Name,Insurance Number,DOB,Gender,Ward";
    String COMMA_DELIMITER = ",";
    String NEW_LINE_SEPARATOR = "\n";
	String csvFile = ".\\developer.csv";
	FileWriter fileWriter;
	String final_send = "";
    private String disease[] 
        = { "CARDIOLOGIST", "NEUROLOGIST", "GYNOCOLOGIST", "SURGEON", "CHILDSPECIALIST", 
            "ONCOLOGIST"}; 
    
    // constructor, to initialize the components 
    // with default values. 
    public DoctorAssignment(String temp_new_pat){ 
    	final_send = temp_new_pat;
        setTitle("Doctor Assignment Form"); 
        setBounds(500, 150, 900, 600); 
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        setResizable(false); 
  
        c = getContentPane(); 
        c.setLayout(null); 
  
        title = new JLabel("Doctor Assignment Form"); 
        title.setFont(new Font("Arial", Font.PLAIN, 30)); 
        title.setSize(475, 40); 
        title.setLocation(280, 30); 
        c.add(title); 
        
        name = new JLabel("Select the Specialist required"); 
        name.setFont(new Font("Arial", Font.PLAIN, 20)); 
        name.setSize(300, 20); 
        name.setLocation(150, 190); 
        c.add(name);  
        
        tdisease = new JComboBox(disease); 
        tdisease.setFont(new Font("Arial", Font.PLAIN, 15)); 
        tdisease.setSize(160, 30); 
        tdisease.setLocation(190, 225); 
        c.add(tdisease); 
  
        assign = new JButton("Assign"); 
        assign.setFont(new Font("Arial", Font.PLAIN, 15)); 
        assign.setSize(100, 20); 
        assign.setLocation(100, 450); 
        assign.addActionListener(this); 
        c.add(assign);
        
        
        sub = new JButton("Submit"); 
        sub.setFont(new Font("Arial", Font.PLAIN, 15)); 
        sub.setSize(100, 20); 
        sub.setLocation(200, 450); 
        sub.addActionListener(this); 
         
        sub.setEnabled(false);
        c.add(sub);
        
        back = new JButton("Go Back"); 
        back.setFont(new Font("Arial", Font.PLAIN, 15)); 
        back.setSize(100, 20); 
        back.setLocation(300, 450); 
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
  
    // method actionPerformed() 
    // to get the action performed 
    // by the user and act accordingly 
    public void actionPerformed(ActionEvent e) 
    { 
    	sub.setEnabled(true);
    	if (e.getSource() == assign) { 
        	DoctorFactory t = new DoctorFactory();
            AbstractDoctor value = t.getObject(Doctorenum.valueOf((String)tdisease.getSelectedItem()));
            System.out.println(value);
            
            if(value == null) {
                tout.setText("Doctor Not Present"); 
                tout.setEditable(false);
                res.setText("Doctor not available..");
                sub.setEnabled(false);
            }
            else{
            	assign.setEnabled(false);
            	back.setEnabled(false);
            	sub.setEnabled(true);
            	String data4 
                = "\n\nSpecialist selected : " 
                        +(String)tdisease.getSelectedItem() 
                        +"\n\n\n"; 
            	String temp = value.assignDoctor();
                tout.setText(data4+" "+temp); 
                System.out.println(data4+" "+temp);
                String[] temp_new = temp.split(",");
                PatientFileDatabase p = new PatientFileDatabase();
                String temp_doc = p.doctor(temp_new[6], temp_new[2], temp_new[4], temp_new[0]);
                tout.setEditable(false);
                res.setText("Doctor Assignment Successfully done..");
            	ActionListener a = new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent f) {
						
    	                if(f.getSource() == sub) {
    	                	PatientFile pf = new PatientFile();
    	                	pf.FileCopy();
    	                	pf.FileCreation(final_send+temp_doc);
//    	                	pf.FileWrite();
    	         
    	                	setVisible(false);
    	                	new Pharmacy();
    	                }
						
					}
				};
            	sub.addActionListener(a);
            	
            }	
    	}
        else if(e.getSource() == back) {
        	setVisible(false);
			new PatientCheckView();
		}
    } 
} 
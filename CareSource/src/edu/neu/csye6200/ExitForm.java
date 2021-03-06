package edu.neu.csye6200;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

public class ExitForm extends JFrame {
private JPanel pnl;
private TableModel model;
private TableModel model1;
private JTable table;
private JTextField txtFE;
private JButton btnSetFE;
private JButton btnSetFE1;
private JButton btn;
private ActionListener al,a2,a3;
private JLabel res;
String rstr = "";
    String[][] data2;
  public ExitForm() throws FileNotFoundException {
//    setDefaultCloseOperation(EXIT_ON_CLOSE);
   
    System.out.println("Inside Exit");
    String[][] data1 = displayTable();
    System.out.println("Next Value");
    String[] columnNames = { "Patient's Name", "Insurance Number", "Doctor Id", "Doctor FName", "Doctor LName", "Specialist", "WardName", "Bed Number"};
    model = new DefaultTableModel(data1, columnNames);
    table = new JTable(model);
    getContentPane().add(new JScrollPane(table));
    pnl = new JPanel();
    pnl.add(new JLabel("Enter Name who is getting discharged:"));
    txtFE = new JTextField(25);
    pnl.add(txtFE);
    btnSetFE = new JButton("Discharge");
    btn = new JButton("Go Back");
    a3 = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent j) {
			if(j.getSource() == btn) {
				setVisible(false);
				new PatientCheckView();
			}
			
		}
	};
	btn.addActionListener(a3);
	
    res = new JLabel("");
    res.setFont(new Font("Arial", Font.PLAIN, 20));
    if(data1 == null)
    	btnSetFE.setEnabled(false);
 
    al = new ActionListener() {
      public void actionPerformed(ActionEvent e) {
// Action on discharge
     if(e.getSource() == btnSetFE) {
     rstr = Remove.demo(txtFE.getText());
     System.out.println(rstr);
     pnl.add(res);
     System.out.println("Value Chweck:"+rstr);
     if(!rstr.equals("Patient not found")) {
	     try { 
	    	 DoctorFactory d = new DoctorFactory();
	    	 String[] next_rec = rstr.split(",");
	        	String pname = next_rec[0];
	        	String ino = next_rec[1];
	        	String did = next_rec[2];
	        	String dfname = next_rec[3];
	        	String dlname = next_rec[4];
	        	String sp = next_rec[5];
	        	String wardname = next_rec[6].trim()+"Ward";
	        	String bedno = next_rec[7];
			        	String patientName = '"'+txtFE.getText()+'"';
			        	if(pname.equals(txtFE.getText())||pname.equals(patientName)) {
			        		System.out.println("txtFE Text is:"+txtFE.getText()+" "+pname);
			        		d.upDateReleasedDocs(did.replace('"', ' ' ).trim(), sp.replace('"', ' ' ).trim(), dfname.replace('"', ' ' ).trim(), dlname.replace('"', ' ' ).trim());
			        		Remove.readk(wardname+","+bedno);
			        	}
	
			}catch (Exception z) { 
				z.printStackTrace(); 
				}
	     
     }
     else {
    	 res.setText(rstr);
     }
     try {
     data2 = displayTable();
} catch (FileNotFoundException e1) {
e1.printStackTrace();
}
   model1 = new DefaultTableModel(data2, columnNames);
             table.setModel(model1);
     }
      }
    };
    btnSetFE.addActionListener(al);
    pnl.add(btnSetFE);
    pnl.add(btn);
    getContentPane().add(pnl, BorderLayout.SOUTH);
    
    setSize(910, 610);
    setVisible(true);
  }
 
  public String[][] displayTable() throws FileNotFoundException {
	  String[] data;
	  Scanner scanner = new Scanner(new File(".\\main.csv"));
	  Scanner scanner1 = new Scanner(new File(".\\main.csv"));
	  int k=0;
//	  System.out.println(scanner.hasNext());
	  while (scanner.hasNextLine()) {
//		  data = scanner.nextLine().split(",");
//		  System.out.println("Inside while");
		  k++;
		  scanner.nextLine();
	  }
	  System.out.println("K: "+k);
	  if(k == 0)
		  return null;
	  String[][] data1 = new String[k][];
	  k=0;
	  while (scanner1.hasNextLine()) {
		  data = scanner1.nextLine().split(",");
		  data1[k] = data;
		  System.out.println(data1[k]);
		  k++;
	  }
	  for(int i=0; i<data1.length; i++)
		  for(int j=0; j<8; j++) {
			  System.out.println(data1[i][j]);
		  }
	  return data1;
  }
  
}
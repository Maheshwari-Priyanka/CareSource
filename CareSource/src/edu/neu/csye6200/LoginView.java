package edu.neu.csye6200;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * Login Page UI
 * @author Pritesh Chauhan
 *
 */
public class LoginView extends JFrame implements ActionListener {
	private Container c; 
    private JLabel title; 
    private JLabel name; 
    private JLabel res;
    private JTextField tname; 
    private JLabel ino; 
    private JPasswordField tmno; 
    private JButton login; 
    private JButton register;
    private JTextArea tout;
    
	public LoginView() {
		
		setTitle("Welcome to CareSource"); 
        setBounds(500, 150, 900, 600); 
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        setResizable(false); 
  
        c = getContentPane(); 
        c.setLayout(null);
        
        // Create title
        title = new JLabel("Login to CareSource"); 
        title.setFont(new Font("Arial", Font.PLAIN, 30)); 
        title.setSize(300, 30); 
        title.setLocation(300, 30); 
        c.add(title); 
        
        // Create Name Label
        name = new JLabel("UserName"); 
        name.setFont(new Font("Arial", Font.PLAIN, 20)); 
        name.setSize(180, 20); 
        name.setLocation(275,150); 
        c.add(name); 
        
        // Create Name TextArea
        tname = new JTextField(); 
        tname.setFont(new Font("Arial", Font.PLAIN, 15)); 
        tname.setSize(190, 20); 
        tname.setLocation(400, 150); 
        c.add(tname);

        // Create Password Label
        ino = new JLabel("Password"); 
        ino.setFont(new Font("Arial", Font.PLAIN, 20)); 
        ino.setSize(200, 20); 
        ino.setLocation(275, 200); 
        c.add(ino);
        
        // Create Password TextArea
        tmno = new JPasswordField(); 
        tmno.setFont(new Font("Arial", Font.PLAIN, 15)); 
        tmno.setSize(190, 20); 
        tmno.setLocation(400, 200); 
        c.add(tmno); 
        
        // Login Button
        login = new JButton("Login"); 
        login.setFont(new Font("Arial", Font.PLAIN, 15)); 
        login.setSize(100, 20); 
        login.setLocation(300, 250); 
        login.addActionListener(this); 
        c.add(login); 
        
        // New User Register
        register = new JButton("Register"); 
        register.setFont(new Font("Arial", Font.PLAIN, 15)); 
        register.setSize(100, 20); 
        register.setLocation(450, 250); 
        register.addActionListener(this); 
        c.add(register); 
        
        res = new JLabel(""); 
        res.setFont(new Font("Arial", Font.PLAIN, 20)); 
        res.setSize(500, 25); 
        res.setLocation(325, 300); 
        c.add(res); 
  
        setVisible(true); 
	}
	public void actionPerformed(ActionEvent e) {
		
		/**
		 * Logging in already registered user
		 */
		if (e.getSource() == login) {
                LoginController val = new LoginController();
                boolean rval = val.check(tname.getText(), tmno.getText()); 
                System.out.println("Here value:"+rval);
                if(rval) {
                	setVisible(false);
                	new PatientCheckView();
                }
                else{
                	res.setText("Please check Username and Password");
                }
		}
		
		/**
		 * Registering New User
		 */
		else if(e.getSource() == register) {
			setVisible(false);
			new RegisterView();
		}
		else { 
			tname.setText("");
			tmno.setText("");
            tout.setText(""); 
        }
	}
	
}

//written by jorge

//imports used for swing
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class loginPage implements ActionListener {
	
	String userName;
	String password;

    private static JLabel userLabel;
    private static    JTextField userText;
    private static    JLabel    passwordLabel;
    private static    JPasswordField    passwordText;
    private static JButton    lGbutton;
    private static JLabel    success;
    
    
    public static void main(String[] args) {
        
        JPanel panel = new JPanel();
        JFrame frame = new JFrame();
        frame.setSize(380,350); //the size of the log in page
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true); //makes the GUI visable
        frame.add(panel);
        
        
        panel.setLayout(null);
        
        userLabel = new JLabel("User");
        userLabel.setBounds(10,20,80,25); //size of label
        panel.add(userLabel);
        
        userText = new JTextField();
        userText.setBounds(100,20,165,25); //size of text field
        panel.add(userText);
        
        passwordLabel = new JLabel("Password");
        passwordLabel.setBounds(10,50,80,25); //size of label
        panel.add(passwordLabel);
        
        passwordText = new JPasswordField();
        passwordText.setBounds(100,50,165,25); //size of password field
        panel.add(passwordText);
        
        lGbutton = new JButton("Login");
        lGbutton.setBounds(10,80,80,25); //size of button
        lGbutton.addActionListener(new loginPage()); //enables action when clicked
        panel.add(lGbutton);
        
            success = new JLabel("");
            success.setBounds(10,110,300,25); //size of label
            panel.add(success);
            success.setText(null);
            
        
        

        frame.setVisible(true);
        
        
        
        
        
    }

    //@Override
    public void actionPerformed(ActionEvent e) {
        this.userName = userText.getText(); //user enters name
        this.password = passwordText.getText(); //user enters password
        
    	
    	
    	
    	String user = userText.getText();
        String password = passwordText.getText();
        System.out.println(user + ", " + password);
        
        //temp. checks if correct credentials
        if(user.equals("Admin") && password.equals("Password1")) {
            success.setText("Login succesful");
        }else{
            success.setText("User ID and password dont match");
        }
        
        
    }




}
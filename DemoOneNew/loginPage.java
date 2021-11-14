package DemoOne;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public abstract class loginPage implements ActionListener extends DemoOne
{

    private static JLabel userLabel;
    private static JTextField userText;
    private static JLabel passwordLabel;
    private static JPasswordField passwordText;
    private static JButton lGbutton;
    private static JLabel success;
    
    public static void main(String[] args) 
    {    
        JPanel panel = new JPanel();
        JFrame frame = new JFrame();
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.add(panel);
        
        panel.setLayout(null);
        
        userLabel = new JLabel("User");
        userLabel.setBounds(10, 20, 80, 25);
        panel.add(userLabel);
        
        userText = new JTextField();
        userText.setBounds(100, 20, 165, 25);
        panel.add(userText);
        
        passwordLabel = new JLabel("Password");
        passwordLabel.setBounds(10, 50, 80, 25);
        panel.add(passwordLabel);
        
        passwordText = new JPasswordField();
        passwordText.setBounds(100, 50, 165, 25);
        panel.add(passwordText);
        
        lGbutton = new JButton("Login");
        lGbutton.setBounds(10,80,80,25);
        lGbutton.addActionListener(null);
        panel.add(lGbutton);
        
            success = new JLabel("");
            success.setBounds(10,110,300,25);
            panel.add(success);
            success.setText(null);
        

        frame.setVisible(true);
        }
    
    
        @Override
        public void actionPerformed(ActionEvent e) {

            String user = userText.getText();
            String password = passwordText.getText();
            System.out.println(user + ", " + password);

            if(user.equals("Admin") && password.equals("Password1")) {
                success.setText("Login succesful");
            }else{
                System.out.println("User ID and password dont match");
            }
        
        
        }   
    
}
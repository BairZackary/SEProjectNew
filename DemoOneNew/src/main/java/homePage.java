//written by zackary
//Ayy
//based off prototype, assisted by jorge and jordan


//imports for swing
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.LineBorder;

import org.bson.Document;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import java.awt.Color;
import java.awt.Component;

public class homePage extends JFrame {

	private JPanel contentPane;
	private JTextField userTextField;
	protected Component frame;

	/**
	 * Launch the application.
	 */

	public static void main(String[] args) {
	
	}
	
	public void display() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					homePage frame = new homePage();
					frame.setVisible(true); //TRUE makes page visable
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});}
	

	/**
	 * Create the frame.
	 */
	public homePage() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1028, 720); //size of page
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JList list = new JList();
		list.setBorder(new LineBorder(new Color(0, 0, 0)));
		list.setBounds(699, 57, 283, 581); //size of inventory list
		contentPane.add(list);
		
		JLabel inventoryManagerLbl = new JLabel("Inventory Manager");
		inventoryManagerLbl.setHorizontalAlignment(SwingConstants.CENTER);
		inventoryManagerLbl.setBounds(10, 15, 386, 14); //size of label
		contentPane.add(inventoryManagerLbl);
		
		JLabel listLbl = new JLabel("Inventory List");
		listLbl.setHorizontalAlignment(SwingConstants.CENTER);
		listLbl.setBounds(699, 15, 283, 31); //size of label
		contentPane.add(listLbl);
		
		//ArrayList test = new ArrayList(Jbutton);
		
		/*final*/ JButton addItemBtn = new JButton("Add Item");
		addItemBtn.addMouseListener(new MouseAdapter() {
			@Override
			//when mouse is clicked
			
			public void mouseClicked(MouseEvent e) {
				
				String Response = "";
				Response = JOptionPane.showInputDialog("for which item");				
				System.out.println(Response);
				
			
				String amount = "";
				if(!Response.equals("")) {
				amount = JOptionPane.showInputDialog("How many would you like to add");
			 
				int i = Integer.parseInt(amount);
				System.out.println(i);
				}
				String credentials = "mongodb+srv://user:user@cluster0.ho2gy.mongodb.net/myFirstDatabase?retryWrites=true&w=majority"; //connects to database
                MongoClient client = MongoClients.create(credentials); //logged into the user in the database
                MongoDatabase db = client.getDatabase("SEProject"); //selects correct project
                MongoCollection col = db.getCollection( "Inventory");
                System.out.println("Database Successfuly retrieved");
                System.out.println("Collection Successfuly created");
            //Insert record into collection by creating a document now you will be able to see the database
                Document doc =new Document("name","New Inventory Item");
                doc.append("id",10);
                doc.append("Amount in inventory",100);
                doc.append("Priority", "1");
            //This line of code is what acutely pushes the document to the cloud
                col.insertOne(doc);
                System.out.println("Insert is completed");
				
				//system responds to items added
				JOptionPane.showMessageDialog(frame, amount +" "+ Response +" Has been added");
				
				}

			
		});
		addItemBtn.setBounds(8, 54, 386, 42);
		contentPane.add(addItemBtn);
		
		JButton removeItemBtn = new JButton("Remove Item");
		removeItemBtn.addMouseListener(new MouseAdapter() {
			@Override
			//when mouse is clicked
			public void mouseClicked(MouseEvent e) {
				
				String Response1;
				Response1 = JOptionPane.showInputDialog("For which item");
				System.out.println(Response1);
				
				
				String answer1;
				answer1 = JOptionPane.showInputDialog("How many would you like to remove");
				int i = Integer.parseInt(answer1);
				
				System.out.println(i);
				
				JOptionPane.showMessageDialog(frame,  answer1+ " " + Response1 + " Has been removed");
				
			}
			
		});
		
		removeItemBtn.setBounds(8, 107, 386, 52);
		contentPane.add(removeItemBtn);
		
		JButton searchBtn = new JButton("Search");
		searchBtn.addMouseListener(new MouseAdapter() {
			@Override
			//when button is clicked
			public void mouseClicked(MouseEvent e) {
				String search1;
				search1 = JOptionPane.showInputDialog("what item are you looking for");
				System.out.println(search1);
			}
		});
		searchBtn.setBounds(10, 170, 384, 52); //button size
		contentPane.add(searchBtn);
		
		userTextField = new JTextField();
		userTextField.setBounds(10, 510, 384, 128); //text field size
		contentPane.add(userTextField);
		userTextField.setColumns(10);
		
		
		JLabel systemOutLbl = new JLabel("System Output");
		systemOutLbl.setHorizontalAlignment(SwingConstants.CENTER);
		systemOutLbl.setBounds(439, 432, 222, 67); //label size
		contentPane.add(systemOutLbl);
		
		JButton continueBtn = new JButton("Enter");
		continueBtn.addActionListener(new ActionListener() {
			//button clicked action 
			public void actionPerformed(ActionEvent e) {
			}
		});
		continueBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		continueBtn.setBounds(439, 542, 222, 96); //size of button
		contentPane.add(continueBtn);
		
		//JLabel lblNewLabel = new JLabel("User Input");
		//lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		//lblNewLabel.setBounds(10, 485, 386, 14); //size of label
		//contentPane.add(lblNewLabel);
	}

	
}

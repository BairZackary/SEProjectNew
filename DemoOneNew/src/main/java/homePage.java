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

import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;

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
		setBounds(100, 100, 596, 485); //size of page
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JList list = new JList();
		list.setBorder(new LineBorder(new Color(0, 0, 0)));
		list.setBounds(267, 84, 250, 281); //size of inventory list
		contentPane.add(list);
		
		JLabel inventoryManagerLbl = new JLabel("Inventory Manager");
		inventoryManagerLbl.setHorizontalAlignment(SwingConstants.CENTER);
		inventoryManagerLbl.setBounds(71, 38, 110, 23); //size of label
		contentPane.add(inventoryManagerLbl);
		
		JLabel listLbl = new JLabel("Inventory List");
		listLbl.setHorizontalAlignment(SwingConstants.CENTER);
		listLbl.setBounds(365, 59, 127, 14); //size of label
		contentPane.add(listLbl);
		
		
	//ADD ITEM BUTTON	
		JButton addItemBtn = new JButton("Add Item");
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
				int i = Integer.parseInt(amount);
	
				String credentials = "mongodb+srv://user:user@cluster0.ho2gy.mongodb.net/myFirstDatabase?retryWrites=true&w=majority"; //connects to database
                MongoClient client = MongoClients.create(credentials); //logged into the user in the database
                MongoDatabase db = client.getDatabase("SEProject"); //selects correct project
                MongoCollection col = db.getCollection( "Inventory");
                System.out.println("Database Successfuly retrieved");
                System.out.println("Collection Successfuly created");
            
		        BasicDBObject searchQuery = new BasicDBObject();
		        searchQuery.put("itemName", Response);
		      
                MongoCursor<Document> cursor = col.find(searchQuery).iterator();
		        
				 if(!cursor.hasNext() ) {
					 Document doc =new Document("itemName",Response);
		                doc.append("quantity",i);
		                col.insertOne(doc);
			       
			        }else{
		            
			        	col.updateOne(Filters.eq("itemName", Response ), Updates.inc("quantity", i));
			            System.out.println("increment complete");
		        }
                    System.out.println("Insert is completed");

				JOptionPane.showMessageDialog(frame, amount +" "+ Response +" Has been added");
				
				}

			
		});
		
		addItemBtn.setBounds(60, 84, 137, 58);
		contentPane.add(addItemBtn);
		
		
	//REMOVE ITEM BUTTON
		
		JButton removeItemBtn = new JButton("Remove Item");
		removeItemBtn.addMouseListener(new MouseAdapter() {
			@Override
			//when mouse is clicked
			public void mouseClicked(MouseEvent e) {
				
				String Response1 = "";
				Response1 = JOptionPane.showInputDialog("For which item");
				System.out.println(Response1);
				
				
				String answer1 = "";
				if(!Response1.equals("")) {
					answer1 = JOptionPane.showInputDialog("How many would you like to remove");
					int i = Integer.parseInt(answer1);
				
					System.out.println(i);
				}
				
				String credentials = "mongodb+srv://user:user@cluster0.ho2gy.mongodb.net/myFirstDatabase?retryWrites=true&w=majority"; //connects to database
                MongoClient client = MongoClients.create(credentials); //logged into the user in the database
                MongoDatabase db = client.getDatabase("SEProject"); //selects correct project
                MongoCollection col = db.getCollection( "Inventory");
                System.out.println("Database Successfuly retrieved");
                System.out.println("Collection Successfuly created");
           
              //Deleting a document from a collection 
                col.deleteOne(Filters.eq("id", 101)); 
                System.out.println("Document deleted successfully...");
                System.out.println(); 
               
				
				JOptionPane.showMessageDialog(frame,  answer1+ " " + Response1 + " Has been removed");
				
			}
			
		});
		
		removeItemBtn.setBounds(60, 185, 137, 58);
		contentPane.add(removeItemBtn);
		
		
	//SEARCH BUTTON	
		JButton searchBtn = new JButton("Search");
		searchBtn.addMouseListener(new MouseAdapter() {
			@Override
			//when button is clicked
			public void mouseClicked(MouseEvent e) {

				String name = "";
				name = JOptionPane.showInputDialog("for which item");				
				
				
				//specific document retrieving in a collection
		        BasicDBObject searchQuery = new BasicDBObject();
		        searchQuery.put("itemName", name);
		        
		        System.out.println("Retrieving specific Mongo Document");
		        String credentials = "mongodb+srv://user:user@cluster0.ho2gy.mongodb.net/myFirstDatabase?retryWrites=true&w=majority"; //connects to database
                MongoClient client = MongoClients.create(credentials); //logged into the user in the database
                MongoDatabase db = client.getDatabase("SEProject"); //selects correct project
                MongoCollection col = db.getCollection( "Inventory");
               
                MongoCursor<Document> cursor = col.find(searchQuery).iterator();
		        
				 if(!cursor.hasNext() ) {
			        	System.out.println("Item does not exist");
			        }while (cursor.hasNext()) {
		            System.out.println(cursor.next());
		        
		        }

			}
		});
		searchBtn.setBounds(60, 284, 137, 69); //button size
		contentPane.add(searchBtn);
		

	}

	
}

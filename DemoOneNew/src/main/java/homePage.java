//initially written by zackary, with assistance from jorge and jordan
//mongo done by raymond
//functions of buttons added by jorge and ray
//addItem() worked on by jordan, jorge, and raymond
//removeItem() worked on by jordan, jorge, and zack
//updateList() worked on by zack and jordan
//search() worked on by all of us


//imports for swing
import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.ListModel;
import javax.swing.JLabel;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Component;

//imports for mongodb
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import org.bson.BsonDocument;
import org.bson.Document;


public class homePage extends JFrame 
{
	//swing objects
	private JPanel contentPane;
	protected static Component frame;
	static JList list = new JList(); //inventory list
	JLabel inventoryManagerLbl = new JLabel("Inventory Manager");
	JLabel listLbl = new JLabel("Inventory List");
	JButton addItemBtn = new JButton("Add Item"); //calls addItem()
	JButton removeItemBtn = new JButton("Remove Item"); //calls removeItem()
	JButton searchBtn = new JButton("Search"); //calls search()

	
	public static void main(String[] args) {
	
	}
	
	
	/**
	 * Called by demoOne.java
	 * Displays the home page UI to the screen
	 */
	public void display() 
	{
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					homePage frame = new homePage();
					frame.setVisible(true); //TRUE makes page visible
				} catch (Exception e) {
					e.printStackTrace(); //prints any errors
				}
			}
		});}
	

	/**
	 * Creates the frame and allows buttons to function.
	 */
	public homePage() 
	{
		//frame configuration
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //what happens when closed
		setBounds(100, 100, 596, 485); //size of page
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//inventory list
		list.setBorder(new LineBorder(new Color(0, 0, 0))); //border color
		list.setBounds(267, 84, 250, 281); //size of inventory list
		contentPane.add(list);
		//inventory label
		inventoryManagerLbl.setHorizontalAlignment(SwingConstants.CENTER); //centers text within object
		inventoryManagerLbl.setBounds(71, 38, 110, 23); //size of label
		contentPane.add(inventoryManagerLbl);
		listLbl.setBounds(359, 59, 127, 14); //size of label
		contentPane.add(listLbl);

		//add item
		JButton addItemBtn = new JButton("Add Item");
		addItemBtn.addMouseListener(new MouseAdapter() 
		{
			@Override
			//when mouse is clicked
			public void mouseClicked(MouseEvent e) 
			{
				addItem(); //calls add item
			}
		});
		addItemBtn.setBounds(60, 84, 137, 58);
		contentPane.add(addItemBtn);
		
		//remove item
		removeItemBtn.addMouseListener(new MouseAdapter() {
			@Override
			//when mouse is clicked
			public void mouseClicked(MouseEvent e) 
			{
				removeItem(); //calls remove item
			}
		});
		removeItemBtn.setBounds(60, 185, 137, 58);
		contentPane.add(removeItemBtn);
		
		//search
		searchBtn.addMouseListener(new MouseAdapter() {
			@Override
			//when button is clicked
			public void mouseClicked(MouseEvent e) 
			{
				search(); //calls search
			}
		});
		searchBtn.setBounds(60, 284, 137, 69); //button size
		contentPane.add(searchBtn);
		
		
		//update the list
		updateList();
	}
	
	
	//add item use case
	public static void addItem()
	{
		String itemName = "";
		itemName = JOptionPane.showInputDialog("What item is being added?").toUpperCase();
		System.out.println(itemName); //testing user response
		
		String quantity = JOptionPane.showInputDialog("How many of the item would you like to add?");
		int i = Integer.parseInt(quantity); //turning the string input into an int
		System.out.println(i); //testing parse
		
		//systemOutLbl.setText(quantity + " " + itemName + " has been added to the inventory."); //used for another way to output to user
		JOptionPane.showMessageDialog(frame, quantity + " " + itemName + " " + "have been added.");
		
		//logging into MongoDB
	    String credentials = "mongodb+srv://user:user@cluster0.ho2gy.mongodb.net/myFirstDatabase?retryWrites=true&w=majority"; //connects to database
	    MongoClient client = MongoClients.create(credentials); //logged into the user in the database
	    MongoDatabase db = client.getDatabase("SEProject"); //selects correct project
	    MongoCollection col = db.getCollection( "Inventory"); //selects the correct items
	    
	    //looks for existing item in database
	    BasicDBObject searchQuery = new BasicDBObject(); //used to query for items
	    searchQuery.put("itemName", itemName); //queries for the item name
	    MongoCursor<Document> cursor = col.find(searchQuery).iterator();
        if(!cursor.hasNext()) //item not found
        { 
        	Document doc =new Document("itemName",itemName);//creates a new item with an itemName
            doc.append("quantity",i); //adds a quantity to the item and adds the number being added
        	//This line of code is what acutely pushes the document to the cloud
            col.insertOne(doc); //pushes the item to the database
            System.out.println("Insert is completed");
          
        } 
        else //item exists 
        { 
          col.updateOne(Filters.eq("itemName", itemName ), Updates.inc("quantity", i));
            System.out.println("increment complete"); 
        }
        
        //update the list
        updateList();
	}
	
	
	//remove item use case
	public static void removeItem()
	{
		//the following is an idea for future improvement, beyond that of the project
//		while (true)
//		{
//			try
//			{
//				//everything would be in here
//				//at the end add:
//				//break
//			}
//			catch (Exception e)
//			{
//				//tell the user they had an input error
//				//print stack error
//			}
//		}
		
		//getting what to remove
		String  itemName = JOptionPane.showInputDialog("What item is being removed?").toUpperCase();
		System.out.println(itemName); //testing user response
		String quantity = JOptionPane.showInputDialog("How many of the item would you like to remove?");
		int i = Integer.parseInt(quantity); //turning the string input into an int
		System.out.println(i); //testing parse
		
		//logging into MongoDB
	    String credentials = "mongodb+srv://user:user@cluster0.ho2gy.mongodb.net/myFirstDatabase?retryWrites=true&w=majority"; //connects to database
	    MongoClient client = MongoClients.create(credentials); //logged into the user in the database
	    MongoDatabase db = client.getDatabase("SEProject"); //selects correct project
	    MongoCollection col = db.getCollection( "Inventory"); //selects the correct items
	    
	    //looks for existing item in database
	    BasicDBObject searchQuery = new BasicDBObject(); //used to query for items
	    searchQuery.put("itemName", itemName); //queries for the item name
	    MongoCursor<Document> cursor = col.find(searchQuery).iterator();
        if(!cursor.hasNext()) //item not found
        { 
        	JOptionPane.showMessageDialog(frame, "Your selected item does not exist.");
        	
        } 
        else //item exists 
        { 
        	int removedAmount  = Math.abs(i) * -1; //makes the number entered into a negative
        	col.updateOne(Filters.eq("itemName", itemName ), Updates.inc("quantity", removedAmount)); //updates the requested item
        }
        
        //update the list
        updateList();
	}
	
	
	//sort inventory use case
	//meant for future implementation
	public static void sortInventory()
	{
		
	}
	
	
	//search use case
	public static void search()
	{
		String requested = JOptionPane.showInputDialog("What item are you looking for?").toUpperCase();
		System.out.println(requested);
		
		//specific document retrieving in a collection
	    BasicDBObject searchQuery = new BasicDBObject();
	    searchQuery.put("itemName", requested); //queries for the requested item
	    
	    System.out.println("Retrieving specific Mongo Document");
	    String credentials = "mongodb+srv://user:user@cluster0.ho2gy.mongodb.net/myFirstDatabase?retryWrites=true&w=majority"; //connects to database
	    MongoClient client = MongoClients.create(credentials); //logged into the user in the database
	    MongoDatabase db = client.getDatabase("SEProject"); //selects correct project
	    MongoCollection col = db.getCollection( "Inventory"); //selects the correct items
	    MongoCursor<Document> cursor = col.find(searchQuery).iterator();
	        
	    //looks for the requested items
	    if(!cursor.hasNext()) 
	    {
	    	System.out.println("Item does not exist");
	    }
	        
	    while(cursor.hasNext()) 
	    {
	    	//System.out.println(cursor.next());
	        Document doc = (Document)cursor.next(); //goes to next document
	        String itemName = doc.getString("itemName");
	        int quantity = doc.getInteger("quantity");
	        	
	        JOptionPane.showMessageDialog(frame, "There is currently " + quantity + " of " + itemName);
	    }
	        
	    //updateList(); //for the other idea for how search would work
	}
	
	
	/**
	 * Updates list
	 */
	public static void updateList()
	{
		//Putting document data into an ArrayList
	    String[] inventoryList = new String[100];
	    try
	    {
	    	//logging into mongo
	    	String credentials = "mongodb+srv://user:user@cluster0.ho2gy.mongodb.net/myFirstDatabase?retryWrites=true&w=majority"; //connects to database
	    	MongoClient client = MongoClients.create(credentials); //logged into the user in the database
	        System.out.println("Created Mongo Connection successfully"); 
	        MongoDatabase db = client.getDatabase("SEProject"); //selects correct project
	        MongoCollection col = db.getCollection( "Inventory"); //selects which collection
	        //System.out.println(col.find()); //prints out all documents
	          
	        //grabbing all documents
	        //Object[] testArray = new Object[4]; //temp array
	        FindIterable<Document> iterDoc = col.find(); //grabs all documents from database
	        int i = 1;
	        // Getting the iterator
	        System.out.println("Listing All Mongo Documents");
	        Iterator it = iterDoc.iterator(); //goes to next fetched item
	        while (it.hasNext()) //if there is another item
	        {
	        	//grabbing important information from the document
	        	Document doc = (Document)it.next(); //goes to next document
	        	String itemDescription = doc.getString("itemName") + "    " + doc.getInteger("quantity");
	        	inventoryList[i - 1] = itemDescription; //adds the important information to the inventoryList
	        	i++;
	        } 
	    }
	    catch (Exception e) //log in unsuccessful
	    {
	        System.out.println("Error");
	        e.printStackTrace(); //prints error to the console
	    }
		
	    list.setListData(inventoryList); //updates the list
		
		
	}
	
}
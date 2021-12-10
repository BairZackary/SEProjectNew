//initially written by zackary, with assistance from jorge and jordan
//changes made by raymond
//functions added by jorge
//addItem() worked on by jordan, jorge, and raymond


//TODO some todos in the code
//TODO instead of global variables, pass as parameters
//TODO standardize all string inputs
//TODO try catch statements and while loops to make sure user input is good


//imports for swing
import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
//import javax.swing.text.Document; //conflicts with org.bson.Document
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
import org.bson.Document;


public class homePage extends JFrame 
{
	//swing objects
	private JPanel contentPane;
	protected static Component frame;
	static //private JTextField userTextField;
	JList list = new JList(); //inventory list
	JLabel inventoryManagerLbl = new JLabel("Inventory Manager");
	JLabel listLbl = new JLabel("Inventory List");
	JButton addItemBtn = new JButton("Add Item"); //calls addItem()
	JButton removeItemBtn = new JButton("Remove Item"); //calls removeItem()
	JButton searchBtn = new JButton("Search"); //calls search()
	//static //JTextField userTextField = new JTextField();
	//static JLabel systemOutLbl = new JLabel("System Output");
	//JButton continueBtn = new JButton("Enter");
	//JLabel userInput = new JLabel("User Input");

	
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
		//listLbl.setHorizontalAlignment(SwingConstants.CENTER);
		listLbl.setBounds(359, 59, 127, 14); //size of label
		//TODO may need to re-center
		contentPane.add(listLbl);

		//add item
		JButton addItemBtn = new JButton("Add Item");
		addItemBtn.addMouseListener(new MouseAdapter() 
		{
			@Override
			//when mouse is clicked
			public void mouseClicked(MouseEvent e) 
			{
				addItem();
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
				removeItem();
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
				search();
			}
		});
		searchBtn.setBounds(60, 284, 137, 69); //button size
		contentPane.add(searchBtn);
		
	}
	
	
	//add item use case
	public static void addItem()
	{
		String itemName = "";
		itemName = JOptionPane.showInputDialog("What item is being added?");
		System.out.println(itemName); //testing user response
		
		String quantity = JOptionPane.showInputDialog("How many of the item would you like to add?");
		int i = Integer.parseInt(quantity); //turning the string input into an int
		System.out.println(i); //testing parse
		
		//TODO two options for outputting to the user
		//systemOutLbl.setText(quantity + " " + itemName + " has been added to the inventory.");
		JOptionPane.showMessageDialog(frame, quantity + " " + itemName + " " + "have been added.");
		
		//logging into MongoDB
	    String credentials = "mongodb+srv://user:user@cluster0.ho2gy.mongodb.net/myFirstDatabase?retryWrites=true&w=majority"; //connects to database
	    MongoClient client = MongoClients.create(credentials); //logged into the user in the database
	    MongoDatabase db = client.getDatabase("SEProject"); //selects correct project
	    MongoCollection col = db.getCollection( "Inventory"); //selects the correct items
	    //TODO check that basic object import went to correct section as well as mongo cursor
	    
	    //looks for existing item in database
	    BasicDBObject searchQuery = new BasicDBObject(); //used to query for items
	    searchQuery.put("itemName", itemName); //queries for the item name
	    MongoCursor<Document> cursor = col.find(searchQuery).iterator();
        if(!cursor.hasNext()) //item not found
        { 
        	Document doc =new Document("itemName",itemName);//creates a new item with an itemName
            //doc.append("id",7);
            doc.append("quantity",i); //adds a quantity to the item and adds the number being added
        	//This line of code is what acutely pushes the document to the cloud
            col.insertOne(doc); //pushes the item to the database
            System.out.println("Insert is completed");
          
        } 
        else //item exists 
        { 
          col.updateOne(Filters.eq("itemName", itemName ), Updates.inc("quantity", i));
            System.out.println("increment complete");
            //TODO make sure filters and updates went to correct place
            
        }
	}
	
	
	//remove item use case
	public static void removeItem()
	{
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
		String  itemName = JOptionPane.showInputDialog("What item is being removed?");
		System.out.println(itemName); //testing user response
		String quantity = JOptionPane.showInputDialog("How many of the item would you like to remove?");
		int i = Integer.parseInt(quantity); //turning the string input into an int
		System.out.println(i); //testing parse
		
		//logging into MongoDB
	    String credentials = "mongodb+srv://user:user@cluster0.ho2gy.mongodb.net/myFirstDatabase?retryWrites=true&w=majority"; //connects to database
	    MongoClient client = MongoClients.create(credentials); //logged into the user in the database
	    MongoDatabase db = client.getDatabase("SEProject"); //selects correct project
	    MongoCollection col = db.getCollection( "Inventory"); //selects the correct items
	    //TODO check that basic object import went to correct section as well as mongo cursor
	    
	    //looks for existing item in database
	    BasicDBObject searchQuery = new BasicDBObject(); //used to query for items
	    searchQuery.put("itemName", itemName); //queries for the item name
	    MongoCursor<Document> cursor = col.find(searchQuery).iterator();
	    //TODO use a try catch to determine that item does not exist
        if(!cursor.hasNext()) //item not found
        { 
        	JOptionPane.showMessageDialog(frame, "Your selected item does not exist.");
        	
        	//OLDER CODE
        	//System.out.println("The item does not exist"); //gives feedback on if the item exists
//        	Document doc =new Document("itemName",itemName);//creates a new item with an itemName
//            //doc.append("id",7);
//            doc.append("quantity",i); //adds a quantity to the item and adds the number being added
//        	//This line of code is what acutely pushes the document to the cloud
//            col.insertOne(doc); //pushes the item to the database
//            System.out.println("Insert is completed");
          
        } 
        else //item exists 
        { 
        	//System.out.println("The negative is: " + (i * -1)); //making sure the number will come out negative
        	int removedAmount  = Math.abs(i) * -1; //makes the number entered into a negative
        	col.updateOne(Filters.eq("itemName", itemName ), Updates.inc("quantity", removedAmount));
        	//TODO we could use the set method rather than the inc function
        	//TODO use try catch to make sure they give correct inputs
        	
        	//OLDER CODE
        	//System.out.println("The item exists");
//          col.updateOne(Filters.eq("itemName", itemName ), Updates.inc("quantity", i));
//            System.out.println("increment complete");
//            //TODO make sure filters and updates went to correct place
            
        }
	}
	
	
	//sort inventory use case
	public static void sortInventory()
	{
		
	}
	
	
	//search use case
	public static void search()
	{
		String requested = JOptionPane.showInputDialog("what item are you looking for");
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
	        	System.out.println(cursor.next());
	        }
	}
	
	
	//update inventory use case
	public static void updateInventory()
	{
		
	}
	
	
	/**
	 * Updates list
	 * Ideas:
	 * we can print to the consol
	 * search for the item
	 * then parse the list to only give us what we want to be displayed
	 * iterater gives the id, itemName, quantity - everything
	 */
	public static void updateList()
	{
		//ArrayList<Document> inventoryList = new ArrayList<Document>();
		try
    	{
	    	String credentials = "mongodb+srv://user:user@cluster0.ho2gy.mongodb.net/myFirstDatabase?retryWrites=true&w=majority"; //connects to database
	        MongoClient client = MongoClients.create(credentials); //logged into the user in the database
	        //System.out.println("Created Mongo Connection successfully"); 
	        MongoDatabase db = client.getDatabase("SEProject"); //selects correct project
	        MongoCollection col = db.getCollection( "Inventory"); //selects which collection

	        System.out.println(col.find());
	        
//	        Object[] testArray = new Object[4]; 
//	        testArray[0] = "Hello";
//	        testArray[1] = "World";
//	        list.setListData(testArray);
	        
	        FindIterable<Document> iterDoc = col.find(); //grabs all documents from database
	        //System.out.println(iterDoc.getClass().getSimpleName());
	        int i = 1;
//	        // Getting the iterator
//	        System.out.println("Listing All Mongo Documents");
	        Iterator it = iterDoc.iterator(); //goes to next fetched item
	        while (it.hasNext()) 
	        {
	        	//col.find(Filters.eq("itemName"), "tacos");
	        	System.out.println(col.find(Filters.eq("quantity")) + " " + i);
	        	//System.out.println(col.get("itemName");
	        	it.next();
	        	//String test = col.get
	        	
	        	
	        	//System.out.println(it.next());
	        	
	        	//DBObject dbo = it.next();
	        	//it.next();
	        	
	        	//String test = it.next();
	        	//System.out.println(it.next());
	            
	            //System.out.println(it.next().get("itemName"));
	            //testArray[i - 1] = 
	            i++;
	        } 
	        //list.setListData(testArray);
    	}catch (Exception e) //log in unsuccessful
    	{
    		System.out.println("Incorrect credentials.");
    		e.printStackTrace(); //I think this will print the error to the console
    	}
	}
	
}

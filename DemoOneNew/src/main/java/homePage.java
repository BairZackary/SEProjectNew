//initially written by zackary, with assistance from jorge and jordan
//changes made by raymond
//functions added by jorge


//imports for swing
import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.text.Document;
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

//imports for mongodb
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;


public class homePage extends JFrame 
{
	//swing objects
	private JPanel contentPane;
	//protected Component frame;
	//private JTextField userTextField;
	
	JList list = new JList();
	JLabel inventoryManagerLbl = new JLabel("Inventory Manager");
	JLabel listLbl = new JLabel("Inventory List");
	JButton addItemBtn = new JButton("Add Item");
	JButton removeItemBtn = new JButton("Remove Item");
	JButton searchBtn = new JButton("Search");
	static //static //JTextField userTextField = new JTextField();

	JLabel systemOutLbl = new JLabel("System Output");
	JButton continueBtn = new JButton("Enter");
	//JLabel userInput = new JLabel("User Input");

	
	public static void main(String[] args) {
	
	}
	
	
	//called by DemoOne.java
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
	 * Create the frame.
	 */
	public homePage() 
	{
		//frame configuration
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //what happens when closed
		setBounds(100, 100, 1028, 720); //size of page
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//inventory list
		list.setBorder(new LineBorder(new Color(0, 0, 0))); //border color
		list.setBounds(699, 57, 283, 581); //size of inventory list
		contentPane.add(list);
		//inventory label
		inventoryManagerLbl.setHorizontalAlignment(SwingConstants.CENTER); //centers text within object
		inventoryManagerLbl.setBounds(10, 15, 386, 14); //size of label
		contentPane.add(inventoryManagerLbl);
		listLbl.setHorizontalAlignment(SwingConstants.CENTER);
		listLbl.setBounds(699, 15, 283, 31); //size of label
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
		addItemBtn.setBounds(8, 54, 386, 42);
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
		removeItemBtn.setBounds(8, 107, 386, 52);
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
		searchBtn.setBounds(10, 170, 384, 52); //button size
		contentPane.add(searchBtn);
		
		//userTextField.setBounds(10, 510, 384, 128); //text field size
		//contentPane.add(userTextField);
		//userTextField.setColumns(10);
		
		
		//final JLabel systemOutLbl = new JLabel("System Output");
		systemOutLbl.setHorizontalAlignment(SwingConstants.CENTER);
		systemOutLbl.setBounds(10, 510, 384, 128); //label size
		systemOutLbl.setText("Test");
		contentPane.add(systemOutLbl);
		
		//enter button
		continueBtn.addActionListener(new ActionListener() {
			//button clicked action
			public void actionPerformed(ActionEvent e) {
			}
		});
		continueBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Object[] testArrayTwo = new Object[3];
		        testArrayTwo[0] = "World";
		        testArrayTwo[1] = "Hello";
		        testArrayTwo[2] = "Test";
		        list.setListData(testArrayTwo);
			}
		});
		continueBtn.setBounds(439, 542, 222, 96); //size of button
		contentPane.add(continueBtn);
		
		
		//trying to get the list to work
//		ArrayList<Document> inventoryList = new ArrayList<Document>();
//		try
//    	{
//	    	String credentials = "mongodb+srv://user:user@cluster0.ho2gy.mongodb.net/myFirstDatabase?retryWrites=true&w=majority"; //connects to database
//	        MongoClient client = MongoClients.create(credentials); //logged into the user in the database
//	        //System.out.println("Created Mongo Connection successfully"); 
//	        MongoDatabase db = client.getDatabase("SEProject"); //selects correct project
//	        MongoCollection col = db.getCollection( "Inventory"); //selects which collection
//
//	        System.out.println(col.find());
//	        
//	        Object[] testArray = new Object[4]; 
//	        testArray[0] = "Hello";
//	        testArray[1] = "World";
//	        list.setListData(testArray);
//	        
//	        FindIterable<Document> iterDoc = col.find(); //grabs all documents from database
//	        int i = 1;
//	        // Getting the iterator
//	        System.out.println("Listing All Mongo Documents");
//	        Iterator it = iterDoc.iterator(); //goes to next fetched item
//	        while (it.hasNext()) 
//	        {
//	            System.out.println(it.next());
//	            testArray[i - 1] = 
//	            i++;
//	        } 
//	        list.setListData(testArray);
//    	}catch (Exception e) //log in unsuccessful
//    	{
//    		System.out.println("Incorrect credentails.");
//    	}
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
		
		//TODO two options for outputing to the user
		systemOutLbl.setText(quantity + " " + itemName + " has been added to the inventory.");
		//JOptionPane.showMessageDialog(frame, answer + " " + "have been added.");
	}
	
	
	
	
	
	
	//remove item use case
	public static void removeItem()
	{
		String response = JOptionPane.showInputDialog("What item is being removed?");
		System.out.println(response); //testing user response
		
		String answer = JOptionPane.showInputDialog("How many of the item would you like to remove?");
		int i = Integer.parseInt(answer); //turning the string input into an int
		System.out.println(i); //testing parse
		
		systemOutLbl.setText(answer + " " + response + " has been removed from the inventory.");
	}
	
	
	//sort inventory use case
	public static void sortInventory()
	{
		
	}
	
	
	//search use case
	public static void search()
	{
		String Response = JOptionPane.showInputDialog("what item are you looking for");
		System.out.println(Response);
	}
	
	
	//update inventory use case
	public static void updateInventory()
	{
		
	}
}

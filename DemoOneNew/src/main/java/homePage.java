//initially written by zackary, with assistance from jorge and jordan
//changes made by raymond
//functions added by jorge


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
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.LineBorder;
import java.awt.Color;


public class homePage extends JFrame 
{
	//swing objects
	private JPanel contentPane;
	//private JTextField userTextField;
	
	//swing objects we interact with
	JList list = new JList();
	JLabel inventoryManagerLbl = new JLabel("Inventory Manager");
	JLabel listLbl = new JLabel("Inventory List");
	JButton addItemBtn = new JButton("Add Item");
	JButton removeItemBtn = new JButton("Remove Item");
	JButton searchBtn = new JButton("Search");
	JTextField userTextField = new JTextField();
	JLabel systemOutLbl = new JLabel("System Output");
	JButton continueBtn = new JButton("Enter");
	JLabel userInput = new JLabel("User Input");

	
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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //what happens when closed
		setBounds(100, 100, 1028, 720); //size of page
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		list.setBorder(new LineBorder(new Color(0, 0, 0))); //border color
		list.setBounds(699, 57, 283, 581); //size of inventory list
		contentPane.add(list);
		
		inventoryManagerLbl.setHorizontalAlignment(SwingConstants.CENTER); //centers text within object
		inventoryManagerLbl.setBounds(10, 15, 386, 14); //size of label
		contentPane.add(inventoryManagerLbl);
		
		listLbl.setHorizontalAlignment(SwingConstants.CENTER);
		listLbl.setBounds(699, 15, 283, 31); //size of label
		contentPane.add(listLbl);

		/*final*/ JButton addItemBtn = new JButton("Add Item");
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
		
		userTextField.setBounds(10, 510, 384, 128); //text field size
		contentPane.add(userTextField);
		userTextField.setColumns(10);
		
		
		JLabel systemOutLbl = new JLabel("System Output");
		systemOutLbl.setHorizontalAlignment(SwingConstants.CENTER);
		systemOutLbl.setBounds(439, 432, 222, 67); //label size
		contentPane.add(systemOutLbl);
		
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
		
		userInput.setHorizontalAlignment(SwingConstants.CENTER);
		userInput.setBounds(10, 485, 386, 14); //size of label
		contentPane.add(userInput);
		
	}
	
	
	//add item use case
	public static void addItem()
	{
		//pseudo-code
//		systemOutLbl.setText("What is the name of the item?");
//		wait for enter to be clicked
//		itemName = userInput.getText();
//		userInput.setText("");
//		add item name to mongodb
//		systemOutLbl.setText("How many items?");
//		wait for enter
//		amount = userInput.getText();
		

		String Response = JOptionPane.showInputDialog("What item is being added?");
		//Response.getText();
		System.out.println(Response); //testing user response
		
		String answer = JOptionPane.showInputDialog("How many of the item would you like to add?");
		//System.out.println(answer); //testing user answer
		int i = Integer.parseInt(answer); //turning the string input into an int
		System.out.println(i); //testing parse
	}
	
	
	//remove item use case
	public static void removeItem()
	{
		String Response = JOptionPane.showInputDialog("How many would you like to remove");
		System.out.println(Response); //test
		
		String answer = JOptionPane.showInputDialog("How many of the item would you like to remove?");
		int i = Integer.parseInt(answer); //turning the string input into an int
		System.out.println(i); //testing parse
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

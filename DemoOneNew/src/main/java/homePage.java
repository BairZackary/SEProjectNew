//written by zackary and raymond
//based off prototype, assisted by jorge and jordan


//imports for swing
import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JList;
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
	private JTextField userTextField;

	
	public static void main(String[] args) {
	
	}
	
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
		
		JList list = new JList();
		list.setBorder(new LineBorder(new Color(0, 0, 0))); //border color
		list.setBounds(699, 57, 283, 581); //size of inventory list
		contentPane.add(list);
		
		JLabel inventoryManagerLbl = new JLabel("Inventory Manager");
		inventoryManagerLbl.setHorizontalAlignment(SwingConstants.CENTER); //centers text within object
		inventoryManagerLbl.setBounds(10, 15, 386, 14); //size of label
		contentPane.add(inventoryManagerLbl);
		
		JLabel listLbl = new JLabel("Inventory List");
		listLbl.setHorizontalAlignment(SwingConstants.CENTER);
		listLbl.setBounds(699, 15, 283, 31); //size of label
		contentPane.add(listLbl);
		
		JButton addItemBtn = new JButton("Add Item");
		addItemBtn.addMouseListener(new MouseAdapter() {
			@Override
			//when mouse is clicked
			public void mouseClicked(MouseEvent e) {
				addItem();
			}
		});
		addItemBtn.setBounds(8, 54, 386, 42);
		contentPane.add(addItemBtn);
		
		JButton removeItemBtn = new JButton("Remove Item");
		removeItemBtn.addMouseListener(new MouseAdapter() {
			@Override
			//when mouse is clicked
			public void mouseClicked(MouseEvent e) {
				removeItem();
			}
		});
		removeItemBtn.setBounds(8, 107, 386, 52);
		contentPane.add(removeItemBtn);
		
		JButton searchBtn = new JButton("Search");
		searchBtn.addMouseListener(new MouseAdapter() {
			@Override
			//when button is clicked
			public void mouseClicked(MouseEvent e) {
				search();
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
		
		JLabel userInput = new JLabel("User Input");
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
	}
	
	
	//remove item use case
	public static void removeItem()
	{
		
	}
	
	
	//sort inventory use case
	public static void sortInventory()
	{
		
	}
	
	
	//search use case
	public static void search()
	{
		
	}
	
	
	//update inventory use case
	public static void updateInventory()
	{
		
	}
}

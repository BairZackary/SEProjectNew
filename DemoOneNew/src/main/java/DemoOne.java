//the driver for the program
//system outputs were left in for future documentation

//written by Zack and raymond Duenas
//worked on by all members of the group


//list of mongodb imports
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import javax.swing.SwingUtilities;
import org.bson.Document;


public class DemoOne 
{
	public static void main(String[] args) 
	{
		//full functionality
		//Ray-provided the logic for the code in lines 24 and 25
		loginPage logGui = new loginPage(); //creates a loginPage.java object
		logGui.display(); //displays log in page
			
		
		//for testing
		//homePage homeGUI = new homePage(); //creates a homePage.java object
		//homeGUI.display(); //displays the home page
		//homeGUI.updateList();
    }
}

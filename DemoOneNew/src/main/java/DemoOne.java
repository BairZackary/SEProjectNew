//written by Zack and raymond Duenas


import org.bson.Document;

//list of mongodb imports
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import javax.swing.SwingUtilities;


public class DemoOne 
{
	public static void main(String[] args) 
	{
		loginPage logGui = new loginPage(); //creates a log in page
		logGui.display(); //starts the program
		
    }
}

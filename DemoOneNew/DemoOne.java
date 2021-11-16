//written by Zack and raymond

import java.util.Iterator; //used for printing out the items in the database
import java.util.Scanner; //temp to test log in

import org.bson.Document;

//list of mongodb imports
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;


//main
public class DemoOne {

    public static void main(String[] args) {
    	//currently not creating a log in page when the demo is ran
    	loginPage login = new loginPage(); //links to loginPage.java
    	String username = login.userName; //gets the username entered by the user
    	String password = login.password; //gets the password entered by the user
    	
    	try
    	{
	    	String credentials = "mongodb+srv://" + username + ":" + password + "@cluster0.ho2gy.mongodb.net/myFirstDatabase?retryWrites=true&w=majority"; //connects to database
	        MongoClient client = MongoClients.create(credentials); //logged into the user in the database
	        System.out.println("Created Mongo Connection successfully"); 
	        MongoDatabase db = client.getDatabase("SEProject"); //selects correct project
	
	        MongoCollection col = db.getCollection( "Inventory"); //selects which collection
	
	        FindIterable<Document> iterDoc = col.find(); //grabs all documents from database
	        int i = 1;
	        // Getting the iterator
	        System.out.println("Listing All Mongo Documents");
	        Iterator it = iterDoc.iterator(); //goes to next fetched item
	        while (it.hasNext()) {
	            System.out.println(it.next());
	            i++;
	        } 
    	}catch (Exception e) //log in unsuccessful
    	{
    		System.out.println("Incorrect credentails.");
    	}
    }

}
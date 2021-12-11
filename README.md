# SE_TermProject

<h1>
  Required tools:<br>
</h1>
Java runtime enviornment:<br>
Inetenrt connection<br>
MongoDB database

GitHub link:
https://github.com/BairZackary/SEProjectNew.git
DemoOne.java
	The driver for the program
	Will run all other required files
LoginPage.java
	Creates a log in page
	Ensures the user gets logged into MongoDB correctly
	Launches homePage.java
HomePage.java
	Creates the home page for the program
	Controls program interaction to the database
	Provides a clean interface for users to interact with

How to run our software:
Download the inventoryManager.jar, then run the jar file
inventoryManager.jar can be found on our GitHub within the SEProjectNew directory (root directory)

Allowed input parameters:
Login page
	Enter in your MongoDB credentials
	Program will tell you if inputted incorrectly
	Program will accept strings in the provided fields 
Home page
	When software asks for item name, any input is accepted
	When asked for item quantity, program will only work if given an integer input

Working credentials:
Username:
	user
Password:
	user

Unit testing:
login page:
	after running demoOne.Java
	enter "user" for username.
	and "user" for password.
	expected outcome is the program logs user into the mongoDB database and sends them to the home page.
	entering anything else in username or password will not connect use to the database or send them to the homepage
home page:
	Add item button:
	user presses add item button
	user is prompted "What item is to be added?" and the user enters Beans.
	user is prompted "How many of the item would you like to add?" and enters 2.
	expected outcome is that 2 Beans are added to the inventory.
	alternatively, if anything but an integer is entered into the 2nd prompt, the program will throw an exception.
Remove item button:
	user presses add item button
	user is prompted "What item is to be removed?" and the user enters Beans.
	user is prompted "How many of the item would you like to remove?" and enters 2.
	expected outcome is that 2 Beans are removed from the inventory.
	alternatively, if anything but an integer is entered into the 2nd prompt, the program will throw an exception.
Search button:
	user presses the search button
	user is prompted "What item are you looking for?" and the user enters Beans.
	if Beans are already in the database it will return the amount in a notification.
	if not then it will not return "Item does not exist."

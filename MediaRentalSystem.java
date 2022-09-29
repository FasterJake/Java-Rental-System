import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

import org.w3c.dom.stylesheets.MediaList;

public class MediaRentalSystem {
	
	 
	public static void main(String[] args) {
		System.out.println("Jake Scanlan, CMIS 242, 3/8/2022");
		System.out.println("");
	  
		try (Scanner scan = new Scanner(System.in)) {
		boolean isOver = false;
		
		//Creates a new Manager object
		Manager manager = new Manager();
		
		//While is not false the loop will continue
		while (!isOver) {
			int choice = getMenuOption(scan);
			
			switch (choice) {
			case 1:
				try {//Starts with asking where you want to load data from
					String fileName = getStringChoice(scan, "Please enter Media.txt, MovieDVD.txt, MusicCD.txt, or EBook.txt to check what media we have available: " );
					manager.loadFromFile(fileName);//Loads data from using loadFromrile with the inputed file location

				}
			catch (IOException e) {
				System.out.println("File cannot be opened: Could not load, no such directory");//If can't read file
			}
			break;
			case 2://Looks for media based on title
				String title = getStringChoice(scan, "Enter the title: ");
				List<Media> collection = manager.findByTitle(title);//Checks list
				if (collection.isEmpty()) {
					System.out.println("There is no media with this title: " + title);//If there is nothing with that title
				}
				else {
					for (Media media: manager.findByTitle(title)) {
						System.out.println(media);//Prints media with that title from list
					}
				}
				break;
				
			case 3:
				//Asks for ID to begin the renting process			
				int id = getIntChoice(scan, "Enter the id: ", null);
			    Boolean rent = manager.tryRentById(id);
				if (rent == null) {
					System.out.println("The media object id = " + id + " is not found");//If id doesnt exist
				}
				else {
					if(rent) {
						
						
					}
					else {
						System.out.println("Media is already rented");
					}
				}
				break;
				
			case 4:
				try {//Asks for the filename of the file location to add files to medialist
					String filename2 = getStringChoice(scan, "Please enter Media.txt, MovieDVD.txt, MusicCD.txt, or EBook.txt to add more to your viewing list " );
					manager.addFiles(filename2);

				}
			    catch (IOException e) {
				    System.out.println("File cannot be opened: Could not load, no such directory");
			}
			break;
			
			case 5://Prints all media currently inside the mediaList
				manager.showAll();
			break;
				
			case 9://Exits the program
				isOver = true;
				break;
				default:
					throw new IllegalStateException();
			}
			System.out.println();
		}
		
		}
		
		System.out.println("Thank you for using the program to rent and find media.");
	}
	
//Creates the layout for the switch/cases
private static int getMenuOption(Scanner scan) {
	String builder = "Welcome to Media Rental System" + System.lineSeparator() +
	"1: Load Media objects..." + System.lineSeparator() +
	"2: Find Media object..." + System.lineSeparator() +
	"3: Rent Media object..." + System.lineSeparator() +
	"4: Add Media objects..." + System.lineSeparator() +
	"5: Show all Media..." + System.lineSeparator() +
	"9: Quit" + System.lineSeparator();
	
	System.out.println(builder);
	return getIntChoice(scan, "Enter your selection: ", Arrays.asList(1, 2, 3, 4, 5, 9));//Selections/cases inside the switch
	}

//Checks for int inputs within case 3 of the switch to find the ID within mediaList
private static int getIntChoice(Scanner scan, String prompt, Collection options) {
	while (true) {
		System.out.print(prompt);
		
		try { 
			int choice = Integer.parseInt(scan.nextLine());
			if(options == null || options.contains(choice)) {
				return choice;
			}
			else throw new Exception();
		} catch (Exception ignored) {
			System.out.println("Invalid input. Please, try again.");
			System.out.println();
		}
		}
}

//Checks for String inputs within case 1, 2, and 4 of the switch to find the ID within mediaList
private static String getStringChoice(Scanner scan, String prompt) {
	while (true) {
		System.out.println(prompt);
		String choice = scan.nextLine().trim();
		if(!choice.isEmpty()) {
			return choice;
		}
		System.out.println("Invalid input. Please, try again");
		System.out.println();
	}
}
}

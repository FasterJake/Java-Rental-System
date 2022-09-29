import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;


public class Manager {
	 //Creates empty objects/constructors so can call methods from each child class
	 EBook bookfee = new EBook();
	 MusicCD cdfee = new MusicCD();
	 MovieDVD dvdfee = new MovieDVD();
	
	 //Creates list mediaList
	public List <Media>mediaList;
	
	//Creates a Manager constructor and creates new ArrayList with mediaList
	public Manager() {
		mediaList = new ArrayList<>();
	}
	
	
	//Method to loadFromFile
	public void loadFromFile(String fileName) throws FileNotFoundException {		

		try (Scanner scan = new Scanner(new File(fileName))){ //Scans each line of the file 
			mediaList.clear();//Clears the array list
			while(scan.hasNextLine()) {//While there is something left in the txt file it runs through 
				String line = scan.nextLine();//Creates a String line
				Media media = Media.parse(line);//Creates a Media variable and then parses each line
				if(media != null) {//If file is not empty 
					mediaList.add(media);//Adds the media to mediaList
					System.out.println(media);//Prints each media from the file
					System.out.println("");
				}
				else {
					System.err.println("Can not parse media from the line" + line);//If file was unreadable
					
				}
			}
		}
	}
	
	
	//Method to add more files to the mediaList
	public void addFiles(String fileName2) throws IOException{
		try (Scanner scan= new Scanner(new File(fileName2))){//Scans each line of the file 
			while(scan.hasNextLine()) {//While there is something left in the txt file it runs through
			String line = scan.nextLine();//Creates a String line
			Media media = Media.parse(line);//Creates a Media variable and then parses each line
			if(media != null) {//If file is not empty
				mediaList.add(media);//Adds the media to mediaList
				System.out.println(media);//Prints each media from the file
				System.out.println("");
			}
			else {
				System.err.println("Can not load data." + line);//If file was unreadable
			}
			}
		}
	}
	
	//Method to show all media
	public void showAll() {
		mediaList.forEach(System.out::println);//Prints each media on its own line
		
	}
		
	//Method to find media by searching for the title
	public List<Media> findByTitle(String title) {
		return mediaList.stream().filter(m ->(m).getTitle().equals(title)).collect(Collectors.toList());
	
	}
	
	//Method to get media and rental fee with the ID
	public void getMediabyId(int id) {
		if(id > 100 && id < 300) { //Anything in the 100-300 range of ID's is an EBook
			Scanner book = new Scanner(System.in);
			System.out.println("Please enter the number of chapters in the book you would like to rent:");
			int bookfinal = book.nextInt();
			bookfee.setNumChapters(bookfinal);//Calls setnumChapters from EBook
			System.out.println("Book was successfully rented. Rental Fee $" + String.format("%.2f",bookfee.calculateRentalFee()));//Calls calculateRentalFee from EBook
		}
			else if(id > 400 && id < 700) { //Anything in the 400-700 range of ID's is a CD
				Scanner cd = new Scanner(System.in);
				System.out.println("Please enter how long the CD is in minutes that you would like to rent:");
				int cdfinal = cd.nextInt();
				cdfee.setLength(cdfinal);//Calls setLength from MusicCD
				System.out.println("CD was successfully rented. Rental Fee $" + String.format("%.2f",cdfee.calculateRentalFee()));//Calls CalculateRentalFee from MusicCD
			}
			else if(id > 700 && id < 1000) { //Anything in the 700-1000 range of ID's is a DVD
				Scanner dvd = new Scanner(System.in);
				System.out.println("Please enter how big the dvd is in MB you would like to rent:");
				int dvdfinal = dvd.nextInt();
				dvdfee.setSize(dvdfinal);//Calls setSize from MovieDVD
				System.out.println("DVD was successfully rented. Rental Fee $" + String.format("%.2f",dvdfee.calculateRentalFee()));//Calls calculateRentalFee from MovieDVD
			}
		
	}

	//Method to start the renting process by checking the ID	
	public Boolean tryRentById(int id) {
		Media media = mediaList.stream().filter(m ->(m).getId() == id).findAny().orElse(null);
		if (media == null) {
			return null;
		}
		if(((Media) media).isAvailable()) {
			((Media) media).setAvailable(false);
			getMediabyId(id);
			return true;
		}
		else {
			return false;
		}
	}
	
	
}

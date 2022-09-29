import java.util.List;

public abstract class Media {
	//Empty Constructor
	public Media() {
		
	}
	
	//Attributes
	private int id; //3digits
	private String title;
	private int year; //4digits
	private boolean available;
	
	//Constructor with attributes
	public Media(int id, String title, int year) {
		
		this.id = id;
		this.title = title;
		this.year = year;
		this.available = true;
		
	}
	//Get methods
	public int getId() {
		return id;
	}
	
	public String getTitle() {
		return title;
	}
	
	public int getYear() {
		return year;
	}
	//Set methods
	public void setTitle(String title) {
		this.title = title;
	}
	
	public void setYear(int year) {
		this.year = year;
	}
	//Method to calculate rental fee, for generic media it is a flat fee of 3.50
	public double calculateRentalFee() {
		
		return 3.50;
	}
	//Method to find if media is available
	public boolean isAvailable() {
		return available;
	}
	//Method to set availability
	public void setAvailable(boolean available) {
		this.available = available;
	}
	//Method to print data to a string
	public String toString() {
		return "Media{id =" + id + ", title = " + title + ", year = " + year + ", available for rent = " + available + "}";
	}
	
	//Method to serialize the objects and then convert them into parse strings
	public abstract String serialized();
	public static Media parse(String line) {
		String[] parts = line.split(","); //Creates a string array parts
		Media media;
		switch (parts[0]){
		case "EBook": //Converts each type of media into parsed strings
			media = new EBook(Integer.parseInt(parts[1]), parts[2], Integer.parseInt(parts[3]), Integer.parseInt(parts[4]));
			break;
		case "MovieDVD":
			media = new MovieDVD(Integer.parseInt(parts[1]), parts[2], Integer.parseInt(parts[3]), Double.parseDouble(parts[4]));
			break;
		case "MusicCD":
			media = new MusicCD(Integer.parseInt(parts[1]), parts[2], Integer.parseInt(parts[3]), Integer.parseInt(parts[4]));
			break;
			default:
				return null;			
		}
		media.setAvailable(Boolean.parseBoolean(parts[5]));
		return media;
	}
}

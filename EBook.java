import java.util.Calendar;

//EBook is a Media
public class EBook extends Media {

	
	//Child attribute
	private int numChapters;
	
	//Inherited constructor
	public EBook(int id, String title, int year, int chapters) {
		super(id, title, year);
		numChapters = chapters;
	}
	
    //Empty constructor
	public EBook() {
		
	}

	//Get method
	public int getNumChapters() {
		return numChapters;
	}
	
	//Set method
	public void setNumChapters(int numChapters) {
		this.numChapters = numChapters;
	}
	
	//Overridden calculateRentalFee method to calculate the rental fee for an EBook
	@Override
	public double calculateRentalFee() {
		
		double fee = numChapters * 0.10;
		int currYear = Calendar.getInstance().get(Calendar.YEAR);
		
		if(this.getYear() == currYear)
			fee += 1; //add 1.00 fee
		return fee;
	}
	
	//Overridden toString method
	@Override 
	public String toString() {
		return "EBook [id= " + getId() + ", title= " + getTitle()
		+ ", year= " + getYear() + ", chapters= " + numChapters + ", availability for rent = " + isAvailable() + 
		"]";
	}
    
	//Overridden serialized method
	@Override
	public String serialized() {
		
		return "EBook;" + getId() + "," + getTitle() + "," + getYear() + "," + numChapters + "," + isAvailable();
	}
	
}

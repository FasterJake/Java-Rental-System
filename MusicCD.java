import java.util.Calendar;

//MusicCD is a media
public class MusicCD extends Media {
	
	//Empty constructor
    public MusicCD() {
		
	}
	
	
	//Child Attribute
	private int length; //Value in minutes
	
	//Inherited constructor
	public MusicCD(int id, String title, int year, int length) {
		super(id, title, year);
		this.length = length;
	}
	
	

	//get method
	public int getLength() {
		return length;
	}
	
	//set method
	public void setLength(int length) {
		this.length = length;
	}
	
	//Overridden calculateRentalFee
	@Override
	public double calculateRentalFee() {
		double fee = length * 0.02; //basic fee
		int currYear = Calendar.getInstance().get(Calendar.YEAR);
		
		if(this.getYear() == currYear)
			fee +=1; //adds 1.00 to fee
		
		return fee;
	}
	
	//Overridden toString method
	@Override
	public String toString() {
		return "MusicCD [id= " +getId() + ", title= " + getTitle() + ", year= "
				+ getYear() + ", length= " + length + " min, availability for rent = " + isAvailable() + "]";
	}
    
	//Overridden serialized method
	@Override
	public String serialized() {
		return "MusicCD;" + getId() + "," + getTitle() + "," + getYear() + "," + length + "," + isAvailable();
	}
}


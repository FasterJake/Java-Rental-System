
//MovieDVD is a media
public class MovieDVD extends Media {
	
	//Empty constructor
	public MovieDVD() {
		
	}
	//Child attribute
	private double size; //value in MB
	
	public MovieDVD(int id, String title, int year, double size) {
		super(id, title, year);
		this.size = size;
	}
	
	
	//Get method
	public double getSize() {
		return size;
	}
	
	//Set method
	public void setSize(double size) {
		this.size = size;
	}
	
	//Inherits calculate rental from parent and no different calculation so should not override
	//Overridden toString Method
	@Override
	public String toString() {
		return "MovieDVD [id= " + getId() + ", title= " + getTitle() + ", year= "
				+ getYear() + ", size= " + size + " MB, availability for rent = " + isAvailable() + "]";
	}
	
	//Overridden serialized method
	@Override
	public String serialized() {
		return "MovieDVD;" + getId() + "," + getTitle() + "," + getYear() + "," + size + "," + isAvailable();
	}

}

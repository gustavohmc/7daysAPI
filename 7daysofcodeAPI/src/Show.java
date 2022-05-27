
public class Show {
	private String title;
	private String imageURL;
	private String year;
	private String rating;
	
	public Show(String title, String imageURL, String year, String rating) {
		super();
		this.title = title;
		this.imageURL = imageURL;
		this.year = year;
		this.rating = rating;
	}
	
	@Override
	public String toString() {
		return ("[TITLE: " + this.title + ", YEAR: " + this.year + ", RATING: " + this.rating + "]");
	}
}

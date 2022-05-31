import java.io.PrintWriter;
import java.util.List;

public class HtmlGenerator {
	
	private PrintWriter pw;

	public HtmlGenerator(PrintWriter pw) {
		this.pw = pw;
	}
	
	void generate(List<Show> shows) {
		pw.println("<html>");
		pw.println("<head>");
		pw.println("</head>");
		pw.println("<body>");
		for(Show show:shows) {
			pw.println("<p>" + show.getTitle() + "</p>");
			pw.println("<img src=\"" + show.getImageURL() + "\">");
			pw.println("<p>Year: " + show.getYear() + "</p>");
			pw.println("<p>Rating: " + show.getRating() + "</p>");
		}
		pw.println("</body>");
		pw.println("</html>");
	}
	
}

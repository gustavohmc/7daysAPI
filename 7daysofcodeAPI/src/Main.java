import java.io.IOException;
import java.io.PrintWriter;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

public class Main {

	public static void main(String[] args) throws IOException, InterruptedException {
		String apiKey = "apikey";
		URI apiIMDB = URI.create("https://imdb-api.com/en/API/Top250TVs/" + apiKey);
		
		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder().uri(apiIMDB).build();

		HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
		String json = response.body();
		
		String[] tvShows = parseJson(json);
		
		List<Show> tvShowsList = showListBuilder(tvShows);

		PrintWriter pw = new PrintWriter("../index.html");
		
		new HtmlGenerator(pw).generate(tvShowsList);
		
		pw.close();
	}
	
	private static String[] parseJson(String json) {
		String trimmedJSON = StringUtils.substringBetween(json,"[", "]");
		String[] results = trimmedJSON.split("},");
		return results;
	}
	
	private static String parseItem(String[] shows, String item, int index ){
		String pattern = item + "\":\"";
		return StringUtils.substringBetween(shows[index], pattern, "\"");		
		
	}
	
	private static List<Show> showListBuilder(String[] shows){
		String title, imageURL, year, rating = "";
		List<Show> results = new ArrayList<>();
		for(int i=0;i<shows.length;i++) {
			title = parseItem(shows, "title", i);
			imageURL = parseItem(shows, "image", i);
			year = parseItem(shows, "year", i);
			rating = parseItem(shows, "Rating", i);
			results.add(new Show(title, imageURL, year, rating));
		}
		return results;
	}

}

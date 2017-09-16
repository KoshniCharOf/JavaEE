package scrape;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Scanner;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class ExtractToJSON {

	private static HashSet<JsonElement> set = new HashSet<>();

	public static void main(String[] args) {

		scrapeLinks();

	}

	static void scrapeLinks() {

		Scanner sc1 = new Scanner(System.in);

		try (BufferedWriter buf = new BufferedWriter(new PrintWriter(new File("Extract.json")));) {

			Document doc = null;
			while (doc == null) {
				try {
					System.out.println("Enter URL like this: https://www.abv.bg/");
					doc = Jsoup.connect(sc1.nextLine()).get();
				} catch (IllegalArgumentException r) {
					continue;
				}
			}

			Elements links = doc.select("a[href]");

			for (Element link : links) {
				// experiment
				JsonObject je = new JsonObject();
				je.addProperty(link.text(), link.attr("href"));
				set.add(je);
			}
			buf.write(new Gson().toJson(set));

		} catch (FileNotFoundException e) {
			System.out.println("FileNotFound");
		} catch (IOException e1) {
			System.out.println("Opps..." + e1.getMessage());
		} finally {
			sc1.close();
		}

	}

}

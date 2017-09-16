package scrape;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class ExtractToXML {
	
	public static void main(String[] args) {

		HashSet<String> xmlSet = new HashSet<>();

		try (BufferedWriter buffXML = new BufferedWriter(new PrintWriter(new File("Extract.xml")))) {
			Document doc = Jsoup.connect("https://www.abv.bg/").post();
			Elements links = doc.select("a[href]");

			for (Element link : links) {
				xmlSet.add(link.attr("href"));
			}

			buffXML.write(new XStream(new DomDriver()).toXML(xmlSet));

		} catch (FileNotFoundException e) {

		} catch (IOException e1) {

		}
	}

}

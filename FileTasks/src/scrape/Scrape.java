package scrape;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.net.URL;
import java.util.Scanner;

public class Scrape {
	public static void main(String[] args) {

		
		try (BufferedReader br = new BufferedReader(new FileReader(new File(scrapeLinks())))){
			while (true) {
				if(!br.ready()) {
					break;
				}
				System.out.println(br.readLine());
			}
		} catch (FileNotFoundException e) {
			System.out.println("Something went wrong");
		} catch (IOException e) {
			System.out.println("Something big went wrong");
		}

	}

	static  String scrapeLinks() throws IOException {
		
		URL url = new URL("https://www.abv.bg");
		InputStream in = url.openStream();
		Scanner sc = new Scanner(in);

		File links = new File("links2.txt");
		PrintStream ps = new PrintStream(links);

		while (sc.hasNextLine()) {
			String line = sc.nextLine();
//(http|ftp|https)://([\w_-]+(?:(?:\.[\w_-]+)+))([\w.,@?^=%&:/~+#-]*[\w@?^=%&/~+#-])?

			if (line.contains("http")) {

				int start = line.indexOf("http");
				String link = line.substring(start);
				int end = link.indexOf("\"");
				end = end < 0 ? link.indexOf("\'") : link.indexOf("\"");

				link = link.substring(0, end);
				ps.print(link + "\n");

				// TODO recursive call
				//scrapeLinks(site, level-1);

			}
		}
		
		sc.close();
		ps.close();
		in.close();
		return links.getPath();

	}

}

package bookStat;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class BookStatXML {
	
	public static void main(String[] args) {
		
		
		//chain for sport
		try (BufferedWriter buff = new BufferedWriter(new PrintWriter(new File("statsXML.xml")))) {
			new XStream(new DomDriver()).toXML(new Statistic(new File("voina_i_mir.txt")), buff);
		} catch (IOException e) {
			System.out.println("No file, no party");
		}

	}

}

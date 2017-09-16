package bookStat;

import java.io.BufferedWriter;
import java.io.File;
import java.io.PrintWriter;

import com.google.gson.Gson;

public class BookStat_JSON {


	public static void main(String[] args) {
		
		
		Statistic stat = new Statistic(new File("voina_i_mir.txt"));
		
		//Serialize
		try(BufferedWriter bur = new BufferedWriter(new PrintWriter(new File("StatsJSON.json")))) {
			bur.write(new Gson().toJson(stat));
		} catch (Exception e) {
			System.out.println("Ops..."+e.getMessage());
		}
		
	}

}

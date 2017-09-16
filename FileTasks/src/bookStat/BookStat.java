package bookStat;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.TreeSet;

public class BookStat {

	public static void main(String[] args)  {

		File stats = null;
		try {
			stats = getStats(new File("voina_i_mir.txt"));
		} catch (FileNotFoundException e) {
			System.out.println("File Not Found");
		}

		// print file
		try (BufferedReader buf = new BufferedReader(new FileReader(stats), 10)) {
			while (buf.ready()) {
				System.out.println(buf.readLine());
			}
		} catch (FileNotFoundException e) {
			System.out.println("No stats file");
		} catch (IOException e) {
			System.out.println("Big mistake");
		}
		
	}

	public static File getStats(File f) throws FileNotFoundException {
		System.out.println(f.getName());
		// read words
		System.out.println("Enter words separated by space and get appearances");
		String[] wordsSearch;
		Scanner sc1 = new Scanner(System.in);
		do {
			wordsSearch = sc1.nextLine().split(" ");
		} while (wordsSearch.length < 2);
		sc1.close();

		// read file
		Scanner sc = new Scanner(f);
		int count = 0;
		//nice to make it again
		int[] appearances = new int[wordsSearch.length];
		
		int countcomma = 0;
		String longest = "";
		HashMap<String, Integer> words = new HashMap<>();
		TreeSet<String> byLength = new TreeSet<>((a, b) -> {
			if (a.length() == b.length()) {
				return a.compareToIgnoreCase(b);
			}
			return b.length() - a.length();
		});
		while (sc.hasNext()) {
			count++;
			String word = sc.next();
			//count appearances of searched words
			for (int i = 0; i < wordsSearch.length; i++) {
				if(word.equalsIgnoreCase(wordsSearch[i])) {
					appearances[i]++;
				}
			}

			if (word.contains(",")) {
				countcomma++;
			}

			if (!words.containsKey(word)) {
				words.put(word, 1);
			} else {
				words.put(word, words.get(word) + 1);
			}
			
			if (word.length() > longest.length()) {
				longest = word;
			}
		}

		TreeSet<Entry<String, Integer>> mostUsed = new TreeSet<Entry<String, Integer>>(
				new Comparator<Entry<String, Integer>>() {
					@Override
					public int compare(Entry<String, Integer> o1, Entry<String, Integer> o2) {
						if (o1.getValue() == o2.getValue()) {
							return o1.getKey().compareTo(o2.getKey());
						}
						return o1.getValue() - o2.getValue();
					}
				});
		for (Entry<String, Integer> entry : words.entrySet()) {
			mostUsed.add(entry);
			byLength.add(entry.getKey());
		}
//		for (Entry<String, Integer> entry : mostUsed) {
//			System.out.println(entry.getKey() + " " + entry.getValue());
//		}

		File stats = new File(f.getName() + " Statistics.txt");
		PrintStream ps = new PrintStream(stats);
		ps.println(f.getName() + " Statistics: \n");
		for (int i = 0; i < wordsSearch.length; i++) {
			ps.println(wordsSearch[i] +" = "+ appearances[i]+" ");
		}
		ps.println("Most used word: " + mostUsed.last());
		ps.println("Longest word: " + longest);
		ps.println("comma count = " + countcomma);
		ps.println("words = " + count);
		int counter = 10;
		ps.println("Top " + counter + " words by lenght: ");
		for (String str : byLength) {
			ps.println(str + " " + str.length());
			if (--counter == 0) {
				break;
			}
		}
		ps.close();
		sc.close();
		return stats;
	}

}

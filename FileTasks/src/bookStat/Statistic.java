package bookStat;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.Map.Entry;

public class Statistic {
	
	private File file;
	
	private transient HashSet<String> allWords = new HashSet<>();
	private TreeMap<String, Integer> searchedWords = new TreeMap<>();
	private HashMap<String, Integer> mostUsedWord = new HashMap<>();
	private String longestWord;
	private int commaCount;
	private int wordsCount;
	private TreeMap<String, Integer> topTenLongest = new TreeMap<>();

	public Statistic(File f) {

		this.file = f;
		loadFields();
	}
	
	
	private void loadFields() {
		System.out.println("Enter words separated by space and get appearances");
		String[] wordsSearch;
		Scanner sc1 = new Scanner(System.in);
		do {
			wordsSearch = sc1.nextLine().split(" ");
		} while (wordsSearch.length < 2);
		sc1.close();
		
		Scanner sc = null;
		try {
			sc = new Scanner(this.file);
		} catch (FileNotFoundException e) {
			System.out.println("File Not Found");
		}
		
		HashMap<String, Integer> usedCount = new HashMap<>();
		while (sc.hasNext()) {
			String word = sc.next().toLowerCase();
			this.allWords.add(word);
			//count appearances of searched words
			for (String string : wordsSearch) {
				if(!string.equalsIgnoreCase(word)) {
					continue;
				}
				if(!this.searchedWords.containsKey(word)) {
					this.searchedWords.put(word, 1);
				}else {
					this.searchedWords.put(word, this.searchedWords.get(word)+1);
				}
			}
			
			//find most used word may be to slow algorithm
			if(!usedCount.containsKey(word)) {
				usedCount.put(word, 1);
			}else {
				usedCount.put(word, usedCount.get(word)+1);
			}
			
			//Counter fields
			this.wordsCount++;
			if(word.contains(",")) {
				this.commaCount++;
			}
			
		}
		
		//top ten longest
		TreeSet<String> longest = new TreeSet<String>((a,b)->b.length()-a.length());
		longest.addAll(allWords);
		int counter = 10;
		for (String str : longest) {
			this.topTenLongest.put(str, str.length());
			if (--counter == 0) {
				break;
			}
		}
		
		//Find longest word
		this.longestWord = longest.first();
		
		//most used continued
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
		for (Entry<String, Integer> entry : usedCount.entrySet()) {
			mostUsed.add(entry);
		}
		this.mostUsedWord.put(mostUsed.last().getKey(), mostUsed.last().getValue());
	}


	

}

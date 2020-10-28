import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Stream;

public class WordFrequency {

	public void printResult(String fileName) {

		Map<String, Integer> wordMap = new HashMap<String, Integer>();
		String lastSentence = null;
		try (Stream<String> stream = Files.lines(Paths.get(fileName))) {
			int maxWordCount = 0;
			int curCount;
			Iterator<String> ite = stream.iterator();
			while (ite.hasNext()) {
				String[] sentences = ite.next().trim().toLowerCase().split("[!?.]+");
				for (String sentence : sentences) {
					for (String token : sentence.trim().split("[!?.,:\\\" ]+")) {
						if (token.length() > 0) {
							curCount = wordMap.compute(token, (k, v) -> (v == null) ? 1 : v + 1);
							if (curCount >= maxWordCount) { // to find the most used word and it's last sentence
								maxWordCount = curCount;
								lastSentence = sentence.trim();
							}
						}
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		int totalWordCount = 0;
		for (int count : wordMap.values()) {
			totalWordCount += count;
		}
		System.out.println("Total words in the given file: " + totalWordCount);

		System.out.println("Top 10 frequent words:");
		getTopTenWords(wordMap).forEach(entry -> {
			System.out.println("Word : " + entry.getKey() + " Count : " + entry.getValue());
		});

		System.out.println("Last sentence of the most frequently used word :" + lastSentence + ".");

	}
	
	// to identify the top 10 words
	private List<Entry<String, Integer>> getTopTenWords(Map<String, Integer> wordMap) {

		List<Entry<String, Integer>> list = new ArrayList<Entry<String, Integer>>(wordMap.entrySet());
		
		// sorting based on the highest frequency, if the frequency is same then sorting lexicographically
		Collections.sort(list, (o1, o2) -> (o2.getValue() != o1.getValue() ? (o2.getValue() - o1.getValue())
				: o1.getKey().compareTo(o2.getKey())));
		return list.subList(0, 10);
	}

	public static void main(String a[]) {
		new WordFrequency().printResult("passage.txt");
	}
}

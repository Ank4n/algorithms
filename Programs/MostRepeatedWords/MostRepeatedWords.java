
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MostRepeatedWords {

    public static void mostRepeatedWords(String inputFile, int k) throws Exception {

        Map<String, Integer> wordCount = new HashMap<>();
        int maxCount = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(inputFile));) {

            String line;

            while ((line = br.readLine()) != null) {
                String[] words = line.split("^[a-zA-Z]+|\\s+|\\p{Punct}");

                for (String word : words) {

                    if (word.isEmpty())
                        continue;

                    int count = 1;

                    // if word already exist, increment count
                    if (wordCount.containsKey(word))
                        count += wordCount.get(word);

                    wordCount.put(word, count);

                    // update maxCount
                    if (count > maxCount)
                        maxCount = count;
                }
            }
        }

        List<List<String>> frequencyList = new ArrayList<List<String>>(maxCount + 1);
        for (int i = 0; i < maxCount + 1; i++)
            frequencyList.add(i, null);

        // traverse through the word list and index words using its count
        for (String word : wordCount.keySet()) {

            int count = wordCount.get(word);

            // if this is the first word for the index, create a new arraylist
            if (frequencyList.get(count) == null)
                frequencyList.set(count, new ArrayList<String>());

            frequencyList.get(count).add(word);
        }

        int wordsRemaining = k;

        // print top k words
        for (int i = frequencyList.size() - 1; i >= 0; i--) {

            List<String> indexedWords = frequencyList.get(i);

            if (indexedWords == null)
                continue;

            Collections.sort(indexedWords);

            for (int j = indexedWords.size() - 1; j >= 0; j--) {

                if (wordsRemaining <= 0)
                    return;

                System.out.println("\"" + indexedWords.get(j) + "\" has been used " + i + " times.");
                wordsRemaining--;
            }

        }

    }

    public static void main(String[] args) throws Exception {
        mostRepeatedWords("input1.txt", 5);
        mostRepeatedWords("input1.txt", 6);
    }

}

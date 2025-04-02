
import java.util.Arrays;
import java.util.HashMap;
import java.util.Set;
import java.util.stream.Collectors;

/*
 * @source https://leetcode.com/problems/most-common-word/
 * @author xiaoque
 * @date 2025.03.22
 */
public class MostCommonWord {
    public String mostCommonWord(String paragraph, String[] banned) {
        // replace all symbols in the para, split into array by " "
        // count the frequency of each word using hashmap, get the one not in banned
        String paraWithoutSymbols = paragraph.replaceAll("[^a-zA-Z]", " ").toLowerCase();
        HashMap<String, Integer> wordFreq = new HashMap<>();
        Set<String> hashBanned = Arrays.stream(banned).collect(Collectors.toSet());

        for (String word : paraWithoutSymbols.split(" ")) {
            if (!hashBanned.contains(word) && word != null && !word.isEmpty()) {
                wordFreq.put(word, wordFreq.getOrDefault(word, 0) + 1);
            }
        }

        String rest = null;
        int count = 0;
        for (String key : wordFreq.keySet()) {
            if (wordFreq.get(key) > count) {
                rest = key;
                count = wordFreq.get(key);
            }
        }
        return rest;
    }
}


/*
 * @source https://leetcode.com/problems/find-words-that-can-be-formed-by-characters/
 * @author xiaoque
 * @date 2025.03.22
 */
public class FindWordsCanBeFormedByCharcaters {
    public int countCharacters(String[] words, String chars) {
        // first count char in chars
        // for each words, we see if there is a match
        int[] charCounts = new int[26];

        for (char c : chars.toCharArray()) {
            charCounts[c - 'a']++;
        }

        int result = 0;
        for (String word : words) {
            if (word.length() <= chars.length()) {
                int[] copy = charCounts.clone();
                result += word.length();
                for (char c : word.toCharArray()) {
                    int index = c - 'a';
                    if (copy[index] < 1) {
                        result -= word.length();
                        break;
                    } else {
                        copy[index]--;
                    }
                }
            }
        }
        return result;
    }
}

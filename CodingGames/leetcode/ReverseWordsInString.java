
/*
 * @source https://leetcode.com/problems/reverse-words-in-a-string/
 * @author xiaoque
 * @date 2025.03.22
 */
public class ReverseWordsInString {
    public String reverseWords(String s) {
        String[] words = s.trim().split("\\s+");
        StringBuilder sb = new StringBuilder();
        for (int i = words.length - 1; i >= 0; i--) {
            sb.append(words[i]).append(i == 0 ? "" : " ");
        }
        return sb.toString();
    }
}

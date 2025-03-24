package leetcode;

/*
 * @source http://leetcode.com/problems/reverse-vowels-of-a-string/
 * @author xiaoque
 * @date 2025.03.22
 */
public class ReverseVowelsString {
    public String reverseVowels(String s) {
        // find vowels from 2 directions, and swap the two once found
        int prev = 0, sub = s.length() - 1;
        char[] charArray = s.toCharArray();
        while (prev < sub) {
            while (prev < s.length() && !isVowels(charArray[prev])) {
                prev++;
            }
            while (sub >= 0 && !isVowels(charArray[sub])) {
                sub--;
            }
            if (prev < sub) {
                char tmp = charArray[prev];
                charArray[prev] = charArray[sub];
                charArray[sub] = tmp;
                prev++;
                sub--;
            }
        }
        return new String(charArray);
    }

    private boolean isVowels(char c) {
        return "aeiouAEIOU".indexOf(c) != -1;
    }
}

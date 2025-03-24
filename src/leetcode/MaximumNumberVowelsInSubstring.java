package leetcode;

/*
 * @source https://leetcode.com/problems/maximum-number-of-vowels-in-a-substring-of-given-length/
 * @author xiaoque
 * @date 2025.03.24
 */
public class MaximumNumberVowelsInSubstring {
    // Note: s only has lowercases
    public int maxVowels(String s, int k) {
        /*
         * initial with a substring of length k
         * loop to find max count
         */

        int count = 0, maxCount = 0;
        char[] charArr = s.toCharArray();
        // inital case
        for (int i = 0; i < k; i++) {
            if (isVowel(charArr[i]))
                count++;
        }
        maxCount = count;
        // move window
        for (int i = k; i < charArr.length; i++) {
            if (isVowel(charArr[i]))
                count++;
            if (isVowel(charArr[i - k]))
                count--;
            maxCount = Math.max(maxCount, count);
        }
        return maxCount;
    }

    private boolean isVowel(char c) {
        return "aeiou".indexOf(c) != -1;
    }
}

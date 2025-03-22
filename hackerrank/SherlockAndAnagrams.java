import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/*
* @source https://www.hackerrank.com/challenges/sherlock-and-squares/problem
* @author xiaoque55
* @date 2021.06.10
*/

class Result {

    /*
     * Complete the 'sherlockAndAnagrams' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts STRING s as parameter.
     */

    // rearrange substrings
    private static String rearrangeStr(String s) {
        char[] arr = s.toCharArray();
        Arrays.sort(arr);
        return new String(arr);
    }

    public static int sherlockAndAnagrams(String s) {
        int count = 0;
        for (int length = 1; length < s.length(); ++length) {
            Map<String, Integer> map = new HashMap<>();

            // check substrings with same lengths
            for (int i = 0; i <= s.length() - length; ++i) {
                String substr = rearrangeStr(s.substring(i, i + length));
                if (map.containsKey(substr)) {
                    count += map.get(substr);
                }
                map.put(substr, map.getOrDefault(substr, 0) + 1);
            }
        }
        return count;
    }

}

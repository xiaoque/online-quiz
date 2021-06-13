import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

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
    private static String rearrangeStr(String s){
        char[] arr = s.toCharArray(); 
        Arrays.sort(arr);
        return new String(arr);     
    }

    public static int sherlockAndAnagrams(String s) {
        int count = 0;
        for(int length = 1; length < s.length(); ++length){
            Map<String, Integer> map = new HashMap<>();
            
            // check substrings with same lengths
            for(int i = 0; i <= s.length() - length; ++i){
                String substr = rearrangeStr(s.substring(i, i+length));
                if(map.containsKey(substr)){
                    count += map.get(substr);
                }
                map.put(substr, map.getOrDefault(substr, 0) + 1);
            }
        }
        return count;
    }

}

// provided by hackerrank
public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int q = Integer.parseInt(bufferedReader.readLine().trim());

        IntStream.range(0, q).forEach(qItr -> {
            try {
                String s = bufferedReader.readLine();

                int result = Result.sherlockAndAnagrams(s);

                bufferedWriter.write(String.valueOf(result));
                bufferedWriter.newLine();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        bufferedReader.close();
        bufferedWriter.close();
    }
}


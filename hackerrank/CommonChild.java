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

class Result {

    /*
     * Complete the 'commonChild' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts following parameters:
     *  1. STRING s1
     *  2. STRING s2
     */

/*
* @source https://www.hackerrank.com/challenges/common-child/problem?
* @author xiaoque55
* @date 2021.06.22
*
* Useful link: https://en.wikipedia.org/wiki/Longest_common_subsequence_problem 
*/

    public static int commonChild(String s1, String s2) {
    // Write your code here
        int[][] matrix = new int[s1.length()+1][s2.length()+1];
        
        for (int i = 0; i < s1.length(); ++i) {
            for (int j = 0; j < s2.length(); ++j) {
                if (s1.charAt(i) == s2.charAt(j)) {
                    matrix[i + 1][j + 1] = matrix[i][j] + 1;
                } else {
                    matrix[i + 1][j + 1] = Math.max(matrix[i][j + 1], matrix[i + 1][j]);
                }
            }
        }
        
        return matrix[s1.length()][s2.length()];
    }

}

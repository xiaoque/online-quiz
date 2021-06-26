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
     * Complete the 'countInversions' function below.
     *
     * The function is expected to return a LONG_INTEGER.
     * The function accepts INTEGER_ARRAY arr as parameter.
     */
/*
* @source https://www.hackerrank.com/challenges/ctci-merge-sort/problem
* @author xiaoque55
* @date 2021.06.13

*/
    public static long countInversions(List<Integer> arr) {
    // Write your code here
        int[] tmpArr = new int[arr.size()];
        return mergeSort(arr, 0, arr.size() - 1, tmpArr);
    }
    
    // Passing the temp array can reduce the running time comparing to create a new one at each call
    public static long mergeSort(List<Integer> arr, int left, int right, int[] tmpArr) {
        if (left >= right) return 0L;
        
        int mid  = left + (right - left) / 2;
        return mergeSort(arr, left, mid, tmpArr)
                + mergeSort(arr, mid + 1, right, tmpArr)
                + merge(arr, left, mid, right, tmpArr);
    }
    

    public static long merge(List<Integer> arr, int left, int mid, int right, int[] tmpArr) {
        long count = 0;
        int start1 = left, start2 = mid + 1;
        int pos = left;
        while (start1 <= mid || start2 <= right) {
            if (start1 > mid) {
                tmpArr[pos++] = arr.get(start2++);
            } else if (start2 > right) {
                tmpArr[pos++] = arr.get(start1++);
            } else if (arr.get(start1) <= arr.get(start2)) {
                tmpArr[pos++] = arr.get(start1++);
            } else {
                tmpArr[pos++] = arr.get(start2++);
                count += mid + 1 - start1;
            }
        }
        int tmp = left;
        while (tmp <= right) {
            arr.set(tmp, tmpArr[tmp++]);
        }
        return count;
    }
}

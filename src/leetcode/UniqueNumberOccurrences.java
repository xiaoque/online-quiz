package leetcode;

import java.util.Arrays;

/*
 * @source https://leetcode.com/problems/unique-number-of-occurrences/
 * @author xiaoque
 * @date 2025.03.24
 */
public class UniqueNumberOccurrences {
    public boolean uniqueOccurrences(int[] arr) {
        // sort the array, so that we can easily count the occurance
        // int[] to store the freq of count

        Arrays.sort(arr);
        // for an arry of length n, max(count) = n
        int[] counts = new int[arr.length + 1];
        int curr = 1;
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] == arr[i - 1]) {
                curr++;
            } else {
                if (counts[curr] == 1)
                    return false;
                counts[curr]++;
                curr = 1;
            }
        }
        // the count of last element in arr
        if (counts[curr] == 0)
            return true;
        return false;
    }
}

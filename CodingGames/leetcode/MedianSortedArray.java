
/*
 * @source https://leetcode.com/problems/median-of-two-sorted-arrays/
 * @author xiaoque
 * @date 2025.03.22
 */
public class MedianSortedArray {
    // brut force, use 2 pointers to find the mid index
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        // for even length = 8, mid = arr[3]+arr[4], odd length = 7, mid = arr[3]
        // need 2 var to store prev value in case of even
        int i = 0, j = 0, curr = 0, prev = 0;
        int m = nums1.length;
        int n = nums2.length;

        for (int c = 0; c <= (m + n) / 2; c++) {
            prev = curr;
            if (i < m && j < n) {
                if (nums1[i] < nums2[j]) {
                    curr = nums1[i++];
                } else {
                    curr = nums2[j++];
                }
            } else if (i < m) {
                curr = nums1[i++];
            } else {
                curr = nums2[j++];
            }
        }
        return (m + n) % 2 == 1 ? curr : 1.0 * (prev + curr) / 2;
    }



    public static void main(String[] args) {

    }
}

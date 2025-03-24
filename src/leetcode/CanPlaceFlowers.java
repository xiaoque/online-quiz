package leetcode;

/*
 * @source https://leetcode.com/problems/can-place-flowers/
 * @author xiaoque
 * @date 2025.03.22
 */
public class CanPlaceFlowers {
    public boolean canPlaceFlowers(int[] flowerbed, int n) {
        // to count max flower can be added
        // index for previous 1, and length > 2, count += length / 2
        int prev = -2, count = 0;
        // if arr[0] == 0, we can suppose {1, 0, arr[0]}
        // to make sure arr[0] can be planted, we need{1, 0, arr[0], 0, 1}
        // so prev starts with -2
        for (int i = 0; i < flowerbed.length; i++) {
            if (flowerbed[i] == 1) {
                int length = i - prev - 1;
                count += (length - 1) / 2;
                prev = i;
            }
        }
        // check if last are 0s
        if (flowerbed[flowerbed.length - 1] == 0) {
            int length = flowerbed.length - prev;
            count += (length - 1) / 2;
        }
        return count >= n;
    }
}

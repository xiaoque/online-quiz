
import java.util.Arrays;

/*
 * @source https://leetcode.com/problems/koko-eating-bananas/
 * @author xiaoque
 * @date 2025.03.25
 */
public class KokoEatingBananas {
    /**
     * Note: range from 1 - max, binary search
     */
    public int minEatingSpeed(int[] piles, int h) {
        Arrays.sort(piles);
        int left = 1, right = piles[piles.length - 1];
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (totalTimeInH(piles, mid, h))
                right = mid - 1;
            else
                left = mid + 1;
        }
        return left;
    }

    private boolean totalTimeInH(int[] piles, int k, int h) {
        int cnt = 0;
        for (int pile : piles) {
            cnt += pile % k == 0 ? pile / k : pile / k + 1;
            if (cnt > h)
                return false;
        }
        return cnt <= h;
    }
}

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 * @source https://leetcode.com/problems/kids-with-the-greatest-number-of-candies/
 * @author xiaoque
 * @date 2025.04.26
 */
public class KidsWithGreatestNumberCandies {
    public List<Boolean> kidsWithCandies(int[] candies, int extraCandies) {
        int max = Arrays.stream(candies).max().getAsInt();
        List<Boolean> result = new ArrayList<>();
        for (int candy : candies) {
            result.add(candy + extraCandies >= max);
        }

        return result;
    }
}


import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/*
 * @source https://leetcode.com/problems/find-the-difference-of-two-arrays/
 * @author xiaoque
 * @date 2025.03.24
 */
public class FindDifferenceTwoArrays {
    public List<List<Integer>> findDifference(int[] nums1, int[] nums2) {
        // brut force, 2 set
        Set<Integer> set1 = new HashSet<>();
        Set<Integer> set2 = new HashSet<>();
        for (int num : nums1) {
            set1.add(num);
        }

        for (int num : nums2) {
            set2.add(num);
        }

        List<Integer> list1 = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();
        for (int num : set1) {
            if (!set2.contains(num))
                list1.add(num);
        }
        for (int num : set2) {
            if (!set1.contains(num))
                list2.add(num);
        }

        return Arrays.asList(list1, list2);
    }
}

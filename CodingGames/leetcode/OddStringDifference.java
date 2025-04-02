
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * @source https://leetcode.com/problems/odd-string-difference/
 * @author xiaoque
 * @date 2025.03.22
 */
public class OddStringDifference {
    public String oddString(String[] words) {
        // store the diff array as string into map with index of the word like (11,{1,
        // 4,5})
        // when the map.key.size > 1 then return the value.size == 1

        Map<String, List<Integer>> diffMap = new HashMap<>();
        int index = 0;
        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            int[] diff = new int[word.length() - 1];
            for (int j = 0; j < word.length() - 1; j++) {
                diff[j] = word.charAt(j + 1) - word.charAt(j);
            }
            diffMap.computeIfAbsent(Arrays.toString(diff), x -> new ArrayList<Integer>()).add(i);
            if (diffMap.keySet().size() > 1 && i > 1) {
                index = findIndex(new ArrayList<>((diffMap.values())));
                if (index >= 0) {
                    break;
                }
            }
        }
        return words[index];
    }

    private int findIndex(List<List<Integer>> values) {
        for (List<Integer> list : values) {
            if (list.size() == 1) {
                return list.getFirst();
            }
        }
        return -1;
    }
}

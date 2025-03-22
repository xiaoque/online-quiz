import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FreqQuery {

    /*
     * @source https://www.hackerrank.com/challenges/frequency-queries/problem
     * 
     * @author xiaoque55
     * 
     * @date 2021.06.11
     */
    // simple brute-force approach, just remember to remove the num with 0 frequency
    static List<Integer> freqQuery(List<List<Integer>> queries) {
        Map<Integer, Integer> map = new HashMap<>();
        List<Integer> res = new ArrayList<>();

        for (int i = 0; i < queries.size(); ++i) {
            int op = queries.get(i).get(0);
            int num = queries.get(i).get(1);
            if (op == 1)
                map.put(num, map.getOrDefault(num, 0) + 1);
            else if (op == 2 && map.containsKey(num)) {
                if (map.get(num) == 1)
                    map.remove(num);
                else
                    map.put(num, map.get(num) - 1);
            } else if (op == 3) {
                if (num > i + 1)
                    res.add(0);
                else {
                    if (map.values().contains(num))
                        res.add(1);
                    else
                        res.add(0);
                }
            }
        }
        return res;
    }
}

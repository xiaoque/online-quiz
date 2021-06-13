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

public class Solution {

/*
* @source https://www.hackerrank.com/challenges/frequency-queries/problem
* @author xiaoque55
* @date 2021.06.11
*/
    // simple brute-force approach, just remember to remove the num with 0 frequency 
    static List<Integer> freqQuery(List<List<Integer>> queries) {
        Map<Integer, Integer> map = new HashMap<>();
        List<Integer> res = new ArrayList<>();

        for(int i = 0; i < queries.size(); ++i){
            int op = queries.get(i).get(0);
            int num = queries.get(i).get(1);
            if(op == 1)
                map.put(num, map.getOrDefault(num, 0) + 1);
            else if(op == 2 && map.containsKey(num)){
                if(map.get(num) == 1)
                    map.remove(num);
                    else map.put(num, map.get(num) - 1);
            }
            else if(op == 3){
                if(num > i + 1) 
                    res.add(0);
                else{
                    if(map.values().contains(num))
                        res.add(1);
                    else res.add(0);
                }
            }
        }
        return res;
    }

// provided by hackerrank
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int q = Integer.parseInt(bufferedReader.readLine().trim());

        List<List<Integer>> queries = new ArrayList<>();

        IntStream.range(0, q).forEach(i -> {
            try {
                queries.add(
                    Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                        .map(Integer::parseInt)
                        .collect(toList())
                );
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        List<Integer> ans = freqQuery(queries);

        bufferedWriter.write(
            ans.stream()
                .map(Object::toString)
                .collect(joining("\n"))
            + "\n"
        );

        bufferedReader.close();
        bufferedWriter.close();
    }
}

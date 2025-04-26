import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/*
 * @source https://leetcode.com/problems/search-suggestions-system/
 * @author xiaoque
 * @date 2025.04.22
 */
public class SearchSuggestionsSystem {
    // brute-force
    public List<List<String>> suggestedProducts(String[] products, String searchWord) {
        // prepare result lists
        List<List<String>> result = new LinkedList<>();
        for (int i = 0; i < searchWord.length(); i++)
            result.add(new LinkedList<>());
        // sort input arr
        Arrays.sort(products);
        // iterate products, add product to result list if prefix is a match
        int total = 0;
        for (String product : products) {
            if (total == searchWord.length() * 3)
                break;
            // iterate searchword
            int length = Math.min(product.length(), searchWord.length());
            for (int i = 0; i < length; i++) {
                if (product.charAt(i) == searchWord.charAt(i)) {
                    // get a match, add to result
                    if (result.get(i).size() < 3) {
                        result.get(i).add(product);
                        total++;
                    }
                } else
                    break;
            }
        }
        return result;
    }

    // 2-pointers
    public List<List<String>> suggestedProducts2(String[] products, String searchWord) {
        // prepare result lists
        List<List<String>> result = new LinkedList<>();
        for (int i = 0; i < searchWord.length(); i++)
            result.add(new LinkedList<>());
        // sort input arr
        Arrays.sort(products);
        // iterate chars in searchword,
        // use 2 pointer to narrow the range that share same prefix as searchword
        int left = 0, right = products.length - 1;
        for (int i = 0; i < searchWord.length(); i++) {
            char c = searchWord.charAt(i);
            // move left forward until find the prefix
            while (left <= right && (products[left].length() == i || products[left].charAt(i) < c))
                left++;
            // move right backward until find the prefix
            while (left <= right && (products[right].length() == i || products[right].charAt(i) > c))
                right--;
            // add products in range to result
            for (int j = 0; j < 3 && left + j <= right; j++) {
                result.get(i).add(products[left + j]);
            }
            // when left > right break the loop
            if (left > right)
                break;
        }
        return result;
    }
}

package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * @source https://leetcode.com/problems/people-whose-list-of-favorite-companies-is-not-a-subset-of-another-list/description/
 * @author xiaoque
 * @date 2025.03.22
 */
public class PeopleWhoseFavoriteCompaniesIsNotSubset {
    public List<Integer> peopleIndexes(List<List<String>> favoriteCompanies) {
        // convert the list into {key: company, value: person} pairs
        // a list a is a subset of other b, if all company in A contains person B
        Map<String, List<Integer>> companyByPerson = new HashMap<>();
        List<Integer> result = new ArrayList<Integer>();

        for (int i = 0; i < favoriteCompanies.size(); i++) {
            for (String company : favoriteCompanies.get(i)) {
                companyByPerson.computeIfAbsent(company, x -> new ArrayList<Integer>()).add(i);
            }
        }
        Integer[] count = new Integer[favoriteCompanies.size()];
        for (int i = 0; i < favoriteCompanies.size(); i++) {
            Arrays.fill(count, 0);
            List<String> personFav = favoriteCompanies.get(i);
            for (String company : personFav) {
                for (Integer person : companyByPerson.get(company)) {
                    if (person != i)
                        count[person]++;
                }
            }

            if (Collections.max(Arrays.asList(count)) < personFav.size()) {
                result.add(i);
            }
        }
        return result;
    }
}


/*
 * @source https://leetcode.com/problems/greatest-common-divisor-of-strings/
 * @author xiaoque
 * @date 2025.03.22
 */
public class GreatestCommonDivisorStrings {
    public String gcdOfStrings(String str1, String str2) {
        // should be one.length > other.length, otherwise should be equalsTo
        // then start from the smaller one, to compare if
        if (!(str1 + str2).equals(str2 + str1)) {
            return "";
        }

        int index = computeGcd(str1.length(), str2.length());
        return str1.substring(0, index);
    }

    private int computeGcd(int length1, int length2) {
        while (length2 != 0) {
            int tmp = length2;
            length2 = length1 % length2;
            length1 = tmp;
        }
        return length1;
    }
}

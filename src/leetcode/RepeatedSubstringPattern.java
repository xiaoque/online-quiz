package leetcode;

/*
 * @source https://leetcode.com/problems/repeated-substring-pattern/
 * @author xiaoque
 * @date 2025.03.22
 */
public class RepeatedSubstringPattern {
    // brut force way
    public boolean repeatedSubstringPattern(String s) {
        for (int i = 1; i < s.length() + 1 / 2; i++) {
            int times = s.length() / i;
            if (s.substring(0, i).repeat(times).equals(s))
                return true;
        }
        return false;
    }

    /*
     * tricky way, there is a repeated pattern s = p+p+... (at least 2 times p)
     * by double it (at least 4 times p)
     * then remove first and last index (return back to 2 times p)
     * if s is presented in new string means such p exists, otherwise false
     */
    public boolean repeatedSubstringPattern2(String s) {
        String doubledS = s + s;
        return doubledS.substring(1, doubledS.length() - 1).contains(s);
    }

}

package leetcode;

/*
 * @source https://leetcode.com/problems/lemonade-change/
 * @author xiaoque
 * @date 2025.03.22
 */
public class LemonadeChange {
    public boolean lemonadeChange(int[] bills) {
        // since 20 will never be used to change, only need to store 5 and 10
        int[] curChange = new int[2];
        for (int i : bills) {
            switch (i) {
                case 5 -> curChange[0]++;
                case 10 -> {
                    curChange[0]--;
                    curChange[1]++;
                    if (curChange[0] < 0)
                        return false;
                }
                case 20 -> {
                    if (curChange[1] >= 1 && curChange[0] >= 1) {
                        curChange[0]--;
                        curChange[1]--;
                    } else if (curChange[0] >= 3) {
                        curChange[0] = curChange[0] - 3;
                    } else
                        return false;
                }
            }

        }
        return true;
    }
}

package leetcode;

/*
 * @source https://leetcode.com/problems/roman-to-integer/description/
 * @author xiaoque
 * @date 2025.03.22
 */
public class RomanToInt {
    public int romanToInt(String s) {
        int resultNum = 0, prev = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            int numeralChar = getValueBySymbol(s.charAt(i));
            if (numeralChar * 4 < prev) {
                resultNum -= numeralChar;
            } else {
                resultNum += numeralChar;
            }
            prev = numeralChar;
        }
        return resultNum;
    }

    private int getValueBySymbol(char c) {
        return switch (c) {
            case 'I' -> 1;
            case 'V' -> 5;
            case 'X' -> 10;
            case 'L' -> 50;
            case 'C' -> 100;
            case 'D' -> 500;
            case 'M' -> 1000;
            default -> 0;
        };
    }
}

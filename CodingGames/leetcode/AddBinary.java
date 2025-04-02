
/*
 * @source https://leetcode.com/problems/add-binary/
 * @author xiaoque
 * @date 2025.03.22
 */
public class AddBinary {
    public String addBinary(String a, String b) {
        StringBuilder sb = new StringBuilder();
        int length = Math.min(a.length(), b.length());
        boolean plus = false;
        for (int i = 0; i < length; i++) {
            int tmp = a.charAt(a.length() - 1 - i) + b.charAt(b.length() - 1 - i) - '0' - '0' + (plus ? 1 : 0);
            sb.append(String.valueOf(tmp % 2));
            plus = tmp / 2 == 1;
        }
        int index = a.length() - length - 1;
        while (index >= 0) {
            int tmp = a.charAt(index) - '0' + (plus ? 1 : 0);
            sb.append(String.valueOf(tmp % 2));
            plus = tmp / 2 == 1;
            index--;
        }
        index = b.length() - length - 1;
        while (index >= 0) {
            int tmp = b.charAt(index) - '0' + (plus ? 1 : 0);
            sb.append(String.valueOf(tmp % 2));
            plus = tmp / 2 == 1;
            index--;
        }
        if (plus) {
            sb.append(1);
        }
        return sb.reverse().toString();
    }
}
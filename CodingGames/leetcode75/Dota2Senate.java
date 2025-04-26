import java.util.LinkedList;
import java.util.Queue;

/*
 * @source 
 * @author xiaoque
 * @date 2025.04.25
 */
public class Dota2Senate {
    public String predictPartyVictory(String senate) {
        // use 2 queue to store Radiant and Dire
        Queue<Integer> rad = new LinkedList<>();
        Queue<Integer> dir = new LinkedList<>();
        // iterate the string to put all R, D into place
        for (int i = 0; i < senate.length(); i++) {
            if (senate.charAt(i) == 'R') {
                rad.offer(i);
            } else
                dir.offer(i);
        }
        int index = senate.length();
        while (!rad.isEmpty() && !dir.isEmpty()) {
            // smaller index moves first
            if (rad.peek() < dir.peek()) {
                rad.offer(index);
            } else {
                dir.offer(index);
            }
            index++;
            rad.poll();
            dir.poll();
        }

        return rad.isEmpty() ? "Dire" : "Radiant";
    }
}

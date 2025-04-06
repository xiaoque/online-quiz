import java.util.List;
import java.util.Stack;

/*
 * @source https://leetcode.com/problems/keys-and-rooms
 * @author xiaoque
 * @date 2025.04.06
 */
public class KeysAndRooms {
    // --------------- using recursion -------------------------
    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        // number of rooms
        int n = rooms.size();
        boolean[] visited = new boolean[n];
        // visit room 0
        dfs(rooms, 0, visited);
        // compute count
        int count = 0;
        for (boolean visit : visited)
            count += visit ? 1 : 0;
        return count == n;
    }

    private void dfs(List<List<Integer>> rooms, Integer key, boolean[] visited) {
        // if room[key] is not visited, then collect its keys and visit recursively
        if (!visited[key]) {
            // set value first, avoid re-call in recursion
            visited[key] = true;
            for (Integer newKey : rooms.get(key)) {
                dfs(rooms, newKey, visited);
            }
        }
    }

    // ----------------- using stack -------------------------------------------
    // room[i] is unlocked if keys collected before dont contain number i
    // visit is not ordered
    // use boolean[rooms.length] to store visited
    // DFS, using keys in unlocked rooms, and add keys recursively
    public boolean canVisitAllRooms2(List<List<Integer>> rooms) {
        // number of rooms
        int n = rooms.size();
        boolean[] visited = new boolean[n];

        int count = 0;
        Stack<Integer> stack = new Stack<>();
        // room 0 is not locked, visit rooms 0 first
        addKeysToStack(stack, rooms.get(0));

        // check if new room is open, if so add keys into stack
        while (!stack.isEmpty()) {
            // store the key, and check if room is opened
            Integer curr = stack.pop();
            if (!visited[curr]) {
                addKeysToStack(stack, rooms.get(curr));
                visited[curr] = true;
                count++;
            }
        }

        // if room 0 is not visited
        if (!visited[0])
            count++;
        return count == n;
    }

    private void addKeysToStack(Stack<Integer> stack, List<Integer> keys) {
        for (Integer key : keys) {
            stack.push(key);
        }
    }
}

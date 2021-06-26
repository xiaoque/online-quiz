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

class Result {

    /*
     * Complete the 'minimumMoves' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts following parameters:
     *  1. STRING_ARRAY grid
     *  2. INTEGER startX
     *  3. INTEGER startY
     *  4. INTEGER goalX
     *  5. INTEGER goalY
     */


    /*
    * @source https://www.hackerrank.com/challenges/castle-on-the-grid/problem
    * @author xiaoque55
    * @date 2021.06.27
    *
    */
    
    static class Point {
        int x;
        int y;
        public Point (int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static int minimumMoves(List<String> grid, int startX, int startY, int goalX, int goalY) {
        if (startX == goalX && startY == goalY)
            return 0;
        
        char [][] array = new char[grid.size()][grid.size()];
        int [][] dist = new int[grid.size()][grid.size()];
        
        // initial distance array and grid array
        for(int i = 0; i < grid.size(); ++i){
            char [] temp = grid.get(i).toCharArray();
            for(int j= 0;j<temp.length;j++){
                array[i][j] = temp[j];
                dist[i][j] = Integer.MAX_VALUE;
            }
        }
        bfs(array, dist, startX, startY);
        return dist[goalX][goalY];
    }
    
    // classic breath first seach
    public static void bfs (char [][] array, int [][] dist, int startX, int startY) {
        dist[startX][startY] = 0;
        Queue<Point> queue = new LinkedList<>();
        queue.offer(new Point(startX, startY));
        
        while (!queue.isEmpty()) {
            Point cur = queue.poll();
            findNextStep(array, dist, queue, cur);
        }
    }
    
    public static void findNextStep (char [][] array, int [][] dist, Queue<Point> queue, Point cur) {
        int x = cur.x;
        int y = cur.y;
        int val = dist[cur.x][cur.y] + 1;
        
        // move to right
         for(int k = x + 1; k < array.length; ++k) {
            if(array[k][y] == 'X'){
                break;
            }
            if(dist[k][y] > val){
                dist[k][y] = val;
                queue.offer(new Point(k, y));
            }
        }
        
        // move to left
        for(int k = x - 1; k >= 0; --k){
            if(array[k][y] == 'X'){
                break;
            }
            if(dist[k][y] > val){
                dist[k][y] = val;
                queue.offer(new Point(k, y));
            }
        }
        
        // move down
        for(int k = y + 1; k < array.length; ++k){
            if(array[x][k] == 'X'){
                break;
            }
            if(dist[x][k] > val){
                dist[x][k] = val;
                queue.offer(new Point(x, k));
            }
        }
        
        // move up
        for(int k = y - 1; k >= 0; --k){
            if(array[x][k] == 'X'){
                break;
            }
            if(dist[x][k] > val){
                dist[x][k] = val;
                queue.offer(new Point(x, k));
            }
        }
    }

}
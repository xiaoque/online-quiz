
/*
 * @source https://leetcode.com/problems/design-circular-queue/
 * @author xiaoque
 * @date 2025.03.22
 */
public class MyCircularQueue {
    int[] queue;
    int begin;
    int count;
    int end;

    public MyCircularQueue(int k) {
        queue = new int[k];
        begin = 0;
        count = 0;
        end = -1;
    }

    public boolean enQueue(int value) {
        if (isFull()) {
            return false;
        }
        end = (end + 1) % queue.length;
        queue[end] = value;
        count++;
        return true;
    }

    public boolean deQueue() {
        if (isEmpty())
            return false;
        begin = (begin + 1) % queue.length;
        count--;
        return true;
    }

    public int Front() {
        if (isEmpty())
            return -1;
        else {
            return queue[begin];
        }
    }

    public int Rear() {
        if (isEmpty())
            return -1;
        else {
            return queue[end];
        }
    }

    public boolean isEmpty() {
        return count == 0;
    }

    public boolean isFull() {
        return (count == queue.length);
    }
}

import java.util.HashMap;
import java.util.Map;

/*
 * @source https://leetcode.com/problems/lfu-cache/
 * @author xiaoque
 * @date 2025.04.24
 */
public class LFUCache {
    // same structure for LRU, with addtional freq
    class Node {
        int key, value, freq;
        Node prev, next;

        Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    // construct a double linked list
    class DoubleLinkedNode {
        int size;
        Node head;
        Node tail;

        // create a dummy head and tail
        DoubleLinkedNode() {
            head = new Node(0, 0);
            tail = new Node(0, 0);
            head.next = tail;
            tail.prev = head;
            size = 0;
        }

        // add node to head: head -> next => head -> node -> next
        public void addNode(Node node) {
            size++;

            node.next = head.next;
            node.next.prev = node;
            head.next = node;
            node.prev = head;
        }

        // remove node from the linked list
        public void removeNode(Node node) {
            size--;
            node.prev.next = node.next;
            node.next.prev = node.prev;
        }

        // return and remove last node in list
        public Node removeLast() {
            if (size >= 1) {
                Node node = tail.prev;
                removeNode(node);
                return node;
            }
            return null;
        }
    }

    private Map<Integer, Node> cache;
    private Map<Integer, DoubleLinkedNode> freqMap;
    private int capacity, minFreq;

    public LFUCache(int capacity) {
        this.cache = new HashMap<>();
        this.freqMap = new HashMap<>();
        this.capacity = capacity;
        this.minFreq = 0;
    }

    // update freq if exits
    public int get(int key) {
        if (cache.containsKey(key)) {
            Node node = cache.get(key);
            addFreqForNode(node);
            return node.value;
        }
        return -1;
    }

    // update freq for node
    public void addFreqForNode(Node node) {
        // remove from prev list
        DoubleLinkedNode list = freqMap.getOrDefault(node.freq, null);
        if (list != null)
            list.removeNode(node);
        // update minimun freq
        if (minFreq == node.freq && freqMap.get(node.freq).size == 0)
            minFreq++;
        node.freq++;
        // add to new list
        freqMap.computeIfAbsent(node.freq, x -> new DoubleLinkedNode()).addNode(node);
    }

    public void put(int key, int value) {
        if (cache.containsKey(key)) {
            // only need to update freq for exiting node
            Node node = cache.get(key);
            node.value = value;
            addFreqForNode(node);
        } else {
            // remove node when capacity reaches
            if (cache.size() == capacity) {
                Node node = freqMap.get(minFreq).removeLast();
                cache.remove(node == null ? null : node.key);
            }

            // update new minfreq to 1
            minFreq = 1;
            // put node into maps
            Node node = new Node(key, value);
            addFreqForNode(node);
            cache.put(key, node);
        }
    }
}

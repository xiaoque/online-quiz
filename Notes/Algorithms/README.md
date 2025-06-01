## Bit manipulations

| Operation            | Symbol | Description                                     |
| :------------------- | :----- | :---------------------------------------------- |
| AND                  | `&`    | 1 if both bits are 1                            |
| OR                   | `|`    | 1 if either bit is 1                            |
| XOR                  | `^`    | 1 if bits are different                         |
| NOT                  | `~`    | Flips all bits                                  |
| Left Shift           | `<<`   | Shifts bits left (adds zeros on the right)      |
| Right Shift          | `>>`   | Shifts bits right (keeps sign bit)              |
| Unsigned Right Shift | `>>>`  | Shifts right (fills with 0s regardless of sign) |

* **Check odd / even** : `num & 1 == 0`  => even,  (and on last bit)
* **Multiply or Divide by 2** : `n >>1 == n / 2` , ` n << 1 == n * 2`
* **Check duplicate**: `a ^ a == 0, a ^ 0 == a`

Functions:

* `Integer.bitCount(n)` → counts the number of set bits
* `Integer.toBinaryString(n)` → shows binary string
* `Integer.highestOneBit(n)` → gets highest one bit
* `Integer.lowestOneBit(n)` → gets lowest one bit

Practice:  [1318.Minimum-Flips-To-Make-A-Or-B-Equal-To-C.md](1318.Minimum-Flips-To-Make-A-Or-B-Equal-To-C.md) 

## Data Structure

### Stack & Queue

* Stack, LIFO

  ```java
  Stack<Integer> stack = new Stack<>();
  stack.push(1);
  stack.pop();
  ```

* Queue, FIFO

  ```java
  Queue<Integer> queue = new LinkedList<>();
  queue.offer(1);
  queue.poll();
  ```

* Deque, an interface that supports double-ended queue (can be served as a queue or a stack). 

  ```java
  Deque<String> stack = new ArrayDeque<>();
  stack.push("Item 1");
  stack.pop();
  // or
  stack.addFirst("Item 2");
  stack.removeFirst();
  ```

  * `Stack`  is synchronized, meaning it has overhead for thread safety that is often unnecessary. `ArrayDeque` is not synchronized, making it faster for single-threaded use cases. For a thread-safe alternative, `ConcurrentLinkedDeque` is preferred.

  ```java
  Queue<String> queue = new ArrayDeque<>();
  queue.offer("Task 1");
  queue.poll();
  // or
  queue.add("Task 1");
  queue.remove();
  ```

  * `ArrayDeque` is generally faster in practice due to better cache locality. Array elements are stored contiguously in memory, which CPUs can access more efficiently than traversing pointers in a `LinkedList`.

### Heap

A queue but keep a logical order of elements. By default, PriorityQueue is a min heap (root item is min). The insert and deletion operations are of time complexity O(logn).

```java
PriorityQueue<Integer> minHeap = new PriorityQueue<>();
PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
```

Use case: **find median in an array**

* Maintain a min heap (right half) and a max heap (left half).
* Balance the 2 heaps with restriction: `minHeap.length() + 1 >= maxHeap`



### Linkded list

#### Find the middle node for a linked list

With a fast and a slow pointer, fast visits 2 nodes at a time, when fast reaches the end node, slow will be a middle of the linked list, at index `n / 2` or `(n - 1) / 2`.

```java
Node slow = head;
Node fast = head;
while (fast != null && fast.next != null) {
    fast = fast.next.next;
    slow = slow.next;
}
```

**Tips**: Add a dummy node as head to keep track of head node.

#### Revert a linked list

To revert list, the linked list is actually separated into 2 list during the operation. `prev -> curr -> next` is update into: 

* The part reverted `curr -> prev`
* The part need to be revert `next -> next.next ...`

At each pass, move `next` points to `curr` node and keep `next.next` node for next iteration.

```java
while(curr != null){
    next = curr.next;            
    curr.next = prev;
    prev = curr;
    curr = next;
}
```

Practice: 

*  [24.Swap-Nodes-In-Pairs.md](Data-structure/24.Swap-Nodes-In-Pairs.md) 

#### Detect a loop in linked list

When a linked list contains a cycle, use **two pointers**, one *fast* traverses 2 nodes each loop, *slow* traverses 1 node each loop, if such a cycle exists, slow node will meet fast node, or fast node hit the end of the linked list. 

Given a list of **n** length, assume exists a cycle of **c** length, we want to find the start node of such a cycle at position **n - c**, call it **l**.

* Seperate cycle into 2 parts by the meeting position such that **x + y  = c**, where **l + x** is the meeting position of fast and slow pointer.
* Slow pointer should traverse **l + x** nodes, while fast pointer traverses total **l + x + m*c** nodes (m times full cycle + slow), and fast traverses 2 times nodes than slow, we have **2 (l + x) = l + x + m*c**.
  * simplify the equation: **2(l + x)  = l + x + m * (x + y)**
  * **l + x = m * (x + y)**
  * **l = (m - 1) (x + y) + y**
  * **l = (m - 1) * c + y**
* Now assume we move slow pointer **l **steps further, we will stop at position **l**
  *  **l + x + ((m - 1) * c + y) => l + (m - 1 )* c + x + y => l + m * c**
* If at the same time, move another pointer from starting node of the list (position 0), 2 pointer will meet at position **l** which is the start point of the cycle.

```java
// add check for null values if loop is not garanteed
while(fast != slow)
	fast = fast.next.next;
	slow = slow.next
    
// get the start node of the loop
fast = head;
while(fast != slow)
	fast = fast.next;
  slow = slow.next;
return slow;
```



## Tree && graph 

#### DFS

Traversal algorithms, often applied to problems like finding the longest path. Three primary orders (pre-order, in-order, and post-order), depending on when the root node is visited. implemented using recursion or an iterative approach with a stack.

```java
// visit root node first
void preorder(args...) {
  if (root == null)
      return;
  result.add(root.val);
  preorder(root.left, result);
  preorder(root.right, result);
}

// visit root node in between children
void inorder(args..){
  inorder(root.left);
  result.add(root.val);
  inorder(root.right);
}

// visit root node after children
void postorder(args...){
  postorder(root.left);
  postorder(root.right);
  result.add(root.val);
}
```

Practice:

* Longest path:  [1372.Longest-Zigzag-Path-In-A-Binary-Tree.md](Tree-Traversal/1372.Longest-Zigzag-Path-In-A-Binary-Tree.md) 
*  [1466.Reorder-Routes-To-Make-All-Paths-Lead-To-The-City-Zero.md](Graph/1466.Reorder-Routes-To-Make-All-Paths-Lead-To-The-City-Zero.md) 
*   [106.Construct-Binary-Tree-From-Inorder-And-Postorder-Traversal.md](Graph/106.Construct-Binary-Tree-From-Inorder-And-Postorder-Traversal.md) 
*    [124.Binary-Tree-Maximum-Path-Sum.md](Tree-Traversal/124.Binary-Tree-Maximum-Path-Sum.md) 

#### BFS

Use queue with 2-layer loop

```java
void bfs(TreeNode node) {
    if (node == null) return;
    Queue<TreeNode> queue = new LinkedList<TreeNode>();
    que.offer(node);

    while (!queue.isEmpty()) {
        int len = queue.size();
        while (len > 0) {
            TreeNode tmpNode = queue.poll();
            itemList.add(tmpNode.val);
            if (tmpNode.left != null) queue.offer(tmpNode.left);
            if (tmpNode.right != null) queue.offer(tmpNode.right);
            len--;
        }
        resList.add(itemList);
    }
}
```

Practice:

* Get shortest path:  [1926.Nearest-exit-from-entrance-in-maze.md](Graph/1926.Nearest-exit-from-entrance-in-maze.md) 



### Graph

* **Topological sort to detect a cycle in DAG**:  [207.Course-Schedule.md](Graph/207.Course-Schedule.md) 



### Trie

A special tree structure for storing and finding strings. Each node represents a character, its children nodes represent the character in next index, node can also maintain informations like a boolean `isEndOfWord`  or a counter `passThrough` to simplify the traversal. Insertion, searching or prefix searching are of time complexity O(n), where n is the length of the string.

```java
class Node {
  Node[] children = new Node[26];
  boolean isEndOfWord = false;
  int passThrough = 0;
}
```



## Sort

|Name|Time Complexity| Space Complexity |
|---| ---|---|
|Bubble sort| O(n ^ 2) | O(1)|
|Selection sort| O(n ^ 2) | O(1)|
|Insertion sort|O(n ^ 2)|O(1)|
|Merge sort| O(n logn) |O(n)|
|Quick sort|O(n logn)|O(logn)|

#### Bubble sort

Sort the array from end, at each iterate finds the current largest elment and put it at the end of the array.

```java
for(i to length - 1)
	for(j to length - i - 1)
    if arr[j] > arr[j + 1]
    	swap arr[j], arr[j + 1]
  end for
end for
```
#### Insertion sort

Start the iteration from 2nd element, keep adjusting elements before `i` until find a place to insert `arr[i]`.

```java
for( i to length - 1)
  key = arr[i]
  j = i - 1
  while j >= 0 and A[j] > key do
    arr[j + 1] = arr[j]
    j = j - 1
  end while
  arr[j + 1] = key
end for
```
#### Merge sort

Recursively dividing the array into two halves, then recursively sorting the two halves and merging them back together to obtain the sorted array.

```java
function sort(arr, left, right)
  if right <= left then return
  end if

  middle = left + (right - left) / 2

  sort(arr, left, middle)
  sort(arr, middle + 1, right)

  merge(arr, left, middle, right)
end function

function merge(arr, left, middle, right) 
	tempList = merge arr[left, middle] and arr[middle + 1, right]
  copy tempList to arr[left, right]
end function
```






## Search


#### Binary search

In an array of size n, to find target value, divide the array into 2 parts from middle then verify the middle value. If the target is smaller than middle, then continue the search within left part; otherwise, continue the search within right part. Restrict the range recurssively until find the result.

> Possible improvement for calculating mid, when left and right are non-negative values, use overflow resisted right shift.
>
> ​		**mid = (left + right) >>> 1**

Depending on the right boundary, there are 2 options.

1. Search in [left, right]
   * Initialize left = 0, right  = n - 1, with while condition left **<=** right.
   * Update right value with mid - 1
2. Search in [left, right)
   * Initlialize left = 0, right = n, with while condition left < right
   * Since nums[right] is not considered, update right with value mid

```java
// opt1
while(left <= right){
  mid = left + (right - left) / 2;
  // find target
  if(arr[mid] == target) return mid;
  // target is in left part, [left, mid - 1]
  if(arr[mid] > target) right = mid - 1;
  // target is in right part, [mid + 1, right]
  else left = mid + 1;
}

// opt2
while(left < right){
  mid = left + (right - left) / 2;
  // find target
  if(arr[mid] == target) return mid;
  // target is in left part, [left, mid)
  if(arr[mid] > target) right = mid;
  // target is in right part, [mid + 1, right)
  else left = mid + 1;
}

// opt2: more common case: find min index that satisfy condition(k)
while(left < right){
  mid = left + (right - left) / 2;
  if(condition(mid)) right = mid;
  else left = mid + 1;
}
```

Practice:

* [875. Koko Eating Bananas](./Binary-Search/875.Koko-Eating-Bananas.md)
* [153.Find-Minimum-In-Rotated-Sorted-Array.md](Binary-Search/153.Find-Minimum-In-Rotated-Sorted-Array.md) 
<<<<<<< HEAD
<<<<<<< HEAD:Notes/Algorithms/README.md
*  [74.Search-A-2d-Matrix.md](Binary-Search/74.Search-A-2d-Matrix.md) 
=======
>>>>>>> 6f2770b (Update notes):Algorithms/README.md
=======
*  [74.Search-A-2d-Matrix.md](Binary-Search/74.Search-A-2d-Matrix.md) 
>>>>>>> 8673bab (refactor folder)



## Sliding windows

```java
void slidingWindows(args){
  windowSize = k;
  array.length = n;
  
  for(item in array){
    update result with new item;
    if (i >= windowSize -1)
      compute result from this window
      remove item[i-k];
  }
}
```

Practice: 

*  [209.Minimum-Size-Subarray-Sum.md](Sliding-Windows/209.Minimum-Size-Subarray-Sum.md) 
*  [480.Sliding-Window-Median.md](Sliding-Windows/480.Sliding-Window-Median.md) 
*  [2090.K-Radius-Subarray-Averages.md](Sliding-Windows/2090.K-Radius-Subarray-Averages.md) 
*   [76.Minimum-Window-Substring.md](Sliding-Windows/76.Minimum-Window-Substring.md) 

## Backtracking 

Convert the problem into tree traversal, where possible choices are subnodes of a tree, the problem is to find a valid path in the tree. Recursively call bk func is to add a node to the path, for-loop is to visit all siblings. Time complexity `O(n ^ h)`, where `n` is the number of subnodes for each node, `h` is the depth of the tree.

Steps for backtracking: 

* **Define args in the function**, besides a list to store current path and a list to store valid path, maybe need an index or currentSum to store current state.
* **Define end condition**, how to verify if current path is valid, stop the recursion
* **Range of current loop**, input parameters for recursion.

```java
function backtracking (args, current, path, result)
  if (current satisfy condition)
    result.add(path)
    return
    
  for each possible choice
    apply(choice);
    backtracking(args, choice, path, result);
    remove(choice);
  end for
end function
```

Practice:

*  [77.Combinations.md](Backtracking/77.Combinations.md) 
*  [131.Palindrome-Partitioning.md](Backtracking/131.Palindrome-Partitioning.md) 

## Dynamic Programming

Steps for DP

* Define the meaning of dp[i].
* Get the formula of dp[i], eg., dp[i] = max(dp[i-1], dp[j] + val).
* Define initial values dp[0].
* Iterate from begin or from end

Practice:

*  [647.Palindromic-Substrings.md](Dynamic-Programming/647.Palindromic-Substrings.md) 
  * Same idea combined with the problem of backtracking  [131.Palindrome-Partitioning.md](Backtracking/131.Palindrome-Partitioning.md) 
  * Another problem with palindromic  [5.Longest-Palindromic-Substring.md](Dynamic-Programming/5.Longest-Palindromic-Substring.md) 
*  2d-array but can be simplified into 1d-array
   *   [62.Unique-Paths.md](Dynamic-Programming/62.Unique-Paths.md) 
   *   [1143.Longest-Common-Subsequence.md](Dynamic-Programming/1143.Longest-Common-Subsequence.md) 


## Others

## 

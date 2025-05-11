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

### Stack

*  [394.Decode-String.md](Data structure/394.Decode-String.md) 

### Heap

By default is a min heap (root item is min). 

```java
PriorityQueue<Integer> minHeap = new PriorityQueue<>();
PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());

// find median in array [1, 3, 5, 9, 6]
// minHeap : [1, 3, 5]
// maxHeap : [9, 6]
```

### Deque

### Linkded list



#### Find middle node for a linked list



#### Revert a linked list

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

3 orders, normally use recursive, or stack

```java
void preorder(args...) {
  if (root == null)
      return;
  result.add(root.val);
  preorder(root.left, result);
  preorder(root.right, result);
}

void inorder(args..){
  inorder(root.left);
  result.add(root.val);
  inorder(root.right);
}

void postorder(args...){
  postorder(root.left);
  postorder(root.right);
  result.add(root.val);
}
```

Practice:

* Longest path:  [1372.Longest-Zigzag-Path-In-A-Binary-Tree.md](Tree-Traversal/1372.Longest-Zigzag-Path-In-A-Binary-Tree.md) 
*  [1466.Reorder-Routes-To-Make-All-Paths-Lead-To-The-City-Zero.md](Graph/1466.Reorder-Routes-To-Make-All-Paths-Lead-To-The-City-Zero.md) 

#### BFS

Use queue with 2-layer loop

```java
void bfs(TreeNode node) {
    if (node == null) return;
    Queue<TreeNode> que = new LinkedList<TreeNode>();
    que.offer(node);

    while (!que.isEmpty()) {
        int len = que.size();
        while (len > 0) {
            TreeNode tmpNode = que.poll();
            itemList.add(tmpNode.val);
            if (tmpNode.left != null) que.offer(tmpNode.left);
            if (tmpNode.right != null) que.offer(tmpNode.right);
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



## Sort

|Name|Complexity| space | Notes|
|---| ---|---|---|
|Bubble sort| N*N| O(1)||
|Selection sort| N*N| O(1)|for array, not stable, too much comparison|
|Insertion sort|N*N|O(1)|more stable than selection|
|Merge sort| nlogn|n||
|Quick sort|nlogn|log n|

#### Bubble sort

```java
for(i to length - 1)
	for(j to length-i-1)
    	swap j with j+1
```
#### Insertion sort
```java
for( i to length)
	for( i-1 to 0)
    	if a(j) > a(i), a(j+1) = a(j);
    set a(i)
```
#### Merge sort

```java
if right <= left return 0;

middle = left + (right - left)/2
leftArr = sort(arr, left, middle);
rightArr = sort(arr, middle + 1, right);
merge(arr, left, middle, right);

function merge(arr, left, middle, right) {
	if arr.left < arr.right
    	temp.add(left)
    else temp.add(right)
    //copy the rest in left and right into temp
    //copy temp to arr
}
merge the array from left to middle and middle to right
```
#### Quick sort




| Name          | Complexity | Notes |
| ------------- | ---------- | ----- |
| Binary search | O(logn)    |       |
| Fibonacci     | O(logn)    |       |
| Backtracking  | O(n*2^n)   |       |



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
* 



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

Try to convert the problem into tree traversal, where possible choices are subnodes of a tree, the problem is to find a valid path in the tree. Recursively call bk func is to add a node to the path, for-loop is to visit all siblings.

Steps for backtracking: 

* **Define args in the function**, besides a list to store current path and a list to store valid path, maybe need an index or currentSum to store current state.
* **Define end condition**, how to define if current path is valid, then stop the recursion
* **Range of current loop**, if an item can be reused, is it possible to reduce loop size, the parameters to next level recursion.

```java
void backtracking (args) {
    if (satisfied) {
        result.add(current);
        return;
    }
  	// if dup is allowed, bk with index, otherwise bk(index+1)
    for (siblings of current node) {
      current += val;
      backtracking(args...);
      current -= val;
    }
}
```

Practice:

*  [77.Combinations.md](Backtracking/77.Combinations.md) 
*  [131.Palindrome-Partitioning.md](Backtracking/131.Palindrome-Partitioning.md) 

## Dynamic Programming

* Find the meaning of dp[i]
* Get the formula of dp[i], eg., dp[i] = max( dp[i-1], dp[j] + val). For i, either take or not take
* Define initial values dp[0]...
* Iterate from begin or from end

Practice:

*  [647.Palindromic-Substrings.md](Dynamic-Programming/647.Palindromic-Substrings.md) 
  * Same idea combined with the problem of backtracking  [131.Palindrome-Partitioning.md](Backtracking/131.Palindrome-Partitioning.md) 
  * Another problem with palindromic  [5.Longest-Palindromic-Substring.md](Dynamic-Programming/5.Longest-Palindromic-Substring.md) 
*  2d-array  [62.Unique-Paths.md](Dynamic-Programming/62.Unique-Paths.md) 

## Others

## 

## Data Structure

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



### Tree traversal 

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

* 

#### BFS

use queue with 2-layer loop

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

In range [0, n], compare value of mid to target; if target is smaller than mid, then redo the check for range (left, mid); otherwise, redo the check for range (mid, right).

> Possible improvement for calculating mid, when left and right are non-negative values, use overflow resisted right shift.
>
> ​		**mid = (left + right) >>> 1**

```java
// opt1
while(left <= right){
  mid = left + (right - left) / 2;
  if(arr[mid] == x) return mid;
  if(arr[mid] > x) right = mid - 1;
  left = mid + 1;
}

// opt2: condition in while changed, then right value changes also
while(left < right){
  mid = left + (right - left) / 2;
  if(arr[mid] > x) right = mid;
  left = mid + 1;
}

// opt2: more common case: find min index that satisfy condition(k)
while(left < right){
  mid = left + (right - left) / 2;
  if(condition(mid)) right = mid;
  left = mid + 1;
}
```

Practice:

* [875. Koko Eating Bananas](./Binary%20search/875.%20Koko%20Eating%20Bananas.md)
* 

## Two pointers

### Detect a loop in linked list

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

* [209. Minimum Size Subarray Sum](./Sliding%20windows/209.%20Minimum%20Size%20Subarray%20Sum.md)
* [480. Sliding Window Median](./Sliding%20windows/480.%20Sliding%20Window%20Median.md)
* [2090. K Radius Subarray Averages](./Sliding%20windows/2090.%20K%20Radius%20Subarray%20Averages.md)
  

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

* [77. Combinations](./Backtracking/77.%20Combinations.md)
* [131. Palindrome Partitioning](./Backtracking/131.%20Palindrome%20Partitioning.md)

## Dynamic Programming

* Find the meaning of dp[i]
* Get the formula of dp[i], eg., dp[i] = max( dp[i-1], dp[j] + val). For i, either take or not take
* Define initial values dp[0]...
* Iterate from begin or from end

Practice:

* [647. Palindromic Substrings](./Dynamic%20Programming/647.%20Palindromic%20Substrings.md)
  * Same idea combined with backtracking check [131. Palindrome Partitioning](./Backtracking/131.%20Palindrome%20Partitioning.md)

## Others

## 

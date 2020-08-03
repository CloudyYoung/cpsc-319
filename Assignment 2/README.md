
# CPSC 319 Summer 2020 Assignment 2


## 1. Instruction of Use

### 1.1 Launch
Open the java file `Main.java`, and run the `main()` method.

### 1.2 Text File
Please place the text file in the same folder with `Main.java`.
When running the program, enter the file name, **without file extension**.

```
> Enter the input file name: text1
```

### 1.3 Binary Tree and Statistics
After reading all the words from provided text file and construct the binayr tree, the following content will be displayed: 
 - Binary tree in visual representation
 - Total number of words
 - Number of unique words
 - Words with highest frequency

The **In-Order** traversal method is used for statistics. It is defined in `BinaryTree.java` method `statistics()`. Other two traversal methods can replace as willing.

For binary tree nodes displayed below, `this (9)` for instance, `this` represents the word and `9` inside the brackets represents its frequency.


```
>     ┌─── was (2)
      │     └─── tough (1) 
    this (9)
      │           ┌─── the (2)
      │     ┌─── test (9)
      │     │     └─── second (3)
      └─── is (9)
            │     ┌─── first (1)
            │     │     │     ┌─── but (1)
            │     │     └─── alright (1)
            └─── a (9)

Total number of words in text1 = 11
Number of unique words in text1 = 4
The word(s) which occur(s) most often and the number of times that it/they occur(s) =
  a = 9 times
  is = 9 times
  test = 9 times
  this = 9 times
```

### 1.4 Switching Functions
There are two functions in the program, the following table is showing the function and the switching command.

|Function|Command|Effective Time|
|--|--|--|
|Word searching|`!find`|After read any file|
|Display tree in traversal methods|`!trav`|After read any file|
|End the program|`!end`|Anytime|

By default, user will be directed to word searching method. User can switch as willing.

```
> Enter the word you are looking for in text1 ? !trav
> (Switched to traversal)
```
```
> Enter the BST traversal method (1 = IN-ORDER, 2 = PRE-ORDER, 3 = POST-ORDER) for text1 ? !find
> (Switched to search)
```
```
> Enter the word you are looking for in text1 ? !end
> ENDED Program is ended. Thank you for using!
```


### 1.5 Searching
```
> Enter the word you are looking for in text1 ? this
> FOUND It appears 9 times in the input text file
```
```
> Enter the word you are looking for in text1 ? that
> FAIL Word not found!
```

### 1.6 Traversal (Bonus)
```
> Enter the BST traversal method (1 = IN-ORDER, 2 = PRE-ORDER, 3 = POST-ORDER) for text1 ? 1
> IN-ORDER output: a alright but first is second test the this tough was 
```
```
> Enter the BST traversal method (1 = IN-ORDER, 2 = PRE-ORDER, 3 = POST-ORDER) for text1 ? 4
> FAIL Input value is invalid
```

### 1.7 End
```
> Enter the input file name: !end
> ENDED Program has ended. Thank you for using!
```




## 2. Traversal

The implementation of traversal is in class `Traversal.java`. It might be complex and confusing to people. For me, it is reasonable, and this section is the explanation.

### 2.1 First Implementation
The first implementation of traversal method is in the class `BinaryTree.java` private method `traversal()`:

```
private ArrayList<Node> traversal(Node node, int mode){
        ArrayList<Node> list = new ArrayList<Node>();
        if(node != null){
            for (int t = 0; t < 3; t++) {
                if (       (t == 0 && mode == Traversal.PRE_ORDER) 
                        || (t == 1 && mode == Traversal.IN_ORDER) 
                        || (t == 2 && mode == Traversal.POST_ORDER)) {
                    list.add(node); // Visit the node
                } else if ((t == 1 && mode == Traversal.PRE_ORDER)
                        || (t == 0 && mode == Traversal.IN_ORDER)
                        || (t == 0 && mode == Traversal.POST_ORDER)) {
                    list.addAll(this.traversal(node.getLeftNode(), mode)); // Visit left child node
                } else if ((t == 2 && mode == Traversal.PRE_ORDER) 
                        || (t == 2 && mode == Traversal.IN_ORDER)
                        || (t == 1 && mode == Traversal.POST_ORDER)) {
                    list.addAll(this.traversal(node.getRightNode(), mode)); // Visit right child node
                }
            }
        }
        return list;
    }
```
By using a `for` loop and `if` conditions inside, when facing different traversal method, it adjusts the order of actions accordingly, instead of having three versions of traversal method code with only slight difference of order, which avoid the duplication of code.

### 2.2 Python Generator

When I was implementing the searching method for binary tree, again, there are going to be duplications of traversal method from the above, one for just traversed all nodes and one for searching.

Recently, I get to know generator in Python by chance; by using a keyword `yield`, data can be traversed one at a time as the data is visited from others, instead of traversed all the data at once.

By researching, there seems no generator in Java. Though I could implement the searching method by using just natural binary searching method, I may or may not want to challenge implementing a generator-like traversal class by myself for fun.

### 2.3 Plan
Based on the first implementation of traversal method, I will make some change to have the following product:
 - Define which traversal method to use and the tree to traverse
 - Method `next()`, it returns the one node to be traversed next each time it is invoked, and returns `null` if there is no more node to be traversed; the traversal order follows the traversal method
 - Method `hasNext()`, return a boolean represents whether the tree is finished traversal

To achieve this goal product, method `next()` to be able to trace back to the node where it stopped last time.

### 2.4 Second Implementation
An additional class `TravNode` extends `Node` is added only for class `Traversal.java`. An additional attribute `step` is added for every node for tracing.

In class `Traversal.java`, attribute `path` is to trace the current node and its parent, in `Stack` (hey, good example of learn as go). When the node is going to be traversed, it is added to the `path`, and when finished, it is removed.

More notes can be found in `Traversal.java`.

### 2.5 Conclusion

By finishing the second implementation, class `BinaryTree.java` would use traversal method more elegantly.

Method `traversal()`:
```
private ArrayList<Node> traversal(Node node, int mode) {
        ArrayList<Node> list = new ArrayList<Node>();

        Traversal trav = new Traversal(this, mode);
        while (trav.hasNext()) {
            list.add(trav.next());
        }
        return list;
    }
```

Method `search()`:   
 - When the node is searched, it will return the node and traversing process is no longer continuing.
```
public Node search(String value) {
        Traversal trav = new Traversal(this, Traversal.IN_ORDER);
        while (trav.hasNext()) {
            Node node = trav.next();
            if (node.getValue().equalsIgnoreCase(value)) {
                return node;
            }
        }
        return null;
    }
```

As a conclusion, although there are additional works and I personally doubt it is practical when I was reassessing, it still is doing the same work of three traversal methods and giving the correct output. It is a fun challenge.



## 3. References
 - https://stackoverflow.com/questions/5762491/how-to-print-color-in-console-using-system-out-println
 - https://www.w3schools.com/java/java_files_read.asp
 - Lecture Powerpoints
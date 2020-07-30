import java.util.ArrayList;
import java.util.Stack;

public class Traversal {

    /**
     * TraveNode class for Traversal
     * The only difference from Node class is it contains an extra attribute: step 
     * this.step represents the traversing step in this.next() method
     */
    private class TravNode extends Node{
        private int step;

        public TravNode(Node node){
            super(node);
            this.step = 1;
        }

        public int getStep(){
            return this.step;
        }

        public int addStep(){
            this.step ++;
            return this.step;
        }

        @Override
        public String toString(){
            return this.getValue() + "(" + this.getFrequency() + ", " + this.step + ")";
        }
    }

    public static final int PRE_ORDER = 0;
    public static final int IN_ORDER = 1;
    public static final int POST_ORDER = 2;

    
    private Stack<TravNode> path;               // The path of traversal, stores TravNode objects
    private int mode;                           // Traversal mode
    private Node next;                      // Store next node

    /**
     * @param node the root node of a binary tree
     * @param mode the traversal mode, acceptable values are: PRE_ORDER, IN_ORDER, and POST_ORDER
     */
    public Traversal(Node node, int mode){
        this.path = new Stack<TravNode>();  // Initialize path
        this.path.add(new TravNode(node));      // Initialize the path with root node
        this.mode = mode;
    }

    /**
     * @param tree a binary tree
     * @param mode the traversal mode, acceptable values are: PRE_ORDER, IN_ORDER,
     *             and POST_ORDER
     */
    public Traversal(BinaryTree tree, int mode){
        this(tree.getRoot(), mode);
    }

    public static void main(String[] args){
        ArrayList<String> list = new ArrayList<String>();
        list.add("Something");
        list.add("Behind");
        list.add("Us");
        list.add("Doing");
        list.add("Great");
        list.add("Babay");
        list.add("Babay");
        list.add("Orders");
        list.add("Cathy");
        BinaryTree tree = new BinaryTree(list);
        System.out.println(tree);
        System.out.println(tree.postOrder());
        Traversal trav = new Traversal(tree, Traversal.POST_ORDER);
        System.out.println(trav.next());
        System.out.println(trav.next());
        System.out.println(trav.next());
        System.out.println(trav.next());
        System.out.println(trav.next());
        System.out.println(trav.next());
        System.out.println(trav.next());
        System.out.println(trav.next());
        System.out.println(trav.next());
    }

    /**
     * Inspired by the Python generator, 
     * this method return the next node in the binary tree each time it is invoked, 
     * instead of traverse the whole tree each time.
     * It stores the path of traversal, and restore the path direcly to get next node.
     * Return null if no more node can be traversed
     * @return the next node
     */
    public Node next(){
        if(this.path.empty()){
            return null;
        }else if(this.next != null){
            Node next = this.next;
            this.next = null;
            return next;
        }else{
            TravNode travNode = this.next(this.path.peek());
            return travNode == null ? null : new Node(travNode); // Degrade TraveNode to Node object
        }
    }

    /**
     * @param travNode the travNode for recursion
     * @return the next travNode object
     */
    private TravNode next(TravNode travNode){

        // Each loop is a step, total 3 steps
        // Steps are from 1 to 3, according to traversal mode, actions are made in different order
        // t is equal to travNode.getStep() all the time
        for (int t = travNode.getStep(); t <= 3; t = travNode.addStep()){ 

            if (       (t == 1 && this.mode == PRE_ORDER)       // Step 1 for Pre-order, if node itself
                    || (t == 2 && this.mode == IN_ORDER)        // Step 2 for In-order
                    || (t == 3 && this.mode == POST_ORDER)) {   // Step 3 for Post-order
                t = travNode.addStep();
                return travNode;                                // Return the node
            } else if (travNode.hasLeftNode() &&                // If node has left child node
                      ((t == 2 && this.mode == PRE_ORDER)       // Step 2 for Pre-order
                    || (t == 1 && this.mode == IN_ORDER)        // Step 1 for In-order
                    || (t == 1 && this.mode == POST_ORDER))) {  // Step 1 for Post-order

                t = travNode.addStep();
                TravNode left = new TravNode(travNode.getLeftNode());  // Create new travNode instance for child node
                this.path.push(left);                                   // Add to path
                return this.next(left);                                // Recursion
            } else if (travNode.hasRightNode() &&               // If node has right child node
                      ((t == 3 && this.mode == PRE_ORDER)       // Step 3 for Pre-order
                    || (t == 3 && this.mode == IN_ORDER)        // Step 3 for In-order
                    || (t == 2 && this.mode == POST_ORDER))) {  // Step 2 for Post-order

                t = travNode.addStep();
                TravNode right = new TravNode(travNode.getRightNode()); // Create new travNode instance for child node
                this.path.push(right);                                   // Add to path
                return this.next(right);                                // Recursion
            }
        }
        
        this.path.pop(); // Finish steps, remove the node from path
        if (!this.path.empty()) { // If has nodes in path, then recursion next node in the path
            return this.next(this.path.peek());
        }
        return null;
    }

    public boolean hasNext(){
        this.next = this.next();
        return !this.path.empty();
    }
    
}
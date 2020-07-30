import java.util.ArrayList;

public class BinaryTree {
    
    private Node root;
    private ArrayList<Node> mostFrequentNodes;
    private int uniqueNodes;
    private int totalNodes;
    private int highestFrequency;

    private static final int PRE_ORDER = 0;
    private static final int IN_ORDER = 1;
    private static final int POST_ORDER = 2;

    public BinaryTree(ArrayList<String> list){
        this.makeTree(list);
    }

    private void makeTree(ArrayList<String> list){

        for(String word : list){
            Node current = root;

            if (root == null) {
                
                root = new Node(word);
                current = root;

            }else{

                boolean isPlaced = false;
                while(!isPlaced){

                    if(word.equals(current.getValue())){ // Find existing word node
                        current.addFrequency();
                        isPlaced = true;
                    }else{ // If not finding
                        int compare = word.compareToIgnoreCase(current.getValue()); // Compare words

                        if (compare > 0 && current.hasRightNode()){ // Continue to right child
                            current = current.getRightNode();
                        }else if(compare < 0 && current.hasLeftNode()){ // Continue to left child
                            current = current.getLeftNode();
                        }else{
                            Node newNode = new Node(word); // Word node is not existing, create new node
                            if (compare > 0) {
                                current.setRightNode(newNode);
                            } else {
                                current.setLeftNode(newNode);
                            }
                            isPlaced = true;
                        }
                    }
                }
            }
        }
    }

    public void countTree(){
        
    }

    @Override
    public String toString(){
        return this.root.print();
    }

    public int getTotalNodes(){
        return this.totalNodes;
    }

    public int getUniqueNodes(){
        return this.uniqueNodes;
    }

    public Node getRoot(){
        return this.root;
    }

    public ArrayList<Node> getMostFrequentNodes(){
        return new ArrayList<Node>(this.mostFrequentNodes);
    }

    public ArrayList<Node> preOrder(){
        return this.traversal(this.root, PRE_ORDER);
    }

    public ArrayList<Node> inOrder() {
        return this.traversal(this.root, IN_ORDER);
    }

    public ArrayList<Node> postOrder() {
        return this.traversal(this.root, POST_ORDER);
    }

    private ArrayList<Node> traversal(Node node, int mode){
        ArrayList<Node> list = new ArrayList<Node>();
        if(node != null){
            for (int t = 0; t < 3; t++) {
                if (       (t == 0 && mode == PRE_ORDER) 
                        || (t == 1 && mode == IN_ORDER) 
                        || (t == 2 && mode == POST_ORDER)) {
                    list.add(node); // Visit the node
                } else if ((t == 1 && mode == PRE_ORDER)
                        || (t == 0 && mode == IN_ORDER)
                        || (t == 0 && mode == POST_ORDER)) {
                    list.addAll(this.traversal(node.getLeftNode(), mode)); // Visit left child node
                } else if ((t == 2 && mode == PRE_ORDER) 
                        || (t == 2 && mode == IN_ORDER)
                        || (t == 1 && mode == POST_ORDER)) {
                    list.addAll(this.traversal(node.getRightNode(), mode)); // Visit right child node
                }
            }
        }
        return list;
    }

}
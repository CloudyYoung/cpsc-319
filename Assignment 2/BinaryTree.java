import java.util.ArrayList;

public class BinaryTree {

    private Node root;
    private ArrayList<Node> mostFrequentNodes;
    private int uniqueNodes;
    private int totalNodes;
    private int highestFrequency;

    public BinaryTree(ArrayList<String> list) {
        this.structure(list);
        this.statistics();
    }

    /**
     * Construct the binary tree by given list of values
     */
    private void structure(ArrayList<String> list) {

        for (String word : list) {
            Node current = root;

            if (root == null) {

                root = new Node(word);
                current = root;

            } else {

                boolean isPlaced = false;
                while (!isPlaced) {

                    if (word.equals(current.getValue())) { // Find existing word node
                        current.addFrequency();
                        isPlaced = true;
                    } else { // If not finding
                        int compare = word.compareToIgnoreCase(current.getValue()); // Compare words

                        if (compare > 0 && current.hasRightNode()) { // Continue to right child
                            current = current.getRightNode();
                        } else if (compare < 0 && current.hasLeftNode()) { // Continue to left child
                            current = current.getLeftNode();
                        } else {
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

    @Override
    public String toString() {
        return this.root.print();
    }

    public int getTotalNodes() {
        return this.totalNodes;
    }

    public int getUniqueNodes() {
        return this.uniqueNodes;
    }

    public Node getRoot() {
        return this.root;
    }

    public ArrayList<Node> getMostFrequentNodes() {
        return new ArrayList<Node>(this.mostFrequentNodes);
    }

    public ArrayList<Node> preOrder() {
        return this.traversal(this.root, Traversal.PRE_ORDER);
    }

    public ArrayList<Node> inOrder() {
        return this.traversal(this.root, Traversal.IN_ORDER);
    }

    public ArrayList<Node> postOrder() {
        return this.traversal(this.root, Traversal.POST_ORDER);
    }

    private ArrayList<Node> traversal(Node node, int mode) {
        ArrayList<Node> list = new ArrayList<Node>();

        Traversal trav = new Traversal(this, mode);
        while (trav.hasNext()) {
            list.add(trav.next());
        }
        return list;
    }

    private void statistics() {
        Traversal trav = new Traversal(this, Traversal.IN_ORDER);
        this.mostFrequentNodes = new ArrayList<Node>();

        while (trav.hasNext()) {
            Node node = trav.next();
            this.totalNodes++;

            if (node.getFrequency() == 1) {
                this.uniqueNodes++;
            } else if (node.getFrequency() > this.highestFrequency) {
                this.highestFrequency = node.getFrequency();
                this.mostFrequentNodes.clear();
                this.mostFrequentNodes.add(node);
            } else if (node.getFrequency() == this.highestFrequency) {
                this.mostFrequentNodes.add(node);
            }
        }
    }

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

}
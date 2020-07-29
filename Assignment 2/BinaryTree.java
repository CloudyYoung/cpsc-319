import java.util.ArrayList;

public class BinaryTree {
    
    private Node root;
    private ArrayList<Node> mostFrequentNodes;
    private int uniqueNodes;
    private int totalNodes;
    private int highestFrequency;

    public BinaryTree(ArrayList<String> list){
        this.makeTree(list);
    }

    private void makeTree(ArrayList<String> list){
        this.uniqueNodes = 0;
        this.totalNodes = list.size();
        this.highestFrequency = 0;

        for(String word : list){
            Node current = root;


            if (root == null) {
                
                root = new Node(word);
                current = root;
                this.uniqueNodes++;

            }else{

                boolean isPlaced = false;

                while(!isPlaced){
                    int compare = word.compareToIgnoreCase(current.getValue());

                    if(word.equals(current.getValue())){ // Find existing word node
                        current.addFrequency();
                        isPlaced = true;
                    }else{ // If not finding
                        if(compare < 0 && current.getRightNode() != null){ // Continue to right child
                            current = current.getRightNode();
                        }else if(compare > 0 && current.getLeftNode() != null){ // Continue to left child
                            current = current.getLeftNode();
                        }else{
                            Node newNode = new Node(word); // Word node is not existing, create new node
                            this.uniqueNodes++;

                            if (compare < 0) {
                                current.setRightNode(newNode);
                            } else {
                                current.setLeftNode(newNode);
                            }

                            isPlaced = true;
                        }
                    }
                }
            }

            // Handle most frequency words
            if (current.getFrequency() > this.highestFrequency) {
                this.highestFrequency = current.getFrequency();
                this.mostFrequentNodes = new ArrayList<Node>(); // Clear array
                this.mostFrequentNodes.add(current); // Add to array
            } else if (current.getFrequency() == this.highestFrequency && !this.mostFrequentNodes.contains(current)) { // If the word has the same frequency with the highest, and not in the array
                this.mostFrequentNodes.add(current); // Add to array
            }

        }
    }

    @Override
    public String toString(){
        return this.root.print("", false);
    }

    public int getTotalNodes(){
        return this.totalNodes;
    }

    public int getUniqueNodes(){
        return this.uniqueNodes;
    }

    public ArrayList<Node> getMostFrequentNodes(){
        return new ArrayList<Node>(this.mostFrequentNodes);
    }

}
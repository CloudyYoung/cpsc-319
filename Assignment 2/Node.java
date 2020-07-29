
public class Node {

    private String value;
    private int frequency;
    private Node left;
    private Node right;

    public Node(String value){
        this.value = value;
        this.frequency = 1;
    }

    public String getValue(){
        return this.value;
    }

    public int getFrequency(){
        return this.frequency;
    }

    public Node getLeftNode(){
        return this.left;
    }

    public Node getRightNode(){
        return this.right;
    }

    public void setLeftNode(Node predecessor){
        this.left = predecessor;
    }

    public void setRightNode(Node successor){
        this.right = successor;
    }

    public void addFrequency(){
        this.frequency ++;
    }

    @Override
    public String toString(){
        return this.value + " (" + this.frequency + ")";
    }

    /**
     * @reference: https://stackoverflow.com/questions/4965335/how-to-print-binary-tree-diagram
     */
    public String print(String prepend, boolean right){
        String string = "";
        boolean isStart = prepend.equals("");
        String nextLeftPrepend = (right || isStart) ? "      " : "│     ";
        String nextRightPrepend = (!right || isStart) ? "      " : "│     ";
        String sign = right ? "┌───" : "└───";

        if (this.right != null) {
            string += this.right.print(prepend + nextLeftPrepend, true);
        }

        string += prepend + (isStart ? "   " : sign) + " " + this.value + " (" + this.frequency + ")\n";

        if (this.left != null) {
            string += this.left.print(prepend + nextRightPrepend, false);
        }

        return string;
    }

}
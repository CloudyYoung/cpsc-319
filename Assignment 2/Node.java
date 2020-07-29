
public class Node {

    private String word;
    private int frequency;
    private Node left;
    private Node right;

    public Node(String word){
        this.word = word;
        this.frequency = 1;
    }

    public String getWord(){
        return this.word;
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
        return this.print(0, "");
    }

    private String print(int level, String sign){
        String string = "";

        if (this.right != null) {
            string += this.right.print(level + 1, "┌───");
        }

        string += "     ".repeat(level) + sign + " " + this.word + " (" + this.frequency + ")\n";

        if (this.left != null) {
            string += this.left.print(level + 1, "└───");
        }
        return string;
    }

}
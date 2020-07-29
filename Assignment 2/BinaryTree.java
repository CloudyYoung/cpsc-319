import java.util.ArrayList;

public class BinaryTree {
    
    Node root;

    public BinaryTree(ArrayList<String> list){
        this.makeTree(list);
        System.out.println(this);
    }

    private void makeTree(ArrayList<String> list){
        for(String word : list){
            if(root == null){
                root = new Node(word);
                continue;
            }

            Node current = root;
            while(current != null){
                int compare = word.compareToIgnoreCase(current.getWord());

                if(compare < 0){
                    if (current.getLeftNode() == null) {
                        current.setLeftNode(new Node(word));
                        current = null;
                    } else {
                        current = current.getLeftNode();
                    }
                }else if(compare > 0){
                    if (current.getRightNode() == null) {
                        current.setRightNode(new Node(word));
                        current = null;
                    }else{
                        current = current.getRightNode();
                    }
                }else{
                    current.addFrequency();
                    current = null;
                }
            }
        }
    }

    @Override
    public String toString(){
        return this.root.toString();
    }

}
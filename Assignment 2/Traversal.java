import java.util.ArrayList;
import java.util.HashMap;

public class Traversal {

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

    private static final int PRE_ORDER = 0;
    private static final int IN_ORDER = 1;
    private static final int POST_ORDER = 2;

    private ArrayList<TravNode> path;
    private int mode;

    public Traversal(Node node, int mode){
        this.path = new ArrayList<TravNode>();
        this.path.add(new TravNode(node));
        this.mode = mode;
    }

    public static void main(String[] args){
        ArrayList<String> list = new ArrayList<String>();
        list.add("Something");
        list.add("Behind");
        list.add("Us");
        list.add("Doing");
        list.add("Great");
        list.add("Babay");
        list.add("Orders");
        list.add("Cathy");
        BinaryTree tree = new BinaryTree(list);
        System.out.println(tree);
        System.out.println(tree.postOrder());
        Traversal trav = new Traversal(tree.getRoot(), Traversal.POST_ORDER);
        System.out.println(trav.next());
        System.out.println(trav.next());
        System.out.println(trav.next());
        System.out.println(trav.next());
        System.out.println(trav.next());
        System.out.println(trav.next());
        System.out.println(trav.next());
        System.out.println(trav.next());
    }

    public Node next(){
        if(this.path.size() <= 0){
            return null;
        }else{
            // System.out.println(this.path);
            TravNode travNode = this.next(this.path.get(this.path.size() - 1));
            return travNode == null ? null : new Node(travNode);
        }
    }

    private TravNode next(TravNode travNode){

        for (int t = travNode.getStep(); t <= 3; t = travNode.addStep()){

            travNode.getStep();
            // System.out.println(this.path);

            if (       (t == 1 && this.mode == PRE_ORDER)
                    || (t == 2 && this.mode == IN_ORDER)
                    || (t == 3 && this.mode == POST_ORDER)) {
                t = travNode.addStep();
                return travNode; // The node
            } else if (travNode.hasLeftNode() && 
                      ((t == 2 && this.mode == PRE_ORDER)
                    || (t == 1 && this.mode == IN_ORDER) 
                    || (t == 1 && this.mode == POST_ORDER))) {

                t = travNode.addStep();
                TravNode left = new TravNode(travNode.getLeftNode());
                this.path.add(left);
                return this.next(left);
            } else if (travNode.hasRightNode() && 
                      ((t == 3 && this.mode == PRE_ORDER)
                    || (t == 3 && this.mode == IN_ORDER) 
                    || (t == 2 && this.mode == POST_ORDER))) {

                t = travNode.addStep();
                TravNode right = new TravNode(travNode.getRightNode());
                this.path.add(right);
                return this.next(right);
            }
        }
        
        this.path.remove(this.path.size() - 1);
        if (this.path.size() > 0) {
            return this.next(this.path.get(this.path.size() - 1));
        }
        return null;
    }
    
}
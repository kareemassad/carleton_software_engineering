
class Node {
    Node left, right;
    int data;

    //constructor
    public Node(int data){
        this.data = data;
    }
    
    public void insert(int value) {
        if(value <= data) {
            if(left == null){
                left = new Node(value);
            } else{
                left.insert(value);
            }
        } else {
            if (right == null) {
                right = new Node(value);
            } else {
                right.insert(value);
            }
        }
    }
    public boolean contains(int value){
        if(value == data) {
            return true;
        }else if(value < data) {
            if (left == null) {
                return false;
            }else{
                return left.contains(value);
            }
        }else{
            if(right == null) {
                return false;
            } else {
                return right.contains(value);
            }
        }
    }

    public void printInOrder(){ // inorder, preorder, postorder
        if(left != null) {
            left.printInOrder(); //(1), (2), (!)
        }
        System.out.println(data); //(2), (1), (3)
        if(right != null) {
            right.printInOrder(); //(3), (3), (2)
        }
    }

}
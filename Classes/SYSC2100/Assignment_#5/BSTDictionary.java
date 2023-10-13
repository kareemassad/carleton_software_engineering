/**
 * @author Kareem El Assad
 * @version 101107739
 * 
 * @param <E>
 * @param <K>
 */
public class BSTDictionary<E, K extends Sortable> implements Dictionary {

    private BSTNode currentNode, head;

    /**
     *  Constructor
     */
    public BSTDictionary() {
        head = null;
    }

    /**
     * Finds the smallest value on the right side of the tree
     *
     * @param n
     * @return temporary, the most left node on the right of the tree
     */
    public BSTNode<String, SortableString> findMin(BSTNode<String, SortableString> n) {
        BSTNode<String, SortableString> temporary = n;
        //while there are more nodes on the left, keep adding them
        while (temporary.getLeft() != null) {
            temporary = temporary.getLeft();
        }
        return temporary;
    }

    /**
     * recursive method that builds a string out of the content of the tree
     *
     * @param current
     * @return
     */
    public String recPrint(BSTNode current) {
        //base case
        if (current == null)
        // if current is empty then return empty string
            return ""; 

        String s = "";
        s += current.getElement() + " ";
        //get all left
        s += recPrint(current.getLeft()) + " "; 
        //get all right
        s += recPrint(current.getRight()) + " "; 

        return s;
    }

    /**
     * recursive method that determines depth by adding one until there isn't one below it. 
     * Then returns largest number from left/right side
     *
     * @param current
     * @return
     */
    public int recDepth(BSTNode current) {
        if (current == null)
            return 0;//if null there's no depth to the BST
        return 1 + Math.max(recDepth(current.getLeft()), recDepth(current.getRight()));//returns the max number of traversals, adds one each recurrentsive loop
    }

    /**
     * Searching method for the BST
     *
     * @param key
     * @return
     */
    @Override
    public Object search(Sortable key) {
        //if curr is null, set curr to head and return null
        if (this.currentNode == null) {
            this.currentNode = head;
            return null;
        // if same then located
        } else if (this.currentNode.getKey().compareTo(key) == 0) {
            BSTNode temporary = new BSTNode(this.currentNode.getKey(), this.currentNode.getElement(), this.currentNode.getLeft(), this.currentNode.getRight()); //copies node values
            this.currentNode = head;
            return temporary.getElement();
        // is key < current Node's key then continue left
        } else if (key.compareTo(this.currentNode.getKey()) < 0){
            this.currentNode = this.currentNode.getLeft();
            return search(key);
        // if key >  current node's key, continue right
        } else {
            this.currentNode = this.currentNode.getRight();
            return search(key);
        }
    }

    @Override
    public void insert(Sortable key, Object element) {
        BSTNode<String, SortableString> temporary = new BSTNode<String, SortableString>((SortableString) key, (String) element, null, null);

        //if no head then set the temporary node as the new head
        if (this.head == null) { 
            this.head = temporary;
            this.currentNode = this.head;
        //if keys not equal
        } else if (this.currentNode.getKey().compareTo(temporary.getKey()) != 0) {
            //if key < key at currentNode && left node is empty
            if (temporary.getKey().compareTo(this.currentNode.getKey()) < 0) { 
                if (this.currentNode.getLeft() == null) {
                    this.currentNode.setLeft(temporary);
                    this.currentNode = head;
                //if the node was not empty the current node is now the left node
                } else { 
                    this.currentNode = this.currentNode.getLeft();
                    insert(key, element);
                }
            } else if (temporary.getKey().compareTo(this.currentNode.getKey()) > 0) { 
                if (this.currentNode.getRight() == null) {
                    this.currentNode.setRight(temporary);
                    this.currentNode = head;
                } else {
                    this.currentNode = this.currentNode.getRight();
                    insert(key, element);
                }
            }
        }
    }


    /**
     * This method locates and deletes nodes for the given key. It also rotates keys around to 
     * handle the gap.
     * 
     * @param key
     */
    @Override
    public void delete(Sortable key) {
        BSTNode<String, SortableString> current = head;
        BSTNode<String, SortableString> point = null;
        //node to delete
        BSTNode<String, SortableString> target = null;

        //handle argument exception
        if (key == null)
            throw new IllegalArgumentException("Null key");

        //as long as not empty keep searching. 
        while (current != null) {
            //When 0, you found target as values are same
            if (key.compareTo(current.getKey()) == 0)
            {
                target = current;
                break;
            //If the value is less -> get left
            } else if (key.compareTo(current.getKey()) < 0) {
                point = current;
                current = current.getLeft();
            //If value is greater -> get right
            } else {
                point = current;
                current = current.getRight();
            }
        }
        //base condition !null
        if (target != null) {
            //if there are nodes below target, then needs to be switched
            if (target.getLeft() != null && target.getRight() != null) {
                //used to find the min of the right side of the tree
                BSTNode<String, SortableString> temporary = findMin(target.getRight());
                target.key = temporary.getKey();
                target.element = temporary.getElement();
                //clear node
                temporary = null;

                //if only exists left values
            } else if (target.getLeft() != null && target.getRight() == null) {
                //left value is new parent
                point.setLeft(target.getLeft()); 
                //clear node
                target = null;

                //if the node only has right values
            } else if (target.getLeft() == null && target.getRight() != null) {
                point.setRight(target.getRight()); 
                //cleares the node
                target = null;
            } else {
                //cleares the node
                target = null;
            }
        }


    }

    @Override
    /**
     *  This method prints the tree
     */
    public void printTree() {
        System.out.println();
        System.out.println(recPrint(this.head));
    }

    @Override
    /**
     * This method returns the depth of the tree
     */
    public int depth() {
        return recDepth(this.head);
    }
}
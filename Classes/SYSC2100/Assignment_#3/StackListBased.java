import java.util.LinkedList;

class StackListBased<Object> {
    //create object in linked list for stack
    private LinkedList<Object> items;

    /**
     * Construct StackListBased and instantiate items to a new linkedList
     */
    StackListBased(){
        items = new LinkedList<>();
    }
    /**
     * Creat new Stack
     * createStack() ADT
     */
    void createStack() {
        items = new LinkedList<>();
    }
    /**
     * Removes all in stack
     */
    void popAll() {
        items.clear();
    }
    /**
     * Gets size of stack
     * getSize() ADT
     * @return size of stack
     */
    public int getSize() {
        return items.size();
    }
    /**
     * Checks if stack is empty
     * isEmpty() ADT
     * @return true if it is empty
     */
    boolean isEmpty() {
        return items.isEmpty();
    }
    /**
     * Pushes param to the top of the stack
     * push() ADT
     * @param e
     */
    void push(Object e){
        items.push(e);
    }
    /**
     * Removes the object at the top of the stack
     * pop() ADT
     * @return object that was removed from the otp
     */
    Object pop() {
        return items.pop();
    }
    /**
     * Looks at the object at the top of the stack 
     * peek() ADT
     * @return the object at the top of the stack
     */
    Object peek() {
        return items.peek();
    }
}
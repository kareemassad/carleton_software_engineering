package Bird_Example;

import java.util.ArrayList;

public class myStack{

    private ArrayList storage;

    public myStack(){
        this.storage = new ArrayList<>();
    }

    public void pop(){
        storage.remove(0);
    }

    public void push(Object obj){
        this.storage.add(obj);
    }

}

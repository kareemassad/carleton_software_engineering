package org.example;

import java.util.ArrayList;

public class Buffer {
    public String name;
    public ArrayList<String> arrayList;
    int componentsIn, componentsOut;
    public Buffer(String name){
        this.name = name;
        this.arrayList = new ArrayList<String>();
        componentsIn = 0;
        componentsOut = 0;
    }

    public boolean isEmpty(){
        return arrayList.isEmpty();
    }

    public boolean isFull(){
        return (arrayList.size() >= 2);
    }

    public int getSize(){
        return arrayList.size();
    }

    public void addComponent(String component){
        arrayList.add(component);
        componentsIn++;
    }
    public void removeComponent(){
        arrayList.remove(0);
        componentsOut++;
    }

    public String getName(){
        return this.name;
    }

    public void printBufferInfo(){
        System.out.println("Buffer " + name + " had " + componentsIn + " components in and " + componentsOut +" components out.");
    }

}

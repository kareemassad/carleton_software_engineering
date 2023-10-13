/*
    @Description: This class counts the number of times a certain string appears in a large text file. It also compares the elapsed times to completion of a LinkedList approach to an ArrayList approach.
    @Author: Kareem El Assad
    @Date: 2/11/2020
*/
 
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.io.*;


public class countStringInText {
    
    /*
     * Returns the lowest index at which substring pattern begins in text (or else
     * -1).
     */

    private static int findBrute(List<Character> text, List<Character> pattern) {
        int n = text.size();
        int m = pattern.size();
        for (int i = 0; i <= n - m; i++) { // try every starting index
            // within text
            int k = 0; // k is index into pattern
            while (k < m && text.get(i + k) == pattern.get(k))
                // kth character of pattern matches
                k++;
            if (k == m) // if we reach the end of the pattern,
                return i; // substring text[i..i+m-1] is a match
        }
        return -1; // search failed
    }
    /* 
        DONE:A method that makes a LinkedList
        1) The linked list can be as big as any given string (so it must itterate)
        2) It must return the string as chars in the exact given order
        space becomes s -> p -> a -> c -> e  with e being tail.
    */
    public static LinkedList<Character> makeLinkedListStr(String word){
        LinkedList<Character> inOrderedList = new LinkedList<Character>(); //This creates a new LinkedList that java infers type of
        //DONE: Iterate through given word and add one by one (seems like an inefficient solution but only one can think of)
        for (int index = 0; index < word.length();index++) {
            inOrderedList.add(word.charAt(index)); //simply adds whatever the char is at the given iterant value to the list
        }
        return inOrderedList; //should return correct list
        //DONE: check this thing for christs sake lol

    }

    /* 
        TODO:A method that makes an Arraylist
        Literally the same thing as the linked list
    */
    public static ArrayList<Character> makeArrayListStr(String word){
        ArrayList<Character> inOrderedArray = new ArrayList<Character>(); 
        for (int index = 0; index < word.length(); index++) {
            inOrderedArray.add(word.charAt(index)); //same concept,adds whatever the char is at the given iterant value to the list
        }
        return inOrderedArray;  //hopefully return list in correct order
    }
    /* 
        A method that reads a file and checks it line by line.
        Taking a line by line approach to this assignment. The program reads the first line of the file, dissects 
        it into an ADT implentation of just words without spaces. It then iterates through the array and just scans 
        for the pattern given by the user.
        
        @Challenges: 
        1) How the hell do you get rid of the spaces?
        2) is the instanceof operator useful here?
        3) why runtime error from BufferedReader? ... you must close java reader
    */
    public static int searchListSpecificWord(String filename, Object K)  throws IOException {
        if(!(K instanceof LinkedList) && !(K instanceof ArrayList)){
            throw new IllegalArgumentException("Invalid type provded");
        }
        int count = 0;
        String[] wordsFromLine;
        BufferedReader read = new BufferedReader(new FileReader(filename));

        try {
            String lineBeingRead = read.readLine();
            

            while (lineBeingRead != null) {
                //DONE: Create the methods for creating a linkedlist and an Array list BEFORE proceeding.
                wordsFromLine = lineBeingRead.split(""); //The string split() method breaks a given string around matches of the given regular expression.
                // This essentially stores words between spaces
                // given "my name is blah" we now have [my, name, is, blah]
                for (int index = 0; index < wordsFromLine.length; index++) { //iterates through array of all the words in the line
                    //same implentation but LinkedList vs ArrayList
                    if (K instanceof LinkedList && findBrute(makeLinkedListStr(wordsFromLine[index]), (LinkedList<Character>) K) != -1) {
                        count = count + 1;
                    } else if(K instanceof ArrayList && findBrute(makeArrayListStr(wordsFromLine[index]), (ArrayList<Character>) K) != -1) { //same thing but arrayList
                        count = count + 1;
                    }
                    
                }
                lineBeingRead = read.readLine(); //do the same thing for the next line.
            }
        }finally{
            read.close(); //had no idea that in java IO needs to be closed. Kinda annoying...
        }
        return count;
    }

    public static void main(String[] args) throws IOException{
        // Scanner scanText = new Scanner(System.in);
        System.out.println("Please enther the location of the text file (path): ");
        // String filename = scanText.nextLine();
        String filename = "LesMis.txt"; 
        System.out.println("What is the pattern you want checked?");
        // String pattern = scanText.nextLine();
        String pattern = "Javert";
        
        //you must mention that it is type character. I thought java would infer type.
        LinkedList<Character> patternForLinkedList = makeLinkedListStr(pattern);
        ArrayList<Character> patternForArrayList = makeArrayListStr(pattern);

        long originalTime = System.currentTimeMillis(); //current time, thanks for the hint prof haha
        
        int countLinkedList = searchListSpecificWord(filename, patternForLinkedList);
        long elapsedTimeLinkedList = System.currentTimeMillis() - originalTime; //elapsed time for linked list

        int countArrayList = searchListSpecificWord(filename, patternForArrayList);
        long elapsedTimeArrayList = System.currentTimeMillis() - originalTime;

        System.out.println("Using the LinkedList approach: " + countLinkedList + " matches, done in " + elapsedTimeLinkedList + " milliseconds");
        
        System.out.println("Using the ArrayList approach: " + countArrayList + " matches, done in " + elapsedTimeArrayList + " milliseconds");
        
        // scanText.close(); // close scanner
    }
}

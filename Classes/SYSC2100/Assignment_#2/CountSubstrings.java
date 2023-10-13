/**
 *  This program takes the users string of letters and the filename to check number of occurances. The program reads each word in each line
 *  then splits up each word and the users word into tokens. It then compares them and if they are the same it increments count. 
 *  The program also runs the same calculation twice using an ArrayList and a LinkedList. It is used to check which approach took
 *  less time to complete.
 *
 *  @author Kareem El Assad
 *  @version 2.0
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;
import java.io.*;
import java.util.*;


public class CountSubstrings {
    /** Counts the number of times a word occured using the ArrayList approach */
    int countArray = 0;
    /** Counts the number of times a word occured using the LinkedList approach */
    int countList = 0;
    
    /** Time taken to complete using ArrayList approach */
    float timeOfArray = 0;
    /** Time taken to complete using LinkedList approach */
	float timeOfList = 0;

    ArrayList<Character> ArrayList = new ArrayList<Character>();
    LinkedList<Character> LinkedList = new LinkedList<Character>();
    /** Generic ArrayList */
    ArrayList<Character> blueArrayList;
    /** Generic LinkedList */
    LinkedList<Character> blueLinkedList;

    public CountSubstrings() {
        // blank intentionally
    }

    //MAIN Method

    public static void main(String[] args) throws IOException {
        try {
            /** New object called blue */
            CountSubstrings blue = new CountSubstrings();
            /** Input reader */
            Scanner input = new Scanner(System.in);

            // Prompt the user to pick the file to search.
            System.out.print("Please enter the file you want to search: " + "\n");
            /** File being searched */
            String filename = input.nextLine();

            // Prompt the user to pick a word to be parsed.
            System.out.print("Please enter the word you want to search for: " + "\n");
            /** Word being parsed*/
            String word = input.nextLine();

            input.close();

            //call method to check words
            blue.checkWords(word, filename);


        } catch (IOException e) {
            System.out.println("file could not be found");
        }
    }
    
    /** 
     *  This method confirms to the user the word and the filename being searched. It splits word and every word of every line
     *  into tokens. It then compares them, if they contain the same tokens in order, it increases the respective counts.
     *  
     *  @param word describes the specific word the user inputed to be searched.
     *  @param filename describes the name of the file to search.
     *  @return print out the word and filename being used to the user. Then prints the results.
    */
    public void checkWords(String word, String filename) throws IOException{
        try {
            System.out.println("The String " + word + " will be searched in file " + filename + "\n");

            BufferedReader reader = new BufferedReader(new FileReader(filename));

            String read = reader.readLine();

            //read through and store each char
            for (int index = 0; index < word.length(); index++) {
                ArrayList.add(word.charAt(index));
                LinkedList.add(word.charAt(index));

            }
            // while there is something to be read, split into tokens and count time. 
            while(read != null) {
                StringTokenizer object = new StringTokenizer(read);

                //Tests if there are more tokens available from this tokenizer's string.
                while (object.hasMoreTokens()) {
                    time(object);
                    
                }
                read = reader.readLine();
            }
            System.out.println("Using ArrayList, found " + countArray + "matches, delivered in " + timeOfArray + " milliseconds.");
			System.out.println("Using LinkedList, found " + countList + "matches, delivered in "  + timeOfList + " milliseconds.");
            
            // reader.close();
        } catch (IOException e) {
            //DONE: handle exception
            System.out.println("Error! File not found or invalid String");
			System.exit(1);
        }
    }
    /** 
     * This method checks the start and ending time of the program using the two different approaches
     * 
     * @param object is the word given from the user broken up into tokens.
     */
    private void time(StringTokenizer object) {
        blueArrayList = new ArrayList<Character>();
        blueLinkedList = new LinkedList<Character>();

        /** Returns next token in object */
        String token = object.nextToken();

        for (int index = 0; index < token.length(); index++) {
            blueArrayList.add(token.charAt(index));
            blueLinkedList.add(token.charAt(index));
        }

        //count start time for ArrayList approach
		long startArray = System.currentTimeMillis();
		if(findBrute(blueArrayList,ArrayList)!=-1){
			countArray++;
        }
        //count end time for ArrayList approach
		long endArray = System.currentTimeMillis();
		timeOfArray = timeOfArray + (endArray-startArray);


		//count start time for LinkedList approach
		long timeStartList = System.currentTimeMillis();
		if(findBrute( blueLinkedList,LinkedList)!=-1){
			countList++;
        }
        //count end time for LinkedList approach
		long timeEndList = System.currentTimeMillis();
		timeOfList = timeOfList + (timeEndList-timeStartList);


    }
    /** 
     * Returns the lowest index at which substring pattern begins in text or -1 fail. 
     * 
     * @param text
     * @param pattern
     * @return substring text[...] is a match or failed
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
}
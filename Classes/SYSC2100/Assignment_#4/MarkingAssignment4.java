/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

 


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;


/**
 *
 * @author JDM, MW
 */
public class MarkingAssignment4 extends Assignment4 {
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        
        BufferedReader keyboardReader = new BufferedReader(new InputStreamReader(System.in));
        String input = new String();
        System.out.println("EXERCISE 3\n");
        //EXERCISE 3 TEST
        // read in substring pattern, catching any exceptions
        try {
            while (true) {
                System.out.print("Enter DIGITS: ");
                input = keyboardReader.readLine();
                break;
            }
        } catch (IOException e) {
            System.out.println(e);
        }  
                //convertToNumber(input);
                System.out.println(convertToNumber(input));
                
               
        
        
         //EXERCISE 2 TEST
        System.out.println("\n\nEXERCISE 2\n");
                // read in substring pattern, catching any exceptions
        try {
            while (true) {
                System.out.print("Enter word: ");
                input = keyboardReader.readLine();
                break;
            }
        } catch (IOException e) {
            System.out.println(e);
        }  
                //convertToNumber(input);
                //System.out.println(convertToNumber(input));
                
                
                
                if (isInLanguage(input)){
                    System.out.println("Word \"" + input + "\" IS a word of the L language");
                }
                else {
                    System.out.println("Word \"" + input + "\" is NOT a word of the L language");
                }
                
           System.out.println("");

           System.out.println("\n\nEXERCISE 1\n");
                
           //EXERCISE 1 TEST
           String [] anArray = {"Ben","Ali","Nancy","Paul","Zack","Caroline","Greg","Rebecca","Emily","Jake"}; // unsorted array of 10    
           
           Integer [] anArray2 = {29, 10, 14, 37, 13}; // unsorted array of 10   
           
           int i, n;
           
           System.out.println("ORIGINAL : Selection Sort\n");
           for (i=0;i<anArray.length;i++)
           {
               System.out.println(anArray[i]);
           }
                      System.out.println("");
           System.out.println("After SELECTION SORT: \n");
           recursiveSelectionSort(anArray,anArray.length);
           
           for (i=0;i<anArray.length;i++)
           {
               System.out.println(anArray[i]);
           }
           
           
           System.out.println("\n\nORIGINAL : Bubble Sort\n");
           for (i=0;i<anArray2.length;i++)
           {
               System.out.println(anArray2[i]);
           }
           
           System.out.println("\nAfter BUBBLE SORT: \n");
           recursiveBubbleSort(anArray2,anArray2.length);
           
           for (i=0;i<anArray2.length;i++)
           {
               System.out.println(anArray2[i]);
           }
                     
    }
}

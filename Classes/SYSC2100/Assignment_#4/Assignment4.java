import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * This class is made for the sysc2100 Assignment number 4
 * 
 * @author Kareem El Assad
 * @version 3/26/2020
 * 
 */
class Assignment4 {
    /**
     * A recursive implementation of the selection sort.
     * @param <T>   Array type
     * @param theArray  The array being sorted  
     * @param n the size of the array
     */
    public static <T extends Comparable<? super T>> void recursiveSelectionSort (T[] theArray, int n){
        //keeps track of spot before the end of the array
        int greatestIndex = n - 1;

        for (int index = n-2; index >= 0; index--){
            if (theArray[index].compareTo(theArray[greatestIndex]) > 0) {
                greatestIndex = index;
            }
        }
        if (greatestIndex != n-1) {
            T temp = theArray[greatestIndex];
            theArray[greatestIndex] = theArray[n-1];
            theArray[n-1] = temp;
        }
        if(n > 2) {
            recursiveSelectionSort(theArray, n-1);
        }
    }
    /**
     * A recursive implementation of the bubble sort.
     * @param <T>   Array type
     * @param theArray  The array being sorted
     * @param n size of the array
     */
    public static <T extends Comparable<? super T>> void recursiveBubbleSort (T[] theArray, int n){
        //sorted identifier set as true for the recursion to work
        boolean checker = true;
        for(int i = n - 1; i > 0; i--){
            /*  returns >0 if curr > curr-1
                returns 0 if curr = curr-1
                return <0 if curr < curr-1
            */
            // if the current value is smaller than the one before then do the switch
            if(theArray[i].compareTo(theArray[i-1]) < 0){
                //simple switcheroo
                T temp = theArray[i];
                theArray[i] = theArray[i-1];
                theArray[i-1] = temp;
                //not sorted identifier
                checker = false;
            }
            //if it isnt true then it is not sorted and so run it again
            if (checker != true) {
                recursiveBubbleSort(theArray, n-1);
            }
        }
    }
    //wasnt this on the midterm?
    /**
     * This program takes a string and check if it is in the L language. Words in the L language
     * follow this format: "pens$snep". The program stores the part of the word before the $ in 
     * a queue and the part after the $ in the stack. It will then check if the queue and stack 
     * have the same size. If they do, it will then remove a letter from the queue and the stack. 
     * If they are the same all the way then the string is in the language otherwise it is not.
     * 
     * @param str string given
     * @return true if the string is in the language
     */
    public static boolean isInLanguage (String str) {
        //need to use both a queue and a stack
        //dont need to put second types are java (should) infer type
        Queue<Character> QQ = new LinkedList<>();
        Stack<Character> SS = new Stack<>();
        int i = 0;

        //Base case. All chars will be in order until $. They will only need to be reversed after the $.
        while(str.charAt(i) != '$') {
            //add current char to QQ
            QQ.add(str.charAt(i)); 
            i++;
        }
        // This is very important as to get past the $ condition when the loop above stops
        i++;
        //fill the stack with the rest
        while (i < str.length()) {
            SS.add(str.charAt(i));
            i++;
        }
        //check first case if they are equal size, if not we know it is not in the language
        if (QQ.size() == SS.size()) {
            for(i = 0; i < QQ.size(); i++) {
                // Head of queue removed should be equal top of the stack. Fail otherwise.
                if (QQ.remove() != SS.pop()) {
                    return false;
                }
            }
            //if all above passed.
            return true;
        }

        return true;
    }

    /**
     * This method takes a string of spaces and numbers then it to a number
     * @param str String to convert to a number
     * @return  returns a number representing
     */
    public static int convertToNumber (String str) {
        int n = 0;
        try {
            // Added this section as to increase the efficiency in best case
            n = Integer.parseInt(str);  
            return n;          
        } catch (NumberFormatException e) {
            //DONE: handle exception
            //loop through string
            for (int index = 0; index < str.length(); index++) {
                //If it is not a space it is a valid number
                if (str.charAt(index) != ' ') {
                    /* Since every new number from the right is 10x smaller than the number 
                    before it in terms of locations ie: ones, tens, hundreds, thousands...
                    We can deduce that any number (2nd number+) parsed from the left will
                    follow a similar pattern. 
                    ie: 247
                    > 2
                    > (2*10) + 4 = 24
                    > (24*10) + 7 = 247 DONE!
                    */
                    n = (n*10) + Character.getNumericValue(str.charAt(index));
                }
            }
            return n;
        }
    }
}
/*
    @Author Kareem El Assad
    @Student_Number 101107739
    @Version 1/30/2020
*/

public class Assignment1 {

    // Question 1
    /*
     * Write a Java method for the Spock problem c(n, k) seen in class. Include
     * output code that shows the actual sequence of calls that are made and the
     * value that they will return when the method is executed. For example, c(3, 2)
     * outputs the following:
     * 
     * @param n is the number of planets in system
     * 
     * @param k is number of planets you can visit
     * 
     * @return possible combos of planets you can visit
     */

    public static int c(int n, int k) {
        // c(n,k) = c(n-1, k-1) + c(n-1, k)
        // Base Cases
        if (n == k) {
            // equal, 1 combo
            return 1;
        } else if (k == 0) {
            // no time, 1 combo
            return 1;
        } else if (k == 1) {
            // time only for one planet, combos as many as n
            return n;
        } else if (k > n) {
            // No time to visit any, no possible combinations
            return 0;
        } else {
            // return eqn case
            return c(n - 1, k - 1) + c(n - 1, k);
        }
    }

    // QUESTION 2
    /*
     * Write a Java method for the problem of Organizing a Parade as presented in
     * class. Name your method P.
     * 
     * RULES: 1) Parade will consist of bands and floats in a single line 2) One
     * band cannot be placed immediately after another
     * 
     * PROBLEM: How many ways can you organize a parade of lenth n?
     * 
     * LET: 1) P(n) = number of ways to organize parade length n - P(n) = P(n-1) +
     * P(n-2) 2) F(n) = number of parades of length n that end with a float - F(n) =
     * P(n-1) 3) B(n) = number of parades of length n that end with a band - B(n) =
     * F(n-1)
     */
    public static int P(int n) {
        /*
         * EQN: P(n) = F(n) + B(n)
         * 
         * BASE CASES: P(1) = 2 (Parades of length 1 are float and band) P(2) = 3
         * (Parades of length 2 are float-float, band-float, and float-band)
         */
        if (n == 1) {
            // Base case 1
            return 2;
        } else if (n == 2) {
            return 3;
        } else {
            return P(n - 1) + P(n - 2);
        }
    }

    // QUESTION 3
    /*
     * Write a recursive Java method writeLine that writes a character repeatedly to
     * form a line of n characters. For example, writeLine(‘*’,5) produces the line
     * *****. Then write a recursive method writeBlock that uses writeLine to write
     * m lines of n characters each. For example, writeBlock(‘*’, 5, 3) produces the
     * output:
     ***** 
     ***** 
     *****
     * 
     * ch = char shape n = number to print line m = coloumns
     */
    public static void writeLine(char ch, int n) {
        if (n == 1) {
            System.out.print(ch);
        } else {
            System.out.print(ch);
            writeLine(ch, n - 1);
        }
    }

    public static void writeBlock(char ch, int n, int m) {
        if (m == 1) { // base case
            writeLine(ch, n); // print line character
            System.out.println("\n");
        } else {
            writeLine(ch, n); // print
            System.out.println("\n");
            writeBlock(ch, n, m - 1); // repeat
        }
    }

    // QUESTION 4
    /*
     * Write a recursive Java method that writes the digits of a positive decimal
     * integer in reverse order. Name your method reverseDigits. number = number to
     * reverse
     */
    public static void reverseDigits(int number) {
        /* NON recursive solution, OOPS! */
        // int reversed = 0;
        // while(number != 0){
        // int digit = number % 10;
        // reversed = reversed * 10 + digit;
        // number /= 10;
        // }
        // System.out.print(reversed);

        /* Recursive solution! */
        String s = Integer.toString(number); // num to string
        if (s.length() == 1) { // base case
            System.out.println(s); // if length is one just print
        } else {
            System.out.print(s.charAt(s.length() - 1)); // print value at end of string
            reverseDigits(Integer.parseInt(s.substring(0, s.length() - 1))); // drops last char, back to int
        }
    }

    // Question 5
    /*
     * Implement the recursive binary search algorithm presented in class for an
     * array of strings. Name your method myBinarySearch.
     * 
     * Compare x with the middle element. If x matches with middle element, we
     * return the mid index. Else If x is greater than the mid element, then x can
     * only lie in right half subarray after the mid element. So we recur for right
     * half. Else (x is smaller) recur for the left half.
     * 
     * anArry = array of strings 
     * first = first index value looked at 
     * last = last
     * index value looked at value = target string
     */
    public static int myBinarySearch(String[] anArray, int first, int last, String value) {
        int midpoint = (last + first) / 2; //midpoint
        if (value.compareTo(anArray[midpoint]) < 0) {   //if in first half, iterate through.
            return myBinarySearch(anArray, first, midpoint, value); //recursive call in 1st half
        } 
        else if (value.compareTo(anArray[midpoint]) > 0) {  //if in second half, iterate through.
            return myBinarySearch(anArray, midpoint, last, value); //recursive call for the 2nd half
        }
        else {  //if equal then you found it woo!
            return midpoint;
        }
    }

    public static void main(String[] args) {
        System.out.println(" "); // vscode java print buffer
        
        //Q1
        // System.out.println(c(102,101)); 
        
        //Q2
        // System.out.println(P(3)); 
        
        //Q3
        // writeBlock('*', 5, 3); 
        
        // Q4
        // reverseDigits(101107739); 

        //Q5
        // String arr[] = {"a", "b", "c", "d"};
        // System.out.println(myBinarySearch(arr, 0, arr.length, "b"));
    }
}
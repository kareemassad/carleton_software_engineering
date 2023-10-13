import java.io.*;

public class Assignment3 {

    public static void main(String[] args) {
	BufferedReader keyboardReader = new BufferedReader(new InputStreamReader(System.in));
	String input = new String();
	//read in substring pattern, catching any exceptions
        try{
            while (true){
                System.out.print("Enter the G-language word to check: ");
                input = keyboardReader.readLine();
                break;
                }
        }catch(IOException e) {
            System.out.println(e);
        }
        LanguageRecognizerG w1 = new LanguageRecognizerG(input);
        w1.recursivePrintG();

    //read in infix expression, catching exceptions
        try{
            while (true){
                System.out.print("Enter the infix expression to evaluate: ");
                input = keyboardReader.readLine();
                break;
            }
        } catch (IOException e) {
            System.out.println(e);
        }

    InfixCalculator w2 = new InfixCalculator(input);
    w2.evaluateInfix();
    }
}
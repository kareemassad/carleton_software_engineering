import java.util.InputMismatchException;
import java.util.Scanner;

public class MyExceptionTestProgram_1 {
    public static void main(String[] args) {

        int number1 = 0, number2 = 0, result = 0;
        Scanner keyboard;
        keyboard = new Scanner(System.in);
        try {
            System.out.println("Enter the first number:");
            number1 = keyboard.nextInt();
            System.out.println("Enter the second number:");
            number2 = keyboard.nextInt();
            result = number1 / number2;
        }catch (InputMismatchException e){
            System.out.println("Input Type Mismatch!");
        }catch (ArithmeticException e){
            System.out.println("Can not divide by zero!");
        }
        System.out.print(number2 + " goes into " + number1);
        System.out.print(" this many times: ");
        System.out.println(result);
    }
}

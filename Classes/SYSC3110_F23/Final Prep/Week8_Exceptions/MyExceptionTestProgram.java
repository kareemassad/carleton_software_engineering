import java.util.Scanner;
public class MyExceptionTestProgram {
    public static String getName(){
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Enter your name");
        String name = keyboard.nextLine();
        if (name.length() <= 0)
            throw new NullPointerException();
        return name;
    }
    public static void main(String[] args) {
        String name = "";
        boolean gotValidName = false;

        while (!gotValidName) {
            try{
                name = getName();
                gotValidName = true;
            }catch(NullPointerException e){
                System.out.println("Name is empty, Please enter your name again");
                gotValidName = false;
            }
        }
        System.out.println("Hello " + name);

    }


}


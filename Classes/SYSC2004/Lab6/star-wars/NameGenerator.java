import java.lang.*;
import java.util.Scanner; //used for user input
/**
 * Write a description of class NameGenerator here.
 *
 * @author Kareem El Assad
 * @version 1.0
 */
public class NameGenerator
{
    // instance variables - replace the example below with your own

    /**
     * Constructor for objects of class NameGenerator
     */
    public NameGenerator()
    {
        //empty
    }

    /**
     * An example of a method - replace this comment with your own
     *
     * @param  firstName  takes the first name of the user
     * @param  lastName   takes the last name of the user
     * @param  mother     takes the mother's maiden name
     * @param  city       take the city the user was born in
     * @return outputs a concantinated string 
     */
    public static String generateStarWarsName(String firstName, String lastName, String mother, String city) {
        // put your code here
        String concFirstName;
        String concLastName;
        String concMother;
        String concCity;

        concFirstName = firstName.substring(0, 2);
        concLastName = lastName.substring(0, 3);
        concMother = mother.substring(0, 2);
        concCity = city.substring(0, 3);

        return  concLastName + concFirstName+ " " + concMother + concCity;
    }

    public static void main(String[] args) {
        String firstName;
        String lastName;
        String motherMaidenName;
        String cityBorn;

        Scanner Obj = new Scanner(System.in);

        System.out.println("Enter your first name");
        firstName = Obj.nextLine();

        System.out.println("Enter your last name");
        lastName = Obj.nextLine();
        System.out.println("Your full name is:" + firstName + " " + lastName);

        System.out.println("Enter your mom's maiden name");
        motherMaidenName = Obj.nextLine();

        System.out.println("Enter the city you were born in");
        cityBorn = Obj.nextLine();
        
        System.out.println("Your star wars name is " + generateStarWarsName(firstName, lastName, motherMaidenName, cityBorn));
    }
}
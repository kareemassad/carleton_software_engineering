import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Kitchen {
    // 3 chef threads
    // 1 agent thread
    // each chef has 1 ingredient
    // * agent randomly requests 1 ingredient (already has 2 other at random)
    // chef fulfills request and consumes sandwich.
    // chef signals to agent that it is done.
    // agent repeats * instruction

    public static int sandwich_count;

    // Method to increment total sandwich count (Assignment limit 20)
    public static void incrementSandwichCount() {
        sandwich_count++;
    }

    // Method to get total sandwich count
    public static int getSandwichCount() {
        return sandwich_count;
    }

    public static void main(String[] args) {
        List<String> table = Collections.synchronizedList(new ArrayList<String>());

        // producers
        Thread chef1 = new Thread(new Chef(table, "Peanut Butter"), "Peanut Butter Chef");
        Thread chef2 = new Thread(new Chef(table, "Jam"), "Jam Chef");
        Thread chef3 = new Thread(new Chef(table, "Bread"), "Bread Chef");

        // consumers
        Thread agent = new Thread(new Agent(table, "Bread", "Jam", "Peanut Butter"), "Agent");

        // start producers
        System.out.println("Starting to make sandwiches");
        chef1.start();
        chef2.start();
        chef3.start();

        // start consumers
        agent.start();
    }

}

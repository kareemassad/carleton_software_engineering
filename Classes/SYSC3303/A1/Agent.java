import java.util.List;
import java.util.Random;

//Agent thread class using Runnable
public class Agent implements Runnable {

    private String ingredient1, ingredient2, ingredient3;
    private int rand_number;

    public List<String> table;

    // constructor
    public Agent(List<String> table, String ingredient1, String ingredient2, String ingredient3) {
        this.table = table;
        this.ingredient1 = ingredient1;
        this.ingredient2 = ingredient2;
        this.ingredient3 = ingredient3;
    }

    // Not sure why we need to override but it doesn't work without it
    @Override
    public void run() {
        while (true) {
            // moved logic to seperate method so I could synchronize it. I feel like I did
            // something wrong to get here.

            placeIngredientsOnTable();
        }

    }

    public synchronized void placeIngredientsOnTable() {
        // while table is full
        while (!table.isEmpty()) {
            // wait till table is empty
            try {
                System.out.println("Table is not empty!");
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        while (table.size() != 2) {
            Random random = new Random();
            rand_number = random.nextInt(3);

            // rand_number will represent the missing ingredient
            // Bread = 0, Peanut = 1, Jam = 2
            if (rand_number == 0) {
                System.out.println(Thread.currentThread().getName() + " is putting " + ingredient1 + " on the table!");
                table.add(ingredient1);

            } else if (rand_number == 1) {
                System.out.println(Thread.currentThread().getName() + " is putting " + ingredient2 + " on the table!");
                table.add(ingredient2);

            } else if (rand_number == 2) {
                System.out.println(Thread.currentThread().getName() + " is putting " + ingredient3 + " on the table!");
                table.add(ingredient3);
            }
        }
        notifyAll();
    }

}

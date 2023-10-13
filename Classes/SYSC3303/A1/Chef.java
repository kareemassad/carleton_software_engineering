import java.util.List;

//Chef thread class using Runnable
public class Chef implements Runnable {

    private String chefs_ingredient;
    private List<String> table;

    // constructor
    public Chef(List<String> table, String chefs_ingredient) {
        this.chefs_ingredient = chefs_ingredient;
        this.table = table;
    }

    // moved logic into seperate method as it wasn't working in run()
    public synchronized void create_sandwich() {
        for (String ingredient : table) {
            while (ingredient.equals(chefs_ingredient) || table.isEmpty()) {
                // goes through if the ingredient is the same as the chef's ingredient or if the
                // table is empty
                try {
                    // wait till agent notifies that there are ingredients on the table
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        // clear table for next sandwich
        table.clear();
        Kitchen.incrementSandwichCount();
        System.out.println("Sandwich made!");
        System.out.println("Total Sandwiches made: " + Kitchen.getSandwichCount());

        notifyAll();
    }

    @Override
    public void run() {
        // keep running until all 20 sandwiches are made
        while (Kitchen.getSandwichCount() < 20) {
            create_sandwich();
        }
    }

}

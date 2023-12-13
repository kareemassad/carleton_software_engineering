// Bill.java
public abstract class Bill {
    protected int minutes;
    protected double rate;
    protected static final double GST = 0.9;

    public Bill(int m, double r) {
        minutes = m;
        rate = r;
    }

    // Template method
    public final double getBillableAmount() {
        double base = calculateBase();
        double tax = calculateTax(base);
        return base + tax;
    }

    // Abstract methods for the steps that vary
    protected abstract double calculateBase();

    protected abstract double calculateTax(double base);
}

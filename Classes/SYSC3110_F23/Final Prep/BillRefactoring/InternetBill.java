// InternetBill.java
public class InternetBill extends Bill {
    public InternetBill(int m, double r) {
        super(m, r);
    }

    @Override
    protected double calculateBase() {
        return 30 + minutes * rate;
    }

    @Override
    protected double calculateTax(double base) {
        return base * GST;
    }
}

// PhoneBill.java
public class PhoneBill extends Bill {
    public PhoneBill(int m, double r) {
        super(m, r);
    }

    @Override
    protected double calculateBase() {
        return 20 + minutes * rate;
    }

    @Override
    protected double calculateTax(double base) {
        return base * GST * 0.2;
    }
}

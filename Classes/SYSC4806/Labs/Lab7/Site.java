public abstract class Site {
    protected static final double TAX_RATE = 0.50;

    // Template method
    public final double getBillableAmount(double _units, double _rate) {
        return getBaseAmount(_units, _rate) + getTaxAmount(_units, _rate);
    }

    protected abstract double getBaseAmount(double _units, double _rate);

    protected abstract double getTaxAmount(double _units, double _rate);
}

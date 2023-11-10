public class ResidentialSite extends Site {
    @Override
    protected double getBaseAmount(double _units, double _rate) {
        return _units * _rate;
    }

    @Override
    protected double getTaxAmount(double _units, double _rate) {
        double base = getBaseAmount(_units, _rate);
        return base * Site.TAX_RATE;
    }
}

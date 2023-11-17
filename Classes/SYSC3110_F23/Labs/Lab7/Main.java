public class Main {
    public static void main(String[] args) {
        Site lifelineSite = new LifelineSite();
        Site residentialSite = new ResidentialSite();

        double units = 100;
        double rate = 1.5;

        double lifelineBill = lifelineSite.getBillableAmount(units, rate);
        double residentialBill = residentialSite.getBillableAmount(units, rate);

        System.out.println("Lifeline Bill: " + lifelineBill);
        System.out.println("Residential Bill: " + residentialBill);
    }
}

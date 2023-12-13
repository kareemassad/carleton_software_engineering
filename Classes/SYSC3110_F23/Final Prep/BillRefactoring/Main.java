// Main.java
public class Main {
    public static void main(String[] args) {
        Bill phoneBill = new PhoneBill(100, 0.5);
        System.out.println("Phone Bill: " + phoneBill.getBillableAmount());

        Bill internetBill = new InternetBill(100, 0.5);
        System.out.println("Internet Bill: " + internetBill.getBillableAmount());

    }
}

public class MyExceptionTestProgram_2 {


    public static void main(String[] args) throws WithdrawalException {
        BankAccount b = new BankAccount("Bob");
        b.deposit(100);
        b.deposit(500.00f);
        b.withdraw(25.00f);
        try {
            b.withdraw(1000000);

        }catch (WithdrawalException e){
            System.out.println(e.getMessage());
        }
        try {
            b.withdraw(25.00f);
            b.withdraw(189.45f);
            b.deposit(100.00f);

        }catch (WithdrawalException e){
            System.out.println(e.getMessage());
        }
        System.out.println("Balance is "+ b.getBalance());
    }
}

public class BankAccount {
    double balance;
    String name;
    public BankAccount(String name){
        this.balance = 0;
        this.name = name;
    }
    public double getBalance(){ return balance;}
    public void withdraw(float anAmount) throws WithdrawalException{
        if (anAmount <= this.balance) {
            this.balance -= anAmount;
        } else
            throw new WithdrawalException("Insufficient Fund in account to withdraw");
    }

    public void deposit(float i) {
        this.balance += i;
    }
}

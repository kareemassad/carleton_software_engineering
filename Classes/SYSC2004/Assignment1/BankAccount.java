/**
 * Write a description of class BankAccount here.
 *
 * @author (Kareem El Assad)
 * @version (1/26/2020)
 */
public class BankAccount
{
    private double currentBalance;
    private double accountNumber;

  public BankAccount (){
      accountNumber = 0;
      currentBalance = 0;
  }
  public BankAccount(int number, int initialBalance){
    accountNumber = number;
    currentBalance = initialBalance;
  }
  public void debit(double amount){
    currentBalance = currentBalance - amount;
  }
  public void credit(double amount){
    currentBalance = currentBalance + amount;
  }
  //accessors
  public double getBalance() {
    return currentBalance;
  }
  //Printer
  public void printInfo(){
    System.out.println("Your current balance is: " + currentBalance);
    System.out.println("Your account number is: " + accountNumber);
  }
}

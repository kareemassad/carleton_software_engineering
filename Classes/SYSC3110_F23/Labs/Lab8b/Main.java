//import controller.AddressBookController;
//import model.AddressBook;
//import view.AddressBookView;

public class Main {
    public static void main(String[] args) {
            AddressBook model = new AddressBook();
            AddressBookView view = new AddressBookView();
            AddressBookController controller = new AddressBookController(model, view);
            view.setVisible(true);
    }
}

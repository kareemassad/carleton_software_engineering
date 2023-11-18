import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;

public class TodoListView {
    private JFrame frame;
    private JList<String> todoList;
    private DefaultListModel<String> listModel;
    private JButton strikeButton;
    private JButton addButton;

    public TodoListView(){
        frame = new JFrame("Todo List");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400,300);

        listModel = new DefaultListModel<>();
        todoList = new JList<>(listModel);
        frame.add(new JScrollPane(todoList), BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        strikeButton = new JButton("Strike Through");
        addButton = new JButton("Add Task");
        buttonPanel.add(strikeButton);
        buttonPanel.add(addButton);
        frame.add(buttonPanel, BorderLayout.SOUTH);

        frame.setVisible(true);
    }

    public void setTodoItems(List<String> items){
        listModel.clear();
        for(String item: items){
            listModel.addElement(item);
        }
    }

    public void addStrikeListener(ActionListener listener){
        strikeButton.addActionListener(listener);
    }

    public int getSelectedIndex(){
        return todoList.getSelectedIndex();
    }

    public void strikeThroughItem(int index){
        String item = listModel.get(index);
        listModel.set(index, "<html><strike>" + item + "</strike></html>");
    }
    public void addAddButtonListener(ActionListener listener){
        addButton.addActionListener(listener);
    }

    public Object getFrame() {
        return frame;
    }
}

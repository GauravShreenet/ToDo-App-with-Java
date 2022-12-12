import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;





public class MyFrame extends JFrame implements ActionListener {
    JPanel panel;
    JLabel label;
    private JList list;
    private DefaultListModel listModel;
    Container window;
    JTextField textField;
    JButton addTask, clearButton;


    public MyFrame() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window = getContentPane();
        window.setLayout(new FlowLayout());
        this.setTitle("Vowel Count");
        this.setSize(400,450);

        this.setResizable(true);
        Border b = BorderFactory.createEmptyBorder();
        listModel = new DefaultListModel();
        list = new JList(listModel);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setSelectedIndex(0);
        list.setVisibleRowCount(20);

        JScrollPane listScrollPane = new JScrollPane(list);
        list.setBackground(new Color(238, 238, 238));

        Dimension d = list.getPreferredScrollableViewportSize();
        d.width = 300;

        listScrollPane.setPreferredSize(d);
        listScrollPane.setBorder(b);

        label = new JLabel("Task Name");
        window.add(label);

        textField = new JTextField(20);
        this.add(Box.createVerticalStrut(50));
        window.add(textField);

        addTask = new JButton("Add Task");
        window.add(addTask);
        this.add(Box.createHorizontalStrut(100));
        addTask.addActionListener(this);

        clearButton = new JButton("Clear Task");
        window.add(clearButton);
        clearButton.addActionListener(this);

        panel = new JPanel();


        panel.add(listScrollPane, BorderLayout.CENTER);
        window.add(panel, BorderLayout.CENTER);




        this.setVisible(true);
    }



    public void actionPerformed(ActionEvent event){
        Object o = event.getSource();
        String task;
        int index;

        if (o == addTask) {
            task = textField.getText();
            //User did not type a unique name...
            if (task.equals("") || alreadyInList(task)) {
                Toolkit.getDefaultToolkit().beep();
                textField.requestFocusInWindow();
                textField.selectAll();
                return;

            }

            index = list.getSelectedIndex();//get selected index
            //no selection, so insert at beginning
            if (index == -1) {
                index = 0;

            } else {//add after the selected item
                index++;
            }
            listModel.insertElementAt(textField.getText(), index);
            //Reset the text field
            textField.requestFocusInWindow();
            textField.setText("");

            //Select the new item and make it visible.
            list.setSelectedIndex(index);
            list.ensureIndexIsVisible(index);
        } else if (o == clearButton) {
            index = list.getSelectedIndex();
            listModel.remove(index);

        }



    }

    public boolean alreadyInList(String task){
        return listModel.contains(task);
    }



}

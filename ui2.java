import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class PlayerManagement extends JFrame {

    private ArrayList<Player> players = new ArrayList<>();
    private DefaultListModel<String> listModel = new DefaultListModel<>();
    private JList<String> playerList = new JList<>(listModel);

    public PlayerManagement() {
        setTitle("Manage Players");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Input fields
        JTextField nameField = new JTextField();
        JTextField ageField = new JTextField();
        JTextField weightField = new JTextField();
        JTextField positionField = new JTextField();

        JButton addButton = new JButton("Add Player");
        JButton deleteButton = new JButton("Delete Selected Player");
        JButton backButton = new JButton("Back");

        addButton.addActionListener(e -> {
            try {
                String name = nameField.getText();
                int age = Integer.parseInt(ageField.getText());
                double weight = Double.parseDouble(weightField.getText());
                String position = positionField.getText();

                Player p = new Player(name, age, weight, position);
                players.add(p);
                listModel.addElement(p.toString());

                nameField.setText("");
                ageField.setText("");
                weightField.setText("");
                positionField.setText("");

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Please enter valid player data.");
            }
        });

        deleteButton.addActionListener(e -> {
            int selectedIndex = playerList.getSelectedIndex();
            if (selectedIndex != -1) {
                players.remove(selectedIndex);
                listModel.remove(selectedIndex);
            }
        });

        backButton.addActionListener(e -> dispose());

        JPanel formPanel = new JPanel(new GridLayout(5, 2, 5, 5));
        formPanel.setBorder(BorderFactory.createTitledBorder("Player Information"));
        formPanel.add(new JLabel("Name:"));
        formPanel.add(nameField);
        formPanel.add(new JLabel("Age:"));
        formPanel.add(ageField);
        formPanel.add(new JLabel("Weight:"));
        formPanel.add(weightField);
        formPanel.add(new JLabel("Position:"));
        formPanel.add(positionField);
        formPanel.add(addButton);
        formPanel.add(deleteButton);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(formPanel, BorderLayout.NORTH);
        mainPanel.add(new JScrollPane(playerList), BorderLayout.CENTER);
        mainPanel.add(backButton, BorderLayout.SOUTH);

        add(mainPanel);
    }
}

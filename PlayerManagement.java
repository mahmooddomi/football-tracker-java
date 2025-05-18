import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class PlayerManagement extends JFrame {

    private DefaultListModel<String> playerListModel = new DefaultListModel<>();
    private JList<String> playerList = new JList<>(playerListModel);
    private ArrayList<String> players = new ArrayList<>();

    public PlayerManagement() {
        setTitle("🧍 Player Management");
        setSize(550, 530);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        getContentPane().setBackground(new Color(30, 30, 30));

        Font font = new Font("Segoe UI", Font.PLAIN, 14);

        // ====== Input Fields ======
        JTextField nameField = new JTextField();
        JTextField ageField = new JTextField();
        JTextField weightField = new JTextField();
        JTextField positionField = new JTextField();

        JTextField[] fields = {nameField, ageField, weightField, positionField};
        for (JTextField field : fields) {
            field.setBackground(new Color(50, 50, 50));
            field.setForeground(Color.WHITE);
            field.setCaretColor(Color.WHITE);
            field.setFont(font);
        }

        JLabel[] labels = {
            new JLabel("Name:"), new JLabel("Age:"),
            new JLabel("Weight (kg):"), new JLabel("Position:")
        };
        for (JLabel label : labels) {
            label.setForeground(Color.WHITE);
            label.setFont(font);
        }

        // ====== Buttons ======
        JButton addButton = new JButton("➕ Add Player");
        JButton deleteButton = new JButton("🗑️ Delete Selected");
        JButton backButton = new JButton("⬅️ Back");

        addButton.setBackground(new Color(0, 128, 0));
        addButton.setForeground(Color.WHITE);
        addButton.setFont(font);

        deleteButton.setBackground(new Color(128, 0, 0));
        deleteButton.setForeground(Color.WHITE);
        deleteButton.setFont(font);

        backButton.setBackground(new Color(70, 130, 180));
        backButton.setForeground(Color.WHITE);
        backButton.setFont(font);

        addButton.addActionListener(e -> {
            try {
                String name = nameField.getText().trim();
                int age = Integer.parseInt(ageField.getText().trim());
                double weight = Double.parseDouble(weightField.getText().trim());
                String position = positionField.getText().trim();

                if (name.isEmpty() || position.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "All fields are required.");
                    return;
                }

                String info = name + " | Age: " + age + " | Weight: " + weight + "kg | Position: " + position;
                players.add(info);
                playerListModel.addElement(info);

                nameField.setText("");
                ageField.setText("");
                weightField.setText("");
                positionField.setText("");

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Please enter valid numeric values for age and weight.");
            }
        });

        deleteButton.addActionListener(e -> {
            int selectedIndex = playerList.getSelectedIndex();
            if (selectedIndex != -1) {
                playerListModel.remove(selectedIndex);
                players.remove(selectedIndex);
            } else {
                JOptionPane.showMessageDialog(this, "Please select a player to delete.");
            }
        });

        backButton.addActionListener(e -> {
            dispose();    
            new MainMenu().setVisible(true);   
        });

        // ====== Form Panel ======
        JPanel formPanel = new JPanel(new GridLayout(6, 2, 8, 8));
        formPanel.setBackground(new Color(30, 30, 30));
        formPanel.setBorder(BorderFactory.createTitledBorder("Add Player"));

        formPanel.add(labels[0]); formPanel.add(nameField);
        formPanel.add(labels[1]); formPanel.add(ageField);
        formPanel.add(labels[2]); formPanel.add(weightField);
        formPanel.add(labels[3]); formPanel.add(positionField);
        formPanel.add(addButton); formPanel.add(deleteButton);

        // ====== Player List Display ======
        playerList.setBackground(new Color(20, 20, 20));
        playerList.setForeground(Color.CYAN);
        playerList.setFont(new Font("Consolas", Font.PLAIN, 13));
        JScrollPane scrollPane = new JScrollPane(playerList);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Player Records"));

        // ====== Layout ======
        setLayout(new BorderLayout(10, 10));
        add(formPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(backButton, BorderLayout.SOUTH);
    }
}

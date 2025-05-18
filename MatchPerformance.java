import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;

public class MatchPerformance extends JFrame {

    private DefaultListModel<String> performanceListModel = new DefaultListModel<>();
    private JList<String> performanceList = new JList<>(performanceListModel);
    private ArrayList<String> performances = new ArrayList<>();
    private final String FILE_NAME = "performances.txt";

    public MatchPerformance() {
        setTitle("🏟️ Match Performance");
        setSize(600, 550);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

           
        loadPerformancesFromFile();

           
        Color backgroundColor = new Color(30, 30, 30);    
        Color inputColor = new Color(50, 50, 50);        
        Color textColor = new Color(255, 255, 255);      
        Font font = new Font("Segoe UI", Font.PLAIN, 14);

         
        JTextField playerNameField = new JTextField();
        JTextField dateField = new JTextField("YYYY-MM-DD");
        JTextField goalsField = new JTextField();
        JTextField passesField = new JTextField();
        JTextField distanceField = new JTextField();

        JTextField[] inputs = {playerNameField, dateField, goalsField, passesField, distanceField};
        for (JTextField input : inputs) {
            input.setBackground(inputColor);
            input.setForeground(textColor);
            input.setCaretColor(Color.WHITE);
            input.setFont(font);
        }

         
        JButton addButton = new JButton("➕ Add");
        JButton backButton = new JButton("🔙 Back");

        addButton.setBackground(new Color(0, 128, 0));
        backButton.setBackground(new Color(128, 0, 0));
        addButton.setForeground(Color.WHITE);
        backButton.setForeground(Color.WHITE);
        addButton.setFont(font);
        backButton.setFont(font);

         
        addButton.addActionListener(evt -> {
            try {
                String name = playerNameField.getText();
                String date = dateField.getText();
                int goals = Integer.parseInt(goalsField.getText());
                int passes = Integer.parseInt(passesField.getText());
                double distance = Double.parseDouble(distanceField.getText());

                String record = name + " | Date: " + date + " | Goals: " + goals +
                        " | Passes: " + passes + " | Distance: " + distance + " km";

                performances.add(record);
                performanceListModel.addElement(record);
                savePerformanceToFile(record);

                playerNameField.setText("");
                dateField.setText("YYYY-MM-DD");
                goalsField.setText("");
                passesField.setText("");
                distanceField.setText("");

            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Please enter valid data.");
            }
        });

        backButton.addActionListener(evt -> dispose());

          
        JPanel formPanel = new JPanel(new GridLayout(6, 2, 5, 5));
        formPanel.setBorder(BorderFactory.createTitledBorder("Performance Details"));
        formPanel.setBackground(backgroundColor);
        formPanel.setForeground(textColor);

        JLabel[] labels = {
            new JLabel("Player Name:"), new JLabel("Date:"),
            new JLabel("Goals:"), new JLabel("Passes:"),
            new JLabel("Distance (km):")
        };

        for (JLabel label : labels) {
            label.setForeground(Color.WHITE);
            label.setFont(font);
        }

        formPanel.add(labels[0]); formPanel.add(playerNameField);
        formPanel.add(labels[1]); formPanel.add(dateField);
        formPanel.add(labels[2]); formPanel.add(goalsField);
        formPanel.add(labels[3]); formPanel.add(passesField);
        formPanel.add(labels[4]); formPanel.add(distanceField);
        formPanel.add(addButton); formPanel.add(backButton);

        performanceList.setBackground(new Color(20, 20, 20));
        performanceList.setForeground(Color.GREEN);
        performanceList.setFont(new Font("Consolas", Font.PLAIN, 13));

        JScrollPane scrollPane = new JScrollPane(performanceList);

        setLayout(new BorderLayout());
        add(formPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
    }

    private void savePerformanceToFile(String data) {
        try (FileWriter fw = new FileWriter(FILE_NAME, true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {
            out.println(data);
            System.out.println("Saved: " + data);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error saving performance to file: " + e.getMessage());
        }
    }

    private void loadPerformancesFromFile() {
        File file = new File(FILE_NAME);
        if (file.exists()) {
            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = br.readLine()) != null) {
                    performances.add(line);
                    performanceListModel.addElement(line);
                }
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, "Error reading performance file.");
            }
        }
    }

public static void main(String[] args) {
    SwingUtilities.invokeLater(() -> {
        new MatchPerformance().setVisible(true);
    });
}}

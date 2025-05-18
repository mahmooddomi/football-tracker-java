import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;

public class PerformanceReport extends JFrame {
    private final String FILE_NAME = "performances.txt";
    private ArrayList<String> performances = new ArrayList<>();

    public PerformanceReport() {
        setTitle("📈 Performance Report");
        setSize(500, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        getContentPane().setBackground(new Color(245, 245, 245));

        JTextArea reportArea = new JTextArea();
        reportArea.setEditable(false);
        reportArea.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        JScrollPane scrollPane = new JScrollPane(reportArea);

        // Load and analyze data
        loadPerformancesFromFile();
        String report = generateReport();
        reportArea.setText(report);

        setLayout(new BorderLayout());
        add(new JLabel(" Player Performance Summary", SwingConstants.CENTER), BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
    }

    private void loadPerformancesFromFile() {
        File file = new File("performances.txt");
        if (file.exists()) {
            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = br.readLine()) != null) {
                    performances.add(line);
                }
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, "Error reading performance file.");
            }
        }
    }

    private String generateReport() {
        int totalGoals = 0, totalPasses = 0;
        double totalDistance = 0;
        int matchCount = 0;
        String bestMatch = "";
        int maxGoals = -1;

        for (String record : performances) {
            try {
                String[] parts = record.split("\\|");
                int goals = Integer.parseInt(parts[2].trim().split(":")[1].trim());
                int passes = Integer.parseInt(parts[3].trim().split(":")[1].trim());
                double distance = Double.parseDouble(parts[4].trim().split(":")[1].trim().replace("km", "").trim());

                totalGoals += goals;
                totalPasses += passes;
                totalDistance += distance;
                matchCount++;

                if (goals > maxGoals) {
                    maxGoals = goals;
                    bestMatch = record;
                }

            } catch (Exception e) {
                // Skip malformed lines
            }
        }

        if (matchCount == 0) return "No performance data available.";

        return String.format(
            "Total Matches: %d\n\nAverage Goals: %.2f\nAverage Passes: %.2f\nAverage Distance: %.2f km\n\nBest Match:\n%s",
            matchCount,
            (double) totalGoals / matchCount,
            (double) totalPasses / matchCount,
            totalDistance / matchCount,
            bestMatch
        );
    }
}

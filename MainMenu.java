import javax.swing.*;
import java.awt.*;

public class MainMenu extends JFrame {

    public MainMenu() {
        setTitle("🏟️ Football Player Tracker");
        setSize(450, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

             
        getContentPane().setBackground(new Color(240, 240, 240));

        JLabel titleLabel = new JLabel("Main Menu", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        titleLabel.setForeground(new Color(30, 30, 30));

          
        JButton playerBtn = new JButton("👤 Player Management");
        JButton matchBtn = new JButton("📊 Match Performance");
        JButton reportBtn = new JButton("📈 Performance Report");
        JButton exitBtn = new JButton("❌ Exit");

        Font btnFont = new Font("Segoe UI", Font.BOLD, 16);

        playerBtn.setFont(btnFont);
        matchBtn.setFont(btnFont);
        reportBtn.setFont(btnFont);
        exitBtn.setFont(btnFont);

          
        playerBtn.setBackground(new Color(70, 130, 180));   
        playerBtn.setForeground(Color.WHITE);
        playerBtn.setFocusPainted(false);

        matchBtn.setBackground(new Color(60, 179, 113));   
        matchBtn.setForeground(Color.WHITE);
        matchBtn.setFocusPainted(false);

        reportBtn.setBackground(new Color(123, 104, 238)); 
        reportBtn.setForeground(Color.WHITE);
        reportBtn.setFocusPainted(false);

        exitBtn.setBackground(new Color(220, 20, 60));     
        exitBtn.setForeground(Color.WHITE);
        exitBtn.setFocusPainted(false);

          
        playerBtn.addActionListener(e -> new PlayerManagement().setVisible(true));
        matchBtn.addActionListener(e -> new MatchPerformance().setVisible(true));
        reportBtn.addActionListener(e -> new PerformanceReport().setVisible(true));
        exitBtn.addActionListener(e -> System.exit(0));

         
        JPanel buttonPanel = new JPanel(new GridLayout(4, 1, 15, 15));
        buttonPanel.setBackground(new Color(240, 240, 240));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));
        buttonPanel.add(playerBtn);
        buttonPanel.add(matchBtn);
        buttonPanel.add(reportBtn);
        buttonPanel.add(exitBtn);

        setLayout(new BorderLayout());
        add(titleLabel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainMenu().setVisible(true));
    }
}

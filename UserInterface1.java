package JavaProgramming;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

class UIapp extends JFrame {
    private JTextArea textArea;
    
    public UIapp() {
        setTitle("Menu");
        setSize(500, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        
        // Text Area
        textArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(textArea);
        add(scrollPane, BorderLayout.CENTER);
        
        // Menu Bar
        JMenuBar menuBar = new JMenuBar();
        
        // Menu
        JMenu menu = new JMenu("Options");
        
        // Menu Items
        JMenuItem dateTime = new JMenuItem("Print Date & Time");
        JMenuItem writeFile = new JMenuItem("Write Date & Time to File");
        JMenuItem randomColor = new JMenuItem("Random Green Color Hue");
        JMenuItem exitApp = new JMenuItem("Exit");
        
        // Add Menu Items to Menu
        menu.add(dateTime);
        menu.add(writeFile);
        menu.add(randomColor);
        menu.add(exitApp);
        
        // Action Listeners
        dateTime.addActionListener(e -> printDateTime());
        writeFile.addActionListener(e -> writeDateTime());
        randomColor.addActionListener(e -> changeColor(randomColor));
        exitApp.addActionListener(e -> System.exit(0));
        
        // Add Menu to MenuBar and set the MenuBar
        menuBar.add(menu);
        setJMenuBar(menuBar);
    }
    
    private void printDateTime() {
        SimpleDateFormat DTformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        textArea.append("Current Date & Time: " + DTformat.format(date) + "\n");
    }
    
    private void writeDateTime() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("log.txt", true))) {
            writer.write(textArea.getText());
            textArea.append("Contents written to log.txt\n");
        } catch (IOException e) {
            textArea.append("Error writing to file\n");
        }
    }

    private void changeColor(JMenuItem randomGreenItem) {
        // Randomly generate a hue for green (between 90 and 150 degrees)
        Random random = new Random();
        float hue = 90f + random.nextFloat() * 60f; // Hue in the range [90°, 150°]
        
        // Ensure full saturation and brightness for a visible color
        Color randomGreen = Color.getHSBColor(hue / 360, 1f, 0.8f);
        
        // Change background color of the content pane
        getContentPane().setBackground(randomGreen);
        
        // Change the background of the scroll pane to ensure visibility
        textArea.setBackground(randomGreen); // or scrollPane.setBackground(randomGreen);

        // Force the frame to repaint to apply the background change
        getContentPane().repaint();
        
        // Optionally update menu item text with the current hue value
        randomGreenItem.setText(String.format("Random Green Background (Hue: %.2f°)", hue));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            UIapp app = new UIapp();
            app.setVisible(true);
        });
    }
}

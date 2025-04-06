
/*
import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.net.Socket;

public class RemoteDesktopClientUI {

    private static Socket clientSocket;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Remote Desktop Client");
            frame.setSize(1920, 1080);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLocationRelativeTo(null);

            // Background image
            ImageIcon bgImage = new ImageIcon("\"C:\\Users\\Akshida\\eclipse-workspace_akshada\\AESImage\\back.jpg\""); // Replace with your actual image path
            JLabel background = new JLabel(bgImage);
            background.setLayout(new GridBagLayout()); // Center layout
            frame.setContentPane(background);

            // Center panel
            JPanel centerPanel = new JPanel();
            centerPanel.setPreferredSize(new Dimension(500, 350));
            centerPanel.setBackground(new Color(255, 255, 255, 200)); // Semi-transparent white
            centerPanel.setBorder(BorderFactory.createCompoundBorder(
                    new MatteBorder(4, 4, 4, 4, new Color(33, 150, 243)), // Blue matte border
                    BorderFactory.createEmptyBorder(30, 30, 30, 30)
            ));
            centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));

            JLabel titleLabel = new JLabel("Remote Desktop Client");
            titleLabel.setFont(new Font("SansSerif", Font.BOLD, 22));
            titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            titleLabel.setForeground(new Color(33, 37, 41));

            // IP input
            JPanel ipPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
            ipPanel.setOpaque(false);
            JLabel ipLabel = new JLabel("IP Address:");
            ipLabel.setFont(new Font("SansSerif", Font.PLAIN, 16));
            JTextField ipField = new JTextField("127.0.0.1", 12);
            ipField.setFont(new Font("SansSerif", Font.PLAIN, 16));
            ipPanel.add(ipLabel);
            ipPanel.add(ipField);

            // Port input
            JPanel portPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
            portPanel.setOpaque(false);
            JLabel portLabel = new JLabel("Port:");
            portLabel.setFont(new Font("SansSerif", Font.PLAIN, 16));
            JTextField portField = new JTextField("5000", 10);
            portField.setFont(new Font("SansSerif", Font.PLAIN, 16));
            portPanel.add(portLabel);
            portPanel.add(portField);

            // Connect button
            JButton connectButton = new JButton("Connect");
            connectButton.setAlignmentX(Component.CENTER_ALIGNMENT);
            connectButton.setBackground(new Color(33, 150, 243));
            connectButton.setForeground(Color.darkGray);
            connectButton.setFocusPainted(false);
            connectButton.setFont(new Font("SansSerif", Font.BOLD, 16));

            connectButton.addActionListener(e -> {
                String ip = ipField.getText().trim();
                String portText = portField.getText().trim();
                try {
                    int port = Integer.parseInt(portText);
                    clientSocket = new Socket(ip, port);
                    JOptionPane.showMessageDialog(frame, "Connected to server at " + ip + ":" + port);
                    // TODO: Call real client logic after successful connection
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(frame, "Connection failed: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            });

            // Add components to center panel with spacing
            centerPanel.add(titleLabel);
            centerPanel.add(Box.createRigidArea(new Dimension(0, 20)));
            centerPanel.add(ipPanel);
            centerPanel.add(Box.createRigidArea(new Dimension(0, 5))); // Reduced spacing here
            centerPanel.add(portPanel);
            centerPanel.add(Box.createRigidArea(new Dimension(0, 25)));
            centerPanel.add(connectButton);

            background.add(centerPanel);
            frame.setVisible(true);
        });
    }
}



import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.io.File;
import java.net.ServerSocket;

public class RemoteDesktopServerUI {

    private static ServerSocket serverSocket;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Remote Desktop Server");
            frame.setSize(1920, 1080);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLocationRelativeTo(null);

            // Background image
            ImageIcon bgImage = new ImageIcon("server_background.jpg"); // Replace with your image
            JLabel background = new JLabel(bgImage);
            background.setLayout(new GridBagLayout());
            frame.setContentPane(background);

            // Center panel
            JPanel centerPanel = new JPanel();
            centerPanel.setPreferredSize(new Dimension(400, 300));
            centerPanel.setBackground(new Color(255, 255, 255, 200)); // Semi-transparent white
            centerPanel.setBorder(BorderFactory.createCompoundBorder(
                    new MatteBorder(4, 4, 4, 4, new Color(76, 175, 80)), // Green border
                    BorderFactory.createEmptyBorder(30, 30, 30, 30)
            ));
            centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));

            JLabel titleLabel = new JLabel("Remote Desktop Server");
            titleLabel.setFont(new Font("SansSerif", Font.BOLD, 22));
            titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            titleLabel.setForeground(new Color(33, 37, 41));

            // Port field
            JPanel portPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
            portPanel.setOpaque(false);
            JLabel portLabel = new JLabel("Port:");
            portLabel.setFont(new Font("SansSerif", Font.PLAIN, 16));
            JTextField portField = new JTextField("5000", 10);
            portField.setFont(new Font("SansSerif", Font.PLAIN, 16));
            portPanel.add(portLabel);
            portPanel.add(portField);

            // Start button
            JButton startButton = new JButton("Start Server");
            startButton.setAlignmentX(Component.CENTER_ALIGNMENT);
            startButton.setBackground(new Color(76, 175, 80));
            startButton.setForeground(Color.WHITE);
            startButton.setFocusPainted(false);
            startButton.setFont(new Font("SansSerif", Font.BOLD, 16));

            startButton.addActionListener(e -> {
                String portText = portField.getText();
                try {
                    int port = Integer.parseInt(portText);
                    startServer(port);
                    JOptionPane.showMessageDialog(frame, "Server started on port: " + port);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(frame, "Failed to start server: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    ex.printStackTrace();
                }
            });

            // Add components
            centerPanel.add(titleLabel);
            centerPanel.add(Box.createRigidArea(new Dimension(0, 30)));
            centerPanel.add(portPanel);
            centerPanel.add(Box.createRigidArea(new Dimension(0, 30)));
            centerPanel.add(startButton);

            background.add(centerPanel);
            frame.setVisible(true);
        });
    }

    private static void startServer(int port) throws Exception {
        if (serverSocket != null && !serverSocket.isClosed()) {
            serverSocket.close();
        }

        serverSocket = new ServerSocket(port);
        System.out.println("Server is listening on port " + port);

        // You can start your existing thread to handle clients here
        new Thread(() -> {
            while (true) {
                try {
                    var socket = serverSocket.accept();
                    System.out.println("Client connected: " + socket.getInetAddress());
                    // You can replace this with your real handler: new ClientHandler(socket).start();
                } catch (Exception e) {
                    e.printStackTrace();
                    break;
                }
            }
        }).start();
    }
}
*/

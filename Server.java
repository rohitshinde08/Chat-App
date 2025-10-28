import java.net.*;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.*;

class Server extends JFrame {
    ServerSocket server;
    Socket socket;
    BufferedReader br;
    PrintWriter out;

    private JLabel heading = new JLabel("Server Area");
    private JTextArea massageArea = new JTextArea();
    private JTextField massageInput = new JTextField();
    private Font font = new Font("Roboto", Font.PLAIN, 20);

    public Server() {
        try {
            server = new ServerSocket(7777);
            System.out.println("Server is ready to accept connection");
            System.out.println("Waiting....");
            socket = server.accept();
            br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream());
            createGUI();
            handleEvent();
            startReading();
            startWriting();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void handleEvent() {
        massageInput.addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == 10) {
                    String contentToSend = massageInput.getText();
                    massageArea.append("Me : " + contentToSend + "\n");
                    out.println(contentToSend);
                    out.flush();
                    massageInput.setText("");
                }
            }

        });
    }

    private void createGUI() {
        this.setTitle("Server Messager");
        this.setSize(500,700);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        heading.setFont(font);
        massageArea.setFont(font);
        massageInput.setFont(font);
        ImageIcon icon=new ImageIcon("chat.png");
        Image img=icon.getImage();
        Image scaledImage = img.getScaledInstance(40, 40, Image.SCALE_SMOOTH);
        heading.setIcon(new ImageIcon(scaledImage));
        heading.setHorizontalTextPosition(SwingConstants.CENTER);
        heading.setVerticalTextPosition(SwingConstants.BOTTOM);
        heading.setHorizontalAlignment(SwingConstants.CENTER);
        heading.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
        massageArea.setEditable(false);
        massageInput.setHorizontalAlignment(SwingConstants.CENTER);

        this.setLayout(new BorderLayout());
        this.add(heading,BorderLayout.NORTH);
        JScrollPane jScrollPane=new JScrollPane(massageArea);
        this.add(jScrollPane,BorderLayout.CENTER);
        this.add(massageInput,BorderLayout.SOUTH);

        this.setVisible(true);

    }

    public void startReading() {
        Runnable r1 = () -> {
            System.out.println("reader is started....");
            try {
                while (!socket.isClosed()) {
                    String msg = br.readLine();
                    if (msg.equals("exit")) {
                        System.out.println("Client terminated the chat");
                        JOptionPane.showMessageDialog(null, "Client terminated the chat...!!!");
                        massageInput.setEnabled(false);
                        socket.close();
                        break;
                    }
                    // System.out.println("Client : " + msg);
                    massageArea.append("Client : "+msg+"\n");
                }
            } catch (Exception e) {
                // TODO: handle exception
                // e.printStackTrace();
                System.out.println("Connection closed");
            }
        };
        new Thread(r1).start();
    }

    public void startWriting() {
        Runnable r2 = () -> {
            try {
                while (!socket.isClosed()) {

                    BufferedReader br1 = new BufferedReader(new InputStreamReader(System.in));
                    String content = br1.readLine();
                    out.println(content);
                    out.flush();
                    if (content.equals("exit")) {
                        socket.close();
                        break;
                    }
                }
            } catch (Exception e) {
                // TODO: handle exception
                // e.printStackTrace();
                System.out.println("connection closed");
            }
        };
        new Thread(r2).start();
    }

    public static void main(String args[]) {
        System.out.println("this is server");
        new Server();
    }
}
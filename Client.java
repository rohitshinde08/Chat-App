import java.io.*;
import java.net.*;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.font.*;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class Client extends JFrame {


    Socket socket;
    BufferedReader br;
    PrintWriter out;

    private JLabel heading= new JLabel("Client Area");
    private JTextArea massageArea=new JTextArea();
    private JTextField massageInput=new JTextField();
    private Font font=new Font("Roboto",Font.PLAIN, 20);

    public Client(){
        try {
            // System.out.println("Sending request to server.....");
            // socket=new Socket("127.0.0.1",7777);
            // System.out.println("Connection Done...");
            // br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            // out = new PrintWriter(socket.getOutputStream());
            createGUI();
            // startReading();
            // startWriting();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void createGUI(){
        this.setTitle("Client Massager");
        this.setSize(500,700);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        heading.setFont(font);
        massageArea.setFont(font);
        massageInput.setFont(font);
        ImageIcon icon = new ImageIcon("chat.png");
        Image img = icon.getImage();
        Image scaledImg = img.getScaledInstance(40, 40, Image.SCALE_SMOOTH);
        heading.setIcon(new ImageIcon(scaledImg));
        // heading.setIcon(new ImageIcon("chat.png"));
        heading.setHorizontalTextPosition(SwingConstants.CENTER);
        heading.setVerticalTextPosition(SwingConstants.BOTTOM);
        heading.setHorizontalAlignment(SwingConstants.CENTER);
        heading.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));

        this.setLayout(new BorderLayout());

        this.add(heading,BorderLayout.NORTH);
        this.add(massageArea,BorderLayout.CENTER);
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
                        System.out.println("Server terminated the chat");
                        socket.close();
                        break;
                    }
                    System.out.println("Server : "+msg);
                }
            } catch (Exception e) {
                // TODO: handle exception
                // e.printStackTrace();
                System.out.println("Connection closed....");
            }
        };
        new Thread(r1).start();
    }

    public void startWriting() {
        Runnable r2 = () -> {
            try {
                while (!socket.isClosed()) {
                    
                    BufferedReader br1=new BufferedReader(new InputStreamReader(System.in));
                    String content=br1.readLine();
                    out.println(content);
                    out.flush();
                    if (content.equals("exit")){
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
    public static void main(String[] args) {
        System.out.println("This is client");
        new Client();
    }
}

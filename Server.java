import java.net.*;
import java.io.*;

class Server {
    ServerSocket server;
    Socket socket;
    BufferedReader br;
    PrintWriter out;

    public Server() {
        try {
            server = new ServerSocket(7777);
            System.out.println("Server is ready to accept connection");
            System.out.println("Waiting....");
            socket = server.accept();
            br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream());
            startReading();
            startWriting();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void startReading() {
        Runnable r1 = () -> {
            System.out.println("reader is started....");
            try {
                while (!socket.isClosed()) {
                    String msg = br.readLine();
                    if (msg.equals("exit")) {
                        System.out.println("Client terminated the chat");
                        socket.close();
                        break;
                    }
                    System.out.println("Client : "+msg);
                }
            } catch (Exception e) {
                // TODO: handle exception
                e.printStackTrace();
            }
        };
    }

    public void startWriting() {
        Runnable r2 = () -> {
            try {
                while (!socket.isClosed()) {
                    
                    BufferedReader br1=new BufferedReader(new InputStreamReader(System.in));
                    // out.println(content);
                }                
            } catch (Exception e) {
                // TODO: handle exception
                e.printStackTrace();
            }
        };
    }

    public static void main(String args[]) {
        System.out.println("this is server");
        new Server();
    }
}
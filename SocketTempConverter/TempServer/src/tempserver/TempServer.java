/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tempserver;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author laboratorio
 */
public class TempServer {
    private static final int PORT = 12345;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(PORT);
        System.out.println("Server socket created...");
        while (true) {
            System.out.println("Awaiting conections...");
            Socket socket = serverSocket.accept();
            System.out.println("Connected");
            new ServerSocketThread(socket).start();
        }
    }
    
}

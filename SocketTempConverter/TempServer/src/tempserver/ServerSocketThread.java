/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tempserver;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 *
 * @author laboratorio
 */
public class ServerSocketThread extends Thread {
    private final Socket socket;
    
    public ServerSocketThread(Socket socket) {
        this.socket = socket;
    }
    
    @Override
    public void run() {
        super.run();
        try {
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            DataInputStream in = new DataInputStream(socket.getInputStream());
            
            Double inputTemp = in.readDouble();
            Boolean fromRadioButtonReal = in.readBoolean();
            Boolean fromRadioButtonDollar = in.readBoolean();
            Boolean fromRadioButtonEuro = in.readBoolean();
            Boolean toRadioButtonReal = in.readBoolean();
            Boolean toRadioButtonDollar = in.readBoolean();
            Boolean toRadioButtonEuro = in.readBoolean();

            double multiplier = 1.0;

            if (fromRadioButtonReal && toRadioButtonDollar) {
                multiplier = 0.19;
            } else if (fromRadioButtonReal && toRadioButtonEuro) {
                multiplier = 0.19;
            } else if (fromRadioButtonDollar && toRadioButtonReal) {
                multiplier = 5.31;
            } else if (fromRadioButtonDollar && toRadioButtonEuro) {
                multiplier = 0.99;
            } else if (fromRadioButtonEuro && toRadioButtonReal) {
                multiplier = 5.37;
            } else if (fromRadioButtonEuro && toRadioButtonDollar) {
                multiplier = 1.01;
            }

            
            out.writeDouble(inputTemp * multiplier);
            out.flush();
            out.close();
            in.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}

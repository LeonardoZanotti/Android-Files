package br.ufpr.tads.tempclient;

import android.os.AsyncTask;
import android.widget.TextView;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class RequestTask extends AsyncTask<MainActivity.MyParams, Void, Double> {
    private TextView output;
    private String unity;
    private static final String IP = "10.0.2.2";
    private static final int PORT = 12345;

    public RequestTask(TextView output, String unity) {
        this.output = output;
        this.unity = unity;
    }

    @Override
    protected Double doInBackground(MainActivity.MyParams... myParams) {
        Double outputTemp = 0.0;

        try {
            Socket socket = new Socket(IP, PORT);
            DataInputStream input = new DataInputStream(socket.getInputStream());
            DataOutputStream output = new DataOutputStream(socket.getOutputStream());

            output.writeDouble(myParams[0].temp);
            output.writeBoolean(myParams[0].fromRadioButtonReal);
            output.writeBoolean(myParams[0].fromRadioButtonDollar);
            output.writeBoolean(myParams[0].fromRadioButtonEuro);
            output.writeBoolean(myParams[0].toRadioButtonReal);
            output.writeBoolean(myParams[0].toRadioButtonDollar);
            output.writeBoolean(myParams[0].toRadioButtonEuro);
            output.flush();
            outputTemp = input.readDouble();
            output.close();
            input.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return outputTemp;
    }

    @Override
    protected void onPostExecute(Double aDouble) {
        super.onPostExecute(aDouble);
        output.setText("Converted value: " + unity + String.format(" %.2f", aDouble));
    }
}

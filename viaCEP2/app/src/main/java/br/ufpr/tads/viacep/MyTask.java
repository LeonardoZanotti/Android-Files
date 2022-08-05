package br.ufpr.tads.viacep;

import android.os.AsyncTask;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MyTask extends AsyncTask<String, Void, String> {
    private TextView output;

    public MyTask(TextView output) {
        this.output = output;
    }

    @Override
    protected String doInBackground(String... strings) {
        String stringUrl = strings[0];
        InputStream inputStream;
        InputStreamReader inputStreamReader;
        StringBuffer stringBuffer = null;

        try {
            URL url = new URL(stringUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            inputStream = connection.getInputStream();
            inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            stringBuffer = new StringBuffer();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuffer.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return stringBuffer.toString();
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        String logradouro = null;
        String complemento = null;
        String bairro = null;
        String localidade = null;
        String uf = null;
        String cep = null;
        try {
            JSONObject jsonObject = new JSONObject(s);
            logradouro = jsonObject.getString("logradouro");
            complemento = jsonObject.getString("complemento");
            bairro = jsonObject.getString("bairro");
            localidade = jsonObject.getString("localidade");
            uf = jsonObject.getString("uf");
            cep = jsonObject.getString("cep");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        output.setText(String.format("CEP: %s\nUF: %s\nLocalidade: %s\nBairro: %s\nLogradouro: %s\nComplemento: %s", cep, uf, localidade, bairro, logradouro, complemento));
    }
}

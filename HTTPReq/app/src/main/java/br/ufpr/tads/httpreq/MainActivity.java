package br.ufpr.tads.httpreq;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView output;
    private final String BLOCKCHAIN_URL = "https://blockchain.info/ticker";
    private final String BITCOIN_URL = "https://blockchain.info/tobtc";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.output = findViewById(R.id.output);
    }

    public void doRequest(View view) {
        MyTask myTask = new MyTask(output);
        myTask.execute(BLOCKCHAIN_URL);
    }

    public void doRequest2(View view) {
        String currency = "USD";            // get this from the UI
        String value = "500";            // get this from the UI

        String finalURL = BITCOIN_URL + "?currency=" + currency + "&value=" + value;

        MyTask myTask = new MyTask(output);
        myTask.execute(finalURL);
    }
}
package br.ufpr.tads.viacep;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    EditText input;
    TextView output;
    private final String BASE_URL = "https://viacep.com.br/ws/";
    private final String RES_FORMAT = "/json/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.input = findViewById(R.id.input);
        this.output = findViewById(R.id.output);
    }

    public void doRequest(View view) {
        String cep = this.input.getText().toString();
        String finalUrl = BASE_URL + cep + RES_FORMAT;
        MyTask myTask = new MyTask(output);
        myTask.execute(finalUrl);
    }
}
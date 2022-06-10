package br.ufpr.tads.sum;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void sum(View view) {
        EditText input1 = findViewById(R.id.input1);
        EditText input2 = findViewById(R.id.input2);
        TextView output = findViewById(R.id.output);

        if (input1.length() == 0 || input2.length() == 0) {
            Toast.makeText(this, "Insert valid numbers!", Toast.LENGTH_SHORT).show();
            return;
        }

        String inputText1 = input1.getText().toString();
        String inputText2 = input2.getText().toString();

        Double inputDouble1 = Double.parseDouble(inputText1);
        Double inputDouble2 = Double.parseDouble(inputText2);

        double sum = inputDouble1 + inputDouble2;

        output.setText("Sum: " + sum);
    }
}
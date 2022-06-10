package br.ufpr.tads.cambioapp;

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

    public void convert(View view) {
        EditText input = findViewById(R.id.input);
        TextView output = findViewById(R.id.output);
        final double dolarCotation = 5.5;

        if (input.length() == 0) {
            Toast.makeText(this, "Informe um valor válido!", Toast.LENGTH_SHORT).show();
        } else {
            String inputText = input.getText().toString();
            Double inputDouble = Double.parseDouble(inputText);
            Double outputValue = inputDouble * dolarCotation;
            output.setText("$" + outputValue.toString());
        }
    }
}
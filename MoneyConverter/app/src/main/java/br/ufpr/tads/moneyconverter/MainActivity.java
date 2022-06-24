package br.ufpr.tads.moneyconverter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private RadioButton fromRadioButtonReal, fromRadioButtonDollar, fromRadioButtonEuro;
    private RadioButton toRadioButtonReal, toRadioButtonDollar, toRadioButtonEuro;
    private TextView output;
    private EditText input;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        input = findViewById(R.id.input);
        output = findViewById(R.id.output);
        fromRadioButtonReal = findViewById(R.id.fromRadioButtonReal);
        fromRadioButtonDollar = findViewById(R.id.fromRadioButtonDollar);
        fromRadioButtonEuro = findViewById(R.id.fromRadioButtonEuro);
        toRadioButtonReal = findViewById(R.id.toRadioButtonReal);
        toRadioButtonDollar = findViewById(R.id.toRadioButtonDollar);
        toRadioButtonEuro = findViewById(R.id.toRadioButtonEuro);
    }

    public void convert(View view) {
        if (input.length() == 0) {
            Toast.makeText(this, "Insert a valid value!", Toast.LENGTH_SHORT).show();
            return;
        } else if (
                (!fromRadioButtonReal.isChecked() &&
                 !fromRadioButtonDollar.isChecked() &&
                 !fromRadioButtonEuro.isChecked())
                        ||
                (!toRadioButtonReal.isChecked() &&
                 !toRadioButtonDollar.isChecked() &&
                 !toRadioButtonEuro.isChecked())) {
            Toast.makeText(this, "Select the unities of conversion!", Toast.LENGTH_SHORT).show();
            return;
        }

        double multiplier = 1.0, value = Double.parseDouble(input.getText().toString());
        String unity = toRadioButtonReal.isChecked() ? "R$" : toRadioButtonDollar.isChecked() ? "$" : "£";

        if (fromRadioButtonReal.isChecked() && toRadioButtonDollar.isChecked()) {
            multiplier = 5.5;
        } else if (fromRadioButtonReal.isChecked() && toRadioButtonEuro.isChecked()) {
            multiplier = 0.18;
        } else if (fromRadioButtonDollar.isChecked() && toRadioButtonReal.isChecked()) {
            multiplier = 5.24;
        } else if (fromRadioButtonDollar.isChecked() && toRadioButtonEuro.isChecked()) {
            multiplier = 0.95;
        } else if (fromRadioButtonEuro.isChecked() && toRadioButtonReal.isChecked()) {
            multiplier = 5.52;
        } else if (fromRadioButtonEuro.isChecked() && toRadioButtonDollar.isChecked()) {
            multiplier = 1.05;
        }

        output.setText("Converted value: " + unity + String.format(" %.2f", value * multiplier));
    }
}
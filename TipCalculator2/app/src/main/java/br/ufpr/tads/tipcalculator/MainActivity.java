package br.ufpr.tads.tipcalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private SeekBar tipBar;
    private TextView tipPercentage, output;
    private EditText paidAmountInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tipBar = findViewById(R.id.tipBar);
        tipPercentage = findViewById(R.id.tipPercentage);
        output = findViewById(R.id.output);
        paidAmountInput = findViewById(R.id.paidAmount);

        tipBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                tipPercentage.setText(progress + "%");
                if (paidAmountInput.length() != 0) {
                    double paidAmountDouble = Double.parseDouble(paidAmountInput.getText().toString());
                    double finalAmount = paidAmountDouble * (progress + 100)/100;
                    output.setText("Final value: R$ " + String.format("%.2f", finalAmount));
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}
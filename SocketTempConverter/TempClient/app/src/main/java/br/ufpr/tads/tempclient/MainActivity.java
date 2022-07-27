package br.ufpr.tads.tempclient;

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


        Double temp = Double.parseDouble(input.getText().toString());
        String unity = toRadioButtonReal.isChecked() ? "R$" : toRadioButtonDollar.isChecked() ? "$" : "£";

        RequestTask task = new RequestTask(output, unity);
        MyParams myParams = new MyParams(
                temp,
                fromRadioButtonReal.isChecked(),
                fromRadioButtonDollar.isChecked(),
                fromRadioButtonEuro.isChecked(),
                toRadioButtonReal.isChecked(),
                toRadioButtonDollar.isChecked(),
                toRadioButtonEuro.isChecked()
        );
        task.execute(myParams);

    }

    public static class MyParams {
        double temp;
        boolean fromRadioButtonReal, fromRadioButtonDollar, fromRadioButtonEuro, toRadioButtonReal, toRadioButtonDollar, toRadioButtonEuro;

        public MyParams(double temp, boolean romRadioButtonReal, boolean fromRadioButtonDollar, boolean fromRadioButtonEuro, boolean toRadioButtonReal, boolean toRadioButtonDollar, boolean toRadioButtonEuro) {
            this.temp = temp;
            this.fromRadioButtonReal = romRadioButtonReal;
            this.fromRadioButtonDollar = fromRadioButtonDollar;
            this.fromRadioButtonEuro = fromRadioButtonEuro;
            this.toRadioButtonReal = toRadioButtonReal;
            this.toRadioButtonDollar = toRadioButtonDollar;
            this.toRadioButtonEuro = toRadioButtonEuro;
        }
    }
}
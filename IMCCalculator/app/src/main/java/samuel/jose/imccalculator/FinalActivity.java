package samuel.jose.imccalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class FinalActivity extends AppCompatActivity {
    private TextView output;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final);
        output = findViewById(R.id.output);
        Intent it = getIntent();
        Bundle params = it.getExtras();
        if (it != null) {
            String name = params.getString("name");
            double imc = params.getDouble("imc", 0.0);
            String result;

            if (imc < 15) {
                result = "Extremely underweight";
            } else if (imc < 16) {
                result = "Dangerously underweight";
            } else if (imc < 18.5) {
                result = "Underweight";
            } else if (imc < 25) {
                result = "Ideal weight";
            } else if (imc < 30) {
                result = "Overweight";
            } else if (imc < 35) {
                result = "Overweight degree I";
            } else if (imc < 46) {
                result = "Overweight degree II";
            } else {
                result = "Overweight degree III";
            }

            result += String.format(" (%.2f)", imc);

            output.setText("The IMC of " + name + " is " + result);
        }
    }
}
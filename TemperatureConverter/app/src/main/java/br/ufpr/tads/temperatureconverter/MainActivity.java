package br.ufpr.tads.temperatureconverter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void convert(View view) {
        RadioButton celsiusInput = findViewById(R.id.celsiusRadio);
        EditText tempInput = findViewById(R.id.tempInput);
        TextView outputText = findViewById(R.id.outputText);
        String inputString = tempInput.getText().toString();

        if (inputString.length() == 0) {
            Toast.makeText(this, "Insert a valid temperature!", Toast.LENGTH_SHORT).show();
            return;
        }

        Double tempInputDouble = Double.parseDouble(inputString);
        double outputValue = celsiusInput.isChecked() ? tempInputDouble * 9/5 + 32 : (tempInputDouble - 32) * 5/9;
        String unity = celsiusInput.isChecked() ? "°F" : "°C";
        outputText.setText(String.format("%.2f %s", outputValue, unity));
    }
}
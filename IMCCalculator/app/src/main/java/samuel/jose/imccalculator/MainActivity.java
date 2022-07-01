package samuel.jose.imccalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText nameInput, heightInput, weightInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nameInput = findViewById(R.id.nameInput);
        heightInput = findViewById(R.id.heightInput);
        weightInput = findViewById(R.id.weightInput);
    }

    public void calculate(View view) {
        if (heightInput.length() == 0 || weightInput.length() == 0) {
            Toast.makeText(this, "Fill all inputs!", Toast.LENGTH_SHORT).show();
            return;
        }

        double heightDouble = Double.parseDouble(heightInput.getText().toString());
        double weightDouble = Double.parseDouble(weightInput.getText().toString());
        double imc = weightDouble / Math.pow(heightDouble, 2);

        Intent it = new Intent(this, FinalActivity.class);
        Bundle params = new Bundle();
        params.putString("name", nameInput.getText().toString());
        params.putDouble("imc", imc);
        it.putExtras(params);
        startActivity(it);
    }
}


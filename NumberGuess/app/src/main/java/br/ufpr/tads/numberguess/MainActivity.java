package br.ufpr.tads.numberguess;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private int randomNumber, tries;
    private boolean tryAgain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.tries = 0;
        this.tryAgain = false;
        this.randomNumber = new Random().nextInt(100) + 1;
    }

    public void guess(View view) {
        EditText input = findViewById(R.id.input);
        TextView output = findViewById(R.id.output);
        Button btn = findViewById(R.id.guessButton);

        if (this.tryAgain) {
            this.randomNumber = new Random().nextInt(100) + 1;
            btn.setText("Guess");
            this.tries = 0;
            input.setText("");
            this.tryAgain = false;
            output.setText("");
        } else {
            if (input.getText().length() == 0) {
                Toast.makeText(this, "Invalid number!", Toast.LENGTH_SHORT).show();
                return;
            }

            int inputInt = Integer.parseInt(input.getText().toString());
            this.tries++;

            if (inputInt > this.randomNumber)
                output.setText("The number is lower!");
            else if (inputInt < this.randomNumber)
                output.setText("The number is higher!");
            else {
                output.setText("Correct!\nNumber of tries: " + this.tries);
                btn.setText("Try again");
                this.tryAgain = true;
            }
        }
    }
}
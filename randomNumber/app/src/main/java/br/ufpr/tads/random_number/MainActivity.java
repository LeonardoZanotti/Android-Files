package br.ufpr.tads.random_number;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void draw(View view) {
        int randomNumber = new Random().nextInt(10) + 1;
        TextView output = findViewById(R.id.output);
        output.setText(String.valueOf(randomNumber));
    }
}
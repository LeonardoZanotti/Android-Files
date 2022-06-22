package br.ufpr.tads.churrascometro;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.SeekBar;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    SeekBar manBar, womanBar;
    TextView manCount, womanCount, sausageCount, meatCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        manBar = findViewById(R.id.manBar);
        womanBar = findViewById(R.id.womanBar);
        manCount = findViewById(R.id.manCount);
        womanCount = findViewById(R.id.womanCount);
        sausageCount = findViewById(R.id.sausageCount);
        meatCount = findViewById(R.id.meatCount);

        manBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                manCount.setText(String.valueOf(progress));
                calculate(progress, womanBar.getProgress());
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        womanBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                womanCount.setText(String.valueOf(progress));
                calculate(manBar.getProgress(), progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    private void calculate(int manProgress, int womanProgress) {
        double sausageCount, meatCount;
        sausageCount = manProgress * 250 + womanProgress * 150;
        meatCount = manProgress * 450 + womanProgress * 300;
        this.sausageCount.setText(String.valueOf(sausageCount/1000 + "kg"));
        this.meatCount.setText(String.valueOf(meatCount/1000 + "kg"));
    }
}
package br.ufpr.tads.colorapp;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    SeekBar redBar, greenBar, blueBar;
    TextView colorCode, colorView;
    String[] hexColorArray = {"00", "00", "00"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        redBar = findViewById(R.id.redBar);
        greenBar = findViewById(R.id.greenBar);
        blueBar = findViewById(R.id.blueBar);
        colorCode = findViewById(R.id.colorCode);
        colorView = findViewById(R.id.colorView);

        redBar.setOnSeekBarChangeListener(new EventSeek((byte) 0));
        greenBar.setOnSeekBarChangeListener(new EventSeek((byte) 1));
        blueBar.setOnSeekBarChangeListener(new EventSeek((byte) 2));

        setColor("#" + hexColorArray[0] + hexColorArray[1] + hexColorArray[2]);
    }

    private void setColor(String color) {
        colorCode.setText(color);
        colorView.setBackgroundColor(Color.parseColor(color));
    }

    private void setHexNumber(int progress, byte color) {
        String progressText = Integer.toHexString(progress);
        hexColorArray[color] = (progressText.length() == 2 ? "" : "0") + progressText;
        setColor("#" + hexColorArray[0] + hexColorArray[1] + hexColorArray[2]);
    }

    private class EventSeek implements SeekBar.OnSeekBarChangeListener {
        private byte color;

        public EventSeek(byte color) {
            this.color = color;
        }

        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            setHexNumber(progress, color);
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    }
}
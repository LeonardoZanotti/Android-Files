package samuel.jose.tiptablecalc;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private double currentBillTotal;
    private int currentCustomPercent;
    private int currentPeople;
    private EditText tip10EditText;
    private EditText tip15EditText;
    private EditText tip20EditText;
    private EditText total10EditText;
    private EditText total15EditText;
    private EditText total20EditText;
    private TextView customTipTextView;
    private EditText billEditText;
    private EditText tipCustomEditText;
    private EditText totalCustomEditText;
    private TextView byPeopleTextView;
    private EditText byPeopleEdiText;

    private SeekBar.OnSeekBarChangeListener customSeekBarListener = new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            currentCustomPercent = progress;
            updateCustom();
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    };

    private SeekBar.OnSeekBarChangeListener peopleSeekBarListener = new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            currentPeople = progress;
            updatePeople();
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    };

    private TextWatcher billTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            try {
                currentBillTotal = Double.parseDouble(charSequence.toString());
            } catch (NumberFormatException e) {
                currentBillTotal = 0.0;
            }
            updateCustom();
            updateStandard();
            updatePeople();
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tip10EditText = findViewById(R.id.tip10EditTextField);
        tip15EditText = findViewById(R.id.tip15EditTextField);
        tip20EditText = findViewById(R.id.tip20EditTextField);
        total10EditText = findViewById(R.id.total10EditTextField);
        total15EditText = findViewById(R.id.total15EditTextField);
        total20EditText = findViewById(R.id.total20EditTextField);
        customTipTextView = findViewById(R.id.customTipTextView);
        billEditText = findViewById(R.id.billEditText);
        tipCustomEditText = findViewById(R.id.tipCustomEditTextField);
        totalCustomEditText = findViewById(R.id.totalCustomEditTextField);
        byPeopleEdiText = findViewById(R.id.byPeopleEdiTextField);
        byPeopleTextView = findViewById(R.id.byPeopleTextView);
        SeekBar customSeekBar = findViewById(R.id.customSeekBar);
        SeekBar peopleSeekBar = findViewById(R.id.peopleSeekBar);

        currentCustomPercent = customSeekBar.getProgress();
        currentPeople = peopleSeekBar.getProgress();
        billEditText.addTextChangedListener(billTextWatcher);
        customSeekBar.setOnSeekBarChangeListener(customSeekBarListener);
        peopleSeekBar.setOnSeekBarChangeListener(peopleSeekBarListener);
    }

    private void updateStandard() {
        double tenPercentTip = currentBillTotal * .10;
        double tenPercentTotal = currentBillTotal + tenPercentTip;
        double fiftyPercentTip = currentBillTotal * .15;
        double fiftyPercentTotal = currentBillTotal + fiftyPercentTip;
        double twentyPercentTip = currentBillTotal * .2;
        double twentyPercentTotal = currentBillTotal + twentyPercentTip;

        tip10EditText.setText(String.format("%.02f", tenPercentTip));
        total10EditText.setText(String.format("%.02f", tenPercentTotal));
        tip15EditText.setText(String.format("%.02f", fiftyPercentTip));
        total15EditText.setText(String.format("%.02f", fiftyPercentTotal));
        tip20EditText.setText(String.format("%.02f", twentyPercentTip));
        total20EditText.setText(String.format("%.02f", twentyPercentTotal));
    }

    private void updateCustom() {
        customTipTextView.setText(currentCustomPercent + "%");

        double customTipAmount = currentBillTotal * currentCustomPercent * .01;
        double customTotalAmount = currentBillTotal + customTipAmount;

        tipCustomEditText.setText(String.format("%.02f", customTipAmount));
        totalCustomEditText.setText(String.format("%.02f", customTotalAmount));
    }

    private void updatePeople() {
        byPeopleTextView.setText(String.valueOf(currentPeople));
        double totalByPeople = currentBillTotal / currentPeople;
        byPeopleEdiText.setText(String.format("%.02f", totalByPeople));
    }
}
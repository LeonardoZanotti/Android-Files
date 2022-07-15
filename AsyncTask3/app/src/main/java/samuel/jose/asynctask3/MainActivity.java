package samuel.jose.asynctask3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private EditText time;
    private TextView finalResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void eventHandler(View view) {
        time = (EditText) findViewById(R.id.et_time);
        finalResult = (TextView) findViewById(R.id.tv_result);

        SleepTask st = new SleepTask(finalResult);
        String sleepTime = time.getText().toString();
        st.execute(sleepTime);
    }
}
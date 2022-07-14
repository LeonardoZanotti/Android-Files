package samuel.jose.asynctask1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity {
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressBar = findViewById(R.id.progressBar);
    }

    public void startAsyncTask(View view) {
        MyAsyncTask task = new MyAsyncTask(progressBar, MainActivity.this);
        task.execute(5);
    }
}
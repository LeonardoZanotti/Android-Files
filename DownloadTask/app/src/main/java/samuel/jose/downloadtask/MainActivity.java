package samuel.jose.downloadtask;

import androidx.appcompat.app.AppCompatActivity;

import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    private EditText url;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = findViewById(R.id.imageView3);
        url = findViewById(R.id.editTextURL);
        url.setText("https://yt3.ggpht.com/ytc/AKedOLTebXu0t294JLWEG6NcuDfd65cO59gnfH-22Oz4Eg=s900-c-k-c0x00ffffff-no-rj");
    }

    public void eventHandler(View view) {
        DownloadTask task = new DownloadTask(this, imageView);
        task.execute(url.getText().toString());
    }
}
package samuel.jose.asynctask2;

import android.os.AsyncTask;
import android.widget.TextView;

public class CounterTask extends AsyncTask<Integer, Integer, Void> {
    private TextView textView;

    public CounterTask(TextView textView) {
        this.textView = textView;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        this.textView.setText("0");
    }

    @Override
    protected Void doInBackground(Integer... integers) {
        int i = 0;
        while (i < integers[0]) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            i++;
            publishProgress(i);
        }
        return null;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        textView.setText(String.valueOf(values[0]));
    }
}

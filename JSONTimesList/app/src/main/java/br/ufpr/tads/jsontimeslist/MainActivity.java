package br.ufpr.tads.jsontimeslist;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {
    private ProgressDialog progressDialog;
    private ListView listView;
    private static String url = "https://dl.dropboxusercontent.com/s/e85uplwzbsvscq3/times.json";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.listView);
        progressDialog = new ProgressDialog(this);
        new GetTeams(progressDialog, listView, getApplicationContext()).execute(url);
    }
}
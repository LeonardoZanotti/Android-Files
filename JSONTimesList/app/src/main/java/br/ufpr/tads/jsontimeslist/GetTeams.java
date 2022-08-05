package br.ufpr.tads.jsontimeslist;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class GetTeams extends AsyncTask<String, Void, Void> {
    private ProgressDialog progressDialog;
    private Context context;
    private ListView listView;
    ArrayList<HashMap<String, String>> teamList;

    public GetTeams(ProgressDialog progressDialog, ListView listView, Context context) {
        this.progressDialog = progressDialog;
        this.listView = listView;
        this.context = context;
    }

    @Override
    protected Void doInBackground(String... strings) {
        HttpHandler httpHandler = new HttpHandler();
        teamList = new ArrayList<>();

        String jsonString = httpHandler.makeServiceCall(strings[0]);
        if (jsonString != null) {
            try {
                JSONObject jsonObject = new JSONObject(jsonString);
                JSONArray jsonArray = jsonObject.getJSONArray("times");

                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject object = jsonArray.getJSONObject(i);
                    String id = object.getString("id");
                    String name = object.getString("nome");
                    String city = object.getString("cidade");
                    String series = object.getString("serie");
                    HashMap<String, String> team = new HashMap<>();
                    team.put("id", id);
                    team.put("name", name);
                    team.put("city", city);
                    team.put("series", series);
                    teamList.add(team);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressDialog.setMessage("Wait...");
        progressDialog.setCancelable(false);
        progressDialog.show();
    }

    @Override
    protected void onPostExecute(Void unused) {
        super.onPostExecute(unused);
        if (progressDialog.isShowing())
            progressDialog.dismiss();
        ListAdapter listAdapter = new SimpleAdapter(
                context,
                teamList,
                R.layout.list_item,
                new String[]{ "name", "city", "series" },
                new int[]{ R.id.name, R.id.city, R.id.series }
        );
        listView.setAdapter(listAdapter);
    }
}

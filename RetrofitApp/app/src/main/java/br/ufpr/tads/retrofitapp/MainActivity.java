package br.ufpr.tads.retrofitapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import br.ufpr.tads.retrofitapp.apiCEP.RetrofitConfig;
import br.ufpr.tads.retrofitapp.model.Address;
import br.ufpr.tads.retrofitapp.model.Photo;
import br.ufpr.tads.retrofitapp.model.Post;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    EditText input;
    TextView output;
    List<Photo> photoList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.input = findViewById(R.id.input);
        this.output = findViewById(R.id.output);
    }

    public void doRequest(View view) {
        if (this.input.getText().toString().length() == 0) {
            Toast.makeText(this, "Informe um CEP", Toast.LENGTH_SHORT).show();
            return;
        }

        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Consultando endereço...");
        progressDialog.show();

        Call<Address> call = new RetrofitConfig("https://viacep.com.br/ws/").getCEPService().getFullAddress(this.input.getText().toString());
        call.enqueue(new Callback<Address>() {
            @Override
            public void onResponse(Call<Address> call, Response<Address> response) {
                if (response.isSuccessful()) {
                    Address address = response.body();
                    progressDialog.dismiss();
                    output.setText(String.format("CEP: %s\nUF: %s\nLocalidade: %s\nBairro: %s\nLogradouro: %s\nComplemento: %s", address.getCep(), address.getUf(), address.getLocalidade(), address.getBairro(), address.getLogradouro(), address.getComplemento()));
                }
            }

            @Override
            public void onFailure(Call<Address> call, Throwable t) {

            }
        });
    }

    public void getListRetrofit(View view) {
        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Consultando fotos...");
        progressDialog.show();

        Call<List<Photo>> call = new RetrofitConfig("https://jsonplaceholder.typicode.com/").getDataService().getPhotos();
        call.enqueue(new Callback<List<Photo>>() {
            @Override
            public void onResponse(Call<List<Photo>> call, Response<List<Photo>> response) {
                if (response.isSuccessful()) {
                    photoList = response.body();

                    for (int i = 0; i < 50; i++) {
                        Photo photo = photoList.get(i);
                        output.setText(output.getText() + "\n" + photo.getTitle());
                    }

                    progressDialog.dismiss();
                }
            }

            @Override
            public void onFailure(Call<List<Photo>> call, Throwable t) {

            }
        });
    }

    public void savePost(View view) {
        Post post = new Post(1234, 4231, "Post title!", "Body title");

        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Enviando post...");
        progressDialog.show();

//        Call<Post> call = new RetrofitConfig("https://jsonplaceholder.typicode.com/").getDataService().savePost(1234, 4231, "Post title!", "Body title");
        Call<Post> call = new RetrofitConfig("https://jsonplaceholder.typicode.com/").getDataService().savePost(post);
        call.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                if (response.isSuccessful()) {
                    Post postResponse = response.body();
                    output.setText("Código: " + response.code() + "\nId: " + postResponse.getId() + "\nTítulo: " + postResponse.getTitle());
                }
                progressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {

            }
        });
    }

    public void updatePost(View view) {
        Post post = new Post(1234, "Post title updated!", "Body title");

        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Atualizando post...");
        progressDialog.show();

        Call<Post> call = new RetrofitConfig("https://jsonplaceholder.typicode.com/").getDataService().updatePost(2, post);
        call.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                if (response.isSuccessful()) {
                    Post postResponse = response.body();
                    output.setText("Código: " + response.code() + "\nId: " + postResponse.getId() + "\nuserId: " + postResponse.getUserId() + "\nTítulo: " + postResponse.getTitle() + "\nBody: " + postResponse.getBody());
                }
                progressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    public void updatePostWithPatch(View view) {
        Post post = new Post(1234, null, "Body title");

        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Atualizando post...");
        progressDialog.show();

        Call<Post> call = new RetrofitConfig("https://jsonplaceholder.typicode.com/").getDataService().updatePostWithPatch(2, post);
        call.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                if (response.isSuccessful()) {
                    Post postResponse = response.body();
                    output.setText("Código: " + response.code() + "\nId: " + postResponse.getId() + "\nuserId: " + postResponse.getUserId() + "\nTítulo: " + postResponse.getTitle() + "\nBody: " + postResponse.getBody());
                }
                progressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    public void deletePost(View view) {
        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Deletando post...");
        progressDialog.show();

        Call<Void> call = new RetrofitConfig("https://jsonplaceholder.typicode.com/").getDataService().deletePost(2);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    output.setText("Status: " + response.code());
                    progressDialog.dismiss();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });
    }
}
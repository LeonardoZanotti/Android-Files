package br.ufpr.tads.retrofitapp.apiCEP;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitConfig {
    private final Retrofit retrofit;

    public RetrofitConfig(String url) {
        this.retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public CEPService getCEPService() {
        return this.retrofit.create(CEPService.class);
    }

    public DataService getDataService() {
        return this.retrofit.create(DataService.class);
    }
}

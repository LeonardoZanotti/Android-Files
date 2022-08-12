package br.ufpr.tads.retrofitapp.apiCEP;

import br.ufpr.tads.retrofitapp.model.Address;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface CEPService {
    @GET("{cep}/json/")
    Call<Address> getFullAddress(@Path("cep") String cep);
}

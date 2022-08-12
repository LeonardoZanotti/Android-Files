package br.ufpr.tads.retrofitapp.apiCEP;

import java.util.List;

import br.ufpr.tads.retrofitapp.model.Photo;
import br.ufpr.tads.retrofitapp.model.Post;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface DataService {
    @GET("photos")
    Call<List<Photo>> getPhotos();

    @GET("posts")
    Call<Post> getPost();

    @POST("posts")
    Call<Post> savePost(@Body Post post);

    @FormUrlEncoded
    @POST("posts")
    Call<Post> savePost(@Field("userId") int userId,
                        @Field("id") int id,
                        @Field("title") String title,
                        @Field("body") String body);

    @PUT("posts/{id}")
    Call<Post> updatePost(@Path ("id") int id, @Body Post post);

    @PATCH("posts/{id}")
    Call<Post> updatePostWithPatch(@Path ("id") int id, @Body Post post);

    @DELETE("posts/{id}")
    Call<Void> deletePost(@Path ("id") int id);
}

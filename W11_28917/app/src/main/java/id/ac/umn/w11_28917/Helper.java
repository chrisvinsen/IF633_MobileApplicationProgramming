package id.ac.umn.w11_28917;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Helper {
    @GET("posts")
    Call<ArrayList<PostModel>> getPosts();
}

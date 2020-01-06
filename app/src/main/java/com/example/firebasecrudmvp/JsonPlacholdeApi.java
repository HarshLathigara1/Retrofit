package com.example.firebasecrudmvp;


import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;
import rx.Observable;

public interface JsonPlacholdeApi {

    @GET("posts")              // ? userId = 1&sort=id&_order=desc
    Call<List<Post>> getPosts(@Query("userId") int userId,
        @Query("_sort") String sort,
        @Query("_order")String  order

    );

    @GET("posts")
    Observable<Post> getPostsObservable();

    @GET("posts")              // ? userId = 1&sort=id&_order=desc
    Call<List<Post>> getPosts(@Query("userId") Integer[] userId, // new Integer[]
                              @Query("userId") int userId2,
                              @Query("_sort") String sort,
                              @Query("_order")String  order

    );




    // INTEGER wrapper classes are nullable

    @GET("posts")              // ? userId = 1
    Call<List<Post>> getPosts(@Query("userId")int userid);




    @GET("posts")
    Call<List<Post>> getPosts();

    @GET("posts") // end point to get data
    Call<List<Post>> getPosts(@QueryMap Map<String,String>parameters);



    @GET("posts/{id}/comments")
    Call<List<Comment>> getComments(@Path("id") int postId);

    @GET
    Call<List<Comment>> getComments(@Url String url);

    @POST("posts") // end point where you want to set data
    Call<Post>createPost(@Body Post post);

    @FormUrlEncoded
    @POST("posts") // end point where you want to set data
    Call<Post>createPost(@Field("userId") int userId,
                         @Field("title") String title,
                         @Field("body") String text

                         );


}

package com.example.firebasecrudmvp;

import android.app.ProgressDialog;
import android.os.Bundle;

import com.example.firebasecrudmvp.root.App;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.functions.Func1;

public class MainActivity extends AppCompatActivity {
    private TextView textResult;
    @Inject
    private JsonPlacholdeApi jsonPlacholdeApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        ((App)getApplication()).getComponent().inject(this);


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textResult = findViewById(R.id.txtname);



        //getPost();
      // getComment();
        
        createPost();





    }

    private void createPost() {

        Post post = new Post(12,"new Title","new text");

        Call<Post> call  = jsonPlacholdeApi.createPost(post);

        call.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                Post post1 = response.body();
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {

            }
        });
    }

    private void getComment() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
            // retro will bring lige to this one
        jsonPlacholdeApi = retrofit.create(JsonPlacholdeApi.class);

        Call<List<Comment>> call = jsonPlacholdeApi.getComments("posts/3/comments");

        // asynchronous background process
        call.enqueue(new Callback<List<Comment>>() {
            @Override
            public void onResponse(Call<List<Comment>> call, Response<List<Comment>> response) {
                    List<Comment>comments = response.body();
                  for (Comment comment : comments){
                      String content = "";
                      content += "ID:" + comment.getId() + "\n";
                      content += "email:" + comment.getEmail() + "\n";
                      content += "name:" + comment.getName() + "\n";
                      content += "text:" + comment.getText() + "\n";
                      content += "post Id:" + comment.getPostid() + "\n";
                      textResult.append(content);

                  }
            }

            @Override
            public void onFailure(Call<List<Comment>> call, Throwable t) {
                textResult.setText(t.getMessage() );


            }
        });


    }

    private void getPost() {
        // this is we  are sending request to server to give me deta from this base url we are telling
        //retrofit to do our work
        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // then retrofit will do this work for us
        jsonPlacholdeApi = retrofit.create(JsonPlacholdeApi.class);
        // for excecuting our network request we create intance of call form retrofit then it will give us
        // method from interface and wiill fatch data
        Call<List<Post>> call  = jsonPlacholdeApi.getPosts(1);

        // this is asuncronous task so we hace to do it on background so currently we are in main thread so it
        // will generate exception
        // retrofit provides method for bacground work
        // it will give you two methods shown below
        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                // if it will be sucess the you will get response from your server

                List<Post>posts = response.body();
                for (Post post:posts){
                    String content = "";
                    content += "ID:" + post.getId() + "\n";
                    content += "userId:" + post.getUserId() + "\n";
                    content += "title:" + post.getTitle() + "\n";
                    content += "text:" + post.getText() + "\n";
                    textResult.append(content);
                }
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                // and if erroe occurs you can catch erroe from here
                // Throwable is supper class of error and message
                textResult.setText(t.getMessage() );

            }
        });

    }

   /* private void getPostsObservableWay(){
      jsonPlacholdeApi.getPostsObservable().flatMap(new Func1<Post, Observable<Post>>() {
          @Override
          public Observable<Post> call(Post post) {
              return post.getText();
          }
      })

    }*/


}

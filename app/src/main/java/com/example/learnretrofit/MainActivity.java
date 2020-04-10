package com.example.learnretrofit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private ListView listView;
    private Button search;
    private TextView user;
    private EditText search_text;
    private RetrofitClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        search=findViewById (R.id.search);
        user=findViewById (R.id.github_user);
        search_text=findViewById (R.id.edit_search);
        listView= findViewById(R.id.listView);

        Retrofit.Builder builder =new Retrofit.Builder()
                //this is the base URL. URL we created in interface will be added to it
                .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit = builder.build();

        client = retrofit.create(RetrofitClient.class);

        search.setOnClickListener (v -> {
            String userName=search_text.getText ().toString ();
            if(userName.equals ("")){
                Toast.makeText (MainActivity.this, "please input a text", Toast.LENGTH_SHORT).show ();
                return;
            }
            showRepos(userName);
            user.setText (userName);
        });

    }

    private void showRepos(String searchText) {
        Call <List<GithubRepo>>call =client.reposForUser(searchText);

        call.enqueue(new Callback<List<GithubRepo>>() {
            @Override
            public void onResponse(Call<List<GithubRepo>> call, Response<List<GithubRepo>> response) {
                try {
                    if(response.isSuccessful ()){
                        List<GithubRepo>repos= response.body();
                        listView.setAdapter(new GitHubRepoAdapter(MainActivity.this,repos));
                    }
                } catch (Exception e) {
                    Toast.makeText (MainActivity.this, "invalid user", Toast.LENGTH_SHORT).show ();
                    e.printStackTrace ();
                }

            }

            @Override
            public void onFailure(Call<List<GithubRepo>> call, Throwable t) {
                Toast.makeText(MainActivity.this,"Something wrong",Toast.LENGTH_SHORT).show();
            }
        });
    }
}

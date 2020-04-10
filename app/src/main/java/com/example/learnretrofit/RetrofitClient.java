package com.example.learnretrofit;


import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface RetrofitClient {

    @GET("users/{user}/repos")
        //List<GithubRepo> is return type    @Path("user")String user is the parameter we will pass
    Call<List<GithubRepo>> reposForUser(@Path("user")String user);


}

package com.example.day09.Api;

import com.example.day09.Model.Login.Login;
import com.example.day09.Model.Register.Register;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface Apiinterface {
    @FormUrlEncoded
    @POST("login.php")
    Call<Login> loginResponse(
      @Field ("username") String username,
      @Field ("password") String password

    );

    @FormUrlEncoded
    @POST("register.php")
    Call<Register> registerResponse(
            @Field ("username") String username,
            @Field ("password") String password,
            @Field ("name") String name

    );
}

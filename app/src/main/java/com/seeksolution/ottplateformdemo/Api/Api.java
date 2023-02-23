package com.seeksolution.ottplateformdemo.Api;

import com.seeksolution.ottplateformdemo.Model.CreateUserResponse;
import com.seeksolution.ottplateformdemo.Model.PackageResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface Api {

    @FormUrlEncoded
    @POST("user")
    Call<CreateUserResponse> createUser(
            @Field("name") String name,
            @Field("email") String email,
            @Field("password") String password,
            @Field("mobile") String mobile
    );

    @GET("package")
    Call<PackageResponse> getSubscriptionPackages();




}

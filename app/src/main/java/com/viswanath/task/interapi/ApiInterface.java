package com.viswanath.task.interapi;

import com.viswanath.task.model.GetLang;
import com.viswanath.task.model.GetLog;
import com.viswanath.task.utility.Constant;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiInterface {

    @FormUrlEncoded
    @POST("")
    Call<GetLog> getlog(
            @Field("mobile") String mobile,
            @Field("password") String password
    );

    @GET(Constant.ApiParams.LANG)
    Call<GetLang> getlanguages();
}

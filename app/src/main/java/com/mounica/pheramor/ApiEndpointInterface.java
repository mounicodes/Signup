package com.mounica.pheramor;

import com.mounica.pheramor.models.PhermoreResponse;
import com.mounica.pheramor.models.User;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiEndpointInterface {

    @POST(" ")
    Call<PhermoreResponse> postUser(@Body User user);
}

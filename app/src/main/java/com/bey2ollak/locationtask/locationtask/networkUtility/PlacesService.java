package com.bey2ollak.locationtask.locationtask.networkUtility;

import com.bey2ollak.locationtask.locationtask.models.Bey2ollakPlacesResult;
import com.bey2ollak.locationtask.locationtask.utilities.Constants;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * @author Bassem Mohsen : basem3312@gmail.com on 9/6/2017.
 */

public interface PlacesService {

    @GET("json/places")
    Call<Bey2ollakPlacesResult> getPlaces(
            @Query("page") int page,
            @Query("size") int size);


    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(Constants.API_BASE_DOMAIN)
            .addConverterFactory(GsonConverterFactory.create())
            .build();
}

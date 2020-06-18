package com.khattab.softxperttask.common.aPIsUtils;


import com.khattab.softxperttask.cars.pojo.Cars;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface TaskClientWebService {

    @GET("cars")
    Call<Cars> getCars(@Query("page") String page);


}
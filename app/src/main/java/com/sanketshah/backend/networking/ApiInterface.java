package com.sanketshah.backend.networking;


import com.sanketshah.data.NewsFeedWrapper;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Created by sanketshah on 04-01-2018.
 */

public interface ApiInterface {
    @GET("facts.json")
    Observable<NewsFeedWrapper> getFacts();
}

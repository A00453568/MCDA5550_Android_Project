package com.example.hotel_reservation_system_assignment;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;



public class Api {

    public static ApiInterface getClient() {
        // change your base URL
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.115.175:8000/") //Set the Root URL
                .addConverterFactory(GsonConverterFactory.create())
                .build(); //Finally building the adapter

        //Creating object for our interface
        ApiInterface api = retrofit.create(ApiInterface.class);
        return api; // return the APIInterface object
    }

}
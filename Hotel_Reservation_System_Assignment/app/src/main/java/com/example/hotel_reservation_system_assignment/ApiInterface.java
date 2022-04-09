package com.example.hotel_reservation_system_assignment;

import com.google.gson.JsonObject;

import java.util.HashMap;
import java.util.List;

import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;


public interface ApiInterface {

    String API_KEY = BuildConfig.API_KEY;

    @GET("/hotel_list_unrestricted")
    Call<HotelListDataWrapper> getHotelsUnrestrictedLists();

    @Headers({"Accept: application/json","API-KEY:"+API_KEY})
    @GET("/hotel_list")
    Call<HotelListDataWrapper> getHotelsLists();


    @Headers({"Accept: application/json","API-KEY:"+API_KEY})
    @POST("/reserve_hotel/")
    Call<ReservationConfirmation> getReservation(@Body Reservation reservation);




}



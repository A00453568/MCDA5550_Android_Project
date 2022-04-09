package com.example.hotel_reservation_system_assignment;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class HotelListDataWrapper {

    public List<HotelListData> getHotels_list() {
        return hotels_list;
    }

    public void setHotels_list(List<HotelListData> hotels_list) {
        this.hotels_list = hotels_list;
    }

    @SerializedName("hotels_list")
    @Expose
    private List<HotelListData> hotels_list = null;


}
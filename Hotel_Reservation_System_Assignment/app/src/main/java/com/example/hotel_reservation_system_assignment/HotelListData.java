package com.example.hotel_reservation_system_assignment;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class HotelListData {

    @SerializedName("hotel_name")
    @Expose
    String hotel_name;
    @SerializedName("price")
    @Expose
    String price;
    @SerializedName("availability")
    @Expose
    String availability;

    public HotelListData(String hotel_name, String price, String availability) {
        this.hotel_name = hotel_name;
        this.price = price;
        this.availability = availability;
    }

    public String getHotel_name() {
        return hotel_name;
    }

    public void setHotel_name(String hotel_name) {
        this.hotel_name = hotel_name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getAvailability() {
        return availability;
    }

    public void setAvailability(String availability) {
        this.availability = availability;
    }
}

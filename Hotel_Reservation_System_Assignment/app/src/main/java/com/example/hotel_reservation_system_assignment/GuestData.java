package com.example.hotel_reservation_system_assignment;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;

@Generated("jsonschema2pojo")
public class GuestData {
    @SerializedName("guest_name")
    @Expose
    private String guest_name;

    @SerializedName("gender")
    @Expose
    private String gender;

    public GuestData(String guest_name, String gender) {
        this.guest_name = guest_name;
        this.gender = gender;
    }

    public GuestData() {
        this.guest_name = "";
        this.gender = "";
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getGuest_name() {
        return guest_name;
    }

    public void setGuest_name(String guest_name) {
        this.guest_name = guest_name;
    }
}

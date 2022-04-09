package com.example.hotel_reservation_system_assignment;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import javax.annotation.Generated;


public class Reservation2 {


    private String hotel_name;
    private String checkin;
    private String checkout;
    private List<GuestData> guests_list = null;


    public String getHotel_name() {
        return hotel_name;
    }

    public void setHotel_name(String hotel_name) {
        this.hotel_name = hotel_name;
    }

    public String getCheckin() {
        return checkin;
    }

    public void setCheckin(String checkin) {
        this.checkin = checkin;
    }

    public String getCheckout() {
        return checkout;
    }

    public void setCheckout(String checkout) {
        this.checkout = checkout;
    }

    public List<GuestData> getGuests_list() {
        return guests_list;
    }

    public void setGuests_list(List<GuestData> guests_list) {
        this.guests_list = guests_list;
    }

}

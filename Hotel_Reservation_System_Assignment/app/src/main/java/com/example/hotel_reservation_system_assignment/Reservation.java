package com.example.hotel_reservation_system_assignment;

import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("jsonschema2pojo")
public class Reservation {

    @SerializedName("hotel_name")
    @Expose
    private String hotelName;

    @SerializedName("checkin")
    @Expose
    private String checkin;

    @SerializedName("checkout")
    @Expose
    private String checkout;

    @SerializedName("guests_list")
    @Expose
    private List<GuestData> guestsList = null;


    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
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

    public List<GuestData> getGuestsList() {
        return guestsList;
    }

    public void setGuestsList(List<GuestData> guestsList) {
        this.guestsList = guestsList;
    }

}

package com.example.hotel_reservation_system_assignment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.POST;

public class GuestDetailsFragment extends Fragment { //implements ItemClickListener

    TextView hotel_name_value_text_view, checkin_value_text_view, checkout_value_text_view,
            price_value_text_view;
    Button submitButton;
    View view;
    List<GuestData> guestDataList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.hotel_guest_details_fragment, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        hotel_name_value_text_view = view.findViewById(R.id.hotel_name_value_text_view);
        checkin_value_text_view = view.findViewById(R.id.checkin_value_text_view);
        checkout_value_text_view = view.findViewById(R.id.checkout_value_text_view);
        price_value_text_view = view.findViewById(R.id.price_value_text_view);

        submitButton = view.findViewById(R.id.guest_reservation_button);

        assert getArguments() != null;
        String hotelName = getArguments().getString("hotel_name");
        String hotelPrice = getArguments().getString("hotel_price");
        String checkInDate = getArguments().getString("check_in_date");
        String checkOutDate = getArguments().getString("check_out_date");
        String numberOfGuests = getArguments().getString("number_of_guests");

        hotel_name_value_text_view.setText(hotelName);
        checkin_value_text_view.setText(checkInDate);
        checkout_value_text_view.setText(checkOutDate);
        price_value_text_view.setText(hotelPrice);

        getHotelsListsData(numberOfGuests);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Reservation reservation = new Reservation();
                reservation.setHotelName(hotelName);
                reservation.setCheckin(checkInDate);
                reservation.setCheckout(checkOutDate);
                reservation.setGuestsList(guestDataList);

//                Reservation2 reservation2 = new Reservation2();
//                reservation2.setHotel_name(hotelName);
//                reservation2.setCheckin(checkInDate);
//                reservation2.setCheckout(checkOutDate);
//                reservation2.setGuests_list(guestDataList);


                Call<POST> call = Api.getClient().getReservation(guestDataList,
                        hotelName, checkInDate, checkOutDate);
                call.enqueue(new Callback<POST>() {
                    @Override
                    public void onResponse(@NonNull Call<POST> call, @NonNull Response<POST> response) {

                        Log.i("hey2:", String.valueOf(response));
                        if(response != null) {

                            Log.i("hey2:", "yippie");
                        }
                        else{
                            Toast.makeText(getActivity(), "Could Not Complete The Reservation", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<POST> call, Throwable t) {
                        Toast.makeText(getActivity(), t.toString(), Toast.LENGTH_SHORT).show();
                        call.cancel();
                    }
                });

//                Call<ReservationConfirmation> call = Api.getClient().getReservation(reservation);
//                call.enqueue(new Callback<ReservationConfirmation>() {
//                    @Override
//                    public void onResponse(@NonNull Call<ReservationConfirmation> call, @NonNull Response<ReservationConfirmation> response) {
//
//                        if(response.body() != null) {
//
//                            Log.i("hey2:", String.valueOf(response.body()));
//                        }
//                        else{
//                            Toast.makeText(getActivity(), "Could Not Complete The Reservation", Toast.LENGTH_SHORT).show();
//                        }
//                    }
//
//                    @Override
//                    public void onFailure(@NonNull Call<ReservationConfirmation> call, Throwable t) {
//                        Toast.makeText(getActivity(), t.toString(), Toast.LENGTH_SHORT).show();
//                        call.cancel();
//                    }
//                });
//                Call<Reservation> call = Api.getClient().getReservation(reservation);
//                call.enqueue(new Callback<Reservation>() {
//                    @Override
//                    public void onResponse(@NonNull Call<Reservation> call, @NonNull Response<Reservation> response) {
//                        Reservation reservationResponse = response.body();
//
//                        if(reservationResponse != null) {
//                            String confirmationNumber = reservationResponse.getconfirmationNumber();
//                            Log.i("hey:","yippie");
//                            assert savedInstanceState != null;
//                            savedInstanceState.putString("confirmation_number", confirmationNumber);
//                            Log.i("hey2:","yippie2");
//                        }
//                        else{
//                            Toast.makeText(getActivity(), "Could Not Complete The Reservation", Toast.LENGTH_SHORT).show();
//                        }
//                    }
//
//                    @Override
//                    public void onFailure(@NonNull Call<Reservation> call, Throwable t) {
//                        Toast.makeText(getActivity(), t.toString(), Toast.LENGTH_SHORT).show();
//                        call.cancel();
//                    }
//                });

//                ReservationConfirmationFragment reservationConfirmationFragment = new ReservationConfirmationFragment();
//                reservationConfirmationFragment.setArguments(savedInstanceState);
//
//                FragmentTransaction fragmentTransaction = getParentFragmentManager().beginTransaction();
//                fragmentTransaction.remove(GuestDetailsFragment.this);
//                fragmentTransaction.replace(R.id.main_layout, reservationConfirmationFragment);
//                fragmentTransaction.addToBackStack(null);
//                fragmentTransaction.commitAllowingStateLoss();
            }
        });
    }

    public ArrayList<GuestData> getGuestData(int numberOfGuests) {
        ArrayList<GuestData> list = new ArrayList<GuestData>(numberOfGuests);

        for(int i=0 ; i<numberOfGuests ; i++)
        {
            list.add(new GuestData());
        }
        return list;

    }

    private void getHotelsListsData(String numberOfGuests) {
        guestDataList = getGuestData(Integer.parseInt(numberOfGuests));
        // Set up the RecyclerView
        setupRecyclerView();
    }

    private void setupRecyclerView() {

        RecyclerView recyclerView = view.findViewById(R.id.guest_details_recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        GuestListAdapter guestListAdapter = new GuestListAdapter(getActivity(), guestDataList);
        recyclerView.setAdapter(guestListAdapter);
    }

}

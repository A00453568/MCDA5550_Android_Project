package com.example.hotel_reservation_system_assignment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


public class ReservationConfirmationFragment extends Fragment {

    TextView reservationTextView;
    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.reservation_confirmation_fragment, container, false);
        return view;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);

        reservationTextView = view.findViewById(R.id.reservation_text_view);

        assert getArguments() != null;
        String confirmationNumber = getArguments().getString("confirmation_number");


        String reservationText = "Thank you for your reservation, your reservation number is "+confirmationNumber;
        reservationTextView.setText(reservationText);

    }

}

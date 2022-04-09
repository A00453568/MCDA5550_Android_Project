package com.example.hotel_reservation_system_assignment;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class HotelSearchFragment extends Fragment {

    View view;
    ConstraintLayout mainLayout;
    TextView titleTextView, searchTextConfirmationTextView;
    EditText guestsCountEditText, nameEditText;
    Button confirmSearchButton, searchButton, retrieveButton, clearButton;
    DatePicker checkInDatePicker, checkOutDatePicker;
    String checkInDate, checkOutDate, numberOfGuests, guestName;

    // Declaration of shared preferences keys
    SharedPreferences sharedPreferences;
    public static final String myPreference = "myPref";
    public static final String name = "nameKey";
    public static final String guestsCount = "guestsCount";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        //super.onCreateView(inflater, container, savedInstanceState);
        view = inflater.inflate(R.layout.hotel_search_layout,container,false);
        return view;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);

        mainLayout = view.findViewById(R.id.main_layout);
        titleTextView = view.findViewById(R.id.title_text_view);
        searchTextConfirmationTextView = view.findViewById(R.id.search_confirm_text_view);

        guestsCountEditText = view.findViewById(R.id.guests_count_edit_text_view);
        nameEditText = view.findViewById(R.id.guest_name_edit_text_view);

        confirmSearchButton = view.findViewById(R.id.confirm_my_search_button);
        searchButton = view.findViewById(R.id.search_button);
        retrieveButton = view.findViewById(R.id.retrieve_button);
        clearButton = view.findViewById(R.id.clear_button);

        checkInDatePicker = view.findViewById(R.id.checkin_datepicker_view);
        checkOutDatePicker = view.findViewById(R.id.checkout_datepicker_view);

        //setup a title text
        titleTextView.setText(R.string.welcome_text);

        confirmSearchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            checkInDate = getDateFromCalendar(checkInDatePicker);
            checkOutDate = getDateFromCalendar(checkOutDatePicker);
            numberOfGuests = guestsCountEditText.getText().toString();
            guestName = nameEditText.getText().toString();

            // Saving into shared preferences
            sharedPreferences = requireActivity().getSharedPreferences(myPreference,
                    Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString(name, guestName);
            editor.putString(guestsCount, numberOfGuests);
            editor.apply();

            String searchConfirmText = "Dear "+guestName+ ", Your check in date is "+checkInDate+", " +
                    "your checkout date is "+checkOutDate+". The number of guests are "
                    +numberOfGuests;
            searchTextConfirmationTextView.setText( searchConfirmText);
            }
        });

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkInDate = getDateFromCalendar(checkInDatePicker);
                checkOutDate = getDateFromCalendar(checkOutDatePicker);
                numberOfGuests = guestsCountEditText.getText().toString();
                guestName = nameEditText.getText().toString();

                Bundle bundle = new Bundle();
                bundle.putString("check_in_date", checkInDate);
                bundle.putString("check_out_date", checkOutDate);
                bundle.putString("number_of_guests", numberOfGuests);
                bundle.putString("guest_name", guestName);

                HotelListFragment hotelListFragment = new HotelListFragment();
                hotelListFragment.setArguments(bundle);

                //FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                FragmentTransaction fragmentTransaction = getParentFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.main_layout, hotelListFragment);
                fragmentTransaction.remove(HotelSearchFragment.this);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

        retrieveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sharedPreferences = requireActivity().getSharedPreferences(myPreference,
                        Context.MODE_PRIVATE);

                if (sharedPreferences.contains(name)) {
                    nameEditText.setText(sharedPreferences.getString(name, ""));
                }
                if (sharedPreferences.contains(guestsCount)) {
                    guestsCountEditText.setText(sharedPreferences.getString(guestsCount, ""));

                }
            }
        });
                clearButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        guestsCountEditText.setText("");
                        nameEditText.setText("");
                    }
                });
    }

    private String getDateFromCalendar(DatePicker datePicker){
        int day = datePicker.getDayOfMonth();
        int month = datePicker.getMonth();
        int year = datePicker.getYear();

        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

        return simpleDateFormat.format(calendar.getTime());
    }

}

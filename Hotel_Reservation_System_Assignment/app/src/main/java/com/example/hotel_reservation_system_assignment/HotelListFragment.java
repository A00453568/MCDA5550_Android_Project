package com.example.hotel_reservation_system_assignment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HotelListFragment extends Fragment  implements ItemClickListener{

    TextView headingTextView;
    List<HotelListData> hotelListResponseData;
    ProgressBar progressBar;
    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.hotel_list_fragment, container, false);
        return view;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);

        headingTextView = view.findViewById(R.id.heading_text_view);
        progressBar = view.findViewById(R.id.progress_bar);

        assert getArguments() != null;
        String checkInDate = getArguments().getString("check_in_date");
        String checkOutDate = getArguments().getString("check_out_date");
        String numberOfGuests = getArguments().getString("number_of_guests");
        String guestName = getArguments().getString("guest_name");

        String headingText = "Welcome "+guestName+", displaying hotels for "+ numberOfGuests+
                " guests staying from "+checkInDate+" to "+checkOutDate;
        headingTextView.setText(headingText);

        getHotelsListsData();
    }


    private void getHotelsListsData() {
        progressBar.setVisibility(View.VISIBLE);
        Call<HotelListDataWrapper> call = Api.getClient().getHotelsLists();
        call.enqueue(new Callback<HotelListDataWrapper>() {
            @Override
            public void onResponse(Call<HotelListDataWrapper> call, Response<HotelListDataWrapper> response) {
                HotelListDataWrapper hotelListWrapper = response.body();
                hotelListResponseData = hotelListWrapper.getHotels_list();
                progressBar.setVisibility(View.GONE);
                // Set up the RecyclerView
                setupRecyclerView();
            }

            @Override
            public void onFailure(Call<HotelListDataWrapper> call, Throwable t) {
                Toast.makeText(getActivity(), t.toString(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void setupRecyclerView() {

        RecyclerView recyclerView = view.findViewById(R.id.hotel_list_recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        HotelListAdapter hotelListAdapter = new HotelListAdapter(getActivity(), hotelListResponseData);
        recyclerView.setAdapter(hotelListAdapter);
        hotelListAdapter.setClickListener(this);

    }

    @Override
    public void onClick(View view, int position) {
        HotelListData hotelListData = hotelListResponseData.get(position);

        String hotelName = hotelListData.getHotel_name();
        String price = hotelListData.getPrice();
        String availability = hotelListData.getAvailability();

        if(availability.equals("false")) {
            Toast.makeText(getActivity(), "The Chosen hotel is currently unavailable",
                    Toast.LENGTH_SHORT).show();
        }
        else {
            Bundle bundle = new Bundle();
            bundle.putString("hotel_name", hotelName);
            bundle.putString("hotel_price", price);
            bundle.putString("hotel_availability", availability);
            bundle.putString("check_in_date", getArguments().getString("check_in_date"));
            bundle.putString("check_out_date", getArguments().getString("check_out_date"));
            bundle.putString("number_of_guests", getArguments().getString("number_of_guests"));

            GuestDetailsFragment guestDetailsFragment = new GuestDetailsFragment();
            guestDetailsFragment.setArguments(bundle);

            FragmentTransaction fragmentTransaction = getParentFragmentManager().beginTransaction();
            fragmentTransaction.remove(HotelListFragment.this);
            fragmentTransaction.replace(R.id.main_layout, guestDetailsFragment);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commitAllowingStateLoss();
        }
    }
}

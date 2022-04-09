package com.example.hotel_reservation_system_assignment;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;


public class GuestListAdapter extends RecyclerView.Adapter<GuestListAdapter.ViewHolder> {

    private List<GuestData> guestDataList;
    private LayoutInflater layoutInflater;
    //private ItemClickListener clickListener;

    //Data gets passed in the constructor
    GuestListAdapter(Context context, List<GuestData> guestDataList) {
        this.layoutInflater = LayoutInflater.from(context);
        this.guestDataList = guestDataList;
    }

//    public void setClickListener(ItemClickListener itemClickListener) {
//        this.clickListener = itemClickListener;
//    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.hotel_guest_details_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
    GuestData guest = guestDataList.get(position);
    String guestName = guest.getGuest_name();
    String guestGender = guest.getGender();

    holder.guest_name_text_view.setText("Guest " + Integer.toString(position+1));
    //holder.guest_name_edit_text_view.setActivated(true);
    //holder.guest_name_text_view.setActivated(true);
    //holder.gender_radio_group.setActivated(true);
    //holder.gender_radio_group.getChildAt(0).setActivated(true); //setChecked(true);
    }

    @Override
    public int getItemCount() {
        if (guestDataList != null) {
            return guestDataList.size();
        }
        else {
            return 0;
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView guest_name_text_view, guest_gender_text_view;
        RadioButton male_radiobutton, female_radiobutton, other_radiobutton;
        RadioGroup gender_radio_group;
        EditText guest_name_edit_text_view;
        Button button;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            guest_name_text_view = itemView.findViewById(R.id.guest_name_text_view);
            guest_name_edit_text_view = itemView.findViewById(R.id.guest_name_edit_text_view);
            guest_gender_text_view = itemView.findViewById(R.id.guest_gender_text_view);

            //male_radiobutton = itemView.findViewById(R.id.male_radiobutton);
            //female_radiobutton = itemView.findViewById(R.id.female_radiobutton);
            //other_radiobutton = itemView.findViewById(R.id.other_radiobutton);
            gender_radio_group = itemView.findViewById(R.id.gender_radio_group);



            gender_radio_group.setOnCheckedChangeListener((radioGroup, checkedID) -> {
                RadioButton radioButton = (RadioButton) itemView.findViewById(checkedID);
                saveRadioButtonChecks(getAbsoluteAdapterPosition(),
                        radioButton.getText().toString(),
                        guest_name_edit_text_view.getText().toString());
            });

            guest_name_edit_text_view.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void afterTextChanged(Editable editable) {
                    int position = getAbsoluteAdapterPosition();
                    GuestData positionalGuestData = guestDataList.get(position);
                    positionalGuestData.setGuest_name(editable.toString());
                    guestDataList.set(position, positionalGuestData);
                }
            });

        }
    }
    private void saveRadioButtonChecks(int position, String checked_radio_button_value, String guest_name){

        Log.i("old data: ",guestDataList.get(position).getGuest_name());
        Log.i("old gender data: ",guestDataList.get(position).getGender());

        guestDataList.get(position).setGender(checked_radio_button_value);
        //guestDataList.get(position).setGuest_name(guest_name);

        Log.i("new data: ",guestDataList.get(position).getGuest_name());
        Log.i("new gender data: ",guestDataList.get(position).getGender());
    }

}
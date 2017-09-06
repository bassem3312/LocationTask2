package com.bey2ollak.locationtask.locationtask.adapters.ViewHolders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.bey2ollak.locationtask.locationtask.R;

/**
 * @author Bassem Mohsen : basem3312@gmail.com on 9/6/2017.
 */

public class Bey2ollakPlaceViewHolder extends RecyclerView.ViewHolder {
    public TextView tvPlaceName;
    public TextView tvPlaceAddress;

    public Bey2ollakPlaceViewHolder(View v) {
        super(v);
        tvPlaceName = (TextView) v.findViewById(R.id.tv_place_name);
        tvPlaceAddress = (TextView) v.findViewById(R.id.tv_place_address);
    }
}

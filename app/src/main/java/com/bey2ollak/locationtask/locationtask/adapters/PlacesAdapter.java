package com.bey2ollak.locationtask.locationtask.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bey2ollak.locationtask.locationtask.R;
import com.bey2ollak.locationtask.locationtask.adapters.ViewHolders.Bey2ollakPlaceViewHolder;
import com.bey2ollak.locationtask.locationtask.models.Content;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Bassem Mohsen : basem3312@gmail.com on 9/6/2017.
 */

public class PlacesAdapter extends RecyclerView.Adapter<Bey2ollakPlaceViewHolder> {

    private List<Content> placesList;

    public PlacesAdapter(List<Content> placesList) {
        this.placesList = placesList;
    }

    @Override
    public int getItemCount() {
        return placesList.size();
    }

    @Override
    public void onBindViewHolder(Bey2ollakPlaceViewHolder bey2ollakPlaceViewHolder, int i) {
        Content ci = placesList.get(i);
        bey2ollakPlaceViewHolder.tvPlaceName.setText(ci.getName());
        bey2ollakPlaceViewHolder.tvPlaceAddress.setText(ci.getAddress());
    }

    @Override
    public Bey2ollakPlaceViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.
                from(viewGroup.getContext()).
                inflate(R.layout.item_place, viewGroup, false);

        return new Bey2ollakPlaceViewHolder(itemView);
    }

    public void addNewPlaces(List<Content> content) {
        if (placesList == null) {
            placesList = new ArrayList<>();
        }
        placesList.addAll(content);
        notifyDataSetChanged();
    }
}
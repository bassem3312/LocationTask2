package com.bey2ollak.locationtask.locationtask.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bey2ollak.locationtask.locationtask.R;
import com.bey2ollak.locationtask.locationtask.adapters.ViewHolders.Bey2ollakPlaceViewHolder;
import com.bey2ollak.locationtask.locationtask.adapters.ViewHolders.ProgressLoadMoreViewHolder;
import com.bey2ollak.locationtask.locationtask.models.Content;
import com.bey2ollak.locationtask.locationtask.utilities.Constants;

import java.util.ArrayList;
import java.util.List;

import static com.bey2ollak.locationtask.locationtask.utilities.Constants.VIEW_TYPE_LOADING;

/**
 * @author Bassem Mohsen : basem3312@gmail.com on 9/6/2017.
 */

public class PlacesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Content> placesList;
    private boolean isLoaded;

    public PlacesAdapter(List<Content> placesList) {
        this.placesList = placesList;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int i) {
        if (holder instanceof Bey2ollakPlaceViewHolder) {
            Bey2ollakPlaceViewHolder bey2ollakPlaceViewHolder = (Bey2ollakPlaceViewHolder) holder;
            Content ci = placesList.get(i);
            bey2ollakPlaceViewHolder.tvPlaceName.setText(ci.getName());
            bey2ollakPlaceViewHolder.tvPlaceAddress.setText(ci.getAddress());
        } else if (holder instanceof ProgressLoadMoreViewHolder) {
            ProgressLoadMoreViewHolder loadingViewHolder = (ProgressLoadMoreViewHolder) holder;
            loadingViewHolder.progressLoadMore.setIndeterminate(true);
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        if (viewType == Constants.VIEW_TYPE_Bey2ollak_Places_ITEM) {
            View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_place, viewGroup, false);
            return new Bey2ollakPlaceViewHolder(itemView);
        } else if (viewType == Constants.VIEW_TYPE_LOADING) {
            View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.progress_load_more, viewGroup, false);
            return new ProgressLoadMoreViewHolder(itemView);
        }
        return null;
    }

    public void addNewPlaces(List<Content> content) {
        if (placesList == null) {
            placesList = new ArrayList<>();
        }
        if (placesList.size() > 0 && placesList.get(placesList.size() - 1) == null) {
            placesList.remove(placesList.size() - 1);
            notifyItemRemoved(placesList.size());
        }
        isLoaded = false;
        placesList.addAll(content);
        notifyDataSetChanged();

    }

    @Override
    public int getItemCount() {
        return placesList == null ? 0 : placesList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return placesList.get(position) == null ? VIEW_TYPE_LOADING : placesList.get(position).getViewType();
    }

    public void addLoadMoreProgress() {
        if (placesList.size() != 0) {
            placesList.add(null);
            notifyItemInserted(placesList.size() - 1);
            isLoaded = true;
        }
    }

}
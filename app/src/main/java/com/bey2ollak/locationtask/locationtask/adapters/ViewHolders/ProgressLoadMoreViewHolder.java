package com.bey2ollak.locationtask.locationtask.adapters.ViewHolders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.bey2ollak.locationtask.locationtask.R;

/**
 * @author Bassem Mohsen : basem3312@gmail.com on 9/6/2017.
 */

public class ProgressLoadMoreViewHolder extends RecyclerView.ViewHolder {
    public final ProgressBar progressLoadMore;

    public ProgressLoadMoreViewHolder(View v) {
        super(v);
        progressLoadMore = (ProgressBar)v.findViewById(R.id.progress_load_more);
    }
}

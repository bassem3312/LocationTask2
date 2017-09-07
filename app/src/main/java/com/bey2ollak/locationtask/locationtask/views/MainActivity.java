package com.bey2ollak.locationtask.locationtask.views;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.bey2ollak.locationtask.locationtask.R;
import com.bey2ollak.locationtask.locationtask.adapters.PlacesAdapter;
import com.bey2ollak.locationtask.locationtask.models.Bey2ollakPlacesResponse;
import com.bey2ollak.locationtask.locationtask.models.Content;
import com.bey2ollak.locationtask.locationtask.presenters.imps.PlacesListAPIPresenterImp;
import com.bey2ollak.locationtask.locationtask.utilities.Constants;
import com.bey2ollak.locationtask.locationtask.utilities.EndlessRecyclerViewScrollListener;
import com.bey2ollak.locationtask.locationtask.viewInterfaces.PlacesListActivityInterface;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements PlacesListActivityInterface {
    int pageIndex = 0;
    private PlacesAdapter placesAdapter;
    private View progressLoading;
    private boolean isLoading;
    private int resoponceTotalNumberOfPages = Constants.NOT_FOUND_VALUE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        callGetPlacedAPI(pageIndex);
    }

    private void initViews() {
        progressLoading = findViewById(R.id.progress_loading);

        RecyclerView recPlaceList = (RecyclerView) findViewById(R.id.cardList_places);
        recPlaceList.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recPlaceList.setLayoutManager(llm);

        placesAdapter = new PlacesAdapter(new ArrayList<Content>());
        recPlaceList.setAdapter(placesAdapter);

        recPlaceList.addOnScrollListener(new EndlessRecyclerViewScrollListener(llm) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                if (!isLoading)
                    callGetPlacedAPI(++pageIndex);
            }
        });

    }

    private void callGetPlacedAPI(int pageIndex) {
        Log.e("Page Index",""+pageIndex);
        if (resoponceTotalNumberOfPages == Constants.NOT_FOUND_VALUE || pageIndex != resoponceTotalNumberOfPages) {
            PlacesListAPIPresenterImp placesListAPIPresenterImp = new PlacesListAPIPresenterImp(this, this);
            placesListAPIPresenterImp.callBey2ollakPlacesAPI(pageIndex, Constants.PAGES_SIZE);
        }
    }

    @Override
    public void showProgressLoading() {
        progressLoading.setVisibility(View.VISIBLE);
    }

    @Override
    public void showLoadMoreProgressLoading() {
        if (placesAdapter.getItemCount() != 0) {
            placesAdapter.addLoadMoreProgress();
        }
    }

    @Override
    public void dismissProgressLoading() {
        progressLoading.setVisibility(View.GONE);
    }

    @Override
    public void dismissLoadMoreProgressLoading() {

    }

    @Override
    public void showErrorDialog(String message) {

    }

    @Override
    public void setIsLoading(boolean isLoading) {
        this.isLoading = isLoading;
    }

    @Override
    public void populatePlacesList(Bey2ollakPlacesResponse bey2OllakPlacesResponseResponse) {
        if (bey2OllakPlacesResponseResponse.getContent() != null && bey2OllakPlacesResponseResponse.getContent().size() != 0) {
            if (placesAdapter == null) {
                placesAdapter = new PlacesAdapter(bey2OllakPlacesResponseResponse.getContent());
            } else {
                placesAdapter.addNewPlaces(bey2OllakPlacesResponseResponse.getContent());
            }
            resoponceTotalNumberOfPages = bey2OllakPlacesResponseResponse.getTotalPages().intValue();
        }
    }
}

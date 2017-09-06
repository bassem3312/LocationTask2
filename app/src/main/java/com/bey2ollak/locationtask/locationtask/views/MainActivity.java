package com.bey2ollak.locationtask.locationtask.views;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.bey2ollak.locationtask.locationtask.R;
import com.bey2ollak.locationtask.locationtask.adapters.PlacesAdapter;
import com.bey2ollak.locationtask.locationtask.models.Bey2ollakPlacesResult;
import com.bey2ollak.locationtask.locationtask.models.Content;
import com.bey2ollak.locationtask.locationtask.presenters.imps.PlacesListAPIPresenterImp;
import com.bey2ollak.locationtask.locationtask.utilities.Constants;
import com.bey2ollak.locationtask.locationtask.viewInterfaces.PlacesListActivityInterface;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements PlacesListActivityInterface {
    int pageIndex = 0;
    private PlacesAdapter placesAdapter;
    private View progressLoading;

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
    }

    private void callGetPlacedAPI(int pageIndex) {
        PlacesListAPIPresenterImp placesListAPIPresenterImp = new PlacesListAPIPresenterImp(this, this);
        placesListAPIPresenterImp.callBey2ollakPlacesAPI(pageIndex, Constants.PAGES_SIZE);
    }

    @Override
    public void showProgressLoading() {
        progressLoading.setVisibility(View.VISIBLE);

    }

    @Override
    public void dismissProgressLoading() {
        progressLoading.setVisibility(View.GONE);
    }

    @Override
    public void showErrorDialog(String message) {

    }

    @Override
    public void populatePlacesList(Bey2ollakPlacesResult bey2ollakPlacesResultResponse) {
        Log.e("=====PAges Size", bey2ollakPlacesResultResponse.getNumber().intValue() + "");
        if (placesAdapter == null) {
            placesAdapter = new PlacesAdapter(bey2ollakPlacesResultResponse.getContent());
        } else {
            placesAdapter.addNewPlaces(bey2ollakPlacesResultResponse.getContent());
        }
    }

    public void loadMorePlaces(int currentPlacesListSize) {

    }
}

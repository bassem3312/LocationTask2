package com.bey2ollak.locationtask.locationtask.presenters.imps;

import android.content.Context;
import android.util.Log;

import com.bey2ollak.locationtask.locationtask.R;
import com.bey2ollak.locationtask.locationtask.models.Bey2ollakPlacesResult;
import com.bey2ollak.locationtask.locationtask.networkUtility.PlacesService;
import com.bey2ollak.locationtask.locationtask.presenters.interfaces.PlacesListAPIPresenter;
import com.bey2ollak.locationtask.locationtask.utilities.NetWorkUtility;
import com.bey2ollak.locationtask.locationtask.viewInterfaces.PlacesListActivityInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @author Bassem Mohsen : basem3312@gmail.com on 9/6/2017.
 */

public class PlacesListAPIPresenterImp implements PlacesListAPIPresenter {
    private final PlacesListActivityInterface placesListActivityInterface;
    private final Context mContext;

    public PlacesListAPIPresenterImp(Context mContext, PlacesListActivityInterface placesListActivityInterface) {
        this.placesListActivityInterface = placesListActivityInterface;
        this.mContext = mContext;
    }

    @Override
    public void callBey2ollakPlacesAPI(int page, int size) {
        if (NetWorkUtility.CheckInternetConnection(mContext)) {
            placesListActivityInterface.showProgressLoading();
            PlacesService locationService = PlacesService.retrofit.create(PlacesService.class);
            Call<Bey2ollakPlacesResult> call = locationService.getPlaces(page, size);
            call.enqueue(new Callback<Bey2ollakPlacesResult>() {
                @Override
                public void onResponse(Call<Bey2ollakPlacesResult> call, Response<Bey2ollakPlacesResult> response) {
                    Log.e("=====Size of arraylist", response.body().getContent().size() + "");
                    placesListActivityInterface.populatePlacesList(response.body());
                    placesListActivityInterface.dismissProgressLoading();
                }

                @Override
                public void onFailure(Call<Bey2ollakPlacesResult> call, Throwable t) {
                    placesListActivityInterface.dismissProgressLoading();
                    placesListActivityInterface.showErrorDialog(t.toString());
                }
            });
        } else {
            placesListActivityInterface.showErrorDialog(mContext.getString(R.string.connection_error_message));
        }
    }
}

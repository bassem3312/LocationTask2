package com.bey2ollak.locationtask.locationtask.presenters.imps;

import android.content.Context;
import android.util.Log;

import com.bey2ollak.locationtask.locationtask.R;
import com.bey2ollak.locationtask.locationtask.models.Bey2ollakPlacesResponse;
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
            if (page == 0) {
                placesListActivityInterface.showProgressLoading();
            } else {
                placesListActivityInterface.showLoadMoreProgressLoading();
            }
            placesListActivityInterface.setIsLoading(true);

            PlacesService locationService = PlacesService.retrofit.create(PlacesService.class);
            Call<Bey2ollakPlacesResponse> call = locationService.getPlaces(page, size, 1000L);
            Log.e("====response URL",call.request().url().toString());
            call.enqueue(new Callback<Bey2ollakPlacesResponse>() {
                @Override
                public void onResponse(Call<Bey2ollakPlacesResponse> call, Response<Bey2ollakPlacesResponse> response) {
                    placesListActivityInterface.populatePlacesList(response.body());
                    placesListActivityInterface.dismissProgressLoading();
                    placesListActivityInterface.setIsLoading(false);
                }

                @Override
                public void onFailure(Call<Bey2ollakPlacesResponse> call, Throwable t) {
                    placesListActivityInterface.dismissProgressLoading();
                    placesListActivityInterface.showErrorDialog(t.toString());
                    placesListActivityInterface.setIsLoading(false);
                }
            });
        } else {
            placesListActivityInterface.showErrorDialog(mContext.getString(R.string.connection_error_message));
            placesListActivityInterface.setIsLoading(false);
        }
    }
}

package com.bey2ollak.locationtask.locationtask.viewInterfaces;

import com.bey2ollak.locationtask.locationtask.models.Bey2ollakPlacesResponse;

/**
 * @author Bassem Mohsen : basem3312@gmail.com on 9/6/2017.
 */

public interface PlacesListActivityInterface extends BaseViewInterface {
    void populatePlacesList(Bey2ollakPlacesResponse bey2OllakPlacesResponseResponse);
}

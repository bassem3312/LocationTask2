package com.bey2ollak.locationtask.locationtask.viewInterfaces;

import com.bey2ollak.locationtask.locationtask.models.Content;

import java.util.ArrayList;

/**
 * @author Bassem Mohsen : basem3312@gmail.com on 9/6/2017.
 */

public interface PlacesListActivityInterface extends BaseViewInterface {
    void populatePlasecList(ArrayList<Content> allPlaces);

    void loadMorePlaces(int currentPlacesListSize);
}

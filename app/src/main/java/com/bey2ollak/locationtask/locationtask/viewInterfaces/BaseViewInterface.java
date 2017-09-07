package com.bey2ollak.locationtask.locationtask.viewInterfaces;

/**
 * @author Bassem Mohsen : basem3312@gmail.com on 9/6/2017.
 */

public interface BaseViewInterface {

    public void showProgressLoading();

    public void showLoadMoreProgressLoading();

    public void dismissProgressLoading();

    public void dismissLoadMoreProgressLoading();

    public void showErrorDialog(String message);

    public void setIsLoading(boolean message);

}

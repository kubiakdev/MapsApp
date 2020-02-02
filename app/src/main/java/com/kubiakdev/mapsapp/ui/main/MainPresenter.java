package com.kubiakdev.mapsapp.ui.main;

import com.kubiakdev.mapsapp.base.BasePresenter;

public class MainPresenter extends BasePresenter<MainContract.View> implements MainContract.Presenter {

    @Override
    public void onShowMapsButtonClicked() {
        view.launchMapsScreen();
    }

    @Override
    public void onPrintLocationButtonClicked() {
        view.requestLocationPermissions();
    }
}

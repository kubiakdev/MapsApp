package com.kubiakdev.mapsapp.ui.main;

import com.kubiakdev.mapsapp.base.BaseContract;

public interface MainContract {

    interface View extends BaseContract.View {

        void launchMapsScreen();

        void requestLocationPermissions();

    }

    interface Presenter extends BaseContract.Presenter<View> {

        void onShowMapsButtonClicked();

        void onPrintLocationButtonClicked();
    }
}

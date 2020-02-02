package com.kubiakdev.mapsapp.ui.maps;

import com.kubiakdev.mapsapp.base.BaseContract;
import com.kubiakdev.mapsapp.model.MarkersData;

import java.util.List;

public interface MapsContract {

    public interface View extends BaseContract.View {

        void addMarkers(List<MarkersData> markersData);
    }

    public interface Presenter extends BaseContract.Presenter<MapsContract.View> {

        void onMapsReady();
    }
}

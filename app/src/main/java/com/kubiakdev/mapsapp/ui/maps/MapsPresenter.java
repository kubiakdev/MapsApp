package com.kubiakdev.mapsapp.ui.maps;

import com.google.android.gms.maps.model.LatLng;
import com.kubiakdev.mapsapp.base.BasePresenter;
import com.kubiakdev.mapsapp.model.MarkersData;

import java.util.ArrayList;
import java.util.List;

public class MapsPresenter extends BasePresenter<MapsContract.View> implements MapsContract.Presenter {

    private static final List<MarkersData> MARKERS_DATA = new ArrayList<MarkersData>() {{
        add(new MarkersData("Poznań", new LatLng(52.4, 16.9)));
        add(new MarkersData("Bangkok", new LatLng(13.75, 100.5)));
        add(new MarkersData("Berlin", new LatLng(52.5, 13.4)));
        add(new MarkersData(" Bruksela", new LatLng(50.8, 4.4)));
    }};

    @Override
    public void onMapsReady() {
        view.addMarkers(MARKERS_DATA);
    }
}

package com.kubiakdev.mapsapp.model;

import com.google.android.gms.maps.model.LatLng;

public class MarkersData {

    private String title;
    private LatLng latLng;

    public MarkersData(String title, LatLng latLng) {
        this.title = title;
        this.latLng = latLng;
    }

    public String getTitle() {
        return title;
    }

    public LatLng getLatLng() {
        return latLng;
    }
}

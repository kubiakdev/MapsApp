package com.kubiakdev.mapsapp.ui.maps;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.MarkerOptions;
import com.kubiakdev.mapsapp.R;
import com.kubiakdev.mapsapp.base.BaseActivity;
import com.kubiakdev.mapsapp.model.MarkersData;

import java.util.List;
import java.util.Objects;

public class MapsActivity
        extends BaseActivity<MapsContract.Presenter>
        implements MapsContract.View, OnMapReadyCallback {

    public static void startActivity(Context context){
        context.startActivity(new Intent(context, MapsActivity.class));
    }

    private GoogleMap googleMap;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SupportMapFragment mapFragment =
                (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        Objects.requireNonNull(mapFragment).getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;
        presenter.onMapsReady();
    }

    @Override
    public void addMarkers(List<MarkersData> markersData) {
        for (MarkersData markerData : markersData) {
            googleMap.addMarker(new MarkerOptions()
                    .title(markerData.getTitle())
                    .position(markerData.getLatLng())
            );
        }
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_maps;
    }

    @Override
    protected MapsContract.Presenter getPresenter() {
        return new MapsPresenter();
    }
}
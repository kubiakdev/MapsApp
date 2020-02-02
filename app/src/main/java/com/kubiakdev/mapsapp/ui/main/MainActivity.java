package com.kubiakdev.mapsapp.ui.main;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.kubiakdev.lib.AcceptedResponse;
import com.kubiakdev.lib.DeniedResponse;
import com.kubiakdev.lib.ForeverDeniedResponse;
import com.kubiakdev.lib.Perms;
import com.kubiakdev.mapsapp.R;
import com.kubiakdev.mapsapp.base.BaseActivity;
import com.kubiakdev.mapsapp.ui.maps.MapsActivity;

import java.util.List;

public class MainActivity extends BaseActivity<MainContract.Presenter> implements MainContract.View {

    private Button showMapsButton;
    private Button printLocationButton;
    private TextView locationText;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        showMapsButton = findViewById(R.id.showMapsButton);
        printLocationButton = findViewById(R.id.printLocation);
        locationText = findViewById(R.id.locationText);

        setOnClickListeners();
    }

    private void setOnClickListeners() {
        showMapsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onShowMapsButtonClicked();
            }
        });

        printLocationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onPrintLocationButtonClicked();
            }
        });
    }

    @Override
    public void launchMapsScreen() {
       MapsActivity.startActivity(this);
    }

    @Override
    public void requestLocationPermissions() {
        new Perms(this).request(
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION
        )
                .onResult(
                        new AcceptedResponse() {
                            @Override
                            public void onAllAccepted(List<String> acceptedPermissions) {
                                showLocationMessage();
                            }
                        },
                        new DeniedResponse() {
                            @Override
                            public void onAtLeastOneDenied(List<String> deniedPermissions) {
                                showPermissionsError();
                            }
                        },
                        new ForeverDeniedResponse() {
                            @Override
                            public void onAtLeastOneForeverDenied(List<String> foreverDeniedPermissions) {
                                showPermissionsError();
                            }
                        }
                );
    }

    private void showLocationMessage() {
        Location location = getLocation();
        if (location != null) {
            showLocationPosition(location);
        } else {
            showLocationPositionError();
        }
    }

    @SuppressLint("MissingPermission")
    private Location getLocation() {
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (locationManager != null) {
            return locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        } else {
            return null;
        }
    }

    @SuppressLint("SetTextI18n")
    private void showLocationPosition(Location location) {
        locationText.setText("Twoje współrzędne:\n\nDługość geograficzna: ${location.longitude}" +
                "\n\nSzerokość geograficzna:${location.latitude}");
    }

    private void showLocationPositionError() {
        Toast.makeText(
                getApplicationContext(),
                "Problem z określeniem pozycji. Sprawdź połączenie lokalizacyjne w opcjach systemu"
                , Toast.LENGTH_LONG
        ).show();
    }

    private void showPermissionsError() {
        Toast.makeText(this, "Nie przyznano odpowiednich uprawnień", Toast.LENGTH_LONG).show();
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_main;
    }

    @Override
    protected MainContract.Presenter getPresenter() {
        return new MainPresenter();
    }
}

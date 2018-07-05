package com.example.admin.googleplaces.activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.admin.googleplaces.R;
import com.example.admin.googleplaces.fragments.MapFragment;
import com.example.admin.googleplaces.fragments.PlaceListFragment;
import com.example.admin.googleplaces.manager.LocationManager;
import com.example.admin.googleplaces.model.Place;
import com.example.admin.googleplaces.model.PlacesResponse;
import com.example.admin.googleplaces.network.GetPlacesService;
import com.example.admin.googleplaces.network.RetrofitInstance;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class PlacesActivity extends AppCompatActivity implements SearchView.OnQueryTextListener,
        PlaceListFragment.OnListFragmentInteractionListener {

    private static final int MY_PERMISSIONS_REQUEST_LOCATION = 10;
    private static final String NAME = "Name";
    private static final String ADDRESS = "Address";
    private static final String RATING = "Rating";
    private static final String OPEN_NOW = "OpenNow";
    LocationManager locationManager;
    Location location;
    FusedLocationProviderClient fusedLocationProviderClient;
    public static final String TAG = PlacesActivity.class.getSimpleName() + "_TAG";
    GetPlacesService placesService;
    private static final int SEARCH_RADIUS = 2000; //represents a search radius of 2000 meters
    private SearchView searchView;
    PlaceListFragment placeListFragment;
    public static final String KEY = "AIzaSyCYoPa5nfFE647sNvSGYqNNhimE3W_F5h4";
    private MapFragment mapFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_places);
        this.fusedLocationProviderClient = LocationServices
                .getFusedLocationProviderClient(this);
        checkPermission();
        placesService = RetrofitInstance.getRetrofitInstance().create(GetPlacesService.class);
        searchView = findViewById(R.id.search);
        searchView.setOnQueryTextListener(this);
        placeListFragment = (PlaceListFragment) getSupportFragmentManager().findFragmentById(R.id.listFragment);
        mapFragment = (MapFragment) getSupportFragmentManager().findFragmentById(R.id.mapFragment);
        //for ease of testing, let's make an initial query looking for food
        onQueryTextSubmit("food");
    }

    public void getDirections(Place place) {
        String destination = getFormattedLocationString(place.getGeometry().getLocation());
        Uri gmmIntentUri = Uri.parse("google.navigation:q=" + destination);
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps");
        startActivity(mapIntent);
    }

    private String getFormattedLocationString(Location location) {
        String lat = String.valueOf(location.getLatitude());
        String lng = String.valueOf(location.getLongitude());
        return lat + "," + lng;
    }

    private String getFormattedLocationString(com.example.admin.googleplaces.model.Location location) {
        String lat = String.valueOf(location.getLat());
        String lng = String.valueOf(location.getLng());
        return lat + "," + lng;
    }

    //region ========================= SearchView.OnClickListener overrides ============================
    @SuppressLint("MissingPermission")
    @Override
    public boolean onQueryTextSubmit(final String query) {
        fusedLocationProviderClient.getLastLocation().addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                PlacesActivity.this.location = location;
                String locationString = getFormattedLocationString(location);

                placesService.getNearbyePlaces(locationString, SEARCH_RADIUS, KEY, query).enqueue(new Callback<PlacesResponse>() {
                    @Override
                    public void onResponse(Call<PlacesResponse> call, Response<PlacesResponse> response) {
                        List<Place> places = response.body().getPlaces();
                        placeListFragment.setPlaces(places);
                        mapFragment.showMarkers(places);
                    }

                    @Override
                    public void onFailure(Call<PlacesResponse> call, Throwable t) {
                        Toast.makeText(PlacesActivity.this, "Search Failed", Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }
    //endregion

    //region ======================== Permissions =========================
    private void checkPermission() {
        // Here, thisActivity is the current activity
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)) {
                showExplanantion();
            } else {
                // No explanation needed; request the permission
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_LOCATION);
            }
        }
    }

    public void showExplanantion() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this)
                .setTitle("Explanantion")
                .setMessage("I need this permission to work")
                .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getApplicationContext(), "Won't work", Toast.LENGTH_SHORT).show();
                    }
                })
                .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        requestPermission();
                    }
                });
        builder.create().show();
    }

    private void requestPermission() {
        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                MY_PERMISSIONS_REQUEST_LOCATION);
    }

    //endregion

    @Override
    public void onPlaceSelect(Place place) {
        Intent intent = new Intent(getApplicationContext(), DetailActivity.class);
        intent.putExtra(NAME, place.getName());
        intent.putExtra(ADDRESS, place.getVicinity());
        intent.putExtra(RATING, place.getRating());
        intent.putExtra(OPEN_NOW, place.getOpeningHours().getOpenNow());
        startActivity(intent);
    }
}

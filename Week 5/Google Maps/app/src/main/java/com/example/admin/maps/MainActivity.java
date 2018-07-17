package com.example.admin.maps;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.GroundOverlayOptions;
import com.google.android.gms.maps.model.JointType;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.gms.maps.model.RoundCap;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.g_map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setMinZoomPreference(9);
        mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
        LatLng issaquah = new LatLng(47.5301011, -122.0326191);
        LatLng seattle = new LatLng(47.6062095, -122.3320708);
        LatLng bellevue = new LatLng(47.6101497, -122.2015159);
        LatLng sammamish = new LatLng(47.6162683, -122.0355736);
        LatLng redmond = new LatLng(47.6739881, -122.121512);



        makeMarker(redmond);
        makeGroundOverlay(sammamish, 5000);
        makeCircle(seattle);
        makePolyline(seattle, redmond);
        makePolygon(issaquah, seattle, bellevue, sammamish, redmond);

        mMap.moveCamera(CameraUpdateFactory.newLatLng(bellevue));
    }

    private void makeGroundOverlay(LatLng bellevue, float size) {
        GroundOverlayOptions groundOverlayOptions = new GroundOverlayOptions();
        groundOverlayOptions.position(bellevue, size, size )
                .image( BitmapDescriptorFactory.fromResource(R.drawable.star));

        mMap.addGroundOverlay(groundOverlayOptions);
    }

    private void makeMarker(LatLng location) {
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(new LatLng(47.6101497, -122.2015159))
                .title("Bellevue")
                .icon( BitmapDescriptorFactory.fromResource(R.drawable.star))
                .rotation(20)
                .draggable(false);
        Marker m = mMap.addMarker(markerOptions);
        m.showInfoWindow();
    }

    private void makePolygon(LatLng loc1, LatLng loc2, LatLng loc3, LatLng loc4, LatLng loc5) {
        PolygonOptions polygonOptions = new PolygonOptions();
        polygonOptions.add(loc1, loc2, loc3, loc4, loc5);
        polygonOptions.strokeJointType(JointType.ROUND);
        polygonOptions.strokeColor(Color.GREEN);
        polygonOptions.strokeWidth(10);

        mMap.addPolygon(polygonOptions);
    }

    private void makePolyline(LatLng loc1, LatLng loc2) {
        PolylineOptions plo =  new PolylineOptions();
        plo.add(loc1);
        plo.add(loc2);
        plo.color(Color.RED);
        plo.geodesic(true);
        plo.startCap(new RoundCap());
        plo.width(20);
        plo.jointType(JointType.BEVEL);
        mMap.addPolyline(plo);
    }

    private void makeCircle(LatLng location) {
        CircleOptions circleOptions = new CircleOptions();
        circleOptions.center(location);
        circleOptions.radius(3500);
        circleOptions.fillColor(Color.BLUE);
        circleOptions.strokeColor(Color.RED);
        circleOptions.strokeWidth(2);

        mMap.addCircle(circleOptions);
    }
}
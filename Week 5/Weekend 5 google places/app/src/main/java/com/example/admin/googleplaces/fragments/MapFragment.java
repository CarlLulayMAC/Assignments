package com.example.admin.googleplaces.fragments;

import android.content.Context;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.admin.googleplaces.R;
import com.example.admin.googleplaces.model.Place;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MapFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class MapFragment extends SupportMapFragment implements OnMapReadyCallback {

    private OnFragmentInteractionListener mListener;
    private GoogleMap map;

    public MapFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.getMapAsync(this);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
    }

    public void showMarkers(List<Place> placeList) {
        if (placeList.size() > 0) {
            for (Place place : placeList) {
                LatLng location = new LatLng(place.getGeometry().getLocation().getLat(), place.getGeometry().getLocation().getLng());
                MarkerOptions markerOptions = new MarkerOptions();
                markerOptions.position(location)
                        .title(place.getName())
                        .draggable(false);
                map.addMarker(markerOptions);
            }
            LatLng cameraFocus = new LatLng(placeList.get(0).getGeometry().getLocation().getLat(),
                    placeList.get(0).getGeometry().getLocation().getLng());
            map.moveCamera(CameraUpdateFactory.newLatLng(cameraFocus));
            map.setMinZoomPreference(9);
            map.setMaxZoomPreference(2);
        }
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}

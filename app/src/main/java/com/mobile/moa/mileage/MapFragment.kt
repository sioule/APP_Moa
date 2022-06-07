package com.mobile.moa.mileage

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.mobile.moa.R
import com.mobile.moa.databinding.FragmentMapBinding

/* written by keh
date: 22.06.08 */

class MapFragment : Fragment(), OnMapReadyCallback {

    private lateinit var mapView: SupportMapFragment
    lateinit var binding: FragmentMapBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMapBinding.inflate(inflater, container, false)

        mapView = childFragmentManager.findFragmentById(R.id.shop_map) as SupportMapFragment;
        mapView.onCreate(savedInstanceState)
        mapView.getMapAsync(this);


        return binding.root
    }

    override fun onMapReady(googleMap: GoogleMap) {
        googleMap.addMarker(
            MarkerOptions()
                .position(LatLng(0.0, 0.0))
                .title("Marker")
        )
    }
}
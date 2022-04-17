package com.example.pwbaseprova;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentContatti#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentContatti extends Fragment implements OnMapReadyCallback {


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FragmentContatti() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Page4Fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentContatti newInstance(String param1, String param2) {
        FragmentContatti fragment = new FragmentContatti();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View rootView = inflater.inflate(R.layout.contatti, container, false);

        FloatingActionButton callButton = rootView.findViewById(R.id.callButton);
        callButton.setOnClickListener(v->{
            Intent intent = new Intent(Intent.ACTION_DIAL);
            intent.setData(Uri.parse("tel:058833215"));
            startActivity(intent);
        });

        FloatingActionButton mailButton = rootView.findViewById(R.id.mailButton);
        mailButton.setOnClickListener(v->{
            Intent intent = new Intent(Intent.ACTION_VIEW);
            Uri data = Uri.parse("mailto:collinaalsole@gmail.com?subject=" +
                    Uri.encode("Informazioni") +
                    "&body=" + Uri.encode("Salve,\n le scrivo per informazioni..."));
            intent.setData(data);
            startActivity(intent);
        });

        TextView textCall = rootView.findViewById(R.id.textViewCall);
        textCall.setOnClickListener(v->{
            Intent intent = new Intent(Intent.ACTION_DIAL);
            intent.setData(Uri.parse("tel:058833215"));
            startActivity(intent);
        });

        TextView textMail = rootView.findViewById(R.id.textViewMail);
        textMail.setOnClickListener(v->{
            Intent intent = new Intent(Intent.ACTION_VIEW);
            Uri data = Uri.parse("mailto:collinaalsole@gmail.com?subject=" +
                    Uri.encode("Informazioni") +
                    "&body=" + Uri.encode("Salve,\n le scrivo per informazioni..."));
            intent.setData(data);
            startActivity(intent);
        });

        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        return rootView;
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        LatLng agriturismo = new LatLng(43.4972223, 10.7772087);

        googleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);

        googleMap.addMarker(new MarkerOptions()
                .position(agriturismo)
                .title("La collina al Sole"));

        googleMap.moveCamera(CameraUpdateFactory.newLatLng(agriturismo));
        googleMap.moveCamera(CameraUpdateFactory.zoomTo(15));
    }

}
package com.example.pwbaseprova;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentHome#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentHome extends Fragment {

    ClickListener activityCallback;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FragmentHome() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Page1Fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentHome newInstance(String param1, String param2) {
        FragmentHome fragment = new FragmentHome();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public interface ClickListener{
        void onButtonClick(int id);
    }

    @Override
    public void onAttach(Context context){
        super.onAttach(context);
        try{
            activityCallback = (ClickListener) context;
        }catch(ClassCastException e){
            throw new ClassCastException(context.toString() + " must implement ClickListener");
        }
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
        View view = inflater.inflate(R.layout.home, container, false);

        ///Image Slider
        ArrayList imageList = new ArrayList<SlideModel>();// Create image list

        imageList.add(new SlideModel(R.drawable.borghese, "Vincitore di 4 ristoranti 2021",ScaleTypes.CENTER_CROP));
        imageList.add(new SlideModel(R.drawable.agriturismo, "L'agriturismo",ScaleTypes.CENTER_CROP));
        imageList.add(new SlideModel(R.drawable.maneggio,"Il maneggio", ScaleTypes.CENTER_CROP));

        ImageSlider imageSlider = view.findViewById(R.id.image_slider);
        imageSlider.setImageList(imageList);
        ///
        CardView maneggio = view.findViewById(R.id.cardManeggio);
        CardView galleria = view.findViewById(R.id.cardGalleria);
        CardView itinerari = view.findViewById(R.id.cardItinerari);
        CardView piatti = view.findViewById(R.id.cardPiatti);

        maneggio.setOnClickListener(v->{
            Intent intent = new Intent(view.getContext(),ManeggioActivity.class);
            startActivity(intent);
        });

        galleria.setOnClickListener(v->{
            Intent intent = new Intent(view.getContext(),GalleriaActivity.class);
            startActivity(intent);
        });

        itinerari.setOnClickListener(v->{
            cardClicked(2);
        });

        piatti.setOnClickListener(v->{
           cardClicked(1);
        });
        return view;
    }

    private void cardClicked(int id) {
        activityCallback.onButtonClick(id);
    }


}
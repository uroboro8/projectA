package com.example.pwbaseprova;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.pwbaseprova.gallery.ImageCustom;
import com.squareup.picasso.Picasso;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link GalleryDialogFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class GalleryDialogFragment extends androidx.fragment.app.DialogFragment implements
        ScaleGestureDetector.OnScaleGestureListener{

    private String imageUrl;
    private ImageView image;
    private ScaleGestureDetector scaleGestureDetector;
    private float mScaleFactor = 1.0f;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public GalleryDialogFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment GalleryDialogFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static GalleryDialogFragment newInstance(String param1, String param2, String imageUrl) {
        GalleryDialogFragment fragment = new GalleryDialogFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        args.putString("image_url",imageUrl);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
            imageUrl = getArguments().getString("image_url");
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_gallery_dialog, container, false);

        scaleGestureDetector = new ScaleGestureDetector(getActivity(), this);

        view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                scaleGestureDetector.onTouchEvent(motionEvent);
                return true;
            }
        });

        image = view.findViewById(R.id.dialogFragmentImage);

        if(imageUrl.trim().length() == 0)
            Picasso.get().load("https://www.mrw.it/img/cope/0iwkf4_1609360688.jpg").into(image);
        else
            Picasso.get().load(imageUrl).into(image);

        return view;
    }


    @Override
    public boolean onScale(ScaleGestureDetector detector) {
        if(mScaleFactor >= 1.0f) {
            mScaleFactor *= detector.getScaleFactor();
            image.setScaleX(mScaleFactor);
            image.setScaleY(mScaleFactor);
        }
        else{
            mScaleFactor = 1.0f;
        }
        return true;
    }

    @Override
    public boolean onScaleBegin(ScaleGestureDetector scaleGestureDetector) {
        return true;
    }

    @Override
    public void onScaleEnd(ScaleGestureDetector scaleGestureDetector) {
    }
}
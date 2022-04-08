package com.example.pwbaseprova;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.pwbaseprova.itinerari.Itinerari;
import com.example.pwbaseprova.itinerari.Itinerario;
import com.example.pwbaseprova.piatti.Piatti;
import com.example.pwbaseprova.piatti.Piatto;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentItinerari#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentItinerari extends Fragment {

    ArrayList<Itinerario> itinerariArrayList;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FragmentItinerari() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Page3Fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentItinerari newInstance(String param1, String param2) {
        FragmentItinerari fragment = new FragmentItinerari();
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
        View view = inflater.inflate(R.layout.itinerari, container, false);
        itinerariArrayList = new ArrayList<>();

        //Inserire codice per fare cose qua
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://uroboro8.github.io/JsonRepository/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        //Http handler è un'interfaccia,retrofit si occupa di istanziarla
        HttpHandler service = retrofit.create(HttpHandler.class);

        //Gli dico che richiesta fare
        Call<Itinerari> request = service.getAllItinerari();

        //Faccio la chiamata al server
        request.enqueue(new Callback<Itinerari>() {
            @Override
            public void onResponse(Call<Itinerari> call, Response<Itinerari> response) {
                if(response.isSuccessful()) {
                    List<Itinerario> itinerari = response.body().getItinerari();
                    itinerariArrayList.addAll(itinerari);
                    Log.e("JSON",itinerariArrayList + "");
                }
            }

            @Override
            public void onFailure(Call<Itinerari> call, Throwable t) {

            }
        });

        return view;
    }
}
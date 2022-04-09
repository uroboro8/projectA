package com.example.pwbaseprova;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
 * Use the {@link FragmentPiatti#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentPiatti extends Fragment implements CustomAdapterPiatti.ItemClickListener {

    ArrayList<Piatto> piattiArrayList;
    CustomAdapterPiatti customAdapterPiatti;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FragmentPiatti() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Page2Fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentPiatti newInstance(String param1, String param2) {
        FragmentPiatti fragment = new FragmentPiatti();
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
       View rootView = inflater.inflate(R.layout.piatti, container, false);

        piattiArrayList = new ArrayList<>();
       RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerViewPiatti);
       recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
       customAdapterPiatti = new CustomAdapterPiatti(piattiArrayList);
       customAdapterPiatti.setClickListener(this);
       recyclerView.setAdapter(customAdapterPiatti);


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://uroboro8.github.io/JsonRepository/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        //Http handler è un'interfaccia,retrofit si occupa di istanziarla
        HttpHandler service = retrofit.create(HttpHandler.class);

        //Gli dico quale metodo usare
        Call<Piatti> piatti = service.getAllPiatti();

        //Faccio la chiamata al server
        piatti.enqueue(new Callback<Piatti>() {
            @Override
            public void onResponse(Call<Piatti> call, Response<Piatti> response) {
                if(response.isSuccessful()) {
                    List<Piatto> piattiList = response.body().getPiatti();
                    piattiArrayList.addAll(piattiList);
                    Log.e("JSON",piattiArrayList + "");
                    customAdapterPiatti.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<Piatti> call, Throwable t) {

            }
        });

       return rootView;
    }


    @Override
    public void onItemClick(View view, int position) {
        Log.e("dentro","dentro");
        Intent intent = new Intent(getActivity().getBaseContext(),DettaglioPiattoActivity.class);
        intent.putExtra("item-value",customAdapterPiatti.getItem(position));
        startActivity(intent);
    }

    @Override
    public void onPause() {
        super.onPause();
        customAdapterPiatti.setClickListener(null);
    }

    @Override
    public void onResume() {
        super.onResume();
        customAdapterPiatti.setClickListener(this);
    }
}
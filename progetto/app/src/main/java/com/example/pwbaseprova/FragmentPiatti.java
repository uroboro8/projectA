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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

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

    private ArrayList<Piatto> piattiArrayList;
    private ArrayList<Piatto> filteredList;
    private CustomAdapterPiatti customAdapterPiatti;

    private HttpHandler service;

    /*
     *  NOTE_DONE = richiesta non fatta
     *  OK =  successo
     *  BAD = fallimento
     */
    private String  REQUEST_CODE = "NOT_DONE";

    //Filtro
    private AutoCompleteTextView menu;


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

        View rootView = inflater.inflate(R.layout.piatti, container, false);

        //Creo la classe retrofit,dandogli l'url base e il convertitore per il Json
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://uroboro8.github.io/JsonRepository/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        //Http handler è un'interfaccia,retrofit si occupa di implementare il metodo
        service = retrofit.create(HttpHandler.class);

        //In questo mondo non ri-eseguo la chiamata http
        //passando da portrait a landscape e viceversa
        if(savedInstanceState == null) {
            piattiArrayList = new ArrayList<>();
            filteredList = new ArrayList<>();
            customAdapterPiatti = new CustomAdapterPiatti(piattiArrayList);
            //Creo la request
            Call<Piatti> request = service.getAllPiatti();
            HttpCall(request);
        }else{
            piattiArrayList = savedInstanceState.getParcelableArrayList("piatti");
            filteredList = savedInstanceState.getParcelableArrayList("piatti-filtered");
            if(!filteredList.isEmpty()){
                customAdapterPiatti = new CustomAdapterPiatti(filteredList);
            }else{
                customAdapterPiatti = new CustomAdapterPiatti(piattiArrayList);
            }
        }

        //Setto la RecyclerView per visualizzare gli elementi a grilia e gli imposto l'adapter per le mie immagini
        //Recycler View
        RecyclerView piattiRecyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerViewPiatti);
        piattiRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        //Adapter
        customAdapterPiatti.setClickListener(this);
        piattiRecyclerView.setAdapter(customAdapterPiatti);

        menu = rootView.findViewById(R.id.filterPiatti);

        return rootView;
    }

    @Override
    public void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList("piatti",piattiArrayList);
        outState.putParcelableArrayList("piatti-filtered",filteredList);
    }

    public void HttpCall(Call<Piatti> request){
        //Faccio la chiamata al server
        request.enqueue(new Callback<Piatti>() {
            @Override
            public void onResponse(Call<Piatti> call, Response<Piatti> response) {
                if(response.isSuccessful()) {
                    List<Piatto> piattiList = response.body().getPiatti();
                    piattiArrayList.addAll(piattiList);
                    customAdapterPiatti.notifyDataSetChanged();
                    REQUEST_CODE = "OK";
                    Log.e("JSON",piattiArrayList + "");
                }
            }
            @Override
            public void onFailure(Call<Piatti> call, Throwable t) {
                REQUEST_CODE = "BAD";
                Log.e("failure",t.getMessage());
            }
        });
    }

    @Override
    public void onItemClick(View view, int position) {
        Intent intent = new Intent(getActivity().getBaseContext(),DettaglioPiattoActivity.class);
        intent.putExtra("item-value",customAdapterPiatti.getItem(position));
        startActivity(intent);
    }

    @Override
    public void onPause() {
        super.onPause();
        //Impedisce che la lista sia cliccabile da altre tab
        customAdapterPiatti.setClickListener(null);
    }

    @Override
    public void onResume() {
        super.onResume();
        //Quando torno su questa tab,ripristino l'ascolto dei click
        customAdapterPiatti.setClickListener(this);

        //Se i dati NON sono stati recuperati la prima volta(es. no internet),rifaccio la chiamata
        if(REQUEST_CODE == "BAD"){
            Call<Piatti> request = service.getAllPiatti();
            HttpCall(request);
        }

        String[] filter = getResources().getStringArray(R.array.filter_piatti);
        ArrayAdapter adapter = new ArrayAdapter(getContext(),R.layout.filter_item,filter);
        menu.setAdapter(adapter);
        menu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View arg1, int pos,
                                    long id) {
                String menuChoice = (String) parent.getItemAtPosition(pos);
                if(piattiArrayList != null){
                    filteredList.clear();
                    if(menuChoice.equals("Tutti")){
                        customAdapterPiatti.updateList(piattiArrayList);
                    }
                    else {
                        for (Piatto piatto : piattiArrayList) {
                            if (piatto.getType().toLowerCase().equals(menuChoice.toLowerCase()))
                                filteredList.add(piatto);
                        }
                        customAdapterPiatti.updateList(filteredList);
                    }
                }
            }
        });
    }
}
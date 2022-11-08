package com.example.helloworld;

//import static com.example.helloworld.connexion.pseudo;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.github.mikephil.charting.charts.PieChart;

public class fragment_profile extends Fragment implements AdapterView.OnItemClickListener {
    private SessionManager sessionManager;
    private TextView affichage_pseudo;
    private Button btn_logout;
    @Nullable
    @Override

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_profile,container,false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

     /*   String[] listviewitems ={"Adresse Mail","Mot de passe","A propos"};

        ListView listView=(ListView) view.findViewById(R.id.listview);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1,listviewitems);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);*/
        PieChart pieChart;
        sessionManager = new SessionManager(getActivity());
        if(sessionManager.isLogged()){
            String pseudo = sessionManager.getPseudo();
            String id = sessionManager.getId();
            affichage_pseudo = (TextView) view.findViewById(R.id.pseudonyme);
            affichage_pseudo.setText(pseudo);
            btn_logout = (Button) view.findViewById(R.id.logout);
        }
        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sessionManager.logout();
                Intent intent = new Intent(getActivity(), connexion.class);
                startActivity(intent);

            }
        });

    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

    }
/*
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int i, long l) {
        if(i==0) Toast.makeText(getActivity(),"Adresse domicile",Toast.LENGTH_SHORT).show();
        if(i==1) Toast.makeText(getActivity(),"Adresse Mail",Toast.LENGTH_SHORT).show();
        if(i==2) Toast.makeText(getActivity(),"Téléphone mobile",Toast.LENGTH_SHORT).show();
        if(i==3) Toast.makeText(getActivity(),"Mot de passe",Toast.LENGTH_SHORT).show();
        if(i==4) Toast.makeText(getActivity(),"Reconnaissance par empreinte digitale",Toast.LENGTH_SHORT).show();
        if(i==5) Toast.makeText(getActivity(),"Notifications",Toast.LENGTH_SHORT).show();
        if(i==6) Toast.makeText(getActivity(),"Données personnelles",Toast.LENGTH_SHORT).show();
        if(i==7) Toast.makeText(getActivity(),"A propos",Toast.LENGTH_SHORT).show();

    }*/
}

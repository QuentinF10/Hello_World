package com.example.helloworld;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.databinding.MergedDataBinderMapper;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.helloworld.databinding.ActivityMainBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link fragment_home#newInstance} factory method to
 * create an instance of this fragment.
 */
public class fragment_home extends Fragment  {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    // fragment_home binding;
    ArrayList<String> userList;
    ArrayList<String> amountList;
    ArrayAdapter<String> listAdapter;
    Handler mainHandler = new Handler();
    ProgressDialog progressDialog;
    private SessionManager sessionManager;
    SwipeRefreshLayout swipeRefreshLayout;


    public fragment_home() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static fragment_home newInstance(String param1, String param2) {
        fragment_home fragment = new fragment_home();
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

    @SuppressLint("RestrictedApi")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_home, container, false);


    }

    //afficher date dans le textview fragment
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        TextView dateView = (TextView) getView().findViewById(R.id.textView2);
        setDate(dateView);

        //Button reload = (Button) getView().findViewById(R.id.reload);
        ListView listView=(ListView) getView().findViewById(R.id.transactions);
        SwipeRefreshLayout swipeRefreshLayout = view.findViewById(R.id.refreshLayout);

        userList = new ArrayList<>();
        //amountList = new ArrayList<>();
        listAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1 ,userList);
        listView.setAdapter(listAdapter);
        new GetData().start();

        //on gère les appuis sur les items de la listview
     /*   listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if(i == 0){


                }
                else if(i == 1){

                }
                else if (i == 2){

                }
            }
        });*/

        // Refresh  the layout
        swipeRefreshLayout.setOnRefreshListener(
                new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {

                        //pour eviter que la listview refresh sans supprimer les anciens éléments
                        listAdapter.clear();
                        //on lance la récup de données
                        new GetData().start();

                        // This line is important as it explicitly
                        // refreshes only once
                        // If "true" it implicitly refreshes forever
                        swipeRefreshLayout.setRefreshing(false);
                    }
                });
        sessionManager = new SessionManager(getActivity());
        if(sessionManager.isLogged()){
            String pseudo = sessionManager.getPseudo();
            String id = sessionManager.getId();
            TextView affichage_pseudo = (TextView) view.findViewById(R.id.textView5);
            affichage_pseudo.setText(pseudo);
        }
    }

        //récuperer les données du JSON
        class GetData extends Thread {

            String data = "";

            @Override
            public void run() {

                mainHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        progressDialog = new ProgressDialog(getActivity());
                        progressDialog.setMessage("Getting Data");
                        progressDialog.setCancelable(false);
                        progressDialog.show();
                    }
                });
                try {
                    URL url = new URL("https://api.npoint.io/880c49e07b8ee16d5c23");
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    InputStream inputStream = httpURLConnection.getInputStream();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                    String line;

                    while ((line = bufferedReader.readLine()) != null) {
                        data = data + line;
                    }
                    //récup des valeurs remplissage des tableaux pour les items de la listview
                    if (!data.isEmpty()) {
                        JSONObject jsonObject = new JSONObject(data);
                        JSONArray Transactions = jsonObject.getJSONArray("Transactions");
                        for (int i = 0; i < Transactions.length(); i++) {
                            JSONObject objet = Transactions.getJSONObject(i);
                            String name = objet.getString("name");
                            //JSONObject amounts = Transactions.getJSONObject(i);
                            String amount = objet.getString("amount");

                            //JSONObject types = Transactions.getJSONObject(i);
                            String type = objet.getString("type");
                            String additions = name + "" + amount + "                                 " + type;
                            //Intent intent = new Intent(getActivity(),transaction_activity.class);
                            // intent.putExtra("amount",amount);
                            //startActivity(intent);
                            userList.add(additions);
                           // amountList.add(amount);
                        }
                    }
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                mainHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (progressDialog.isShowing()) progressDialog.dismiss();
                        listAdapter.notifyDataSetChanged();
                    }
                });
            }
        }



    public void setDate (TextView view){
        Date today = Calendar.getInstance().getTime();//getting date
        SimpleDateFormat formatter = new SimpleDateFormat("MM");//formating according to my need
        String date = formatter.format(today);
        switch (date){
            case "1":
                date = "Janvier";
                break;
            case "2":
                date = "Février";
                break;
            case "3":
                date = "Mars";
                break;
            case "4":
                date = "Avril";
                break;
            case "5":
                date = "Mai";
                break;
            case "6":
                date = "Juin";
                break;
            case "7":
                date = "Juillet";
                break;
            case "8":
                date = "Août";
                break;
            case "9":
                date = "Septembre";
                break;
            case "10":
                date = "Octobre";
                break;
            case "11":
                date = "Novembre";
                break;
            case "12":
                date = "Décembre";
                break;
        }
        view.setText(date);
    }
/*
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

    }

    public static class DataBinderMapperImpl extends MergedDataBinderMapper {
      DataBinderMapperImpl() {
        addMapper(new com.example.helloworld.DataBinderMapperImpl());
      }
    }

    public static interface DataBindingComponent {
    }*/
    }

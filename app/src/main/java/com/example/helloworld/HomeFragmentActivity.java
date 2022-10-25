package com.example.helloworld;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.databinding.MergedDataBinderMapper;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragmentActivity#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragmentActivity extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HomeFragmentActivity() {
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
    public static HomeFragmentActivity newInstance(String param1, String param2) {
        HomeFragmentActivity fragment = new HomeFragmentActivity();
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
        TextView dateView = (TextView) getView().findViewById(R.id.dateTextView);
        setDate(dateView);
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
    public static class DataBinderMapperImpl extends MergedDataBinderMapper {
      DataBinderMapperImpl() {
        addMapper(new com.example.helloworld.DataBinderMapperImpl());
      }
    }

    public static interface DataBindingComponent {
    }
}
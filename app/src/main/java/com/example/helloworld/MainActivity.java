package com.example.helloworld;


import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;



import com.example.helloworld.databinding.ActivityMainBinding;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
  //  ActivityMainBinding binding2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        replaceFragment(new HomeFragmentActivity());

        binding.bottomNavigationView.setOnNavigationItemSelectedListener(item -> {

            switch(item.getItemId()){
                case R.id.home:
                    replaceFragment(new HomeFragmentActivity());
                    break;
                case R.id.budget:
                    replaceFragment(new BudgetFragment());
                    break;
                case R.id.epargne:
                    replaceFragment(new EpargneFragment());
                    break;
                case R.id.stats:
                    replaceFragment(new StatsFragment());
                    break;
                case R.id.profile:
                    replaceFragment(new fragment_profile());
                    break;

            }
            return true;
        });

    }

    private void replaceFragment(Fragment fragment) {

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout,fragment);
        fragmentTransaction.commit();
    }

}
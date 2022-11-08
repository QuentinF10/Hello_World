package com.example.helloworld;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private PieChart pieChart;
    Spinner spinner;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pieChart = findViewById(R.id.activity_main_piechart);

        spinner = (Spinner)findViewById(R.id.spinner);
        String[] arrMonths = new String[]{"Septembre","Octobre","Novembre"};
        ArrayAdapter<CharSequence> adapter1 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,arrMonths);
        spinner.setAdapter(adapter1 );
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = arrMonths[position];
                setupPieChart();
                loadPieChartData(selectedItem);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });




    }


    private void setupPieChart() {
        pieChart.setDrawHoleEnabled(true);
        pieChart.setUsePercentValues(true);
        pieChart.setEntryLabelTextSize(12);
        pieChart.setEntryLabelColor(Color.BLACK);
        pieChart.getDescription().setEnabled(false);

       // Legend l = pieChart.getLegend();
        //l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        //l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        //l.setOrientation(Legend.LegendOrientation.VERTICAL);
       // l.setDrawInside(false);
       // l.setEnabled(true);

    }
    private void loadPieChartData(String selectedItem){
        ArrayList<PieEntry> entries = new ArrayList<>();

        if (Objects.equals(selectedItem, "Septembre")) {
            entries.add(new PieEntry(0.2f, "Food"));
            entries.add(new PieEntry(0.1f, "Essence"));
            entries.add(new PieEntry(0.5f, "Porno"));
            entries.add(new PieEntry(0.2f, "Electricity"));
        }
        else if(Objects.equals(selectedItem, "Octobre")){
            entries.add(new PieEntry(0.1f, "Food"));
            entries.add(new PieEntry(0.2f, "Essence"));
            entries.add(new PieEntry(0.3f, "Porno"));
            entries.add(new PieEntry(0.4f, "Electricity"));
        }
        else if(Objects.equals(selectedItem, "Novembre")){
            entries.add(new PieEntry(0.3f, "Food"));
            entries.add(new PieEntry(0.3f, "Essence"));
            entries.add(new PieEntry(0.3f, "Porno"));
            entries.add(new PieEntry(0.1f, "Electricity"));
        }
        ArrayList<Integer> colors = new ArrayList<>();

        for (int color: ColorTemplate.MATERIAL_COLORS) {
            colors.add(color);
        }

        for (int color: ColorTemplate.VORDIPLOM_COLORS) {
            colors.add(color);
        }
        PieDataSet dataSet = new PieDataSet(entries, "Expense Category");
        dataSet.setColors(colors);

        PieData data = new PieData(dataSet);
        data.setDrawValues(true);
        data.setValueFormatter(new PercentFormatter(pieChart));
        data.setValueTextSize(12f);
        data.setValueTextColor(Color.BLACK);

        pieChart.setData(data);
        pieChart.invalidate();

        pieChart.animateY(1400, Easing.EaseInOutQuad);
    }
}
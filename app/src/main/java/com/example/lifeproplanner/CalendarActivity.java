package com.example.lifeproplanner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.CalendarView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.SharedPreferences;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Map;


public class CalendarActivity extends AppCompatActivity {

    CalendarView calendarView;
    EditText editTextPlanTitle;
    TimePicker timePicker;
    Button buttonSavePlan;
    RecyclerView recyclerView;
    SharedPreferences sharedPreferences;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        calendarView = findViewById(R.id.calendarView);
        editTextPlanTitle = findViewById(R.id.editTextPlanTitle);
        timePicker = findViewById(R.id.timePicker);
        buttonSavePlan = findViewById(R.id.buttonSavePlan);
        recyclerView = findViewById(R.id.recyclerView);
        sharedPreferences = getSharedPreferences("PlanPrefs", MODE_PRIVATE);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int year, int month, int dayOfMonth) {
                String date = year + "-" + (month + 1) + "-" + dayOfMonth;
                loadPlans(date);
            }
        });

        buttonSavePlan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String planTitle = editTextPlanTitle.getText().toString();
                int hour = timePicker.getHour();
                int minute = timePicker.getMinute();
                int year = (int) (calendarView.getDate() / 10000);
                int month = (int) ((calendarView.getDate() % 10000) / 100);
                int dayOfMonth = (int) calendarView.getDate() % 100;
                String selectedDate = year + "-" + month + "-" + dayOfMonth;

                savePlan(selectedDate, planTitle, hour, minute);
            }
        });

    }

    private void savePlan(String date, String title, int hour, int minute){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        String planKey = date + "_" + hour + ":" + minute;
        editor.putString(planKey, title);
        editor.apply();

        Toast.makeText(CalendarActivity.this, "Plan Saved", Toast.LENGTH_SHORT).show();
        loadPlans(date);
    }


    private void loadPlans(String date) {
        Map<String, ?> allPlans = sharedPreferences.getAll();
        ArrayList<String> plansForDate = new ArrayList<>();

        for (Map.Entry<String, ?> entry : allPlans.entrySet()) {
            String key = entry.getKey();
            if (key.startsWith(date)) {
                plansForDate.add(entry.getValue().toString());
            }
        }

        RecyclerView.Adapter<PlanAdapter.PlanViewHolder> adapter = new PlanAdapter(plansForDate);
        recyclerView.setAdapter(adapter);
    }


}
package com.techwiz.pockethishab;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.techwiz.pockethishab.fragments.Dashboard;
import com.techwiz.pockethishab.fragments.Expense;
import com.techwiz.pockethishab.fragments.Income;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottomNavigationView);

        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        bottomNavigationView.setSelectedItemId(R.id.dashboard);
    }

    Dashboard dashboardFragment = new Dashboard();
    Expense expenseFragment = new Expense();
    Income incomeFragment = new Income();

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();  // Get the selected item's ID

        if (itemId == R.id.income) {
            getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, incomeFragment).commit();
            return true;
        } else if (itemId == R.id.dashboard) {
            getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, dashboardFragment).commit();
            return true;
        } else if (itemId == R.id.expense) {
            getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, expenseFragment).commit();
            return true;
        }
        return false;
    }
}

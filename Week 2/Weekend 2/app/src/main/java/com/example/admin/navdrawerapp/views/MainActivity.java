package com.example.admin.navdrawerapp.views;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.admin.navdrawerapp.R;

public class MainActivity extends DrawerActivity {

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle drawerToggle;
    private NavigationView navView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        drawerLayout = findViewById(R.id.activity_main);
//        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout,R.string.Open, R.string.Close);
//        drawerLayout.addDrawerListener(drawerToggle);
//        drawerToggle.syncState();
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        navView = findViewById(R.id.navView);
//        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
//            @Override
//            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//                int id = item.getItemId();
//                Intent intent;
//                switch(id)
//                {
//                    case R.id.factorial:
//                        intent = new Intent(getApplicationContext(), FactorialActivity.class);
//                        startActivity(intent);
//                        return true;
//                    case R.id.other:
//                        intent = new Intent(getApplicationContext(), OtherActivity.class);
//                        startActivity(intent);
//                        return true;
//                    case R.id.mycart:
//                        Toast.makeText(MainActivity.this, "My Cart",Toast.LENGTH_SHORT).show();
//                    default:
//                        return true;
//                }
//            }
//        });


    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//
//        if(drawerToggle.onOptionsItemSelected(item))
//            return true;
//
//        return super.onOptionsItemSelected(item);
//    }
}

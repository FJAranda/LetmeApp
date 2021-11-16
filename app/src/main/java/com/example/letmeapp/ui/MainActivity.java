package com.example.letmeapp.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;
import android.util.Log;

import com.example.letmeapp.R;
import com.example.letmeapp.databinding.ActivityMainBinding;
import com.example.letmeapp.ui.dashboard.DashboardFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {
    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;
    private NavController navController;
    private final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarMain.toolbar);

        navController = Navigation.findNavController(this, R.id.nav_host_fragment);

        binding.appBarMain.fab.setOnClickListener( v  ->{
            navController.navigate(R.id.action_dashboardFragment_to_objectFragment);
        });

        DrawerLayout drawerLayout = binding.drawerLayout;
        NavigationView  navigationView = binding.navView;

        navigationView.getHeaderView(0).findViewById(R.id.ivUserNavDrawer).setOnClickListener(v ->{
            navController.navigate(R.id.action_dashboardFragment_to_userFragment);
        });

        appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).setOpenableLayout(drawerLayout).build();
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }

    public FloatingActionButton getFab(){
        return binding.appBarMain.fab;
    }
}
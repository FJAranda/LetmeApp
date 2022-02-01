package com.example.letmeapp.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.letmeapp.R;
import com.example.letmeapp.databinding.ActivityMainBinding;
import com.example.letmeapp.model.User;
import com.example.letmeapp.ui.dashboard.DashboardFragment;
import com.example.letmeapp.ui.login.LoginActivity;
import com.example.letmeapp.utils.MyUtils;
import com.facebook.login.LoginManager;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.squareup.picasso.Picasso;

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

        setDrawerLayout();

        getUserSharedPreferences();

    }

    @Override
    protected void onStop() {
        super.onStop();
        //TODO: SOLUCIONAR ERROR CON LOGOUT Y LOGIN CONSECUTIVOS DE FACEBOOK Y FIREBASE
        //finish();
    }

    private void getUserSharedPreferences() {
        User user = MyUtils.getUserData(this);
        if (user.isCompleted()){
            setUserData(user);
        }else{
            Snackbar.make(binding.getRoot(), getString(R.string.strCompleteUser), BaseTransientBottomBar.LENGTH_LONG).show();
        }
    }


    private void setUserData(User user) {
        ImageView  ivUserImage = binding.navView.getHeaderView(0).findViewById(R.id.ivUserNavDrawer);
        try {
            Picasso.get()
                    .load(user.getImage())
                    .placeholder(R.drawable.letmeapp)
                    .error(R.drawable.letmeapp)
                    .into(ivUserImage);
        }catch (Exception e){
            //Si algo falla picasso pone la imagen de error.
        }
        TextView tvTitle = binding.navView.getHeaderView(0).findViewById(R.id.tvTitleNavDrawer);
        tvTitle.setText(user.getName());
        TextView tvSubtitle = binding.navView.getHeaderView(0).findViewById(R.id.tvSubtitleNavDrawer);
        tvSubtitle.setText(user.getUsername());
    }

    private void setDrawerLayout() {
        navController = Navigation.findNavController(this, R.id.nav_host_fragment);

        DrawerLayout drawerLayout = binding.drawerLayout;
        NavigationView  navigationView = binding.navView;
        navigationView.setItemIconTintList(null);

        navigationView.getHeaderView(0).findViewById(R.id.ivUserNavDrawer).setOnClickListener(v ->{
            navController.navigate(R.id.action_dashboardFragment_to_userFragment);
            drawerLayout.closeDrawer(GravityCompat.START);
        });

        navigationView.getMenu().findItem(R.id.item_logout).setOnMenuItemClickListener( v -> {
            FirebaseAuth.getInstance().signOut();
            GoogleSignInOptions googleSignInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                    .requestIdToken(getString(R.string.requestIdTokenWeb))
                    .requestEmail()
                    .build();
            GoogleSignInClient googleSignInClient = GoogleSignIn.getClient(this, googleSignInOptions);
            googleSignInClient.signOut();
            LoginManager.getInstance().logOut();
            PreferenceManager.getDefaultSharedPreferences(this).edit().clear().apply();
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
            return true;
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
}
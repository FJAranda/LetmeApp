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

import com.example.letmeapp.R;
import com.example.letmeapp.databinding.ActivityMainBinding;
import com.example.letmeapp.model.User;
import com.example.letmeapp.ui.dashboard.DashboardFragment;
import com.example.letmeapp.ui.login.LoginActivity;
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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity {
    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;
    private NavController navController;
    private final String TAG = "MainActivity";
    private FirebaseFirestore firestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarMain.toolbar);

        setDrawerLayout();

        //TODO: GET USER DATA FROM DATABASE USING EMAIL SAVED IN PREFERENCES
        //GET USER DATA FROM SHAREDPREFENRENCES -> IF NOT EXIST -> GETUSERDATAFROMFIRESTORE -> IF NOT EXIST -> GO TO USERFRAGMENT TO SAVE USERDATA
        //                                      -> IF EXIST -> SET IN NAVIGATION DRAWER
        //Comprobar si los datos del usuario estan en sharedPreferences
        User user = getUserData();
        if (user.isCompleted()){
            //Setear los datos del usuario
            setUserData(user);
            //Si no, comprobar si estan en firestore
        }else{
            //Si no estan en firestore, ir a userfragment para que se introduzcan los datos faltantes
        }

        Log.d("SPM", PreferenceManager.getDefaultSharedPreferences(this).getString(User.EMAIL_TAG, null));
    }

    private User getUserData(){
        User user = new User(
                PreferenceManager.getDefaultSharedPreferences(this).getString(User.USERNAME_TAG, ""),
                PreferenceManager.getDefaultSharedPreferences(this).getString(User.NAME_TAG, ""),
                PreferenceManager.getDefaultSharedPreferences(this).getString(User.EMAIL_TAG, ""),
                PreferenceManager.getDefaultSharedPreferences(this).getString(User.IMAGE_TAG, "")
        );
        return user;
    }

    private void setUserData(User user) {
        ImageView  ivUserImage = binding.navView.getHeaderView(0).findViewById(R.id.ivUserNavDrawer);
        /*Picasso.get()
                .load(user.getImage())
                .placeholder(R.drawable.letmeapp)
                .error(R.drawable.letmeapp)
                .into(ivUserImage);*/
        /*firestore.collection(User.USER_COLLECTION)
                .document(PreferenceManager.getDefaultSharedPreferences(this).getString(User.EMAIL_TAG, null))
                .get()
                .addOnSuccessListener( v -> {

                })
                .addOnFailureListener(v -> {

                });*/
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
            LoginManager.getInstance().logOut();
            FirebaseAuth.getInstance().signOut();
            GoogleSignInOptions googleSignInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                    .requestIdToken(getString(R.string.requestIdTokenWeb))
                    .requestEmail()
                    .build();
            GoogleSignInClient googleSignInClient = GoogleSignIn.getClient(this, googleSignInOptions);
            googleSignInClient.signOut();
            LoginManager.getInstance().logOut();
            PreferenceManager.getDefaultSharedPreferences(this).edit().clear().apply();
            startActivity(new Intent(MainActivity.this, LoginActivity.class));
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
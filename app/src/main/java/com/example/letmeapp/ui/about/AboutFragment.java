package com.example.letmeapp.ui.about;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.letmeapp.R;
import com.vansuita.materialabout.builder.AboutBuilder;
import com.vansuita.materialabout.views.AboutView;

public class AboutFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        AboutView view = AboutBuilder.with(getActivity())
                .setPhoto(R.mipmap.profile_picture)
                .setCover(R.mipmap.profile_cover)
                .setName(getString(R.string.strDeveloperName))
                .setSubTitle(getString(R.string.strDeveloperJob))
                .setBrief(getString(R.string.strDeveloperInfo))
                .setAppIcon(R.mipmap.ic_launcher)
                .setAppName(R.string.app_name)
                //.addGooglePlayStoreLink("8002078663318221363")
                .addGitHubLink("fjaranda")
                //.addFacebookLink("user")
                //.addFiveStarsAction()
                .setVersionNameAsAppSubTitle()
                .addShareAction(R.string.app_name)
                .setWrapScrollView(true)
                .setLinksAnimated(true)
                .setShowAsCard(false)
                .build();

        return view;
    }
}
package com.induce.vaios.inducedev;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import me.relex.circleindicator.CircleIndicator;

/**
 * Created by Vaios on 27/01/2016.
 */
public class firstpageimagefragment extends Fragment implements View.OnClickListener {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.fragment_screen_slide_page, container, false);




        ImageView image = (ImageView) rootView.findViewById(R.id.imagelist);
        image.setOnClickListener(this);
        image.setImageResource(R.drawable.com_facebook_profile_picture_blank_portrait);
        return rootView;
    }


    @Override
    public void onClick(View v) {

        System.out.println("clicked!");
        Toast.makeText(getActivity(), "this is my Toast message!!! =)",
                Toast.LENGTH_LONG).show();

    }
}


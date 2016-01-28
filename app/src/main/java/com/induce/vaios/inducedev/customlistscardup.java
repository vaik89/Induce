package com.induce.vaios.inducedev;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.parse.ParseObject;

import java.util.ArrayList;

import it.gmariotti.cardslib.library.cards.material.MaterialLargeImageCard;
import it.gmariotti.cardslib.library.internal.Card;
import it.gmariotti.cardslib.library.recyclerview.internal.CardArrayRecyclerViewAdapter;

/**
 * Created by Vaios on 28/01/2016.
 */
public class customlistscardup extends MaterialLargeImageCard implements View.OnClickListener {
    private Context cnt;
    private static final String LOG_TAG = customlistscardup.class.getSimpleName();
    public ImageView imageView;
    private CardArrayRecyclerViewAdapter ad;
    public Boolean clicked;
    //  TextView t1;
    private ParseObject parseObject;
    public Drawable dra;
    ArrayList<String> list = new ArrayList<>();
    int color;

    public customlistscardup(Context context, int data, CardArrayRecyclerViewAdapter adapt) {
        super(context, R.layout.samplecardup);
        this.color = data;
        this.cnt = context;
        this.ad = adapt;


    }


    @Override
    public void setupInnerViewElements(ViewGroup parent, View view) {
        super.setupInnerViewElements(parent, view);

        System.out.println("Inside setup inner view for card ");
        imageView = (ImageView) view.findViewById(R.id.card_thumbnail_image);
        imageView.setImageResource(color);
       // imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        this.setOnClickListener(new OnCardClickListener() {
            @Override
            public void onClick(Card card, View view) {
                System.out.println("clicked card"+ getId());
            }
        });


    }




    @Override
    public void onClick(View v) {

        System.out.println("clicked card"+ getId());

    }
}

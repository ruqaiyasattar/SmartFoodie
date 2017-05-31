package com.example.dell.mycook.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.DefaultSliderView;
import com.example.dell.mycook.Activities.VideosListActivity;
import com.example.dell.mycook.R;
import com.example.dell.mycook.models.RecyclerItemClickListener;
import com.example.dell.mycook.models.Item;
import com.example.dell.mycook.models.Rec_Adapter;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class home extends BaseFragment {

    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private List<Item> recList;
    private Rec_Adapter recAdapter;

    String[] names = {"Choclate", "Fish", "Chicken", "mix Vegetables", "Pizza's", "Zinger's",
            "Breake  Fast", "Lunch", "Dinner", "Cookies", "Juices", "Yogurt Tips", "Delicious Rice", "Cooking tips",
            "Masala tips","Food for kid's"};

    String[] pics = {
            "http://ghk.h-cdn.co/assets/15/12/1426719496-chocolate-cake.jpg",
            "http://st.depositphotos.com/1003814/3347/i/950/depositphotos_33471443-stock-photo-fish-dish-roasted-fish-and.jpg",
            "http://img.taste.com.au/JoEy73f4/w720-h480-cfill-q80/taste/2016/11/paprika-chicken-with-quinoa-tabbouleh-83041-1.jpeg",
            "http://static.framar.bg/snimki/lubopitno//vareni-zelenchuci.jpg",
            "http://www.esecatering.com/fotos_pizza/pizzas-para-eventos.jpg",
            "http://burgerburgerfbg.com/wp-content/uploads/2015/01/Henny.jpg",
            "http://www.nutritionaldieting.com/wp-content/uploads/2015/11/7-Quick-and-Healthy-Breakfast-Food-Ideas-That-Save-You-Time.jpg",
            "http://entouraaj.wpengine.netdna-cdn.com/wp-content/uploads/2012/10/Pasta-Image.jpg",
            "https://shk-images.s3.amazonaws.com/wp-content/uploads/uploads/files/10353/large/Chicken%20Dinner%20On%20MyPlate%20for%20Kids.jpg",
            "https://www.immaculatebaking.com/wp-content/uploads/ibc-cookies-hero-605x365.png",
            "http://benefitsofjuicing.net/wp-content/uploads/A-Comprehensive-Guide-to-Juicing-for-Healthy-Living-The-Real-Benefits-of-Juicing-1.jpg",
            "http://www.yogurtinnutrition.com/wp-content/uploads/2016/06/yogurt-nutrient-snack-375x195.jpg",
            "http://blog.giallozafferano.it/passionecooking/wp-content/uploads/2015/08/insalata-di-riso-tonno-uova-cipolline-800.jpg",
            "https://www.tarladalal.com/tips/cooking-tips_big.jpg",
            "https://i.ytimg.com/vi/93kxJ81osiE/hqdefault.jpg",
            "http://cdn.24.co.za/files/Cms/General/d/4077/e4eb663eeac848869ac4a445e95c3e54.jpg"
    };

    ArrayList<String> banners = new ArrayList<>();

    public home() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        setContentView(inflater.inflate(R.layout.mymainpage, container, false));
        SliderLayout sliderShow = (SliderLayout) findViewById(R.id.slider);
        banners.add("http://www.discovermorningtonpeninsula.com.au/food/images/healthy-food.jpg");
        banners.add("http://www.healthnavigator.org.nz/media/1002/healthy-eating.jpg");
        banners.add("https://expresandote.com/images/Healthy-Eating-Food-Healthy-Diet-733x440.jpg");
        banners.add("http://www.yogurtinnutrition.com/wp-content/uploads/2016/06/yogurt-nutrient-snack-375x195.jpg");
        banners.add("http://www.maltonfoodbank.ca/wp-content/uploads/2016/07/fam-food.jpg");
        banners.add("http://img.webmd.com/dtmcms/live/webmd/consumer_assets/site_images/sponsored_programs/r_fit/jr/content_images/fit_parentarticles_10waystogetkidstoeathealthyfood_w1.jpg");
        banners.add("http://i.cdn.turner.com/cnn/2011/TRAVEL/05/26/chocolate.tourism.europe/t1larg.chocolate.apf.gi.jpg");
        banners.add("https://i.ytimg.com/vi/93kxJ81osiE/hqdefault.jpg");

        for(int bi = 0; bi<banners.size();bi++){

            DefaultSliderView textSliderView = new DefaultSliderView(getContext());
            textSliderView.image(banners.get(bi));
            sliderShow.addSlider(textSliderView);
        }

        mRecyclerView = (RecyclerView) findViewById(R.id.myrecyclerforrecipes);
        if (mRecyclerView != null) {
            mRecyclerView.setHasFixedSize(true);
        }

        mLayoutManager = new GridLayoutManager(getContext(), 2);
        mRecyclerView.setLayoutManager(mLayoutManager);


        //intializing an arraylist called list
        recList = new ArrayList<>();
        //adding data from arrays to list
        for (int i = 0; i < names.length; i++) {
            Item item = new Item(names[i], pics[i]);
            recList.add(item);
        }
        //initializing adapter add calling
        recAdapter = new Rec_Adapter(recList,pics);
        mRecyclerView.setAdapter(recAdapter);
        recAdapter.notifyDataSetChanged();

        mRecyclerView.addOnItemTouchListener
                (new RecyclerItemClickListener(getContext(), new RecyclerItemClickListener
                .OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent i = new Intent(getActivity(), VideosListActivity.class);
                i.putExtra("categoryId",position);
                startActivity(i);
            }
        }));
        return getContentView();
    }

}

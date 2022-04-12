package com.example.pwbaseprova;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;

import com.example.pwbaseprova.piatti.Piatto;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements FragmentHome.ClickListener {

    private ViewPager2 viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        setContentView(R.layout.activity_main);
        viewPager = findViewById(R.id.pager);
        buildTabLayout();
    }

    private void buildTabLayout() {
        TabLayout tabLayout = findViewById(R.id.tab_layout);

        CustomPagerAdapter adapter = new CustomPagerAdapter(this, 4);
        //PER LO SCROLL VERTICALE:
        //viewPager.setOrientation(ViewPager2.ORIENTATION_VERTICAL);

        //EFFETTI VISIVI TRANSIZIONI TRA FRAGMENT:
        viewPager.setPageTransformer(new DepthPageTransformer());
        //viewPager.setPageTransformer(new ZoomOutPageTransformer());
        viewPager.setAdapter(adapter);
/*
        ArrayList<String> nomiTab = new ArrayList<>();
        nomiTab.add("Home");
        nomiTab.add("Menù");
        nomiTab.add("Itinerari");
        nomiTab.add("Contatti");*/

        ArrayList<Integer> iconeTab = new ArrayList<>();

        iconeTab.add(R.drawable.ic_baseline_home_48);
        iconeTab.add(R.drawable.ic_baseline_local_dining_48);
        iconeTab.add(R.drawable.ic_baseline_nature_people_48);
        iconeTab.add(R.drawable.ic_baseline_local_phone_48);


        //QUI CREO ANCHE I TAB
        //NON CE BISOGNO DI CREALI TU A MANO QUINDI NEL FILE XML(ACTIVITY MAIN) NEL TABLAYOUT PUOI EVITARE DI METTERE I TABITEM, PERO RICORDATI DI METTERE TABLAYOUT COME MATCH PARENT
        new TabLayoutMediator(tabLayout, viewPager,
                (tab, position) -> {
                    tab.setIcon(iconeTab.get(position));
                    //tab.setText(nomiTab.get(position));
                }).attach();
        //tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);


    }

    @Override
    public void onButtonClick(int id) {
       viewPager.setCurrentItem(id);
    }


    private class ZoomOutPageTransformer implements ViewPager2.PageTransformer {
        private static final float MIN_SCALE = 0.85f;
        private static final float MIN_ALPHA = 0.5f;

        public void transformPage(View view, float position) {
            int pageWidth = view.getWidth();
            int pageHeight = view.getHeight();

            if (position < -1) { // [-Infinity,-1)
                // This page is way off-screen to the left.
                view.setAlpha(0f);

            } else if (position <= 1) { // [-1,1]
                // Modify the default slide transition to shrink the page as well
                float scaleFactor = Math.max(MIN_SCALE, 1 - Math.abs(position));
                float vertMargin = pageHeight * (1 - scaleFactor) / 2;
                float horzMargin = pageWidth * (1 - scaleFactor) / 2;
                if (position < 0) {
                    view.setTranslationX(horzMargin - vertMargin / 2);
                } else {
                    view.setTranslationX(-horzMargin + vertMargin / 2);
                }

                // Scale the page down (between MIN_SCALE and 1)
                view.setScaleX(scaleFactor);
                view.setScaleY(scaleFactor);

                // Fade the page relative to its size.
                view.setAlpha(MIN_ALPHA +
                        (scaleFactor - MIN_SCALE) /
                                (1 - MIN_SCALE) * (1 - MIN_ALPHA));

            } else { // (1,+Infinity]
                // This page is way off-screen to the right.
                view.setAlpha(0f);
            }
        }
    }

    private class DepthPageTransformer implements ViewPager2.PageTransformer {
        private static final float MIN_SCALE = 0.75f;

        public void transformPage(View view, float position) {
            int pageWidth = view.getWidth();

            if (position < -1) { // [-Infinity,-1)
                // This page is way off-screen to the left.
                view.setAlpha(0f);

            } else if (position <= 0) { // [-1,0]
                // Use the default slide transition when moving to the left page
                view.setAlpha(1f);
                view.setTranslationX(0f);
                view.setTranslationZ(0f);
                view.setScaleX(1f);
                view.setScaleY(1f);

            } else if (position <= 1) { // (0,1]
                // Fade the page out.
                view.setAlpha(1 - position);

                // Counteract the default slide transition
                view.setTranslationX(pageWidth * -position);
                // Move it behind the left page
                view.setTranslationZ(-1f);

                // Scale the page down (between MIN_SCALE and 1)
                float scaleFactor = MIN_SCALE
                        + (1 - MIN_SCALE) * (1 - Math.abs(position));
                view.setScaleX(scaleFactor);
                view.setScaleY(scaleFactor);

            } else { // (1,+Infinity]
                // This page is way off-screen to the right.
                view.setAlpha(0f);
            }
        }
    }
}
package com.example.android.bookmyvisitzmedios.Homescreen;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.android.bookmyvisitzmedios.Category_detail_activity;
import com.example.android.bookmyvisitzmedios.Homescreen.adapter.CategoryAdapter;
import com.example.android.bookmyvisitzmedios.Homescreen.adapter.SlidePageAdapter;
import com.example.android.bookmyvisitzmedios.Homescreen.model.Category;
import com.example.android.bookmyvisitzmedios.Homescreen.model.Slide;
import com.example.android.bookmyvisitzmedios.R;
import com.example.android.bookmyvisitzmedios.Utils.DataSource;
import com.example.android.bookmyvisitzmedios.Utils.OnCategoryclickListner;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class HomeScreen extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener , OnCategoryclickListner {

     TextView username , useremail , viewall;
     FirebaseAuth mauth;
     ImageView imageView;
    ViewPager viewPager;
    List<Slide> mslide;
    SlidePageAdapter madapter;
    TabLayout indicator;
    RecyclerView rv_category , rv_populargym;
    private View navHeader;
    CategoryAdapter adapter;

    List<Category> cat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        mslide = new ArrayList<>();
        cat = new ArrayList<>();


        iniViews();

        iniNavigationDrawer();

        iniGetUserCred();

        iniSlider();

        iniCategory();

        iniPopularGym();


    }



    private void iniPopularGym() {
        CategoryAdapter populargym = new CategoryAdapter(this , DataSource.getPopulargym() , this);
        rv_populargym.setAdapter(populargym);
        rv_populargym.setLayoutManager(new LinearLayoutManager(this , LinearLayoutManager.HORIZONTAL , false));
    }

    private void iniViews() {
        viewPager = findViewById(R.id.slidepager);
        indicator = findViewById(R.id.indicator);
        rv_category = findViewById(R.id.Rv_popularmovie);
        rv_populargym = findViewById(R.id.Rv_populargym);
        viewall = findViewById(R.id.viewallbtn);

//
    }

    private void iniCategory() {
        adapter = new CategoryAdapter(HomeScreen.this , DataSource.getCategory() , this);
        rv_category.setAdapter(adapter);
        rv_category.setLayoutManager(new GridLayoutManager(this , 3 ));


    }

    private void iniSlider() {
        //setup timmer
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new Slidertime(), 4000, 6000);

        mslide.add(new Slide(R.drawable.slideimg1, "Delhi"));
        mslide.add(new Slide(R.drawable.slideimg2, "Mumbai"));
        mslide.add(new Slide(R.drawable.slideimg3, "Indore"));

        madapter = new SlidePageAdapter(this, mslide);
        viewPager.setAdapter(madapter);
        indicator.setupWithViewPager(viewPager, true);
    }

    private void iniGetUserCred() {
        FirebaseUser user = mauth.getCurrentUser();

        username.setText(user.getDisplayName());
        useremail.setText(user.getEmail());
        Glide.with(this)
                .load(user.getPhotoUrl())
                .into(imageView);
    }

    private void iniNavigationDrawer() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.empty, R.string.empty);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        // for hamburger icon color
        toggle.getDrawerArrowDrawable().setColor(getResources().getColor(R.color.white));

        // display nagovation in nav header

        navHeader = navigationView.getHeaderView(0);
        //fr displaying activity into main screen
        mauth = FirebaseAuth.getInstance();
        username = navHeader.findViewById(R.id.username);
        useremail = navHeader.findViewById(R.id.useremail);
        imageView = navHeader.findViewById(R.id.userimage);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.navigation_drawer, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_tools) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onCategoryClick(Category category, ImageView imageView) {
        Intent intent = new Intent(this , Category_detail_activity.class);
        intent.putExtra("title" , category.getTitle());
        intent.putExtra("rating" , category.getRating());
        intent.putExtra("image" , category.getImages());
        intent.putExtra("location" , category.getLocation());
        intent.putExtra("time" , category.getTime());
        startActivity(intent);

    }
    public void OnclickViewAll()
    {
        viewall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                cat.add(new Category("new " , R.drawable.gymfive));
                cat.add(new Category("new " , R.drawable.gymfive));
                cat.add(new Category("new " , R.drawable.gymfive));
                adapter.notifyDataSetChanged();
            }

        });
    }

    // for auto incrementation of view pager
    class Slidertime extends TimerTask {

        @Override
        public void run() {
            HomeScreen.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (viewPager.getCurrentItem() < mslide.size() - 1) {
                        viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
                    } else {
                        viewPager.setCurrentItem(0);
                    }
                }
            });
        }
    }
}

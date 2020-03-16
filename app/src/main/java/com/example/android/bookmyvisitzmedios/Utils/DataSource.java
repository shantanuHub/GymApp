package com.example.android.bookmyvisitzmedios.Utils;

import com.example.android.bookmyvisitzmedios.Homescreen.model.Category;
import com.example.android.bookmyvisitzmedios.R;

import java.util.ArrayList;
import java.util.List;

public class DataSource {


    public static List<Category> getCategory()
    {

        List<Category> cat  = new ArrayList<>();
        cat.add(new Category("Meditation" , R.drawable.meditation ));
        cat.add(new Category("Breathe" , R.drawable.breadth));
        cat.add(new Category("Workout" , R.drawable.workout));
        cat.add(new Category("Cardio" , R.drawable.cardio));
        cat.add(new Category("Yoga" , R.drawable.yoga));
        cat.add(new Category("Calm" , R.drawable.breadth));


        return cat;
    }

    public static List<Category> getPopulargym()
    {

        List<Category> cat  = new ArrayList<>();
        cat.add(new Category("gurugram" , R.drawable.gym));
        cat.add(new Category("delhi" , R.drawable.gymtwo));
        cat.add(new Category("indore" , R.drawable.gymthree));
        cat.add(new Category("mumbai" , R.drawable.gymfour));
        cat.add(new Category("agra" , R.drawable.gymfive));
        cat.add(new Category("gujrat" , R.drawable.gym));


        return cat;
    }
}

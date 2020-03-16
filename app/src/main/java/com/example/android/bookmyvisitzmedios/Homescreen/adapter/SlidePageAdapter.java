package com.example.android.bookmyvisitzmedios.Homescreen.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.bookmyvisitzmedios.Homescreen.model.Slide;
import com.example.android.bookmyvisitzmedios.R;

import java.util.List;

public class SlidePageAdapter extends PagerAdapter {

    Context mcontext;
    List<Slide> mslide;

    public SlidePageAdapter(Context mcontext, List<Slide> mslide) {
        this.mcontext = mcontext;
        this.mslide = mslide;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        LayoutInflater inflater = (LayoutInflater) mcontext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View slideLayout = inflater.inflate(R.layout.slide_img , null);

        ImageView slideimg = slideLayout.findViewById(R.id.imageView);
        TextView slidetext = slideLayout.findViewById(R.id.slidetext);

        slideimg.setImageResource(mslide.get(position).getImg());
        slidetext.setText(mslide.get(position).getText());

        container.addView(slideLayout);

        return slideLayout;
        }

    @Override
    public int getCount() {
        return mslide.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
       return  view == o;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}

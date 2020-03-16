package com.example.android.bookmyvisitzmedios.Homescreen.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.android.bookmyvisitzmedios.Category_detail_activity;
import com.example.android.bookmyvisitzmedios.Homescreen.model.Category;
import com.example.android.bookmyvisitzmedios.R;
import com.example.android.bookmyvisitzmedios.Utils.OnCategoryclickListner;

import java.util.List;

import butterknife.OnClick;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.MyViewHolder> {

    private Context context;
    private List<Category> mlist;
    private OnCategoryclickListner listner;

    public CategoryAdapter(Context context, List<Category> mlist , OnCategoryclickListner onItemClickListener) {
        this.context = context;
        this.mlist = mlist;
        this.listner = onItemClickListener;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.rvitem_list ,viewGroup , false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {

        Category catgory = mlist.get(i);

        myViewHolder.text.setText(catgory.getTitle());
        myViewHolder.image.setImageResource(catgory.getImages());

        myViewHolder.layout.setVisibility(View.GONE);

        //if the position is equals to the item position which is to be expanded

            Animation slideDown = AnimationUtils.loadAnimation(context, R.anim.animate_view);

            //toggling visibility
            myViewHolder.layout.setVisibility(View.VISIBLE);

            //adding sliding effect
            myViewHolder.layout.startAnimation(slideDown);

    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView image;
        TextView text;
        ConstraintLayout layout;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.rv_img);
            text = itemView.findViewById(R.id.rv_textview);
            layout = itemView.findViewById(R.id.layout);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listner.onCategoryClick(mlist.get(getAdapterPosition()), image);
                }
            });

        }
    }
}

package com.example.bnm20.myapplication;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;

import static com.example.bnm20.myapplication.MainActivity.deviceHeight;
import static com.example.bnm20.myapplication.MainActivity.deviceWidth;
import static com.example.bnm20.myapplication.MainActivity.statusheight;

public class TabFragment1 extends Fragment {

    ImageView imageView;


    public TabFragment1(){

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.tabfragment1, container, false);

        imageView = (ImageView) v.findViewById(R.id.main_content1_image);
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) imageView.getLayoutParams();
        params.width = deviceWidth;
        params.height = deviceHeight - statusheight;
        imageView.setLayoutParams(params);

        Drawable alpha = imageView.getDrawable();
        alpha.setAlpha(99);

        return v;
    }
}

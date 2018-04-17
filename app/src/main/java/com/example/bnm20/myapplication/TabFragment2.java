package com.example.bnm20.myapplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import static com.example.bnm20.myapplication.MainActivity.deviceHeight;
import static com.example.bnm20.myapplication.MainActivity.deviceWidth;
import static com.example.bnm20.myapplication.MainActivity.statusheight;

public class TabFragment2 extends Fragment {
    ImageView imageView;
    public TabFragment2(){

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.tabfragment2, container, false);


        //Drawable alpha = imageView.getDrawable();
        //alpha.setAlpha(99);

        return v;
    }


}

package com.example.bnm20.myapplication;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.TextViewCompat;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import static com.example.bnm20.myapplication.MainActivity.deviceHeight;
import static com.example.bnm20.myapplication.MainActivity.deviceWidth;
import static com.example.bnm20.myapplication.MainActivity.statusheight;

public class TabFragment1 extends Fragment {

    ImageView weatherImage;
    TextView temper;
    TextView high_temper;
    TextView low_temper;
    TextView coment;
    ImageView high_temp_Image;
    ImageView low_temp_Image;

    double[] x;


    SwipeRefreshLayout refreshLayout = null;

    public TabFragment1(){

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.tabfragment1, container, false);
        refreshLayout = (SwipeRefreshLayout) v.findViewById(R.id.swipe_refresh);

        x = ((AirCareApplication)this.getActivity().getApplication()).getTemp();


        ///////////////

        ///////////////

        weatherImage = (ImageView) v.findViewById(R.id.fragment1_weatherimage);
        weatherImage.setImageResource(R.drawable.icon_weather_map09);
        LinearLayout.LayoutParams fragment1_weatherimage_params = (LinearLayout.LayoutParams) weatherImage.getLayoutParams();
        fragment1_weatherimage_params.height = deviceWidth/4;
        fragment1_weatherimage_params.width = deviceWidth/4;
        weatherImage.setLayoutParams(fragment1_weatherimage_params);


        temper = (TextView) v.findViewById(R.id.fragment1_temper);
        temper.setText(x[0] + "°");
        temper.setTextSize(TypedValue.COMPLEX_UNIT_DIP,convertPixelsToDp(deviceWidth/5,getContext()));
        RelativeLayout.LayoutParams fragment1_temper_params = (RelativeLayout.LayoutParams) temper.getLayoutParams();
        fragment1_temper_params.height = deviceWidth*14 /64;
        fragment1_temper_params.width = deviceWidth/3;
        temper.setLayoutParams(fragment1_temper_params);

        high_temp_Image = (ImageView) v.findViewById(R.id.fragment1_high_temp_image);
        LinearLayout.LayoutParams fragment1_high_temp_Image_params = (LinearLayout.LayoutParams) high_temp_Image.getLayoutParams();
        fragment1_high_temp_Image_params.height = deviceWidth/17;
        fragment1_high_temp_Image_params.width = deviceWidth/17;
        high_temp_Image.setLayoutParams(fragment1_high_temp_Image_params);

        low_temp_Image = (ImageView) v.findViewById(R.id.fragment1_low_temp_image);
        LinearLayout.LayoutParams fragment1_low_temp_Image_params = (LinearLayout.LayoutParams) low_temp_Image.getLayoutParams();
        fragment1_low_temp_Image_params.height = deviceWidth/17;
        fragment1_low_temp_Image_params.width = deviceWidth/17;
        low_temp_Image.setLayoutParams(fragment1_low_temp_Image_params);

        high_temper = (TextView) v.findViewById(R.id.fragment1_high_temp);
        high_temper.setText("23" + "°");
        high_temper.setTextSize(TypedValue.COMPLEX_UNIT_DIP,convertPixelsToDp(deviceWidth/22,getContext()));
        LinearLayout.LayoutParams fragment1_high_temper = (LinearLayout.LayoutParams) high_temper.getLayoutParams();
        fragment1_high_temper.height = deviceWidth/14;
        fragment1_high_temper.width = deviceWidth/14;
        high_temper.setLayoutParams(fragment1_high_temper);

        low_temper = (TextView) v.findViewById(R.id.fragment1_low_temp);
        low_temper.setText("23" + "°");
        low_temper.setTextSize(TypedValue.COMPLEX_UNIT_DIP,convertPixelsToDp(deviceWidth/22,getContext()));
        LinearLayout.LayoutParams fragment1_low_temper = (LinearLayout.LayoutParams) low_temper.getLayoutParams();
        fragment1_low_temper.height = deviceWidth/14;
        fragment1_low_temper.width = deviceWidth/14;
        low_temper.setLayoutParams(fragment1_low_temper);

        coment = (TextView) v.findViewById(R.id.fragment1_coment);
        coment.setText("구름 많음");
        coment.setTextSize(TypedValue.COMPLEX_UNIT_DIP,convertPixelsToDp(deviceWidth/16,getContext()));
        LinearLayout.LayoutParams fragment1_coment_params = (LinearLayout.LayoutParams) coment.getLayoutParams();
        fragment1_coment_params.height = deviceWidth/6;
        coment.setLayoutParams(fragment1_coment_params);

        return v;
    }

    //dp를 px로 변환 (dp를 입력받아 px을 리턴)
    public static float convertDpToPixel(float dp, Context context){
        Resources resources = context.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        float px = dp * ((float)metrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT);
        return px;
    }

    //px을 dp로 변환 (px을 입력받아 dp를 리턴)
    public static float convertPixelsToDp(float px, Context context){
        Resources resources = context.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        float dp = px / ((float)metrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT);
        return dp;
    }
}

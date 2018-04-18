package com.example.bnm20.myapplication;

import android.app.Application;

public class AirCareApplication extends Application{
    private double[] temp;


    public double[] getTemp(){
        return temp;
    }

    public void setTemp(double[] x){
        this.temp = x;
    }


}

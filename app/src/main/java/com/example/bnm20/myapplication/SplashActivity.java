package com.example.bnm20.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
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

public class SplashActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try{
            ParsingThread();
        }catch (Exception e){

        }

        startActivity(new Intent(this, MainActivity.class));
        finish();
    }


    private void ParsingThread() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                // TODO Auto-generated method stub
                final ArrayList<Weather> nowWeather = NowWeaterParsing("http://newsky2.kma.go.kr/service/SecndSrtpdFrcstInfoService2/ForecastTimeData?serviceKey=T5XaqD0tRmasPbia5AVSw%2F9fE1TcIyYy%2BV1M7uKwv%2B8m6XC%2FZEHLURANv1xKwR25KI%2BM4d9MDU5QKHKcw6lXXA%3D%3D&base_date=20180418&base_time=1100&numOfRows=100&pageSize=1&pageNo=1&startPage=2&nx=89&ny=91&_type=xml");
                final double[] NowTemperatureValues = new double[1]; // 현재온도
                final double[] NowPOPValues = new double[1]; // 현재 1시간 강수량
                final double[] NowSKYValues = new double[1]; // 현재 하늘 상태
                final double[] NowREHValues = new double[1]; // 현재 습도
                final double[] NowLGTValues = new double[1]; // 현재 낙뢰
                final long now = System.currentTimeMillis();
                final Date d = new Date(now);
                final SimpleDateFormat date = new SimpleDateFormat("yyyyMMdd");
                final String formatDate = date.format(d);
                SimpleDateFormat date2 = new SimpleDateFormat("hhmm");
                final String nowtime = date2.format(d);
                Calendar Cnow = Calendar.getInstance();
                Cnow.setTime(d);
                final int isAMorPM = Cnow.get(Calendar.AM_PM);

                NowDataPrint(d, nowWeather, isAMorPM, NowTemperatureValues, NowPOPValues, NowSKYValues, NowREHValues, NowLGTValues);

                Log.d("온도값", ""+NowTemperatureValues[0]);
                Log.d("NowPOPValues", ""+NowPOPValues[0]);
                Log.d("NowSKYValues", ""+NowSKYValues[0]);
                Log.d("NowREHValues", ""+NowREHValues[0]);
                Log.d("NowLGTValues", ""+NowLGTValues[0]);




            }
        }).start();
    }




    private ArrayList<Weather> NowWeaterParsing(String s) {
        ArrayList<Weather> AItem = new ArrayList<Weather>();
        try {
            URL url = new URL(s);
            InputStream is = url.openStream();

            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();  // xml 파싱 try/catch 문 반드시 사용
            XmlPullParser parser = factory.newPullParser();
            parser.setInput(new InputStreamReader(is,"UTF-8")); // 로드한 xml 파일을 파서에 넣음

            Log.i("====parser","loaded!"); // 파싱이 되었는지 확인

            int eventType = parser.getEventType();
            String tagName = "";
            Weather i = null;
            String[] data = null;

            while(eventType != XmlPullParser.END_DOCUMENT){
                switch (eventType)
                {
                    case XmlPullParser.START_TAG :
                        tagName = parser.getName();
                        if( parser.getName().equals("item"))
                        {
                            i = new Weather();
                        }
                        break;
                    case XmlPullParser.END_TAG :
                        if(parser.getName().equals("item"))
                        {
                            AItem.add( i );
                            i = null;
                        }
                        break;

                    case XmlPullParser.TEXT :

                        switch (tagName)
                        {
                            case "baseDate":
                                i.setBaseData(parser.getText());
                                break;
                            case "baseTime" :
                                i.setBaseTime(parser.getText());
                                break;
                            case "category" :
                                i.setCategory(parser.getText());
                                break;
                            case "fcstDate":
                                i.setFcstDate(parser.getText());
                                break;
                            case "fcstTime":
                                i.setFcstTime(parser.getText());
                                break;
                            case "fcstValue":
                                i.setFcstValue(Double.parseDouble(parser.getText()));
                                break;
                            case "nx" :
                                i.setNx(Integer.parseInt(parser.getText()));
                                break;
                            case "ny" :
                                i.setNy(Integer.parseInt(parser.getText()));
                                break;
                        }
                        break;
                }
                eventType = parser.next();
            }
            is.close();
            parser.wait();
        }catch (Exception e)
        {
        }
        return AItem;
    }
    private void NowDataPrint(Date d, ArrayList<Weather> nowWeather, int isAMorPM, double[] nowTemperatureValues, double[] nowPOPValues, double[] nowSKYValues, double[] nowREHValues, double[] nowLGTValues) {
        SimpleDateFormat date2 = new SimpleDateFormat("hhmm");
        String nowtime = date2.format(d);
        Calendar now = Calendar.getInstance();
        now.setTime(d);

        for(int i=0; i<nowWeather.size(); i++)
        {
            switch (nowWeather.get(i).getCategory())
            {
                case "T1H" : // 1시간마다 온도
                    OneHourMeasure(nowtime, i, isAMorPM, nowWeather, nowTemperatureValues);
                    break;
                case "RN1" : // 1시간마다 강수량
                    OneHourMeasure(nowtime, i, isAMorPM, nowWeather, nowPOPValues);
                    break;
                case "SKY" : // 현재 하늘상태
                    OneHourMeasure(nowtime, i, isAMorPM, nowWeather, nowSKYValues);
                    break;
                case "UUU" : // 동서바람
                    break;
                case "VVV" : // 남북바람
                    break;
                case "REH" : // 현재 습도
                    OneHourMeasure(nowtime, i, isAMorPM, nowWeather, nowREHValues);
                    break;
                case "LGT" : // 낙뢰
                    OneHourMeasure(nowtime, i, isAMorPM, nowWeather, nowLGTValues);
                    break;
                case "VEC" :
                    break;
                case "WSD" :
                    break;
            }
        }
    }
    private void OneHourMeasure(String nowtime, int i, int isAMorPM, ArrayList<Weather> nowWeather, double[] Values) {
        if (isAMorPM == Calendar.PM) //오후
        {   if (1200 <= Integer.parseInt(nowtime) && Integer.parseInt(nowtime) <=1259) { // 0 부터 1시반 사이일때
            if (nowWeather.get(i).getFcstTime().equals("1200")) //12시 데이터 출력
                Values[0] = nowWeather.get(i).getFcstValue();
        }else if (100 <= Integer.parseInt(nowtime) && Integer.parseInt(nowtime) <=159) { // 1시 부터 2시 사이일때
            if (nowWeather.get(i).getFcstTime().equals("1300")) //1시 데이터 출력
                Values[0] = nowWeather.get(i).getFcstValue();
        } else if (200 <= Integer.parseInt(nowtime) && Integer.parseInt(nowtime) <=259) { // 2시  부터 3시 사이일때
            if (nowWeather.get(i).getFcstTime().equals("1400")) //2시 데이터 출력
                Values[0] = nowWeather.get(i).getFcstValue();
        }else if (300 <= Integer.parseInt(nowtime) && Integer.parseInt(nowtime) <=359) { //3시 부터 4시 사이일때
            if (nowWeather.get(i).getFcstTime().equals("1500")) //3시 데이터 출력
                Values[0] = nowWeather.get(i).getFcstValue();
        }else if (400 <= Integer.parseInt(nowtime) && Integer.parseInt(nowtime) <=459) { //4시 부터 5시 사이일때
            if (nowWeather.get(i).getFcstTime().equals("1600")) //4시 데이터 출력
                Values[0] = nowWeather.get(i).getFcstValue();
        }else if (500 <= Integer.parseInt(nowtime) && Integer.parseInt(nowtime) <=559) { //5시 부터 6시 사이일때
            if (nowWeather.get(i).getFcstTime().equals("1700")) //5시 데이터 출력
                Values[0] = nowWeather.get(i).getFcstValue();
        }else if (600 <= Integer.parseInt(nowtime) && Integer.parseInt(nowtime) <=659) { //6시 부터 7시 사이일때
            if (nowWeather.get(i).getFcstTime().equals("1800")) //6시 데이터 출력
                Values[0] = nowWeather.get(i).getFcstValue();
        }else if (700 <= Integer.parseInt(nowtime) && Integer.parseInt(nowtime) <=759) { //6시 부터 7시 사이일때
            if (nowWeather.get(i).getFcstTime().equals("1900")) //7시 데이터 출력
                Values[0] = nowWeather.get(i).getFcstValue();
        }else if (800 <= Integer.parseInt(nowtime) && Integer.parseInt(nowtime) <=859) { //7시 부터 8시 사이일때
            if (nowWeather.get(i).getFcstTime().equals("2000")) //8시 데이터 출력
                Values[0] = nowWeather.get(i).getFcstValue();
        }else if (900 <= Integer.parseInt(nowtime) && Integer.parseInt(nowtime) <=959) { //9시 부터 10시 사이일때
            if (nowWeather.get(i).getFcstTime().equals("2100")) //10시 데이터 출력
                Values[0] = nowWeather.get(i).getFcstValue();
        }else if (1000 <= Integer.parseInt(nowtime) && Integer.parseInt(nowtime) <=1059) { //10시 부터 11시 사이일때
            if (nowWeather.get(i).getFcstTime().equals("2200")) //3시 데이터 출력
                Values[0] = nowWeather.get(i).getFcstValue();
        }else if (1100 <= Integer.parseInt(nowtime) && Integer.parseInt(nowtime) <=1159) { //11시 부터 12시 사이일때
            if (nowWeather.get(i).getFcstTime().equals("2300")) //3시 데이터 출력
                Values[0] = nowWeather.get(i).getFcstValue();
        } } else if (isAMorPM == Calendar.AM) { //오전
            if (0 <= Integer.parseInt(nowtime) && Integer.parseInt(nowtime) <=59) { // 0 부터 1시반 사이일때
                if (nowWeather.get(i).getFcstTime().equals("0000")) //00시 데이터 출력
                    Values[0] = nowWeather.get(i).getFcstValue();
            }else if (100 <= Integer.parseInt(nowtime) && Integer.parseInt(nowtime) <=159) { // 1 부터 2시 사이일때
                if (nowWeather.get(i).getFcstTime().equals("0100")) //01시 데이터 출력
                    Values[0] = nowWeather.get(i).getFcstValue();
            }else if (200 <= Integer.parseInt(nowtime) && Integer.parseInt(nowtime) <=259) { // 2 부터 3시 사이일때
                if (nowWeather.get(i).getFcstTime().equals("0200")) //2시 데이터 출력
                    Values[0] = nowWeather.get(i).getFcstValue();
            }else if (300 <= Integer.parseInt(nowtime) && Integer.parseInt(nowtime) <=359) { // 3 부터 4시 사이일때
                if (nowWeather.get(i).getFcstTime().equals("0300")) //3시 데이터 출력
                    Values[0] = nowWeather.get(i).getFcstValue();
            } else if (400 <= Integer.parseInt(nowtime) && Integer.parseInt(nowtime) <=459) { // 4 부터 5 사이일때
                if (nowWeather.get(i).getFcstTime().equals("0400")) //12시 데이터 출력
                    Values[0] = nowWeather.get(i).getFcstValue();
            }else if (500 <= Integer.parseInt(nowtime) && Integer.parseInt(nowtime) <=559) { // 4 부터 5 사이일때
                if (nowWeather.get(i).getFcstTime().equals("0500")) //12시 데이터 출력
                    Values[0] = nowWeather.get(i).getFcstValue();
            }else if (600 <= Integer.parseInt(nowtime) && Integer.parseInt(nowtime) <=659) { // 4 부터 5 사이일때
                if (nowWeather.get(i).getFcstTime().equals("0600")) //12시 데이터 출력
                    Values[0] = nowWeather.get(i).getFcstValue();
            }else if (700 <= Integer.parseInt(nowtime) && Integer.parseInt(nowtime) <=759) { // 4 부터 5 사이일때
                if (nowWeather.get(i).getFcstTime().equals("0700")) //12시 데이터 출력
                    Values[0] = nowWeather.get(i).getFcstValue();
            }else if (800 <= Integer.parseInt(nowtime) && Integer.parseInt(nowtime) <=859) { // 4 부터 5 사이일때
                if (nowWeather.get(i).getFcstTime().equals("0800")) //12시 데이터 출력
                    Values[0] = nowWeather.get(i).getFcstValue();
            }else if (900 <= Integer.parseInt(nowtime) && Integer.parseInt(nowtime) <=959) { // 4 부터 5 사이일때
                if (nowWeather.get(i).getFcstTime().equals("0900")) //12시 데이터 출력
                    Values[0] = nowWeather.get(i).getFcstValue();
            }else if (1000 <= Integer.parseInt(nowtime) && Integer.parseInt(nowtime) <=1059) { // 4 부터 5 사이일때
                if (nowWeather.get(i).getFcstTime().equals("1000")) //12시 데이터 출력
                    Values[0] = nowWeather.get(i).getFcstValue();
            }else if (1100 <= Integer.parseInt(nowtime) && Integer.parseInt(nowtime) <=1159) { // 4 부터 5 사이일때
                if (nowWeather.get(i).getFcstTime().equals("1100")) //12시 데이터 출력
                    Values[0] = nowWeather.get(i).getFcstValue();
            }

        }
    }


}

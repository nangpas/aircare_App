package com.example.bnm20.myapplication;


import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.media.Image;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TabLayout tabLayout;

    Fragment fragment;
    FragmentManager fragmentManager;

    public static int statusheight;

    public static int deviceWidth;
    public static int deviceHeight;

    ImageView imageView;
    Toolbar toolbar;

    TextView toolbar_text;
    ImageButton toolbar_button;
    ImageView toolbar_image;




    private DrawerLayout drawerLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        statusheight = getStatusBarHeight(this);

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        deviceHeight = displayMetrics.heightPixels;
        deviceWidth = displayMetrics.widthPixels;

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        LinearLayout.LayoutParams toolbar_params = (LinearLayout.LayoutParams) toolbar.getLayoutParams();
        toolbar_params.height = (4*statusheight);
        toolbar.setLayoutParams(toolbar_params);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);



        //////////////툴바 안의 view 설정 /////////////////
        toolbar_button = (ImageButton) findViewById(R.id.toolbar_button);
        toolbar_image = (ImageView) findViewById(R.id.toolbar_image);
        toolbar_text = (TextView) findViewById(R.id.toolbar_text);

        RelativeLayout.LayoutParams toolbar_button_params = (RelativeLayout.LayoutParams) toolbar_button.getLayoutParams();
        toolbar_button_params.height = statusheight;
        toolbar_button_params.width = statusheight;
        toolbar_button_params.topMargin = statusheight/2;
        toolbar_button_params.rightMargin = statusheight/2;
        toolbar_button.setLayoutParams(toolbar_button_params);

        RelativeLayout.LayoutParams toolbar_image_params = (RelativeLayout.LayoutParams) toolbar_image.getLayoutParams();
        toolbar_image_params.height = statusheight;
        toolbar_image_params.width = (5*statusheight) / 6;
        toolbar_image_params.topMargin = statusheight/2;
        toolbar_image.setLayoutParams(toolbar_image_params);

        RelativeLayout.LayoutParams toolbar_text_params = (RelativeLayout.LayoutParams) toolbar_text.getLayoutParams();
        toolbar_text_params.height = statusheight;
        toolbar_text_params.topMargin = statusheight/2;
        toolbar_text_params.leftMargin = statusheight/2;
        toolbar_text.setLayoutParams(toolbar_text_params);

        fragmentManager = getSupportFragmentManager();

        ////
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        tabLayout.addTab(tabLayout.newTab().setText("날씨"));
        tabLayout.addTab(tabLayout.newTab().setText("내부 환경"));
        tabLayout.addTab(tabLayout.newTab().setText("외부 환경"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        fragment = new TabFragment1();

        fragmentManager.beginTransaction().replace(R.id.fragment_main,fragment).commit();

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()) {
                    case 0:
                        fragment = new TabFragment1();
                        fragmentManager.beginTransaction().replace(R.id.fragment_main, fragment).commit();
                        break;
                    case 1:
                        fragment = new TabFragment2();
                        fragmentManager.beginTransaction().replace(R.id.fragment_main, fragment).commit();
                        break;
                    case 2:
                        fragment = new TabFragment3();
                        fragmentManager.beginTransaction().replace(R.id.fragment_main, fragment).commit();
                        break;
                    default:
                        fragment = new TabFragment1();
                        fragmentManager.beginTransaction().replace(R.id.fragment_main, fragment).commit();
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });





        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        NavigationView navigationView = (NavigationView) findViewById(R.id.navigation_view);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                menuItem.setChecked(true);
                drawerLayout.closeDrawers();

                int id = menuItem.getItemId();
                switch (id) {
                    default:
                        break;

                    case R.id.menu_alarm:
                       //Toast.makeText(MainActivity.this, menuItem.getTitle(), Toast.LENGTH_LONG).show();
                        Intent intent=new Intent(MainActivity.this,Alarm_setting.class);
                        startActivity(intent);
                        break;

                }
                return true;
            }
        });
    }

    public void toolbar_button_onClicked(View v){
        drawerLayout.openDrawer(GravityCompat.END);
    }


    static public int getStatusBarHeight(Context context) {
        int result = 0;
        int resourceId = context.getResources()
                .getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = context.getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }
}


package since.since1700.Fragment.Brands.DetailDesign;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTabHost;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TabHost;
import android.widget.TabWidget;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.Volley;

import since.since1700.MySingleton;
import since.since1700.R;

/**
 * Created by Kuppusamy on 10/5/2017.
 */

public class DetailPage extends AppCompatActivity {
    SharedPreferences sharedpreferences;

    public static final String mypreference = "mypref";
    RequestQueue requestQueue;
    ImageLoader imageLoader;
    NetworkImageView banner_brandimage;
    Button follow_button;
    ImageButton back_imagebutton;
    private FragmentTabHost mTabHost;
    TabWidget tabWidget;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
      getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.brandsdetailpage);
        sharedpreferences =  getSharedPreferences(mypreference,
                Context.MODE_PRIVATE);
        requestQueue= Volley.newRequestQueue(getApplicationContext());
        imageLoader= MySingleton.getInstance(this).getImageLoader();

        Intent get=getIntent();

banner_brandimage=(NetworkImageView)findViewById(R.id.select_brand_image);
        follow_button=(Button)findViewById(R.id.follow_button);
        back_imagebutton=(ImageButton)findViewById(R.id.back_button);
        String fontPath = "fonts/OpenSans-Regular.ttf";
        final Typeface opensansfont = Typeface.createFromAsset(getAssets(), fontPath);
String image="https://androiddevelopmentnew.000webhostapp.com/cars.png";
follow_button.setTypeface(opensansfont);
        banner_brandimage.setImageUrl(image,imageLoader);

        tabWidget=(TabWidget)findViewById(android.R.id.tabs) ;
        mTabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
        mTabHost.setup(this, getSupportFragmentManager(), android.R.id.tabcontent);


        mTabHost.addTab(
                mTabHost.newTabSpec("Feed").setIndicator("Feed", null),
                ShopdetailBrand.class, null);

        mTabHost.addTab(
                mTabHost.newTabSpec("Shop").setIndicator("Shop", null),
                ShopdetailBrand.class, null);
  //  mTabHost.getCurrentTab();

      /*  if(mTabHost.getCurrentTabTag().equals("Feed")){
            mTabHost.getCurrentTabView().setBackgroundResource(R.drawable.membershipgradient);


        }*/
       // mTabHost.getCurrentTabView().setBackgroundResource(R.drawable.membershipgradient);


        back_imagebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

mTabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
    @Override
    public void onTabChanged(String tabId) {
        mTabHost.getCurrentTab();


        /*if (tabId.equals("Feed")) {
            if (mTabHost.getCurrentTab() == 0) {
                mTabHost.getCurrentTabView().setBackgroundResource(R.drawable.membershipgradient);
            }
            mTabHost.getCurrentTabView().setBackgroundResource(R.drawable.membershipgradient);
            if (mTabHost.getCurrentTab() == 1) {
                mTabHost.getCurrentTabView().setBackgroundResource(R.color.white);
            }

        }else {
            if (mTabHost.getCurrentTab() == 0) {
                mTabHost.getCurrentTabView().setBackgroundResource(R.color.white);
            }
            if (mTabHost.getCurrentTab() == 1) {
                mTabHost.getCurrentTabView().setBackgroundResource(R.drawable.membershipgradient);
            }
        }*/

    }
});




    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}

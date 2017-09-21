package since.since1700;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

import since.since1700.Fragment.BrandsFragment;
import since.since1700.Fragment.ContactFragment;
import since.since1700.Fragment.EventsFragment;
import since.since1700.Fragment.FeedFragment;
import since.since1700.Fragment.ShopFragment;

/**
 * Created by Kuppusamy on 9/21/2017.
 */

public class MainActivity extends AppCompatActivity {
    SharedPreferences sharedpreferences;
    public static final String mypreference = "mypref";
    public static final String colorcode = "colorCode";

    ImageButton feed_btn,brands_btn,shop_btn,events_btn,contact_btn;

    String colorcodes;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        sharedpreferences =  getSharedPreferences(mypreference,
                Context.MODE_PRIVATE);

        colorcodes=sharedpreferences.getString(colorcode,"");
        //   colorcodes = colorcodes.replaceAll("\\D+","");
       Log.e("ColorCodes",colorcodes+"hihi");
        String fontPath = "fonts/OpenSans-Regular.ttf";
        final Typeface opensansfont = Typeface.createFromAsset(getAssets(), fontPath);
       feed_btn=(ImageButton)findViewById(R.id.btn_feed);
        brands_btn=(ImageButton)findViewById(R.id.btn_brands);
        shop_btn=(ImageButton)findViewById(R.id.btn_shop);
        events_btn=(ImageButton)findViewById(R.id.btn_event);
        contact_btn=(ImageButton)findViewById(R.id.btn_contact);



        feed_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                brands_btn.setBackgroundResource(R.color.mytransparent);
                shop_btn.setBackgroundResource(R.color.mytransparent);
                events_btn.setBackgroundResource(R.color.mytransparent);
               contact_btn.setBackgroundResource(R.color.mytransparent);
                Fragment selectedFragment = null;
                selectedFragment = FeedFragment.newInstance();
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_layout, selectedFragment);
                transaction.commit();
             if(colorcodes.length()==0){

                }else {

                        if(colorcodes.equals("blue")){
                            feed_btn.setBackgroundResource(R.drawable.membershipgradient);
                        }else if(colorcodes.equalsIgnoreCase("#59247c")){
                            feed_btn.setBackgroundResource(R.drawable.membershipgradient);
                        }else if(colorcodes.equalsIgnoreCase("#1d487a")){
                            feed_btn.setBackgroundResource(R.drawable.membershipgradient);
                        }

                }
            }
        });
        brands_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                feed_btn.setBackgroundResource(R.color.mytransparent);
                shop_btn.setBackgroundResource(R.color.mytransparent);
                events_btn.setBackgroundResource(R.color.mytransparent);
                contact_btn.setBackgroundResource(R.color.mytransparent);
                Fragment selectedFragment = null;
                selectedFragment = BrandsFragment.newInstance();
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_layout, selectedFragment);
                transaction.commit();
              if(colorcodes.length()==0){

                }else {
                    if(colorcodes.equalsIgnoreCase("004")){
                        Log.e("Msg","hihihi");
                    }else {
                        if(colorcodes.equals("blue")){
                            brands_btn.setBackgroundResource(R.drawable.membershipgradient);
                        }else if(colorcodes.equalsIgnoreCase("#59247c")){
                            brands_btn.setBackgroundResource(R.drawable.membershipgradient);
                        }else if(colorcodes.equalsIgnoreCase("#1d487a")){
                            brands_btn.setBackgroundResource(R.drawable.membershipgradient);
                        }
                    }
                }
            }
        });
       shop_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //  if(myprofileid!=null) {
                brands_btn.setBackgroundResource(R.color.mytransparent);
                feed_btn.setBackgroundResource(R.color.mytransparent);
                events_btn.setBackgroundResource(R.color.mytransparent);
                contact_btn.setBackgroundResource(R.color.mytransparent);

                Fragment selectedFragment = null;
                selectedFragment = ShopFragment.newInstance();
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_layout, selectedFragment);
                transaction.commit();
               if (colorcodes.length() == 0) {

                } else {
                    if (colorcodes.equalsIgnoreCase("004")) {
                        Log.e("Msg", "hihihi");
                    } else {
                        if(colorcodes.equals("blue")){
                            shop_btn.setBackgroundResource(R.drawable.membershipgradient);
                        }else if(colorcodes.equalsIgnoreCase("#59247c")){
                            shop_btn.setBackgroundResource(R.drawable.membershipgradient);
                        }else if(colorcodes.equalsIgnoreCase("#1d487a")){
                            shop_btn.setBackgroundResource(R.drawable.membershipgradient);
                        }
                    }
                }

            }
        });
        events_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                brands_btn.setBackgroundResource(R.color.mytransparent);
                shop_btn.setBackgroundResource(R.color.mytransparent);
                feed_btn.setBackgroundResource(R.color.mytransparent);
                contact_btn.setBackgroundResource(R.color.mytransparent);
                Fragment selectedFragment = null;
                selectedFragment = EventsFragment.newInstance();
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_layout, selectedFragment);
                transaction.commit();
               if(colorcodes.length()==0){

                }else {
                    if(colorcodes.equalsIgnoreCase("004")){
                        Log.e("Msg","hihihi");
                    }else {
                        if(colorcodes.equals("blue")){
                            events_btn.setBackgroundResource(R.drawable.membershipgradient);
                        }else if(colorcodes.equalsIgnoreCase("#59247c")){
                            events_btn.setBackgroundResource(R.drawable.membershipgradient);
                        }else if(colorcodes.equalsIgnoreCase("#1d487a")){
                            events_btn.setBackgroundResource(R.drawable.membershipgradient);
                        }
                    }
                }
            }
        });
        contact_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                brands_btn.setBackgroundResource(R.color.mytransparent);
                shop_btn.setBackgroundResource(R.color.mytransparent);
                events_btn.setBackgroundResource(R.color.mytransparent);
                feed_btn.setBackgroundResource(R.color.mytransparent);
                Fragment selectedFragment = null;
                selectedFragment = ContactFragment.newInstance();
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_layout, selectedFragment);
                transaction.commit();
               if(colorcodes.length()==0){

                }else {
                    if(colorcodes.equalsIgnoreCase("004")){
                        Log.e("Msg","hihihi");
                    }else {
                        if(colorcodes.equals("blue")){
                            contact_btn.setBackgroundResource(R.drawable.membershipgradient);
                        }else if(colorcodes.equalsIgnoreCase("#59247c")){
                            contact_btn.setBackgroundResource(R.drawable.membershipgradient);
                        }else if(colorcodes.equalsIgnoreCase("#1d487a")){
                            contact_btn.setBackgroundResource(R.drawable.membershipgradient);
                        }
                    }
                }
            }
        });



        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_layout, FeedFragment.newInstance());
        transaction.commit();
      if(colorcodes.length()==0){

        }else {
            if(colorcodes.equalsIgnoreCase("004")){
                Log.e("Msg","hihihi");
            }else {
                if(colorcodes.equals("blue")){
                    feed_btn.setBackgroundResource(R.drawable.membershipgradient);
                }else if(colorcodes.equalsIgnoreCase("#59247c")){
                    feed_btn.setBackgroundResource(R.drawable.membershipgradient);
                }else if(colorcodes.equalsIgnoreCase("#1d487a")){
                    feed_btn.setBackgroundResource(R.drawable.membershipgradient);
                }
            }
        }



    }
}

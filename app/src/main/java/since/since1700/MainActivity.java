package since.since1700;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SlidingPaneLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.TranslateAnimation;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import since.since1700.Filter.ExpandableListAdapter;
import since.since1700.Filter.ExpandableListDataPump;
import since.since1700.Filter.FragmentDrawer;
import since.since1700.Fragment.BrandsFragment;
import since.since1700.Fragment.ContactFragment;
import since.since1700.Fragment.EventsFragment;
import since.since1700.Fragment.FeedFragment;
import since.since1700.Fragment.ShopFragment;
import since.since1700.Profile.ProfileActivity;

/**
 * Created by Kuppusamy on 9/21/2017.
 */

public class MainActivity extends AppCompatActivity implements FragmentDrawer.FragmentDrawerListener {
    SharedPreferences sharedpreferences;
    public static final String mypreference = "mypref";
    public static final String colorcode = "colorCode";
    ImageView profile,profileimage;
    ImageView filter;
    ImageButton feed_btn,brands_btn,shop_btn,events_btn,contact_btn;
    Toolbar toolbar;
    private FragmentDrawer drawerFragment;
    LinearLayout layout,profilelayout;
     ExpandableListView mDrawerListView;
    List<String> expandableListTitle;
    HashMap<String, List<String>> expandableListDetail;
    Button close;
    String colorcodes;
    float x1,x2;
    float y1, y2;
    TextView profilename,viewprofile,privilages,auctions,cart,rewards,blog,aboutus,terms;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.main_activity);

        toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

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
        filter = (ImageView) findViewById(R.id.filter);
        profileimage = (ImageView) findViewById(R.id.profileimage);
        profile = (ImageView) findViewById(R.id.profilePic);
        profilelayout = (LinearLayout)findViewById(R.id.profile_layout);

        profilename = (TextView) findViewById(R.id.profilename);
        viewprofile = (TextView) findViewById(R.id.viewprofile);
        privilages = (TextView) findViewById(R.id.privilages);
        auctions = (TextView) findViewById(R.id.auctions);
        cart = (TextView) findViewById(R.id.cart);
        rewards = (TextView) findViewById(R.id.myrewards);
        blog = (TextView) findViewById(R.id.blog);
        aboutus = (TextView) findViewById(R.id.aboutus);
        terms = (TextView) findViewById(R.id.terms);

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                profilelayout.setVisibility(View.VISIBLE);
            }
        });

        viewprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent next=new Intent(getApplicationContext(),ProfileActivity.class);
                startActivity(next);
            }
        });

        mDrawerListView = (ExpandableListView) findViewById(R.id.lvExp);
        close = (Button)findViewById(R.id.close);

        layout = (LinearLayout)findViewById(R.id.fragment_layout);
        filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                layout.setVisibility(View.VISIBLE);
            }
        });


       /* mDrawerListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                drawerListener.onDrawerItemSelected(view, position);
                mDrawerLayout.closeDrawer(containerView);
            }
        });*/

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layout.setVisibility(View.GONE);
            }
        });

        expandableListDetail = ExpandableListDataPump.getData();
        expandableListTitle = new ArrayList<String>(expandableListDetail.keySet());


        mDrawerListView.setAdapter(new ExpandableListAdapter(this, expandableListTitle, expandableListDetail));

        mDrawerListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {

            @Override
            public void onGroupExpand(int groupPosition) {

            }
        });

        //SlidingPaneLayout slidingPaneLayout = (SlidingPaneLayout) findViewById(R.id.fragment_layout);
        //slidingPaneLayout.setEnable(false);

        mDrawerListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                boolean retVal = true;

                // Fragment fragment = null;
                String title = getString(R.string.app_name);
                // Class fragmentClass = null;
                /*if (groupPosition == ExpandableListAdapterTamil.ITEM1) {
                    retVal = false;
                } else if (groupPosition == ExpandableListAdapterTamil.ITEM2) {*/

                Log.e("POSITIONNNN", String.valueOf(groupPosition));
                    /*Fragment fragment = new HerbesAndSpicesEnglish();
                    FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction =
                     fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.container_body, fragment);
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();
*/
                //retVal = false;
                /*} else if (groupPosition == ExpandableListAdapterTamil.ITEM3) {
                    retVal = false;
                    // call some activity here
                } else if (groupPosition == ExpandableListAdapterTamil.ITEM4) {
                    // call some activity here
                    retVal = false;
                }*/
                return retVal;
            }
        });
        Picasso.with(this)
                .load("http://simpli-city.in//gloclAPI//image//03072017193744_1878332239_glocl_posted_image.jpg")
                .into(profileimage);


        Picasso.with(this)
                .load("http://simpli-city.in//gloclAPI//image//03072017193744_1878332239_glocl_posted_image.jpg")
                .into(profile);


        feed_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                feed_btn.setBackgroundResource(R.drawable.bluebutton);
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
                brands_btn.setBackgroundResource(R.drawable.bluebutton);
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
                shop_btn.setBackgroundResource(R.drawable.bluebutton);
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
                events_btn.setBackgroundResource(R.drawable.bluebutton);
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
                contact_btn.setBackgroundResource(R.drawable.bluebutton);
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
    public boolean onTouchEvent(MotionEvent touchevent) {
        switch (touchevent.getAction()) {
            // when user first touches the screen we get x and y coordinate
            case MotionEvent.ACTION_DOWN: {
                x1 = touchevent.getX();
                y1 = touchevent.getY();
                break;
            }
            case MotionEvent.ACTION_UP: {
                x2 = touchevent.getX();
                y2 = touchevent.getY();


                if (x1 < x2) {
                    Toast.makeText(this, "Left to Right Swap Performed", Toast.LENGTH_LONG).show();
                }

                // if right to left sweep event on screen
                if (x1 > x2) {
                    Toast.makeText(this, "Right to Left Swap Performed", Toast.LENGTH_LONG).show();
                }

                // if UP to Down sweep event on screen
                if (y1 < y2) {
                    Toast.makeText(this, "UP to Down Swap Performed", Toast.LENGTH_LONG).show();
                }


                if (y1 > y2) {
                    Toast.makeText(this, "Down to UP Swap Performed", Toast.LENGTH_LONG).show();
                }
                break;
            }
        }
        return false;
    }
    @Override
    public void onDrawerItemSelected(View view, int position) {

    }
}

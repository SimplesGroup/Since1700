package since.since1700;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.IOException;
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
import since.since1700.Login.LoginActivity;
import since.since1700.Profile.ProfileActivity;
import since.since1700.Search.CustomAdapter;
import since.since1700.Search.SearchActivity;
import since.since1700.Search.StudentRepo;

/**
 * Created by Kuppusamy on 9/21/2017.
 */

public class MainActivity extends AppCompatActivity implements OnGestureListener,FragmentDrawer.FragmentDrawerListener{
    SharedPreferences sharedpreferences;
    public static final String mypreference = "mypref";
    public static final String colorcode = "colorCode";
    ImageView profile,profileimage,camera,back;
    ImageView filter,search;
    ImageButton feed_btn,brands_btn,shop_btn,events_btn,contact_btn;
    Toolbar toolbar;
    private FragmentDrawer drawerFragment;
    LinearLayout layout,profilelayout,linearLayout,closelayout1,closelayout2,logoutlayout;
    RelativeLayout relativelayout;
     ExpandableListView mDrawerListView;
    List<String> expandableListTitle;
    HashMap<String, List<String>> expandableListDetail;
    Button close,closeprofile,btnlogout;
    String colorcodes;
    float x1,x2;
    float y1, y2;
    TextView profilename,viewprofile,privilages,auctions,cart,rewards,blog,aboutus,terms;
    public Bitmap myBitmap;
    Uri picUri;
    private CustomAdapter customAdapter;
    ListView listView;
    Cursor cursor;
    StudentRepo studentRepo ;
    private final static String TAG= MainActivity.class.getName().toString();
    EditText searchedit;
    GestureDetector gestureDetector;
    public  static  final String SelectedCategory="category";
    SQLiteDatabase mDatabase;
    ListView searchlistview;
    ViewPager pager;
    String categoryitems;
String id;
    public static final String DATABASE_NAME = "mysearchdatabase";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.main_activity);

        toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        sharedpreferences =  getSharedPreferences(mypreference,
                Context.MODE_PRIVATE);
        categoryitems=sharedpreferences.getString(SelectedCategory,"");
        Log.e("CAT",categoryitems);
        colorcodes=sharedpreferences.getString(colorcode,"");
        //   colorcodes = colorcodes.replaceAll("\\D+","");
       Log.e("ColorCodes",colorcodes+"hihi");
        String fontPath = "fonts/PFBeauSansPro-Reg_0.otf";
        final Typeface opensansfont = Typeface.createFromAsset(getAssets(), fontPath);
        mDatabase = openOrCreateDatabase(DATABASE_NAME, MODE_PRIVATE, null);
        Intent get=getIntent();
        id=get.getStringExtra("ID");

        gestureDetector = new GestureDetector(MainActivity.this, MainActivity.this);
       feed_btn=(ImageButton)findViewById(R.id.btn_feed);
        brands_btn=(ImageButton)findViewById(R.id.btn_brands);
        shop_btn=(ImageButton)findViewById(R.id.btn_shop);
        events_btn=(ImageButton)findViewById(R.id.btn_event);
        contact_btn=(ImageButton)findViewById(R.id.btn_contact);
        filter = (ImageView) findViewById(R.id.filter);
        search = (ImageView) findViewById(R.id.search);
       // relativelayout = (RelativeLayout) findViewById(R.id.relativeLayout2);
         profileimage = (ImageView) findViewById(R.id.profileimage);
        profile = (ImageView) findViewById(R.id.profilePic);
        camera = (ImageView) findViewById(R.id.camera);
        profilelayout = (LinearLayout)findViewById(R.id.profile_layout);
        linearLayout = (LinearLayout)findViewById(R.id.linearLayout2);
        profilename = (TextView) findViewById(R.id.profilename);
        viewprofile = (TextView) findViewById(R.id.viewprofile);
        privilages = (TextView) findViewById(R.id.privilages);
        auctions = (TextView) findViewById(R.id.auctions);
        cart = (TextView) findViewById(R.id.cart);
        rewards = (TextView) findViewById(R.id.myrewards);
        blog = (TextView) findViewById(R.id.blog);
        aboutus = (TextView) findViewById(R.id.aboutus);
        terms = (TextView) findViewById(R.id.terms);
        final ProfileActivity p = new ProfileActivity();
        relativelayout = (RelativeLayout) findViewById(R.id.relativelayout);
        closelayout1 = (LinearLayout) findViewById(R.id.close_layout1);
        closelayout2 = (LinearLayout) findViewById(R.id.close_layout2);
        logoutlayout = (LinearLayout) findViewById(R.id.logout_layout);


        relativelayout.setVisibility(View.VISIBLE);
        privilages.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pos = "2";
                Intent i = new Intent(MainActivity.this, ProfileActivity.class);
//Create the bundle
                Bundle b = new Bundle();
//Add your data to bundle
                b.putString("pos", pos);
                i.putExtras(b);
                startActivity(i);
            }
        });

        auctions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pos = "1";
                Intent i = new Intent(MainActivity.this, ProfileActivity.class);
//Create the bundle
                Bundle b = new Bundle();

//Add your data to bundle

                b.putString("pos", pos);
                i.putExtras(b);
                startActivity(i);
            }
        });

        cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pos = "3";
                Intent i = new Intent(MainActivity.this, ProfileActivity.class);
//Create the bundle
                Bundle b = new Bundle();
//Add your data to bundle
                b.putString("pos", pos);
                i.putExtras(b);
                startActivity(i);
            }
        });

        rewards.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pos = "4";
                Intent i = new Intent(MainActivity.this, ProfileActivity.class);
//Create the bundle
                Bundle b = new Bundle();
//Add your data to bundle
                b.putString("pos", pos);
                i.putExtras(b);
                startActivity(i);
            }
        });

        blog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pos = "5";
                Intent i = new Intent(MainActivity.this, ProfileActivity.class);
//Create the bundle
                Bundle b = new Bundle();
//Add your data to bundle
                b.putString("pos", pos);
                i.putExtras(b);
                startActivity(i);
            }
        });

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /* Intent i = new Intent(getApplicationContext(), DetailPage.class);
                startActivity(i);*/
               profilelayout.setVisibility(View.VISIBLE);
                relativelayout.setAlpha(0.6f);
            }
        });


        camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivityForResult(getPickImageChooserIntent(), 200);
            }
        });
        viewprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String pos = "0";

                Intent i = new Intent(MainActivity.this, ProfileActivity.class);
//Create the bundle
                Bundle b = new Bundle();
//Add your data to bundle
                b.putString("pos", pos);
                i.putExtras(b);
                startActivity(i);
            }
        });

        aboutus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent next=new Intent(getApplicationContext(),AboutUsActivity.class);
                startActivity(next);
            }
        });
      terms.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent next=new Intent(getApplicationContext(),TermsandCondition.class);
        startActivity(next);
    }
  });
        mDrawerListView = (ExpandableListView) findViewById(R.id.lvExp);
        close = (Button)findViewById(R.id.close);
        closeprofile = (Button)findViewById(R.id.closeprofile);
        btnlogout = (Button)findViewById(R.id.logout);

        layout = (LinearLayout)findViewById(R.id.fragment_layout);
        filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                layout.setVisibility(View.VISIBLE);
                relativelayout.setAlpha(0.6f);
            }
        });

      search.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {

               Intent i = new Intent(MainActivity.this, SearchActivity.class);
                startActivity(i);
            }
        });

/*
        if(layout.getVisibility() == View.VISIBLE || profilelayout.getVisibility() == View.VISIBLE)
        {

        }
*/

       /* mDrawerListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                drawerListener.onDrawerItemSelected(view, position);
                mDrawerLayout.closeDrawer(containerView);
            }
        });*/


       logoutlayout.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {

               SharedPreferences.Editor editor = sharedpreferences.edit();
               editor.clear();
               Intent i = new Intent(MainActivity.this, LoginActivity.class);
               startActivity(i);

           }
       });

        closelayout2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                profilelayout.setVisibility(View.GONE);
                relativelayout.setAlpha(1);
            }
        });
        closelayout1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layout.setVisibility(View.GONE);
                relativelayout.setAlpha(1);
            }
        });

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layout.setVisibility(View.GONE);
                relativelayout.setAlpha(1);
            }
        });

        closeprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                profilelayout.setVisibility(View.GONE);
                relativelayout.setAlpha(1);
            }
        });

        expandableListDetail = ExpandableListDataPump.getData();
        expandableListTitle = new ArrayList<String>(expandableListDetail.keySet());
        Log.e("KeY",expandableListDetail.keySet().toString());


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
                    fragmentTransaction.replace(R.id.\

                    container_body, fragment);
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

        Bitmap mbitmap = ((BitmapDrawable) getResources().getDrawable(R.drawable.stevejobs)).getBitmap();
        Bitmap imageRounded = Bitmap.createBitmap(mbitmap.getWidth(), mbitmap.getHeight(), mbitmap.getConfig());
        Canvas canvas = new Canvas(imageRounded);
        Paint mpaint = new Paint();
        mpaint.setAntiAlias(true);
        mpaint.setShader(new BitmapShader(mbitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP));
        canvas.drawRoundRect((new RectF(0, 0, mbitmap.getWidth(), mbitmap.getHeight())), 100, 100, mpaint);// Round Image Corner 100 100 100 100
        profileimage.setImageBitmap(imageRounded);
     /* Picasso.with(this)
                .load("http://simpli-city.in//gloclAPI//image//03072017193744_1878332239_glocl_posted_image.jpg")
                .into(profileimage);*/

       // profile.setImageURI(Uri.parse("http://simpli-city.in//gloclAPI//image//03072017193744_1878332239_glocl_posted_image.jpg"));
       Picasso.with(this)
                .load("http://simpli-city.in//gloclAPI//image//03072017193744_1878332239_glocl_posted_image.jpg")
                .into(profile);




        feed_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                feed_btn.setImageResource(R.mipmap.feedwhite);
               // feed_btn.setBackgroundResource(R.drawable.bluebutton);
                brands_btn.setBackgroundResource(R.color.mytransparent);
                brands_btn.setImageResource(R.mipmap.brands);
                shop_btn.setBackgroundResource(R.color.mytransparent);
                shop_btn.setImageResource(R.mipmap.shop);
                events_btn.setBackgroundResource(R.color.mytransparent);
                events_btn.setImageResource(R.mipmap.event);
               contact_btn.setBackgroundResource(R.color.mytransparent);
               contact_btn.setImageResource(R.mipmap.contact);
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
                brands_btn.setImageResource(R.mipmap.brandswhite);
                brands_btn.setBackgroundResource(R.drawable.bluebutton);
                feed_btn.setBackgroundResource(R.color.mytransparent);
                shop_btn.setBackgroundResource(R.color.mytransparent);
                events_btn.setBackgroundResource(R.color.mytransparent);
                contact_btn.setBackgroundResource(R.color.mytransparent);

                feed_btn.setImageResource(R.mipmap.feed);
                shop_btn.setImageResource(R.mipmap.shop);
                events_btn.setImageResource(R.mipmap.event);
                contact_btn.setImageResource(R.mipmap.contact);

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
                shop_btn.setImageResource(R.mipmap.shopwhite);
                shop_btn.setBackgroundResource(R.drawable.bluebutton);
                brands_btn.setBackgroundResource(R.color.mytransparent);
                feed_btn.setBackgroundResource(R.color.mytransparent);
                events_btn.setBackgroundResource(R.color.mytransparent);
                contact_btn.setBackgroundResource(R.color.mytransparent);

                feed_btn.setImageResource(R.mipmap.feed);
                brands_btn.setImageResource(R.mipmap.brands);
                events_btn.setImageResource(R.mipmap.event);
                contact_btn.setImageResource(R.mipmap.contact);


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
                events_btn.setImageResource(R.mipmap.eventswhite);
                events_btn.setBackgroundResource(R.drawable.bluebutton);
                brands_btn.setBackgroundResource(R.color.mytransparent);
                shop_btn.setBackgroundResource(R.color.mytransparent);
                feed_btn.setBackgroundResource(R.color.mytransparent);
                contact_btn.setBackgroundResource(R.color.mytransparent);

                feed_btn.setImageResource(R.mipmap.feed);
                brands_btn.setImageResource(R.mipmap.brands);
                shop_btn.setImageResource(R.mipmap.shop);
                contact_btn.setImageResource(R.mipmap.contact);


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
                contact_btn.setImageResource(R.mipmap.conciergewhite);
                contact_btn.setBackgroundResource(R.drawable.bluebutton);
                brands_btn.setBackgroundResource(R.color.mytransparent);
                shop_btn.setBackgroundResource(R.color.mytransparent);
                events_btn.setBackgroundResource(R.color.mytransparent);
                feed_btn.setBackgroundResource(R.color.mytransparent);

                feed_btn.setImageResource(R.mipmap.feed);
                brands_btn.setImageResource(R.mipmap.brands);
                shop_btn.setImageResource(R.mipmap.shop);
                events_btn.setImageResource(R.mipmap.event);

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

        if(id!=null){
            changefrag();
        }else {

            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.frame_layout, FeedFragment.newInstance());
            transaction.commit();
            if (colorcodes.length() == 0) {

            } else {
                if (colorcodes.equalsIgnoreCase("004")) {
                    Log.e("Msg", "hihihi");
                } else {
                    if (colorcodes.equals("blue")) {
                        feed_btn.setBackgroundResource(R.drawable.membershipgradient);
                        feed_btn.setImageResource(R.mipmap.feedwhite);
                    } else if (colorcodes.equalsIgnoreCase("#59247c")) {
                        feed_btn.setBackgroundResource(R.drawable.membershipgradient);
                        feed_btn.setImageResource(R.mipmap.feedwhite);
                    } else if (colorcodes.equalsIgnoreCase("#1d487a")) {
                        feed_btn.setBackgroundResource(R.drawable.membershipgradient);
                        feed_btn.setImageResource(R.mipmap.feedwhite);
                    }
                }
            }
        }
    }

    @Override
    public void onDrawerItemSelected(View view, int position) {
    }

    private void changefrag(){
if(id.equals("1")){
    feed_btn.setImageResource(R.mipmap.feedwhite);
    // feed_btn.setBackgroundResource(R.drawable.bluebutton);
    brands_btn.setBackgroundResource(R.color.mytransparent);
    brands_btn.setImageResource(R.mipmap.brands);
    shop_btn.setBackgroundResource(R.color.mytransparent);
    shop_btn.setImageResource(R.mipmap.shop);
    events_btn.setBackgroundResource(R.color.mytransparent);
    events_btn.setImageResource(R.mipmap.event);
    contact_btn.setBackgroundResource(R.color.mytransparent);
    contact_btn.setImageResource(R.mipmap.contact);
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
}else if(id.equals("2")) {
    brands_btn.setImageResource(R.mipmap.brandswhite);
    brands_btn.setBackgroundResource(R.drawable.bluebutton);
    feed_btn.setBackgroundResource(R.color.mytransparent);
    shop_btn.setBackgroundResource(R.color.mytransparent);
    events_btn.setBackgroundResource(R.color.mytransparent);
    contact_btn.setBackgroundResource(R.color.mytransparent);

    feed_btn.setImageResource(R.mipmap.feed);
    shop_btn.setImageResource(R.mipmap.shop);
    events_btn.setImageResource(R.mipmap.event);
    contact_btn.setImageResource(R.mipmap.contact);

    Fragment selectedFragment = null;
    selectedFragment = BrandsFragment.newInstance();
    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
    transaction.replace(R.id.frame_layout, selectedFragment);
    transaction.commit();
    if (colorcodes.length() == 0) {

    } else {
        if (colorcodes.equalsIgnoreCase("004")) {
            Log.e("Msg", "hihihi");
        } else {
            if (colorcodes.equals("blue")) {
                brands_btn.setBackgroundResource(R.drawable.membershipgradient);
            } else if (colorcodes.equalsIgnoreCase("#59247c")) {
                brands_btn.setBackgroundResource(R.drawable.membershipgradient);
            } else if (colorcodes.equalsIgnoreCase("#1d487a")) {
                brands_btn.setBackgroundResource(R.drawable.membershipgradient);
            }
        }
    }
}else if(id.equals("3")){

    shop_btn.setImageResource(R.mipmap.shopwhite);
    shop_btn.setBackgroundResource(R.drawable.bluebutton);
    brands_btn.setBackgroundResource(R.color.mytransparent);
    feed_btn.setBackgroundResource(R.color.mytransparent);
    events_btn.setBackgroundResource(R.color.mytransparent);
    contact_btn.setBackgroundResource(R.color.mytransparent);

    feed_btn.setImageResource(R.mipmap.feed);
    brands_btn.setImageResource(R.mipmap.brands);
    events_btn.setImageResource(R.mipmap.event);
    contact_btn.setImageResource(R.mipmap.contact);


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

}else if (id.equals("4")){
    events_btn.setImageResource(R.mipmap.eventswhite);
    events_btn.setBackgroundResource(R.drawable.bluebutton);
    brands_btn.setBackgroundResource(R.color.mytransparent);
    shop_btn.setBackgroundResource(R.color.mytransparent);
    feed_btn.setBackgroundResource(R.color.mytransparent);
    contact_btn.setBackgroundResource(R.color.mytransparent);

    feed_btn.setImageResource(R.mipmap.feed);
    brands_btn.setImageResource(R.mipmap.brands);
    shop_btn.setImageResource(R.mipmap.shop);
    contact_btn.setImageResource(R.mipmap.contact);


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
}else if (id.equals("5")){
    contact_btn.setImageResource(R.mipmap.conciergewhite);
    contact_btn.setBackgroundResource(R.drawable.bluebutton);
    brands_btn.setBackgroundResource(R.color.mytransparent);
    shop_btn.setBackgroundResource(R.color.mytransparent);
    events_btn.setBackgroundResource(R.color.mytransparent);
    feed_btn.setBackgroundResource(R.color.mytransparent);

    feed_btn.setImageResource(R.mipmap.feed);
    brands_btn.setImageResource(R.mipmap.brands);
    shop_btn.setImageResource(R.mipmap.shop);
    events_btn.setImageResource(R.mipmap.event);

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
    }

    public Intent getPickImageChooserIntent() {

        // Determine Uri of camera image to save.
        Uri outputFileUri = getCaptureImageOutputUri();

        List<Intent> allIntents = new ArrayList<>();
        PackageManager packageManager = getPackageManager();

        // collect all camera intents
        Intent captureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        List<ResolveInfo> listCam = packageManager.queryIntentActivities(captureIntent, 0);
        for (ResolveInfo res : listCam) {
            Intent intent = new Intent(captureIntent);
            intent.setComponent(new ComponentName(res.activityInfo.packageName, res.activityInfo.name));
            intent.setPackage(res.activityInfo.packageName);
            if (outputFileUri != null) {
                intent.putExtra(MediaStore.EXTRA_OUTPUT, outputFileUri);
            }
            allIntents.add(intent);
        }

        // collect all gallery intents
        Intent galleryIntent = new Intent(Intent.ACTION_GET_CONTENT);
        galleryIntent.setType("image/*");
        List<ResolveInfo> listGallery = packageManager.queryIntentActivities(galleryIntent, 0);
        for (ResolveInfo res : listGallery) {
            Intent intent = new Intent(galleryIntent);
            intent.setComponent(new ComponentName(res.activityInfo.packageName, res.activityInfo.name));
            intent.setPackage(res.activityInfo.packageName);
            allIntents.add(intent);
        }

        // the main intent is the last in the list (fucking android) so pickup the useless one
        Intent mainIntent = allIntents.get(allIntents.size() - 1);
        for (Intent intent : allIntents) {
            if (intent.getComponent().getClassName().equals("com.android.documentsui.DocumentsActivity")) {
                mainIntent = intent;
                break;
            }
        }
        allIntents.remove(mainIntent);

        // Create a chooser from the main intent
        Intent chooserIntent = Intent.createChooser(mainIntent, "Select source");

        // Add all other intents
        chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, allIntents.toArray(new Parcelable[allIntents.size()]));

        return chooserIntent;
    }
    private Uri getCaptureImageOutputUri() {
        Uri outputFileUri = null;
        File getImage = getExternalCacheDir();
        if (getImage != null) {
            outputFileUri = Uri.fromFile(new File(getImage.getPath(), "profile.png"));
        }
        return outputFileUri;
    }
    public Uri getPickImageResultUri(Intent data) {
        boolean isCamera = true;
        if (data != null) {
            String action = data.getAction();
            isCamera = action != null && action.equals(MediaStore.ACTION_IMAGE_CAPTURE);
        }


        return isCamera ? getCaptureImageOutputUri() : data.getData();
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        Bitmap bitmap;
        if (resultCode == Activity.RESULT_OK) {



            if (getPickImageResultUri(data) != null) {
                picUri = getPickImageResultUri(data);

                try {
                    myBitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), picUri);
                    myBitmap = rotateImageIfRequired(myBitmap, picUri);
                    myBitmap = getResizedBitmap(myBitmap, 500);

                    //ImageView croppedImageView = (ImageView) findViewById(R.id.profilePic);
                   // croppedImageView.setImageBitmap(myBitmap);
                    profile.setImageBitmap(myBitmap);

                } catch (IOException e) {
                    e.printStackTrace();
                }


            } else {


                bitmap = (Bitmap) data.getExtras().get("data");

                myBitmap = bitmap;
              //  ImageView croppedImageView = (ImageView) findViewById(R.id.profilePic);
                if (profile != null) {
                    profile.setImageBitmap(myBitmap);
                }

                profile.setImageBitmap(myBitmap);

            }

        }

    }



    private static Bitmap rotateImageIfRequired(Bitmap img, Uri selectedImage) throws IOException {

        ExifInterface ei = new ExifInterface(selectedImage.getPath());
        int orientation = ei.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);

        switch (orientation) {
            case ExifInterface.ORIENTATION_ROTATE_90:
                return rotateImage(img, 90);
            case ExifInterface.ORIENTATION_ROTATE_180:
                return rotateImage(img, 180);
            case ExifInterface.ORIENTATION_ROTATE_270:
                return rotateImage(img, 270);
            default:
                return img;
        }
    }
    private static Bitmap rotateImage(Bitmap img, int degree) {
        Matrix matrix = new Matrix();
        matrix.postRotate(degree);
        Bitmap rotatedImg = Bitmap.createBitmap(img, 0, 0, img.getWidth(), img.getHeight(), matrix, true);
        img.recycle();
        return rotatedImg;
    }

    public Bitmap getResizedBitmap(Bitmap image, int maxSize) {
        int width = image.getWidth();
        int height = image.getHeight();

        float bitmapRatio = (float) width / (float) height;
        if (bitmapRatio > 0) {
            width = maxSize;
            height = (int) (width / bitmapRatio);
        } else {
            height = maxSize;
            width = (int) (height * bitmapRatio);
        }
        return Bitmap.createScaledBitmap(image, width, height, true);
    }

    private void createSearchTable() {
        mDatabase.execSQL(
                "CREATE TABLE IF NOT EXISTS search (\n" +
                        "    id INTEGER NOT NULL CONSTRAINT search_pk PRIMARY KEY AUTOINCREMENT,\n" +
                        "    name varchar(200) NOT NULL" +
                        ");"
        );
    }
    @Override
    public boolean onFling(MotionEvent motionEvent1, MotionEvent motionEvent2, float X, float Y) {

        if(motionEvent1.getY() - motionEvent2.getY() > 50){

            Toast.makeText(MainActivity.this , " Swipe Up " , Toast.LENGTH_LONG).show();

            return true;
        }

        if(motionEvent2.getY() - motionEvent1.getY() > 50){

            Toast.makeText(MainActivity.this , " Swipe Down " , Toast.LENGTH_LONG).show();

            return true;
        }

        if(motionEvent1.getX() - motionEvent2.getX() > 50){

            Toast.makeText(MainActivity.this , " Swipe Left " , Toast.LENGTH_LONG).show();

            return true;
        }

        if(motionEvent2.getX() - motionEvent1.getX() > 50) {

            Toast.makeText(MainActivity.this, " Swipe Right ", Toast.LENGTH_LONG).show();
            profilelayout.setVisibility(View.GONE);
            return true;
        }
        else {

            return true ;
        }
    }

    @Override
    public void onLongPress(MotionEvent arg0) {

        // TODO Auto-generated method stub

    }

    @Override
    public boolean onScroll(MotionEvent arg0, MotionEvent arg1, float arg2, float arg3) {

        // TODO Auto-generated method stub

        return false;
    }

    @Override
    public void onShowPress(MotionEvent arg0) {

        // TODO Auto-generated method stub

    }

    @Override
    public boolean onSingleTapUp(MotionEvent arg0) {

        // TODO Auto-generated method stub

        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent motionEvent) {

        // TODO Auto-generated method stub

        return gestureDetector.onTouchEvent(motionEvent);
    }

    @Override
    public boolean onDown(MotionEvent arg0) {

        // TODO Auto-generated method stub

        return false;
    }

}

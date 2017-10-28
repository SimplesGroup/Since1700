package since.since1700.Profile;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import since.since1700.Fragment.Brands.DetailDesign.Feedbrand;
import since.since1700.Fragment.BrandsFragment;
import since.since1700.Fragment.ContactFragment;
import since.since1700.Fragment.EventsFragment;
import since.since1700.Fragment.FeedFragment;
import since.since1700.Fragment.ShopFragment;
import since.since1700.Model.FeedProductModel;
import since.since1700.MySingleton;
import since.since1700.R;

/**
 * Created by Sandhiya on 10/4/2017.
 */

public class ProfileActivity extends AppCompatActivity {
    ImageView profile,camera;
    private Toolbar toolbar;
    private TabLayout tabLayout;
    private Resources mResources;
    public ViewPager viewPager;
    public Bitmap mBitmap;
    Uri picUri;
    String colorcodes;
    public static final String colorcode = "colorCode";
    SharedPreferences sharedpreferences;
    ImageButton feed_btn,brands_btn,shop_btn,events_btn,contact_btn;
    public static final String mypreference = "mypref";
    float cornerRadius = 50.0f;
    int requestcount = 1;
    RequestQueue requestQueue;
    String ITEMURL = "https://androiddevelopmentnew.000webhostapp.com/productlist.json";
    Feedbrand.ProductAdapterFeed productAdapter;
    List<FeedProductModel> productlist = new ArrayList<FeedProductModel>();
    FeedProductModel model = new FeedProductModel();
    ImageLoader imageLoader;
    int pos=2;
  //  String pic = "http://simpli-city.in//gloclAPI//image//03072017193744_1878332239_glocl_posted_image.jpg";
  //int a = Integer.parseInt(pic);
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_profile);

        profile = (ImageView) findViewById(R.id.profilePic);
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);
        sharedpreferences =  getSharedPreferences(mypreference,
                Context.MODE_PRIVATE);
        requestQueue = Volley.newRequestQueue(getApplicationContext());
        imageLoader = MySingleton.getInstance(getApplicationContext()).getImageLoader();
        colorcodes=sharedpreferences.getString(colorcode,"");
        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        feed_btn=(ImageButton)findViewById(R.id.btn_feed);
        brands_btn=(ImageButton)findViewById(R.id.btn_brands);
        shop_btn=(ImageButton)findViewById(R.id.btn_shop);
        events_btn=(ImageButton)findViewById(R.id.btn_event);
        contact_btn=(ImageButton)findViewById(R.id.btn_contact);
        camera = (ImageView) findViewById(R.id.camera);
        camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivityForResult(getPickImageChooserIntent(), 200);
            }
        });
        mResources = getResources();
        getData();
viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
});
        Bitmap mbitmap = ((BitmapDrawable) getResources().getDrawable(R.drawable.stevejobs)).getBitmap();
        Bitmap imageRounded = Bitmap.createBitmap(mbitmap.getWidth(), mbitmap.getHeight(), mbitmap.getConfig());
        Canvas canvas = new Canvas(imageRounded);
        Paint mpaint = new Paint();
        mpaint.setAntiAlias(true);
        mpaint.setShader(new BitmapShader(mbitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP));
        canvas.drawRoundRect((new RectF(0, 0, mbitmap.getWidth(), mbitmap.getHeight())), 100, 100, mpaint);// Round Image Corner 100 100 100 100
        profile.setImageBitmap(imageRounded);

       /*Picasso.with(this)
                .load("http://simpli-city.in//gloclAPI//image//03072017193744_1878332239_glocl_posted_image.jpg")
                .into(profile);
*/

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

        Bundle bundle = getIntent().getExtras();
        String text= bundle.getString("pos");
if(pos==0){

}else {
    viewPager.setCurrentItem(Integer.parseInt(text));
}





    }

    private void getData() {
        requestQueue.add(getDataFromTheServer(requestcount));
        requestcount++;
    }

    JsonObjectRequest getDataFromTheServer(int requestcount) {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, ITEMURL, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.e("JSON ", response.toString());
                if (response != null) {
                    // progressDialog.dismiss();
                    //  dissmissDialog();
                    ParseJsonFeed(response);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(DefaultRetryPolicy.DEFAULT_TIMEOUT_MS * 4, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        requestQueue.add(jsonObjectRequest);
        return jsonObjectRequest;
    }

    private void ParseJsonFeed(JSONObject response) {
        try {
            JSONArray feedArray = response.getJSONArray("result");

            for (int i = 0; i < feedArray.length(); i++) {
                JSONObject obj = (JSONObject) feedArray.get(i);

                model.setId(obj.getString("id"));
                String image = obj.isNull("productimage") ? null : obj
                        .getString("productimage");
                model.setProductimage(image);

                model.setProducttitle(obj.getString("producttitle"));
               /* model.setLikescount(obj.getInt("likescount"));
                model.setSharecount(obj.getInt("sharecount"));
                model.setMoreimagescount(obj.getString("moreimagescount"));*/
                productlist.add(model);
            }
          //  productAdapter.notifyDataSetChanged();
        } catch (JSONException e) {

        }


    }
    private void setupViewPager(ViewPager viewPager) {

        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new Profiles(), "Profiles");
        adapter.addFragment(new Favourites(), "Favourites");
        adapter.addFragment(new Privileges(), "Privileges");
        adapter.addFragment(new Cart(), "Cart");
        adapter.addFragment(new MyRewards(), "My Rewards");
        viewPager.setAdapter(adapter);

    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            pos = position;
            Log.e("POSITIONNNNN", String.valueOf(pos));
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);


        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
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
                    mBitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), picUri);
                    mBitmap = rotateImageIfRequired(mBitmap, picUri);
                    mBitmap = getResizedBitmap(mBitmap, 500);

                    ImageView croppedImageView = (ImageView) findViewById(R.id.profilePic);
                    croppedImageView.setImageBitmap(mBitmap);
                    profile.setImageBitmap(mBitmap);

                } catch (IOException e) {
                    e.printStackTrace();
                }


            } else {


                bitmap = (Bitmap) data.getExtras().get("data");

                mBitmap = bitmap;
                ImageView croppedImageView = (ImageView) findViewById(R.id.profilePic);
                if (croppedImageView != null) {
                    croppedImageView.setImageBitmap(mBitmap);
                }

                profile.setImageBitmap(mBitmap);

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
}

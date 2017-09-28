package since.since1700.Filter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.squareup.picasso.Picasso;
import java.util.HashMap;
import java.util.List;
import since.since1700.R;
import since.since1700.Fragment.ShopFragments.ShopHomeFragment;

public class NavigationDrawerActivity extends AppCompatActivity implements FragmentDrawer.FragmentDrawerListener {

    //private static String TAG = NavigationDrawerActivityTamil.class.getSimpleName();
    private ExpandableListView mDrawerListView;
    List<String> expandableListTitle;
    HashMap<String, List<String>> expandableListDetail;

    private Toolbar mToolbar;
    private FragmentDrawer drawerFragment;
    private RecyclerView recyclerView;
   // private HomeAdapterEnglish adapter;
   // private List<AlbumEnglish> albumEnglishList;
    ImageView profile;
   // private List<ProductEnglish> productEnglishList;
   // private static final String TAG = MainActivityEnglish.class.getSimpleName();
    private String URL_FEED = "http://simpli-city.in/request2.php?rtype=alldata&key=simples&page=3";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_navigation);

       //  mToolbar = (Toolbar) findViewById(R.id.toolbar);

       // setSupportActionBar(mToolbar);

       // getSupportActionBar().setDisplayShowHomeEnabled(true);
       // getSupportActionBar().setTitle("");
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        LinearLayout recyclerLayout = (LinearLayout) findViewById(R.id.recycler_layout);

        /*albumEnglishList = new ArrayList<>();
        adapter = new HomeAdapterEnglish(this, albumEnglishList);*/

        drawerFragment = (FragmentDrawer) getSupportFragmentManager().findFragmentById(R.id.fragment_navigation_drawer);
        drawerFragment.setUp(R.id.fragment_navigation_drawer, (DrawerLayout) findViewById(R.id.drawer_layout), mToolbar);
        drawerFragment.setDrawerListener(this);



        /*profile = (ImageView) findViewById(R.id.profilePic);
        Picasso.with(this)
                .load("http://simpli-city.in//gloclAPI//image//03072017193744_1878332239_glocl_posted_image.jpg")
                .into(profile);

        profile.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {

        //startActivity(new Intent(NavigationDrawerActivity.this, MyProfileActivityEnglish.class));
    }
     });*/
        // display the first navigation drawer view on app launch
    //displayView();

        /* Cache cache = AppControllerTamil.getInstance().getRequestQueue().getCache();
        Cache.Entry entry = cache.get(URL_FEED);
        if (entry != null) {
            // fetch the data from cache
            try {
                String data = new String(entry.data, "UTF-8");
                try {
                    parseJsonFeed(new JSONObject(data));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }

        } else {
            // making fresh volley request and getting json
            JsonObjectRequest jsonReq = new JsonObjectRequest(Request.Method.GET,
                    URL_FEED, new Response.Listener<JSONObject>() {

                @Override
                public void onResponse(JSONObject response) {
                    VolleyLog.d(TAG, "Response: " + response.toString());
                    if (response != null) {
                        parseJsonFeed(response);
                    }
                }
            }, new Response.ErrorListener() {

                @Override
                public void onErrorResponse(VolleyError error) {
                    VolleyLog.d(TAG, "Error: " + error.getMessage());
                }
            });

            // Adding request to volley request queue
            AppControllerTamil.getInstance().addToRequestQueue(jsonReq);
        }
*/
    }

    /* private void parseJsonFeed(JSONObject response) {
        try {
            JSONArray feedArray = response.getJSONArray("result");

            for (int i = 0; i < feedArray.length(); i++) {
                JSONObject feedObj = (JSONObject) feedArray.get(i);

                ProductEnglish item = new ProductEnglish();
                item.setPid(feedObj.getInt("id"));
                item.setProducts(feedObj.getString("title"));

                // Image might be null sometimes
                String image = feedObj.isNull("image") ? null : feedObj
                        .getString("image");
                item.setProductimage(image);



                productEnglishList.add(item);
            }

            // notify data changes to list adapater
            adapter.notifyDataSetChanged();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
*/
   /* private void prepareAlbums() {
        int[] covers = new int[]{
                R.drawable.grains,
                R.drawable.herbs,
                R.drawable.rices,
                R.drawable.dhals};

        AlbumEnglish a = new AlbumEnglish("Fennel Seeds", 250, covers[0]);
        albumEnglishList.add(a);

        a = new AlbumEnglish("Asafoetida", 250, covers[1]);
        albumEnglishList.add(a);

        a = new AlbumEnglish("Red Chilli Powder", 150, covers[2]);
        albumEnglishList.add(a);

        a = new AlbumEnglish("Black Cardamon", 540, covers[3]);
        albumEnglishList.add(a);

        adapter.notifyDataSetChanged();
    }*/


   /* @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }*/

   /* @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement


        if(id == R.id.action_search){
            startActivity(new Intent(NavigationDrawerActivityTamil.this, MyProfileActivityTamil.class));
            Toast.makeText(getApplicationContext(), "Search action is selected!", Toast.LENGTH_SHORT).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }*/

    @Override
    public void onDrawerItemSelected(View view, int position) {
        displayView();
    }

    private void displayView() {
        Fragment fragment = null;
       // String title = getString(R.string.app_name);

                fragment = new ShopHomeFragment();

        if (fragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.container_body, fragment);
            fragmentTransaction.commit();

            // set the toolbar title
          //  getSupportActionBar().setTitle(title);
        }
    }
}
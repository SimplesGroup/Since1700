package since.since1700.Fragment.Brands.DetailDesign;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTabHost;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TabWidget;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;
import java.util.List;

import since.since1700.Model.FeedProductModel;
import since.since1700.MySingleton;
import since.since1700.R;

/**
 * Created by Kuppusamy on 10/5/2017.
 */

public class DetailPageAccessories extends AppCompatActivity {
    SharedPreferences sharedpreferences;

    public static final String mypreference = "mypref";
    RequestQueue requestQueue;
    ImageLoader imageLoader;
    NetworkImageView banner_brandimage;
    Button follow_button;
    ImageButton back_imagebutton;
    private FragmentTabHost mTabHost;
    TabWidget tabWidget;
    public ViewPager viewPager;
    RecyclerView recyclerView_products;
    int requestcount = 1;
    String ITEMURL = "https://androiddevelopmentnew.000webhostapp.com/productlist.json";
    private TabLayout tabLayout;
    List<FeedProductModel> productlist = new ArrayList<FeedProductModel>();
   // ProductAdapterFeed productAdapter;
    String team;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.brandsdetailpage);
        sharedpreferences = getSharedPreferences(mypreference,
                Context.MODE_PRIVATE);
        requestQueue = Volley.newRequestQueue(getApplicationContext());
        imageLoader = MySingleton.getInstance(this).getImageLoader();
       /* RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView_products = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView_products.setLayoutManager(layoutManager);*/
        Intent get = getIntent();
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);
        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        banner_brandimage = (NetworkImageView) findViewById(R.id.select_brand_image);
        follow_button = (Button) findViewById(R.id.follow_button);
        back_imagebutton = (ImageButton) findViewById(R.id.back_button);
        String fontPath = "fonts/PFBeauSansPro-Reg_0.otf";
        final Typeface opensansfont = Typeface.createFromAsset(getAssets(), fontPath);
        String image = "https://androiddevelopmentnew.000webhostapp.com/brand/hermes.jpg";
        follow_button.setTypeface(opensansfont);
        Intent pos = getIntent();
     //   final String team = pos.getStringExtra("POSITION");
//        Log.e("getposition",team);

        if(team=="0"){

        }



        follow_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                follow_button.setText("Following");
            }
        });
        banner_brandimage.setImageUrl(image, imageLoader);
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
        /*getData();
        productAdapter = new ProductAdapterFeed(productlist, recyclerView_products);
        recyclerView_products.setAdapter(productAdapter);*/



        back_imagebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


    }
    private void setupViewPager(ViewPager viewPager) {

      ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new FeedbrandAccessories(), "Feed");
        adapter.addFragment(new ShopdetailBrandAccessories(), "Shop");

        Bundle data = new Bundle();//create bundle instance
        data.putString("POSITION",team);
        new ShopdetailBrand().setArguments(data);

        /*Bundle bundle = new Bundle();
        bundle.putString("POSITION", team);
        ShopdetailBrand fragment=new ShopdetailBrand();
        fragment.setArguments(bundle);*/
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
        //    pos = position;
          //  Log.e("POSITIONNNNN", String.valueOf(pos));
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
   /* private void getData() {
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
                FeedProductModel model = new FeedProductModel();
                model.setId(obj.getString("id"));
                String image = obj.isNull("productimage") ? null : obj
                        .getString("productimage");
                model.setProductimage(image);
                model.setProducttitle(obj.getString("producttitle"));
               *//* model.setLikescount(obj.getInt("likescount"));
                model.setSharecount(obj.getInt("sharecount"));
                model.setMoreimagescount(obj.getString("moreimagescount"));*//*
                productlist.add(model);
            }
            productAdapter.notifyDataSetChanged();
        } catch (JSONException e) {

        }


    }


    static class Itemviewholder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageButton selectbutton;
        NetworkImageView productimage;
        ImageView hoverimage;
        TextView productitle;
        RecyclerView_OnClickListener.OnClickListener recyclerView_onClickListener;

        public Itemviewholder(View itemView) {
            super(itemView);
            productimage = (NetworkImageView) itemView.findViewById(R.id.feedImage);
            // hoverimage=(ImageView)itemView.findViewById(R.id.hoverimage);
            // productitle=(TextView)itemView.findViewById(R.id.product_category_name);
            // selectbutton=(ImageButton) itemView.findViewById(R.id.select_item_imagebutton);
        }

        @Override
        public void onClick(View v) {
// setting custom listener
            if (recyclerView_onClickListener != null) {
                recyclerView_onClickListener.OnItemClick(v, getAdapterPosition());

            }
        }

        public void setClickListener(
                RecyclerView_OnClickListener.OnClickListener onClickListener) {
            this.recyclerView_onClickListener = recyclerView_onClickListener;
        }
    }

    static class LoadingViewHolder extends RecyclerView.ViewHolder {
        public ProgressBar progressBar;

        public LoadingViewHolder(View itemView) {
            super(itemView);
            progressBar = (ProgressBar) itemView.findViewById(R.id.progressBar1);
        }
    }

    public class ProductAdapterFeed extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
        LayoutInflater inflater;

        ImageLoader mImageLoader;
        private final int VIEW_TYPE_ITEM = 1;
        private final int VIEW_TYPE_LOADING = 3;

        boolean loading;
        OnLoadMoreListener onLoadMoreListener;

        private int visibleThreshold = 5;
        private int lastVisibleItem, totalItemCount;

        private int currentvisiblecount;
        public List<FeedProductModel> productlist;

        public ProductAdapterFeed(List<FeedProductModel> productlist, RecyclerView recyclerView) {
            this.productlist = productlist;

            if (recyclerView.getLayoutManager() instanceof LinearLayoutManager) {

                final LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView
                        .getLayoutManager();


                recyclerView
                        .addOnScrollListener(new RecyclerView.OnScrollListener() {
                            @Override
                            public void onScrolled(RecyclerView recyclerView,
                                                   int dx, int dy) {
                                super.onScrolled(recyclerView, dx, dy);

                                totalItemCount = linearLayoutManager.getItemCount();
                                lastVisibleItem = linearLayoutManager
                                        .findLastVisibleItemPosition();
                                currentvisiblecount = linearLayoutManager.findLastVisibleItemPosition();
                           *//* if(lastVisibleItem>=10){
                                fab.setVisibility(View.VISIBLE);
                            }else {
                                fab.setVisibility(View.GONE);
                            }*//*
                                if (!loading
                                        && totalItemCount <= (lastVisibleItem + visibleThreshold)) {
                                    // End has been reached
                                    // Do something
                                    if (onLoadMoreListener != null) {
                                        onLoadMoreListener.onLoadMore();
                                    }
                                    loading = true;
                                }
                            }
                        });
            }
        }


        @Override
        public int getItemCount() {
            return productlist.size();
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            RecyclerView.ViewHolder viewHolder = null;
            if (viewType == VIEW_TYPE_ITEM) {
                View view = LayoutInflater.from(getApplicationContext()).inflate(R.layout.feed_home_item, parent, false);
                return new Itemviewholder(view);
            } else if (viewType == VIEW_TYPE_LOADING) {
                View view = LayoutInflater.from(getApplicationContext()).inflate(R.layout.layout_loading_item, parent, false);
                return new LoadingViewHolder(view);
            }
            return null;
        }

        @Override
        public int getItemViewType(int position) {
            return productlist.get(position) == null ? VIEW_TYPE_LOADING : VIEW_TYPE_ITEM;
        }

        public void setOnLoadMoreListener(OnLoadMoreListener onLoadMoreListener) {
            this.onLoadMoreListener = onLoadMoreListener;
        }

        public void setLoaded() {
            loading = false;
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            if (holder instanceof Itemviewholder) {
                if (mImageLoader == null)
                    mImageLoader = MySingleton.getInstance(getApplicationContext()).getImageLoader();

                final Itemviewholder userViewHolder = (Itemviewholder) holder;
                FeedProductModel itemModel = productlist.get(position);
                final int pos = position;

                String fontPath = "fonts/OpenSans-Regular.ttf";
                final Typeface opensansfont = Typeface.createFromAsset(getApplicationContext().getAssets(), fontPath);

                userViewHolder.productimage.setImageUrl(itemModel.getProductimage(), mImageLoader);
                *//*userViewHolder.productimage.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent next = new Intent(getApplicationContext(), DetailPage.class);
                        next.putExtra("IMAGE", "https://androiddevelopmentnew.000webhostapp.com/cars.png");
                        startActivity(next);
                    }
                });*//*


            } else {
                if (holder instanceof LoadingViewHolder) {
                    LoadingViewHolder loadingViewHolder = (LoadingViewHolder) holder;
                    loadingViewHolder.progressBar.setIndeterminate(true);
                }
            }
        }
*/








    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}

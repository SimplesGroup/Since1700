package since.since1700.Fragment.ShopFragments;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import since.since1700.CustomVolleyRequest;
import since.since1700.DetailPage;
import since.since1700.Filter.NavigationDrawerActivity;
import since.since1700.OnLoadMoreListener;
import since.since1700.R;

/**
 * Created by Sandhiya on 9/21/2017.
 */

public class ShopCategoryFragment extends Fragment {

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    ImageView filter;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private CategoryAdapter categoryadapter;
    List<CategoryModel> categorymodellist=new ArrayList<CategoryModel>();
    RequestQueue requestQueue;
    int requestcount=1;
    String ITEMURL="https://androiddevelopmentnew.000webhostapp.com/productlist.json";
    private boolean loading;
    protected Handler handler;
    ProgressDialog pdialog;

    @Nullable
    public static ShopCategoryFragment newInstance() {
        ShopCategoryFragment fragment = new ShopCategoryFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.shop_category_fragment, container, false);
        toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);

        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);

        filter = (ImageView) getActivity().findViewById(R.id.filter);
       /* filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Successssss", Toast.LENGTH_LONG).show();
                Intent i = new Intent(getActivity(), NavigationDrawerActivity.class);
                startActivity(i);
            }
        });*/
      //  viewPager = (ViewPager) view.findViewById(R.id.viewpager);
      //  setupViewPager(viewPager);
        requestQueue= Volley.newRequestQueue(getActivity());
        handler = new Handler();
       // tabLayout = (TabLayout) view.findViewById(R.id.tabs);
       // tabLayout.setupWithViewPager(viewPager);

        pdialog = new ProgressDialog(getActivity());
        pdialog.show();
        pdialog.setContentView(R.layout.custom_progressdialog);
        pdialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
GetData();
        mLayoutManager=new LinearLayoutManager(getActivity());
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(mLayoutManager);
        categoryadapter = new CategoryAdapter(getActivity(),categorymodellist,recyclerView);
        recyclerView.setAdapter(categoryadapter);
        return view;
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getActivity().getSupportFragmentManager());
        adapter.addFragment(new ShopCategoryFragment(), "Featured");
        adapter.addFragment(new ShopHomeFragment(), "Offer");
        adapter.addFragment(new ShopCategoryFragment(), "Cars");
        adapter.addFragment(new ShopPopularFragment(), "Bike");
        adapter.addFragment(new ShopOnSaleFragment(), "Jet");
        adapter.addFragment(new ShopOnSaleFragment(), "Jewellery");



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
    private void GetData() {
        //Adding the method to the queue by calling the method getDataFromServer
        requestQueue.add(getDataFromTheServer(requestcount));
        // getDataFromTheServer();
        //Incrementing the request counter
        requestcount++;
    }

    public void dissmissDialog() {
        // TODO Auto-generated method stub
        if (pdialog != null) {
            if (pdialog.isShowing()) {
                pdialog.dismiss();
            }
            pdialog = null;
        }

    }
    private JsonObjectRequest getDataFromTheServer(final int requestcount){
        JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.GET, ITEMURL, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                if (response != null) {
                  dissmissDialog();
                    ParseJsonFeed(response);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(DefaultRetryPolicy.DEFAULT_TIMEOUT_MS * 5, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        requestQueue.add(jsonObjectRequest);
        return jsonObjectRequest;
    }

    private void ParseJsonFeed(JSONObject response) {
        try {
            JSONArray feedArray = response.getJSONArray("result");

            for (int i = 0; i < feedArray.length(); i++) {
                JSONObject obj = (JSONObject) feedArray.get(i);
                CategoryModel model=new CategoryModel();

                String image = obj.isNull("productimage") ? null : obj
                        .getString("productimage");
                model.setFeedimage(image);
                Log.e("JSON",model.getFeedimage().toString());
                categorymodellist.add(model);
            }
            categoryadapter.notifyDataSetChanged();
        } catch (JSONException e) {

        }
    }
    static class LoadingViewHolder extends RecyclerView.ViewHolder {
        public ProgressBar progressBar;

        public LoadingViewHolder(View itemView) {
            super(itemView);
            progressBar = (ProgressBar) itemView.findViewById(R.id.progressBar1);
        }
    }
    public class CategoryAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

        Context context;
        List<CategoryModel> categoryList=new ArrayList<CategoryModel>();
        private OnLoadMoreListener onLoadMoreListener;
        private final int VIEW_TYPE_ITEM = 1;
        private final int VIEW_TYPE_LOADING = 3;
        private int visibleThreshold = 5;
        private int lastVisibleItem, totalItemCount;
        private boolean loading;
        ImageLoader mImageLoader;


        public CategoryAdapter(Context context, List<CategoryModel> list, RecyclerView recyclerView) {
            this.categoryList = list;
            this.context = context;

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
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            Log.e("HOMEEEEEEEEE", "SSSSSSS");



            if (viewType == VIEW_TYPE_ITEM) {
                View view = LayoutInflater.from(getActivity()).inflate(R.layout.feed_category_item, parent, false);
                return new CategoryAdapter.MyViewHolder(view);
            } else if (viewType == VIEW_TYPE_LOADING) {
                View view = LayoutInflater.from(getActivity()).inflate(R.layout.layout_loading_item, parent, false);
                return new LoadingViewHolder(view);
            }
            return  null;
        }

        @Override
        public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {



            if (holder instanceof CategoryAdapter.MyViewHolder) {

                final CategoryModel model = categoryList.get(position);
                if (mImageLoader == null)
                    mImageLoader = CustomVolleyRequest.getInstance(getActivity()).getImageLoader();
                final CategoryAdapter.MyViewHolder userViewHolder = (CategoryAdapter.MyViewHolder) holder;

                userViewHolder.feedimage.setImageUrl("http://simpli-city.in//gloclAPI//image//03072017193744_1878332239_gloc" + "l_posted_image.jpg",mImageLoader);
                userViewHolder.feedimage.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(getActivity(), DetailPage.class);
                        startActivity(i);
                    }
                });
            }
            else {
                ((ProgressViewHolder) holder).progressBar.setIndeterminate(true);
            }


        }



        @Override
        public int getItemCount() {
            return categoryList.size();
        }

        public void setOnLoadMoreListener(OnLoadMoreListener onLoadMoreListener) {
            this.onLoadMoreListener = onLoadMoreListener;
        }
        @Override
        public int getItemViewType(int position) {
            return categoryList.get(position) == null ? VIEW_TYPE_LOADING : VIEW_TYPE_ITEM;
        }

        public void setLoaded() {
            loading = false;
        }
        public  class MyViewHolder extends RecyclerView.ViewHolder {
            public NetworkImageView feedimage;





            public MyViewHolder(View v) {
                super(v);
                feedimage = (NetworkImageView) v.findViewById(R.id.feedImage);


            }
        }

        public  class ProgressViewHolder extends RecyclerView.ViewHolder {
            public ProgressBar progressBar;

            public ProgressViewHolder(View v) {
                super(v);
                progressBar = (ProgressBar) v.findViewById(R.id.progressBar1);
            }
        }

    }





    public  class CategoryModel {

        String feedimage;
        String postedby;
        String name;
        String product;

        public String getPostedby() {
            return postedby;
        }

        public void setPostedby(String postedby) {
            this.postedby = postedby;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getProduct() {
            return product;
        }

        public void setProduct(String product) {
            this.product = product;
        }

        public String getFeedimage() {
            return feedimage;
        }

        public void setFeedimage(String feedimage) {
            this.feedimage = feedimage;
        }
    }

}

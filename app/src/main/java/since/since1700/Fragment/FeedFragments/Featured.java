package since.since1700.Fragment.FeedFragments;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

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

import since.since1700.DetailPage;
import since.since1700.Model.FeedProductModel;
import since.since1700.MySingleton;
import since.since1700.OnLoadMoreListener;
import since.since1700.R;
import since.since1700.RecyclerView_OnClickListener;

/**
 * Created by Kuppusamy on 9/26/2017.
 */

public class Featured extends Fragment {
    private boolean isFragmentLoaded=false;

    ProgressDialog progressDialog;
    Spinner sort,filter;
    SharedPreferences sharedpreferences;
    RecyclerView recyclerView_products;
    public static final String mypreference = "mypref";
    RequestQueue requestQueue;
    int requestcount=1;
    String ITEMURL="https://androiddevelopmentnew.000webhostapp.com/productlist.json";

    List<FeedProductModel> productlist=new ArrayList<FeedProductModel>();
    ProductAdapterFeed productAdapter;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //requestQueue = Volley.newRequestQueue(getActivity());
    }
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(true);
        if (isVisibleToUser && !isFragmentLoaded ) {
            Log.e("TAB:","TamilALL");
            isFragmentLoaded = true;
        }else {

        }
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fee_child_feartured,container,false);

        sharedpreferences = getActivity(). getSharedPreferences(mypreference,
                Context.MODE_PRIVATE);
        requestQueue= Volley.newRequestQueue(getActivity());
        String fontPath = "fonts/PFBeauSansPro-Reg_0.otf";
        final Typeface opensansfont = Typeface.createFromAsset(getActivity().getAssets(), fontPath);
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(getActivity());
        recyclerView_products=(RecyclerView)view.findViewById(R.id.recycler_feed_view);
        recyclerView_products.setLayoutManager(layoutManager);
        Log.e("TAB","FEATURED");
        Log.e("TAB", "1");
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.show();
        progressDialog.setContentView(R.layout.custom_progressdialog);
        progressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        sort = (Spinner)view.findViewById(R.id.spin_sort);
        filter = (Spinner)view.findViewById(R.id.spin_filter);
        getData();
        productAdapter = new ProductAdapterFeed(productlist,recyclerView_products);
        recyclerView_products.setAdapter(productAdapter);

        sort.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                 Log.e("TAB", String.valueOf(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        filter.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.e("TAB", String.valueOf(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        return view;
    }

    private void getData(){
        requestQueue.add(getDataFromTheServer(requestcount));
        requestcount++;
    }
    JsonObjectRequest getDataFromTheServer(int requestcount){
        JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.GET, ITEMURL, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.e("JSON ",response.toString());
                if (response != null) {
                    progressDialog.dismiss();
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
                FeedProductModel model=new FeedProductModel();
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
            productAdapter.notifyDataSetChanged();
        } catch (JSONException e) {

        }
    }


    static  class Itemviewholder extends RecyclerView.ViewHolder implements View.OnClickListener{
        ImageButton selectbutton;
        NetworkImageView productimage;
        ImageView hoverimage ,likebutton;
        TextView productitle;
        RecyclerView_OnClickListener.OnClickListener recyclerView_onClickListener;
        public Itemviewholder(View itemView) {
            super(itemView);
            productimage=(NetworkImageView)itemView.findViewById(R.id.product_category_image);
            // hoverimage=(ImageView)itemView.findViewById(R.id.hoverimage);
            likebutton=(ImageView)itemView.findViewById(R.id.likebutton);
            productitle=(TextView)itemView.findViewById(R.id.product_category_name);
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
    public  class ProductAdapterFeed extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
        LayoutInflater inflater;

        ImageLoader mImageLoader;
        private final int VIEW_TYPE_ITEM = 1;
        private final int VIEW_TYPE_LOADING = 3;

        boolean loading;
        OnLoadMoreListener onLoadMoreListener;

        private int visibleThreshold = 5;
        private int lastVisibleItem, totalItemCount;

        private  int currentvisiblecount;
        public  List<FeedProductModel> productlist;

        public ProductAdapterFeed(List<FeedProductModel> productlist, RecyclerView recyclerView) {
            this.  productlist = productlist;

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
                                currentvisiblecount=linearLayoutManager.findLastVisibleItemPosition();
                           /* if(lastVisibleItem>=10){
                                fab.setVisibility(View.VISIBLE);
                            }else {
                                fab.setVisibility(View.GONE);
                            }*/
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
                View view = LayoutInflater.from(getActivity()).inflate(R.layout.feed_item_mainfeed_list, parent, false);
                return  new Itemviewholder(view);
            } else if (viewType == VIEW_TYPE_LOADING) {
                View view = LayoutInflater.from(getActivity()).inflate(R.layout.layout_loading_item, parent, false);
                return new LoadingViewHolder(view);
            }
            return null;
        }

        @Override
        public int getItemViewType(int position) {
            return                                                                                                                                                                productlist.get(position)==null ? VIEW_TYPE_LOADING : VIEW_TYPE_ITEM;
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
                    mImageLoader = MySingleton.getInstance(getActivity()).getImageLoader();

                final Itemviewholder userViewHolder = (Itemviewholder) holder;
                FeedProductModel itemModel=productlist.get(position);
                final int    pos = position;

                String fontPath = "fonts/OpenSans-Regular.ttf";
                final Typeface opensansfont = Typeface.createFromAsset(getActivity().getAssets(), fontPath);
                userViewHolder.productitle.setText(itemModel.getProducttitle());
                userViewHolder.likebutton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        userViewHolder.likebutton.setImageResource(R.mipmap.heartred);

                    }
                });
                userViewHolder.productitle.setTypeface(opensansfont);
                userViewHolder.productimage.setImageUrl(itemModel.getProductimage(),mImageLoader);

                userViewHolder.productimage.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(getActivity(), DetailPage.class);
                        startActivity(i);
                    }
                });

            }else {
                if (holder instanceof LoadingViewHolder) {
                    LoadingViewHolder loadingViewHolder = (LoadingViewHolder) holder;
                    loadingViewHolder.progressBar.setIndeterminate(true);
                }
            }
        }



    }



}

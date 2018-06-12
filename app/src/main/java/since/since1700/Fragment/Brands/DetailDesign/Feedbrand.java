package since.since1700.Fragment.Brands.DetailDesign;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
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

import since.since1700.Fragment.Brands.Featured_brands;
import since.since1700.Fragment.FeedFragment;
import since.since1700.Model.FeedProductModel;
import since.since1700.MySingleton;
import since.since1700.OnLoadMoreListener;
import since.since1700.R;
import since.since1700.RecyclerView_OnClickListener;

/**
 * Created by Kuppusamy on 10/5/2017.
 */

public class Feedbrand extends Fragment {

    @Nullable
    public static Feedbrand newInstance() {
        Feedbrand fragment = new Feedbrand();
        return fragment;
    }
    ImageLoader imageLoader;
    RecyclerView recyclerView_products;
    int requestcount = 1;
    RequestQueue requestQueue;
   public  String ITEMURL;

    List<FeedProductModel> productlist = new ArrayList<FeedProductModel>();
     ProductAdapterFeed productAdapter;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.brands_detail_feed_page,container,false);
        requestQueue = Volley.newRequestQueue(getActivity());
        imageLoader = MySingleton.getInstance(getActivity()).getImageLoader();
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView_products = (RecyclerView) view.findViewById(R.id.recycler_view);
        recyclerView_products.setLayoutManager(layoutManager);

        productAdapter = new ProductAdapterFeed(productlist, recyclerView_products);
        recyclerView_products.setAdapter(productAdapter);
        Bundle pos = getActivity().getIntent().getExtras();
        final String team = pos.getString("URL");

        ITEMURL = team;
        Log.e("getpositionfeedsssss",ITEMURL);
        getData();
        return view;
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
                FeedProductModel model = new FeedProductModel();
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


    static class Itemviewholder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageButton selectbutton;
        NetworkImageView productimage;
        Button claim;
        ImageView hoverimage;
        TextView productitle;
        RecyclerView_OnClickListener.OnClickListener recyclerView_onClickListener;

        public Itemviewholder(View itemView) {
            super(itemView);
            productimage = (NetworkImageView) itemView.findViewById(R.id.product_category_image);
            // hoverimage=(ImageView)itemView.findViewById(R.id.hoverimage);
            // productitle=(TextView)itemView.findViewById(R.id.product_category_name);
          claim=(Button) itemView.findViewById(R.id.rsvp);
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
                View view = LayoutInflater.from(getActivity()).inflate(R.layout.brand_feed_mainfeed_list, parent, false);
                return  new Itemviewholder(view);
            } else if (viewType == VIEW_TYPE_LOADING) {
                View view = LayoutInflater.from(getActivity()).inflate(R.layout.layout_loading_item, parent, false);
                return new LoadingViewHolder(view);
            }
            return null;
        }

        @Override
        public int getItemViewType(int position) {
            return productlist.get(position)==null ? VIEW_TYPE_LOADING : VIEW_TYPE_ITEM;
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
userViewHolder.claim.setVisibility(View.GONE);
                userViewHolder.productimage.setImageUrl(itemModel.getProductimage(),mImageLoader);
                /*userViewHolder.productimage.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent next=new Intent(getActivity(), DetailPage.class);
                        next.putExtra("IMAGE","https://androiddevelopmentnew.000webhostapp.com/cars.png");
                        startActivity(next);
                    }
                });*/


            }else {
                if (holder instanceof LoadingViewHolder) {
                    LoadingViewHolder loadingViewHolder = (LoadingViewHolder) holder;
                    loadingViewHolder.progressBar.setIndeterminate(true);
                }
            }
        }



    }

}


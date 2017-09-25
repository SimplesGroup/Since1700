package since.since1700.Fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import since.since1700.OnLoadMoreListener;
import since.since1700.R;

/**
 * Created by Sandhiya on 9/21/2017.
 */

 public class ShopHomeFragment extends Fragment {

    private static final String TAG = String.valueOf(ShopHomeFragment.class);
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private HomeAdapter homeadapter;
    RequestQueue requestQueue;
    private ArrayList<HomeModel> feedimageList = new ArrayList<HomeModel>();
    int requestcount=1;
    String ITEMURL="https://androiddevelopmentnew.000webhostapp.com/productlist.json";
    private boolean loading;
    protected Handler handler;



    @Nullable
    public static ShopHomeFragment newInstance() {
        ShopHomeFragment fragment = new ShopHomeFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.feed_shop_home_fragment, container, false);

        mLayoutManager=new LinearLayoutManager(getActivity());
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(mLayoutManager);
        homeadapter = new HomeAdapter(getActivity(),feedimageList,recyclerView);
        recyclerView.setAdapter(homeadapter);

        requestQueue= Volley.newRequestQueue(getActivity());

        GetData();

        homeadapter.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                //add null , so the adapter will check view_type and show progress bar at bottom
                feedimageList.add(null);
                homeadapter.notifyItemInserted(feedimageList.size() - 1);

                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //   remove progress item
                        feedimageList.remove(feedimageList.size() - 1);
                        homeadapter.notifyItemRemoved(feedimageList.size());
                        //add items one by one
                        int start = feedimageList.size();
                        int end = start + 5;

                        for (int i = start + 1; i <= end; i++) {
                            GetData();
                            homeadapter.notifyItemInserted(feedimageList.size());
                        }
                        homeadapter.setLoaded();
                        //or you can add all at once but do not forget to call mAdapter.notifyDataSetChanged();
                    }
                }, 2000);

            }
        });


        return view;
    }


    private void GetData() {
        //Adding the method to the queue by calling the method getDataFromServer
        requestQueue.add(getDataFromTheServer(requestcount));
        // getDataFromTheServer();
        //Incrementing the request counter
        requestcount++;
    }
    private JsonObjectRequest getDataFromTheServer(final int requestcount){
        JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.GET, ITEMURL, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                if (response != null) {
Log.e("JSON",response.toString());
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
                HomeModel model=new HomeModel();

                String image = obj.isNull("productimage") ? null : obj
                        .getString("productimage");
                model.setFeedimage(image);

                feedimageList.add(model);
            }
            homeadapter.notifyDataSetChanged();
        } catch (JSONException e) {

        }
    }

public class HomeAdapter extends RecyclerView.Adapter{

    Context context;
    List<HomeModel> feedimageList=new ArrayList<HomeModel>();
    private OnLoadMoreListener onLoadMoreListener;
    private final int VIEW_ITEM = 1;
    private final int VIEW_PROG = 0;
    private int visibleThreshold = 5;
    private int lastVisibleItem, totalItemCount;
    private boolean loading;
    public ImageView feedimage;


    public HomeAdapter(Context context,List<HomeModel> list,RecyclerView recyclerView) {
        this.feedimageList = list;
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

    public class MyViewHolder extends RecyclerView.ViewHolder {


        public MyViewHolder(View view) {
            super(view);
            feedimage = (ImageView) view.findViewById(R.id.feedImage);


        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        Log.e("HOMEEEEEEEEE", "SSSSSSS");


        RecyclerView.ViewHolder vh;
        if (viewType == VIEW_ITEM) {
            View v = LayoutInflater.from(parent.getContext()).inflate(
                    R.layout.feed_home_item, parent, false);

            vh = new MyViewHolder(v);
        } else {
            View v = LayoutInflater.from(parent.getContext()).inflate(
                    R.layout.progressbar_item, parent, false);

            vh = new ProgressViewHolder(v);
        }
        return vh;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {

        final HomeModel model = feedimageList.get(position);
        feedimage = (ImageView) recyclerView.findViewById(R.id.feedImage);

       feedimage.setImageURI(Uri.parse(model.getFeedimage()));

    }
    public List<HomeModel> getFeedImageList() {
        return feedimageList;
    }

    @Override
    public int getItemCount() {
        return feedimageList.size();
    }

    public void setOnLoadMoreListener(OnLoadMoreListener onLoadMoreListener) {
        this.onLoadMoreListener = onLoadMoreListener;
    }

    public void setLoaded() {
        loading = false;
    }


    public  class ProgressViewHolder extends RecyclerView.ViewHolder {
        public ProgressBar progressBar;

        public ProgressViewHolder(View v) {
            super(v);
            progressBar = (ProgressBar) v.findViewById(R.id.progressBar1);
        }
    }

}

    public  class HomeModel {

        String feedimage;

        public String getFeedimage() {
            return feedimage;
        }

        public void setFeedimage(String feedimage) {
            this.feedimage = feedimage;
        }
    }
}
package since.since1700;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
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

/**
 * Created by Kuppusamy on 9/19/2017.
 */

public class ChooseyourInterest extends AppCompatActivity {
    Button button_next;
    SharedPreferences sharedpreferences;
    RecyclerView recyclerView_select_yourinterest;
    public static final String mypreference = "mypref";
    TextView textview_chooseyour_interest_title;

    RequestQueue requestQueue;
    int requestcount=1;
    String ITEMURL="https://androiddevelopmentnew.000webhostapp.com/productlist.json";
ProgressDialog pdialog;
    List<ItemModel>productlist=new ArrayList<ItemModel>();
    ProductAdapter productAdapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.chooseyourinterest);
        sharedpreferences =  getSharedPreferences(mypreference,
                Context.MODE_PRIVATE);
requestQueue= Volley.newRequestQueue(ChooseyourInterest.this);
        String fontPath = "fonts/OpenSans-Regular.ttf";
        final Typeface opensansfont = Typeface.createFromAsset(getAssets(), fontPath);

        textview_chooseyour_interest_title=(TextView)findViewById(R.id.textview_chooseyourinterest);
        button_next=(Button)findViewById(R.id.button_next);
        recyclerView_select_yourinterest=(RecyclerView)findViewById(R.id.recylerview_chooseyour_interest);

        textview_chooseyour_interest_title.setTypeface(opensansfont);
        button_next.setTypeface(opensansfont);

        textview_chooseyour_interest_title.setText("Choose Your Interests");
        button_next.setText("NEXT");

        RecyclerView.LayoutManager layoutManager=new GridLayoutManager(getApplicationContext(),3);
        recyclerView_select_yourinterest.setLayoutManager(layoutManager);

        pdialog = new ProgressDialog(this);
        pdialog.show();
        pdialog.setContentView(R.layout.custom_progressdialog);
        pdialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
GetData();
        productAdapter=new ProductAdapter(productlist,recyclerView_select_yourinterest);
        recyclerView_select_yourinterest.setAdapter(productAdapter);


        productAdapter.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                Log.e("haint", "Load More");


                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Log.e("haint", "Load More 2");
                    //    GetData();


                        //productAdapter.setLoaded();
                    }
                }, 2000);
            }
        });

        button_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String data = "";
                List<ItemModel> stList = ((ProductAdapter) productAdapter)
                        .getItemlist();

                for (int i = 0; i < stList.size(); i++) {
                    ItemModel singleStudent = stList.get(i);
                    if (singleStudent.getSelected() == true) {

                        data = data + "\n" + singleStudent.getProductname().toString();

                    }

                }

                /*Toast.makeText(getApplicationContext(),
                        "Selected items: \n" + data, Toast.LENGTH_LONG)
                        .show();*/
                Intent in=new Intent(getApplicationContext(),MainActivity.class);
                startActivity(in);
            }
        });

    }
    private void GetData() {
        //Adding the method to the queue by calling the method getDataFromServer
        requestQueue.add(getDataFromTheServer(requestcount));
        // getDataFromTheServer();
        //Incrementing the request counter
        requestcount++;
    }
    private JsonObjectRequest getDataFromTheServer(int requestcount){
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
                ItemModel model=new ItemModel();
                model.setId(obj.getString("id"));
                String image = obj.isNull("productimage") ? null : obj
                        .getString("productimage");
                model.setProductimage(image);
                model.setProductname(obj.getString("producttitle"));
                productlist.add(model);
            }
            productAdapter.notifyDataSetChanged();
        } catch (JSONException e) {

        }


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
    public class ItemModel{
String id,productname,productimage;
        private boolean isSelected;

        public boolean getSelected() {
            return isSelected;
        }

        public void setSelected(boolean selected) {
            isSelected = selected;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getProductimage() {
            return productimage;
        }

        public void setProductimage(String productimage) {
            this.productimage = productimage;
        }

        public String getProductname() {
            return productname;
        }

        public void setProductname(String productname) {
            this.productname = productname;
        }

    }
    static  class Itemviewholder extends RecyclerView.ViewHolder{
        ImageButton selectbutton;
        NetworkImageView productimage;
        ImageView hoverimage;
        TextView productitle;
        public Itemviewholder(View itemView) {
            super(itemView);
            productimage=(NetworkImageView)itemView.findViewById(R.id.product_category_image);
            hoverimage=(ImageView)itemView.findViewById(R.id.hoverimage);
            productitle=(TextView)itemView.findViewById(R.id.product_category_name);
            selectbutton=(ImageButton) itemView.findViewById(R.id.select_item_imagebutton);
        }
    }

    static class LoadingViewHolder extends RecyclerView.ViewHolder {
        public ProgressBar progressBar;

        public LoadingViewHolder(View itemView) {
            super(itemView);
            progressBar = (ProgressBar) itemView.findViewById(R.id.progressBar1);
        }
    }
public  class ProductAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{



    ImageLoader mImageLoader;
    private final int VIEW_TYPE_ITEM = 1;
    private final int VIEW_TYPE_LOADING = 3;
    boolean loading;
    OnLoadMoreListener onLoadMoreListener;
    private int visibleThreshold = 5;
    private int lastVisibleItem, totalItemCount;
    private  int currentvisiblecount;
    public  List<ItemModel> productlist;


    public ProductAdapter(List<ItemModel> productlist, RecyclerView recyclerView) {
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
        if (viewType == VIEW_TYPE_ITEM) {
            View view = LayoutInflater.from(getApplicationContext()).inflate(R.layout.feed_item_chooseyourinterest, parent, false);
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
                mImageLoader = CustomVolleyRequest.getInstance(getApplicationContext()).getImageLoader();

            final Itemviewholder userViewHolder = (Itemviewholder) holder;
            ItemModel itemModel=productlist.get(position);
        final int    pos = position;

            String fontPath = "fonts/OpenSans-Regular.ttf";
            final Typeface opensansfont = Typeface.createFromAsset(getAssets(), fontPath);
             userViewHolder.productitle.setText(itemModel.getProductname());
            userViewHolder.productitle.setTypeface(opensansfont);
            userViewHolder.productimage.setImageUrl(itemModel.getProductimage(),mImageLoader);


   userViewHolder.productimage.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {


        if (productlist.get(pos).getSelected()) {
            userViewHolder.selectbutton.setVisibility(View.INVISIBLE);
            userViewHolder.hoverimage.setVisibility(View.INVISIBLE);
            productlist.get(pos).setSelected(false);
        } else {
          //  Toast.makeText(getApplicationContext(), productlist.get(pos).getProductname() + " selected!", Toast.LENGTH_SHORT).show();
            userViewHolder.hoverimage.setVisibility(View.VISIBLE);
            userViewHolder.selectbutton.setVisibility(View.VISIBLE);
            userViewHolder.selectbutton.setBackgroundResource(R.mipmap.tickblue);
            productlist.get(pos).setSelected(true);
        }
    }
});
   userViewHolder.selectbutton.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        if (productlist.get(pos).getSelected()) {
            userViewHolder.hoverimage.setVisibility(View.INVISIBLE);
            userViewHolder.selectbutton.setVisibility(View.INVISIBLE);
            productlist.get(pos).setSelected(false);
        } else {
            userViewHolder.hoverimage.setVisibility(View.VISIBLE);
           // Toast.makeText(getApplicationContext(), productlist.get(pos).getProductname() + " selected!", Toast.LENGTH_SHORT).show();
            userViewHolder.selectbutton.setVisibility(View.VISIBLE);
            userViewHolder.selectbutton.setBackgroundResource(R.mipmap.tickblue);
            productlist.get(pos).setSelected(true);
        }
    }
});
        }else {
            if (holder instanceof LoadingViewHolder) {
                LoadingViewHolder loadingViewHolder = (LoadingViewHolder) holder;
                loadingViewHolder.progressBar.setIndeterminate(true);
            }
        }
    }
    public List<ItemModel> getItemlist() {
        return productlist;
    }


}


}

package since.since1700;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebView;
import android.widget.ImageView;

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

import since.since1700.Model.FeedProductModel;

/**
 * Created by Sandhiya on 10/21/2017.
 */

public class DetailPage extends AppCompatActivity {
    public static final String mypreference = "mypref";
    RequestQueue requestQueue;
    int requestcount=1;
    String ITEMURL="https://androiddevelopmentnew.000webhostapp.com/productlist.json";
    ProgressDialog progressDialog;
    List<ProductModel> productlist=new ArrayList<ProductModel>();
    NetworkImageView feedimage;
    ProductModel model=new ProductModel();
    ImageLoader mImageLoader;
    WebView webone,webtwo,webthree;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.detailpage_layout);
        requestQueue= Volley.newRequestQueue(getApplicationContext());
        progressDialog = new ProgressDialog(getApplicationContext());
//        progressDialog.show();
        progressDialog.setContentView(R.layout.custom_progressdialog);
        progressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        mImageLoader = MySingleton.getInstance(getApplicationContext()).getImageLoader();
        getData();

        feedimage = (NetworkImageView) findViewById(R.id.product_category_image);
        webone = (WebView)findViewById(R.id.webview_one);
        ;
   //     feedimage.setImageResource(R.drawable.background);

//        Log.e("IMAGEVIEWDATE",model.getProductimage());
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

                model.setId(obj.getString("id"));
                String image = obj.isNull("productimage") ? null : obj
                        .getString("productimage");
                model.setProductimage(image);

                feedimage.setImageUrl(model.getProductimage(),mImageLoader);
                model.setProducttitle(obj.getString("producttitle"));


                String descrition = obj.isNull("producttitle") ? null : obj
                        .getString("producttitle");
                String ss = descrition;
                String s = ss;
                // s = s.replace("\"", "'");
                s = s.replace("\\", "");

                // description.setText(Html.fromHtml(s));

                //description.loadData(String.format(descrip), "text/html", "utf-8");
                //description.loadData(descrip,"text/html","utf-8");
                String fonts = "<html>\n" + "\t<head>\n" + "\t\t<meta  \thttp-equiv=\"content-type\" content=\"text/html;\" charset=\"UTF-8\">\n" + "\t\t<style>\n" + "\t\t@font-face {\n" + "  font-family: 'segeoui-light';\n" + " src: url('file:///android_asset/fonts/RobotoSlab-Regular.ttf');\n" + "  font-style: normal;\n" + "}\n" + "\n" + "@font-face {\n" + "  font-family: 'segeoui-regular';\n" + "src: url('file:///android_asset/fonts/RobotoSlab-Regular.ttf');\n" + "  font-style: normal;\n" + "}\n" + "\n" + "@font-face {\n" + "  font-family: 'segeoui-sbold';\n" + " src: url('file:///android_asset/fonts/RobotoSlab-Regular.ttf');\n" + "  font-style: normal;\n" + "}\n" + "\n" + "@font-face {\n" + "    font-family: 'RobotoSlab-Bold';\n" + "   src: url('file:///android_asset/fonts/RobotoSlab-Regular.ttf');\n" + "    font-style: normal;\n" + "}\n" + "@font-face {\n" + "    font-family: 'RobotoSlab-Light';\n" + "    src: url('file:///android_asset/fonts/RobotoSlab-Regular.ttf');\n" + "    font-style: normal;\n" + "}\n" + "@font-face {\n" + "    font-family: 'RobotoSlab-Regular';\n" + "    src: url('file:///android_asset/fonts/RobotoSlab-Regular.ttf');\n" + "    font-style: normal;\n" + "}\n" + "@font-face {\n" + "    font-family: 'RobotoSlab-Thin';\n" + "    src: url('file:///android_asset/fonts/RobotoSlab-Regular.ttf');\n" + "    font-style: normal;\n" + "}\n" + "\t\t</style>\n" + "\t</head>";
                webone.loadDataWithBaseURL("", fonts + descrition + "</head>", "text/html", "utf-8", "");
                // description.setBackgroundColor(0x0a000000);
                webone.setBackgroundColor(Color.TRANSPARENT);
                Log.e("WEBCONTENT",descrition);
               /* model.setLikescount(obj.getInt("likescount"));
                model.setSharecount(obj.getInt("sharecount"));
                model.setMoreimagescount(obj.getString("moreimagescount"));*/
                productlist.add(model);
            }
            //productAdapter.notifyDataSetChanged();
        } catch (JSONException e) {

        }


    }
    public class ProductModel {
        String producttitle,productimage,id,moreimagescount;
        int likescount,sharecount;

        public String getProducttitle() {
            return producttitle;
        }

        public void setProducttitle(String producttitle) {
            this.producttitle = producttitle;
        }

        public void setProductimage(String productimage) {
            this.productimage = productimage;
        }

        public String getProductimage() {
            return productimage;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public int getLikescount() {
            return likescount;
        }

        public void setLikescount(int likescount) {
            this.likescount = likescount;
        }

        public int getSharecount() {
            return sharecount;
        }

        public void setSharecount(int sharecount) {
            this.sharecount = sharecount;
        }

        public String getMoreimagescount() {
            return moreimagescount;
        }

        public void setMoreimagescount(String moreimagescount) {
            this.moreimagescount = moreimagescount;
        }

    }

}
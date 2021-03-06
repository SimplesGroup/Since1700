package since.since1700;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.Gallery;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
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

import since.since1700.Adapter.DetailPageAdapter;
import since.since1700.Fragment.EventsFragments.EventDetailPageActivity;
import since.since1700.Fragment.EventsFragments.EventDetailPageAdapter;
import since.since1700.Fragment.FeedFragment;
import since.since1700.Model.FeedProductModel;

import static android.R.attr.description;

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
   // NetworkImageView feedimage;
    ProductModel model=new ProductModel();
    ImageLoader mImageLoader;
    WebView webone,webtwo,webthree;
    ImageButton feed_btn,brands_btn,shop_btn,events_btn,contact_btn;
    String colorcodes;
    public static final String colorcode = "colorCode";
    String id;
    SharedPreferences sharedpreferences;
    RecyclerView gallery;
    DetailPageAdapter imageAdapter;
    LinearLayout count_layout;
    int count = 0;
    static TextView page_text[];
    TextView brandname,specification,engine;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.detailpage_layout);
        Intent get = getIntent();
        id = get.getStringExtra("ID");
        String fontPath = "fonts/PFBeauSansPro-Reg_0.otf";
        final Typeface opensansfont = Typeface.createFromAsset(getAssets(), fontPath);

      //  String fontBold = "fonts/PFBeauSansPro-Bold_0.otf";
        //final Typeface opensansfontbold = Typeface.createFromAsset(getAssets(), fontPath);
        sharedpreferences = getSharedPreferences(mypreference,
                Context.MODE_PRIVATE);
        colorcodes = sharedpreferences.getString(colorcode, "");
        count_layout = (LinearLayout) findViewById(R.id.image_count);
        gallery = (RecyclerView) findViewById(R.id.mygallery01);
        requestQueue = Volley.newRequestQueue(getApplicationContext());
        progressDialog = new ProgressDialog(getApplicationContext());
//        progressDialog.show();
        progressDialog.setContentView(R.layout.custom_progressdialog);
        progressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        mImageLoader = MySingleton.getInstance(getApplicationContext()).getImageLoader();
        gallery.setLayoutManager(new LinearLayoutManager(DetailPage.this, LinearLayoutManager.HORIZONTAL,false));
        gallery.setItemAnimator(new DefaultItemAnimator());
        imageAdapter = new DetailPageAdapter(getApplicationContext(),productlist);
        gallery.setAdapter(imageAdapter);
        getData();

        brandname = (TextView) findViewById(R.id.product_category_name);
        specification = (TextView) findViewById(R.id.product_specification);
        engine = (TextView) findViewById(R.id.engine);
        // feedimage = (NetworkImageView) findViewById(R.id.product_category_image);
        webone = (WebView) findViewById(R.id.webview_one);
        webtwo = (WebView) findViewById(R.id.webview_eventdescription);
        webthree = (WebView) findViewById(R.id.webview_event);

        //     feedimage.setImageResource(R.drawable.background);

//        Log.e("IMAGEVIEWDATE",model.getProductimage());

//        brandname.setTypeface(opensansfontbold);
      //  specification.setTypeface(opensansfontbold);
       // engine.setTypeface(opensansfontbold);
        //RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.HORIZONTAL,false);
        String video="http://simpli-city.in/chainzbigseandrink.mp4";
        ProductModel model=new ProductModel();
        model.setId("1");
        model.setLikescount(1);
        model.setMoreimagescount("4");
        model.setProductimage("");
        model.setProductvideo(video);
        //model.setProductvideo("android.resource://"+"since.since1700"+"/"+ R.raw.chainzbigseandrink);
        model.setProducttitle("fsgsgsdgsdgsgfs");
        model.setSharecount(1234);
        productlist.add(model);
        ProductModel model1=new ProductModel();
        model1.setId("2");
        model1.setLikescount(2);
        model1.setMoreimagescount("4");
        model1.setProductvideo("");
        model1.setProductimage("http://simpli-city.in/vdfdhfv78lmdsvmg5todlsh4jffgskjb2947qnt/images/news/3TNFADemo1.jpg");
        model1.setProducttitle("fsgsgsdgsdgsgfs");
        model1.setSharecount(1234);
        productlist.add(model1);
        ProductModel model2=new ProductModel();
        model2.setId("3");
        model2.setLikescount(2);
        model2.setProductvideo("");



        model2.setMoreimagescount("4");
        model2.setProductimage("http://simpli-city.in/vdfdhfv78lmdsvmg5todlsh4jffgskjb2947qnt/images/news/3TNFADemo1.jpg");
        model2.setProducttitle("fsgsgsdgsdgsgfs");
        model2.setSharecount(1234);
        productlist.add(model2);
        /*ProductModel model3=new ProductModel();
        model3.setId("4");
        model3.setLikescount(1);
        model3.setMoreimagescount("4");
        model3.setProductimage("");
        model3.setProductvideo("android.resource://"+"since.since1700"+"/"+ R.raw.chainzbigseandrink);
        model3.setProducttitle("fsgsgsdgsdgsgfs");
        model3.setSharecount(1234);
        productlist.add(model3);
        ProductModel model4=new ProductModel();
        model4.setId("5");
        model4.setLikescount(2);
        model4.setProductvideo("");
        model4.setMoreimagescount("4");
        model4.setProductimage("http://simpli-city.in/vdfdhfv78lmdsvmg5todlsh4jffgskjb2947qnt/images/news/3TNFADemo1.jpg");
        model4.setProducttitle("fsgsgsdgsdgsgfs");
        model4.setSharecount(1234);
        productlist.add(model4);*/

    // gallery.setLayoutManager(mLayoutManager);

       imageAdapter.notifyDataSetChanged();
       // count = gallery.getAdapter().getCount();
        //page_text = new TextView[count];
        /*for (int i = 0; i < count; i++) {
            page_text[i] = new TextView(this);
            page_text[i].setText(".");
            page_text[i].setTextSize(45);
            page_text[i].setTypeface(null, Typeface.BOLD);
            page_text[i].setTextColor(android.graphics.Color.GRAY);
            count_layout.addView(page_text[i]);
        }*/
       /* gallery.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int position, long arg3) {
                // TODO Auto-generated method stub
                //galleryimage.setImageResource(imageAdapter.flowers[position]);
                for (int i = 0; i < count; i++) {
                    page_text[i].setTextColor(android.graphics.Color.GRAY);
                }
                page_text[position].setTextColor(android.graphics.Color.WHITE);

            }

            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub

            }
        });*/
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

                //feedimage.setImageUrl(model.getProductimage(),mImageLoader);
                model.setProducttitle(obj.getString("producttitle"));


                String des = obj.isNull("producttitle") ? null : obj
                        .getString("producttitle");
                String ss = des;
                String s = ss;

                // s = s.replace("\"", "'");
                s = s.replace("\\", "");
                String descrition = "This is a section of our privilege partners, offering benefits like free hotel stays, loyalty status, and special experiences and discounts to our members. \n\n\t This is a section of our privilege partners, offering benefits like free hotel stays, loyalty status, and special experiences and discounts to our members.";

               String performance = "Release date \t\t	August 2016 \n Form factor	\t\tTouchscreen \nDimensions (mm)\t\t151.00 x 76.00 x 8.30 \nWeight (g)\t\t	175.00 \nBattery capacity (mAh)\t\t	4100 \nRemovable battery	\t\tNo \nColours	\t\tGold, Grey, Matte Black \nSAR value	\t\tNA";

                //description.loadData(String.format(descrip), "text/html", "utf-8");
                //description.loadData(descrip,"text/html","utf-8");
               String fonts = "<html>\n" + "\t<head>\n" + "\t\t<meta  \\thttp-equiv=\"content-type\" content=\"text/html;\" charset=\"UTF-8\">\n" + "\t\t<style>\n" + "\t\t@font-face {\n" + "  font-family: 'segeoui-light';\n" + " src: url('file:///android_asset/fonts/RobotoSlab-Regular.ttf');\n" + "  font-style: normal;\n" + "}\n" + "\n" + "@font-face {\n" + "  font-family: 'segeoui-regular';\n" + "src: url('file:///android_asset/fonts/RobotoSlab-Regular.ttf');\n" + "  font-style: normal;\n" + "}\n" + "\n" + "@font-face {\n" + "  font-family: 'segeoui-sbold';\n" + " src: url('file:///android_asset/fonts/RobotoSlab-Regular.ttf');\n" + "  font-style: normal;\n" + "}\n" + "\n" + "@font-face {\n" + "    font-family: 'RobotoSlab-Bold';\n" + "   src: url('file:///android_asset/fonts/RobotoSlab-Regular.ttf');\n" + "    font-style: normal;\n" + "}\n" + "@font-face {\n" + "    font-family: 'RobotoSlab-Light';\n" + "    src: url('file:///android_asset/fonts/RobotoSlab-Regular.ttf');\n" + "    font-style: normal;\n" + "}\n" + "@font-face {\n" + "    font-family: 'RobotoSlab-Regular';\n" + "    src: url('file:///android_asset/fonts/RobotoSlab-Regular.ttf');\n" + "    font-style: normal;\n" + "}\n" + "@font-face {\n" + "    font-family: 'RobotoSlab-Thin';\n" + "    src: url('file:///android_asset/fonts/RobotoSlab-Regular.ttf');\n" + "    font-style: normal;\n" + "}\n" + "\t\t</style>\n" + "\t</head>";
                webone.loadData(fonts + descrition + "</head>", "text/html", "utf-8");
               webtwo.loadData(fonts + performance + "</head>", "text/html", "utf-8");
                webthree.loadData(fonts + performance + "</head>", "text/html", "utf-8");
                // description.setBackgroundColor(0x0a000000);
                webone.setBackgroundColor(Color.TRANSPARENT);
              //  Log.e("WEBCONTENT",descrition);
               /* model.setLikescount(obj.getInt("likescount"));
                model.setSharecount(obj.getInt("sharecount"));
                model.setMoreimagescount(obj.getString("moreimagescount"));*/
                //productlist.add(model);
            }
            //productAdapter.notifyDataSetChanged();
        } catch (JSONException e) {

        }


    }
    public class ProductModel {
        String producttitle,productimage,id,moreimagescount,productvideo;
        int likescount,sharecount;

        public String getProducttitle() {
            return producttitle;
        }

        public void setProducttitle(String producttitle)
        {
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

        public String getProductvideo() {
            return productvideo;
        }

        public void setProductvideo(String productvideo) {
            this.productvideo = productvideo;
        }

        public void setMoreimagescount(String moreimagescount) {
            this.moreimagescount = moreimagescount;
        }
    }
}

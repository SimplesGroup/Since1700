package since.since1700.Profile;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import since.since1700.ProductEnglish;
import since.since1700.R;

/**
 * Created by Sandhiya on 10/5/2017.
 */

public class Privileges extends Fragment {

    @Nullable
    public static Privileges newInstance() {
        Privileges fragment = new Privileges();
        return fragment;
    }

    private RecyclerView recyclerView;
    private ProductsAdapterEnglish adapter;
    private List<ProductEnglish> productEnglishList;
    Button home;
 //   private static final String TAG = MainActivityEnglish.class.getSimpleName();
    private String URL_FEED = "http://simpli-city.in/request2.php?rtype=alldata&key=simples&page=3";


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.profile_privilages, container, false);
        recyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);
        LinearLayout recyclerLayout = (LinearLayout) rootView.findViewById(R.id.recycler_layout);

        productEnglishList = new ArrayList<>();
        adapter = new ProductsAdapterEnglish(getActivity(), productEnglishList);

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getActivity(), 2);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(10), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
        prepareAlbums();
        // Inflate the layout for this fragment


        /*recyclerLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(),ProductDetailsEnglish.class);
                startActivity(i);

            }
        });
*/
        home = (Button) rootView.findViewById(R.id.home);

        /*home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = null;
                // String title = getString(R.string.app_name);

                fragment = new HomeFragmentEnglish();

                if (fragment != null) {
                    FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.container_body, fragment);
                    fragmentTransaction.commit();

                    // set the toolbar title
                    //  getSupportActionBar().setTitle(title);
                }
            }
        });*/


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
        return rootView;
    }

    /*private void parseJsonFeed(JSONObject response) {
        try {
            JSONArray feedArray = response.getJSONArray("result");

            for (int i = 0; i < feedArray.length(); i++) {
                JSONObject feedObj = (JSONObject) feedArray.get(i);

                ProductTamil item = new ProductTamil();
                item.setPid(feedObj.getInt("id"));
                item.setPname(feedObj.getString("title"));

                // Image might be null sometimes
                String image = feedObj.isNull("image") ? null : feedObj
                        .getString("image");
                item.setPimage(image);
                item.setPprice(feedObj.getString("qtype"));
                item.setPquantity(feedObj.getString("image_url"));


                productEnglishList.add(item);
            }

            // notify data changes to list adapater
            adapter.notifyDataSetChanged();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }*/
    private void prepareAlbums() {

    /*   ProductTamil item = new ProductTamil();
        ProductTamil item1 = new ProductTamil();
        ProductTamil item2 = new ProductTamil();
        ProductTamil item3 = new ProductTamil();
        ProductTamil item4 = new ProductTamil();
        ProductTamil item5 = new ProductTamil();

        item.setPname("Fennel Seeds");
        item1.setPname("Asafoetida");
        item2.setPname("Red Chilli Powder");
        item3.setPname("Black Cardamon");
        item4.setPname("White Pepper");
        item5.setPname("Black Pepper");

        item.setPprice("250");
        item1.setPprice("250");
        item2.setPprice("250");
        item3.setPprice("250");
        item4.setPprice("250");
        item5.setPprice("250");



        item.setPquantity("per Kilogram");
        item1.setPquantity("per Kilogram");
        item2.setPquantity("per Kilogram");
        item3.setPquantity("per Kilogram");
        item4.setPquantity("per Kilogram");
        item5.setPquantity("per Kilogram");


        productEnglishList.add(item);
        productEnglishList.add(item1);
        productEnglishList.add(item2);
        productEnglishList.add(item3);
        productEnglishList.add(item4);
        productEnglishList.add(item5);

      *//*  ArrayList<Integer> itemsimg = new ArrayList<Integer>();
        itemsimg.add(R.drawable.ic_star);
        itemsimg.add(R.drawable.ic_moon);
        itemsimg.add(R.drawable.ic_dog);
        itemsimg.add(R.drawable.ic_bird);
        itemsimg.add(R.drawable.ic_tree);*//*
*/
        int[] covers = new int[]{
                R.mipmap.brands,
                R.mipmap.brands,
                R.mipmap.brands,
                R.mipmap.brands,
                R.mipmap.brands,
                R.mipmap.brands};



        ProductEnglish a = new ProductEnglish("Fennel Seeds", "250", covers[0],"per kilogram");
        productEnglishList.add(a);

        a = new ProductEnglish("Asafoetida", "250", covers[1],"per kilogram");
        productEnglishList.add(a);

        a = new ProductEnglish("Red Chilli Powder", "150", covers[2],"per kilogram");
        productEnglishList.add(a);

        a = new ProductEnglish("Black Cardamon", "540", covers[3],"per kilogram");
        productEnglishList.add(a);

        a = new ProductEnglish("White Pepper", "14", covers[4],"per kilogram");
        productEnglishList.add(a);

        a = new ProductEnglish("Black Pepper", "1", covers[5],"per kilogram");
        productEnglishList.add(a);

        adapter.notifyDataSetChanged();
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

        private int spanCount;
        private int spacing;
        private boolean includeEdge;

        public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
            this.spanCount = spanCount;
            this.spacing = spacing;
            this.includeEdge = includeEdge;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view); // item position
            int column = position % spanCount; // item column

            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
                outRect.right = (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)

                if (position < spanCount) { // top edge
                    outRect.top = spacing;
                }
                outRect.bottom = spacing; // item bottom
            } else {
                outRect.left = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
                outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
                if (position >= spanCount) {
                    outRect.top = spacing; // item top
                }
            }
        }
    }

    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }

    public class ProductsAdapterEnglish extends RecyclerView.Adapter<ProductsAdapterEnglish.MyViewHolder> {

        private Context mContext;
        private List<ProductEnglish> productEnglishList;



        public class MyViewHolder extends RecyclerView.ViewHolder {
            public TextView title, count,quantity;
            public ImageView thumbnail, overflow;

            public MyViewHolder(View view) {
                super(view);
                title = (TextView) view.findViewById(R.id.profile);
               // count = (TextView) view.findViewById(R.id.count);
               // quantity = (TextView) view.findViewById(R.id.kg);
                thumbnail = (ImageView) view.findViewById(R.id.thumbnail);
               // overflow = (ImageView) view.findViewById(R.id.overflow);
            }
        }


        public ProductsAdapterEnglish(Context mContext, List<ProductEnglish> productEnglishList) {
            this.mContext = mContext;
            this.productEnglishList = productEnglishList;
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.album_card, parent, false);

            return new MyViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(final MyViewHolder holder, int position) {
          //  String splash = "fonts/LATO-MEDIUM.TTF";
          //  final Typeface tf = Typeface.createFromAsset(mContext.getAssets(), splash);

            final ProductEnglish productEnglish = productEnglishList.get(position);
            holder.title.setText(productEnglish.getPname());
          //  holder.count.setText("Rs." + productEnglish.getPprice());
            //holder.quantity.setText(productEnglish.getPquantity());

            // loading album cover using Glide library
            Glide.with(mContext).load(productEnglish.getPimage()).into(holder.thumbnail);

           /* holder.overflow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    showPopupMenu(holder.overflow);
                }
            });*/

            /*holder.thumbnail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(mContext,ProductDetailsEnglish.class);

                    i.putExtra("TITLE", productEnglish.getPname());

                    Log.e("TITLEEEE", productEnglish.getPname());
                    mContext.startActivity(i);
                }
            });
*/
           /* holder.title.setTypeface(tf);
            holder.count.setTypeface(tf);
            holder.quantity.setTypeface(tf);*/
        }

        /**
         * Showing popup menu when tapping on 3 dots
         */
       /* private void showPopupMenu(View view) {
            // inflate menu
            PopupMenu popup = new PopupMenu(mContext, view);
            MenuInflater inflater = popup.getMenuInflater();
            inflater.inflate(R.menu.menu_album, popup.getMenu());
            popup.setOnMenuItemClickListener(new MyMenuItemClickListener());
            popup.show();
        }*/

        /**
         * Click listener for popup menu items
         */
        /*class MyMenuItemClickListener implements PopupMenu.OnMenuItemClickListener {

            public MyMenuItemClickListener() {
            }

            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.action_add_favourite:
                        Toast.makeText(mContext, "Add to favourite", Toast.LENGTH_SHORT).show();
                        return true;
                    case R.id.action_play_next:
                        Toast.makeText(mContext, "Play next", Toast.LENGTH_SHORT).show();
                        return true;
                    default:
                }
                return false;
            }
        }*/

        @Override
        public int getItemCount() {
            return productEnglishList.size();
        }
    }


}

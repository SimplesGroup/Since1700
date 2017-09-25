package since.since1700.Fragment;

import android.content.Context;
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
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import since.since1700.Adapter.CategoryAdapter;
import since.since1700.Adapter.LocationAdapter;
import since.since1700.Model.LocationModel;
import since.since1700.R;

/**
 * Created by Sandhiya on 9/21/2017.
 */

 public class ShopHomeFragment extends Fragment {
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private HomeAdapter homeadapter;
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
        homeadapter = new HomeAdapter(getActivity());
        recyclerView.setAdapter(homeadapter);


        return view;
    }

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.MyViewHolder> {

    Context context;
    List<LocationModel> modellist=new ArrayList<LocationModel>();
    ArrayList<String> album = new ArrayList<String>();
    // ImageLoader mImageLoader;
    String image;

    public HomeAdapter(Context context) {

        this.context = context;

    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        public Button checkbutton;
        LinearLayout countrylayout;


        public MyViewHolder(View view) {
            super(view);
            // title = (TextView) view.findViewById(R.id.listTitle);
            //  checkbutton = (Button) view.findViewById(R.id.btn_check);
            //  countrylayout = (LinearLayout) view.findViewById(R.id.countrylayout);

        }
    }



    @Override
    public HomeAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.feed_home_item
                        , parent, false);
        Log.e("LISTTTTTTTT","SSSSSSS");
        return new HomeAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final HomeAdapter.MyViewHolder holder, final int position) {

        final LocationModel comment = modellist.get(position);

       /*  holder.title.setText(comment.getLocation());
        String fontPath = "fonts/OpenSans-Regular.ttf";
//        final Typeface opensansfont = Typeface.createFromAsset(context.getAssets(), fontPath);

        // holder.title.setTypeface(opensansfont);


        holder.countrylayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (modellist.get(position).isSelected()) {
                    holder.checkbutton.setVisibility(View.VISIBLE);
                    holder.checkbutton.setBackgroundResource(R.drawable.unchecked);
                    modellist.get(position).setSelected(false);
                } else {
                    // Toast.makeText(getApplicationContext(), modellist.get(position).getProductname() + " selected!", Toast.LENGTH_SHORT).show();
                    holder.checkbutton.setVisibility(View.VISIBLE);
                    holder.checkbutton.setBackgroundResource(R.mipmap.tickblue);
                    modellist.get(position).setSelected(true);
                }
            }
        });
        holder.checkbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (modellist.get(position).isSelected()) {
                    holder.checkbutton.setVisibility(View.VISIBLE);
                    holder.checkbutton.setBackgroundResource(R.drawable.unchecked);
                    modellist.get(position).setSelected(false);
                } else {
                    // Toast.makeText(getApplicationContext(), modellist.get(position).getProductname() + " selected!", Toast.LENGTH_SHORT).show();
                    holder.checkbutton.setVisibility(View.VISIBLE);
                    holder.checkbutton.setBackgroundResource(R.mipmap.tickblue);
                    modellist.get(position).setSelected(true);
                }
            }
        });*/


    }
    public List<LocationModel> getCountryist() {
        return modellist;
    }

    @Override
    public int getItemCount() {
        return modellist.size();
    }
}
}
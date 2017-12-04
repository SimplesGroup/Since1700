package since.since1700.Adapter;

import android.content.Context;
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

import since.since1700.Model.LocationModel;
import since.since1700.R;

/**
 * Created by Sandhiya on 12/4/2017.
 */

public class BlogDetailPageAdapter extends RecyclerView.Adapter<BlogDetailPageAdapter.MyViewHolder> {

    Context context;
    List<LocationModel> modellist=new ArrayList<LocationModel>();

    public BlogDetailPageAdapter(Context context,List<LocationModel> list) {

        this.modellist = list;
        this.context = context;

    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        public Button checkbutton;
        LinearLayout countrylayout;


        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.listTitle);
            checkbutton = (Button) view.findViewById(R.id.btn_check);
            countrylayout = (LinearLayout) view.findViewById(R.id.countrylayout);

        }
    }


    @Override
    public BlogDetailPageAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.locationitem_layout, parent, false);
        Log.e("LISTTTTTTTT","SSSSSSS");
        return new BlogDetailPageAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final BlogDetailPageAdapter.MyViewHolder holder, final int position) {

        final LocationModel comment = modellist.get(position);
        if (position % 2 == 0) {
            holder.itemView.setBackgroundResource(R.color.gray);
        } else {
            holder.itemView.setBackgroundResource(R.color.lightgray);
        }
        holder.title.setText(comment.getLocation());
        String fontPath = "fonts/PFBeauSansPro-Reg_0.otf";
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
                    holder.checkbutton.setBackgroundResource(R.mipmap.tickwhiteblue);
                    modellist.get(position).setSelected(true);
                }
            }
        });

        holder.checkbutton.setVisibility(View.GONE);
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
                    holder.checkbutton.setBackgroundResource(R.mipmap.tickwhiteblue);
                    modellist.get(position).setSelected(true);
                }
            }
        });


    }
    public List<LocationModel> getCountryist() {
        return modellist;
    }

    @Override
    public int getItemCount() {
        return modellist.size();
    }
}
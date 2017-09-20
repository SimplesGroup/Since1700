package since.since1700.Adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import since.since1700.Model.LocationModel;
import since.since1700.R;

/**
 * Created by Sandhiya on 9/19/2017.
 */

public class LocationAdapter extends RecyclerView.Adapter<LocationAdapter.MyViewHolder> {

    Context context;
    List<LocationModel> modellist=new ArrayList<LocationModel>();
    ArrayList<String> album = new ArrayList<String>();
   // ImageLoader mImageLoader;
    String image;

    public LocationAdapter(Context context,List<LocationModel> list) {

        this.modellist = list;
        this.context = context;

    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
     public Button checkbutton;


        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.listTitle);
            checkbutton = (Button) view.findViewById(R.id.btn_check);

        }
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.locationitem_layout, parent, false);
        Log.e("LISTTTTTTTT","SSSSSSS");
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {

       final LocationModel comment = modellist.get(position);

        holder.title.setText(comment.getLocation());
        holder.checkbutton.setClickable(modellist.get(position).isSelected());

        holder.checkbutton.setTag(modellist.get(position));


    }


    @Override
    public int getItemCount() {
        return modellist.size();
    }
}
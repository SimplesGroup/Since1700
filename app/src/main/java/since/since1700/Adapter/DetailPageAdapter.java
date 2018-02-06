package since.since1700.Adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import java.util.ArrayList;
import java.util.List;

import since.since1700.CustomVolleyRequest;
import since.since1700.DetailPage;
import since.since1700.Model.LocationModel;
import since.since1700.R;

/**
 * Created by Sandhiya on 12/4/2017.
 */

public class DetailPageAdapter extends RecyclerView.Adapter<DetailPageAdapter.MyViewHolder> {

    Context context;
    List<DetailPage.ProductModel> modellist=new ArrayList<>();
    ImageLoader imageLoader;
    public DetailPageAdapter(Context context, List<DetailPage.ProductModel> list) {

        this.modellist = list;
        this.context = context;

    }

    public DetailPageAdapter(Context context) {
        this.context = context;

    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public NetworkImageView image;
        public VideoView video;


        public MyViewHolder(View view) {
            super(view);
            image = (NetworkImageView) view.findViewById(R.id.feedImage1);
            video = (VideoView) view.findViewById(R.id.videoView);
        }
    }


    @Override
    public DetailPageAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.gallery_layout, parent, false);
        Log.e("LISTTTTTTTT","SSSSSSS");
        return new DetailPageAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final DetailPageAdapter.MyViewHolder holder, final int position) {

        final DetailPage.ProductModel comment = modellist.get(position);
        imageLoader= CustomVolleyRequest.getInstance(context).getImageLoader();
        Log.e("DATA","called");
        String im="http://simpli-city.in/vdfdhfv78lmdsvmg5todlsh4jffgskjb2947qnt/images/news/3TNFADemo1.jpg";
       holder.image.setImageUrl(im,imageLoader);

       /* MediaController mediaController = new MediaController(context);
        String uriPath = "android.resource://"+"since.since1700"+"/"+ R.raw.chainzbigseandrink;
        Uri uri = Uri.parse(uriPath);
        holder.video.setVideoURI(uri);
        holder.video.setMediaController(mediaController);
        holder.video.requestFocus();
        holder.video.start();*/

    }


    @Override
    public int getItemCount() {
        return modellist.size();
    }
}
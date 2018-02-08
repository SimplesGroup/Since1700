package since.since1700.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.media.MediaPlayer;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.RelativeLayout;
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
    List<DetailPage.ProductModel> modellist=new ArrayList<DetailPage.ProductModel>();
    ImageLoader imageLoader;
    MediaController mediaController;
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
        public RelativeLayout layout;
        public Button videobutton;
        public ImageView thumbnail_mini;


        public MyViewHolder(View view) {
            super(view);
           // mediaController = new MediaController(context);
            image = (NetworkImageView) view.findViewById(R.id.feedImage1);
            video = (VideoView) view.findViewById(R.id.videoView);
            layout = (RelativeLayout)view.findViewById(R.id.video_container_layout);
            videobutton = (Button)view.findViewById(R.id.btn_video);
            thumbnail_mini = (ImageView)view.findViewById(R.id.thumbnail_mini);
            //video.setMediaController(mediaController);
        }
    }


    @Override
    public DetailPageAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.gallery_layout, parent, false);

        return new DetailPageAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final DetailPageAdapter.MyViewHolder holder,  int position) {

        final DetailPage.ProductModel comment = modellist.get(position);
        imageLoader= CustomVolleyRequest.getInstance(context).getImageLoader();
        Log.e("DATA","called");

        Bitmap bmThumbnail = ThumbnailUtils.createVideoThumbnail(comment.getProductvideo(),
                MediaStore.Images.Thumbnails.MINI_KIND);
        holder.thumbnail_mini.setImageBitmap(bmThumbnail);


      if(comment.getProductimage().equals("")&&comment.getProductvideo()!=null){
            holder.image.setVisibility(View.GONE);




        }else {
          String im=comment.getProductimage();
          holder.image.setImageUrl(im,imageLoader);
      }
         if(comment.getProductvideo().equals("")) {


             holder.video.setVisibility(View.GONE);
             holder.videobutton.setVisibility(View.GONE);


         } else {
             String uriPath = comment.getProductvideo();
             Uri uri = Uri.parse(uriPath);
             holder.video.setVideoURI(uri);
             holder.video.requestFocus();
             // holder.video.start();
             Log.e("LISTTTTTTTT","SSSSSSS");
             //notifyDataSetChanged();
        }
        holder.video.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.video.start();
            }
        });

        holder.video.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                // This is just to show image when loaded
                mp.start();
                mp.pause();
            }
        });

        holder.video.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {

                holder.video.setVideoPath(comment.getProductvideo());
                holder.video.start();
            }
        });
         holder.videobutton.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                /*  holder.image.setVisibility(View.GONE);
                 holder.video.setVisibility(View.VISIBLE);
                 String uriPath = comment.getProductvideo();
                 Uri uri = Uri.parse(uriPath);
                 holder.video.setVideoURI(uri);
                 holder.video.start();*/
             }
         });

        if(holder.video.isPlaying()){
            holder.videobutton.setVisibility(View.GONE);
        }


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
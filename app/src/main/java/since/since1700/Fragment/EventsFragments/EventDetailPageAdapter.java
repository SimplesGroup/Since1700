package since.since1700.Fragment.EventsFragments;

import android.content.Context;
import android.net.Uri;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.VideoView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import since.since1700.CustomVolleyRequest;
import since.since1700.R;

/**
 * Created by Sandhiya on 10/12/2017.
 */

public class EventDetailPageAdapter extends BaseAdapter {
    private Context context;
    ImageLoader imageLoader;
    private LayoutInflater mInflater;

    public EventDetailPageAdapter(Context c) {
        mInflater = (LayoutInflater)c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        // TODO Auto-generated constructor stub
        this.context = c;
    }
    public int getCount() {
        // TODO Auto-generated method stub
        return flowers.length;
    }
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return position;
    }
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }
    public View getView(int position, View convertView, ViewGroup parent) {


        System.out.println("getView " + position + " " + convertView);
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.gallery_layout, null);
            holder = new ViewHolder();
            holder.image = (NetworkImageView) convertView.findViewById(R.id.feedImage1);
            holder.video = (VideoView) convertView.findViewById(R.id.videoView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder)convertView.getTag();
        }
        imageLoader= CustomVolleyRequest.getInstance(context).getImageLoader();
        String im="http://simpli-city.in/vdfdhfv78lmdsvmg5todlsh4jffgskjb2947qnt/images/news/3TNFADemo1.jpg";
        holder.image.setImageUrl(im,imageLoader);
       /* holder.image.setImageResource(flowers[position]);
        holder.image.setScaleType(NetworkImageView.ScaleType.FIT_XY);
        holder.image.setPadding(100,0,100,0);
        holder.image.setLayoutParams(new RelativeLayout.LayoutParams(1400, 700));*/

    /*  MediaController mediaController = new MediaController(context);
        String uriPath = "android.resource://"+"since.since1700"+"/"+ R.raw.chainzbigseandrink;
        Uri uri = Uri.parse(uriPath);
        holder.video.setVideoURI(uri);
        holder.video.setMediaController(mediaController);
        holder.video.requestFocus();
        holder.video.start();*/
        return convertView;
    }

    public static class ViewHolder {
        public NetworkImageView image;
        public VideoView video;
    }
     public int[] flowers = { R.drawable.stevejobs, R.drawable.stevejobs,
            R.drawable.stevejobs,R.drawable.stevejobs, R.drawable.stevejobs};
}

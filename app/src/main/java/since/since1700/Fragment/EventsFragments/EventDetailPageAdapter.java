package since.since1700.Fragment.EventsFragments;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import since.since1700.R;

/**
 * Created by Sandhiya on 10/12/2017.
 */

public class EventDetailPageAdapter extends BaseAdapter {
    private Context context;

    public EventDetailPageAdapter(Context c) {
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
        // TODO Auto-generated method stub
        ImageView image = new ImageView(context);

        image.setImageResource(flowers[position]);
        image.setScaleType(ImageView.ScaleType.FIT_XY);
        image.setPadding(100,10,100,10);
      image.setLayoutParams(new Gallery.LayoutParams(1000, 600));
        return image;
    }
     public int[] flowers = { R.drawable.stevejobs, R.drawable.stevejobs,
            R.drawable.stevejobs,R.drawable.stevejobs, R.drawable.stevejobs};
}

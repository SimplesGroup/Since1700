package since.since1700.Profile;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Gallery;
import android.widget.LinearLayout;
import android.widget.TextView;

import since.since1700.Fragment.EventsFragments.EventDetailPageAdapter;
import since.since1700.R;

/**
 * Created by Sandhiya on 11/7/2017.
 */

public class BlogDetailPage extends AppCompatActivity {

    Gallery gallery;
    EventDetailPageAdapter imageAdapter;
    LinearLayout count_layout;
    int count = 0;
    static TextView page_text[];
    TextView eventdetaillebel,eventdeatil,eventtitle;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.blogdetailpage);


        count_layout = (LinearLayout) findViewById(R.id.image_count);
        gallery = (Gallery) findViewById(R.id.mygallery01);
        eventdetaillebel = (TextView) findViewById(R.id.eventdetaillebel);
        eventdeatil = (TextView) findViewById(R.id.eventdeatil);
        String fontPath = "fonts/PFBeauSansPro-Reg_0.otf";
        final Typeface opensansfont = Typeface.createFromAsset(getAssets(), fontPath);

        eventdetaillebel.setTypeface(opensansfont);
        eventdeatil.setTypeface(opensansfont);
        imageAdapter = new EventDetailPageAdapter(this);
        gallery.setAdapter(imageAdapter);
        count=gallery.getAdapter().getCount();
        page_text = new TextView[count];
        for (int i = 0; i < count; i++) {
            page_text[i] = new TextView(this);
            page_text[i].setText(".");
            page_text[i].setTextSize(45);
            page_text[i].setTypeface(null, Typeface.BOLD);
            page_text[i].setTextColor(android.graphics.Color.GRAY);
            count_layout.addView(page_text[i]);
        }
        gallery.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

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
        });


    }
    }

package since.since1700.Profile;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Gallery;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import since.since1700.Adapter.BlogDetailPageAdapter;
import since.since1700.Adapter.LocationAdapter;
import since.since1700.Fragment.EventsFragments.EventDetailPageAdapter;
import since.since1700.Model.LocationModel;
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
    List<LocationModel> modellist=new ArrayList<LocationModel>();
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private BlogDetailPageAdapter locationadapter;
    Context context;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.blogdetailpage);

        mLayoutManager=new LinearLayoutManager(context);
        recyclerView = (RecyclerView) findViewById(R.id.commentlist);
        recyclerView.setLayoutManager(mLayoutManager);
        locationadapter = new BlogDetailPageAdapter(context,modellist);
        recyclerView.setAdapter(locationadapter);

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



        final LocationModel comment = new LocationModel();
        final LocationModel comment1 = new LocationModel();
        final LocationModel comment2 = new LocationModel();
        final LocationModel comment3 = new LocationModel();
        final LocationModel comment4 = new LocationModel();
        final LocationModel comment5 = new LocationModel();

        comment.setLocation("Lorem Ipsum is simply dummy text of the printing and typesetting");
        comment1.setLocation("Here are the latest projects and contests matching your profile and skills:");
        comment2.setLocation("Lorem Ipsum is simply dummy text of the printing and typesetting");
        comment3.setLocation("Here are the latest projects and contests matching your profile and skills:");
        comment4.setLocation("Lorem Ipsum is simply dummy text of the printing and typesettingA");
        comment5.setLocation("Here are the latest projects and contests matching your profile and skills:");


        modellist.add(comment);
        modellist.add(comment1);
        modellist.add(comment2);
        modellist.add(comment3);
        modellist.add(comment4);
        modellist.add(comment5);

    }
    }

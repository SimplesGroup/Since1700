package since.since1700.Profile;

import android.app.Fragment;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Gallery;
import android.widget.LinearLayout;
import android.widget.TextView;

import since.since1700.Fragment.EventsFragments.EventDetailPageActivity;
import since.since1700.Fragment.EventsFragments.EventDetailPageAdapter;
import since.since1700.R;

/**
 * Created by Sandhiya on 11/7/2017.
 */

public class Blog extends android.support.v4.app.Fragment {

    Gallery gallery;
    EventDetailPageAdapter imageAdapter;
    LinearLayout count_layout;
    int count = 0;
    static TextView page_text[];
    TextView eventdetaillebel,eventdeatil,eventtitle;
    @Nullable
    public static Blog newInstance() {
        Blog fragment = new Blog();
        return fragment;
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //requestQueue = Volley.newRequestQueue(getActivity());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.blog, container, false);
        count_layout = (LinearLayout) view.findViewById(R.id.image_count);
        gallery = (Gallery) view.findViewById(R.id.mygallery01);
        eventdetaillebel = (TextView) view.findViewById(R.id.eventdetaillebel);
        eventdeatil = (TextView) view.findViewById(R.id.eventdeatil);
        String fontPath = "fonts/PFBeauSansPro-Reg_0.otf";
        final Typeface opensansfont = Typeface.createFromAsset(getActivity().getAssets(), fontPath);

        eventdetaillebel.setTypeface(opensansfont);
        eventdeatil.setTypeface(opensansfont);
        imageAdapter = new EventDetailPageAdapter(getActivity());
        gallery.setAdapter(imageAdapter);
        count=gallery.getAdapter().getCount();
        page_text = new TextView[count];
        for (int i = 0; i < count; i++) {
            page_text[i] = new TextView(getActivity());
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


        return view;
    }
    }

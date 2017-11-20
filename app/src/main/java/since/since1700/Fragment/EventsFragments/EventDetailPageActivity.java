package since.since1700.Fragment.EventsFragments;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Gallery;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import since.since1700.Fragment.FeedFragment;
import since.since1700.MainActivity;
import since.since1700.R;

/**
 * Created by Sandhiya on 10/11/2017.
 */

public class EventDetailPageActivity extends AppCompatActivity {
    Gallery gallery;
    EventDetailPageAdapter imageAdapter;
    LinearLayout count_layout;
    int count = 0;
    static TextView page_text[];
    Button remindme;
    long startTime, endTime;
    Calendar cal;
    public ViewPager viewPager;
    String event_title, event_place, event_startdate, event_enddate;
    ImageView galleryimage;
    ImageButton feed_btn,brands_btn,shop_btn,events_btn,contact_btn;
    String colorcodes;
    public static final String colorcode = "colorCode";
    String id;
    SharedPreferences sharedpreferences;
    public static final String mypreference = "mypref";
    int pos=2;
    TextView eventdetaillebel,eventdeatil,eventtitle,text_eventvenue,text_eventvenue_details,text_eventlocation,text_eventlocation_details,text_eventwebsite,text_eventwebsite_details,text_eventontactname,text_eventontactname_details,text_eventphonenumber,text_eventphonenumber_details,text_eventemail,text_eventemail_details,text_date_event,text_date_event_data,text_timing_event,text_timing_event_data;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.eventdetailpage_layout);
        count_layout = (LinearLayout) findViewById(R.id.image_count);
        gallery = (Gallery) findViewById(R.id.mygallery01);
        Intent get=getIntent();
        id=get.getStringExtra("ID");

        sharedpreferences =  getSharedPreferences(mypreference,
                Context.MODE_PRIVATE);
        colorcodes=sharedpreferences.getString(colorcode,"");
        //galleryimage = (ImageView)findViewById(R.id.galleryimage);
        remindme = (Button) findViewById(R.id.remindme);

        eventdetaillebel = (TextView) findViewById(R.id.eventdetaillebel);
        eventdeatil = (TextView) findViewById(R.id.eventdeatil);
        eventtitle = (TextView) findViewById(R.id.eventtitle);
        text_eventvenue = (TextView) findViewById(R.id.text_eventvenue);
        text_eventvenue_details = (TextView) findViewById(R.id.text_eventvenue_details);
        text_eventlocation = (TextView) findViewById(R.id.text_eventlocation);
        text_eventlocation_details = (TextView) findViewById(R.id.text_eventlocation_details);
        text_eventwebsite = (TextView) findViewById(R.id.text_eventwebsite);
        text_eventwebsite_details = (TextView) findViewById(R.id.text_eventwebsite_details);
        text_eventontactname = (TextView) findViewById(R.id.text_eventontactname);
        text_eventontactname_details = (TextView) findViewById(R.id.text_eventontactname_details);
        text_eventphonenumber = (TextView) findViewById(R.id.text_eventphonenumber);
        text_eventphonenumber_details = (TextView) findViewById(R.id.text_eventphonenumber_details);
        text_eventemail = (TextView) findViewById(R.id.text_eventemail);
        text_eventemail_details = (TextView) findViewById(R.id.text_eventemail_details);
        text_date_event = (TextView) findViewById(R.id.text_date_event);
        text_date_event_data = (TextView) findViewById(R.id.text_date_event_data);
        text_timing_event = (TextView) findViewById(R.id.text_timing_event);
        text_timing_event_data = (TextView) findViewById(R.id.text_timing_event_data);

        String fontPath = "fonts/PFBeauSansPro-Reg_0.otf";
        final Typeface opensansfont = Typeface.createFromAsset(getAssets(), fontPath);

        eventdetaillebel.setTypeface(opensansfont);
        eventdeatil.setTypeface(opensansfont);
        eventtitle.setTypeface(opensansfont);
        text_eventvenue.setTypeface(opensansfont);
        text_eventvenue_details.setTypeface(opensansfont);
        text_eventlocation.setTypeface(opensansfont);
        text_eventlocation_details.setTypeface(opensansfont);
        text_eventwebsite.setTypeface(opensansfont);
        text_eventwebsite_details.setTypeface(opensansfont);
        text_eventontactname.setTypeface(opensansfont);
        text_eventontactname_details.setTypeface(opensansfont);
        text_eventphonenumber.setTypeface(opensansfont);
        text_eventphonenumber_details.setTypeface(opensansfont);
        text_eventemail.setTypeface(opensansfont);
        text_eventemail_details.setTypeface(opensansfont);
        text_date_event.setTypeface(opensansfont);
        text_date_event_data.setTypeface(opensansfont);
        text_timing_event.setTypeface(opensansfont);
        text_timing_event_data.setTypeface(opensansfont);

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
                    EventDetailPageActivity.page_text[i].setTextColor(android.graphics.Color.GRAY);
                }
                EventDetailPageActivity.page_text[position].setTextColor(android.graphics.Color.WHITE);

            }

            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
            }
        });

        remindme.setOnClickListener(new View.OnClickListener() {

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(Intent.ACTION_INSERT);
        intent.setType("vnd.android.cursor.item/event");
        cal = Calendar.getInstance();
        try {
            Date date = new SimpleDateFormat("MMMM dd, yyyy").parse(event_startdate);
            startTime = date.getTime();
            //startTime=date.setHours(4);


        } catch (Exception e) {
        }
        try {
            Date date = new SimpleDateFormat("MMMM dd, yyyy").parse(event_enddate);
            endTime = date.getTime();
        } catch (Exception e) {
        }

        intent.putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, startTime);
        intent.putExtra(CalendarContract.EXTRA_EVENT_END_TIME, endTime);

        intent.putExtra(CalendarContract.Events.TITLE, event_title);
        //intent.putExtra(CalendarContract.Events.DESCRIPTION,  "This is a sample description");
        intent.putExtra(CalendarContract.Events.EVENT_LOCATION, event_place);
        intent.putExtra(CalendarContract.Events.RRULE, "FREQ=YEARLY");

        startActivity(intent);
    }
});
    }
}

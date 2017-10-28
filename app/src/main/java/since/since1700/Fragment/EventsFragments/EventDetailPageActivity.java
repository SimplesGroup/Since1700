package since.since1700.Fragment.EventsFragments;


import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Gallery;
import android.widget.LinearLayout;
import android.widget.TextView;
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

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.eventdetailpage_layout);
        count_layout = (LinearLayout) findViewById(R.id.image_count);
        gallery = (Gallery) findViewById(R.id.mygallery01);
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
                for (int i = 0; i < count; i++) {
                    EventDetailPageActivity.page_text[i].setTextColor(android.graphics.Color.GRAY);
                }
                EventDetailPageActivity.page_text[position].setTextColor(android.graphics.Color.WHITE);

            }

            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub

            }
        });

    }
}

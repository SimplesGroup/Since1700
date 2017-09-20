package since.since1700.Login;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

import java.util.ArrayList;
import java.util.List;

import since.since1700.Adapter.LocationAdapter;
import since.since1700.Model.LocationModel;
import since.since1700.R;

/**
 * Created by Sandhiya on 9/19/2017.
 */

public class LocationActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private LocationAdapter madapter;
    Context context;
    List<LocationModel> modellist=new ArrayList<LocationModel>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_location);

        mLayoutManager=new LinearLayoutManager(context);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(mLayoutManager);
        madapter = new LocationAdapter(context,modellist);
        recyclerView.setAdapter(madapter);

        final LocationModel comment = new LocationModel();
        final LocationModel comment1 = new LocationModel();
        final LocationModel comment2 = new LocationModel();
        final LocationModel comment3 = new LocationModel();
        final LocationModel comment4 = new LocationModel();
        final LocationModel comment5 = new LocationModel();
        comment.setLocation("NORTH AMERICA");
        comment1.setLocation("SOUTH AMERICA");
        comment2.setLocation("AFRICA");
        comment3.setLocation("EUROPE");
        comment4.setLocation("ASIA");
        comment5.setLocation("AUSTRALIA");
        modellist.add(comment);
        modellist.add(comment1);
        modellist.add(comment2);
        modellist.add(comment3);
        modellist.add(comment4);
        modellist.add(comment5);


    }
}

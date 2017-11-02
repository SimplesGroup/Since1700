package since.since1700.Login;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import since.since1700.Adapter.LocationAdapter;
import since.since1700.ChooseyourInterest;
import since.since1700.Model.LocationModel;
import since.since1700.R;

/**
 * Created by Sandhiya on 9/19/2017.
 */

public class LocationActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private LocationAdapter locationadapter;
    Context context;
    List<LocationModel> modellist=new ArrayList<LocationModel>();
    List<Button> color = new ArrayList<>();
    Button next;
    Button black,red,blue;
    public static final String mypreference = "mypref";
    SharedPreferences sharedpreferences;
    String colorcodes;
    TextView chooselocation,choosecolor;

    public static final String colorcode = "colorCode";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_location);
        sharedpreferences =  getSharedPreferences(mypreference,
                Context.MODE_PRIVATE);

        mLayoutManager=new LinearLayoutManager(context);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(mLayoutManager);
        locationadapter = new LocationAdapter(context,modellist);
        recyclerView.setAdapter(locationadapter);

        next = (Button) findViewById(R.id.btn_next);
        chooselocation = (TextView)findViewById(R.id.chooselocation);
        choosecolor = (TextView)findViewById(R.id.choosecolor);

        black = (Button)findViewById(R.id.btn_black);
        red = (Button)findViewById(R.id.btn_red);
        blue = (Button)findViewById(R.id.btn_blue);

        String fontPath = "fonts/OpenSans-Regular.ttf";
        final Typeface opensansfont = Typeface.createFromAsset(getAssets(), fontPath);



        black.setTypeface(opensansfont);
        red.setTypeface(opensansfont);
        blue.setTypeface(opensansfont);
        chooselocation.setTypeface(opensansfont);
        choosecolor.setTypeface(opensansfont);
        next.setTypeface(opensansfont);


        black.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                black.setBackgroundResource(R.mipmap.tickblue);
                red.setBackgroundResource(R.drawable.red);
                blue.setBackgroundResource(R.drawable.blue);
             /*  if(modellist.get(0).isColorSelection()) {
                   black.setVisibility(View.VISIBLE);
                   black.setBackgroundResource(R.drawable.black);
                   modellist.get(0).setColorSelection(false);
               }else {
                   // Toast.makeText(getApplicationContext(), modellist.get(position).getProductname() + " selected!", Toast.LENGTH_SHORT).show();
                   black.setVisibility(View.VISIBLE);
                   black.setBackgroundResource(R.mipmap.tickblue);
                   modellist.get(0).setColorSelection(true);
               }*/


                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.putString(colorcode, "blue");
                editor.commit();

            }
        });

        red.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                red.setBackgroundResource(R.mipmap.tickblue);
                black.setBackgroundResource(R.drawable.black);
                blue.setBackgroundResource(R.drawable.blue);
                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.putString(colorcode, "blue");
                editor.commit();

            }
        });

        blue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                blue.setBackgroundResource(R.mipmap.tickblue);
                black.setBackgroundResource(R.drawable.black);
                red.setBackgroundResource(R.drawable.red);
                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.putString(colorcode, "blue");
                editor.commit();

              /*  if(colorcodes.length()==0){

                }else {
                    if(colorcodes.equalsIgnoreCase("blue")){
                        Log.e("Msg","hihihi");
                        chooselocation.setBackgroundResource(R.drawable.bluebutton);
                        choosecolor.setBackgroundResource(R.drawable.bluebutton);
                    }else {
                        if(colorcodes.equals("#626262")){
                            chooselocation.setBackgroundResource(R.drawable.bluebutton);
                            choosecolor.setBackgroundResource(R.drawable.bluebutton);

                        }else{
                            chooselocation.setBackgroundResource(R.drawable.bluebutton);
                            choosecolor.setBackgroundResource(R.drawable.bluebutton);

                        }
                    }
                }*/

            }
        });



        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String data = "";
                List<LocationModel> stList = ((LocationAdapter) locationadapter)
                        .getCountryist();

                for (int i = 0; i < stList.size(); i++) {
                    LocationModel country = stList.get(i);
                    if (country.isSelected() == true) {

                        data = data + "\n" + country.getLocation().toString();

                    }

                }


              /*  Toast.makeText(getApplicationContext(),
                        "Selected items: \n" + data, Toast.LENGTH_LONG)
                        .show();*/
                Intent intent=new Intent(getApplicationContext(), ChooseyourInterest.class);
                startActivity(intent);
            }
        });

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

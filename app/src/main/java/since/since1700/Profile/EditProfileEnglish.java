package since.since1700.Profile;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

import since.since1700.R;


/**
 * Created by Sandhiya on 9/13/2017.
 */

public class EditProfileEnglish extends AppCompatActivity {

    Button home,back;
    TextView name, name1, address, address2, dob, dob1,language,  notification, notification1;
    CalendarView simpleCalendarView;
    String date;
    final Context cc = this;
    String newDate;
    Spinner language1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.edit_profile);

        home = (Button)findViewById(R.id.button_signin);
        name = (TextView) findViewById(R.id.name);
        name1 = (TextView) findViewById(R.id.name1);


        home.setText("Submit");
        name.setText("Address");
        name1.setHint("II Floor, Sasha Building");



        String splash = "fonts/PFBeauSansPro-Reg_0.otf";
        final Typeface tf = Typeface.createFromAsset(getAssets(), splash);

        name.setTypeface(tf);
        name1.setTypeface(tf);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

}

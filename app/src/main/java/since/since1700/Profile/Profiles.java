package since.since1700.Profile;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

import since.since1700.R;

/**
 * Created by Sandhiya on 10/5/2017.
 */

public class Profiles extends Fragment {
    CalendarView simpleCalendarView;
    Button home,back,back1,back2,back3,back4;
    TextView  address, address2, dob, dob1,language, notification, notification1,color,country;
    Context cc;
    Spinner country1,language1,color1;
    String date;
    String newDate;
    @Nullable
    public static Profiles newInstance() {
        Profiles fragment = new Profiles();
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.profile_profile, container, false);

       //
        // home = (Button)rootView.findViewById(R.id.home);
        address = (TextView) rootView.findViewById(R.id.address);
        address2 = (TextView) rootView.findViewById(R.id.address1);
        dob = (TextView) rootView.findViewById(R.id.dob);
        dob1 = (TextView) rootView.findViewById(R.id.dob1);
        country = (TextView) rootView.findViewById(R.id.country);
        country1 = (Spinner) rootView.findViewById(R.id.country1);
        color = (TextView) rootView.findViewById(R.id.color);
        color1 = (Spinner) rootView.findViewById(R.id.color1);
        language = (TextView) rootView.findViewById(R.id.language);
        language1 = (Spinner) rootView.findViewById(R.id.language1);
        notification = (TextView) rootView.findViewById(R.id.notification);

        back = (Button)rootView.findViewById(R.id.back);
     //   back1 = (Button)rootView.findViewById(R.id.back1);


        address.setText("Address");
        address2.setText("II Floor, Sasha Building");
        dob.setText("DOB");
        dob1.setText("09/10/1986");
        country.setText("Country");
        language.setText("Language");
        notification.setText("Notification");
        color.setText("Color");

        dob1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                s();
            }
        });
        /*String splash = "fonts/LATO-MEDIUM.TTF";
        final Typeface tf = Typeface.createFromAsset(getActivity().getAssets(), splash);
*/
     /*   home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(),NavigationDrawerActivityEnglish.class);
                startActivity(i);
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(),EditProfileEnglish.class);
                startActivity(i);
            }
        });

        back1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(),EditProfileEnglish.class);
                startActivity(i);
            }
        });

        back2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(),EditProfileEnglish.class);
                startActivity(i);
            }
        });

        back3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(),EditProfileEnglish.class);
                startActivity(i);
            }
        });

        back4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(),EditProfileEnglish.class);
                startActivity(i);
            }
        });
*/
        /*name.setTypeface(tf);
        name1.setTypeface(tf);
        address.setTypeface(tf);
        address2.setTypeface(tf);
        dob.setTypeface(tf);
        dob1.setTypeface(tf);
        language.setTypeface(tf);
        notification.setTypeface(tf);*/

        // Inflate the layout for this fragment
        return rootView;
    }

    public void s(){
        final ArrayList<String> calenderDatesnew =new ArrayList<>();
        LayoutInflater layoutInflaterAndroid = LayoutInflater.from(getActivity());
        View mView = layoutInflaterAndroid.inflate(R.layout.activity_calender, null);


        AlertDialog.Builder alertDialogBuilderUserInput = new AlertDialog.Builder(getActivity());
        simpleCalendarView = (CalendarView) mView.findViewById(R.id.simpleCalendarView);
        // get the reference of CalendarView
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            simpleCalendarView.setFocusedMonthDateColor(Color.BLACK);
            simpleCalendarView.setUnfocusedMonthDateColor(Color.GRAY); // set the yellow color for the dates of an unfocused month
            simpleCalendarView.setSelectedWeekBackgroundColor(Color.WHITE); // red color for the selected week's background
            simpleCalendarView.setWeekSeparatorLineColor(Color.GREEN);
// set the red color for the dates of  focused month
        }

       /* simpleCalendarView.init(today, nextYear.getTime())
                .inMode(CalendarPickerView.SelectionMode.MULTIPLE);// green color for the week separator line*/
        // perform setOnDateChangeListener event on CalendarView


        simpleCalendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                // display the selected date by using a toast
                int Month=month+1;



                date =dayOfMonth + "/" + Month+ "/" + year;

                dob1.setText(date);
                System.out.println("DATE" + date);

                calenderDatesnew.add(Month + "/" + dayOfMonth+ "/" + year);
                newDate=Month + "/" + dayOfMonth+ "/" + year;
                ArrayList<String> n= new ArrayList<String>();
                n.add(date);
                Log.d("OOPSsssssssssss", date);
                for (int i=0, d=calenderDatesnew.size(); i<d; i++) {
                    Log.d("OOPS",calenderDatesnew.get(i));

                }
            }
        });
        alertDialogBuilderUserInput.setView(mView);
        alertDialogBuilderUserInput
                .setCancelable(false)
                .setPositiveButton("Add", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogBox, int id) {
                        // ToDo get user input here
                       /*if(textValue.equals("from")){
                            from_date.setText("Select Leave");

                            Intent intent = getIntent();
                            finish();
                            startActivity(intent);
                        }else{
                            to_date.setText(newDate);
                        }*/
                    }
                })
                .setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialogBox, int id) {
                                dialogBox.cancel();
                            }
                        });
        AlertDialog alertDialogAndroid = alertDialogBuilderUserInput.create();
        alertDialogAndroid.show();
    }
}

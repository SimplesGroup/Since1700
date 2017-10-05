package since.since1700.Profile;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import since.since1700.R;

/**
 * Created by Sandhiya on 10/5/2017.
 */

public class Profiles extends Fragment {

    Button home,back,back1,back2,back3,back4;
    TextView  address, address2, dob, dob1,language, notification, notification1,color,color1,country;
    TextView language1;
    Spinner country1;
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
        color1 = (TextView) rootView.findViewById(R.id.color1);
        language = (TextView) rootView.findViewById(R.id.language);
        language1 = (TextView) rootView.findViewById(R.id.language1);
        notification = (TextView) rootView.findViewById(R.id.notification);

        back = (Button)rootView.findViewById(R.id.back);
        back1 = (Button)rootView.findViewById(R.id.back1);
        back2 = (Button)rootView.findViewById(R.id.back2);
        back3 = (Button)rootView.findViewById(R.id.back3);
        back4 = (Button)rootView.findViewById(R.id.back4);

        address.setText("Address");
        address2.setText("II Floor, Sasha Building");
        dob.setText("DOB");
        dob1.setText("09/10/1986");
        country.setText("Country");
        language.setText("Language");
        language1.setText("English");
        notification.setText("Notification");
        color.setText("Color");


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
}

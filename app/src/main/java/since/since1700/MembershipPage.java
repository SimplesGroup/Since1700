package since.since1700;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

public class MembershipPage extends AppCompatActivity {
TextView textview_membership_tile,textview_firstdescription,textview_seconddescription,textview_invite_member_title,textview__invite_member_description;
    TextView  textview_apply_membership_title,textview_apply_member_description;
    Button button_applyformembership;
    SharedPreferences sharedpreferences;

    public static final String mypreference = "mypref";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.menbership);
        sharedpreferences =  getSharedPreferences(mypreference,
                Context.MODE_PRIVATE);

        textview_membership_tile=(TextView)findViewById(R.id.textview_membership);
        textview_firstdescription=(TextView)findViewById(R.id.textview_firstdescription);
        textview_seconddescription=(TextView)findViewById(R.id.textview_seconddescription);
        textview_invite_member_title=(TextView)findViewById(R.id.textview_invitebyamember_title);
        textview__invite_member_description=(TextView)findViewById(R.id.textview_invitebyamember_description);
        textview_apply_membership_title=(TextView)findViewById(R.id.textview_applymembership_title);
        textview_apply_member_description=(TextView)findViewById(R.id.textview_applymembership_description);

        button_applyformembership=(Button)findViewById(R.id.button_applymembership);

        textview_membership_tile.setText("Membership & Privileges");
        textview_firstdescription.setText("This is a section of our privilege partners, offering benefits "+
                "like free hotel stays, loyalty status, and special experiences " +
                "and discounts to our members.");

        textview_seconddescription.setText("Here are the ways to join Since1700.");
        textview_invite_member_title.setText("BE INVITED BY A MEMBER");
        textview__invite_member_description.setText("Member of Since1700 can invite a limited number of trusted friends to join the community. If you know a member, ask them for an invitation.");
        textview_apply_membership_title.setText("APPLY FOR MEMBERSHIP");
        textview_apply_member_description.setText("If you dont know any members, you can submit an application for membership. Our international committee of trustees review each application.");

        button_applyformembership.setText("APPLY FOR MEMBERSHIP");


        button_applyformembership.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }
}

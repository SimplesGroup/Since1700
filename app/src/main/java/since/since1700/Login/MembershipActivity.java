package since.since1700.Login;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import since.since1700.R;

/**
 * Created by user on 3/27/2018.
 */

public class MembershipActivity extends AppCompatActivity {

    EditText username,emailsignin,mobileno;
    Button btnapplyformembership;
    Spinner loaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_applymembership);

        username = (EditText) findViewById(R.id.edt_username);
        emailsignin = (EditText)findViewById(R.id.ed_email);
        mobileno = (EditText)findViewById(R.id.ed_mbl);
        btnapplyformembership = (Button) findViewById(R.id.btn_membership);

        username.setHint("Username");
        emailsignin.setHint("Email");
        mobileno.setHint("Mobile No");

        String fontPath = "fonts/PFBeauSansPro-Reg_0.otf";
        final Typeface opensansfont = Typeface.createFromAsset(getAssets(), fontPath);

        username.setTypeface(opensansfont);
        emailsignin.setTypeface(opensansfont);
        mobileno.setTypeface(opensansfont);

        btnapplyformembership.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MembershipActivity.this,PaymentPage.class);
                startActivity(i);
            }
        });

    }
}

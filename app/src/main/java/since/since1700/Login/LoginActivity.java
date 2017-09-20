package since.since1700.Login;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import since.since1700.MembershipPage;
import since.since1700.R;

/**
 * Created by Sandhiya on 9/18/2017.
 */

public class LoginActivity extends AppCompatActivity {

    EditText username,emailsignin,passwordsignin,logincode,emailsignup,passwordsignup;
    Button signin,signup,btnsignin,btnsignup;
    LinearLayout signinlayout,signuplayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);

       signin = (Button) findViewById(R.id.signin);
        signup = (Button) findViewById(R.id.signup);
        btnsignin = (Button)findViewById(R.id.btn_signin);
        btnsignup = (Button) findViewById(R.id.btn_signup);
                signinlayout = (LinearLayout) findViewById(R.id.signin_layout);
        signuplayout = (LinearLayout)findViewById(R.id.signup_layout);

        emailsignin = (EditText)findViewById(R.id.edt_email);
        passwordsignin = (EditText)findViewById(R.id.edt_password);


        username = (EditText) findViewById(R.id.edt_username);
        logincode = (EditText) findViewById(R.id.edt_lgncode);
        emailsignup = (EditText) findViewById(R.id.ed_email);
        passwordsignup = (EditText)findViewById(R.id.ed_password);

        username.setHint("Username");
        emailsignin.setHint("Email");
        emailsignup.setHint("Email");
        passwordsignin.setHint("************");
        passwordsignup.setHint("************");
        logincode.setHint("Your Login Code");

        signin.setBackgroundResource(R.drawable.bluebutton);
        signup.setBackgroundColor(00000000);

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               signin.setBackgroundResource(R.drawable.bluebutton);
                signup.setBackgroundColor(00000000);
                signuplayout.setVisibility(View.GONE);
                signinlayout.setVisibility(View.VISIBLE);
            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signup.setBackgroundResource(R.drawable.bluebutton);
                signin.setBackgroundColor(00000000);
                signuplayout.setVisibility(View.VISIBLE);
                signinlayout.setVisibility(View.GONE);
            }
        });


        btnsignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validation();
                Intent i = new Intent(LoginActivity.this,LocationActivity.class);
                startActivity(i);
            }
        });

        btnsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validation();
                Intent i = new Intent(LoginActivity.this,MembershipPage.class);
                startActivity(i);
            }
        });

    }


    public void validation() {
        Log.d("SignupEnglish", "SignupEnglish");

        if (!validate()) {
            onSignupFailed();
            return;
        }

        signin.setEnabled(false);

        final ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this,
                R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Creating Account...");
        progressDialog.show();



        // TODO: Implement your own signup logic here.

        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        // On complete call either onSignupSuccess or onSignupFailed
                        // depending on success
                        onSignupSuccess();
                        // onSignupFailed();
                        progressDialog.dismiss();
                    }
                }, 3000);
    }


    public void onSignupSuccess() {
        signin.setEnabled(true);
        setResult(RESULT_OK, null);
        finish();
    }

    public void onSignupFailed() {
        Toast.makeText(getBaseContext(), "Login failed", Toast.LENGTH_LONG).show();

        signin.setEnabled(true);
    }
    public boolean validate() {
        boolean valid = true;

        String name = username.getText().toString();

        String addresss = logincode.getText().toString();
        String emailup = emailsignup.getText().toString();
        String emailin = emailsignin.getText().toString();

        String passwordup = passwordsignup.getText().toString();
        String Passwordin = passwordsignin.getText().toString();

        if (name.isEmpty() || name.length() < 3) {
            username.setError("at least 3 characters");
            valid = false;
        } else {
            username.setError(null);
        }

        if (addresss.isEmpty()) {
            logincode.setError("Enter Valid Login Code");
            valid = false;
        } else {
            logincode.setError(null);
        }


        if (emailup.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(emailup).matches()) {
            emailsignup.setError("enter a valid email address");
            valid = false;
        } else {
            emailsignup.setError(null);
        }

        if (emailin.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(emailin).matches()) {
            emailsignin.setError("enter a valid email address");
            valid = false;
        } else {
            emailsignin.setError(null);
        }

        if (passwordup.isEmpty() || passwordsignup.length() < 4 || passwordup.length() > 10) {
            passwordsignup.setError("between 4 and 10 alphanumeric characters");
            valid = false;
        } else {
            passwordsignup.setError(null);
        }

        if (Passwordin.isEmpty() || passwordsignin.length() < 4 || Passwordin.length() > 10) {
            passwordsignin.setError("between 4 and 10 alphanumeric characters");
            valid = false;
        } else {
            passwordsignin.setError(null);
        }



        return valid;
    }
}

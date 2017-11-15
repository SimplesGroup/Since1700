package since.since1700;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

/**
 * Created by Sandhiya on 10/9/2017.
 */

public class AboutUsActivity extends AppCompatActivity  {

    TextView aboutus,details;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_aboutus);

        aboutus = (TextView) findViewById(R.id.aboutus);
        details = (TextView) findViewById(R.id.details);

        String fontPath = "fonts/PFBeauSansPro-Reg_0.otf";
        final Typeface opensansfont = Typeface.createFromAsset(getAssets(), fontPath);

        aboutus.setTypeface(opensansfont);
        details.setTypeface(opensansfont);


    }
}

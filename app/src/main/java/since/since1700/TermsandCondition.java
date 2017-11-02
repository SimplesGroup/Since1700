package since.since1700;

import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

/**
 * Created by Kuppusamy on 11/2/2017.
 */

public class TermsandCondition extends AppCompatActivity {
    TextView termsandcondition;
        WebView details;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_termsandcondition);
        termsandcondition = (TextView) findViewById(R.id.termsandcondition);
        details=(WebView)findViewById(R.id.details);
        String fontPath = "fonts/OpenSans-Regular.ttf";
        final Typeface opensansfont = Typeface.createFromAsset(getAssets(), fontPath);
        termsandcondition.setTypeface(opensansfont);
        termsandcondition.setText("Terms and Condition");
        WebSettings webSettings = details.getSettings();

        webSettings.setJavaScriptEnabled(true);
       // details.setWebViewClient(new MyBrowser());
        details.getSettings().setLoadsImagesAutomatically(true);
        details.getSettings().setPluginState(WebSettings.PluginState.ON);
        details.getSettings().setAllowFileAccess(true);
        details.getSettings().setJavaScriptEnabled(true);
        String s="tLorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industrys standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.\\n \\n \\t Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industrys standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. \\n \\n \\t Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industrys standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged.";
        Log.e("DATA",s);
        details.loadData("<html>"+s+"</html>", "text/html", "utf-8");
    }
    private class MyBrowser extends WebViewClient {

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
            // ((EditText) getActionBar().getCustomView().findViewById(R.id.editText)).setText(url);
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }
}

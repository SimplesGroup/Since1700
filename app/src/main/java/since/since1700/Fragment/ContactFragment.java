package since.since1700.Fragment;

import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import since.since1700.R;

/**
 * Created by Kuppusamy on 9/21/2017.
 */

public class ContactFragment extends Fragment {
    TextView textView_title,textView_description;
    ImageButton button_call,button_whatsapp,button_email,button_sms;

    @Nullable
    public static ContactFragment newInstance() {
        ContactFragment fragment = new ContactFragment();
        return fragment;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.contact_page,container,false);
        String fontPath = "fonts/PFBeauSansPro-Reg_0.otf";
        final Typeface opensansfont = Typeface.createFromAsset(getActivity().getAssets(), fontPath);
            textView_title=(TextView)view.findViewById(R.id.contact_title);
        textView_description=(TextView)view.findViewById(R.id.contact_description);
        button_call=(ImageButton)view.findViewById(R.id.callus);
        button_whatsapp=(ImageButton)view.findViewById(R.id.wharsapp);
        button_email=(ImageButton)view.findViewById(R.id.emailus);
        button_sms=(ImageButton)view.findViewById(R.id.sms);

        textView_title.setTypeface(opensansfont);
        textView_description.setTypeface(opensansfont);
        textView_title.setText("Concierge");
        textView_description.setText("An exsclusive personal shopper service connected to a network of luxury brands.our object istry to get a up-scale luxury market in closer contact with our customers.");



      final   String phone="1234567890";
        button_call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:" +phone));
                startActivity(intent);
            }
        });

        button_whatsapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                Uri uri = Uri.parse("smsto:"+"9894111173");
                Intent sendIntent = new Intent(Intent.ACTION_SENDTO,uri);
                sendIntent.setPackage("com.whatsapp");
                //sendIntent.putExtra("sms_body", "The SMS text");
               sendIntent.putExtra(Intent.EXTRA_TEXT, "This is my text to send.");
                //sendIntent.setType("text/plain");
                startActivity(sendIntent);

            }
        });
button_email.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent email = new Intent(Intent.ACTION_SEND);
        email.putExtra(Intent.EXTRA_EMAIL, new String[]{"youremail@yahoo.com"});
        email.putExtra(Intent.EXTRA_SUBJECT, "subject");
        email.putExtra(Intent.EXTRA_TEXT, "message");
        email.setType("message/rfc822");
        startActivity(Intent.createChooser(email, "Choose an Email client :"));
    }
});

        button_sms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("smsto:"+"0800000123");
                Intent it = new Intent(Intent.ACTION_SENDTO, uri);
                it.putExtra("sms_body", "The SMS text");
                startActivity(it);
            }
        });

        return view;
    }
}

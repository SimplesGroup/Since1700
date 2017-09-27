package since.since1700.Fragment.FeedFragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import since.since1700.R;

/**
 * Created by Kuppusamy on 9/26/2017.
 */

public class Accessories_Frag extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fee_child_feartured,container,false);

        return view;
    }
}

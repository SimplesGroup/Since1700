package since.since1700.Fragment.ShopFragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import since.since1700.R;

/**
 * Created by Sandhiya on 9/21/2017.
 */

public class ShopOnSaleFragment extends Fragment {
    @Nullable
    public static ShopOnSaleFragment newInstance() {
        ShopOnSaleFragment fragment = new ShopOnSaleFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.feed_fragment, container, false);
        return view;
    }
}

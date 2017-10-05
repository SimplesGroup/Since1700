package since.since1700.Fragment.Brands.DetailDesign;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import since.since1700.R;

/**
 * Created by Kuppusamy on 10/5/2017.
 */

public class ShopdetailBrand extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.brands_detail_feed_page,container,false);

        return view;
    }
}

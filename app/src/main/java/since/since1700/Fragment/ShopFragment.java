package since.since1700.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import since.since1700.Fragment.ShopFragments.ShopBikesFragment;
import since.since1700.Fragment.ShopFragments.ShopCarsFragment;
import since.since1700.Fragment.ShopFragments.ShopCategoryFragment;
import since.since1700.Fragment.ShopFragments.ShopHomeFragment;
import since.since1700.Fragment.ShopFragments.ShopJetFragment;
import since.since1700.Fragment.ShopFragments.ShopJewelleryFragment;
import since.since1700.Fragment.ShopFragments.ShopOnSaleFragment;
import since.since1700.Fragment.ShopFragments.ShopPopularFragment;
import since.since1700.R;

/**
 * Created by Kuppusamy on 9/21/2017.
 */

public class ShopFragment extends Fragment {
    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;


    @Nullable
    public static ShopFragment newInstance() {
        ShopFragment fragment = new ShopFragment();
        return fragment;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.shop_fragment,container,false);
        /*toolbar = (Toolbar) view.findViewById(R.id.toolbar);

        getActivity().setSupportActionBar(toolbar);
*/

        viewPager = (ViewPager) view.findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) view.findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        return view;
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getChildFragmentManager());

        adapter.addFragment(new ShopCategoryFragment(), "Featured");
        adapter.addFragment(new ShopHomeFragment(), "Offer");
        adapter.addFragment(new ShopCarsFragment(), "Cars");
        adapter.addFragment(new ShopBikesFragment(), "Bike");
        adapter.addFragment(new ShopJetFragment(), "Jet");
        adapter.addFragment(new ShopJewelleryFragment(), "Jewellery");

        viewPager.setAdapter(adapter);
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);

        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }
}

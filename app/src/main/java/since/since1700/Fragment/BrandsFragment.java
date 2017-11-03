package since.since1700.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import since.since1700.Fragment.Brands.Featured_brands;
import since.since1700.R;

/**
 * Created by Kuppusamy on 9/21/2017.
 */

public class BrandsFragment extends Fragment {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    FeedViewPagerAdapter adapter;
    @Nullable
    public static BrandsFragment newInstance() {
        BrandsFragment fragment = new BrandsFragment();
        return fragment;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.brands_fragment,container,false);
        viewPager = (ViewPager) view.findViewById(R.id.viewpager);
        viewPager.setOffscreenPageLimit(1);

        adapter = new FeedViewPagerAdapter(getChildFragmentManager());
        tabLayout = (TabLayout) view.findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabsFromPagerAdapter(adapter);
        setupViewPager(viewPager);
        return view;
    }
    private void setupViewPager(ViewPager viewPager) {
        FeedViewPagerAdapter adapter = new FeedViewPagerAdapter(getChildFragmentManager());
        adapter.addFragment(new Featured_brands(), "Featured");
        adapter.addFragment(new Featured_brands(), "Accessories");
        adapter.addFragment(new Featured_brands(), "Apparel");
        adapter.addFragment(new Featured_brands(), "Art");
        adapter.addFragment(new Featured_brands(), "Beauty");
        adapter.addFragment(new Featured_brands(), "Bikes");
        adapter.addFragment(new Featured_brands(), "Cars");
        adapter.addFragment(new Featured_brands(), "Decor");
        adapter.addFragment(new Featured_brands(), "Footwear");
        adapter.addFragment(new Featured_brands(), "Gadgets");
        adapter.addFragment(new Featured_brands(), "Gourmet");
        adapter.addFragment(new Featured_brands(), "Homes");
        adapter.addFragment(new Featured_brands(), "Jets & Yachts");
        adapter.addFragment(new Featured_brands(), "Jewellery");
        adapter.addFragment(new Featured_brands(), "Travel");
        adapter.addFragment(new Featured_brands(), "Watches");
        viewPager.setAdapter(adapter);
    }

    class FeedViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public FeedViewPagerAdapter(FragmentManager manager) {
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

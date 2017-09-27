package since.since1700.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
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

import since.since1700.Fragment.FeedFragments.Accessories_Frag;
import since.since1700.Fragment.FeedFragments.Apparel;
import since.since1700.Fragment.FeedFragments.Art;
import since.since1700.Fragment.FeedFragments.Beauty;
import since.since1700.Fragment.FeedFragments.Bikes;
import since.since1700.Fragment.FeedFragments.Cars;
import since.since1700.Fragment.FeedFragments.Decor;
import since.since1700.Fragment.FeedFragments.Featured;
import since.since1700.Fragment.FeedFragments.Footwear;
import since.since1700.Fragment.FeedFragments.Gadgets;
import since.since1700.Fragment.FeedFragments.Gourmet;
import since.since1700.Fragment.FeedFragments.Homes;
import since.since1700.Fragment.FeedFragments.JetsYachts;
import since.since1700.Fragment.FeedFragments.Jewellery;
import since.since1700.Fragment.FeedFragments.Travel;
import since.since1700.Fragment.FeedFragments.Watches;
import since.since1700.R;

/**
 * Created by Kuppusamy on 9/21/2017.
 */

public class FeedFragment extends Fragment {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    FeedViewPagerAdapter adapter;
    CoordinatorLayout mCoordinator;
    CollapsingToolbarLayout mCollapsingToolbarLayout;
    @Nullable
    public static FeedFragment newInstance() {
        FeedFragment fragment = new FeedFragment();
        return fragment;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.feed_fragment,container,false);



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
        adapter.addFragment(new Featured(), "Featured");
        adapter.addFragment(new Accessories_Frag(), "Accessories");
        adapter.addFragment(new Apparel(), "Apparel");
        adapter.addFragment(new Art(), "Art");
        adapter.addFragment(new Beauty(), "Beauty");
        adapter.addFragment(new Bikes(), "Bikes");
        adapter.addFragment(new Cars(), "Cars");
        adapter.addFragment(new Decor(), "Decor");
        adapter.addFragment(new Footwear(), "Footwear");
        adapter.addFragment(new Gadgets(), "Gadgets");
        adapter.addFragment(new Gourmet(), "Gourmet");
        adapter.addFragment(new Homes(), "Homes");
        adapter.addFragment(new JetsYachts(), "Jets & Yachts");
        adapter.addFragment(new Jewellery(), "Jewellery");
        adapter.addFragment(new Travel(), "Travel");
        adapter.addFragment(new Watches(), "Watches");
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

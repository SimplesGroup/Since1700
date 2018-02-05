package since.since1700.Fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.List;
import since.since1700.Fragment.FeedFragments.Featured;
import since.since1700.R;

/**
 * Created by Kuppusamy on 9/21/2017.
 */

public class FeedFragment extends Fragment {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    FeedViewPagerAdapter adapter;
    SharedPreferences sharedpreferences;
    public static final String mypreference = "mypref";
    public static final String Interestarea = "interestarea";
    String user_interested_area;
    @Nullable
    public static FeedFragment newInstance() {
        FeedFragment fragment = new FeedFragment();
        return fragment;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.feed_fragment,container,false);
        sharedpreferences = getActivity(). getSharedPreferences(mypreference,
                Context.MODE_PRIVATE);

        user_interested_area=sharedpreferences.getString(Interestarea,"");
        Log.e("INTEREST",user_interested_area);
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
        adapter.addFragment(new Featured(), "Accessories");
        adapter.addFragment(new Featured(), "Apparel");
        adapter.addFragment(new Featured(), "Art");
        adapter.addFragment(new Featured(), "Auctions");
        adapter.addFragment(new Featured(), "Beauty");
        adapter.addFragment(new Featured(), "Bikes");
        adapter.addFragment(new Featured(), "Cars");
        adapter.addFragment(new Featured(), "Consumer electronics");
        adapter.addFragment(new Featured(), "Footwear");
        adapter.addFragment(new Featured(), "Fragrances");
        adapter.addFragment(new Featured(), "Gastronomy");
        adapter.addFragment(new Featured(), "Home Decor");
        adapter.addFragment(new Featured(), "Homes");
        adapter.addFragment(new Featured(), "Jets & Yachts");
        adapter.addFragment(new Featured(), "Jewellery");
        adapter.addFragment(new Featured(), "Premium events");
        adapter.addFragment(new Featured(), "Travel");
        adapter.addFragment(new Featured(), "Watches");
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

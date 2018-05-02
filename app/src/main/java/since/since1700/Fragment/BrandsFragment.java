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

import since.since1700.Fragment.Brands.Accessories_Frag_brands;
import since.since1700.Fragment.Brands.Apparel_brands;
import since.since1700.Fragment.Brands.Art_brands;
import since.since1700.Fragment.Brands.Beauty_brands;
import since.since1700.Fragment.Brands.Bikes_brands;
import since.since1700.Fragment.Brands.Cars_brands;
import since.since1700.Fragment.Brands.Decor_brands;
import since.since1700.Fragment.Brands.Featured_brands;
import since.since1700.Fragment.Brands.Footwear_brands;
import since.since1700.Fragment.Brands.Gadgets_brands;
import since.since1700.Fragment.Brands.Gourmet_brands;
import since.since1700.Fragment.Brands.Homes_brands;
import since.since1700.Fragment.Brands.JetsYachts_brands;
import since.since1700.Fragment.Brands.Jewellery_brands;
import since.since1700.Fragment.Brands.Travel_brands;
import since.since1700.Fragment.Brands.Watches_brands;
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
        adapter.addFragment(new Accessories_Frag_brands(), "Accessories");
        adapter.addFragment(new Apparel_brands(), "Apparel");
        adapter.addFragment(new Art_brands(), "Art");
        adapter.addFragment(new Beauty_brands(), "Beauty");
        adapter.addFragment(new Bikes_brands(), "Bikes");
        adapter.addFragment(new Cars_brands(), "Cars");
        adapter.addFragment(new Decor_brands(), "Decor");
        adapter.addFragment(new Footwear_brands(), "Footwear");
        adapter.addFragment(new Gadgets_brands(), "Gadgets");
        adapter.addFragment(new Gourmet_brands(), "Gourmet");
        adapter.addFragment(new Homes_brands(), "Homes");
        adapter.addFragment(new JetsYachts_brands(), "Jets & Yachts");
        adapter.addFragment(new Jewellery_brands(), "Jewellery");
        adapter.addFragment(new Travel_brands(), "Travel");
        adapter.addFragment(new Watches_brands(), "Watches");
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

package since.since1700.Filter;

/**
 * Created by Ravi on 29/07/15.
 */

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ExpandableListView;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import since.since1700.R;

public class FragmentDrawer extends Fragment {

    private static String TAG = FragmentDrawer.class.getSimpleName();

    private RecyclerView recyclerView;
    private ActionBarDrawerToggle mDrawerToggle;
    private DrawerLayout mDrawerLayout;
    private NavigationDrawerAdapter adapter;
    private View containerView;
    private static String[] titles = null;
    private FragmentDrawerListener drawerListener;
    private ExpandableListView mDrawerListView;
    List<String> expandableListTitle;
    HashMap<String, List<String>> expandableListDetail;
    Button close;


    public FragmentDrawer() {

    }

    public void setDrawerListener(FragmentDrawerListener listener) {
        this.drawerListener = listener;
    }

    public static List<NavDrawerItem> getData() {
        List<NavDrawerItem> data = new ArrayList<>();


        // preparing navigation drawer items
        for (int i = 0; i < titles.length; i++) {
            NavDrawerItem navItem = new NavDrawerItem();
            navItem.setTitle(titles[i]);
            data.add(navItem);
        }
        return data;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // drawer labels
        titles = getActivity().getResources().getStringArray(R.array.nav_drawer_labels);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflating view layout
        View layout = inflater.inflate(R.layout.fragment_navigation_drawer, container, false);


        mDrawerListView = (ExpandableListView) layout.findViewById(R.id.lvExp);
        close = (Button)layout.findViewById(R.id.close);


        mDrawerListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                drawerListener.onDrawerItemSelected(view, position);
                mDrawerLayout.closeDrawer(containerView);
            }
        });

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDrawerLayout.closeDrawers();
            }
        });

        expandableListDetail = ExpandableListDataPump.getData();
        expandableListTitle = new ArrayList<String>(expandableListDetail.keySet());


     mDrawerListView.setAdapter(new ExpandableListAdapter(getActivity(), expandableListTitle, expandableListDetail));

        mDrawerListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {

            @Override
            public void onGroupExpand(int groupPosition) {

            }
        });

        mDrawerListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                boolean retVal = true;

               // Fragment fragment = null;
                String title = getString(R.string.app_name);
                // Class fragmentClass = null;
                /*if (groupPosition == ExpandableListAdapterTamil.ITEM1) {
                    retVal = false;
                } else if (groupPosition == ExpandableListAdapterTamil.ITEM2) {*/

                Log.e("POSITIONNNN", String.valueOf(groupPosition));
                    /*Fragment fragment = new HerbesAndSpicesEnglish();
                    FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.container_body, fragment);
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();
*/
                //retVal = false;
                /*} else if (groupPosition == ExpandableListAdapterTamil.ITEM3) {
                    retVal = false;
                    // call some activity here
                } else if (groupPosition == ExpandableListAdapterTamil.ITEM4) {
                    // call some activity here
                    retVal = false;
                }*/
                return retVal;
            }
        });

        mDrawerListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {

            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {
                Log.e("POSITIONNNNPAR", String.valueOf(groupPosition));
                Log.e("POSITIONNNNCHILDD", String.valueOf(childPosition ));

                if (groupPosition == ExpandableListAdapter.ITEM1) {
                    if (childPosition == ExpandableListAdapter.SUBITEM1_1) {

                   /*     Intent i = new Intent(getActivity(),ProductDetailsEnglish.class);
                        startActivity(i);*/

                    }
                    else if (childPosition == ExpandableListAdapter.SUBITEM1_2) {

                        // call activity here

                    }
                    else if (childPosition == ExpandableListAdapter.SUBITEM1_3) {

                        // call activity here

                    }
                    else if (childPosition == ExpandableListAdapter.SUBITEM1_4) {

                        // call activity here

                    }


                } else if (groupPosition == ExpandableListAdapter.ITEM2) {

                    if (childPosition == ExpandableListAdapter.SUBITEM2_1) {

                        /*Intent i = new Intent(getActivity(),ProductDetailsEnglish.class);
                        startActivity(i);
*/
                    }
                    else if (childPosition == ExpandableListAdapter.SUBITEM2_2) {

                        // call activity here

                    }
                    else if (childPosition == ExpandableListAdapter.SUBITEM2_3) {

                        // call activity here

                    }
                    else if (childPosition == ExpandableListAdapter.SUBITEM2_4) {

                        // call activity here

                    }

                    else if (groupPosition == ExpandableListAdapter.ITEM3) {

                        if (childPosition == ExpandableListAdapter.SUBITEM2_1) {

                            // call activity here

                        } else if (childPosition == ExpandableListAdapter.SUBITEM2_2) {

                            // call activity here

                        } else if (childPosition == ExpandableListAdapter.SUBITEM2_3) {

                            // call activity here

                        } else if (childPosition == ExpandableListAdapter.SUBITEM2_4) {

                            // call activity here

                        }
                    }


                }
                return true;
            }
        });
        return layout;
    }


    public void setUp(int fragmentId, DrawerLayout drawerLayout, final Toolbar toolbar) {
        containerView = getActivity().findViewById(fragmentId);
        mDrawerLayout = drawerLayout;
        mDrawerToggle = new ActionBarDrawerToggle(getActivity(), drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                getActivity().invalidateOptionsMenu();
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                getActivity().invalidateOptionsMenu();
            }

            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                super.onDrawerSlide(drawerView, 1);
             //toolbar.setAlpha(1 - slideOffset / 2);
            }
        };

        mDrawerLayout.setDrawerListener(mDrawerToggle);
        mDrawerLayout.post(new Runnable() {
            @Override
            public void run() {
                mDrawerToggle.syncState();
            }
        });

    }

    public static interface ClickListener {
        public void onClick(View view, int position);

        public void onLongClick(View view, int position);
    }

    static class RecyclerTouchListener implements RecyclerView.OnItemTouchListener {

        private GestureDetector gestureDetector;
        private ClickListener clickListener;

        public RecyclerTouchListener(Context context, final RecyclerView recyclerView, final ClickListener clickListener) {
            this.clickListener = clickListener;
            gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
                @Override
                public boolean onSingleTapUp(MotionEvent e) {
                    return true;
                }

                @Override
                public void onLongPress(MotionEvent e) {
                    View child = recyclerView.findChildViewUnder(e.getX(), e.getY());
                    if (child != null && clickListener != null) {
                        clickListener.onLongClick(child, recyclerView.getChildPosition(child));
                    }
                }
            });
        }

        @Override
        public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {

            View child = rv.findChildViewUnder(e.getX(), e.getY());
            if (child != null && clickListener != null && gestureDetector.onTouchEvent(e)) {
                clickListener.onClick(child, rv.getChildPosition(child));
            }
            return false;
        }

        @Override
        public void onTouchEvent(RecyclerView rv, MotionEvent e) {
        }

        @Override
        public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

        }


    }

    public interface FragmentDrawerListener {
        public void onDrawerItemSelected(View view, int position);
    }
}

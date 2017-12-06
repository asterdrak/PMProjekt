package rustrll.sl.com.russiantrello;

import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.julienvey.trello.domain.Card;

import java.util.ArrayList;
import java.util.List;

public class BoardActivity extends AppCompatActivity {


    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private BoardActivity.SectionsPagerAdapter mSectionsPagerAdapter;
    Trellolizer trellolizer;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new BoardActivity.SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                trellolizer = new Trellolizer();
                String text = "";
                List<Card> cards = trellolizer.get_cards();
                for(int i = 0; i < cards.size(); i++) {
                    text += cards.get(i).getName() + ", ";
                }

                Snackbar.make(view, text, Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_list, container, false);

            ListView tasksListView = rootView.findViewById(R.id.tasksListView);

            TaskAdapter tasksListAdapter = new TaskAdapter(getActivity().getApplicationContext(), tasksList(getArguments().getInt(ARG_SECTION_NUMBER)));
            tasksListView.setAdapter(tasksListAdapter);
            return rootView;
        }


        public ArrayList<Task> tasksList(int position) {
            switch (position) {
                case 5:
                    return otherTasksList();
                case 4:
                    return mustTasksList();
                case 3:
                    return shouldTasksList();
                case 2:
                    return couldTasksList();
                case 1:
                    return wontTasksList();
            }
            return new ArrayList<>();
        }

        private ArrayList<Task> wontTasksList() {
            ArrayList tasksList = new ArrayList<Task>();

            tasksList.add(new Task("Zadanie 1", "Do jutra 12:00"));
            tasksList.add(new Task("Zadanie 2", "Do dziś 13:00"));
            tasksList.add(new Task("Zadanie 3", "Do jutra 9:00"));

            return tasksList;
        }

        private ArrayList<Task> couldTasksList() {
            ArrayList tasksList = new ArrayList<Task>();

            tasksList.add(new Task("Zadanie 4", "Do jutra 12:00"));
            tasksList.add(new Task("Zadanie 5", "Do dziś 13:00"));
            tasksList.add(new Task("Zadanie 6", "Do jutra 9:00"));

            return tasksList;

        }

        private ArrayList<Task> shouldTasksList() {
            ArrayList tasksList = new ArrayList<Task>();

            tasksList.add(new Task("Zadanie 7", "Do jutra 12:00"));
            tasksList.add(new Task("Zadanie 8", "Do dziś 13:00"));
            tasksList.add(new Task("Zadanie 9", "Do jutra 9:00"));

            return tasksList;
        }

        private ArrayList<Task> mustTasksList() {
            ArrayList tasksList = new ArrayList<Task>();

            tasksList.add(new Task("Zadanie 10", "Do jutra 12:00"));
            tasksList.add(new Task("Zadanie 11", "Do dziś 13:00"));
            tasksList.add(new Task("Zadanie 12", "Do jutra 9:00"));

            return tasksList;
        }

        private ArrayList<Task> otherTasksList() {
            ArrayList tasksList = new ArrayList<Task>();

            tasksList.add(new Task("Zadanie 13", "Do jutra 12:00"));
            tasksList.add(new Task("Zadanie 14", "Do dziś 13:00"));
            tasksList.add(new Task("Zadanie 15", "Do jutra 9:00"));

            return tasksList;
        }
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            return PlaceholderFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 5;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "To Assign";
                case 1:
                    return "Must";
                case 2:
                    return "Should";
                case 3:
                    return "Could";
                case 4:
                    return "Won't";
            }
            return null;
        }
    }
}

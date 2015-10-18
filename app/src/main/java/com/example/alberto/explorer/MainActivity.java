package com.example.alberto.explorer;

import android.content.Intent;
import android.database.Cursor;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.*;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    static int k=0;
    static boolean pass=false;
    static CallbackManager callbackManager;
    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;
    public static CallbackManager callbackmanager;
    public static AccessTokenTracker accessTokenTracker;
    public static AccessToken accessToken;
    static DBadapter myDb;
    private void openDB() {
        myDb = new DBadapter(this);
        myDb.open();
    }
    private void closeDB() {
        myDb.close();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        FacebookSdk.sdkInitialize(getApplicationContext());
        callbackManager = CallbackManager.Factory.create();
        openDB();

        accessTokenTracker = new AccessTokenTracker() {
            @Override
            protected void onCurrentAccessTokenChanged(
                    AccessToken oldAccessToken,
                    AccessToken currentAccessToken) {
                // Set the access token using
                // currentAccessToken when it's loaded or set.
            }
        };
        // If the access token is available already assign it.
        accessToken = AccessToken.getCurrentAccessToken();
        LoginManager.getInstance().registerCallback(callbackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        // App code
                        accessToken = AccessToken.getCurrentAccessToken();
                    }

                    @Override
                    public void onCancel() {
                        // App code
                    }

                    @Override
                    public void onError(FacebookException exception) {
                        // App code
                    }
                });

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);





        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                myDb.deleteAll();
                Demo.create_list(myDb);
            }
        });

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }
  //  @Override
    //protected void onResume() {
    //    super.onResume();

        // Logs 'install' and 'app activate' App Events.
     //   AppEventsLogger.activateApp(this);
   // }

   // @Override
   // protected void onPause() {
    //    super.onPause();

        // Logs 'app deactivate' App Event.
    //    AppEventsLogger.deactivateApp(this);
   // }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_list) {
           k=1;
            pass=true;

        }

        return super.onOptionsItemSelected(item);
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
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "SECTION 1";
                case 1:
                    return "SECTION 2";
                case 2:
                    return "SECTION 3";
                case 3:
                    return "SECTION 4";
            }
            return null;
        }
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

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            if(!pass)
              k=getArguments().getInt(ARG_SECTION_NUMBER);

            pass=false;
            if(k==3) {

                final View    rootView3= inflater.inflate(R.layout.reg_image3, container, false);

                ViewChanger.getview(rootView3);
            }

            if(k==2) {

                final View    rootView2= inflater.inflate(R.layout.reg_image, container, false);

                ViewChanger.getview(rootView2);
            }
            if(k==1) {

              final View    rootView= inflater.inflate(R.layout.fragment_main, container, false);

                Cursor cursor = myDb.getAllRows();

                // Allow activity to manage lifetime of the cursor.
                // DEPRECATED! Runs on the UI thread, OK for small/short queries.


                // Setup mapping from cursor to view fields:
                String[] fromFieldNames = new String[]
                        {DBadapter.KEY_NAME, DBadapter.KEY_IMAGE, DBadapter.KEY_PLACE, DBadapter.KEY_TYPE, DBadapter.KEY_BUDGET};

                int[] toViewIDs = new int[]
                        {R.id.item_name, R.id.item_icon, R.id.item_favcolour, R.id.textView, R.id.textView2};

                // Create adapter to may columns of the DB onto elemesnt in the UI.
                SimpleCursorAdapter myCursorAdapter =
                        new SimpleCursorAdapter(
                                getContext(),        // Context
                                R.layout.item_layout,    // Row layout template
                                cursor,                    // cursor (set of DB records to map)
                                fromFieldNames,            // DB Column names
                                toViewIDs                // View IDs to put information in
                        );

                // Set the adapter for the list view
                ViewChanger view2=new ViewChanger();
                view2.func(myCursorAdapter);
                ListView myList = (ListView) rootView.findViewById(R.id.listView);
                myList.setAdapter(ViewChanger.adtoshow);






                Spinner dropdown = (Spinner)rootView.findViewById(R.id.spinner1);
                String[] items = new String[]{"1", "2", "three"};
                ArrayAdapter<CharSequence> adapter = ArrayAdapter
                        .createFromResource(getContext(), R.array.brew_array,
                                android.R.layout.simple_spinner_item);

                dropdown.setAdapter(adapter);
                ViewChanger.getview(rootView);
            LoginButton loginButton = (LoginButton) rootView.findViewById(R.id.login_button);
            loginButton.setReadPermissions("user_friends");

            loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
                @Override
                public void onSuccess(LoginResult loginResult) {
                    accessToken = AccessToken.getCurrentAccessToken();
                    GraphRequest request = GraphRequest.newMeRequest(
                            accessToken,
                            new GraphRequest.GraphJSONObjectCallback() {
                                @Override
                                public void onCompleted(
                                        JSONObject object,
                                        GraphResponse response) {

                                    // JSONObject jobj = Util.parseJson(response);
                                    //  try {

                                    if (object != null) {
                                        String name = (String) object.opt("name");
                                        String age = (String) object.opt("birthday_date");
                                        String locale = (String) object.opt("locale");
                                        TextView textElement = (TextView) rootView.findViewById(R.id.login_label);
                                        textElement.setText("Welcome, "+name);
                                        //  JSONArray data = jobj.getJSONArray("data");
                                        // for (int i = 0; i < data.length(); i++) {
                                        //     String indi = data.getJSONObject(i).getString("name");
                                        System.out.println(name);
                                        // myDb.insertRow(name,locale, locale, name, name);


                                    }
                                }
                                // }

                                // } catch (JSONException e) {
                                //e.printStackTrace();
                                //}

                            });
                    Bundle parameters = new Bundle();
                    parameters.putString("fields", "id,name,link,age_range,locale");
                    request.setParameters(parameters);
                    request.executeAsync();
                }

                @Override
                public void onCancel() {
                    // App code
                }

                @Override
                public void onError(FacebookException exception) {
                    // App code
                }
            });


                registerListClickCallback();
            }

            return ViewChanger.setview();
        }

        private void registerListClickCallback() {

            View rootview=ViewChanger.viewtoshow;
            ListView myList = (ListView) rootview.findViewById(R.id.listView);
            myList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View viewClicked,
                                        int position, long idInDB) {

                    Toast.makeText(getContext(),
                            "Stations display is on", Toast.LENGTH_SHORT).show();
                }
            });
        }

    }

}

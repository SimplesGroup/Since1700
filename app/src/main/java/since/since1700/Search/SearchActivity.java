package since.since1700.Search;

import android.annotation.TargetApi;
import android.app.SearchManager;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ListView;
import android.widget.Toast;

import since.since1700.MainActivity;
import since.since1700.R;

/**
 * Created by Sandhiya on 10/26/2017.
 */

public class SearchActivity extends AppCompatActivity {

    private CustomAdapter customAdapter;
    ListView listView;
    Cursor cursor;
    StudentRepo studentRepo ;
    SQLiteDatabase mDatabase;
    private final static String TAG= MainActivity.class.getName().toString();

    public static final String DATABASE_NAME = "mysearchdatabase";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        mDatabase = openOrCreateDatabase(DATABASE_NAME, MODE_PRIVATE, null);
        studentRepo = new StudentRepo(this);
        cursor=studentRepo.getStudentList();
        customAdapter = new CustomAdapter(SearchActivity.this,  cursor, 0);
        listView = (ListView) findViewById(R.id.lstStudent);
        listView.setAdapter(customAdapter);
        listView.setVisibility(View.VISIBLE);
        if(cursor==null) insertDummy();
    }

    private void insertDummy(){
        Student student= new Student();

        student.age=22;
        student.email="tanwoonhow@intstinctcoder.com";
        student.name="Tan Woon How";
        studentRepo.insert(student);

        studentRepo = new StudentRepo(this);
        student.age=20;
        student.email="Jimmy@intstinctcoder.com";
        student.name="Jimmy Tan Yao Lin";
        studentRepo.insert(student);

        studentRepo = new StudentRepo(this);
        student.age=21;
        student.email="Robert@intstinctcoder.com";
        student.name="Robert Pattinson";
        studentRepo.insert(student);

        studentRepo = new StudentRepo(this);
        student.age=19;
        student.email="jason@intstinctcoder.com";
        student.name="Jason Tan";
        studentRepo.insert(student);


        studentRepo = new StudentRepo(this);
        student.age=18;
        student.email="bftan@intstinctcoder.com";
        student.name="Tan Bor Feng";
        studentRepo.insert(student);


        studentRepo = new StudentRepo(this);
        student.age=23;
        student.email="janet@intstinctcoder.com";
        student.name="Janelle Monae";
        studentRepo.insert(student);


        studentRepo = new StudentRepo(this);
        student.age=21;
        student.email="james@intstinctcoder.com";
        student.name="James Harden";
        studentRepo.insert(student);

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.options_menu, menu);

        //
        //getSupportActionBar().setDisplayHomeAsUpEnabled(false);

            SearchManager manager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
            MenuItem searchViewItem = menu.findItem(R.id.action_search);

            final SearchView searchViewAndroidActionBar = (SearchView) MenuItemCompat.getActionView(searchViewItem);
            searchViewAndroidActionBar.setOnQueryTextListener(new SearchView.OnQueryTextListener()  {

                @Override
                public boolean onQueryTextSubmit(String s) {
                    Log.d(TAG, "onQueryTextSubmit ");
                   // relativelayout.setVisibility(View.GONE);
                    listView.setVisibility(View.VISIBLE);
                    cursor=studentRepo.getStudentListByKeyword(s);
                    if (cursor==null){
                        Toast.makeText(SearchActivity.this,"No records found!",Toast.LENGTH_LONG).show();
                    }else{
                        Toast.makeText(SearchActivity.this, cursor.getCount() + " records found!",Toast.LENGTH_LONG).show();
                    }
                    customAdapter.swapCursor(cursor);

                    return false;
                }

                @Override
                public boolean onQueryTextChange(String s) {
                    Log.d(TAG, "onQueryTextChange ");
                    listView.setVisibility(View.VISIBLE);
                    cursor=studentRepo.getStudentListByKeyword(s);
                    if (cursor!=null){
                        customAdapter.swapCursor(cursor);
                    }

                    return false;
                }

            });

        return true;

    }
}

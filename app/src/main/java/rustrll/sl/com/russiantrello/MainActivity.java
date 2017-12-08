package rustrll.sl.com.russiantrello;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.design.widget.TabLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

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

import android.widget.EditText;
import android.widget.ListView;

import com.julienvey.trello.domain.Board;
import com.julienvey.trello.domain.Card;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    static final public String secretApiKey = "8373585fdd1635ff23cd99aad95e450065c9c7d2d6a7deb691c55af87db5e622";
    static final public String apiKey = "d18619238242c717df349f1a34dbead3";

    DatabaseHelper myDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EditText etToSave = (EditText) findViewById(R.id.editText1);
        String textFromPreferences = getSharedPreferences("accessKey", Activity.MODE_PRIVATE).getString("editText1", "");
        etToSave.setText(textFromPreferences);

        //Database is created with first time you use it. Later same database file is used by Android.
        myDb = new DatabaseHelper(this);
    }

    protected void trelloRedirect(View v){
        Uri uri = Uri.parse("https://trello.com/1/authorize?expiration=never&name=RussianTrello&key="+MainActivity.apiKey); // missing 'http://' will cause crashed
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }

    protected void saveAccessKey(View v){
        EditText etToSave = (EditText) findViewById(R.id.editText1);
        SharedPreferences.Editor preferencesEditor = getSharedPreferences("userAccessKey", Activity.MODE_PRIVATE).edit();
        String editTextData = etToSave.getText().toString();
        preferencesEditor.putString("editText1", editTextData);
        preferencesEditor.commit();
        Intent boardIntent = new Intent(this, BoardActivity.class);
        this.startActivity(boardIntent);
    }
}

package solo.shinhan.com.solo;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {

    private EditText mSearchHouse;
    private ImageView mSettingBtn;
    private ListView mHouseList;
    //private HouseInfoAdapter mAdapter;
    private ListAdapter mListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        mListAdapter  = new ListAdapter(SoloSingleton.getInstance().getHouseInfoList());
       // mAdapter = new HouseInfoAdapter(getApplicationContext(),R.id.root_layout,oData);
        mHouseList = (ListView) findViewById(R.id.house_list_view);

        mHouseList.setAdapter(mListAdapter);
    }
}

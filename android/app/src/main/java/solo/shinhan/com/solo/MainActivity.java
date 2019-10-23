package solo.shinhan.com.solo;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.core.content.ContextCompat;

public class MainActivity extends Activity {

    private EditText mSearchHouse;
    private ImageView mSettingBtn;
    private ImageView mBackBtn;
    private ListView mHouseList;
    //private HouseInfoAdapter mAdapter;
    private HouseListAdapter mHouseListAdapter;

    long pressedTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.blue_common));
        }

        mHouseListAdapter = new HouseListAdapter(SoloSingleton.getInstance().getHouseInfoList());
       // mAdapter = new HouseInfoAdapter(getApplicationContext(),R.id.root_layout,oData);
        mHouseList = (ListView) findViewById(R.id.house_list_view);
        mBackBtn = (ImageView) findViewById(R.id.back_btn);
        mBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        ((ImageView)findViewById(R.id.setting_btn)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SelectActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
            }
        });

        Log.i("size",SoloSingleton.getInstance().getHouseInfoList().size()+"");

        mHouseList.setAdapter(mHouseListAdapter);

        mHouseList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getBaseContext(), HouseDetailActivity.class);
                intent.putExtra("houseNo",i);
                startActivity(intent);
//                overridePendingTransition(0,0);
//                finish();
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
//        overridePendingTransition(0,0);
    }
}

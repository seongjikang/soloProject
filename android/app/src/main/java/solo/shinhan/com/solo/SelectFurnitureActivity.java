package solo.shinhan.com.solo;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;

public class SelectFurnitureActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select_furniture_activity);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(getBaseContext(), HouseDetailActivity.class);
        intent.putExtra("houseNo",getIntent().getIntExtra("houseNo",-1));
        startActivity(intent);
        overridePendingTransition(0,0);
        finish();
    }
}

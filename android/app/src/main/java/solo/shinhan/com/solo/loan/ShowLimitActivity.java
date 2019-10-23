package solo.shinhan.com.solo.loan;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.core.content.ContextCompat;
import solo.shinhan.com.solo.R;
import solo.shinhan.com.solo.SelectActivity;

public class ShowLimitActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_show_limit);

		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
			getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.blue_common));
		}
		setTopTitleView();

		Button btn_next = findViewById(R.id.btn_next);
		btn_next.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Intent intent = new Intent(getApplicationContext(), ShowResultActivity.class);
				startActivity(intent);
			}
		});
	}


	private void setTopTitleView() {
		ImageView backBtn = (ImageView) findViewById(R.id.back_btn);
		backBtn.setOnClickListener(new View.OnClickListener() {
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
	}

}

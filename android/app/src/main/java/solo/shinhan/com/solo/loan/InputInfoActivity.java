package solo.shinhan.com.solo.loan;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import solo.shinhan.com.solo.R;

public class InputInfoActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_input_info);

		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
			getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.blue_common));
		}

		setTopTitleView();

		Button btn_next = findViewById(R.id.btn_next);
		btn_next.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				checkValid();
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
	}

	public void checkValid() {

		// TODO check..

		Intent intent = new Intent(getApplicationContext(), ShowLimitActivity.class);
		startActivity(intent);
	}

}

package solo.shinhan.com.solo;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

public class LoginActivity extends Activity {
	private long pressedTime = 0;

	private LinearLayout mSolLoginBtn;
	private LinearLayout mNoUuid;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
			getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.blue_common));
		}

		mSolLoginBtn = (LinearLayout) findViewById(R.id.sol_login_btn);
		mNoUuid = findViewById(R.id.ll_no_uuid);

		mSolLoginBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
                /*
                    임시 USER DATA;
                */
				SoloUser soloUser = new SoloUser(0, "강성지", 30, "M");

				SoloSingleton.getInstance().setSoloUser(soloUser);
				Intent intent = new Intent(getBaseContext(), SelectActivity.class);
				startActivity(intent);
				overridePendingTransition(0, 0);
				finish();
			}
		});

		showLoginBtn();
	}

	/**
	 * Uuid 있으면 SOL로 로그인 버튼 / 아니면 회원가입 뷰 보여줌
	 *
	 * @return
	 */
	public void showLoginBtn() {
		boolean isUuid = true;
		if (isUuid) {
			mSolLoginBtn.setVisibility(View.VISIBLE);
			mNoUuid.setVisibility(View.GONE);
		} else {
			mSolLoginBtn.setVisibility(View.GONE);
			mNoUuid.setVisibility(View.VISIBLE);
		}
	}

	@Override
	public void onBackPressed() {
		if (pressedTime == 0) {
			Toast.makeText(LoginActivity.this, " 한 번 더 누르면 종료됩니다.", Toast.LENGTH_LONG).show();
			pressedTime = System.currentTimeMillis();
		} else {
			int seconds = (int) (System.currentTimeMillis() - pressedTime);

			if (seconds > 2000) {
				Toast.makeText(LoginActivity.this, " 한 번 더 누르면 종료됩니다.", Toast.LENGTH_LONG).show();
				pressedTime = 0;
			} else {
				super.onBackPressed();
				finish();
			}
		}
	}
}

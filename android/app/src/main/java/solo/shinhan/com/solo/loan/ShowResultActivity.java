package solo.shinhan.com.solo.loan;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;

import androidx.core.content.ContextCompat;
import solo.shinhan.com.solo.R;

public class ShowResultActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_show_result);

		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
			getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.blue_common));
		}
	}
}

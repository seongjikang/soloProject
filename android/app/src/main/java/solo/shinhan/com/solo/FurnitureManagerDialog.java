package solo.shinhan.com.solo;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;

public class FurnitureManagerDialog extends Dialog {

    private Button mRotateBtn;
    private Button mDeleteBtn;
    private ImageView mFurnitureView;
    private ImageView mCancelBtn;

    private View.OnClickListener mRotateListener;
    private View.OnClickListener mDeleteListener;
    private View.OnClickListener mCancelListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        layoutParams.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        layoutParams.dimAmount = 0.8f;
        getWindow().setAttributes(layoutParams);

        setContentView(R.layout.furniture_manager_dialog);

        mCancelBtn = (ImageView)findViewById(R.id.cancel_btn);
        mFurnitureView = (ImageView) findViewById(R.id.furniture_view);
        mRotateBtn = (Button) findViewById(R.id.rotate_btn);
        mDeleteBtn = (Button) findViewById(R.id.delete_btn);

        mCancelBtn.setOnClickListener(mCancelListener);
        mRotateBtn.setOnClickListener(mRotateListener);
        mDeleteBtn.setOnClickListener(mDeleteListener);

    }

    public FurnitureManagerDialog(@NonNull Context context, View.OnClickListener rotateListener, View.OnClickListener deleteListener, View.OnClickListener cancelListener) {
        super(context);
        this.mRotateListener = rotateListener;
        this.mDeleteListener = deleteListener;
        this.mCancelListener = cancelListener;
    }
}

package solo.shinhan.com.solo;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;

import java.util.ArrayList;

public class FurnitureManagerDialog extends Dialog {

    private Button mRotateBtn;
    private Button mDeleteBtn;
    private ImageView mFurnitureView;
    private ImageView mCancelBtn;

    private int mCurrentPosition;
    private int mDirection;

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
        mFurnitureView.setImageBitmap(SoloSingleton.getInstance().getMyCollocateFurnitureInfoList().get(mCurrentPosition).getFurnitureInfo().getFurnitureImage());
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

    public int getCurrentPosition() {
        return mCurrentPosition;
    }

    public void setCurrentPosition(int currentPosition) {
        this.mCurrentPosition = currentPosition;
    }

    public int getDirection() {
        return mDirection;
    }

    public void setDirection(int direction) {
        this.mDirection = direction;
    }

}

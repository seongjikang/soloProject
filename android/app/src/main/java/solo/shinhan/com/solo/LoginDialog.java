package solo.shinhan.com.solo;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

public class LoginDialog extends BottomSheetDialogFragment implements View.OnClickListener{

    public static LoginDialog getInstance() {
        return new LoginDialog();
    }

    TextView test;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.login_dialog,container, false);
        test = (TextView) view.findViewById(R.id.test_btn);

        test.setOnClickListener(this);
        return view;

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case  R.id.test_btn:
                Toast.makeText(getContext(),"Login",Toast.LENGTH_SHORT).show();
                break;
        }
        dismiss();
    }

}

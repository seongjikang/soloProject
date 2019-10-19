package solo.shinhan.com.solo;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class ItemListAdapter extends BaseAdapter {
    LayoutInflater inflater = null;
    private List<MyCollocateFurnitureInfo> m_oData = null;
    private int nListCnt = 0;
    public ItemListAdapter(List<MyCollocateFurnitureInfo> _oData) {
        this.m_oData = _oData;
        this.nListCnt = m_oData.size();
    }

    @Override
    public int getCount() {
        Log.i("TAG", "getCount");
        return nListCnt;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            final Context context = parent.getContext();
            if (inflater == null) {
                inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            }
            convertView = inflater.inflate(R.layout.list_item, parent, false);
        }

        ImageView mFurnitureView = (ImageView) convertView.findViewById(R.id.furniture_view);
        RelativeLayout mFurnitureLayout = (RelativeLayout) convertView.findViewById(R.id.furniture_layout);
//        mFurnitureView.setImageBitmap(m_oData.get(position).getFurnitureInfo().getFurnitureImage());
        Picasso.get().load(m_oData.get(position).getFurnitureInfo().getFurnitureImageString()).into(mFurnitureView);
        if(!SoloSingleton.getInstance().getMyCollocateFurnitureInfoList().get(position).isSelectFurniture()) {
            mFurnitureLayout.setBackgroundColor(Color.WHITE);
        }

        return convertView;
    }
}

package solo.shinhan.com.solo;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class FurnitureListAdapter extends BaseAdapter{
    LayoutInflater inflater = null;
    private List<FurnitureInfo> m_oData = null;
    private int nListCnt = 0;

    public FurnitureListAdapter(List<FurnitureInfo> _oData) {
        m_oData = _oData;
        nListCnt = m_oData.size();
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
            convertView = inflater.inflate(R.layout.list_furniture_info, parent, false);
        }

        TextView mFurnitureName = (TextView) convertView.findViewById(R.id.furniture_name);

        mFurnitureName.setText(m_oData.get(position).getFurnitureName());

        return convertView;
    }
}

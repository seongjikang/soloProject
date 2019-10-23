package solo.shinhan.com.solo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class HouseInfoAdapter extends BaseAdapter{

    private Context mContext;
    private int mLayout;
    private List<HouseInfo> mItemList;
    private LayoutInflater mInflater;

    public HouseInfoAdapter(Context context, int layout, List<HouseInfo> itemList) {
        this.mContext = context;
        this.mLayout = layout;
        this.mItemList = itemList;
        this.mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return 0;
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
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;

        if(view == null) {
            view = mInflater.inflate(R.layout.list_house_info,viewGroup,false);
            holder = new ViewHolder();
            holder.mHouseName = (TextView)view.findViewById(R.id.house_name);
            holder.mHouseSize = (TextView)view.findViewById(R.id.house_size);
            holder.mAddress = (TextView)view.findViewById(R.id.address);
            holder.mAddressDetail = (TextView)view.findViewById(R.id.address_detail);
            holder.mHouseView = (ImageView) view.findViewById(R.id.house_view);

            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        holder.mHouseName.setText(mItemList.get(i).getHouseName());
        holder.mAddress.setText(mItemList.get(i).getAddress());
        holder.mHouseSize.setText(""+mItemList.get(i).getHouseSize());
        holder.mAddressDetail.setText(mItemList.get(i).getAddressDetail());
//        holder.mHouseView.setImageBitmap(mItemList.get(i).getHouseView());
        Picasso.get().load(mItemList.get(i).getHouseViewString()).into(holder.mHouseView);
        if(mItemList.get(i).getHouseViewString() == null) {
            holder.mHouseView.setImageBitmap(mItemList.get(i).getHouseView());
        }

        return view;
    }

    private class ViewHolder{
        TextView mHouseName;
        TextView mHouseSize;
        TextView mAddress;
        TextView mAddressDetail;
        ImageView mHouseView;
    }

}

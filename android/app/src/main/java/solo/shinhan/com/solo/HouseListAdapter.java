package solo.shinhan.com.solo;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.BufferedInputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

public class HouseListAdapter extends BaseAdapter {
	LayoutInflater inflater = null;
	private List<HouseInfo> m_oData = null;
	private int nListCnt = 0;

	public HouseListAdapter(List<HouseInfo> _oData) {
		m_oData = _oData;
		nListCnt = m_oData.size();
	}

	@Override
	public int getCount() {
		Log.i("TAG", "getCount");
		return nListCnt;
	}

	@Override
	public Object getItem(int position) {
		return null;
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			final Context context = parent.getContext();
			if (inflater == null) {
				inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			}
			convertView = inflater.inflate(R.layout.list_house_info, parent, false);
		}

		TextView mHouseName = (TextView) convertView.findViewById(R.id.house_name);
		TextView mHouseSize = (TextView) convertView.findViewById(R.id.house_size);
		TextView mAddress = (TextView) convertView.findViewById(R.id.address);
		TextView mAddressDetail = (TextView) convertView.findViewById(R.id.address_detail);
		ImageView mHouseView = (ImageView) convertView.findViewById(R.id.house_view);

		mHouseName.setText(m_oData.get(position).getHouseName());
		mAddress.setText(m_oData.get(position).getAddress());
		mHouseSize.setText(m_oData.get(position).getHouseSize() + "㎡");
		mAddressDetail.setText(m_oData.get(position).getAddressDetail());
//		mHouseView.setImageBitmap(m_oData.get(position).getHouseView());
//		mHouseView.setImageBitmap(getBitmapFromURL(m_oData.get(position).getHouseViewString()));
		Picasso.get().load(m_oData.get(position).getHouseViewString()).into(mHouseView);

		return convertView;
	}

}
package com.logitech.adapter;

import com.logitech.view.R;
import com.logitech.model.DeviceDetailBO;
import com.logitech.model.DevicesBO;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * @author Nithya since 10/22/2015
 */ 

public class DeviceAdapter extends BaseAdapter {
	Context ctx;
	DevicesBO devicesBean;
	Holder viewHolder;

	public DeviceAdapter(Context ctx, DevicesBO data) {
		this.ctx = ctx;
		this.devicesBean = data;
	}

	@Override
	public int getCount() {
		return devicesBean.devices.length;
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
	public View getView(int position, View view, ViewGroup viewGroup) {
		if (view == null) {
			LayoutInflater inflater = (LayoutInflater) ctx
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			view = inflater.inflate(R.layout.device_row, viewGroup, false);
			viewHolder = new Holder();
			viewHolder.txtDeviceName = (TextView) view
					.findViewById(R.id.deviceName);
			viewHolder.txtDeviceModel = (TextView) view
					.findViewById(R.id.deviceModel);
			viewHolder.txtDeviceType = (TextView) view
					.findViewById(R.id.deviceType);
			view.setTag(viewHolder);
		} else {
			viewHolder = (Holder) view.getTag();
		}

		DeviceDetailBO deviceInfo = devicesBean.devices[position];
		viewHolder.txtDeviceName.setText(deviceInfo.name.toUpperCase());
		viewHolder.txtDeviceModel.setText(deviceInfo.model);
		viewHolder.txtDeviceType.setText(deviceInfo.deviceType);

		return view;
	}

	class Holder {
		TextView txtDeviceName;
		TextView txtDeviceType;
		TextView txtDeviceModel;
	}
}
package com.logitech.model;

import com.google.gson.annotations.SerializedName;

/**
 * @author Nithya since 10/22/2015
 */

public class DevicesBO {
	@SerializedName("devices")
	public DeviceDetailBO[] devices;
	
}

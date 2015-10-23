package com.logitech.model;

import com.google.gson.annotations.SerializedName;

/**
 * @author Nithya since 10/22/2015
 */

public class DeviceDetailBO {

	@SerializedName("deviceType")
	public String deviceType;

	@SerializedName("model")
	public String model;

	@SerializedName("name")
	public String name;

}

package com.list.commons;

import java.util.Comparator;

import com.list.jpa.Device;

public class DeviceNameComparator implements Comparator<Device>{

	@Override
	public int compare(Device o1, Device o2) {
		return o1.getDeviceName().compareToIgnoreCase(o2.getDeviceName());
	}
	
}

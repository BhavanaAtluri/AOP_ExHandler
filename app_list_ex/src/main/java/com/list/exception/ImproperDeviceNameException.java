package com.list.exception;

public class ImproperDeviceNameException extends DeviceNotFoundException{

	private static final long serialVersionUID = 1L;
	
	
	public ImproperDeviceNameException(){}
	
	public ImproperDeviceNameException(String statusMsg) {
		super(statusMsg);
	}
	
}

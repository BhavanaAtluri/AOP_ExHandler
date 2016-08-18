package com.list.dto;

import java.io.Serializable;

import org.springframework.data.annotation.Id;

public class DeviceDto implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DeviceDto(){
		
	}
	
	@Id
	private String deviceId;
	
	private String deviceName;
	private String eventType;
	private int eventCount;
	
	private String eventDate;
	
	public String getDeviceId() {
		return deviceId;
	}
	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}
	public String getDeviceName() {
		return deviceName;
	}
	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}
	public String getEventType() {
		return eventType;
	}
	public void setEventType(String eventType) {
		this.eventType = eventType;
	}
	public int getEventCount() {
		return eventCount;
	}
	public void setEventCount(int eventCount) {
		this.eventCount = eventCount;
	}
	public String getEventDate() {
		return eventDate;
	}
	public void setEventDate(String eventDate) {
		this.eventDate = eventDate;
	}
	
	
	public String toString(){
		return "{Device details - Id : "+ this.deviceId + ", Name : "+ this.deviceName + ", event count : "+this.eventCount+", event Date: "+this.eventDate+", Event Type : "+ this.eventType+" }";
	}

}

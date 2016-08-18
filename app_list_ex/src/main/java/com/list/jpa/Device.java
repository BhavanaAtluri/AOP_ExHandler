package com.list.jpa;

import java.util.Date;
import java.sql.Time;

import org.springframework.data.annotation.Id;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;


public class Device {
	
	public Device(){
		
	}
	
	@Id
	private String deviceId;
	
	private String deviceName;
	private String eventType;
	private int eventCount;
	

	@DateTimeFormat(iso=ISO.DATE_TIME, pattern="yyyy-MM-dd'T'HH:mm:ss.SSSZ")
	private Date eventDate;
	
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
	public Date getEventDate() {
		return eventDate;
	}
	public void setEventDate(Date eventDate) {
		this.eventDate = eventDate;
	}
	
	
	public String toString(){
		return "{Device details - Id : "+ this.deviceId + ", Name : "+ this.deviceName + ", event count : "+this.eventCount+", event Date: "+this.eventDate+", Event Type : "+ this.eventType+" }";
	}

}

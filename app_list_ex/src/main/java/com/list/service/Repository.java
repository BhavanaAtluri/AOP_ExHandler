package com.list.service;


import java.util.Date;
import java.sql.Time;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.list.dto.DeviceDto;
import com.list.jpa.Device;

public interface Repository extends MongoRepository<Device, Long>{

//	public List<Device> findByEventDate(Date eventDate);
	
	public Device findByDeviceId(String id);
	
	public List<Device> findByDeviceName(String deviceName);
	
	public List<DeviceDto> findByEventDateGreaterThan(Date date);
	
	public Device save(Device d);
	
}

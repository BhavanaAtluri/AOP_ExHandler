package com.list.service;

import java.util.ArrayList;

import java.util.Date;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.list.dto.DeviceDto;
import com.list.jpa.Device;

@Service
public class DeviceService{
	@Autowired
	private Repository repository;
	@Autowired
    private MongoTemplate mongoTemplate;
	
	public Device Save(Device d){
		return repository.save(d); 
	}
	
	public DeviceDto findById(String deviceId){
		Device d = repository.findByDeviceId(deviceId);
		
		DeviceDto dto = map(d,d.getEventDate());
		
		return dto;
	}
	
	public List<DeviceDto> findByEventDateGreaterThan(Date date){
		
		Query query = new Query();

		query.addCriteria(Criteria.where("eventDate").gte(date));
		
		
		
		List<Device> deviceList = mongoTemplate.find(query, Device.class);
		
		List<DeviceDto> deviceDtoList = new ArrayList<DeviceDto>();
		for(Device d:deviceList){
			DeviceDto dto = map(d,d.getEventDate());
			deviceDtoList.add(dto);
		}
		return deviceDtoList;
	}
	
	public List<DeviceDto> findByDeviceName(String deviceName){
		List<Device> deviceList = repository.findByDeviceName(deviceName);
		
		List<DeviceDto> deviceDtoList = new ArrayList<DeviceDto>();
		for(Device d:deviceList){
			DeviceDto dto = map(d,d.getEventDate());
			deviceDtoList.add(dto);
		}
		return deviceDtoList;
	}
	
	private DeviceDto map(Device d, Date date){
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss");
		
		DeviceDto dto = new DeviceDto();
		
		dto.setDeviceId(d.getDeviceId());
		dto.setDeviceName(d.getDeviceName());
		dto.setEventCount(d.getEventCount());
		dto.setEventType(d.getEventType());
		dto.setEventDate(format.format(date));
		
		return dto;
	}
	
	
}
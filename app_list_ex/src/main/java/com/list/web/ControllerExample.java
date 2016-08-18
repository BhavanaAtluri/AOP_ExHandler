package com.list.web;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.Enumeration;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.list.commons.DeviceNameComparator;
import com.list.dto.DeviceDto;
import com.list.exception.DeviceNotFoundException;
import com.list.exception.ImproperDeviceNameException;
import com.list.jpa.Device;
import com.list.service.DeviceService;


@RestController
@RequestMapping("/device")
public class ControllerExample {
	@Autowired
	private DeviceService deviceService;
	
	private LinkedList<Device> deviceList = null;
	
	private LinkedList<Integer> list = null;
	
	private TreeMap<String,LinkedList<Device>> deviceTree = null;
	
	Map<String, Set<String>> dictionary = new TreeMap<>();
	
	ControllerExample(DeviceService deviceService){
		this.deviceService = deviceService;
	}
	
	@RequestMapping(value="/",method=RequestMethod.POST)
	@ResponseBody
	public Device create(@RequestBody Device device) throws Exception{
		System.out.println(device.toString());
		
//		Device saved = deviceService.Save(device);
		
		
		String dName = device.getDeviceName();
		
		if(!dName.toLowerCase().contains("device")){throw new ImproperDeviceNameException("The Device name is improper. It should start with 'Device'");}
	
		int deviceId;
		String deviceIdStr;
		
		if(dName.contains("-")){
			int index = dName.indexOf('-');
			
			deviceIdStr = dName.substring(6, index);
			
//			System.out.println("Indexxxxxxxxxxxxxxxxxxxxxxx : "+index);
			
			deviceId = Integer.valueOf(deviceIdStr); 
		
//			System.out.println("Devicceeeeeeeeeeeeeeeeee ID : "+deviceId);
		
		}
		else{
			deviceIdStr = dName.substring(6, dName.length()).trim();
		
			deviceId = Integer.valueOf(deviceIdStr); 
//			System.out.println("Devicceeeeeeeeeeeeeeeeee ID : "+deviceId);
		}

		/*
			Device saved = deviceService.Save(device);
		
		
			String dName = device.getDeviceName();
		
			int deviceId;
			String deviceIdStr;
		
			if(dName.contains("-")){
				int index = dName.indexOf('-');
			
				deviceIdStr = dName.substring(6, (index-1));
			
				System.out.println("Indexxxxxxxxxxxxxxxxxxxxxxx : "+index);
			
				deviceId = Integer.valueOf(deviceIdStr); 
			
				System.out.println("Devicceeeeeeeeeeeeeeeeee ID : "+deviceId);
			
				index = dName.indexOf('-');
			
				index++;
			
				String deviceIdPartStr = dName.substring(index, dName.length()).trim();
			
				int deviceIdPart = Integer.valueOf(deviceIdPartStr);
			
				System.out.println("Devicceeeeeeeeeeeeeeeeee ID partttttttttttttttttttttt: "+deviceIdPart);
			
			}
			else{
				deviceIdStr = dName.substring(6, dName.length()).trim();
			
				deviceId = Integer.valueOf(deviceIdStr); 
				System.out.println("Devicceeeeeeeeeeeeeeeeee ID : "+deviceId);
			}
		
			device.setDeviceName(deviceIdStr);
		
			if(deviceList == null)
			{
				deviceList = new LinkedList<Device>();
				deviceList.add(device);
			}
			else{
				ListIterator<Device> ir = deviceList.listIterator();
				boolean addflag = false;
				while(ir.hasNext()){
					Device d = ir.next();
				
					if(deviceId <= Integer.valueOf(d.getDeviceName())){
						ir.previous();
						ir.add(device);
						addflag = true;
						break;
					}
				}
			
				if(!addflag){
					deviceList.addLast(device);
				}
			}
		
			ListIterator<Device> ir = deviceList.listIterator();
		
			while(ir.hasNext()){
				System.out.println(ir.next().toString());
			}
		
		}*/
		
		if(deviceTree == null){
			deviceTree = new TreeMap<String,LinkedList<Device>>();
			
			LinkedList<Device> li = new LinkedList<Device>();
			li.add(device);
			
			deviceTree.put(deviceIdStr, li);
		}
		
		else{
			if(deviceTree.containsKey(deviceIdStr)){
				LinkedList<Device> list = deviceTree.get(deviceIdStr);
				
				/*ListIterator<Device> li = list.listIterator();
				
				while(li.hasNext()){
					Device d = li.next();
					
					int result = device.getDeviceName().compareTo(d.getDeviceName());
					
					if(result >= 0){
						li.previous();
						li.add(device);
						break;
					}
				}
				
				if(!li.hasNext()){
					li.add(device);
				}*/
				
				list.add(device);
				
				Collections.sort(list, new DeviceNameComparator());
				
				deviceTree.put(deviceIdStr, list);
			}
			else{
				LinkedList<Device> li = new LinkedList<Device>();
				li.add(device);
				deviceTree.put(deviceIdStr, li);
			}
		}
		
//		System.out.println(deviceTree);
		
		return deviceService.Save(device);
	}
	
	@RequestMapping(value="/listwithname/{name}",method=RequestMethod.GET)
	@ResponseBody
	public List<Device> deviceListusingMap(@PathVariable("name") String name){
		return deviceTree.get(name);
	}
	
	/*@RequestMapping(value="/treemap",method=RequestMethod.GET)
	@ResponseBody
	public Map<String, Set<String>> treemap(){
		
		Set<String> a = new Set<>(Arrays.asList("Actual", "Arrival", "Actuary"));
	    Set<String> b = new Set<>(Arrays.asList("Bump", "Bravo", "Basic"));

	    dictionary.put("B", b);
	    dictionary.put("A", a);
		return dictionary;
	}*/
	
	
	@RequestMapping(value="/partofmap/{from}/{to}",method=RequestMethod.GET)
	@ResponseBody
	public Map<String,LinkedList<Device>> partOfMapUsingName(@PathVariable("from") String fromKey,@PathVariable("to") String toKey){
		Map<String,LinkedList<Device>> subMap = deviceTree.subMap(fromKey,true,toKey,true);
		
		Set<Entry<String,LinkedList<Device>>> entrySet = subMap.entrySet();
		
		Enumeration<Map.Entry<String,LinkedList<Device>>> e =  Collections.enumeration(entrySet);
		
		while(e.hasMoreElements()){
			Entry<String,LinkedList<Device>> entry = e.nextElement();
			
			LinkedList<Device> d=entry.getValue();
			
			Enumeration<Device> en = Collections.enumeration(d);
			
			while(en.hasMoreElements()){
				System.out.println("Key : "+entry.getKey()+", Value Device Name: "+en.nextElement().getDeviceName());
			}
		}
		return subMap;
	}
	
	
	@RequestMapping(value="/list/{n}",method=RequestMethod.GET)
	@ResponseBody
	public List<Integer> list(@PathVariable("n") int n){
		if(list == null)
		{
			list = new LinkedList<Integer>();
			list.add(n);
			return list;
		}
		ListIterator<Integer> ir = list.listIterator();
		boolean addFlag = false;
		while(ir.hasNext()){
			int current = ir.next();
			
			if(n <= current){
				ir.previous();
				ir.add(n);
				addFlag = true;
				break;
			}
		}
		
		if(!addFlag){
			list.addLast(n);
		}
		
		return list;
	}
	
	@RequestMapping(value="/test", method=RequestMethod.GET)
	public String save(){
		
		
		return "Hello";
	}
	
	
	@RequestMapping(value="/findById/{id}", method=RequestMethod.GET)
	@ResponseBody
	public DeviceDto findById(@PathVariable("id") String id){
//		if(deviceService.findById(id) == null) throw new DeviceNotFoundException("No Device with the ID specified");
		
		DeviceDto deviceDto = deviceService.findById(id);
		System.out.println(deviceDto.toString());
		
		return deviceDto;
	}
	
	@RequestMapping(value="/findByName/{name}", method=RequestMethod.GET)
	@ResponseBody
	public List<DeviceDto> findByDeviceName(@PathVariable("name") String name){
		return deviceService.findByDeviceName(name);
	}
	
	@RequestMapping(value="/findFromDate/{date}", method=RequestMethod.GET)
	@ResponseBody
	public List<DeviceDto> findByDate(@PathVariable("date") String dateString) throws Exception{
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss");
		
		Date date= format.parse(dateString);
		
		return deviceService.findByEventDateGreaterThan(date);
	}
	
	/*@RequestMapping(value="/findByTime/{time}", method=RequestMethod.GET)
	@ResponseBody
	public List<Device> findByTime(@PathVariable("time") Time time){
		return deviceService.findByEventTime(time);
	}*/
	
	
}

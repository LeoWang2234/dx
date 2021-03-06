package com.ecust.service.impl;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecust.dao.EquipmentDao;
import com.ecust.pojo.Equipment;
import com.ecust.pojo.Page;
import com.ecust.service.EquipmentService;
import com.ecust.utils.PageUtils;
@Service
public class EquipmentServiceImpl implements EquipmentService {
	@Autowired
	private EquipmentDao equipmentDao;
	
	public Map<String, Object> queryAllEquipment(int pageNumber,int pageSize,String name) {
		
		// 分页，并判断分页参数是否存在
		Page page = new Page();
		if (page.getPageNo() == null && page.getPageSize() == null) {
			page.setPageNo(pageNumber);
			page.setPageSize(pageSize);
		}else{
			page.setPageNo(1);
			page.setPageSize(10);
		}
		PageUtils.page(page);
		List<Map<String, Object>> map = equipmentDao.queryAllEquipment(name);
		Map<String,Object> result = PageUtils.proccess(map);
		return result;	
		
	}

	@Override
	public Map<String,Object> queryEquipmentById(int equipmentId) {
		
		Map<String,Object> map = equipmentDao.queryEquipmentById(equipmentId);
		return map;
	}

	@Override
	public Boolean updateEquipment(Equipment equipment) {
		equipmentDao.updateEquipment(equipment);
		return true;
	}

	@Override
	public Boolean createEquipment(Equipment equipment) {
		
		equipmentDao.createEquipment(equipment);
		return true;
	}

	@Override
	public Boolean deleteEquipment(String equipmentId) {
		equipmentDao.deleteEquipment(equipmentId);
		return true;
	}

	@Override
	public Map<String, Object> queryAllType() {
		List<Map<String, Object>> map = equipmentDao.queryAllType();
		//日期格式转换
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		for(int i = 0;i<map.size();i++){
			Timestamp ctreatTime = (Timestamp) map.get(i).get("createTime");
			String str = df.format(ctreatTime);
			map.get(i).put("createTime", str);
		}
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("data", map);
		return result;
	}

	@Override
	public boolean createType(String createId, String typeName) {
		
		equipmentDao.createType(createId,typeName);
		return true;
	}

	@Override
	public Boolean deleteType(String typeId) {
		equipmentDao.deleteType(typeId);
		return true;
	}

}

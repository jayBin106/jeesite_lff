/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sys.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.sys.entity.LffFacility;
import com.thinkgem.jeesite.modules.sys.dao.LffFacilityDao;

/**
 * lffsbService
 * @author lffsb
 * @version 2019-10-22
 */
@Service
@Transactional(readOnly = true)
public class LffFacilityService extends CrudService<LffFacilityDao, LffFacility> {

	public LffFacility get(String id) {
		return super.get(id);
	}
	
	public List<LffFacility> findList(LffFacility lffFacility) {
		return super.findList(lffFacility);
	}
	
	public Page<LffFacility> findPage(Page<LffFacility> page, LffFacility lffFacility) {
		return super.findPage(page, lffFacility);
	}
	
	@Transactional(readOnly = false)
	public void save(LffFacility lffFacility) {
		super.save(lffFacility);
	}
	
	@Transactional(readOnly = false)
	public void delete(LffFacility lffFacility) {
		super.delete(lffFacility);
	}
	
}
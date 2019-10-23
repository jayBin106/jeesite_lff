/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sys.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.sys.entity.LffCommercial;
import com.thinkgem.jeesite.modules.sys.dao.LffCommercialDao;

/**
 * lffshService
 * @author lffsh
 * @version 2019-10-22
 */
@Service
@Transactional(readOnly = true)
public class LffCommercialService extends CrudService<LffCommercialDao, LffCommercial> {

	public LffCommercial get(String id) {
		return super.get(id);
	}
	
	public List<LffCommercial> findList(LffCommercial lffCommercial) {
		return super.findList(lffCommercial);
	}
	
	public Page<LffCommercial> findPage(Page<LffCommercial> page, LffCommercial lffCommercial) {
		return super.findPage(page, lffCommercial);
	}
	
	@Transactional(readOnly = false)
	public void save(LffCommercial lffCommercial) {
		super.save(lffCommercial);
	}
	
	@Transactional(readOnly = false)
	public void delete(LffCommercial lffCommercial) {
		super.delete(lffCommercial);
	}
	
}
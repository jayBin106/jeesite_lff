/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.lfffc.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.lfffc.entity.LffFC;

/**
 * 单表生成DAO接口
 * @author LiFengFeng
 * @version 2019-10-22
 */
@MyBatisDao
public interface LffFCDao extends CrudDao<LffFC> {
	
}
/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.lfffc.entity;

import javax.validation.constraints.NotNull;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 单表生成Entity
 * @author LiFengFeng
 * @version 2019-10-22
 */
public class LffFC extends DataEntity<LffFC> {
	
	private static final long serialVersionUID = 1L;
	private Integer cId;		// 商户表id
	private Integer fId;		// 设备表id
	
	public LffFC() {
		super();
	}

	public LffFC(String id){
		super(id);
	}

	@NotNull(message="商户表id不能为空")
	public Integer getCId() {
		return cId;
	}

	public void setCId(Integer cId) {
		this.cId = cId;
	}
	
	@NotNull(message="设备表id不能为空")
	public Integer getFId() {
		return fId;
	}

	public void setFId(Integer fId) {
		this.fId = fId;
	}
	
}
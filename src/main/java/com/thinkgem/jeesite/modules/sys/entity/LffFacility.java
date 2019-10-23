/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sys.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import javax.validation.constraints.NotNull;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * lffsbEntity
 * @author lffsb
 * @version 2019-10-22
 */
public class LffFacility extends DataEntity<LffFacility> {
	
	private static final long serialVersionUID = 1L;
	private String serial;		// 序列号
	private String facilityMac;		// 设备mac
	private String name;		// 设备名称
	private String os;		// 操作系统
	private Date pdcDate;		// 出厂时间
	private String mcid;		// 商户id
	private String facilityS;		// 设备组
	private Date beginDate;	// 开始时间
	private Date endDate;	// 结束时间
	
	public Date getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
	public LffFacility() {
		super();
	}

	public LffFacility(String id){
		super(id);
	}

	@Length(min=1, max=20, message="序列号长度必须介于 1 和 20 之间")
	public String getSerial() {
		return serial;
	}

	public void setSerial(String serial) {
		this.serial = serial;
	}
	
	@Length(min=1, max=20, message="设备mac长度必须介于 1 和 20 之间")
	public String getFacilityMac() {
		return facilityMac;
	}

	public void setFacilityMac(String facilityMac) {
		this.facilityMac = facilityMac;
	}
	
	@Length(min=1, max=255, message="设备名称长度必须介于 1 和 255 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=1, max=20, message="操作系统长度必须介于 1 和 20 之间")
	public String getOs() {
		return os;
	}

	public void setOs(String os) {
		this.os = os;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@NotNull(message="出厂时间不能为空")
	public Date getPdcDate() {
		return pdcDate;
	}

	public void setPdcDate(Date pdcDate) {
		this.pdcDate = pdcDate;
	}
	
	@Length(min=0, max=20, message="商户id长度必须介于 0 和 20 之间")
	public String getMcid() {
		return mcid;
	}

	public void setMcid(String mcid) {
		this.mcid = mcid;
	}
	
	@Length(min=0, max=2, message="设备组长度必须介于 0 和 2 之间")
	public String getFacilityS() {
		return facilityS;
	}

	public void setFacilityS(String facilityS) {
		this.facilityS = facilityS;
	}
	
}
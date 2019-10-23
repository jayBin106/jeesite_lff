/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sys.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * lffshEntity
 *
 * @author lffsh
 * @version 2019-10-22
 */
public class LffCommercial extends DataEntity<LffCommercial> {

    private static final long serialVersionUID = 1L;
    private String mcname;        // 商户名称
    private String mctepy;        // 商户类型
    private Area area;        // 所属区域
    private String site;        // 具体地址
    private String contacts;        // 联系人
    private String contactphone;        // 联系人手机
    private String longitudelatitude;        // 经纬度
    private String number;        // 数量
    private String facilityid;        // 设备id
    private String facilitynames;        // 设备名字集

    private String name;        // 设备名字

    @Length(min = 1, max = 20, message = "商户名称长度必须介于 1 和 20 之间")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public LffCommercial() {
        super();
    }

    public LffCommercial(String id) {
        super(id);
    }

    @Length(min = 1, max = 20, message = "商户名称长度必须介于 1 和 20 之间")
    public String getMcname() {
        return mcname;
    }

    public void setMcname(String mcname) {
        this.mcname = mcname;
    }

    @Length(min = 1, max = 20, message = "商户类型长度必须介于 1 和 20 之间")
    public String getMctepy() {
        return mctepy;
    }

    public void setMctepy(String mctepy) {
        this.mctepy = mctepy;
    }

    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
    }

    @Length(min = 1, max = 20, message = "具体地址长度必须介于 1 和 20 之间")
    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    @Length(min = 1, max = 20, message = "联系人长度必须介于 1 和 20 之间")
    public String getContacts() {
        return contacts;
    }

    public void setContacts(String contacts) {
        this.contacts = contacts;
    }

    @Length(min = 1, max = 20, message = "联系人手机长度必须介于 1 和 20 之间")
    public String getContactphone() {
        return contactphone;
    }

    public void setContactphone(String contactphone) {
        this.contactphone = contactphone;
    }

    @Length(min = 1, max = 30, message = "经纬度长度必须介于 1 和 30 之间")
    public String getLongitudelatitude() {
        return longitudelatitude;
    }

    public void setLongitudelatitude(String longitudelatitude) {
        this.longitudelatitude = longitudelatitude;
    }

    @Length(min = 0, max = 255, message = "数量长度必须介于 0 和 255 之间")
    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @Length(min = 1, max = 11, message = "设备id长度必须介于 1 和 11 之间")
    public String getFacilityid() {
        return facilityid;
    }

    public void setFacilityid(String facilityid) {
        this.facilityid = facilityid;
    }

    @Length(min = 1, max = 255, message = "设备名字集长度必须介于 1 和 255 之间")
    public String getFacilitynames() {
        return facilitynames;
    }

    public void setFacilitynames(String facilitynames) {
        this.facilitynames = facilitynames;
    }

}
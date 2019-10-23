/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sys.web;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.thinkgem.jeesite.modules.sys.entity.Area;
import com.thinkgem.jeesite.modules.sys.entity.LffFacility;
import com.thinkgem.jeesite.modules.sys.service.LffFacilityService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.sys.entity.LffCommercial;
import com.thinkgem.jeesite.modules.sys.service.LffCommercialService;

/**
 * lffshController
 *
 * @author lffsh
 * @version 2019-10-22
 */
@Controller
@RequestMapping(value = "${adminPath}/sys/lffCommercial")
public class LffCommercialController extends BaseController {

    @Autowired
    private LffCommercialService lffCommercialService;
    @Autowired
    private LffFacilityService lffFacilityService;

    @ModelAttribute
    public LffCommercial get(@RequestParam(required = false) String id) {
        LffCommercial entity = null;
        if (StringUtils.isNotBlank(id)) {
            entity = lffCommercialService.get(id);
        }
        if (entity == null) {
            entity = new LffCommercial();
        }
        return entity;
    }

    @RequiresPermissions("sys:lffCommercial:view")
    @RequestMapping(value = {"list", ""})
    public String list(LffCommercial lffCommercial, HttpServletRequest request, HttpServletResponse response, Model model) {
        Page<LffCommercial> page = lffCommercialService.findPage(new Page<LffCommercial>(request, response), lffCommercial);
        model.addAttribute("page", page);
        return "modules/sys/lffCommercialList";
    }

    @RequiresPermissions("sys:lffCommercial:view")
    @RequestMapping(value = "form")
    public String form(LffCommercial lffCommercial, Model model) {
        model.addAttribute("lffCommercial", lffCommercial);
        return "modules/sys/lffCommercialForm";
    }

    @RequiresPermissions("sys:lffCommercial:edit")
    @RequestMapping(value = "save")
    public String save(LffCommercial lffCommercial, Model model, RedirectAttributes redirectAttributes) {
        if (!beanValidator(model, lffCommercial)) {
            return form(lffCommercial, model);
        }
        //删除之前选择的
        String nowid = lffCommercial.getId();
        String facilityid = lffCommercial.getFacilityid();
        String[] split = facilityid.split(",");
        lffCommercial.setNumber(split.length + "");
        if (StringUtils.isBlank(nowid)) {
            lffCommercialService.save(lffCommercial);
        } else {
            LffCommercial lffCommercial1 = lffCommercialService.get(nowid);
            lffCommercialService.save(lffCommercial);
            String facilityid2 = lffCommercial1.getFacilityid();
            if (StringUtils.isNotBlank(facilityid2)) {
                String[] split2 = facilityid2.split(",");
                for (int i = 0; i < split2.length; i++) {
                    LffFacility lffFacility = lffFacilityService.get(split2[i]);
                    lffFacility.setMcid("0");
                    lffFacilityService.save(lffFacility);
                }
            }
        }
        //进行设备表关联
        String id = lffCommercial.getId();
        for (int i = 0; i < split.length; i++) {
            LffFacility lffFacility = lffFacilityService.get(split[i]);
            lffFacility.setMcid(id);
            lffFacilityService.save(lffFacility);
        }
        addMessage(redirectAttributes, "保存查询成功");
        return "redirect:" + Global.getAdminPath() + "/sys/lffCommercial/?repage";
    }

    @RequiresPermissions("sys:lffCommercial:edit")
    @RequestMapping(value = "delete")
    public String delete(LffCommercial lffCommercial, RedirectAttributes redirectAttributes) {
        lffCommercialService.delete(lffCommercial);
        addMessage(redirectAttributes, "删除查询成功");
        return "redirect:" + Global.getAdminPath() + "/sys/lffCommercial/?repage";
    }

    /**
     * 获取机构JSON数据。
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "treeData")
    public List<Map<String, Object>> treeData() {
        List<Map<String, Object>> mapList = Lists.newArrayList();
        LffFacility lffFacility = new LffFacility();
        lffFacility.setDelFlag("0");
        lffFacility.setMcid("0");
        List<LffFacility> list = lffFacilityService.findList(lffFacility);
        for (int i = 0; i < list.size(); i++) {
            LffFacility e = list.get(i);
            Map<String, Object> map = Maps.newHashMap();
            map.put("id", e.getId());
            map.put("name", e.getName());
            mapList.add(map);
        }
        return mapList;
    }

    /**
     * 验证商户名是否有效
     *
     * @return
     */
    @ResponseBody
    @RequiresPermissions("sys:lffCommercial:edit")
    @RequestMapping(value = "checkName")
    public String checkLoginName(@RequestParam String oldMcName, @RequestParam String mcname) {
        if (mcname != null && mcname.equals(oldMcName)) {
            return "true";
        } else if (mcname != null) {
            LffCommercial lffCommercial = new LffCommercial();
            lffCommercial.setDelFlag("0");
            lffCommercial.setName(mcname);
            List<LffCommercial> list = lffCommercialService.findList(lffCommercial);
            if (list != null && list.size() == 0) {
                return "true";
            }
        }
        return "false";
    }

}
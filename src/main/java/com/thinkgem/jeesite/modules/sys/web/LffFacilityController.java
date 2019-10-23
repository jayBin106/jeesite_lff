/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sys.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.sys.entity.LffFacility;
import com.thinkgem.jeesite.modules.sys.service.LffFacilityService;

/**
 * lffsbController
 * @author lffsb
 * @version 2019-10-22
 */
@Controller
@RequestMapping(value = "${adminPath}/sys/lffFacility")
public class LffFacilityController extends BaseController {

	@Autowired
	private LffFacilityService lffFacilityService;
	
	@ModelAttribute
	public LffFacility get(@RequestParam(required=false) String id) {
		LffFacility entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = lffFacilityService.get(id);
		}
		if (entity == null){
			entity = new LffFacility();
		}
		return entity;
	}
	
	@RequiresPermissions("sys:lffFacility:view")
	@RequestMapping(value = {"list", ""})
	public String list(LffFacility lffFacility, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<LffFacility> page = lffFacilityService.findPage(new Page<LffFacility>(request, response), lffFacility); 
		model.addAttribute("page", page);
		return "modules/sys/lffFacilityList";
	}

	@RequiresPermissions("sys:lffFacility:view")
	@RequestMapping(value = "form")
	public String form(LffFacility lffFacility, Model model) {
		model.addAttribute("lffFacility", lffFacility);
		return "modules/sys/lffFacilityForm";
	}

	@RequiresPermissions("sys:lffFacility:edit")
	@RequestMapping(value = "save")
	public String save(LffFacility lffFacility, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, lffFacility)){
			return form(lffFacility, model);
		}
		lffFacilityService.save(lffFacility);
		addMessage(redirectAttributes, "保存查询成功");
		return "redirect:"+Global.getAdminPath()+"/sys/lffFacility/?repage";
	}
	
	@RequiresPermissions("sys:lffFacility:edit")
	@RequestMapping(value = "delete")
	public String delete(LffFacility lffFacility, RedirectAttributes redirectAttributes) {
		lffFacilityService.delete(lffFacility);
		addMessage(redirectAttributes, "删除查询成功");
		return "redirect:"+Global.getAdminPath()+"/sys/lffFacility/?repage";
	}

}
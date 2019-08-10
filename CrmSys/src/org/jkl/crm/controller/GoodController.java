package org.jkl.crm.controller;

import java.util.List;

import org.jkl.crm.entity.Good;
import org.jkl.crm.service.CrmService;
import org.jkl.crm.util.tag.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
/**
 * 商品控制处理器
 * @author Louis
 */
@Controller
public class GoodController {
	@Autowired
	private CrmService crmService;
	
	
	@RequestMapping("/selectGoodType")
	public String showGoodType(@ModelAttribute Good good,Integer pageIndex,Model model) {
		System.out.println("type = " +good.getType());//选择类型
		PageModel pageModel = new PageModel();
		if(pageIndex != null) {
			pageModel.setPageIndex(pageIndex);
		}
		/**查询商品信息*/
		List<Good> goods = crmService.findGoods(good, pageModel);
		model.addAttribute("goods", goods);//放入request域中
		model.addAttribute("pageModel", pageModel);
		return "good/selectByType";
	}
	
	
}

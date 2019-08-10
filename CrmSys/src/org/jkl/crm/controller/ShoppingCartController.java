package org.jkl.crm.controller;

import org.jkl.crm.service.CrmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * 购物车处理器
 * @author Louis
 */
@Controller
public class ShoppingCartController {
	@Autowired
	private CrmService crmService;
	
}

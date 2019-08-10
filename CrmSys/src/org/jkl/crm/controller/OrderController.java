package org.jkl.crm.controller;

import org.jkl.crm.service.CrmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * 订单处理器
 * @author Louis
 */
@Controller
public class OrderController {
	@Autowired
	private CrmService crmService;
	
}

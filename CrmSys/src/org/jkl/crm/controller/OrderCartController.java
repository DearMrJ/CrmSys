package org.jkl.crm.controller;

import org.jkl.crm.service.CrmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * 
 * @author Louis
 */
@Controller
public class OrderCartController {
	@Autowired
	private CrmService crmService;
	
}

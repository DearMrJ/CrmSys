package org.jkl.crm.test;


import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.request;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.jkl.crm.controller.UserController;
import org.jkl.crm.entity.User;
import org.jkl.crm.util.common.CrmConstants;
import org.springframework.web.servlet.ModelAndView;

class Test {

	@org.junit.jupiter.api.Test
	void testLogin() {
	}

	@org.junit.jupiter.api.Test
	void testLogout() {
	}

	@org.junit.jupiter.api.Test
	void testReset(HttpSession session) {
		User user = new User("ht","159753");
		session.setAttribute(CrmConstants.USER_SESSION, user);
		ModelAndView mv = new ModelAndView();
		ModelAndView modelAndView = new UserController().reset("15975", "159753",session , mv);
		System.out.println(modelAndView.getViewName());
	}

}

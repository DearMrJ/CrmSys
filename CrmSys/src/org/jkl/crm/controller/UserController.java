package org.jkl.crm.controller;

import static org.jkl.crm.JavaMail.Mail.sendMail;
import static org.jkl.crm.JavaMail.RegistMail.sendRegistMail;

import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.jkl.crm.entity.User;
import org.jkl.crm.service.CrmService;
import org.jkl.crm.util.common.CrmConstants;
import org.jkl.crm.util.encryption.RSAUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


/**
 * 用户请求处理器
 * @author Alan
 */
@RequestMapping("user")
@Controller
public class UserController {
	@Autowired
	private CrmService crmService;
	/**
	 * 用户登录处理
	 * @param name
	 * @param password
	 * @param session
	 * @param mv
	 * @return
	 * @throws NoSuchAlgorithmException 
	 */
	@RequestMapping("login")
	public ModelAndView login(@RequestParam("username") String name,
			@RequestParam("password") String password,HttpSession session, ModelAndView mv) {
		System.out.println("当前sessionid："+session.getId());
		System.out.println("解密前："+password);

		/**********************此处实现前端数据RSA解密**************************/
		try {
			password = RSAUtils.decryptBase64(password);//解密获得密码明文
		} catch (Exception e) {
			System.out.println("解密出错啦！");
		}
		System.out.println("解密后："+password);
		/******************************************************************/
		User user = (User) session.getAttribute(CrmConstants.USER_SESSION);
		if(user==null) {
			System.out.println("首次登录！");
			user = crmService.login(name, password);
		}
		if(user!=null) {//登录成功
			//将用户保存到HttpSession中,key为"USER_SESSION"
			session.setAttribute(CrmConstants.USER_SESSION, user);
			System.out.println("验证通过,返回主界面");
			//跳转至主页面
			mv.setViewName("redirect:/bookmain");
		}else {//登录失败，回显 用户名或密码错误
			//设置登录失败提示信息
			mv.addObject("message","用户名 或 密码错误！请重新输入..");
			System.out.println("验证失败");
			//服务器内部跳转至登录页面
			mv.setViewName("redirect:/loginForm");
		}
		return mv;
	}
	
	/**
	 * 产生公钥public key，前端点击登录button触发此服务，并将数据发送至前端用于加密
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return
	 */
	@RequestMapping("getPublicKey")
	public String getPublicKey(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
		try {
			PrintWriter writer = httpServletResponse.getWriter();//前端getKey.js中Ajax接收
			String publickey = RSAUtils.generateBase64PublicKey();
			writer.write(publickey);
//			System.out.println(publickey);//打印公钥
			return null;
		} catch (Exception e) {
			/**do something**/
			return null;
		}
	}
	
	/**
	 * 用户登出处理
	 * @param session
	 * @param mv
	 * @return
	 */
	@RequestMapping("logout")
	public ModelAndView logout(HttpSession session, ModelAndView mv) {
		if(session.getAttribute(CrmConstants.USER_SESSION)!=null) {
			System.out.println("用户登出");
			session.setAttribute(CrmConstants.USER_SESSION, null);
		}
		mv.setViewName("redirect:/bookmain");
		return mv;
	}
	
	/**
	 * 重置密码，修改通过时向用户预留的邮箱发送一封信件
	 * @param password
	 * @param currentpwd
	 * @param session
	 * @param mv
	 * @return
	 */
	@RequestMapping("reset")
	public ModelAndView reset(@RequestParam("password") String password,
			@RequestParam("currentpwd") String currentpwd,HttpSession session, ModelAndView mv) {
		User user = (User) session.getAttribute(CrmConstants.USER_SESSION);
		System.out.println("======>>> "+user.getName()+" 修改密码！");
		if (currentpwd!=null) 
		if(currentpwd.equals(user.getPassword())) {
			user.setPassword(password);
			crmService.modifyUser(user);
			System.out.println("密码修改成功！");
			mv.addObject("message", "密码修改成功，请重新登录");
			
			/**此处实现javamail，对当前修改密码的用户发送一份邮件**/
			sendMail(user);
			/*****************************************/
		}else {
			System.out.println("原始密码错误");
			mv.addObject("message", "原始密码错误，修改失败");
		}
		mv.setViewName("redirect:/bookmain");
		return mv;
	}
	
	/**
	 * 用户注册处理
	 * @param name
	 * @param password
	 * @param email
	 * @return
	 */
	@RequestMapping("regist")
	public ModelAndView regist(@RequestParam("username") String name, @RequestParam("password") String password,@RequestParam("email") String email) {
		System.out.println("解密前："+password);
		/**********************此处实现前端数据RSA解密**************************/
		try {
			password = RSAUtils.decryptBase64(password);//解密获得密码明文
		} catch (Exception e) {
			System.out.println("解密出错啦！");
		}
		System.out.println("解密后："+password);
		/******************************************************************/
		ModelAndView mv = new ModelAndView();
		if(crmService.isExist(name)) {
			mv.addObject("message", "该用户名已存在！");
			mv.setViewName("forward:/register");
			return mv;
		}
		User user = new User(name, password, email);
		crmService.addUser(user);
		mv.addObject("message", "注册成功！");
		sendRegistMail(user);
		mv.setViewName("redirect:/bookmain");
		return mv;
	}
}

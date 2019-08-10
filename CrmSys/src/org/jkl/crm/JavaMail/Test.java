package org.jkl.crm.JavaMail;

import static org.jkl.crm.JavaMail.Mail.createMimeMessage;
import static org.jkl.crm.JavaMail.Mail.createProperty;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.MimeMessage;

public class Test {
	public static void main(String[] args) {
		try {
			Session ssession = Session.getInstance(createProperty());//
			ssession.setDebug(true);// 开启日志，打印程序详细执行过程
			// 创建邮件
			MimeMessage message = createMimeMessage(ssession, "285692983@qq.com","臭弟弟", "3106900015s@qq.com");
			Transport transport = ssession.getTransport();// 建立连接对象
			/**当QQ被冻结过，或者其他什么异常，要重新获得授权码。 QAQ西湖的水 我的泪***/
			transport.connect("285692983@qq.com", "pgjszvjgktddbgdd");// 建立连接，其中密码以“授权码”的形式体现
			transport.sendMessage(message, message.getAllRecipients());
			transport.close();
		} catch (MessagingException e) {
			System.out.println("┌───────────────────────────────┐");
			System.out.println("│温馨提示:沙雕用户注册的时候把邮件输错了！│");
			System.out.println("└───────────────────────────────┘");
			/***do something***/
		}catch (Exception e) {
			/***do something***/
		}finally {
			/***do something***/
		}
	}
}

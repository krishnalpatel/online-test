package controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import business.Authenticator;
import dao.UserdescDao;
import pojo.Userdesc;

@Controller
@RequestMapping(value = "/lc")
public class LoginController {

	@Autowired
	private UserdescDao userdescDao;

	@Autowired
	private Authenticator authenticator;

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView login(HttpServletRequest request) {

		if (authenticator.authenticate(request.getParameter("uid"), request.getParameter("uname"))) {
			Map<String, Object> model = new HashMap<>();
			model.put("message", "Welcome, " + request.getParameter("uname") + "!");
			model.put("uid", request.getParameter("uid"));
			
			return new ModelAndView("/qc/testsetup","model", model);
		}
		return new ModelAndView("/index.jsp", "message", "Login Failed: No such user!");
	}

	@RequestMapping(value = "register", method = RequestMethod.GET)
	public ModelAndView register(Userdesc userdesc, HttpServletRequest request) {
		userdescDao.create(userdesc);
		return new ModelAndView("/index.jsp", "message", "User Registered!");
	}

	public UserdescDao getUserdescDao() {
		return userdescDao;
	}

	public void setUserdescDao(UserdescDao userdescDao) {
		this.userdescDao = userdescDao;
	}

	public Authenticator getAuthenticator() {
		return authenticator;
	}

	public void setAuthenticator(Authenticator authenticator) {
		this.authenticator = authenticator;
	}

}

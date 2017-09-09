package controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import dao.UserdescDao;
import dao.UsertempDao;
import pojo.Userdesc;
import pojo.Usertemp;

@Controller
@RequestMapping(value = "/rc")
public class ResultController {

	@Autowired
	private UsertempDao usertempDao;
	@Autowired
	private UserdescDao userdescDao;

	@RequestMapping(value = "/genresult")
	public ModelAndView generateResult(HttpServletRequest request) {

		String uid = request.getParameter("uid");
		List<Usertemp> usertempList = usertempDao.list(uid);
		int dc = 0, dcmax = 0, di = 0, dimax = 0, ss = 0, ssmax = 0;
		for (Usertemp usertemp : usertempList) {
			if (usertemp.getCatcode().equalsIgnoreCase("DC")) {
				dcmax+=10;
				if (usertemp.getUans() != null)
					if (usertemp.getRchoice().equalsIgnoreCase(usertemp.getUans()))
						dc += 10;
			} else if (usertemp.getCatcode().equalsIgnoreCase("DI")) {
				dimax+=10;
				if (usertemp.getUans() != null)
					if (usertemp.getRchoice().equalsIgnoreCase(usertemp.getUans()))
						di += 10;
			} else if (usertemp.getCatcode().equalsIgnoreCase("SS")) {
				ssmax+=10;
				if (usertemp.getUans() != null)
					if (usertemp.getRchoice().equalsIgnoreCase(usertemp.getUans()))
						ss += 10;
			}
		}

		// update userdesc table to reflect the total score
		Userdesc userdesc = userdescDao.update(uid, dc + di + ss);
		System.out.println(userdesc);
		// clear the usertemp for the userdesc
		usertempDao.clearAllForUserdesc(userdesc);

		Map<String, Object> model = (Map<String, Object>) request.getAttribute("model");
		model.put("userdesc", userdesc);
		model.put("dc", dc);
		model.put("dcmax", dcmax);
		model.put("di", di);
		model.put("dimax", dimax);
		model.put("ss", ss);
		model.put("ssmax", ssmax);
		model.put("max", dcmax+dimax+ssmax);
		model.put("usertemplist", usertempList);
		return new ModelAndView("/result.jsp", "model", model);
	}

	public UsertempDao getUsertempDao() {
		return usertempDao;
	}

	public void setUsertempDao(UsertempDao usertempDao) {
		this.usertempDao = usertempDao;
	}

	public UserdescDao getUserdescDao() {
		return userdescDao;
	}

	public void setUserdescDao(UserdescDao userdescDao) {
		this.userdescDao = userdescDao;
	}

}

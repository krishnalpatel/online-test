package controller;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import business.QuestionRandomizer;
import dao.CategoryDao;
import dao.QuestionDao;
import dao.UserdescDao;
import dao.UsertempDao;
import pojo.Question;
import pojo.Userdesc;
import pojo.Usertemp;

@Controller
@RequestMapping(value = "/qc")
public class QuestionController {
	@Autowired
	private QuestionDao questionDao;
	@Autowired
	private UsertempDao usertempDao;
	@Autowired
	private UserdescDao userdescDao;
	@Autowired
	private QuestionRandomizer questionRandomizer;

	@RequestMapping(value = "/testsetup")
	public ModelAndView testSetup(HttpServletRequest request) {
		// randomly selecting questions
		List<Question> selectedQuestionsList = questionRandomizer.getQuestions();
		for (Question question : selectedQuestionsList) {
			System.out.println(question);
		}

		// get the uid of the user from the model/map in the request
		Map<String, Object> model = (Map<String, Object>) request.getAttribute("model");
		/*
		 * Iterator<String> i = model.keySet().iterator(); while(i.hasNext()){
		 * System.out.println("Model Value: "+model.get(i.next())); }
		 */
		// creating usertemps and storing them for the particular user
		String uid = (String) model.get("uid");
		Userdesc userdesc = userdescDao.fetchByUid(uid);

		usertempDao.clearAllForUserdesc(userdesc);
		int count = 1;
		for (Question question : selectedQuestionsList) {
			usertempDao.create(new Usertemp(userdesc, count++, question, question.getCategory().getCatcode(),
					question.getRchoice()));

		}

		// setting timer for the test
		model.put("timer", 100);
		// putting boolean testStarted to false
		model.put("testStarted", false);
		// putting question count to 0
		model.put("questionCount", 0);
		return new ModelAndView("/start.jsp", "model", model);
	}

	@RequestMapping(value = "/starttest", method = RequestMethod.GET)
	public ModelAndView startTest(HttpServletRequest request) {

		String uid = request.getParameter("uid");
		int questionCount = Integer.parseInt(request.getParameter("questionCount"));

		Map<String, Object> model = new HashMap<>();
		model.put("uid", uid);
		model.put("message", request.getParameter("message"));
		model.put("testStarted", true);
		model.put("questionCount", questionCount + 1);
		model.put("timer", Integer.parseInt(request.getParameter("timer")));

		// get the first question for the uid and questioncount+1
		Usertemp usertemp = usertempDao.findUsertempByUidAndQno(uid, questionCount + 1);
		// Question question = usertemp.getQuestion();

		// put the question object in the map/model
		model.put("usertemp", usertemp);

		return new ModelAndView("/start.jsp", "model", model);
	}

	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public ModelAndView test(HttpServletRequest request) {

		String uid = request.getParameter("uid");
		int questionCount = Integer.parseInt(request.getParameter("questionCount"));
		String ans = request.getParameter("ans");
		usertempDao.update(uid, questionCount, ans);

		Map<String, Object> model = new HashMap<>();
		model.put("uid", uid);
		model.put("message", request.getParameter("message"));
		model.put("testStarted", true);
		System.out.println("timer: "+ Integer.parseInt(request.getParameter("timer")));
		model.put("timer", Integer.parseInt(request.getParameter("timer")));

		if (request.getParameter("submit").equalsIgnoreCase("Quit")) {
			return new ModelAndView("/rc/genresult","model",model);
		} else {

			switch (request.getParameter("submit")) {

			case "First":
				model.put("questionCount", 1);
				model.put("usertemp", usertempDao.findUsertempByUidAndQno(uid, 1));
				break;

			case "Next":
				model.put("questionCount", questionCount + 1);
				model.put("usertemp", usertempDao.findUsertempByUidAndQno(uid, questionCount + 1));
				break;

			case "Previous":
				model.put("questionCount", questionCount - 1);
				model.put("usertemp", usertempDao.findUsertempByUidAndQno(uid, questionCount - 1));
				break;

			case "Last":
				model.put("questionCount", 10);
				model.put("usertemp", usertempDao.findUsertempByUidAndQno(uid, 10));
				break;

			}

			return new ModelAndView("/start.jsp","model",model);
		}

	}

	public QuestionDao getQuestionDao() {
		return questionDao;
	}

	public void setQuestionDao(QuestionDao questionDao) {
		this.questionDao = questionDao;
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

package business;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import dao.QuestionDao;
import pojo.Question;

@Component(value="questionRandomizer")
public class QuestionRandomizer {
	
	@Autowired
	private QuestionDao questionDao;
	
	public List<Question> getQuestions(){
		Random r = new Random();
		List<Question> list = questionDao.list();
		
		int size = list.size();
		
		ArrayList<Integer> intarray = new ArrayList<>(10);
		System.out.println("Initial size: "+intarray.size());
		
		while(intarray.size()!=10){
			int random = r.nextInt(size);
			if(!intarray.contains(random))
				intarray.add(random);
		}
		System.out.println("intarray: "+ intarray);
		
		List<Question> randomQuestionList = new ArrayList<>();
		for (Integer integer : intarray) {
			randomQuestionList.add(list.get(integer));
		}
		
		return randomQuestionList;
	}

}

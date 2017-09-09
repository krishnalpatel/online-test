package dao;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import pojo.Question;

@Repository(value = "questionDao")
@EnableTransactionManagement()
public class QuestionDao extends Dao {
	
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public List<Question> list(){
		return (List<Question>) template.find("select q from Question q");
	}

}

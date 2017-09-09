package dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import pojo.Userdesc;

@Repository(value = "userdescDao")
@EnableTransactionManagement()
public class UserdescDao extends Dao {

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void create(Userdesc userdesc) {
		try {
			template.save(userdesc);
		} catch (Exception e) {
			System.out.println("User could not be registered");
			e.printStackTrace();
		}
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public Userdesc fetchByUid(String uid) {
		return template.get(Userdesc.class, uid);
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public Userdesc update(String uid, int score) {
		Userdesc userdesc = fetchByUid(uid);
		userdesc.setScore(score);
		template.update(userdesc);
		System.out.println("Updated userdesc: "+userdesc);
		return userdesc;
	}

}

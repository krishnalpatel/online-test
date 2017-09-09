package dao;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import pojo.Userdesc;
import pojo.Usertemp;

@Repository(value = "usertempDao")
@EnableTransactionManagement()
public class UsertempDao extends Dao {

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void create(Usertemp usertemp) {
		try {
			template.save(usertemp);
		} catch (Exception e) {
			System.out.println("Error inserting Usertemp: " + usertemp);
			e.printStackTrace();
		}
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void clearAllForUserdesc(Userdesc userdesc) {
		System.out.println("Clearing for Userdesc: " + userdesc);

		List<Usertemp> usertempList = (List<Usertemp>) template
				.find("select u from Usertemp u where userdesc_uid ='" + userdesc.getUid() + "'");

		System.out.println("size of existing usertemp list: " + usertempList.size());

		for (Usertemp usertemp : usertempList) {
			template.delete(usertemp);
		}
		System.out.println("Usertemp Cleared!");
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public Usertemp findUsertempByUidAndQno(String uid, int qno) {
		return (Usertemp) template.find("select u from Usertemp u where userdesc_uid ='" + uid + "' and qno=" + qno)
				.get(0);
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void update(String uid, int qno, String uans) {
		Usertemp usertemp = findUsertempByUidAndQno(uid, qno);
		usertemp.setUans(uans);
		template.update(usertemp);
	}

	public List<Usertemp> list(String uid) {
		return (List<Usertemp>) template.find("select u from Usertemp u where userdesc_uid='" + uid + "'");
	}

}

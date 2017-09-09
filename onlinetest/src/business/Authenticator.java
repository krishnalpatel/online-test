package business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import dao.UserdescDao;
import pojo.Userdesc;

@Component(value="authenticator")
public class Authenticator {
	
	@Autowired
	private UserdescDao userdescDao;

	
	public boolean authenticate(String uid, String uname){
		
		try{
			Userdesc userdesc = userdescDao.fetchByUid(uid);
			if(userdesc.getUname().equalsIgnoreCase(uname))
				return true;
			return false;
		}
		catch(Exception e){
			System.out.println("No such user exists with id: "+uid);
			e.printStackTrace();
			return false;
		}
		
	}


	public UserdescDao getUserdescDao() {
		return userdescDao;
	}


	public void setUserdescDao(UserdescDao userdescDao) {
		this.userdescDao = userdescDao;
	}

	
}

package pojo;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Userdesc {

	@Id
	@Column(length=8)
	String uid;
	@Column(length=20)
	String uname;
	@Column(nullable=true)
	int score;
	
	@OneToMany(mappedBy="userdesc")
	private Set<Usertemp> utemps = new HashSet<>();

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	@Override
	public String toString() {
		return "Userdesc [uid=" + uid + ", uname=" + uname + ", score=" + score + "]";
	}

}

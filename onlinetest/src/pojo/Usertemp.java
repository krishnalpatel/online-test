package pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Usertemp {

	@Id
	@GeneratedValue
	int id;
	@ManyToOne
	Userdesc userdesc;
	int qno;
	@ManyToOne
	Question question;
	@Column(length = 2)
	String catcode;
	@Column(length = 1)
	String rchoice;
	@Column(length = 1, nullable = true)
	String uans;

	public Usertemp() {
	}

	public Usertemp(Userdesc userdesc, int qno, Question question, String catcode, String rchoice) {
		this.userdesc = userdesc;
		this.qno = qno;
		this.question = question;
		this.catcode = catcode;
		this.rchoice = rchoice;
	}

	public Userdesc getUserdesc() {
		return userdesc;
	}

	public void setUserdesc(Userdesc userdesc) {
		this.userdesc = userdesc;
	}

	public int getQno() {
		return qno;
	}

	public void setQno(int qno) {
		this.qno = qno;
	}

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	public String getCatcode() {
		return catcode;
	}

	public void setCatcode(String catcode) {
		this.catcode = catcode;
	}

	public String getRchoice() {
		return rchoice;
	}

	public void setRchoice(String rchoice) {
		this.rchoice = rchoice;
	}

	public String getUans() {
		return uans;
	}

	public void setUans(String uans) {
		this.uans = uans;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Usertemp [uid=" + userdesc.uid + ", qno=" + qno + ", rno=" + question.qno + ", catcode=" + catcode
				+ ", rchoice=" + rchoice + ", uans=" + uans + "]";
	}

}

package pojo;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Question {

	@Id
	int qno;
	String ques;
	String a;
	String b;
	String c;
	String d;
	@Column(length = 1)
	String rchoice;
	@ManyToOne
	Category category = new Category();
	
	@OneToMany(mappedBy="question")
	private Set<Usertemp> utemps = new HashSet<>();

	public int getQno() {
		return qno;
	}

	public void setQno(int qno) {
		this.qno = qno;
	}

	public String getQues() {
		return ques;
	}

	public void setQues(String ques) {
		this.ques = ques;
	}

	public String getA() {
		return a;
	}

	public void setA(String a) {
		this.a = a;
	}

	public String getB() {
		return b;
	}

	public void setB(String b) {
		this.b = b;
	}

	public String getC() {
		return c;
	}

	public void setC(String c) {
		this.c = c;
	}

	public String getD() {
		return d;
	}

	public void setD(String d) {
		this.d = d;
	}

	public String getRchoice() {
		return rchoice;
	}

	public void setRchoice(String rchoice) {
		this.rchoice = rchoice;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	@Override
	public String toString() {
		return "Question [qno=" + qno + ", ques=" + ques + ", a=" + a + ", b=" + b + ", c=" + c + ", d=" + d
				+ ", rchoice=" + rchoice + ", catcode=" + category.catcode + "]";
	}

}

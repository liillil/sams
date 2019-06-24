package cn.java.sams.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="COURSES")
public class Courses {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	/**
	 * 课程编号
	 */
	@Column(name="COURSES_NUM",length=20)
	private String coursesNum;
	/**
	 * 课程名
	 */
	@Column(name="COURSES_NAME",length=40)
	private String coursesName;
	/**
	 * 学分
	 */
	@Column(name="CREDIT",length=20)
	private Integer credit;
	/**
	 * 学时
	 */
	@Column(name="PERIOD",length=20)
	private Integer period;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCoursesName() {
		return coursesName;
	}
	public void setCoursesName(String coursesName) {
		this.coursesName = coursesName;
	}
	public Integer getCredit() {
		return credit;
	}
	public void setCredit(Integer credit) {
		this.credit = credit;
	}
	public Integer getPeriod() {
		return period;
	}
	public void setPeriod(Integer period) {
		this.period = period;
	}
	
	public String getCoursesNum() {
		return coursesNum;
	}
	public void setCoursesNum(String coursesNum) {
		this.coursesNum = coursesNum;
	}
	public Courses() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}

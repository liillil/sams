package cn.java.sams.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="MAJOR")
public class Major {
	private int id;
	/**
	 * 专业编号
	 */
	private String majorNum;
	/**
	 * 专业名称
	 */
	private String majorName;
	/**
	 * 所属院系
	 */
	private Department department;
	/**
	 * 	专业所需学习的课程列表
	 */
	private List<Courses> majorCourses;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@Column(name="MAJOR_NUM",length=40)
	public String getMajorNum() {
		return majorNum;
	}
	public void setMajorNum(String majorNum) {
		this.majorNum = majorNum;
	}
	@Column(name="MAJOR_NAME",length=40)
	public String getMajorName() {
		return majorName;
	}
	public void setMajorName(String majorName) {
		this.majorName = majorName;
	}
	@ManyToOne
	@JoinColumn(name="DEPARTMENT_ID")
	public Department getDepartment() {
		return department;
	}
	@ManyToMany
	@JoinTable(name="MAJOR_COURSES",joinColumns=@JoinColumn(name="MAJOR_ID",referencedColumnName="id"),inverseJoinColumns=@JoinColumn(name="COURSES_ID",referencedColumnName="id"))
	public List<Courses> getMajorCourses() {
		return majorCourses;
	}
	public void setMajorCourses(List<Courses> majorCourses) {
		this.majorCourses = majorCourses;
	}
	public void setDepartment(Department department) {
		this.department = department;
	}
	public Major() {
		super();
	}
	@Override
	public String toString() {
		return "Major [id=" + id + ", majorNum=" + majorNum + ", majorName=" + majorName + ", department=" + department
				+ "]";
	}
	
	
	
}

package cn.java.sams.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="s_student_courses")
public class Student_StudentCourses {
	
 @Id
 @GeneratedValue(strategy=GenerationType.IDENTITY)
 private int id;
 
 @ManyToOne(fetch=FetchType.EAGER)
 @JoinColumn(name="STUDENT_COURSES_ID")
 private StudentCourses sCourses;
 
 @ManyToOne(fetch=FetchType.EAGER)
 @JoinColumn(name="STUDENT_ID")
 private Student student;
 
 @Column(name="SCORE",length=20)
 private Integer score;
 
 /*
  * 以下备用字段
  */
 @Column(name="INFO1",length=20)
 private Integer info1;
 @Column(name="INFO2",length=20)
 private Integer info2; 
 @Column(name="INFO3",length=20)
 private Integer info3; 
 @Column(name="STR1",length=50)
 private String str1;
 @Column(name="STR2",length=50)
 private String str2;
 @Column(name="STR3",length=50)
 private String str3;
 
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public StudentCourses getsCourses() {
		return sCourses;
	}
	public void setsCourses(StudentCourses sCourses) {
		this.sCourses = sCourses;
	}
	public Student getStudent() {
		return student;
	}
	public void setStudent(Student student) {
		this.student = student;
	}
	public Integer getScore() {
		return score;
	}
	public void setScore(Integer score) {
		this.score = score;
	}
	
	public Integer getInfo1() {
		return info1;
	}
	public void setInfo1(Integer info1) {
		this.info1 = info1;
	}
	public Integer getInfo2() {
		return info2;
	}
	public void setInfo2(Integer info2) {
		this.info2 = info2;
	}
	public Integer getInfo3() {
		return info3;
	}
	public void setInfo3(Integer info3) {
		this.info3 = info3;
	}
	public String getStr1() {
		return str1;
	}
	public void setStr1(String str1) {
		this.str1 = str1;
	}
	public String getStr2() {
		return str2;
	}
	public void setStr2(String str2) {
		this.str2 = str2;
	}
	public String getStr3() {
		return str3;
	}
	public void setStr3(String str3) {
		this.str3 = str3;
	}
	@Override
	public String toString() {
		return "Student_StudentCourses [id=" + id + "]";
	}
 
 
}

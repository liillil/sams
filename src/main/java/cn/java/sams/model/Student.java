package cn.java.sams.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
@Entity
@Table(name="STUDENT")
public class Student {
	/**
	 * id
	 */
	private int id;
	/**
	 * 学号
	 */
	private String studentNum;
	/**
	 * 学生姓名
	 */
	private String studentName;
	/**
	 * 
	 */
	private String password;
	/**
	 * 入学日期
	 */
	@JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date enrollmentDate;
	/**
	 * 性别 0.男 1.女
	 */
	private Integer sex;
	/**
	 * 班级编号
	 */
	private String classNum;
	/**
	 *  专业
	 */
	private Major major;
	/**
	 * 状态 0.禁用 1.正常
	 */
	private Integer status;
	/**
	 * 专业ID
	 */
	//private List<Student_StudentCourses> ssCourses;
	
	private Integer MajorId;
	
	private String  startDate;
	
	private String endDate;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@Column(name="STUDENT_NUM",length=50)
	public String getStudentNum() {
		return studentNum;
	}
	public void setStudentNum(String studentNum) {
		this.studentNum = studentNum;
	}
	@Column(name="STUDENT_NAME",length=40)
	public String getStudentName() {
		return studentName;
	}
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	@Column(name="PASSWORD",length=100)
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Temporal(TemporalType.DATE)
	@Column(name="ENROLLMENT_DATE")
	public Date getEnrollmentDate() {
		return enrollmentDate;
	}
	public void setEnrollmentDate(Date enrollmentDate) {
		this.enrollmentDate = enrollmentDate;
	}
	@Column(name="SEX",length=20)
	public Integer getSex() {
		return sex;
	}
	public void setSex(Integer sex) {
		this.sex = sex;
	}
	@Column(name="CLASS_NUM",length=40)
	public String getClassNum() {
		return classNum;
	}
	public void setClassNum(String classNum) {
		this.classNum = classNum;
	}
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="MAJOR_ID")
	public Major getMajor() {
		return major;
	}
	public void setMajor(Major major) {
		this.major = major;
	}
//	@OneToMany(mappedBy="student",cascade=CascadeType.REMOVE,fetch=FetchType.EAGER)
//	public List<Student_StudentCourses> getSsCourses() {
//		return ssCourses;
//	}
//	public void setSsCourses(List<Student_StudentCourses> ssCourses) {
//		this.ssCourses = ssCourses;
//	}	
	@Column(name="STATUS",length=20)
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	@Transient
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	@Transient
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}	
	@Transient
	public Integer getMajorId() {
		return MajorId;
	}
	public void setMajorId(Integer majorId) {
		MajorId = majorId;
	}
	public Student() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Student [id=" + id + ", studentNum=" + studentNum + ", studentName=" + studentName + ", password="
				+ password + ", enrollmentDate=" + enrollmentDate + ", sex=" + sex + ", classNum=" + classNum
				+ ", major=" + major + ", status=" + status + ", MajorId=" + MajorId + ", startDate=" + startDate
				+ ", endDate=" + endDate + "]";
	}
	
	
}

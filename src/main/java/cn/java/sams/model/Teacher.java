package cn.java.sams.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;


@Entity
@Table(name="TEACHER")
public class Teacher {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	/**
	 * 姓名
	 */
	@Column(name="TEACHER_NAME",length=40)
	private String teacherName;
	/**
	 * 编号
	 */
	@Column(name="TEACHER_NUM",length=50)
	private String teacherNum;
	/**
	 * 密码
	 */
	@Column(name="PASSWORD",length=100)
	private String password;
	/**
	 * 性别 0.男 1.女
	 */
	@Column(name="SEX",length=20)
	private Integer sex;
	/**
	 *  入职日期
	 */
	@JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	@Column(name="DATE_OF_ENROLLMENT")
	private Date dateOfEnrollment;
	/**
	 * 状态 0.禁用 1.正常
	 */
	@Column(name="STATUS",length=20)
	private Integer status;
	
	/**
	 *  角色列表
	 */
	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(name = "TEACHER_ROLE",joinColumns = @JoinColumn(name="TEACHER_ID",referencedColumnName = "id"),inverseJoinColumns = @JoinColumn(name = "ROLE_ID",referencedColumnName = "id"))
	private List<Role> roles = new ArrayList<>();
	
	@Transient
	private String startDate;
	
	@Transient
	private String endDate;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTeacherName() {
		return teacherName;
	}
	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}
	public String getTeacherNum() {
		return teacherNum;
	}
	public void setTeacherNum(String teacherNum) {
		this.teacherNum = teacherNum;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Integer getSex() {
		return sex;
	}
	public void setSex(Integer sex) {
		this.sex = sex;
	}
	public Date getDateOfEnrollment() {
		return dateOfEnrollment;
	}
	public void setDateOfEnrollment(Date dateOfEnrollment) {
		this.dateOfEnrollment = dateOfEnrollment;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	public List<Role> getRoles() {
		return roles;
	}
	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public Teacher() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
}

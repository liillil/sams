package cn.java.sams.model;



public class StudentScore {
	/**
	 *  课程名
	 */
	private String coursesName;
	/**
	 *  学生名称
	 */
	private String studentName;
	/**
	 * 班级编号
	 */
	private String classNum;
	/**
	 * 学号
	 */
	private String studentNum;
	/**
	 *  专业id
	 */
	private Integer majorId;
	/**
	 *  成绩
	 */
	private Integer score;
	/**
	 * 专业
	 */
	private Major major;
	/**
	 * 开课日期
	 */
	private String sDate;
	private String eDate;
	/**
	 * 备用字段
	 */
	private Integer info1;
	private Integer info2;
	private Integer info3;
	
	
	public String getCoursesName() {
		return coursesName;
	}
	public void setCoursesName(String coursesName) {
		this.coursesName = coursesName;
	}
	public String getClassNum() {
		return classNum;
	}
	public void setClassNum(String classNum) {
		this.classNum = classNum;
	}
	public Integer getMajorId() {
		return majorId;
	}
	public void setMajorId(Integer majorId) {
		this.majorId = majorId;
	}
	public String getStudentName() {
		return studentName;
	}
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	public Integer getScore() {
		return score;
	}
	public void setScore(Integer score) {
		this.score = score;
	}
	public String getStudentNum() {
		return studentNum;
	}
	public void setStudentNum(String studentNum) {
		this.studentNum = studentNum;
	}
	public Major getMajor() {
		return major;
	}
	public void setMajor(Major major) {
		this.major = major;
	}
	public String getsDate() {
		return sDate;
	}
	public void setsDate(String sDate) {
		this.sDate = sDate;
	}
	public String geteDate() {
		return eDate;
	}
	public void seteDate(String eDate) {
		this.eDate = eDate;
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
	@Override
	public String toString() {
		return "StudentScore [coursesName=" + coursesName + ", studentName=" + studentName + ", classNum=" + classNum
				+ ", studentNum=" + studentNum + ", majorId=" + majorId + ", score=" + score + ", info1=" + info1
				+ ", info2=" + info2 + ", info3=" + info3 + "]";
	}
	
	
	
}

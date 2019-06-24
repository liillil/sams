package cn.java.sams.model;

public class TeacherCourses {
	/**
	 * 开课日期
	 */
	private String sDate;
	/**
	 * 专业编号
	 */
	private Integer MajorId;
	/**
	 * 课程名称
	 */
	private String coursesName;
	/**
	 * 班级编号
	 */
	private String classNum;
	/**
	 * 教师编号
	 */
	private String teacherNum;
	/**
	 * 教师名称
	 */
	private String teacherName;
	/**
	 * 专业
	 */
	private Major major;

	public String getsDate() {
		return sDate;
	}

	public void setsDate(String sDate) {
		this.sDate = sDate;
	}

	public Integer getMajorId() {
		return MajorId;
	}

	public void setMajorId(Integer majorId) {
		MajorId = majorId;
	}

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

	public String getTeacherNum() {
		return teacherNum;
	}

	public void setTeacherNum(String teacherNum) {
		this.teacherNum = teacherNum;
	}

	public String getTeacherName() {
		return teacherName;
	}

	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}	
	public Major getMajor() {
		return major;
	}

	public void setMajor(Major major) {
		this.major = major;
	}

	@Override
	public String toString() {
		return "TeacherCourses [sDate=" + sDate + ", MajorId=" + MajorId + ", coursesName=" + coursesName
				+ ", classNum=" + classNum + ", teacherNum=" + teacherNum + ", teacherName=" + teacherName + "]";
	}
	
	
}

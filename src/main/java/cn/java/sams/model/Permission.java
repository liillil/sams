package cn.java.sams.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 权限
 * @author Administrator
 *
 */
@Entity
@Table(name="permission")
public class Permission {
	private int id;
	
	private String resource;
	
	private Integer status;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	@Column(name="resource",length=100)
	public String getResource() {
		return resource;
	}

	public void setResource(String resource) {
		this.resource = resource;
	}
	@Column(name="status",length=10)
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Permission() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Permission [id=" + id + ", resource=" + resource + ", status=" + status + "]";
	}
	
	
}

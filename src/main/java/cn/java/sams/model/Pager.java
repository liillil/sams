package cn.java.sams.model;

import java.util.List;

public class Pager<T> {
	/**
	 * 每页显示数据的条数
	 */
	private int size;
	/**
	 * 每页的第一条数据的条数索引
	 */
	private int offset;
	/**
	 * 查询出来的总数据条数，total--easyui分页插件里的变量名一致
	 */
	private int total;
	/**
	 * 具体数据，rows--easyui分页插件里的变量名一致
	 */
	private List<T> rows;
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public int getOffset() {
		return offset;
	}
	public void setOffset(int offset) {
		this.offset = offset;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public List<T> getRows() {
		return rows;
	}
	public void setRows(List<T> rows) {
		this.rows = rows;
	}
	
	
}

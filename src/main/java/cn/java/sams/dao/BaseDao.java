package cn.java.sams.dao;

public interface BaseDao<T> {
	/**
	 * 查找
	 * @param id
	 */
	public T find(int id);
	/**
	 * 添加
	 * @param t
	 */
	public void add(T t);
	/**
	 * 删除
	 * @param id
	 */
	public void delete(int id);
	/**
	 * 修改
	 * @param t
	 */
	public void merge(T t);
}

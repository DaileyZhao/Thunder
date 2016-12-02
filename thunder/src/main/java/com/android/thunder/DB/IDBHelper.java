package com.android.thunder.DB;

import android.util.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 
 * @author yezi
 * 
 * @param <T>
 */
public interface IDBHelper<T> {
	/**
	 * 插入数据
	 * 
	 * @param data
	 * @return
	 */
	boolean insert(List<T> data);

	/**
	 * 更新数据
	 * 
	 * @param row
	 * @param where
	 * @return
	 */
	boolean update(HashMap<String, String> row,
				   Pair<String, ArrayList<String>> where);

	/**
	 * 删除数据
	 * 
	 * @param where
	 * @return
	 */
	boolean delete(Pair<String, ArrayList<String>> where);

	/**
	 * 查找
	 * 
	 * @param where
	 * @return
	 */
	List<T> findAll(Pair<String, ArrayList<String>> where);

	/**
	 * 查找
	 * 
	 * @param where
	 * @param limit
	 * @return
	 */
	List<T> findAll(Pair<String, ArrayList<String>> where, int limit);

	/**
	 * 查找
	 * 
	 * @param where
	 * @param order
	 * @param limit
	 * @return
	 */
	List<T> findAll(Pair<String, ArrayList<String>> where, String order,
					int limit);

	/**
	 * 查找
	 * 
	 * @param where
	 * @return
	 */
	T findOne(Pair<String, ArrayList<String>> where);

	/**
	 * 查找
	 * 
	 * @param where
	 * @param order
	 * @return
	 */
	T findOne(Pair<String, ArrayList<String>> where, String order);

	/**
	 * 解析数据
	 * 
	 * @param maps
	 * @return
	 */
	List<T> parseData(ArrayList<HashMap<String, String>> maps);
}

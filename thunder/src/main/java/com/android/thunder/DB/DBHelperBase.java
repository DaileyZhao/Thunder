package com.android.thunder.DB;

import android.util.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


/**
 * 数据库操作base
 * 
 * @author yezi
 * @see <p>
 *      sql =
 *      "dateline >= ? and dateline = ? and data != '0' and event in (?, ?)";
 *      <p>
 *      Where where = new Where(sql, new ArrayList<String>());
 *      <p>
 *      where.second.add("20160101");
 *      <p>
 *      where.second.add("20160808");
 *      <p>
 *      where.second.add("1");
 *      <p>
 *      where.second.add("3");
 *      <p>
 *      findAll(where,"dateline asc", 10000);
 * 
 * 
 * @param <T>
 *            需要查询的数据库的实体类
 */
public abstract class DBHelperBase<T extends IDBMode> implements IDBHelper<T> {

	/**
	 * 获取一个实体对象
	 * 
	 * @return
	 */
	public abstract T getMode();

	/**
	 * 操作的数据库的表名
	 * 
	 * @return
	 */
	abstract String getTableName();

	@Override
	public boolean insert(List<T> data) {
		if (data != null) {
			DB db = null;
			synchronized (DB.lock_db) {
				try {
					db = new DB(getTableName());
					db.beginTransaction();
					for (T t : data) {
						db.insert(t.getHashMap(), true);
					}
					db.setTransactionSuccessful();
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					if (db != null) {
						db.endTransaction();
						db.close();
					}
				}

			}
		}
		return true;
	}

	@Override
	public boolean update(HashMap<String, String> row,
			Pair<String, ArrayList<String>> where) {
		DB db = null;
		synchronized (DB.lock_db) {
			try {
				db = new DB(getTableName());
				db.beginTransaction();
				db.update(row, where);
				db.setTransactionSuccessful();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (db != null) {
					db.endTransaction();
					db.close();
				}
			}

		}
		return true;
	}

	@Override
	public boolean delete(Pair<String, ArrayList<String>> where) {
		DB db = null;
		boolean isDelete = false;
		synchronized (DB.lock_db) {
			try {
				db = new DB(getTableName());
				db.beginTransaction();
				int result = db.delete(where);
				if (result > 0) {
					isDelete = true;
				}
				db.setTransactionSuccessful();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (db != null) {
					db.endTransaction();
					db.close();
				}
			}

		}
		return isDelete;
	}

	@Override
	public List<T> findAll(Pair<String, ArrayList<String>> where) {
		return findAll(where, null, 10000);
	}

	@Override
	public List<T> findAll(Pair<String, ArrayList<String>> where, int limit) {
		return findAll(where, null, limit);
	}

	@Override
	public List<T> findAll(Pair<String, ArrayList<String>> where, String order,
			int limit) {
		ArrayList<T> list = new ArrayList<T>();
		DB db = null;
		synchronized (DB.lock_db) {
			try {
				db = new DB(getTableName());
				ArrayList<HashMap<String, String>> maps = db.findAll(where,
						order, limit);
				return parseData(maps);
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (db != null) {
					db.close();
				}
			}
		}
		return list;
	}

	@Override
	public T findOne(Pair<String, ArrayList<String>> where) {
		return findOne(where, null);
	}

	@Override
	public T findOne(Pair<String, ArrayList<String>> where, String order) {
		DB db = null;
		synchronized (DB.lock_db) {
			try {
				db = new DB(getTableName());
				HashMap<String, String> map = db.findOne(where, order);
				if (map != null) {
					T t = getMode();
					t.paseData(map);
					return t;
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (db != null) {
					db.close();
				}
			}
		}
		return null;
	}

	@Override
	public List<T> parseData(ArrayList<HashMap<String, String>> maps) {
		List<T> list = new ArrayList<T>();
		if (maps != null) {
			for (HashMap<String, String> hashMap : maps) {
				T t = getMode();
				t.paseData(hashMap);
				list.add(t);
			}
		}
		return list;
	}

	/**
	 * 获取本地最大时间戳
	 * 
	 * @return
	 */
	public long getLatestUpdateTime(String uid) {
		String sql = " updatetime >0 and uid = " + uid;
		Where where = new Where(sql, new ArrayList<String>());
		T bean = findOne(where, "updatetime desc");
		if (bean != null) {
			return bean.getUpdateTime();
		}
		return 0;
	}

	/**
	 * 获取所有待上传数据
	 * 
	 * @return
	 */
	public List<T> getUploadBeans(String uid) {
		String sql = " updatetime = 0 and uid = " + uid;
		Where where = new Where(sql, new ArrayList<String>());
		return findAll(where);
	}
}

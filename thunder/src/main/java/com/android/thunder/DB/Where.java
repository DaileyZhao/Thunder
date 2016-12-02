package com.android.thunder.DB;

import android.util.Pair;

import java.util.ArrayList;

/**
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
 */
public class Where extends Pair<String, ArrayList<String>> {

	public Where(String first, ArrayList<String> second) {
		super(first, second);
	}
}

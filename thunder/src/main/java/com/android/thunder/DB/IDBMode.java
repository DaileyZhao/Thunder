package com.android.thunder.DB;

import org.json.JSONObject;

import java.util.HashMap;

/**
 * @author yezi
 * 
 */
public interface IDBMode {

	IDBMode paseData(HashMap<String, String> map);

	HashMap<String, String> getHashMap();

	JSONObject toJson();

	IDBMode fromJson(JSONObject json);
	
	long getUpdateTime();
}

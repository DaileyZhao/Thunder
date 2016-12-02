package com.android.thunder.DB;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.android.thunder.ThunderApplication;


/**
 * xinwo database demo
 * 
 * @author yezi
 * 
 */
public class DB extends BaseDB {
	/**
	 * 数据库同步锁
	 */
	public static final Byte[] lock_db = new Byte[0];
	/**
	 * 数据库名称
	 */
	private static final String DB_NAME = "xinwo.db";

	public DB(String tableName) {
		super(tableName);
	}

	public DB(String tableName, boolean isWriteble) {
		super(tableName, isWriteble);
	}

	@Override
	public int getDBMinVersion() {
		return 0;
	}

	@Override
	public int getDBVersion() {
		return 1;
	}

	@Override
	public String getDBName() {
		return DB_NAME;
	}

	@Override
	protected Context getContext() {
		return ThunderApplication.getContext();
	}

	@Override
	protected Byte[] getDBLock() {
		return lock_db;
	}

	@Override
	protected void onDBCreate(SQLiteDatabase db) {
		try {
			db.beginTransaction();
			String sql = null;
			// 用户数据表
			sql = "create table user_" + getDBVersion() + "(";
			sql += "uid integer primary key not null default '0',";// 服务器生成uid
			sql += "createtime integer not null default '0',";// 本地时间
			sql += "updatetime integer not null default '0',";// 更新时间，只要有修改updatetime为0，上传后同步服务器时间
			sql += "avatar varchr(200)  not null default '',";// 头像
			sql += "gender integer not null default '0',";// 性别
			sql += "height integer not null default '0',";// 身高
			sql += "weight varchr(100) not null default '',";// 体重
			sql += "birthday integer not null default '0',";// 生日
			sql += "state integer not null default '0',";// 状态
			sql += "illness varchr(2000)  not null default '',";// 健康状态
			sql += "period integer not null default '0',";// 经期天数
			sql += "cycle integer not null default '0',";// 周期天数
			sql += "pregday integer not null default '0',";// 预产期
			sql += "phone varchr(100) not null default '',";// 手机号
			sql += "linkid integer not null default '0',";// 第三方帐号id
			sql += "linktype integer not null default '0',";//
			sql += "linktoken varchr(100) not null default '',";// 链接大姨妈token
			sql += "accounttoken varchr(100) not null default '',";// 所属帐号的token
			sql += "extra varchr(2000)  not null default '',";// 新增信息
			sql += "nick varchr(100) not null default ''";// 昵称
			sql += ")";
			db.execSQL(sql);
			// 日历数据表
			sql = "create table calendar_" + getDBVersion() + "(";
			sql += "uid integer not null default '0',";// uid
			sql += "event integer not null default '0', ";// 事件类型（eg：经期开始，经期结束，同房...）
			sql += "data varchar(2000) not null default '', ";// 记录数据
			sql += "dateline integer not null default '0', ";// 时间戳（20160101）
			sql += "updatetime integer not null default '0' ,";// 更新时间
			sql += "createtime integer not null default '0',";// 创建时间
			sql += "primary key (uid,dateline, event)";// 联合主键
			sql += ")";
			db.execSQL(sql);
			// 尿常规测试结果
			sql = "create table ncg_test_" + getDBVersion() + "(";
			sql += "uid integer not null default '0',";// uid
			sql += "updatetime integer not null default '0' ,";// 更新时间
			sql += "createtime integer not null default '0',";// 创建时间
			sql += "sg integer not null default '0',";// 尿比重
			sql += "bld integer not null default '0',";// 隐血/潜血
			sql += "leu integer not null default '0',";// 白细胞
			sql += "ph integer not null default '0',";// 酸碱度
			sql += "bil integer not null default '0',";// 胆红素
			sql += "glu integer not null default '0',";// 葡萄糖
			sql += "ket integer not null default '0',";// 酮体
			sql += "pro integer not null default '0',";// 蛋白质
			sql += "ubg integer not null default '0',";// 尿胆原
			sql += "nit integer not null default '0',";// 亚硝酸盐
			sql += "vc integer not null default '0',";// 维生素C
			sql += "primary key (uid,createtime)";// 联合主键
			sql += ")";
			db.execSQL(sql);
			// FSH测试结果
			sql = "create table fsh_test_" + getDBVersion() + "(";
			sql += "uid integer not null default '0',";// uid
			sql += "updatetime integer not null default '0' ,";// 更新时间
			sql += "createtime integer not null default '0',";// 创建时间
			sql += "level integer not null default '0',";// 卵巢结果
			sql += "primary key (uid,createtime)";// 联合主键
			sql += ")";
			db.execSQL(sql);
			// LH测试结果
			sql = "create table lh_test_" + getDBVersion() + "(";
			sql += "uid integer not null default '0',";// uid
			sql += "updatetime integer not null default '0' ,";// 更新时间
			sql += "createtime integer not null default '0',";// 创建时间
			sql += "level integer not null default '0',";// LH浓度, 60-70为峰值
			sql += "primary key (uid,createtime)";// 联合主键
			sql += ")";
			db.execSQL(sql);
			// HCG测试结果
			sql = "create table hcg_test_" + getDBVersion() + "(";
			sql += "uid integer not null default '0',";// uid
			sql += "updatetime integer not null default '0' ,";// 更新时间
			sql += "createtime integer not null default '0',";// 创建时间
			sql += "level integer not null default '0',";// HCG浓度
			sql += "primary key (uid,createtime)";// 联合主键
			sql += ")";
			db.execSQL(sql);
			// 通知
			sql = "create table message_" + getDBVersion() + "(";
			sql += "id integer primary key autoincrement not null default '1', ";// 本地自增id主键
			sql += "uid integer not null default '0',";// uid
			sql += "mid integer not null default '0',";// 消息id
			sql += "createtime integer not null default '0',";// 消息创建时间
			sql += "read integer not null default '0',";// 已读，未读
			sql += "type integer not null default '0',";// 消息类型
			sql += "title varchr(100) not null default '',";// 标题
			sql += "content varchr(100) not null default ''";// 标题
			sql += ")";
			db.execSQL(sql);
			// 用户会话列表
			sql = "create table conversation_" + getDBVersion() + "(";
			sql += "uid integer primary key not null default '0', ";// 用户id，主键
			sql += "mid integer not null default '0'";// 消息id
			sql += ")";
			db.execSQL(sql);
			db.setTransactionSuccessful();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.endTransaction();
		}
	}

	@Override
	protected void onDBUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

	}
}

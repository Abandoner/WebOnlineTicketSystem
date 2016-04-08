package bean;

import java.sql.*;

public class type {
	private String typeid;
	private String traintype;

	public String getTypeid() {
		return typeid;
	}

	public void setTypeid(String t) {
		this.typeid = t;
	}

	public void setTypename(String type) {
		this.traintype = type;
	}

	public String getSeat() {
		return traintype;
	}

	public boolean hasLogin(String tid) { // 检查该类型是否已经存在
		boolean f = true;
		String sql = "select type_id " + "from type where type_id ='" + tid
				+ "' ";
		sqlBean db = new sqlBean();
		try {
			ResultSet rs = db.executeQuery(sql);
			if (rs.next()) {
				f = false;
				System.out.println("该类型已经存在");
			} else {
				f = true;
				System.out.println("该类型不存在");
			}
		} catch (Exception e) {
			e.getMessage();
		}
		return f;
	}

	public ResultSet getType() {
		String sql = "select * from type";
		sqlBean db = new sqlBean();
		ResultSet rs = db.executeQuery(sql);
		return rs;
	}

	public void updateType() {
		String sql = "update type SET " + "type_id='" + typeid + "',name='"
				+ traintype + "' " + "where type_id='" + typeid + "'";
		sqlBean db = new sqlBean();
		db.executeInsert(sql);

	}

	public void deleteType() {

		String sql = "delete from type where type_id ='" + typeid + "' ";
		sqlBean db = new sqlBean();
		db.executeDelete(sql);
	}

	public int deleteType(String tid) {
		int num = 0;
		String sql = "delete from type where type_id ='" + tid + "' ";
		sqlBean db = new sqlBean();
		num = db.executeDelete(sql);
		return num;
	}

	public void addType() {
		String sql = "insert into type(type_id,name)  " + "VALUES('" + typeid
				+ "','" + traintype + "' )";
		sqlBean db = new sqlBean();
		db.executeInsert(sql);
	}
}

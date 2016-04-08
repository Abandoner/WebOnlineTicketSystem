package bean;

import java.sql.*;

public class sta_sta {
	private String stationid1;
	private String stationid2;
	private double price;
	private double distance;

	public String getStationid1() {
		return stationid1;
	}

	public void setStationid1(String id1) {
		this.stationid1 = id1;
	}

	public String getStationid2() {
		return stationid2;
	}

	public void setStationid2(String id2) {
		this.stationid2 = id2;
	}

	public void setPrice(double pri) {
		this.price = pri;
	}

	public double getPrice() {
		return price;
	}

	public void setDistance(double dis) {
		this.distance = dis;
	}

	public double getDistance() {
		return distance;
	}

	public boolean hasLogin(String id1, String id2) { // 检查该站站信息是否已经存在
		boolean f = true;
		String sql = "select station_id1,station_id2 "
				+ "from station_station where station_id1 ='" + id1
				+ "' and station_id2='" + id2 + "' ";
		sqlBean db = new sqlBean();
		try {
			ResultSet rs = db.executeQuery(sql);
			if (rs.next()) {
				f = false;
				System.out.println("该站站信息已经存在！");
			} else {
				f = true;
				System.out.println("该站站信息不存在！");
			}
		} catch (Exception e) {
			e.getMessage();
		}
		return f;
	}

	public ResultSet getSta_sta1() {
		String sql = "select station_id1,station_id2,distance,total_price"
				+ " from station_station";
		sqlBean db = new sqlBean();
		ResultSet rs = db.executeQuery(sql);
		System.out.println("查询站站信息成功");
		return rs;
	}

	// 更新站站信息
	public void updateSta_sta() {
		String sql = "update station_station SET " + "station_id1='"
				+ stationid1 + "',station_id2='" + stationid2 + "',"
				+ "distance='" + distance + "',total_price='" + price + "'"
				+ " where station_id1='" + stationid1 + "' and station_id2='"
				+ stationid2 + "' ";
		sqlBean db = new sqlBean();
		db.executeInsert(sql);
		System.out.println("更新站站信息成功");

	}

	// 删除站站信息
	public void deleteSta_sta() {
		String sql = "delete from station_station " + "where station_id1='"
				+ stationid1 + "' and station_id2='" + stationid2 + "' ";
		sqlBean db = new sqlBean();
		db.executeDelete(sql);
		System.out.println("删除站点信息成功 ！");
	}

	//
	public int deleteSta_sta(String stationid1, String stationid2) {
		int num = 0;
		String sql = "delete from station_station" + " where station_id1='"
				+ stationid1 + "' and station_id2='" + stationid2 + "' ";
		sqlBean db = new sqlBean();
		num = db.executeDelete(sql);
		return num;
	}

	public void addSta_sta() {
		String sql = "insert into station_station(station_id1,station_id2,distance,total_price)  "
				+ "VALUES('"
				+ stationid1
				+ "','"
				+ stationid2
				+ "',"
				+ "'"
				+ distance + "','" + price + "')";
		sqlBean db = new sqlBean();
		db.executeInsert(sql);
		System.out.println("添加站点信息成功！");
	}
}

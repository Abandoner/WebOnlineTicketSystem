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

	public boolean hasLogin(String id1, String id2) { // ����վվ��Ϣ�Ƿ��Ѿ�����
		boolean f = true;
		String sql = "select station_id1,station_id2 "
				+ "from station_station where station_id1 ='" + id1
				+ "' and station_id2='" + id2 + "' ";
		sqlBean db = new sqlBean();
		try {
			ResultSet rs = db.executeQuery(sql);
			if (rs.next()) {
				f = false;
				System.out.println("��վվ��Ϣ�Ѿ����ڣ�");
			} else {
				f = true;
				System.out.println("��վվ��Ϣ�����ڣ�");
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
		System.out.println("��ѯվվ��Ϣ�ɹ�");
		return rs;
	}

	// ����վվ��Ϣ
	public void updateSta_sta() {
		String sql = "update station_station SET " + "station_id1='"
				+ stationid1 + "',station_id2='" + stationid2 + "',"
				+ "distance='" + distance + "',total_price='" + price + "'"
				+ " where station_id1='" + stationid1 + "' and station_id2='"
				+ stationid2 + "' ";
		sqlBean db = new sqlBean();
		db.executeInsert(sql);
		System.out.println("����վվ��Ϣ�ɹ�");

	}

	// ɾ��վվ��Ϣ
	public void deleteSta_sta() {
		String sql = "delete from station_station " + "where station_id1='"
				+ stationid1 + "' and station_id2='" + stationid2 + "' ";
		sqlBean db = new sqlBean();
		db.executeDelete(sql);
		System.out.println("ɾ��վ����Ϣ�ɹ� ��");
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
		System.out.println("���վ����Ϣ�ɹ���");
	}
}

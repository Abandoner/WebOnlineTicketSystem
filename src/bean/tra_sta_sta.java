package bean;

import java.sql.*;

//train_station表的操作
public class tra_sta_sta {
	private String stationid;
	private String starttime;
	private String waittime;
	private String trainid;
	private int shunxu;

	public String getStationid() {
		return stationid;
	}

	public void setStationid(String id) {
		this.stationid = id;
	}

	public void setStarttime(String time) {
		this.starttime = time;
	}

	public String getStarttime() {
		return starttime;
	}

	public void setWaittime(String time) {
		this.waittime = time;
	}

	public String getWaittime() {
		return waittime;
	}

	public void setTrainid(String trainid) {
		this.trainid = trainid;
	}

	public String getTrainid() {
		return trainid;
	}

	public void setShunxu(int sx) {
		this.shunxu = sx;
	}

	public int getShunxu() {
		return shunxu;
	}

	public boolean hasLogin(String tid, String sid) { // 检查该站点在train_station表中是否已经存在
		boolean f = true;
		String sql = "select station_id "
				+ "from train_station where train_id ='" + tid
				+ "' and station_id='" + sid + "' ";
		sqlBean db = new sqlBean();
		try {
			ResultSet rs = db.executeQuery(sql);
			if (rs.next()) {
				f = false;
				System.out.println("该车站存在");
			} else {
				f = true;
				System.out.println("该车站不存在");
			}
		} catch (Exception e) {
			e.getMessage();
		}
		return f;
	}

	public ResultSet getTrainStation() {
		String sql = "select train_id,station_id,sequence,wait_time,starttime"
				+ " from train_station ";
		sqlBean db = new sqlBean();
		ResultSet rs = db.executeQuery(sql);
		return rs;
	}

	public void updateTra_Sta() {
		String sql = "update train_station SET " + "station_id='" + stationid
				+ "',train_id='" + trainid + "'," + "sequence='" + shunxu
				+ "',wait_time='" + waittime + "',starttime='" + starttime
				+ "'" + " where train_id='" + trainid + "' and station_id='"
				+ stationid + "' ";
		sqlBean db = new sqlBean();
		db.executeInsert(sql);

	}

	public void deleteTra_Sta() {
		String sql = "delete from train_station where station_id ='"
				+ stationid + "' and train_id='" + trainid + "' ";
		sqlBean db = new sqlBean();
		db.executeDelete(sql);
	}

	public int deleteTra_Sta(String sid, String tid) {
		int num = 0;
		String sql = "delete from train_station where station_id ='" + sid
				+ "' and train_id='" + tid + "' ";
		sqlBean db = new sqlBean();
		num = db.executeDelete(sql);
		return num;
	}

	public void addTra_Sta() {
		String sql = "insert into train_station(train_id,station_id,sequence,wait_time,starttime) "
				+ "VALUES('"
				+ trainid
				+ "','"
				+ stationid
				+ "','"
				+ shunxu
				+ "','" + waittime + "','" + starttime + "')";
		sqlBean db = new sqlBean();
		db.executeInsert(sql);
	}
}

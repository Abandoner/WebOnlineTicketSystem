package bean;

import java.sql.*;

public class query {
	private String trainid;
	private String stationid;
	private String destination;

	public String getStartplace() {
		return stationid;
	}

	public void setStartplace(String st) {
		this.stationid = st;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String md) {
		this.destination = md;
	}

	public void setTrainid(String trainid) {
		this.trainid = trainid;
	}

	public String getTrainid() {
		return trainid;
	}

	// 按车次查询该车次经过的所有站点
	public ResultSet Trainquery(String trainid) {
		String sql = "select train_id,station_id,sequence,starttime from train_station "
				+ " where train_id='" + trainid + "'order by sequence";
		sqlBean db = new sqlBean();
		ResultSet rs = db.executeQuery(sql);
		return rs;

	}

	// 查询经过两个站点的车次集合
	public ResultSet Trainidquery(String start, String destination) {
		String sql = "select a.* from (select train_id,station_id,starttime "
				+ " from train_station where station_id='" + start + "')as a"
				+ " cross join (select train_id,station_id,starttime "
				+ " from train_station where station_id='" + destination
				+ "') as b" + " on a.train_id=b.train_id ";

		sqlBean db = new sqlBean();
		ResultSet rs = db.executeQuery(sql);
		return rs;

	}

	public ResultSet getMaxseat(String trainid) {
		String flag = "0";
		String sql = "select * from ticket where train_id='" + trainid
				+ "' and isbook='" + flag + "'";
		sqlBean db = new sqlBean();
		ResultSet rs = db.executeQuery(sql);
		return rs;
	}

	public ResultSet getMaxTicSeat(String trainid) {
		String flag = "0";
		String sql = "select min(seat) from ticket where train_id='" + trainid
				+ "' and isbook='" + flag + "'";
		sqlBean db = new sqlBean();
		ResultSet rs = db.executeQuery(sql);
		return rs;
	}

	// 按顺序查询经过两个站点之间的所有站点
	public ResultSet Stationvarquery(String trainid, int seq1, int seq2) {
		String sql = "select station_id from train_station "
				+ "where train_id='" + trainid + "' and sequence between '"
				+ seq1 + "' and '" + seq2 + "' order by sequence";
		sqlBean db = new sqlBean();
		ResultSet rs = db.executeQuery(sql);
		return rs;
	}

	// 查询某个车次的某个站点的顺序号
	public ResultSet Sequencequery(String trainid, String stationid) {
		String sql = "select sequence from train_station where train_id='"
				+ trainid + "' and station_id='" + stationid + "' ";
		sqlBean db = new sqlBean();
		ResultSet rs = db.executeQuery(sql);
		return rs;
	}

	public ResultSet Stationquery(String station) {
		String sql = "select train_station.train_id,station_id,train_station.starttime,sequence,start_station,dest_station"
				+ " from train_station,train where station_id='"
				+ station
				+ "' " + " and train_station.train_id=train.train_id";
		sqlBean db = new sqlBean();
		ResultSet rs = db.executeQuery(sql);
		return rs;
	}

	public ResultSet getStationdistance(String station1, String station2) {
		String sql = "select distance from station_station where station_id1='"
				+ station1 + "' " + " and station_id2='" + station2 + "' ";
		sqlBean db = new sqlBean();
		ResultSet rs = db.executeQuery(sql);
		return rs;
	}

	public ResultSet getUnitPrice() {
		String sql = "select unit_price from train";
		sqlBean db = new sqlBean();
		ResultSet rs = db.executeQuery(sql);
		return rs;
	}

	public ResultSet getTicketnum(String trainid) {
		String flag = "0";
		String sql = "select count(*) from ticket where train_id='" + trainid
				+ "'and isbook ='" + flag + "'";
		sqlBean db = new sqlBean();
		ResultSet rs = db.executeQuery(sql);
		return rs;
	}

	public ResultSet getCountnum(String userid, String start, String dest) {
		String sql = "select countnumber from book where user_id='" + userid
				+ "' and" + " start_station='" + start + "' and dest_station='"
				+ dest + "'";
		sqlBean db = new sqlBean();
		ResultSet rs = db.executeQuery(sql);
		return rs;
	}

}

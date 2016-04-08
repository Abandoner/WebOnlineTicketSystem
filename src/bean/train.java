package bean;

import java.sql.*;

public class train {
	private String startstation;
	private String deststation;
	private String starttime;
	private String traintype;
	private String trainid;
	private int ticketnum;
	private int speed;
	private double unitprice;

	public String getStartstaion() {
		return startstation;
	}

	public void setStartstation(String sp) {
		this.startstation = sp;
	}

	public String getDeststation() {
		return deststation;
	}

	public void setDeststation(String md) {
		this.deststation = md;
	}

	public void setStarttime(String time) {
		this.starttime = time;
	}

	public String getStarttime() {
		return starttime;
	}

	public void setType(String type) {
		this.traintype = type;
	}

	public String getType() {
		return traintype;
	}

	public void setTrainid(String trainid) {
		this.trainid = trainid;
	}

	public String getTrainid() {
		return trainid;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public int getSpeed() {
		return speed;
	}

	public void setUnitprice(double pri) {
		this.unitprice = pri;
	}

	public double getUnitprice() {
		return unitprice;
	}

	public void setTicketnum(int num) {
		this.ticketnum = num;
	}

	public int getTicketnum() {
		return ticketnum;
	}

	public boolean hasLogin(String tid, String sp, String des) { // 检查该车次是否已经存在
		boolean f = true;
		String sql = "select train_id " + "from train where train_id ='" + tid
				+ "' and start_station='" + sp + "' and dest_station='" + des
				+ "' ";
		sqlBean db = new sqlBean();
		try {
			ResultSet rs = db.executeQuery(sql);
			if (rs.next()) {
				f = false;
				System.out.println("该车次已经存在");
			} else {
				f = true;
				System.out.println("该车次不存在");
			}
		} catch (Exception e) {
			e.getMessage();
		}
		return f;
	}

	public ResultSet getTrain() {
		updateTrainNUm();
		String sql = "select * from train ";
		sqlBean db = new sqlBean();
		ResultSet rs = db.executeQuery(sql);
		return rs;
	}

	public void updateTrainNUm() {
		String sql = "select train_id from train ";
		String notrain = "";
		String sqll = "update train SET ticket_num =(select count(*) from ticket where train_id='"
				+ notrain + "')";
		sqlBean db = new sqlBean();
		ResultSet rs = db.executeQuery(sql);
		try {
			while (rs.next()) {
				notrain = rs.getString("train_id");
				ResultSet rss = db.executeQuery(sqll);
			}
		} catch (SQLException e) {

		}

	}

	public void updateTrain() {
		String sql = "update train SET " + "start_station='" + startstation
				+ "',dest_station='" + deststation + "'," + "starttime='"
				+ starttime + "',train_type='" + traintype + "'," + "speed='"
				+ speed + "',ticket_num='" + ticketnum + "',unit_price='"
				+ unitprice + "'" + " where train_id='" + trainid + "' ";
		sqlBean db = new sqlBean();
		db.executeInsert(sql);
	}

	public void deleteTrain() {

		String sql = "delete from train where train_id ='" + trainid + "'";
		sqlBean db = new sqlBean();
		db.executeDelete(sql);
	}

	public int deleteTrain(String tid) {
		int num = 0;
		String sql = "delete from train where train_id ='" + tid + "'";
		sqlBean db = new sqlBean();
		num = db.executeDelete(sql);
		return num;
	}

	public void addTrain() {
		String sql = "insert into train(train_id,start_station,dest_station,starttime,train_type,speed,ticket_num,unit_price)  "
				+ "VALUES('"
				+ trainid
				+ "','"
				+ startstation
				+ "','"
				+ deststation
				+ "',"
				+ "'"
				+ starttime
				+ "','"
				+ traintype
				+ "','" + speed + "','" + ticketnum + "','" + unitprice + "')";
		sqlBean db = new sqlBean();

		db.executeInsert(sql);
	}

	public ResultSet ticketnum() {
		String sql = "select ticket_num from train ";
		sqlBean db = new sqlBean();
		ResultSet rs = db.executeQuery(sql);
		return rs;
	}

}
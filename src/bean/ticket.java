package bean;

import java.sql.*;

public class ticket {
	private String ticketid;
	private String startplace;
	private String destination;
	private String type;
	private String trainid;
	private double price;
	private int seat;
	private String startdate;

	public String getticketId() {
		return ticketid;
	}

	public void setticketId(String tid) {
		this.ticketid = tid;
	}

	public String getStartplace() {
		return startplace;
	}

	public void setStartplace(String sp) {
		this.startplace = sp;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String md) {
		this.destination = md;
	}

	public String getStartdate() {
		return startdate;
	}

	public void setStartdate(String sd) {
		this.startdate = sd;
	}

	public void setType(String t) {
		this.type = t;
	}

	public String getType() {
		return type;
	}

	public void setTrainid(String trainid) {
		this.trainid = trainid;
	}

	public String getTrainid() {
		return trainid;
	}

	public void setSeat(int seat) {
		this.seat = seat;
	}

	public int getSeat() {
		return seat;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double jg) {
		this.price = jg;
	}

	public boolean hasLogin(String tid) { // 检查该车票是否已经存在
		boolean f = true;
		String sql = "select ticket_id from ticket where ticket_id ='" + tid
				+ "'";
		sqlBean db = new sqlBean();
		try {
			ResultSet rs = db.executeQuery(sql);
			if (rs.next()) {
				f = false;
				System.out.println("该车票已经存在！");
			} else {
				f = true;
				System.out.println("该车票不存在！");
			}
		} catch (Exception e) {
			e.getMessage();
		}
		return f;
	}

	public String getTicket_id(String train_id) {
		String flag = "0";
		String sql = "select min(ticket_id) from ticket where train_id='"
				+ train_id + "' and isbook='" + flag + "'";
		sqlBean db = new sqlBean();
		String tic_id = "";
		try {
			ResultSet rs = db.executeQuery(sql);
			if (rs.next()) {
				tic_id = rs.getString("min(ticket_id)");
			} else {
				tic_id = null;
				System.out.println("车票编号分配失败！");
			}
		} catch (Exception e) {
			e.getMessage();
		}
		db.CloseDataBase();
		return tic_id;
	}

	public ResultSet getTicket() {
		String sql = "select * from ticket where isBook= '0' order by ticket_id ";
		sqlBean db = new sqlBean();
		ResultSet rs = db.executeQuery(sql);
		System.out.println("车票信息如下：");
		return rs;
	}

	public void addBook(String tic, String start, String dest, String userid,
			double sub, String count, double afford, String starttime,
			String ordertime) {
		ticket ticd = new ticket();

		String sql = "insert into book(ticket_id,start_station,dest_station,user_id,subscription,countnumber,afford)  "
				+ "VALUES('"
				+ tic
				+ "','"
				+ start
				+ "','"
				+ dest
				+ "','"
				+ userid
				+ "','"
				+ sub
				+ "',"
				+ "'"
				+ count
				+ "','"
				+ afford
				+ "')";
		String sqll = "update book SET starttime='" + starttime
				+ "',ordertime='" + ordertime + "' where ticket_id ='" + tic
				+ "'";

		sqlBean db = new sqlBean();
		System.out.println("添加车票成功");
		db.executeInsert(sql);
		db.CloseDataBase();
		sqlBean db2 = new sqlBean();
		db2.executeInsert(sqll);
		updateTicket(tic);
		db2.CloseDataBase();
	}

	public void updateBook(String userid, int num) {// 从Book表中更新订票数目
		String sql = "update book SET countnumber='" + num + "' "
				+ " where user_id='" + userid + "' and start_station='"
				+ startplace + "' and dest_station='" + destination + "'";
		sqlBean db = new sqlBean();
		db.executeDelete(sql);
		System.out.println("更新车票数量成功!");
	}

	public void deleteBook(String userid) {// 从Book表中删除订票信息
		String sql = "delete from book  where user_id='" + userid
				+ "' and start_station='" + startplace + "' and dest_station='"
				+ destination + "'";
		sqlBean db = new sqlBean();
		db.executeDelete(sql);
		System.out.println("删除");
	}

	public void deleteTicket(String tic) {
		String sql = "delete from ticket where ticket_id ='" + tic + "'";
		sqlBean db = new sqlBean();
		db.executeDelete(sql);
	}

	public void addTicket() {
		String sql = "insert into ticket(ticket_id,start_station,dest_station,startdate,price,type,seat,train_id)  "
				+ "VALUES('"
				+ ticketid
				+ "','"
				+ startplace
				+ "','"
				+ destination
				+ "','"
				+ startdate
				+ "',"
				+ "'"
				+ price
				+ "','"
				+ type + "','" + seat + "','" + trainid + "')";
		sqlBean db = new sqlBean();
		db.executeInsert(sql);
	}

	public void updateTicket(String tic) {
		String flag = "1";
		String sql = "update ticket SET isbook='" + flag
				+ "' where ticket_id= '" + tic + "'";
		sqlBean db = new sqlBean();
		db.executeInsert(sql);
		db.CloseDataBase();
		System.out.println("添加updateTicket isbook=1！");
	}

	public void updateDeletBook(String tic) {
		String flag = "0";

		String sql = "update ticket SET isbook='" + flag
				+ "'where ticket_id= '" + tic + "'";
		sqlBean db = new sqlBean();
		db.executeInsert(sql);
		db.CloseDataBase();

	}

	public void updateTrain(int ticketnum) {
		String sql = "update train SET ticket_num='" + ticketnum + "' "
				+ " where train_id='" + trainid + "' ";
		sqlBean db = new sqlBean();
		db.executeInsert(sql);
		System.out.println("更新车票数量");

	}
}

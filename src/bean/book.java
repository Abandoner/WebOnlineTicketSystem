package bean;

import java.sql.*;

public class book {
	private String userid; // 用户号
	private int countnum; // 订票数目
	private double dingjin; // 订金
	private double afford; // 应付金额
	private String startplace;
	private String ticket_id;
	private String destination;
	private String starttime;
	private String ordertime;

	public String getticket_id() {
		return ticket_id;
	}

	public void setticket_id(String uid) {
		this.ticket_id = uid;
	}

	public String getstarttime() {
		return starttime;
	}

	public void setstarttime(String uid) {
		this.starttime = uid;
	}

	public String getordertime() {
		return ordertime;
	}

	public void setordertime(String uid) {
		this.ordertime = uid;
	}

	public String getuserId() {
		return userid;
	}

	public void setuserId(String uid) {
		this.userid = uid;
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

	public int getCountnum() {
		return countnum;
	}

	public void setCountnum(int num) {
		this.countnum = num;
	}

	public double getSub() {
		return dingjin;
	}

	public void setSub(double dj) {
		this.dingjin = dj;
	}

	public double getAfford() {
		return afford;
	}

	public void setAfford(double afford) {
		this.afford = afford;
	}

	public boolean hasLogin(String tid, String uid) { // 检查该订票信息是否已经存在
		boolean f = true;
		String sql = "select user_id,ticket_id from book"
				+ " where ticket_id ='" + tid + "' and user_id='" + uid + "' ";
		sqlBean db = new sqlBean();
		try {
			ResultSet rs = db.executeQuery(sql);
			if (rs.next()) {
				f = false;
			} else {
				f = true;
			}
		} catch (Exception e) {
			e.getMessage();
		}
		return f;
	}

	public ResultSet getBook() {
		String sql = "select book.ticket_id,user_id,book.start_station,book.dest_station,subscription,countnumber,afford,"
				+ "train_id,type,price,book.starttime,book.ordertime from book,ticket"
				+ " where book.ticket_id=ticket.ticket_id ";
		sqlBean db = new sqlBean();
		ResultSet rs = db.executeQuery(sql);
		return rs;
	}

	public void deleteBook(String ticid) {

		String sql = "delete from book where ticket_id='" + ticid + "' ";
		sqlBean db = new sqlBean();
		db.executeDelete(sql);
	}

	public int deleteBook(String userid, String ticketid) {
		int num = 0;
		String sql = "delete from book where ticket_id ='" + ticketid
				+ "' and " + " user_id='" + userid + "' ";
		sqlBean db = new sqlBean();
		num = db.executeDelete(sql);
		return num;
	}

}

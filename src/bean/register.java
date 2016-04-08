package bean;

import java.sql.*;

public class register {
	private String userid;
	private String username;
	private String password;
	private String id;
	private String sex;
	private String email;
	private String phone;
	private String address;

	public void setSex(String s) {
		sex = s;
	}

	public String getSex() {
		return sex;
	}

	public String getuserId() {
		return userid;
	}

	public void setuserId(String userid) {
		this.userid = userid;
	}

	public String getUserName() {
		return username;
	}

	public void setUserName(String name) {
		this.username = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public boolean hasLogin(String userid) { // 检查该yonghu是否已经注册
		boolean f = true;
		String sql = "select user_id from user where user_id ='" + userid + "'";
		sqlBean db = new sqlBean();
		try {
			ResultSet rs = db.executeQuery(sql);
			if (rs.next()) {
				f = false;
				System.out.println("该用户已经被注册");
			} else {
				f = true;
				System.out.println("该用户没有被注册");
			}
		} catch (Exception e) {
			e.getMessage();
		}
		return f;
	}

	public void addUser() {
		String sql = "insert into user(user_id,user_name,sex,id,password,email,phone,address)"
				+ " VALUES('"
				+ userid
				+ "','"
				+ username
				+ "','"
				+ sex
				+ "','"
				+ id
				+ "','"
				+ password
				+ "','"
				+ email
				+ "','"
				+ phone
				+ "','"
				+ address + "')";

		sqlBean db = new sqlBean();
		db.executeInsert(sql);
	}
}

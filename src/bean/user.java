package bean;

import java.sql.*;

public class user {
	private String userid;   //帐号
	private String username;  //用户名
	private String password;  //密码
	private String id; // 身份证号
	private String sex;    //性别
	private String email;   //邮箱
	private String phone;   //电话
	private String address;   //地址

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

	public String getuserName() {
		return username;
	}

	public void setuserName(String name) {
		this.username = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmail() {
		return email;
	}

	public void setPhone(String p) {
		this.phone = p;
	}

	public String getPhone() {
		return phone;
	}

	public void setAddress(String ad) {
		this.address = ad;
	}

	public String getAddress() {
		return address;
	}
	
    // 检查该用户是否已经注册
	public boolean hasLogin(String userid) {    //传入参数userid
		boolean f = true;     //定于注册过为true
		String sql = "select user_id from user where user_id ='" + userid + "'";  //从数据库中查询user_id
		sqlBean db = new sqlBean(); 
		try {
			ResultSet rs = db.executeQuery(sql);//获取到数据库中的use_rid
			if (rs.next()) {
				f = false;  //如果没有注册返回false
			} else {
				f = true;   //注册过返回true
			}
		} catch (Exception e) {
			e.getMessage();
		}
		return f;
	}

	public ResultSet getUser() throws SQLException {
		String sql = "select * from user ";
		sqlBean db = new sqlBean();
		ResultSet rs = db.executeQuery(sql);
		if(rs.next()){
			System.out.println(rs.getString(1));
		}
		return rs;
	}
   // 查看旧密码是否正确
	public ResultSet equalpass(String userid) {
		String sql = "select password from user where user_id='" + userid
				+ "' ";
		sqlBean db = new sqlBean();
		ResultSet rs = db.executeQuery(sql);
		return rs;

	}
   // 查看管理员旧密码是否正确
	public ResultSet equaladminpass(String id) { 
		String sql = "select password from admin where admin_id='" + id + "' ";
		sqlBean db = new sqlBean();
		ResultSet rs = db.executeQuery(sql);
		return rs;

	}

	public int updatepass(String userid, String pass) { // 修改密码
		int num = 0;
		String sql = "update user SET password ='" + pass + "' where user_id='"
				+ userid + "' ";
		sqlBean db = new sqlBean();
		num = db.executeInsert(sql);
		return num;
	}

	public int updateadmin(String aid, String pass) { // 修改密码
		int num = 0;
		String sql = "update admin SET password ='" + pass
				+ "' where admin_id='" + aid + "' ";
		sqlBean db = new sqlBean();
		num = db.executeInsert(sql);
		return num;
	}

	public void deleteUser() {

		String sql = "delete from user where user_id ='" + userid + "' ";
		sqlBean db = new sqlBean();
		db.executeDelete(sql);
	}

	public int deleteUser(String userid) {
		int num = 0;
		String sql = "delete from user where user_id ='" + userid + "' ";
		sqlBean db = new sqlBean();
		num = db.executeDelete(sql);
		return num;
	}

}

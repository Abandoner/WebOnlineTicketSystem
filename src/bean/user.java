package bean;

import java.sql.*;

public class user {
	private String userid;   //�ʺ�
	private String username;  //�û���
	private String password;  //����
	private String id; // ���֤��
	private String sex;    //�Ա�
	private String email;   //����
	private String phone;   //�绰
	private String address;   //��ַ

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
	
    // �����û��Ƿ��Ѿ�ע��
	public boolean hasLogin(String userid) {    //�������userid
		boolean f = true;     //����ע���Ϊtrue
		String sql = "select user_id from user where user_id ='" + userid + "'";  //�����ݿ��в�ѯuser_id
		sqlBean db = new sqlBean(); 
		try {
			ResultSet rs = db.executeQuery(sql);//��ȡ�����ݿ��е�use_rid
			if (rs.next()) {
				f = false;  //���û��ע�᷵��false
			} else {
				f = true;   //ע�������true
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
   // �鿴�������Ƿ���ȷ
	public ResultSet equalpass(String userid) {
		String sql = "select password from user where user_id='" + userid
				+ "' ";
		sqlBean db = new sqlBean();
		ResultSet rs = db.executeQuery(sql);
		return rs;

	}
   // �鿴����Ա�������Ƿ���ȷ
	public ResultSet equaladminpass(String id) { 
		String sql = "select password from admin where admin_id='" + id + "' ";
		sqlBean db = new sqlBean();
		ResultSet rs = db.executeQuery(sql);
		return rs;

	}

	public int updatepass(String userid, String pass) { // �޸�����
		int num = 0;
		String sql = "update user SET password ='" + pass + "' where user_id='"
				+ userid + "' ";
		sqlBean db = new sqlBean();
		num = db.executeInsert(sql);
		return num;
	}

	public int updateadmin(String aid, String pass) { // �޸�����
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

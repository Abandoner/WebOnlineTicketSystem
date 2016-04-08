package bean;

import java.sql.*;

public class sqlBean {
	public Connection conn = null;

	public ResultSet rs = null;

	private String DatabaseDriver = null;
	// DataSource ����Դ����DSN
	private String DatabaseConnStr = "jdbc:mysql://localhost:3306/dlnuyj?useUnicode=true&characterEncoding=utf-8";
	private String User = "root";
	private String password = "123456";

	// ���巽��
	public void setDatabaseDriver(String Driver) {
		this.DatabaseDriver = Driver;
	}

	public String getDatabaseDriver() {
		return (this.DatabaseDriver);
	}

	public void setDatabaseConnStr(String ConnStr) {
		this.DatabaseConnStr = ConnStr;
	}

	public String getDatabaseConnStr() {
		return (this.DatabaseConnStr);
	}

	public void setuser(String user) {
		this.User = user;
	}

	public String user() {
		return (this.User);
	}

	public void setpassword(String password) {
		this.password = password;
	}

	public String getpassword() {
		return (this.password);
	}

	public sqlBean(String Driver, String user, String password) {
		try {
			setDatabaseDriver(Driver);
			setuser(user);
			setpassword(password);
			Class.forName(DatabaseDriver);
		} catch (java.lang.ClassNotFoundException e) {
			System.err.println("�����������д���:" + e.getMessage());
			System.out.print("ִ�в����д�?:" + e.getMessage());// ������ͻ���
		}
	}

	public sqlBean() {
		try {
			String Driver = "com.mysql.jdbc.Driver";
			setDatabaseDriver(Driver);
			Class.forName(DatabaseDriver);
		} catch (java.lang.ClassNotFoundException e) {
			System.err.println("�����������д���:" + e.getMessage());
			System.out.print("ִ�в����д�?:" + e.getMessage());// ������ͻ���
		}
	}

	public int executeInsert(String sql) {
		int num = 0;
		try {
			conn = DriverManager.getConnection(DatabaseConnStr, User, password);
			Statement stmt = conn.createStatement();
			num = stmt.executeUpdate(sql);
		} catch (SQLException ex) {
			System.err.println("ִ�в����д�?:" + ex.getMessage());
			System.out.print("ִ�в����д�?:" + ex.getMessage());// ������ͻ���
		}

		CloseDataBase();
		return num;
	}


	public ResultSet executeQuery(String sql) {
		rs = null;
		try {
			conn = DriverManager.getConnection(DatabaseConnStr, User, password);
			Statement stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
		} catch (SQLException ex) {
			System.err.println("ִ�в�ѯ�д�?:" + ex.getMessage());
			System.out.print("ִ�в�ѯ�д�?:" + ex.getMessage()); // ������ͻ���
		}

		return rs;
	}

	public int executeDelete(String sql) {
		int num = 0;
		try {

			conn = DriverManager.getConnection(DatabaseConnStr, User, password);
			Statement stmt = conn.createStatement();
			num = stmt.executeUpdate(sql);
		} catch (SQLException ex) {
			System.err.println("ִ��ɾ���д�?:" + ex.getMessage());
			System.out.print("ִ��ɾ���д�?:" + ex.getMessage()); // ������ͻ���
		}
		CloseDataBase();
		return num;
	}

	public void CloseDataBase() {
		try {
			conn.close();
		} catch (Exception end) {
			System.err.println("ִ�йر�Connection�����д���" + end.getMessage());
			System.out.print("ִ��ִ�йر�Connection�����д����д�?:" + end.getMessage()); // ������ͻ���
		}
	}

}

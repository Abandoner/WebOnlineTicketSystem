package bean;

import java.sql.*;

public class sqlBean {
	public Connection conn = null;

	public ResultSet rs = null;

	private String DatabaseDriver = null;
	// DataSource 数据源名称DSN
	private String DatabaseConnStr = "jdbc:mysql://localhost:3306/dlnuyj?useUnicode=true&characterEncoding=utf-8";
	private String User = "root";
	private String password = "123456";

	// 定义方法
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
			System.err.println("加载驱动器有错误:" + e.getMessage());
			System.out.print("执行插入有错?:" + e.getMessage());// 输出到客户端
		}
	}

	public sqlBean() {
		try {
			String Driver = "com.mysql.jdbc.Driver";
			setDatabaseDriver(Driver);
			Class.forName(DatabaseDriver);
		} catch (java.lang.ClassNotFoundException e) {
			System.err.println("加载驱动器有错误:" + e.getMessage());
			System.out.print("执行插入有错?:" + e.getMessage());// 输出到客户端
		}
	}

	public int executeInsert(String sql) {
		int num = 0;
		try {
			conn = DriverManager.getConnection(DatabaseConnStr, User, password);
			Statement stmt = conn.createStatement();
			num = stmt.executeUpdate(sql);
		} catch (SQLException ex) {
			System.err.println("执行插入有错?:" + ex.getMessage());
			System.out.print("执行插入有错?:" + ex.getMessage());// 输出到客户端
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
			System.err.println("执行查询有错?:" + ex.getMessage());
			System.out.print("执行查询有错?:" + ex.getMessage()); // 输出到客户端
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
			System.err.println("执行删除有错?:" + ex.getMessage());
			System.out.print("执行删除有错?:" + ex.getMessage()); // 输出到客户端
		}
		CloseDataBase();
		return num;
	}

	public void CloseDataBase() {
		try {
			conn.close();
		} catch (Exception end) {
			System.err.println("执行关闭Connection对象有错误：" + end.getMessage());
			System.out.print("执行执行关闭Connection对象有错误：有错?:" + end.getMessage()); // 输出到客户端
		}
	}

}

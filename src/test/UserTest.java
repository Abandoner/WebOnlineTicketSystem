package test;

import java.sql.SQLException;

import bean.user;

public class UserTest {

	/**
	 * @param args
	 * @throws SQLException 
	 */
	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
        user user=new user();
        user.setuserId("sd100110");
        user.setId("sd10010");
        user.setEmail("jkd_1922@163.com");
        user.setuserName("zhanghaiyang");
        user.setPassword("sd10010");
        user.setPhone("021-120022110");
        user.setSex("man");
        user.setAddress("江苏省镇江市江苏科技大学南校区65号");
        System.out.println(user.getId());
        System.out.println(user.getAddress());
        System.out.println(user.getEmail());
        System.out.println(user.getPassword());
        System.out.println(user.getSex());
        System.out.println( user.updatepass("sd100110", "sd100110"));
        System.out.println(user.deleteUser("sd100110"));
        
        user.hasLogin("sd1001"); //检查该用户ID是否已经注册过
        System.out.println(user.getUser());//获取SQL所有查询结果
 
        
	}

}

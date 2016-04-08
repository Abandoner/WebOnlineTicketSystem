package test;

import bean.register;

public class RegisterTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		register re=new register();
		re.hasLogin("sd1001");//检查该用户是否被注册过
		//re.addUser();//添加新的用户

	}

}

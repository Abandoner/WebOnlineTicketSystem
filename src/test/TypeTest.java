package test;

import java.sql.ResultSet;
import java.sql.SQLException;

import bean.type;

public class TypeTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
        type type=new type();
        //检查该类型是否存在
        type.hasLogin("1");
        type.hasLogin("6");
        System.out.println("==================================");
        ResultSet rs=type.getType();
        try {
			while(rs.next()){
				System.out.println(rs.getString(1)+"\t"+rs.getString(2));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(type.getSeat());
		
		
        
	}

}

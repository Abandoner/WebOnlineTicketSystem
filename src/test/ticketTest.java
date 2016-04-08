package test;

import java.sql.ResultSet;
import java.sql.SQLException;

import bean.ticket;

public class ticketTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
        ticket tic=new ticket();
        //检查该车票是否存在
        tic.hasLogin("D54011");
        tic.hasLogin("D540232");
        System.out.println("----------------------------------");
        ResultSet rs=tic.getTicket();
        try {
			while(rs.next()){
				System.out.println(rs.getString(1)+"\t"+rs.getString(2)+"\t"+
						rs.getString(3)+"\t"+rs.getString(4)+"\t"+
						rs.getDouble(5)+"\t"+rs.getString(6)+"\t"+
						rs.getString(7)+"\t"+rs.getString(8)+"\t");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 System.out.println("----------------------------------");
		 tic.addTicket();
		// 从Book表中更新订票数目
		 System.out.println("----------------------------------");
		 tic.updateBook("sd1001", 10);
		 tic.updateTrain(1000);
	
	}

}

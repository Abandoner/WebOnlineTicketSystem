package test;

import java.sql.ResultSet;
import java.sql.SQLException;

import bean.train;

public class TrainTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
       train train=new train();
     //���ó����Ƿ��Ѿ����� 
       train.hasLogin("D5402", "�Ϻ�", "�Ͼ�");
       train.hasLogin("D5419", "�Ϻ�", "��");
       train.hasLogin("1476", "beijing", "hengshui");
       System.out.println("------------------");
       /*��ȡ�����г��Ľ����*/
       ResultSet rs=train.getTrain();
       try {
		while(rs.next()){
			   System.out.println(rs.getString(1)+"\t"+rs.getString(2)+"\t"+rs.getString(3)+"\t"+
					  rs.getString(4)+"\t"+rs.getInt(5)+"\t"+rs.getInt(6)+"\t"+rs.getString(7)+"\t"+
					  rs.getDouble(8));
		   }
	} catch (SQLException e) {
		e.printStackTrace();
	}
	System.out.println("------------------");
	    ResultSet rs1=train.ticketnum();
	    try {
			while(rs1.next()){
//				System.out.println(rs1.getString(1)+"\t"+rs1.getString(2));
				System.out.println(rs1.getString(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
       
	}

}

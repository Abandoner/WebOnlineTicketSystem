package test;

import java.sql.ResultSet;
import java.sql.SQLException;

import bean.query;

public class QueryTest {

	/**
	 * @param args
	 * @throws SQLException 
	 */
	public static void main(String[] args) throws SQLException {
		query query=new query();
        ResultSet rs=query.Trainquery("1234");//按车次查询该车次经过的所有站点
        while(rs.next()){
        	System.out.println(rs.getString(1)+"\t"+rs.getString(2)+"\t"+rs.getInt(3)+"\t"+rs.getString(4));
        }
        //查询经过两个站点的车次集合
        ResultSet rs1=query.Trainidquery("昆山", "苏州");
        	while(rs1.next()){
        		System.out.println(rs1.getString(1)+"\t"+rs1.getString(2)+"\t"+rs1.getString(3));
        	}
        //获取该车次的座位号
        ResultSet rs2=query.getMaxseat("D5406");
        while(rs2.next()){
        	System.out.println(rs2.getInt(1));
        }
      //按顺序查询经过两个站点之间的所有站点
        ResultSet rs3=query.Stationvarquery("D5402", 2, 6);
        while(rs3.next()){
        	System.out.println(rs3.getString(1));
        }
      //查询某个车次的某个站点的顺序号
        ResultSet rs4=query.Sequencequery("D5402", "苏州");
        while(rs4.next()){
        	System.out.println(rs4.getInt(1));
        }
        ResultSet rs5=query.Stationquery("镇江");
        while(rs5.next()){
        	System.out.println(rs5.getString(1)+"\t"+rs5.getString(2)+"\t"+rs5.getString(3)+"\t"+rs5.getString(4)+"\t"+rs5.getString(5)+"\t");
        }
        ResultSet rs6=query.getStationdistance("苏州","南京");
        while(rs6.next()){
        	System.out.println(rs6.getDouble(1));
        }
        

	}

}

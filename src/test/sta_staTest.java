package test;

import java.sql.ResultSet;
import java.sql.SQLException;

import bean.sta_sta;

public class sta_staTest {

	/**
	 * @param args
	 * @throws SQLException 
	 */
	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		sta_sta stas=new sta_sta();
		// 检查该站站信息是否已经存在
		stas.hasLogin("上海", "南京");
		stas.hasLogin("北京", "南京");
		System.out.println("--------------------------------------------------");
		ResultSet rs=stas.getSta_sta1();//获取站站信息
		while(rs.next()){
			System.out.println(rs.getString(1)+"\t"+rs.getString(2)+"\t"+rs.getDouble(3)+"\t"
					+rs.getDouble(4));
		}
		System.out.println("--------------------------------------------------");
		 /*stas.setDistance(315.0);
		 stas.setPrice(93.0);
		 stationid1 = stas.setStationid1("上海");
		 stationid2 = stas.setStationid2("南京");*/
		stas.deleteSta_sta();
		stas.deleteSta_sta("beijing", "nanjing");
		stas.addSta_sta();
		stas.deleteSta_sta();
		 
		

	}

}

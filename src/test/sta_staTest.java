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
		// ����վվ��Ϣ�Ƿ��Ѿ�����
		stas.hasLogin("�Ϻ�", "�Ͼ�");
		stas.hasLogin("����", "�Ͼ�");
		System.out.println("--------------------------------------------------");
		ResultSet rs=stas.getSta_sta1();//��ȡվվ��Ϣ
		while(rs.next()){
			System.out.println(rs.getString(1)+"\t"+rs.getString(2)+"\t"+rs.getDouble(3)+"\t"
					+rs.getDouble(4));
		}
		System.out.println("--------------------------------------------------");
		 /*stas.setDistance(315.0);
		 stas.setPrice(93.0);
		 stationid1 = stas.setStationid1("�Ϻ�");
		 stationid2 = stas.setStationid2("�Ͼ�");*/
		stas.deleteSta_sta();
		stas.deleteSta_sta("beijing", "nanjing");
		stas.addSta_sta();
		stas.deleteSta_sta();
		 
		

	}

}

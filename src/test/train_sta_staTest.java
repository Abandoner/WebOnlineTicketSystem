package test;

import java.sql.ResultSet;
import java.sql.SQLException;

import bean.tra_sta_sta;

public class train_sta_staTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
        tra_sta_sta ss=new tra_sta_sta();
      //检查该站点在train_station表中是否已经存在
        ss.hasLogin("D5402", "苏州");
        ss.hasLogin("D5402", "北京");
        System.out.println("------------------------------");
        ResultSet rs=ss.getTrainStation();
        try {
			while(rs.next()){
				System.out.println(rs.getString(1)+"\t"+rs.getString(2)+"\t"+
						rs.getInt(3)+"\t"+rs.getString(4)+"\t"+rs.getString(5));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		 System.out.println("------------------------------");
		 ss.addTra_Sta();
		 ss.deleteTra_Sta();
		 ss.deleteTra_Sta("D5402", "苏州");
	}

}

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
        ResultSet rs=query.Trainquery("1234");//�����β�ѯ�ó��ξ���������վ��
        while(rs.next()){
        	System.out.println(rs.getString(1)+"\t"+rs.getString(2)+"\t"+rs.getInt(3)+"\t"+rs.getString(4));
        }
        //��ѯ��������վ��ĳ��μ���
        ResultSet rs1=query.Trainidquery("��ɽ", "����");
        	while(rs1.next()){
        		System.out.println(rs1.getString(1)+"\t"+rs1.getString(2)+"\t"+rs1.getString(3));
        	}
        //��ȡ�ó��ε���λ��
        ResultSet rs2=query.getMaxseat("D5406");
        while(rs2.next()){
        	System.out.println(rs2.getInt(1));
        }
      //��˳���ѯ��������վ��֮�������վ��
        ResultSet rs3=query.Stationvarquery("D5402", 2, 6);
        while(rs3.next()){
        	System.out.println(rs3.getString(1));
        }
      //��ѯĳ�����ε�ĳ��վ���˳���
        ResultSet rs4=query.Sequencequery("D5402", "����");
        while(rs4.next()){
        	System.out.println(rs4.getInt(1));
        }
        ResultSet rs5=query.Stationquery("��");
        while(rs5.next()){
        	System.out.println(rs5.getString(1)+"\t"+rs5.getString(2)+"\t"+rs5.getString(3)+"\t"+rs5.getString(4)+"\t"+rs5.getString(5)+"\t");
        }
        ResultSet rs6=query.getStationdistance("����","�Ͼ�");
        while(rs6.next()){
        	System.out.println(rs6.getDouble(1));
        }
        

	}

}

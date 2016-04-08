package test;

import java.sql.ResultSet;
import java.sql.SQLException;

import bean.book;

public class BookTest {

	/**
	 * @param args
	 * @throws SQLException 
	 */
	public static void main(String[] args) throws SQLException {
		book book=new book();
		book.setuserId("111");
		book.setAfford(89.0);
		book.setCountnum(1000);
		book.setDestination("12212");
		book.setSub(3131);
		book.setStartplace("shanghai");
		book.getuserId();
		System.out.println(book.getAfford());
		System.out.println(book.getCountnum());
		System.out.println(book.getBook());
		
		
		ResultSet rs1=book.getBook();
		while(rs1.next()){
			System.out.println(rs1.getString(1)+"\t"+rs1.getString(2)+"\t"+rs1.getString(3)+"\t"
					+rs1.getString(4)+"\t"+rs1.getInt(5)+"\t"+rs1.getDouble(6));
		}
		//book.deleteBook();
		//int rs2=book.deleteBook("sd1001", "D5402");
		

	}

}

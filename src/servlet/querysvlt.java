package servlet;

import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import bean.query;
import bean.ticket;

public class querysvlt extends HttpServlet {

	public querysvlt() {
		super();
	}

	@Override
	public void destroy() {
		super.destroy();

	}

	boolean flag;

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8"); // 设置字符编码
		String stationid = req.getParameter("name2"); // 获取地址ID1
		String stationid1 = req.getParameter("name3");// 获取地址ID2
		int success = 0;
		String action = req.getParameter("action");
		String message = "";

		if ("addressquery".equalsIgnoreCase(action)) {
			flag = true;
			ResultSet rs = doAddressQuery(req, res);

			if (flag)
				sendBean(req, res, rs, "/addressqueryresult.jsp");
		}

	}

	public ResultSet doAddressQuery(HttpServletRequest req,
			HttpServletResponse res) throws ServletException, IOException {
		query q = new query();
		ticket t = new ticket();
		String stationid = req.getParameter("name2");
		String stationid1 = req.getParameter("name3");
		ResultSet rs = q.Trainidquery(stationid, stationid1);
		String a[] = new String[100];
		String b[] = new String[100];
		int i = 0;
		int j = 0;
		int m = 0;
		int sum = 0;
		int temp;
		int temp1;
		try {
			while (rs.next()) {
				String trainid = rs.getString("train_id");
				a[i] = trainid;
				ResultSet rs1 = q.Sequencequery(a[i], stationid);
				temp = rs1.getInt("sequence");
				ResultSet rs2 = q.Sequencequery(a[i], stationid1);
				temp1 = rs2.getInt("sequence");
				ResultSet rs3 = q.Stationvarquery(trainid, temp, temp1);
				while (rs3.next()) {
					stationid = rs3.getString("station_id");
					b[j] = stationid;
					System.out.print(b[j] + "|");
					j++;
				}

				for (m = 0; m < b.length; m++) {
					ResultSet rs4 = q.getStationdistance(b[m], b[m + 1]);
					int dis = rs4.getInt("distance");
					System.out.println(dis + "|");
					ResultSet rs5 = q.getUnitPrice();
					int uni = rs5.getInt("unit_price");
					System.out.println(uni + "|");
					int price = dis * uni;
					sum = sum + price;
					System.out.println(sum);
				}

			}
		} catch (SQLException ex) {
			doError(req, res, "没有该信息");
			System.out.println("没有该信息");
		}
		return rs;

	}

	public void sendBean(HttpServletRequest req, HttpServletResponse res,
			ResultSet rs, String target) throws ServletException, IOException {
		req.setAttribute("query", rs);
		RequestDispatcher rd = getServletContext().getRequestDispatcher(target);
		rd.forward(req, res);
	}

	public void doError(HttpServletRequest req, HttpServletResponse res,
			String str) throws ServletException, IOException {

		flag = false;
		req.setAttribute("problem", str);
		RequestDispatcher rd = getServletContext().getRequestDispatcher(
				"/adminerror.jsp");
		rd.forward(req, res);
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		doGet(req, res);

	}

}

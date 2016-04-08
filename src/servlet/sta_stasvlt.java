package servlet;

import java.io.*;
import java.sql.*;

import javax.servlet.*;
import javax.servlet.http.*;
import bean.sta_sta;
import bean.query;

public class sta_stasvlt extends HttpServlet {

	public sta_stasvlt() {
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
		req.setCharacterEncoding("UTF-8");
		String stationid1 = new String(req.getParameter("stationid1").getBytes(
				"ISO-8859-1"), "utf-8");
		String stationid2 = new String(req.getParameter("stationid2").getBytes(
				"ISO-8859-1"), "utf-8");
		System.out.println(stationid1 + "@@@@@@@@@@@@@@@@@");
		System.out.println(stationid2 + "!!!!!!!!!!!!!!!!!");
		int success = 0;
		String action = req.getParameter("action");
		sta_sta s = null;
		String message = "";

		if ("new".equalsIgnoreCase(action)) {// 添加
			flag = true;
			s = doNew(req, res);

			if (flag)
				sendBean(req, res, s, "/sta_sta.jsp");
		}
		if ("update".equalsIgnoreCase(action)) {// 更新
			flag = true;
			s = doUpdate(req, res);
			if (flag)
				sendBean(req, res, s, "/sta_sta.jsp");
		}

		if ("delete".equalsIgnoreCase(action)) {// 删除
			try {
				success = doDelete(stationid1, stationid2);
			} catch (SQLException e) {
			}
			System.out.print(success + "删除成功");
			if (success != 1) {
				doError(req, res,
						"sta_stasvlt: Delete unsuccessful. Rows affected: "
								+ success);
				System.out.println("delete infomation fail");
			} else {

				RequestDispatcher rd = getServletContext()
						.getRequestDispatcher("/sta_sta.jsp");
				rd.forward(req, res);
			}
		}
	}

	public sta_sta doNew(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		sta_sta s = new sta_sta();
		query q = new query();
		String stationid1 = req.getParameter("stationid1");
		String stationid2 = req.getParameter("stationid2");
		double dis = Double.parseDouble(req.getParameter("distance"));
		double uni = 0;
		ResultSet rs = q.getUnitPrice();
		try {
			while (rs.next()) {
				uni = rs.getDouble("unit_price");
			}
		} catch (SQLException ex) {
		}
		double price = dis * uni;
		if (hasLogin(req, res, stationid1, stationid2)) {
			s.setStationid1(stationid1);
			s.setStationid2(stationid2);
			s.setDistance(dis);
			s.setPrice(price);
			s.addSta_sta();
		}
		return s;
	}

	public sta_sta doUpdate(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		sta_sta s = new sta_sta();
		query q = new query();
		double dis = Double.parseDouble(req.getParameter("distance"));
		System.out.println(dis + "6%%%%%%%%%%%%%%%%%%");
		String id1 = req.getParameter("stationid1");
		String id2 = req.getParameter("stationid2");
		System.out.println(id1 + "%%%%%%%%%%%%%%%%");
		System.out.println(id2 + "%%%%%%%%%%%%%%%%");

		double uni = 0;
		ResultSet rs = q.getUnitPrice();
		try {
			while (rs.next()) {
				uni = rs.getDouble("unit_price");
			}
		} catch (SQLException ex) {
		}
		System.out.println(uni + "66666666666666666666");
		double price = dis * uni;
		System.out.println(price + "6*********************6");

		s.setStationid1(id1);
		s.setStationid2(id2);
		s.setDistance(dis);
		s.setPrice(price);
		s.updateSta_sta();
		return s;
	}

	public int doDelete(String id1, String id2) throws SQLException {
		int num = 0;
		sta_sta s = new sta_sta();
		num = s.deleteSta_sta(id1, id2);
		return num;
	}

	public void sendBean(HttpServletRequest req, HttpServletResponse res,
			sta_sta ss, String target) throws ServletException, IOException {
		req.setAttribute("sta_sta", ss);
		RequestDispatcher rd = getServletContext().getRequestDispatcher(target);
		rd.forward(req, res);
	}

	public void doError(HttpServletRequest req, HttpServletResponse res,
			String str) throws ServletException, IOException {

		flag = false;
		req.setAttribute("problem", str);
		RequestDispatcher rd = getServletContext().getRequestDispatcher(
				"/adminerror.jsp");// 跳转
		rd.forward(req, res);
	}

	public boolean hasLogin(HttpServletRequest req, HttpServletResponse res,
			String id1, String id2) throws ServletException, IOException {
		boolean f = true;
		String message = "对不起，该站站信息已经存在!";
		sta_sta s = new sta_sta();
		f = s.hasLogin(id1, id2);
		if (f == false) {
			doError(req, res, message);
			System.out.println("该站站信息已经存在！");
		}
		return f;
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		doGet(req, res);

	}

}

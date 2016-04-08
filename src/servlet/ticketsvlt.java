package servlet;

import java.io.*;
import java.sql.*;

import javax.servlet.*;
import javax.servlet.http.*;

import bean.ticket;
import bean.query;
import bean.book;

public class ticketsvlt extends HttpServlet {

	public ticketsvlt() {
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
		String ticketid = req.getParameter("ticketid");
		String userid = req.getParameter("userid");
		int success = 0;
		String action = req.getParameter("action");
		ticket t = null;
		String message = "";

		String tic_id = "";
		if ("order".equalsIgnoreCase(action)) {
			flag = true;
			t = doOrder(req, res);

			if (flag) {
				RequestDispatcher rd = getServletContext()
						.getRequestDispatcher("/orderstatuing.jsp");
				rd.forward(req, res);
			}
		}

		if ("deleteticket".equalsIgnoreCase(action)) {
			try {
				success = doDeleteticket(req, res, ticketid);
			} catch (SQLException e) {
			}
			System.out.print(success);
			if (success != 1) {
				doadminError(req, res,
						"ticketsvlt: Delete unsuccessful. Rows affected: "
								+ success);
			} else {
				res.sendRedirect("orderinfo.jsp");
			}

		}

		if ("delete".equalsIgnoreCase(action)) {
			try {
				success = doDelete(req, res, ticketid);
			} catch (SQLException e) {
			}
			System.out.print(success);
			if (success != 1) {
				doError(req, res,
						"ticketsvlt: Delete unsuccessful. Rows affected: "
								+ success);
			} else {
				res.sendRedirect("orderstatus.jsp");
			}

		}
	}

	public ticket doOrder(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		ticket t = new ticket();
		ticket tic = new ticket();
		query q = new query();
		String ticketid = "";
		int seat = 0;
		int maxseat = 0;
		String userid = req.getParameter("userid");
		String startplace = req.getParameter("startplace");
		String destination = req.getParameter("destination");
		String type = req.getParameter("typeid");
		String starttime = req.getParameter("startdate");
		String ordertime = req.getParameter("startdate_hide");
		String trainid = req.getParameter("trainid");
		String startdate = req.getParameter("startdate");
		String trainsid = req.getParameter("trainid");
		int countnum = Integer.parseInt(req.getParameter("countnumber"));
		double dingjin = 0.0;
		String countno = "";
		int ticketnum = 0;
		dingjin = Double.valueOf(req.getParameter("dingjin")).doubleValue();
		// 剩余票数
		ResultSet r = q.getMaxseat(trainid);
		try {
			while (r.next()) {
				ticketnum = r.getRow();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// 以下为求价格
		String stationid = req.getParameter("startplace");
		String stationid1 = req.getParameter("destination");
		ResultSet rs = q.Trainidquery(stationid, stationid1);
		String a[] = new String[100];
		String b[] = new String[100];
		int i = 0;
		int j = 0;
		int m = 0;
		double sum = 0;
		int temp = 0;
		int temp1 = 0;
		try {
			while (rs.next()) {
				String train_id = rs.getString("train_id");
				a[i] = train_id;

				ResultSet rs1 = q.Sequencequery(a[i], stationid);
				while (rs1.next()) {
					temp = rs1.getInt("sequence");
				}
				ResultSet rs2 = q.Sequencequery(a[i], stationid1);
				while (rs2.next()) {
					temp1 = rs2.getInt("sequence");
				}
				ResultSet rs3 = q.Stationvarquery(train_id, temp, temp1);
				while (rs3.next()) {
					stationid = rs3.getString("station_id");
					b[j] = stationid;
					j++;
				}

				int dis = 0;
				double uni = 0;
				for (m = 0; m < length(b); m++) {
					ResultSet rs4 = q.getStationdistance(b[m], b[m + 1]);
					while (rs4.next()) {
						dis = rs4.getInt("distance");
					}
					ResultSet rs5 = q.getUnitPrice();
					while (rs5.next()) {
						uni = rs5.getDouble("unit_price");
					}
					double price = dis * uni;
					sum = sum + price;
				}
				i++;
			}
		} catch (SQLException ex) {
			doError(req, res, "订票失败");
		}

		double price1 = sum * countnum;
		double afford = sum * countnum - dingjin;
		countno = trainid + String.valueOf(countnum) + userid;
		if (isTrue(req, res, ticketnum, countnum)) {
			for (int a1 = 0; a1 < countnum; a1++) {

				ticketid = tic.getTicket_id(trainid);
				tic.updateTicket(ticketid);
				// trainid+String.valueOf(seat);
				t.setticketId(ticketid);
				t.setStartplace(startplace);
				t.setDestination(destination);
				t.setStartdate(startdate);
				t.setType(type);
				t.setTrainid(trainid);

				t.setPrice(price1);
				t.setSeat(seat);
				t.updateTicket(ticketid);
				t.addBook(ticketid, startplace, destination, userid, dingjin,
						countno, afford, starttime, ordertime);
				t.updateTicket(ticketid);
			}
			System.out.println(ticketid);

		}
		return t;

	}

	public int doDelete(HttpServletRequest req, HttpServletResponse res,
			String tid) throws ServletException, SQLException {
		int num = 0;
		ticket t = new ticket();
		book b = new book();

		String ticid = tid;
		// 剩余票数
		t.updateDeletBook(ticid);
		b.deleteBook(ticid);

		return 1;
	}

	public int doDeleteticket(HttpServletRequest req, HttpServletResponse res,
			String tid) throws ServletException, SQLException {
		int num = 0;
		ticket t = new ticket();
		book b = new book();

		String ticid = tid;
		// 剩余票数
		t.deleteTicket(ticid);
		b.deleteBook(ticid);

		return 1;
	}

	public void sendBean(HttpServletRequest req, HttpServletResponse res,
			ticket tt, String target) throws ServletException, IOException {
		req.setAttribute("ticket", tt);
		RequestDispatcher rd = getServletContext().getRequestDispatcher(target);
		rd.forward(req, res);
	}

	public void doError(HttpServletRequest req, HttpServletResponse res,
			String str) throws ServletException, IOException {

		flag = false;
		req.setAttribute("problem", str);
		RequestDispatcher rd = getServletContext().getRequestDispatcher(
				"/zhuceError.jsp");
		rd.forward(req, res);
	}

	public void doadminError(HttpServletRequest req, HttpServletResponse res,
			String str) throws ServletException, IOException {

		flag = false;
		req.setAttribute("problem", str);
		RequestDispatcher rd = getServletContext().getRequestDispatcher(
				"/adminerror.jsp");
		rd.forward(req, res);
	}

	public boolean hasLogin(HttpServletRequest req, HttpServletResponse res,
			String ticketid) throws ServletException, IOException {
		boolean f = true;
		String message = "对不起，该车票已经存在!";
		ticket t = new ticket();
		f = t.hasLogin(ticketid);
		if (f == false) {
			doError(req, res, message);
		}
		return f;
	}

	public int length(String a[]) {
		int i = 0;
		int count = 0;
		for (i = 0; i < a.length; i++)
			if (a[i] != null)
				count++;
		return count;
	}

	public boolean isTrue(HttpServletRequest req, HttpServletResponse res,
			int ticketnum, int countnum) throws ServletException, IOException {
		boolean f = true;
		String message = "";

		if (ticketnum <= 0) {
			f = false;
			message = "票已售完！";
			if (flag)
				doError(req, res, message);
		}
		if (ticketnum < countnum) {
			f = false;
			message = "票数不足！";
			if (flag)
				doError(req, res, message);
		}

		return f;

	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		doGet(req, res);

	}

}
